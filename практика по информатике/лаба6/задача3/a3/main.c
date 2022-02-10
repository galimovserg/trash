#include <stdio.h>
#include <stdlib.h>

int main()
{
    int i,j,n,minn;
    int a[100][100];
    scanf("%d",&n);
    for (i=0;i<n;i++)
    {
        for (j=0;j<n;j++){
            a[i][j]=rand()%200-100;
            printf("%d ",a[i][j]);
        }
        printf("\n");
    }
    minn=a[0][n-1];
    for (i=0;i<n;i++)
        if (a[i][n-i-1]<minn) minn=a[i][n-i-1];

    printf("\n%d",minn);
}
