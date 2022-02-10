#include <stdio.h>
#include <stdlib.h>
#include <math.h>


int main()
{


    int counts=0;
     long long  shislo=2,n=1;
    while (counts<10){
            n++;


            shislo=shislo*2;

        if (pr(n)==1){
            if(pr(shislo-1)==1){
            counts++;
            printf("%lli ",shislo-1);
            if (counts%5==0)
                { printf("%\n");}

          }
        }

    }
    return 0;
}
int pr(long long p){
long  int i=2;
int f=1;
if (p%2==0){f=0;}
if (p==2){f=1;}
for (i=3;i<=sqrtl(p);i=i+2){
    if (p%i==0){f=0;}
}


return f;
}
