#include "main.h"

float red=1,green=1,blue=1,qww=0.001;
char sy[20]="0\n";
char sx[20]="0\n";
float posx,posy,radiuss,pgrad1,pgrad2;
void sectorgraph(float pbegin,float prc,char s[20],int rk,int gk,int bk,int pos){
    setcolor(rk,gk,bk);
    DrawPie(posx,posy,radiuss,radiuss,pbegin,pbegin+360*prc+0.2);
    bar(posx+radiuss+50,pos,posx+radiuss+75,pos+25);
    drawtext(posx+radiuss+75,pos+20,s);
}
void display()
{
    glClearColor(red, green, blue, 1);
    glClear(GL_COLOR_BUFFER_BIT);

    posx=400;
    posy=400;
    radiuss=250;

    setcolor(255,1,1);
    pgrad2=0;

    sectorgraph(pgrad2,0.4099," - ADSL - 1956(40.99%)\n",255,1,1,150);
    pgrad2=pgrad2+360*0.4099;
    sectorgraph(pgrad2,0.4047," - Ethernet - 1931 (40,47%)\n",250,100,1,200);
    pgrad2=pgrad2+360*0.4047;
    sectorgraph(pgrad2,0.0539," - i am not know... - 257 (5,39%)\n",1,255,1,250);
    pgrad2=pgrad2+360*0.0539;
    sectorgraph(pgrad2,0.0528," - GPRS - 252 (5,28%)\n",1,1,255,300);
    pgrad2=pgrad2+360*0.0528;
    sectorgraph(pgrad2,0.0421," - Free Wi-Fi - 201 (4,21%)\n",1,100,100,350);
    pgrad2=pgrad2+360*0.0421;
    sectorgraph(pgrad2,0.026," - WiMAX - 126 (2,64%) \n",100,255,100,400);
    pgrad2=pgrad2+360*0.0264;
    sectorgraph(pgrad2,0.0103," - Dial-up - 49 (1,03%) \n",1,255,255,450);
    pgrad2=pgrad2+360*0.0103;

    setcolor(1,1,1);
    drawtext(401,506,"40.99%\n");
    drawtext(572,377,"2.64%\n");
    drawtext(570,330,"4.21%\n");
    drawtext(540,280,"5.28%\n");
    drawtext(482,233,"5.39%\n");
    drawtext(280,280,"40.4%\n");
    glFlush();

}

int main(int argc, char **argv)
{
    setlocale(0,"rus");
    printf("нажмите для продолжения");
    getch();
    drawgraph(display,FNot,FNot,FNot,1024,800,"windows");
}
