#include "main.h"

int main()
{
    float a,b,c;
    setlocale(LC_CTYPE,"rus");
    printf("������� �������� ������\n");
    scanf("%f %f %f",&a,&b,&c);
    printf("���������: %f ���. ��.",ParallelepipedVolume(a,b,c));
    return 0;
}
