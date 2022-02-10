import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.TexturePaint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.xml.crypto.dsig.Transform;

public class MapGame  extends JPanel implements Runnable{
	protected Graphics2D g2d;
	//���������� �������� �� ���� ������� ���������
	Double k=80.;
	PlayerSnake player2=new PlayerSnake(50,256,3.,0.,0., 11.);
	PlayerSnake player=new PlayerSnake(30,256,0.8,5.,3., 5.);
	PlayerSnake player3=new PlayerSnake(15,256,1.0,5.,9., 9.);
	Double x1=0.;Double y1=0.;
	int delay=25;
	boolean correct=true;
	boolean flag=true;
	boolean isshow=true;
	long time=System.currentTimeMillis();
	long timel=time+33;
	long timefps=time;
	long countfps=0;
	
	int height=1040,width=1900;
	int height2=1040/2,width2=1900/2;
	MapGame(int left, int top, int w, int h){
		setLayout(null);
		setBounds(left,top,w,h);
		addMouseMotionListener(new MouseAdapter(){
			
			public void mouseMoved(MouseEvent event) {
				
				x1=(double)event.getX();
				y1=(double)event.getY();
				
				
				
			}
			public void mouseDragged(MouseEvent event) {
				x1=(double)event.getX();
				y1=(double)event.getY();
				
			}
		
		});
		addMouseListener(new MouseAdapter(){
			
			public void mousePressed(MouseEvent event) {
				
				x1=(double)event.getX();
				y1=(double)event.getY();
				time=System.currentTimeMillis();
				player.fullspeed(time);
				
			}
			public void mouseReleased(MouseEvent event) {
				
				x1=(double)event.getX();
				y1=(double)event.getY();
				time=System.currentTimeMillis();
				player.nofullspeed(time);
				
			}
		
		});
		player2.setColor(230, 80, 50);
		//player2.fullspeed(time);
		player3.setColor(50, 80, 80);
	}
	
