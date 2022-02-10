#include <stdio.h>
#include <stdlib.h>

int main()
{

int n,i,j,p,k;
scanf("%d",&n);
int a[100][100];
for (i=0;i<n;i++){
    for (j=0;j<n;j++){
        a[i][j]=rand()%200-100;
        printf("%d ",a[i][j]);
    }
    printf("\n");
}
for (i=0;i<n;i++){
    p=1;k=0;
    for (j=0;j<n;j++){
        if (a[i][j]<0){
                k=1;
            p=p*a[i][j];
        }
    }

printf("%d ",p*k);

}
}
