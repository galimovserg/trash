#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
void pmatrix(int n,int m[200][200],int *pn){
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
        *(pn+i)=p*q;
    }

}
void main()
{
    int vec[200];
    int mas[200][200];
    int n,i,j;
    setlocale(LC_CTYPE,"rus");
    printf("введите размерность квадратной матрицы!\n");
    scanf("%d",&n);
    printf("введите через пробел %d элементов матрицы: ",n*n);
    for (i=0;i<n;i++)
        for (j=0;j<n;j++) scanf("%d",&mas[i][j]);

    pmatrix(n,mas,&vec[0]);
    for (i=0;i<n;i++)
        printf("произведение %d строки равно %d\n",i,vec[i]);

}