	double player2tox=30.;
	double player2toy=20.;
	boolean flagboost=true;
	
	
	public void run(){
		if(correct){
			while(true){
				try {
					//���� �������� �� ��������� ��������
					if(flag){
						
						Thread.sleep(5);
						if(isshow){
						time=System.currentTimeMillis();
						time2+=time-timel;
						if(time-timefps>500){
							
							System.out.println(countfps*(time-timefps)/500*2);
							countfps=0;
							timefps=time;
						}
						if(time-timel>2000){timel=time-2000;}
						player.step((x1-width2)/k,(y1-height2)/k,time-timel);
						Double deat=Math.sqrt((player2tox-player2.masx[0])*(player2tox-player2.masx[0])+(player2toy-player2.masy[0])*(player2toy-player2.masy[0]));
						if(deat<player2.radius){
							player2tox=Math.random()*60.-30.;
							player2toy=Math.random()*60.-30.;
						}
						if(deat<player2.radius*10){
							if(flagboost){
								player2.nofullspeed(time);
								flagboost=false;
							}
							
						}else{
							flagboost=true;
							player2.fullspeed(time);
						}
						player2.step(player2tox-player2.masx[0],player2toy-player2.masy[0],time-timel);
						player3.step(player.masx[player.length-1]-player3.masx[0],player.masy[player.length-1]-player3.masy[0],time-timel);
						timel=time;
						isshow=false;
						
						repaint();
						countfps++;
						}
						
						
					//����� ������ �� ������
					}else{
						Thread.sleep(1);
					}
			
				} catch (InterruptedException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
			}
		}
	}
	BufferedImage img;
	BufferedImage bi;
	boolean bif=true;
	int toint(double f){
		if((double)Math.round(f)>f){return (int)Math.round(f)-1;}
		return (int)Math.round(f);
	}
	public Graphics2D gg;
	
	AffineTransform at=AffineTransform.getTranslateInstance(0, 0);
	GradientPaint gp22=new GradientPaint(0, 0, new Color(20,120,20),80, 80,new Color(0,100,0));
	@Override
	protected void paintComponent(Graphics g) {
			
		super.paintComponent(g);
		g2d= (Graphics2D) g;
		xpos=player.masx[0];
		ypos=player.masy[0];
		img=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		gg=img.createGraphics();
		gg.setClip ( 0, 0, width, height );
		gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		double w=10;
		if(bif){
			
			bi = new BufferedImage(width+(int)(w*k), height+(int)(w*k),BufferedImage.TYPE_INT_RGB);
			Graphics2D ggs=bi.createGraphics();
			
			BufferedImage bi2=null;
			try {
				bi2=ImageIO.read(new File("texture.bmp"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Rectangle r = new Rectangle(0, 0, (int)(w*k), (int)(w*k));
		    ggs.setPaint(new TexturePaint(bi2, r));
		    Rectangle rect = new Rectangle(0,0,width+(int)(w*k),height+(int)(w*k));
		    ggs.fill(rect);
		    
			bif=false;
			
		}
		
	  gg.drawImage(bi, -(int)((xpos*k-toint(xpos/w)*w*k)), -(int)((ypos*k-toint(ypos/w)*w*k)), null);
	    
		
		
		
		
		
		
		gg.setColor(new Color(200,200,200,80));
		gg.fillOval((int)(k*(player2tox-xpos)+width2),(int)(k*(player2toy-ypos)+height2),(int)(k*0.5),(int)(k*0.5));
		
		xpos=player.masx[0];
		ypos=player.masy[0];
		player.draw();
		
		player2.draw();
		player3.draw();
		g2d.drawImage(img, at,null);
		
		isshow=true;
        
	}
	Double xpos=0.;
	Double ypos=0.;
	double mm=0.1;
	double tt=0.2;
	double tm=0.0035;
	double time2=0;
	
	
	
	
	GradientPaint gp1=new GradientPaint(0, 0, new Color(50,50,230),200, 100,new Color(225, 225, 255));
	
	class PlayerSnake extends Snake{
		PlayerSnake(int len, int lenmax, Double rads, Double x, Double y, Double speed) {
			super(len, lenmax, rads, x, y, speed);
			// TODO Auto-generated constructor stub
		}
		AffineTransform at;
		boolean bigfl=true;
		BufferedImage bi;float hk;
		
		@Override
		void draw(){
			
			double rleg=0.5;
			Double hspeed=rleg/3*2;
			
				if(bigfl){ 
					Graphics2D big;
					hk= (float) ( this.radius*k*1.8-this.radius*k);
					float radius = (float) (this.radius*k*1.8);
				bi = new BufferedImage((int)((radius)*2), (int)((radius)*2),BufferedImage.TYPE_INT_ARGB_PRE);
				 big= bi.createGraphics();
				big.setColor(new Color(200,200,200,80));
				 //RadialGradientPaint p =new RadialGradientPaint(new Double(1.), new Double(1.), new Double(1.),{new Color(200,200,200,80),new Color(200,200,255,80),CycleMethod.NO_CYCLE);
				Point2D center = new Point2D.Float(radius,radius );
		       
		        float[] dist = {0.7f, .99f};
		        Color[] colors = {new Color(255,255,255,255), new Color(255,255,255,0)};
		        RadialGradientPaint  paint = new RadialGradientPaint(center,  radius-hk/2, dist, colors,CycleMethod.REFLECT);
		        big.setPaint(paint);
		        big.setStroke(new BasicStroke(hk));
		        big.drawOval((int)hk, (int)hk,(int) (this.radius*k*2) ,(int) (this.radius*k*2)  );
		        //big.setColor(new Color(redcol,greencol,bluecol));
		        //big.fillOval((int)hk, (int)hk,(int) (this.radius*k*2) ,(int) (this.radius*k*2)  );
				bigfl=false;
				}
				
				for(int i=length-1;i>=1;i--){
				if(fullspeed){
					
					//at=AffineTransform.getTranslateInstance(, );
					if((int)(k*(masx[i]+radius-xpos)+width2)>0&&(int)(k*(masx[i]-2*radius-xpos)+width2)<width&&(int)(k*(masy[i]+radius-ypos)+height2)>0&&(int)(k*(masy[i]-2*radius-ypos)+height2)<height){
					gg.drawImage(bi,(int)(k*(masx[i]-radius-xpos)-hk+width2),(int)(k*(masy[i]-radius-ypos)-hk+height2) ,null);
				
						
					}}
				}
			for(int i=length-1;i>=1;i--){ 
				
				if((int)(k*(masx[i]+radius-xpos)+width2)>0&&(int)(k*(masx[i]-2*radius-xpos)+width2)<width&&(int)(k*(masy[i]+radius-ypos)+height2)>0&&(int)(k*(masy[i]-2*radius-ypos)+height2)<height){
					
				
				
				
					double radiuss=radius+mm*(Math.cos(-tt*i+time2*tm)+1);
					double vx=(masx[i-1]-masx[i])/Math.sqrt((masx[i-1]-masx[i])*(masx[i-1]-masx[i])+(masy[i-1]-masy[i])*(masy[i-1]-masy[i]))*radiuss;
					double vy=(masy[i-1]-masy[i])/Math.sqrt((masx[i-1]-masx[i])*(masx[i-1]-masx[i])+(masy[i-1]-masy[i])*(masy[i-1]-masy[i]))*radiuss;;
					
					
					double vectx1=TransitionX(vx,vy,0.,0.,Math.toRadians(70.5));
					double vectx2=TransitionX(vx,vy,0.,0.,Math.toRadians(-70.5));
					double vecty1=TransitionY(vx,vy,0.,0.,Math.toRadians(70.5));
					double vecty2=TransitionY(vx,vy,0.,0.,Math.toRadians(-70.5));
					double leg1x=k*(masx[i]-xpos+vectx1);
					double leg1y=k*(masy[i]-ypos+vecty1);
					double leg2x=k*(masx[i]-xpos+vectx2);
					double leg2y=k*(masy[i]-ypos+vecty2);
					if(fullspeed){
						gg.setColor(new Color(160,160,160));
					}else{
						gg.setColor(new Color(120,100,100));
					}
					gg.fillOval((int)leg1x+width2-(int)(rleg*k/2), (int)leg1y+height2-(int)(rleg*k/2), (int)(rleg*k), (int)(rleg*k));
					gg.fillOval((int)leg2x+width2-(int)(rleg*k/2), (int)leg2y+height2-(int)(rleg*k/2), (int)(rleg*k), (int)(rleg*k));
					
					
					
					
					gp1 = new GradientPaint((int)(k*(masx[i]-vx-xpos)+width2), (int)(k*(masy[i]-vy-ypos)+height2), new Color((int)(15.*(Math.cos(-tt*i+time2*tm)))+redcol,(int)(15.*(Math.cos(-tt*i+time2*tm)))+greencol,(int)(15.*(Math.cos(-tt*i+time2*tm)))+bluecol),(int)(k*(masx[i]+vx-xpos)+width2), (int)(k*(masy[i]+vy-ypos)+height2),new Color(225, 225, 255));
					gg.setPaint(gp1);
					//gg.setColor(new Color((int)(15.*(Math.cos(-tt*i+time2*tm)))+redcol,(int)(15.*(Math.cos(-tt*i+time2*tm)))+greencol,(int)(15.*(Math.cos(-tt*i+time2*tm)))+bluecol));
					gg.fillOval((int)(k*(masx[i]-radiuss-xpos)+width2), (int)(k*(masy[i]-radiuss-ypos)+height2), (int)(k*radiuss*2), (int)(k*radiuss*2));
					//gg.setColor(new Color(70,70,100));
					//gg.drawOval((int)(k*(masx[i]-radiuss-xpos)+width2), (int)(k*(masy[i]-radiuss-ypos)+height2), (int)(k*radiuss*2), (int)(k*radiuss*2));
				
					
					gg.setColor(new Color(200,200,200));
					double rp=0.3;
					gg.fillOval((int)(k*(masx[i]-xpos+radiuss/radius*TransitionX(masx[i-1]-masx[i],masy[i-1]-masy[i],0.,0.,Math.PI)-rp/2)+width2-2), (int)(k*(masy[i]+radiuss/radius*TransitionY(masx[i-1]-masx[i],masy[i-1]-masy[i],0.,0.,Math.PI)-ypos-rp/2)+height2), (int)(rp*k), (int)(rp*k));
					
					
					
					
				
				}
			}
			
			double vectx1=radius*1.8/3*TransitionX(dxlast,dylast,0.,0.,Math.toRadians(30));
			double vectx2=radius*1.8/3*TransitionX(dxlast,dylast,0.,0.,Math.toRadians(-30));
			double vecty1=radius*1.8/3*TransitionY(dxlast,dylast,0.,0.,Math.toRadians(30));
			double vecty2=radius*1.8/3*TransitionY(dxlast,dylast,0.,0.,Math.toRadians(-30));
			double leg1x=k*(masx[0]-xpos+vectx1);
			double leg1y=k*(masy[0]-ypos+vecty1);
			double leg2x=k*(masx[0]-xpos+vectx2);
			double leg2y=k*(masy[0]-ypos+vecty2);
			double vx=(dxlast)*radius;
			double vy=(dylast)*radius;
			
			gp1 = new GradientPaint((int)(k*(masx[0]-vx-xpos)+width2), (int)(k*(masy[0]-vy-ypos)+height2), new Color(redcol,greencol,bluecol),(int)(k*(masx[0]+vx-xpos)+width2), (int)(k*(masy[0]+vy-ypos)+height2),new Color(180, 180, 180));
			gg.setPaint(gp1);
			gg.fillOval((int)(k*(masx[0]-radius-xpos)+width2), (int)(k*(masy[0]-radius-ypos)+height2), (int)(k*radius*2), (int)(k*radius*2));
			gg.setColor(new Color(70,70,100));
			gg.drawOval((int)(k*(masx[0]-radius-xpos)+width2), (int)(k*(masy[0]-radius-ypos)+height2), (int)(k*radius*2), (int)(k*radius*2));
			double reyes=2*radius/3;
			gg.setColor(new Color(220,220,220));
			gg.fillOval((int)leg1x+width2-(int)(reyes*k/2), (int)leg1y+height2-(int)(reyes*k/2), (int)(reyes*k), (int)(reyes*k));
			gg.fillOval((int)leg2x+width2-(int)(reyes*k/2), (int)leg2y+height2-(int)(reyes*k/2), (int)(reyes*k), (int)(reyes*k));
			double reyes2=reyes/2;
		
			leg1x=leg1x+k*(reyes2*vectxx/2);
			leg1y=leg1y+k*(reyes2*vectyy/2);
			leg2x=leg2x+k*(reyes2*vectxx/2);
			leg2y=leg2y+k*(reyes2*vectyy/2);
			gg.setColor(new Color(20,20,20));
			gg.fillOval((int)leg1x+width2-(int)(reyes2*k/2), (int)leg1y+height2-(int)(reyes2*k/2), (int)(reyes2*k), (int)(reyes2*k));
			gg.fillOval((int)leg2x+width2-(int)(reyes2*k/2), (int)leg2y+height2-(int)(reyes2*k/2), (int)(reyes2*k), (int)(reyes2*k));
			//gg.fillOval((int)(k*(masx[0]-radius-xpos)+width2), (int)(k*(masy[0]-radius-ypos)+height2), (int)(k*radius*2), (int)(k*radius*2));
			
			
		}
	}
}
