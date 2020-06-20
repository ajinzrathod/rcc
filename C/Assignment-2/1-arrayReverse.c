#include<stdio.h>
int getData(int myArray[], int n)
{
	int i;
	int *p = myArray;
	for (i = 0; i < n; ++i) {
		printf("Enter number %d: ", i + 1);
		scanf("%d",p);
		p++;
	}
}

int reverseData(int myArray[], int n)
{
	int i, *p = myArray + n - 1;

	printf("\n ===== Numbers in reverse Order ===== \n");
	
	for (i = 0; i < n; ++i) {
		printf("%d: ", *p);
		p--;
	}
}

int main()
{
	int myArray[10], n, i;
	int *p = myArray;
	printf("Enter N: ");
	scanf("%d",&n);

	getData(myArray, n);
	reverseData(myArray, n);

	printf("\n");
	return 0;
}
