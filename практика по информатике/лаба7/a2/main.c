#include "main.h"
int main()
{
    int mas[200][200];
    int n,i,j;
    setlocale(LC_CTYPE,"rus");
    printf("������� ����������� ���������� �������!\n");
    scanf("%d",&n);
    printf("������� ����� ������ %d ��������� �������: ",n*n);
    for (i=0;i<n;i++) for (j=0;j<n;j++) scanf("%d",&mas[i][j]);
    printmatrix(n,mas);
    pmatrix(n,mas);
    return 0;
}
