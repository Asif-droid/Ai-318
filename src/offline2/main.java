package offline2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
       // System.out.println(args[0]);

        Scanner sc=new Scanner(System.in);

        File file=new File("data\\d-10-06.txt");
        try {
            Scanner fsc=new Scanner(file);

            String noStr=fsc.nextLine();
            noStr=noStr.replace(";","");
            String sa[]=noStr.split("=");
            int n=Integer.parseInt(sa[1]);
            fsc.nextLine();
            fsc.nextLine();
            int mat[][]=new int[n][n];
            int i=0;
            //System.out.println(n);
            ArrayList<variable> variables=new ArrayList<>();
            while(fsc.hasNext()){
                String line=fsc.nextLine();
                line=line.replace("[","");
                line=line.replace("]","");
                line=line.replace(" ","");
                line=line.replaceAll("[a-z=|;]","");
                String lA[]=line.split(",");
                //System.out.println(lA.length+" "+i);




                for(int j=0;j<n;j++){
                    int x=Integer.parseInt(lA[j]);
                    //System.out.println(x/10);
                    mat[i][j]=x;
                    if(x==0){
                        variable v=new variable(i,j,n);
                        variables.add(v);
                    }
                }

                i++;

            }
            for(int k=0;k<n;k++){
                for(int j=0;j<n;j++){
                    System.out.print(mat[k][j]+" ");
                }
                System.out.println();
            }


            csp csp=new csp(mat);
            System.out.println(variables.size());
            for(int x=1;x<=csp.rowMap.size();x++){
                ArrayList<Integer> l=csp.rowMap.get(x);
                if(l==null){
                    continue;
                }

                for(int y:l){
                    for(variable v:variables){
                        if(v.row==y){
                            v.remove(x);
                        }
                    }
                }
            }
            for(int x=1;x<=csp.colMap.size();x++){
                ArrayList<Integer> l=csp.colMap.get(x);
                if(l==null){
                    continue;
                }

                for(int y:l){
                    for(variable v:variables){
                        if(v.col==y){
                            v.remove(x);
                        }
                    }
                }
            }




            heuristic h=new heuristic(variables);
            variable[] vars= h.sort(1);


            backtrackAlgo backtrackAlgo=new backtrackAlgo();
            backtrackAlgo.solve(mat,csp,vars,1);
////
//            forCheck fc=new forCheck();
//            fc.solve(mat,csp,vars,3);






        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
