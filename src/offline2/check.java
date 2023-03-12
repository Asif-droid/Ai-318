package offline2;

import java.util.Scanner;

public class check {
    static int mat[][];
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        mat=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                mat[i][j] = sc.nextInt();
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                if(!find(mat[i][j],n,i,j)){
                    System.out.println("pr");
                }
            }
            System.out.println();
        }
    }
    static boolean find(int x,int n,int r,int c){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                if(i==r&&j==c)
                    continue;
                if(i==r||c==j){
                    if(x==mat[i][j])
                        return false;
                }
            }

        }
        return true;
    }
}
