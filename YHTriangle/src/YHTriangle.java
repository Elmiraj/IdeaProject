import java.util.Scanner;

public class YHTriangle {
    public static void printYHTriangle() {
        System.out.println("how many lines do you want:");
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int num[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            num[i][0] = num[i][i] = 1;
            for (int j = 1; j < i; j++) {
                num[i][j] = num[i - 1][j - 1] + num[i - 1][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(num[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        printYHTriangle();
    }
}
