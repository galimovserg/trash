import java.awt.Graphics2D;

public class Snake implements Runnable{
	Double radius=0.;
	Double distans=0.;
	int maxlength=0;
	int length=0;
	Double speed=0.;
	Double masx[];
	Double masy[];
	long lasttime=0;
	long ttime=0;
	boolean isOkay=false;
	boolean fullspeed=false;
	Double dxlast=1.;
	Double dylast=0.;
	
	Double dlast=1.;
	Double amax=1.5;
	Double boost=0.;
	
	Snake(int len,int lenmax,Double rads,Double x,Double y,Double speed){
		if(len<=lenmax&&len>0&&rads>0&&speed>=0){
			length=len;
			maxlength=lenmax;
			radius=rads;
			distans=rads/3*2;
			this.speed=speed;
			this.boost=2*speed;
			masx=new Double[lenmax];
			masy=new Double[lenmax];
			masx[0]=x;
			masy[0]=y;
			for(int i=1;i<length;i++){
				masx[i]=x-distans*i;
				masy[i]=y;
			}
			isOkay=true;
		}
	}
	int redcol=50;
	int greencol=50;
	int bluecol=230;
	void setColor(int r, int g, int b){
		this.redcol=r;
		this.greencol=g;
		this.bluecol=b;
	}
	void fullspeed(long time){
		
		fullspeed=true;ttime=lasttime;
	}
	void nofullspeed(long time){
		lasttime=time;
		ttime=lasttime;
		fullspeed=false;
	}
	Double TransitionX(Double x,Double y,Double x0,Double y0, Double a){
		return  (x) * Math.cos(a) - (y ) * Math.sin(a);
	}
	Double TransitionY(Double x,Double y,Double x0,Double y0, Double a){
		return (y ) * Math.cos(a) + (x ) * Math.sin(a);
	}
	void stepshlang(Double VectorX, Double VectorY,int time){
		if(isOkay){
			double speedl=speed;
			if(fullspeed){
				speedl=2.*speed;
			}
			
			Double d=Math.sqrt((VectorX)*(VectorX)+(VectorY)*(VectorY));
			
			
			if(d>radius){
				//единичный вектор
				VectorX=VectorX/d;
				VectorY=VectorY/d;
				d=1.;
				
				//угол между предыдущим вектором и этим
				Double cosa=VectorX*dxlast+VectorY*dylast;
				Double da=0.;
				if(cosa!=1.){
					da=Math.toDegrees(Math.acos(cosa));
				}
				//если угол больше нужного
				if(Math.abs(da)>amax){
					
					Double VectorXX;
					Double VectorYY;
					if(VectorX*dylast-VectorY*dxlast>0.){
						VectorXX = TransitionX(dxlast,dylast ,0.,0., -Math.toRadians(amax));
						VectorYY = TransitionY(dxlast,dylast ,0.,0., -Math.toRadians(amax));
					}else{
						VectorXX =TransitionX(dxlast,dylast ,0.,0., Math.toRadians(amax));
						VectorYY = TransitionY(dxlast,dylast ,0.,0., Math.toRadians(amax));
					}
					Double dx=time*speedl/1000.*(VectorXX);
					Double dy=time*speedl/1000.*(VectorYY);
					masx[0]=masx[0]+dx;
					masy[0]=masy[0]+dy;
					dxlast=VectorXX;
					dylast=VectorYY;
					dlast=1.;
					
				}else{System.out.println(cosa+" "+da);
					Double dx=time*speedl/1000.*(VectorX);
					Double dy=time*speedl/1000.*(VectorY);
					masx[0]=masx[0]+dx;
					masy[0]=masy[0]+dy;
					dxlast=VectorX;
					dylast=VectorY;
					dlast=1.;
				}
			}else{
				Double dx=time*speedl/1000.*(dxlast);
				Double dy=time*speedl/1000.*(dylast);
				masx[0]=masx[0]+dx;
				masy[0]=masy[0]+dy;
				dlast=1.;
				
			}
			for(int i=1;i<length;i++){
					Double dd=Math.sqrt((masx[i-1]-masx[i])*(masx[i-1]-masx[i])+(masy[i-1]-masy[i])*(masy[i-1]-masy[i]));
					if(dd>distans){
						masx[i]=masx[i-1]+distans*(masx[i]-masx[i-1])/dd;
						masy[i]=masy[i-1]+distans*(masy[i]-masy[i-1])/dd;
					}else{
						return;
					}
					
			}
		}
	}
	
	
	double vectxx=1.,vectyy=0.;
	void step(Double VectorX, Double VectorY,long time){
		if(isOkay){
			ttime+=time;
			double speedl;
			double amaxl=amax;
			if(fullspeed){
				speedl=3*speed;
				amaxl=amax*3;
			}else{
				
				speedl=3*speed-boost*(ttime-lasttime)/1000;
				if(speedl<=speed){
					speedl=speed;
				}
				
			}
			
			Double d=Math.sqrt((VectorX)*(VectorX)+(VectorY)*(VectorY));
			
			
			if(d>0){
				//единичный вектор
				VectorX=VectorX/d;
				VectorY=VectorY/d;
				d=1.;
				vectxx=VectorX;
				vectyy=VectorY;
				//угол между предыдущим вектором и этим
				Double cosa=VectorX*dxlast+VectorY*dylast;
				Double da=0.;
				if(cosa!=1.){
					da=Math.toDegrees(Math.acos(cosa));
				}
				//если угол больше нужного
				if(Math.abs(da)>amaxl){
					
					Double VectorXX;
					Double VectorYY;
					if(VectorX*dylast-VectorY*dxlast>0.){
						VectorXX = TransitionX(dxlast,dylast ,0.,0., -Math.toRadians(amaxl));
						VectorYY = TransitionY(dxlast,dylast ,0.,0., -Math.toRadians(amaxl));
					}else{
						VectorXX =TransitionX(dxlast,dylast ,0.,0., Math.toRadians(amaxl));
						VectorYY = TransitionY(dxlast,dylast ,0.,0., Math.toRadians(amaxl));
					}
					Double dx=time*speedl/1000.*(VectorXX);
					Double dy=time*speedl/1000.*(VectorYY);
					masx[0]=masx[0]+dx;
					masy[0]=masy[0]+dy;
					dxlast=VectorXX;
					dylast=VectorYY;
					dlast=1.;
					
				}else{
					Double dx=time*speedl/1000.*(VectorX);
					Double dy=time*speedl/1000.*(VectorY);
					masx[0]=masx[0]+dx;
					masy[0]=masy[0]+dy;
					dxlast=VectorX;
					dylast=VectorY;
					dlast=1.;
				}
			}else{
				Double dx=time*speedl/1000.*(dxlast);
				Double dy=time*speedl/1000.*(dylast);
				masx[0]=masx[0]+dx;
				masy[0]=masy[0]+dy;
				dlast=1.;
				
			}
			for(int i=1;i<length;i++){
					Double dd=Math.sqrt((masx[i-1]-masx[i])*(masx[i-1]-masx[i])+(masy[i-1]-masy[i])*(masy[i-1]-masy[i]));
					if(dd>distans){
						masx[i]=masx[i-1]+distans*(masx[i]-masx[i-1])/dd;
						masy[i]=masy[i-1]+distans*(masy[i]-masy[i-1])/dd;
					}else{
						return;
					}
					
			}
		}
	}
	void grow(){
		
	}
	void draw(){
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1);
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
