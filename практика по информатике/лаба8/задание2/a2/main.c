#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
int main(){
setlocale(LC_CTYPE,"rus");
int a[10],sum,i=0;
char name[80];
gets(name);
sum=0;
for (i=0;i<10;i++) a[i]=0;
for (i=0;name[i]!=NULL;i++){

if (((name[i]-'0')>=0)&&((name[i]-'0')<=9)){
    sum+=1- a[name[i]-'0'];
    a[name[i]-'0']=1;
}
}

printf("Количество различных цифр: %d\n",sum);
}
