#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
int main(){
char fname[]="input.txt";
FILE *in;
char name[80];
int i=0,counts=0;
if ((in=fopen(fname,"rt"))==NULL)
	{
		/* если результат неудачен - выводим сообщение */
		printf("File not found.\n");
		getch();
		return 0;
	}
	fgets(name,80,in);
    fclose(in);

setlocale(LC_CTYPE,"rus");



for (i=0;name[i]!=NULL;i++){
    if (name[i]==' ') {
        counts++;
    }
}
printf("Количество пробелов: %d",counts);
return 0;
}

