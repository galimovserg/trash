#include <stdio.h>
#include <stdlib.h>
//��������� ���������� ��� ��������� ������ ������� ��������
#include <locale.h>
int main()
{
    setlocale(LC_CTYPE,"rus");
    //������� ����������

    rg:{}
     float a,b,h,v;
    system("cls");
    printf("������� ������ ������ a,b,h, � �����������\n");
    scanf("%f %f %f",&a,&b,&h);
    v=a*b*h;
    printf("����� �����: %f",v);
    getch();
    goto rg;
    return 0;
}
