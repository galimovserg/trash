#include <stdio.h>
#include <stdlib.h>

int main()
{
    int i,counts,n;
    int mas[100];
    printf("input n --> ");
    scanf("%d",&n);
    for (i=0;i<n;i++){
       printf("input mas[%d] --> ",i);
       scanf("%d",&mas[i]);
    }
    counts=0;
    for (i=0;i<n;i++){
      if (mas[i]%2<0){printf("%d ",mas[i]); counts++;}
    }
    printf("\ncount = %d",counts);
    return 0;
}
