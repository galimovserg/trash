#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <locale.h>
int pr(long long int p){
		    long long	int i, g=p*p,q=p;


		    	while (q>0){
		    		if (q%10!=g%10){

		    			return 0;
					}
					q=q/10;
					g=g/10;
				}

		    	return 1;
			}
int main() {
		   setlocale(LC_ALL,"Russian");
		   int counts=0;
		 long  int i=0;
		   while(counts<10){
		   	i++;



		   	if (pr(i)==1){
		   		counts++;
		   		if (counts%5==0){
				   printf("%3d\n",i);
				   } else {
				  printf("%3d, ",i);
				  }

			   }

		   }
		    return 0;
 }


