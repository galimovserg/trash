#include "main.h"

void display()
{
    int i,j;
    glClearColor(1, 1, 1, 1.0);
    glClear(GL_COLOR_BUFFER_BIT);
    setcolor(250,100,1);

    int hh=120;
    bar(hh,2*hh,7*hh,4*hh);
    for (i=2;i<=6;i++){
        setcolor(255,255,255);
        bar(i*hh-hh/4,2.5*hh,i*hh+hh/4,3.5*hh);
    }
    fillellipse(1.5*hh,3*hh,hh/8,hh/8);
    fillellipse(6.5*hh,3*hh,hh/8,hh/8);
    glFlush();
}
void keydowns(int key,int x,int y){

}
int main()
{
    setlocale(0,"rus");
    printf("нажмите для продолжения");
    getch();
    drawgraph(display,FNot,FNot,FNot,1024,800,"windows");

}
