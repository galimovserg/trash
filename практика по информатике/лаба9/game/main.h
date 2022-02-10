#include <time.h>
#include <GL/glut.h>
#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include <time.h>
#include <string.h>
#include <ctype.h>
#include <math.h>

const float DEG2RAD = 3.14159/180.0;
float rcolor; float gcolor; float bcolor;
int height, width;
int env=0;
//устанавливает текущий цвет кисти
void setcolor(int rl,int gl,int bl);
//устанавливает стиль и толщину пера
void linestyle(int style,int width);
//закрашивает область установленным цветом
void bar(int x1,int y1,int x2,int y2);
//рисует прямоугольник
void rectangle(int x1,int y1,int x2,int y2);
//рисует линию
void line(int x1,int y1,int x2,int y2);
//рисует эллипс с координатами центра и радиусами
void drawEllipse(float x,float y,float xradius, float yradius);
//рисует закрашенный эллипс с координатами центра и радиусами
void fillellipse(float x,float y,float xradius, float yradius);
//рисует закрашенный сектор эллипса
void DrawPie(float x,float y,float xradius, float yradius,float a1,float a2);
//рисует окружность
void circle(float x,float y,float radius);
//рисует многоугольник
void fillpoly(int cpoly,int *pl);
//выводит строку на экран( для латинского алфавита)
void drawtext(float x,float y, char s[]);
//задержка в миллисекундах
void delay(int ms);
//основная функция инициализации графического окна
void drawgraph(int *displ,int *loops,int *prKeys,int *prSpKeys,int *prSpMous,int w,int h,char winname[]);
//пустая функция Fnot не выполняется
void FNot(){
}
//displ - ссылка на функцию которая выполняется при сворачивании, открытии, разворачивании,
// изменении размера окна и т.п
//loops- ссылка на функцию которая выполняется в основном цикле
//prKeys-ссылка на функцию которая вызывается при нажатии кнопки клавиатуры
//prSpKeys-ссылка на функцию которая вызывается при нажатии функциональной кнопки клавиатуры
void drawgraph(int *displ,int *loops,int *prKeys,int *prSpKeys,int *prSpMouse,int w,int h,char winname[]){
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
    height=h;
    width=w;
    glutInitWindowSize(width,height);
    glutCreateWindow(&winname[0]);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(0, width, 0, height);
    if (displ!=&FNot){
    glutDisplayFunc(displ);
    }
    if (prSpKeys!=&FNot){
    glutSpecialFunc(prSpKeys);
    }
    if (prKeys!=&FNot){
    glutKeyboardFunc(prKeys);
    }
    if (loops!=&FNot){
    glutIdleFunc(loops);
    }
    if (prSpMouse!=&FNot){
    glutMouseFunc(prSpMouse);
    }
    glutMainLoop();
}
void setcolor(int rl,int gl,int bl)
 {
     rcolor=(rl+0.00001)/255;
     gcolor=(gl+0.00001)/255;
     bcolor=(bl+0.00001)/255;
     glColor3f(rcolor, gcolor, bcolor);
 }
void linestyle(int style,int width)
 {
     switch (style) {

     }
     glLineWidth(width);
 }
void bar(int x1,int y1,int x2,int y2)
 {
      glColor3f(rcolor, gcolor, bcolor);
      glBegin(GL_QUAD_STRIP);
    glVertex2f(x1, height-y1);
    glVertex2f(x1, height-y2);
    glVertex2f(x1, height-y2);
    glVertex2f(x2, height-y2);
    glVertex2f(x2, height-y2);
    glVertex2f(x2, height-y1);
    glVertex2f(x2, height-y1);
    glVertex2f(x1, height-y1);
    glEnd();

 }
void rectangle(int x1,int y1,int x2,int y2)
 {
    glColor3f(rcolor, gcolor, bcolor);
    glBegin(GL_LINES);
    glVertex2f(x1, height-y1);
    glVertex2f(x1, height-y2);
    glVertex2f(x1, height-y2);
    glVertex2f(x2, height-y2);
    glVertex2f(x2, height-y2);
    glVertex2f(x2, height-y1);
    glVertex2f(x2, height-y1);
    glVertex2f(x1, height-y1);
    glEnd();
 }
void line(int x1,int y1,int x2,int y2)
{
   glColor3f(rcolor, gcolor, bcolor);
    glBegin(GL_LINES);
    glVertex2f(x1, height-y1);
    glVertex2f(x2, height-y2);

    glEnd();
}
void drawEllipse(float x,float y,float xradius, float yradius)
{
   glColor3f(rcolor, gcolor, bcolor);
	glBegin(GL_LINE_LOOP);
    int i;
	for( i=0; i < 360; i++)
	{

		float degInRad = i*DEG2RAD;
		glVertex2f(x+cos(degInRad)*xradius,height-(y+sin(degInRad)*yradius));
	}
	glEnd();
}
void fillellipse(float x,float y,float xradius, float yradius)
{
    glColor3f(rcolor, gcolor, bcolor);
	glBegin(GL_POLYGON);
    int i;
	for( i=0; i < 360; i++)
	{

		float degInRad = i*DEG2RAD;
		glVertex2f(x+cos(degInRad)*xradius,height-(y+sin(degInRad)*yradius));
	}

	glEnd();
}
void DrawPie(float x,float y,float xradius, float yradius,float a1,float a2)
{
   glColor3f(rcolor, gcolor, bcolor);
	glBegin(GL_POLYGON);
    float i;
    glVertex2f(x,height-y);
	for( i=a1-0.1; i <= a2; i+=0.1)
	{

		float degInRad = i*DEG2RAD;
		glVertex2f(x+cos(degInRad)*xradius,height-(y+sin(degInRad)*yradius));
	}
	glEnd();
}
void circle(float x,float y,float radius)
{
    drawEllipse(x,y,radius,radius);
}
void fillpoly(int cpoly,int *pl)
{

int cc;
    glColor3f(rcolor, gcolor, bcolor);
    glBegin(GL_POLYGON);
    for (cc=0;cc<cpoly;cc+=2){
        glVertex2f(*(pl+cc),*(pl+cc+1));
    }

    glEnd();
}
void delay(int ms)
{
 int c = clock() + ms;

  while(clock() < c){};
}
void drawtext(float x,float y, char s[])
{
int i;
glColor3f(rcolor, gcolor, bcolor);
glRasterPos2i(x,height-y);
i=0;
    while (s[i]!='\n'){
        glutBitmapCharacter(GLUT_BITMAP_HELVETICA_12,s[i]);
        i++;
    }
}
