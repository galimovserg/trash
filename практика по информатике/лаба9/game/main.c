#include "main.h"
#include <process.h>



void display(){
    glClearColor(1.0, 1.0, 1.0, 1.0);
    glClear(GL_COLOR_BUFFER_BIT);


    glFlush();
}
void processSpecialKeys(int key, int x, int y){
    switch(key) {
		case GLUT_KEY_LEFT :

            break;
		case GLUT_KEY_RIGHT :

		    break;
		case GLUT_KEY_UP :

		    break;
        case GLUT_KEY_DOWN  :

		    break;
        case GLUT_KEY_F1  :

		    break;
        case GLUT_KEY_F2 :

		    break;
	}

	display();
}
void mousedown(int button, int state,int x, int y){

  display();
}
int main()
{

    setlocale (LC_ALL, "rus");
    printf("нажмите для продолжения");
    getch();

    drawgraph(display,FNot,FNot,processSpecialKeys,mousedown,1024,800,"hello world");

}

