#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
int main(){
    setlocale(LC_CTYPE,"rus");
    int a[100][100];
    int k=0,p=1,i,j,n;
    scanf("%d",&n);
    for (i=0;i<n;i++)
        for (j=0;j<n;j++) scanf("%d",&a[i][j]);
    for (i=0;i<n;i++){
        if (a[i][i]<0) k++;
        if (a[i][n-i-1]>0) p=p*a[i][n-i-1];
    }
    printf("количество равно: %d\n",k);
    printf("произведение равно: %d\n",p);
    return 0;
}
