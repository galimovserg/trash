#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <locale.h>
void conts(char name[]){
  int i;
    for(i=0;name[i]!=NULL;i++)
{
    if (name[i]<=-81){
       name[i]=name[i]+64;
    } else
    {
       name[i]=name[i]+16;
    }
}
}
int main(){
setlocale(LC_CTYPE,"rus");
char name[80],name2[]="àáâãäå¸æçèéêëìíîïðñòóôõö÷øùúûüýþÿ\n",name3[80];
int i=0,j=0;
gets(name);
gets(name3);
conts(&name);
conts(&name3);

for(i=0;name[i]!=NULL;i++)
    for (j=0;name3[j]!=NULL;j++)
        if (name[i]==name2[j]){
           name[i]=name3[j];
           break;
        }

printf(name);

}
