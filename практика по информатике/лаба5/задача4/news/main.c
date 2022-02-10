#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main()
{
    int counts=0,n=3;
     unsigned  long long int shislo=2;
    while (counts<10){
           //printf("%d\n",shislo);
          n++;
            shislo=shislo*2;
        if (pr(n)==1){
            counts++;
            if (counts%5==0){ printf("%d\n",shislo-1);} else{
           printf("%d ",shislo-1);}
        }

    }
    return 0;
}
int pr(unsigned  long long int p){
int i=2;
int f=1;
if (p%2==0){f=0;}
for (i=3;i<=sqrt(p)+1;i=i+2){

    if (p%i==0){f=0;}
}


return f;
}
