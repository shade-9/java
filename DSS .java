import java.util.*;
public class DSS
{
	public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter the string of lower case English alphabets");
    String c = sc.nextLine();
    char[] arr = c.toCharArray();
    int n = arr.length;
    int x = 2;
    int e = 0;
    double y = Math.pow (x,n);
    double a = 0;
    for(int i = 0; i<n ; i++){
    for(int j = i + 1; j < n ; j++){
        if(arr[i]==arr[j]){
            a ++;
            e = 1;
        }
    } for(int j = i + 1; j < n - 1 ; j++){
        if(e==1 && arr[j] == arr[j+1]){
            a++; 
        }
    }
        }
 double b =y - a;
 System.out.println("\n"+y);
 System.out.println("\n"+n);
 System.out.println("\n"+a);
 System.out.println("\n"+e);
System.out.println("\n"+b);
}
}