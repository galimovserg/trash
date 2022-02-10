#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
void printmatrix(int n,int m[200][200]){
int i,j;
for (i=0;i<n;i++)
    {
        for (j=0;j<n;j++)
        {
                printf("%3d ",m[i][j]);
        }
        printf("\n");
    }
}
void pmatrix(int n,int m[200][200]){
int i,j,p,q;
for (i=0;i<n;i++)
    {
        p=1;
        q=0;
        for (j=0;j<n;j++)
        if (m[i][j]<0)
        {
                p=p*m[i][j];
                q=1;
        }
        printf("произведение строки %d равно: %d\n",i+1,p*q);
    }

}
