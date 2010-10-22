#include "stdio.h"
int function(int a, int b, int c) {
    char buffer[5]="abcd";
    int sum = 300;
    int *ret;

    ret = (int *) buffer;
    printf(" 0x%x %s \n",ret,ret);

    printf(" 0x%x %d \n",ret+1,*(ret+1));
    printf(" 0x%x %d \n",ret+2,*(ret+2));
    printf(" 0x%x %d \n",ret+3,*(ret+3));
    printf(" 0x%x %d \n",ret+4,*(ret+4));
    printf(" 0x%x %d \n",ret+5,*(ret+5));

    printf("======================\n");
    ret = (int *) (buffer+5);
    printf(" 0x%x %d \n",ret,ret);


    printf(" 0x%x %d \n",ret-1,*(ret-1));
    printf(" 0x%x %d \n",ret-2,*(ret-2));
    printf(" 0x%x %d \n",ret-3,*(ret-3));
    
    printf(" 0x%x 0x%x \n",ret-4,*(ret-4));
    printf(" 0x%x 0x%x \n",ret-5,*(ret-5));
    printf(" 0x%x 0x%x \n",ret-6,*(ret-6));
    printf(" 0x%x 0x%x \n",ret-7,*(ret-7));
    printf(" 0x%x 0x%x \n",ret-8,*(ret-8));
    printf(" 0x%x 0x%x \n",ret-9,*(ret-9));
    printf(" 0x%x 0x%x \n",ret-10,*(ret-10));
    printf(" 0x%x 0x%x \n",ret-11,*(ret-11));
    printf(" 0x%x 0x%x \n",ret-12,*(ret-12));

   //printf(" 0x%x %d \n",ret,*ret);
   //
    /*for(int i=0;i<10;i++){
      	if(i<1)
      		printf(" 0x%x %s \n",ret,*(&ret));
	else
		printf(" 0x%x %d \n",ret,*ret);      
	ret--;
    }
*/
    //ret = (int *) (buffer-16);
    //(*ret) += 10;
    //sum = a+b+c;
    return sum;
    //printf("%x\ %x\ %x\ %x\ %x\ \n",buffer,ret,*ret,&ret,&buffer);
    /*
    printf("%x %x %x %x %x \n",buffer,&sum, &ret,&ret+2,ret);
    printf("%d \n",*(ret-1));
    printf("%s \n",*(ret-2));
    printf("previous frame pointer %x %x\n",*(ret-3),ret-3); // previous frame pointer
    printf("return address %x %x \n",*(ret-4),ret-4); // return address
    printf("%d %x \n",*(ret-5),ret-5);
    printf("%d %x \n",*(ret-6),ret-6);
    printf("%d %x \n",*(ret-7),ret-7);

    printf("check %x %x %x \n",buffer,buffer-16,ret-4);

    ret =(int *) buffer-16;
    (*ret) += 10;

    printf("return address %x %x \n",*ret,ret); // return address
*/
// check int a
//    *p = *(ret-5);
   /* *p = *(ret-4);
    printf(" *p %x for %x\n",*p,p);

    *p = *p+10;
    printf(" *p %x for %x\n",*p,p);
  
    printf("return address %x %x \n",*(ret-4),ret-4); // return address
 */
 }

 void main() {
    int x;
    x = 0;
    x = function(11,2,3);
    x = 1;
    printf("%d \n",x);
 }
