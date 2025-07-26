// 1st Method
import java.util.Scanner;

public class Armstrong {
public static void main(int num){

int check=0;
int digit;
int count=0;
System.out.println("Enter number: ");
Scanner scanner =new Scanner(System.in);
int num=scanner.nextInt();

int n=num;
int temp=num;
while (num > 0) {
count = count + 1;
num = num / 10;
};

while(n>0){
digit=n%10;
check+=Math.pow(digit,count);
n=n/10;

}
if(check==temp){
System.out.println("Armstrong number");
}
else{
System.out.println("Not Armstrong number");
}

}
}