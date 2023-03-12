package offline2;

import java.util.ArrayList;
import java.util.HashMap;

public class csp {
    HashMap<Integer, ArrayList<Integer>> rowMap=new HashMap<Integer, ArrayList<Integer>>();
    HashMap<Integer, ArrayList<Integer>> colMap=new HashMap<Integer, ArrayList<Integer>>();

    public csp(int mat[][]){
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat.length;j++){
                if(mat[i][j]!=0){
//                    if(rowMap.get(mat[i][j])==null){
//                        rowMap.put(mat[i][j],new ArrayList<>());
//                        rowMap.get(mat[i][j]).add(i);
//                    }
//                    else {
//                        rowMap.get(mat[i][j]).add(i);
//                    }
//                    if(colMap.get(mat[i][j])==null){
//                        colMap.put(mat[i][j],new ArrayList<>());
//                        colMap.get(mat[i][j]).add(j);
//                    }
//                    else{
//                        colMap.get(mat[i][j]).add(j);
//                    }
                    rowPut(i,mat[i][j]);
                    colPut(j,mat[i][j]);

                }
            }
        }
    }
    public void rowRemove(int row,int val){
        if(rowMap.get(val)!=null){
            int x=rowMap.get(val).indexOf(row);
            rowMap.get(val).remove(x);
        }

    }
    public void colRemove(int col,int val){
        if(colMap.get(val)!=null){
            int x=colMap.get(val).indexOf(col);
            //System.out.println(val+"val ix"+x+" col "+col);
            colMap.get(val).remove(x);
        }

    }
    public void rowPut(int row,int val){
        //System.out.println("put v"+val+"row "+row);
        if(rowMap.get(val)==null){
            rowMap.put(val,new ArrayList<>());
            rowMap.get(val).add(row);
        }else
            rowMap.get(val).add(row);
    }
    public void colPut(int col,int val){
        //System.out.println("put v"+val+"col"+col);
        if(colMap.get(val)==null){
            colMap.put(val,new ArrayList<>());
            colMap.get(val).add(col);
        }else
            colMap.get(val).add(col);
    }
    void show(){
       for (int i:rowMap.keySet()){
           System.out.println(i+" ->"+rowMap.get(i));
       }
        System.out.println("col");
        for (int i:colMap.keySet()){
            System.out.println(i+" ->"+colMap.get(i));
        }
    }

    public boolean safe(int r,int c,int n){
        ArrayList<Integer> rows=rowMap.get(n);
        if(rows==null){

            return true;
        }

        if(rows.contains(r)){
            return false;
        }
        ArrayList<Integer> cols=colMap.get(n);
        if(cols==null){
            return true;
        }
        if(cols.contains(c)){
            return false;
        }
        return true;
    }
}
