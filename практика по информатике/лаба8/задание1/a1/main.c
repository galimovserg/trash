#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
int main(){
setlocale(LC_CTYPE,"rus");
char name[80];
int i=0,counts=0;
gets(name);

for (i=0;name[i]!=NULL;i++){
    if (name[i]==' ') {
        counts++;
    }
}
printf("Количество пробелов: %d",counts);
return 0;
}

