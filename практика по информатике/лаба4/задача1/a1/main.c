#include <stdio.h>
#include <stdlib.h>
//подключим библиотеку для поддержки вывода русских символов
#include <locale.h>
int main()
{
    setlocale(LC_CTYPE,"rus");
    //обьявим переменные

    rg:{}
     float a,b,h,v;
    system("cls");
    printf("введите длинны сторон a,b,h, в миллиметрах\n");
    scanf("%f %f %f",&a,&b,&h);
    v=a*b*h;
    printf("обьем равен: %f",v);
    getch();
    goto rg;
    return 0;
}
