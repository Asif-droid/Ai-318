package offline1;

import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.setOut;

public class main {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int k=sc.nextInt();

        node start=new node(k);
        node goal =new node(k);
        int x=1;
        for (int i=0;i<k;i++){
            for(int j=0;j<k;j++){
                if(i==k-1 && j==k-1){
                    goal.grid[i][j]=0;
                }
                else{
                    goal.grid[i][j]=x;
                    x++;
                }

            }
        }
        goal.showGrid();
        System.out.println("enter num 1 to "+ (k*k-1)+" and 0 for blank");
        for(int i=0;i<k;i++){
            for(int j=0;j<k;j++){
                int val=sc.nextInt();
                if(val>k*k-1 || val <0){
                    System.out.println("must be btn 1 to "+ (k*k-1));
                    exit(0);
                }
                start.grid[i][j]=val;
            }
        }
        //System.out.println(start.manhatan(goal));
        int c=countInversion(start);
        algo algoH=new algo(goal);
        algo algoM=new algo(goal);
        if(k%2==1){

            if(c%2!=0){
                System.out.println("not Solvable");
            }
            else{
                System.out.println("Solvable");
                System.out.println("Using hamming...");
                algoH.solve(start,"ham");
                System.out.println("Using manhattan...");
                algoM.solve(start,"man");
                  //start.manhatan(goal);
            }

        }else{
            int []xy=new int[2];
            xy=findBlank(start);
            if(xy[0]%2==0 &&(c%2==1)){
                System.out.println("Solvable");
                System.out.println("Using hamming...");
                algoH.solve(start,"ham");
                System.out.println("Using manhattan...");
                algoM.solve(start,"man");
            }
            else if(xy[0]%2==1 &&(c%2==0)){
                System.out.println("Solvable");
                System.out.println("Using hamming...");
                algoH.solve(start,"ham");
                System.out.println("Using manhattan...");
                algoM.solve(start,"man");
            }
            else{
                System.out.println("not Solvable");
            }

        }


        //System.out.println(start.hammHeuristic(goal));;

    }

    public static int countInversion(node n){
        int s=n.s;
        int ar[]=new int[s*s];
        int k=0;
        for(int i=0;i<s;i++){
            for(int j=0;j<s;j++){
                if(n.grid[i][j]!=0){
                    ar[k]=n.grid[i][j];
                    k++;
                }
            }

        }
        int cI=0;
        for(int i=0;i<k;i++){
            int p=ar[i];
            for(int j=i;j<k;j++){
                if(ar[j]<p){
                  cI++;
                }
            }
        }
        return cI;
    }
    static int[] findBlank(node n){
        int [] xy=new int[2];
        int k=n.grid[0].length;
        for(int i=0;i<k;i++){
            for(int j=0;j<k;j++){
                if(n.grid[i][j]==0){
                    xy[0]=i;
                    xy[1]=j;
                    return xy;
                }
            }
        }
        return xy;
    }

    //0 1 2 3 4 2 5 7 8 6
    //1 2 3 4 5 6 7 0 8 neighbour done heuristic of neghbour
    //1 2 3 4 0 5 7 8 6
    //0 1 3 4 2 5 7 8 6
    //1 2 3 4 5 0 7 8 6
    //1 5 7 8 0 6 3 2 4
    //1  2  3 4  6  5 7  8  0
    //8  6  7 2  5  4 1  3  0
    //3  2  4  8 1  6  0 12 5 10  7 11 9 13 14 15
    //1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 0
    //1 3 7 11 9 6 4 8 5 2 12 15 13 14 0 10
    //1 2 3 4 5 6 0 8 9 10 7 12 13 14 11 15
    //1 2 3 4 5 0 7 8 9  6 11 12 13 10 14 15
    //1  2  8  3 5 11  6  4 0 10  7 12 9 13 14 15
}
