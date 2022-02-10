#include "main.h"
float posx=400,posy=400;
float hh=250;float kk=0.1;



float f(float x)
{
    return x+cos(pow(x,0.52)+2);

   // return 3*sin(sqrt(x)+0.35*x-3.8);

}
void display(){
    float i,j;

    float sttt=0.001;

    float hs=10;
    int pp[100];


    char st[20]="0\n";
    char sy[10]="y\n";
    char sx[10]="x\n";
    char sf[30]="gраafik_y=x+sin(x^0.52+2)\n";
    glClearColor(1.0, 1.0, 1.0, 1.0);
    glClear(GL_COLOR_BUFFER_BIT);
    setcolor(250,100,1);

    for (i=sttt;i<=50;i+=sttt){
        if ((i>0.5)&&(i<1.0)){setcolor(255,1,1);linestyle(1,4);} else {setcolor(250,130,51);linestyle(1,2);}
        line((i-sttt)*hh+posx,-f(i-sttt)*hh+posy,i*hh+posx,-f(i)*hh+posy);
    }
    setcolor(0,0,0);
    linestyle(1,2);
    line(0,posy,width,posy);
    line(posx,0,posx,height);
    linestyle(1,1);
    int k=1;
    for (i=hh*kk;i<=width-posx-40;i+=hh*kk){
        if (k==0){
        k=1-k;
        circle(posx+i,posy,3);

        sy[6]='\n';
        sprintf(sy,"%2.2f",i/hh);
        drawtext(posx+i-20,posy+20,sy);
        }
        else {
            k=1-k;
            circle(posx+i,posy,2);
        }
    }
    k=1;
    for (i=hh*kk;i<=posx;i+=hh*kk){

        if (k==0){
            k=1-k;
        sy[6]='\n';
        sprintf(sy,"%2.2f",-i/hh);
         drawtext(posx-i-20,posy+20,sy);
        circle(posx-i,posy,3);
        }
        else {
            k=1-k;
            circle(posx-i,posy,2);
        }
    }
    k=1;
    for (i=hh*kk;i<=height-posy;i+=hh*kk){

       if (k==0){

        sy[6]='\n';
        sprintf(sy,"%2.2f",-i/hh);
        drawtext(posx-50,posy+i+10,sy);
        circle(posx,posy+i,3);
       } else
       {
        circle(posx,posy+i,2);
       }
       k=1-k;
    }
    k=1;
    for (i=hh*kk;i<=posy;i+=hh*kk){

       if (k==0){
            sy[3]='\n';
        sy[6]='\n';
        sprintf(sy,"%2.1f",i/hh);
        drawtext(posx-50,posy-i+10,sy);
        circle(posx,posy-i,3);
       } else
       {
        circle(posx,posy-i,2);
       }
       k=1-k;
    }

    pp[0]=posx;
    pp[1]=height;
    pp[2]=posx+hs;
    pp[3]=height-hs;
    pp[4]=posx-hs;
    pp[5]=height-hs;
    fillpoly(5,&pp[0]);
    pp[0]=width;
    pp[1]=height-posy;
    pp[2]=width-hs;
    pp[3]=height-posy+hs;
    pp[4]=width-hs;
    pp[5]=height-posy-hs;
    fillpoly(5,&pp[0]);
    sy[0]='y';
    sy[1]='\n';
    drawtext(posx-20,posy+30,st);
    drawtext(posx+20,20,sy);
    drawtext(width-20,posy-20,sx);
    drawtext(20,20,sf);
    glFlush();


}
void processSpecialKeys(int key, int x, int y){
    switch(key) {
		case GLUT_KEY_LEFT :
           posx+=33;

            break;
		case GLUT_KEY_RIGHT :
		    posx-=33;
		    break;
		case GLUT_KEY_UP :
		    posy+=33;
		    break;
        case GLUT_KEY_DOWN  :
            posy-=33;
		    break;
        case GLUT_KEY_F1  :
		    hh=hh*1.2;
		    posx=posx*1.2;
		    posy=posy*1.2;
		    break;
        case GLUT_KEY_F2 :
            hh=hh/1.2;
            posx=posx/1.2;
		    posy=posy/1.2;
		    break;
	}

	if ((hh<=180)&&(hh>10)){
      kk=1;
	}
	if ((hh<=2000)&&(hh>250)){
      kk=0.1;
	}
	if ((hh>2000)&&(hh<10000)){
      kk=0.01;
	}
	display();
}

int main()
{

    setlocale (LC_ALL, "rus");
    printf("нажмите для продолжения");
    getch();
    drawgraph(display,FNot,FNot,processSpecialKeys,1024,800,"hello world");

}
