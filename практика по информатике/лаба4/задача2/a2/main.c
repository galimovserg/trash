#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include <math.h>
int main()
{
    setlocale(LC_CTYPE,"rus");
    int a,b;
    rt:{}
    system("cls");
    printf("введите число и нажмите [Enter]\n");
    scanf("%d",&a);
    b=a%3;
    if (b==0){printf("число %d делится на три целочисленно/n",a);}
    else {printf("число %d не делится на три целочисленно/n",a);}
    getch();
    goto rt;

    return 0;
}
