#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#define FNAME "filewrt.txt"
int main(){


setlocale(LC_CTYPE,"rus");
 char fname[20]=FNAME;
	FILE *out;					/* ������� ��������� �� ���� */
    int i,j;

    if ((out=fopen(fname,"w"))==NULL) /* ��������� ���� ��� ������ */
	{
		/* ���� ��������� �������� - ������� ��������� */
		printf("File not created.\n");
		getch();
		return;
	};
	for(i=0;i<8;i++){
		for(j=0;j<8;j++){
			if (i+j==7){
				fprintf(out,"%5d",0);
			}else{
				fprintf(out,"%5d",1);
			}
		}
			fprintf(out,"\n");
    }
    fclose(out);
	printf("���� ������.");
	getch();
    return;
}
