#include <stdio.h>
#include <stdlib.h>
#include <math.h>
int main()
{
    int counts=0;
    int i=3;
    while (counts<25){
        i+=2;
        if (pr(i)*pr(i-2)==1){
           counts++;
            if (counts%5!=0){
            printf("(%3d, %3d), ",i-2,i);
            } else {
            printf("(%3d, %3d)\n",i-2,i);
            }
        }
    }
    return 0;
}
int pr(int p){
    int f=1,i;
    for ( i=2;i<sqrt(p)+1; i++){
        if (p%i==0){f=0;}
     }
return f;
}
