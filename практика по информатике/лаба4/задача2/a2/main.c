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
    printf("������� ����� � ������� [Enter]\n");
    scanf("%d",&a);
    b=a%3;
    if (b==0){printf("����� %d ������� �� ��� ������������/n",a);}
    else {printf("����� %d �� ������� �� ��� ������������/n",a);}
    getch();
    goto rt;

    return 0;
}
