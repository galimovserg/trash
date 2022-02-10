#include "main.h"

int main()
{
    float a,b,c;
    setlocale(LC_CTYPE,"rus");
    printf("введите величины сторон\n");
    scanf("%f %f %f",&a,&b,&c);
    printf("результат: %f куб. мм.",ParallelepipedVolume(a,b,c));
    return 0;
}
