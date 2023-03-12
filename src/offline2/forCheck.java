package offline2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;



public class forCheck {
    int node;
    int bt;


    public boolean helper(int mat[][],variable[] vars,csp csp,int ix,int he){

        //variable newvar;
        //System.out.println(row);
        //int nwrow;
        int newix;
        if(ix==vars.length){
            return true;
        }
        else{


            //System.out.println();
            ArrayList<Integer> newup=new ArrayList<>();

            newix=ix+1;
            variable v=vars[ix];
            for(int x=0;x<v.domain.size();x++){
                int i=v.domain.get(x);
                boolean isEmpty=false;
                if(csp.safe(v.row,v.col,i)) {
                    for (int j = 0; j < vars.length; j++) {
                        if (j != ix) {
                            if(v.row==vars[j].row||v.col==vars[j].col){
                                if(vars[j].domain.contains(i)){
                                    newup.add(j);
                                    vars[j].remove(j);
                                    if(vars[j].domain.size()==0){
                                        for (int a : newup) {
                                            vars[a].add(i);
                                        }
                                        isEmpty=true;
                                        break;
                                    }
                                }
                            }


                        }
                    }
                    // System.out.println(newup.size());;

                    if(!isEmpty){
                        mat[v.row][v.col] = i;
                        csp.rowPut(v.row,i);
                        csp.colPut(v.col,i);
                        variable[] vx;
                        //test
                        if(he>1&&he<5){
                            for(int k=0;k<vars.length;k++){
                                if(vars[k].row==v.row||vars[k].col==v.col){
                                    vars[k].degree--;
                                }
                            }
                            heuristic h=new heuristic();
                            vx=h.fromtosort(he,ix+1,vars);
                        }
                        else {
                            vx=vars;
                        }

                        //debug

                        node++;

//
                        if (helper(mat, vx, csp, newix,he)) {
                            return true;
                        } else {

                            mat[v.row][v.col] = 0;
                            if(he>1&&he<5){
                                for(int k=0;k<vars.length;k++){
                                    if(vars[k].row==v.row||vars[k].col==v.col){
                                        vars[k].degree++;
                                    }
                                }
                            }

                            csp.colRemove(v.col,i);
                            csp.rowRemove(v.row,i);
                            bt++;
//
                        }

                    }

                }
            }
        }



        return false;
    }

    public void solve(int mat[][],csp csp,variable[] variables,int he){

//        while (!h.hVars.isEmpty()){
//            variable x=h.getVar();
//            System.out.println(x.row+" "+x.length);
//        }
        node=0;
        bt=0;
        ArrayList<Integer> up=new ArrayList<>();
        long start=System.currentTimeMillis();
        if(helper(mat,variables,csp,0,he)){
            for(int i=0;i<mat.length;i++){
                for(int j=0;j<mat.length;j++){
                    System.out.print(mat[i][j]+"   ");
                }
                System.out.println();
            }
        }

        long time=System.currentTimeMillis()-start;
        System.out.println(time);
        System.out.println("No of nodes: "+node);
        System.out.println("No of bt: "+bt);
    }
}
