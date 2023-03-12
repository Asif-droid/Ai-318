package offline2;

import java.util.ArrayList;

public class variable {
    public int row;
    public int col;
    int degree;
    int length;
    float h4;
    int id;
    ArrayList<Integer> domain=new ArrayList<>();
    public variable(int row,int col,int n){

        for(int i=1;i<=n;i++){
            domain.add(i);
        }
        this.row=row;
        this.col=col;
        length=n;
        h4=0;

    }
    public void add(int a){
        domain.add(a);
        length++;
    }
    public void remove(int a){
        int ix=domain.indexOf(a);
        //System.out.println(ix+""+a);
        if(ix>=0&&ix<domain.size()){
            domain.remove(ix);
            length--;
        }

    }
    public void show(){
        System.out.println(row+" "+col);
        for(int i:domain){
            System.out.print(i);
        }
        System.out.println();
    }
    public int domlength(){
        return length;
    }
}
