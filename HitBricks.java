import java.awt.Point;
import java.awt.event.KeyEvent;

import GameCore.GameImage;
import GameCore.GameSprite;
import GameCore.NumCreator;

import com.rupeng.game.GameCore;


public class HitBricks implements Runnable
{
	public void run()
	{
	GameCore.confirm("是否开始游戏");
	 int Count=0;

		GameCore.setGameSize(800, 500);
		   GameImage imgs[]=new GameImage[10];
	        
	        for(int i=0;i<10;i++)
	        {   
	        	imgs[i]=new GameImage("砖块1.png");
	        	int num=imgs[i].getNum();
	        	imgs[i].setPosition((num-1)*100,0);
	        }
		 GameImage imgBoard = new GameImage("挡板.png");
	        imgBoard.setPosition(400, 450);
	         
	        GameSprite spriteBall = new GameSprite("ball1");
	        spriteBall.setPosition(100, 400);
	        spriteBall.playAnimate("rotate");
	        int ballVX=GameCore.rand(1,3);//x方向的分速度
	        int ballVY =-1;//y方向的分速度     
	       
	        while(true)
	        {
	            int keycode = GameCore.getPressedKeyCode();
	            if(keycode==KeyEvent.VK_LEFT&&imgBoard.getPosition().x>0)
	            {
	                imgBoard.moveLeft(3);//为了让挡板移动速度快一些，所以提供了一个一次可以移动多步的重载方法
	            }
	            else if(keycode==KeyEvent.VK_RIGHT
	                    &&imgBoard.getPosition().x+imgBoard.getSize().width<GameCore.getGameSize().width)
	            {
	                imgBoard.moveRight(3);
	            }
	            Point posBall = spriteBall.getPosition();       
	             
	            int xBall = posBall.x;
	            int yBall = posBall.y;
	            xBall+=ballVX*1;
	            yBall +=ballVY*1;
	             
	            spriteBall.setPosition(xBall, yBall);
	            if(xBall==0)//碰到了左边
	            {
	                ballVX=-ballVX;//横向反弹
	            }
	            if(xBall+10==GameCore.getGameSize().width)//胖到了右边
	            {
	                ballVX=-ballVX;//横向反弹
	            }
	            if(yBall==0)//碰到了上边
	            {
	                ballVY=-ballVY;
	            }
	            if(yBall+10==GameCore.getGameSize().height)//如果碰到下面就死了
	            {
	                //ballVY=-ballVY;
	            	GameCore.alert("You Lose!");
	                GameCore.exit();
	            }
	            if(spriteBall.isIntersectWith(imgBoard))//当球碰到挡板的时候Y方向速度翻转，也就是反弹
	            {
	                ballVY=-ballVY;
	            }
	            for(GameImage img:imgs)
	            {
	            if(spriteBall.isYIntersectWith(img)&&img.isAlive())
	            {
	            	ballVY=-ballVY;
	            	img.setAlive(false);
	            	img.hide();
	            	Count++;
	            
	            }
	            if(spriteBall.isXIntersectWith(img)&&img.isAlive())
	            {
	            	ballVX=-ballVX;
	            	img.setAlive(false);
	            	img.hide();
	            	Count++;
	            }
	            if(Count==imgs.length)
	            	GameCore.alert("You win!");
	
	            }
	        
	                         
	            GameCore.pause(5);
	        }
	}
	public static void main(String args[])
	{
		GameCore.start(new HitBricks());
	}

}
