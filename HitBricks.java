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
	GameCore.confirm("�Ƿ�ʼ��Ϸ");
	 int Count=0;

		GameCore.setGameSize(800, 500);
		   GameImage imgs[]=new GameImage[10];
	        
	        for(int i=0;i<10;i++)
	        {   
	        	imgs[i]=new GameImage("ש��1.png");
	        	int num=imgs[i].getNum();
	        	imgs[i].setPosition((num-1)*100,0);
	        }
		 GameImage imgBoard = new GameImage("����.png");
	        imgBoard.setPosition(400, 450);
	         
	        GameSprite spriteBall = new GameSprite("ball1");
	        spriteBall.setPosition(100, 400);
	        spriteBall.playAnimate("rotate");
	        int ballVX=GameCore.rand(1,3);//x����ķ��ٶ�
	        int ballVY =-1;//y����ķ��ٶ�     
	       
	        while(true)
	        {
	            int keycode = GameCore.getPressedKeyCode();
	            if(keycode==KeyEvent.VK_LEFT&&imgBoard.getPosition().x>0)
	            {
	                imgBoard.moveLeft(3);//Ϊ���õ����ƶ��ٶȿ�һЩ�������ṩ��һ��һ�ο����ƶ��ಽ�����ط���
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
	            if(xBall==0)//���������
	            {
	                ballVX=-ballVX;//���򷴵�
	            }
	            if(xBall+10==GameCore.getGameSize().width)//�ֵ����ұ�
	            {
	                ballVX=-ballVX;//���򷴵�
	            }
	            if(yBall==0)//�������ϱ�
	            {
	                ballVY=-ballVY;
	            }
	            if(yBall+10==GameCore.getGameSize().height)//����������������
	            {
	                //ballVY=-ballVY;
	            	GameCore.alert("You Lose!");
	                GameCore.exit();
	            }
	            if(spriteBall.isIntersectWith(imgBoard))//�������������ʱ��Y�����ٶȷ�ת��Ҳ���Ƿ���
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
