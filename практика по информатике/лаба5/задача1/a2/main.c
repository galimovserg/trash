#include <stdio.h>
#include <stdlib.h>
#include <math.h>
//#include <windows.h>

void main() {
float s, x, h, i;

scanf("%f", &h);
for(i = 0.5; i <= 1.0001; i+=h){
s = i + cos(pow (i,0.52) + 2);
printf("x=%f => f(x)= %f\n",i, s);
}
return 0;
}
