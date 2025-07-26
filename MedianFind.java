import java.util.Scanner;

public class MedianFind {
    int x, y;
    int[] arr1, arr2, arr3;


    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        System.out.print("Enter No. of elements in 1st array: ");
        int x = sc.nextInt();
        System.out.print("Enter No. of elements in 2nd array: ");
        int y = sc.nextInt();

        int[] arr1 = new int[x];
        int[] arr2 = new int[y];

        int[] arr3 = new int[x + y];

        for (int i = 0; i < x; ++i) {
            System.out.print("Enter element of arr1: ");
            arr1[i] = sc.nextInt();
        }

        for (int j = 0; j < y; ++j) {
            System.out.print("Enter element of arr2: ");
            arr2[j] = sc.nextInt();
        }

        int l,m,n;
        l = m = n = 0;

        while (l != x && m != y) {
            if (arr1[l] < arr2[m]) {
                arr3[n++] = arr1[l++];
            }
            else {
                arr3[n++] = arr2[m++];
            }
        }
        while (l != x) {
            arr3[n++] = arr1[l++];
        }
        while (m != y) {
            arr3[n++] = arr2[m++];
        }

        int median = arr3[(x+y)/2];
        System.out.println("Median of the array is: "+median);


    }

}