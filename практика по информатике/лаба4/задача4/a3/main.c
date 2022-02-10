#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <locale.h>
int main()
{
    setlocale(LC_CTYPE,"rus");
    int a,b,g;
    system("cls");
    printf("выберите вид транспорта:\n");
    printf("  1 - легковой автомобиль\n");
    printf("  2 - микроавтобус\n");
    printf("  3 - грузовой автомобиль\n");
    printf("  4 - грузопассажирский автомобиль\n");
    scanf("%d",&a);
    printf("введите количество пассажиров\n");
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
            printf("необходимо %d рейс",b);
        }
        else{
            printf("необходимо %d рейсов",b);
        }
    } else printf("неверные данные");
    getch();
    return 0;
}
