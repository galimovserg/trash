#include <stdio.h>
#include <stdlib.h>
#include <locale.h>

int main(){
setlocale(LC_CTYPE,"rus");
char fname[]="input.txt";
FILE *in,*out;
char name[80],name2[]="��������������������������������\n",
     name3[]="�����Ũ��������������������������\n";
if ((in=fopen(fname,"rt"))==NULL)
	{
		printf("File not found.\n");
		getch();
		return 0;
	}
fgets(name,80,in);
fclose(in);

printf("�������� ������: \n");
printf(name);printf("\n");
int i,j;
for (i=0;name[i]!=NULL;i++){
    for (j=0;name2[j]!=NULL;j++){
        if (name[i]==name2[j]){
            name[i]=name2[(i+j+1)%33];
            break;
        } else{
            if (name[i]==name3[j]){
            name[i]=name3[(i+j+1)%33];
            break;
        }
        }
    }
}
printf("������������ ������: \n");
printf(name);printf("\n");

 if ((out=fopen("output.txt","w"))==NULL) /* ��������� ���� ��� ������ */
	{
		return;
	};
 fprintf(out,name);
}
