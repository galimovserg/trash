#include <stdio.h>
#include <stdlib.h>
#include <math.h>
int main()
{
    int a,p,itog,i;
    scanf("%d %d",&a,&p);
    itog=1;
    for (i=0;i<=p;i++) itog=itog*(a-p*i);
    printf("%d",itog);
    return 0;
}
