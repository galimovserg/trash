#include <stdio.h>
#include <stdlib.h>

int main()
{
    long long int m;
    int i;
    m=2;
    for (i=1;i<=62;i++){
        m=m*2;
        printf("%i",m/100000);
        printf("%i\n",m%100000);

    }
    return 0;
}
