#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <locale.h>
int main()
{
    setlocale(LC_CTYPE,"rus");
    int a,b,g;
    system("cls");
    printf("�������� ��� ����������:\n");
    printf("  1 - �������� ����������\n");
    printf("  2 - ������������\n");
    printf("  3 - �������� ����������\n");
    printf("  4 - ����������������� ����������\n");
    scanf("%d",&a);
    printf("������� ���������� ����������\n");
    scanf("%d",&b);
    switch(a){
        case 1:{
            g=4; break;
        }
        case 2:{
            g=8; break;
        }
        case 3:{
            g=2; break;
        }
        case 4:{
            g=12; break;
        }
        default: {
            g=1; break;
        }
    }
    if (g>1){
        if (b%g==0) b=b/g;
        else b=b/g+1;

        if ((b%10==1)&(b%100!=11)){
            printf("���������� %d ����",b);
        }
        else{
            printf("���������� %d ������",b);
        }
    } else printf("�������� ������");
    getch();
    return 0;
}
