package offline2;

public class backtrackAlgo {
    //heuristic heuristic=new heuristic();
    int bt;
    int node;



    public boolean helper(int mat[][],variable[] vars,csp csp,int ix,int he){

        //variable newvar;
        //System.out.println(row);
        //int nwrow;
        int newix;
        if(ix==vars.length){
            return true;
        }
        else{

            newix=ix+1;

            variable v=vars[ix];
            for(int x=0;x<v.domain.size();x++){
                int i=v.domain.get(x);
                if(csp.safe(v.row,v.col,i)){
                    mat[v.row][v.col]=i;
                    if(he>1&&he<5){
                        for(int k=0;k<vars.length;k++){
                            if(vars[k].row==v.row||vars[k].col==v.col){
                                vars[k].degree--;
                            }
                        }
                    }


                    csp.rowPut(v.row,i);
                    csp.colPut(v.col,i);
                    node++;
                    heuristic h=new heuristic();
                    variable[] vx= h.fromtosort(he,ix+1,vars);
                    if(helper(mat,vx,csp,newix,he)){
                        return true;
                    }
                    else{
                        csp.rowRemove(v.row,i);
                        csp.colRemove(v.col,i);
                        mat[v.row][v.col]=0;
                        if(he>1&&he<5){
                            for(int k=0;k<vars.length;k++){
                                if(vars[k].row==v.row||vars[k].col==v.col){
                                    vars[k].degree++;
                                }
                            }
                        }

                        bt++;
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
        bt=0;
        node=0;

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
