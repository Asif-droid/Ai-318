package offline2;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;


public class heuristic {
    variable []var;
    public  heuristic(){

    }
    public heuristic(ArrayList<variable> vars){
        var=new variable[vars.size()];
        int k=0;
        for(variable v:vars){

            var[k]=v;
            k++;
        }

    }

    public  variable[] sort(int h) {

        if(h==1){
            System.out.println("Using heuristic 1");
            for (int i = 0; i < var.length; i++) {
                for (int j = 0; j < var.length - 1 - i; j++) {
                    if (var[j].length > var[j + 1].length) {
                        variable temp = var[j];
                        var[j] = var[j + 1];
                        var[j + 1] = temp;
                    }
                }
            }
        }
        else if(h==2){
            System.out.println("Using heuristic 2");
            degreeAssign();
            for (int i = 0; i < var.length; i++) {
                for (int j = 0; j < var.length - 1 - i; j++) {
                    if (var[j].degree < var[j + 1].degree) {
                        variable temp = var[j];
                        var[j] = var[j + 1];
                        var[j + 1] = temp;
                    }
                }
            }

        }
        else if(h==3){
            System.out.println("Using heuristic 3");
            degreeAssign();
            for (int i = 0; i < var.length; i++) {
                for (int j = 0; j < var.length - 1 - i; j++) {
                    if (var[j].length >= var[j + 1].length) {

                        if(var[j].length == var[j + 1].length){
                            if (var[j].degree < var[j + 1].degree) {
                                variable temp = var[j];
                                var[j] = var[j + 1];
                                var[j + 1] = temp;
                            }
                        }
                        else {
                            variable temp = var[j];
                            var[j] = var[j + 1];
                            var[j + 1] = temp;
                        }


                    }
                }
            }

        }
        else if(h==4){
            System.out.println("Using heuristic 4");
            degreeAssign();
            for (int i = 0; i < var.length; i++) {
                for (int j = 0; j < var.length - 1 - i; j++) {
                    if (var[j].h4 > var[j + 1].h4) {
                        variable temp = var[j];
                        var[j] = var[j + 1];
                        var[j + 1] = temp;

                    }
                }
            }

        }
        else {
            System.out.println("Using heuristic 5");
        }



        return var;
    }
    public void degreeAssign(){
        for(int i=0;i<var.length;i++){
            int c=0;
            for(int j=0;j<var.length;j++){
                if(j!=i){
                    if(var[i].row==var[j].row||var[i].col==var[j].col){
                        c++;
                    }
                }
            }
            var[i].degree=c;
            float x=(float) var[i].length/c;
            var[i].h4=x;
           // System.out.println(var[i].length+" "+c+" "+x);
        }
    }
    public void degreeAssignfromArray(variable[] var){
        for(int i=0;i<var.length;i++){
            int c=0;
            for(int j=0;j<var.length;j++){
                if(j!=i){
                    if(var[i].row==var[j].row||var[i].col==var[j].col){
                        c++;
                    }
                }
            }
            var[i].degree=c;
            float x=(float) var[i].length/c;
            var[i].h4=x;
            // System.out.println(var[i].length+" "+c+" "+x);
        }
    }
    public  variable[] fromtosort(int h,int ix,variable[] var) {

        if(h==1){
            //System.out.println("Using heuristic 1");
            for (int i = ix; i < var.length; i++) {
                for (int j = ix; j < var.length - 1 - i; j++) {
                    if (var[j].length > var[j + 1].length) {
                        variable temp = var[j];
                        var[j] = var[j + 1];
                        var[j + 1] = temp;
                    }
                }
            }
        }
        else if(h==2){
            //System.out.println("Using heuristic 2");
            degreeAssignfromArray(var);
            for (int i = ix; i < var.length; i++) {
                for (int j = ix; j < var.length - 1 - i; j++) {
                    if (var[j].degree < var[j + 1].degree) {
                        variable temp = var[j];
                        var[j] = var[j + 1];
                        var[j + 1] = temp;
                    }
                }
            }

        }
        else if(h==3){
           // System.out.println("Using heuristic 3");
            degreeAssignfromArray(var);
            for (int i = ix; i < var.length; i++) {
                for (int j = ix; j < var.length - 1 - i; j++) {
                    if (var[j].length >= var[j + 1].length) {

                        if(var[j].length == var[j + 1].length){
                            if (var[j].degree < var[j + 1].degree) {
                                variable temp = var[j];
                                var[j] = var[j + 1];
                                var[j + 1] = temp;
                            }
                        }
                        else {
                            variable temp = var[j];
                            var[j] = var[j + 1];
                            var[j + 1] = temp;
                        }


                    }
                }
            }

        }
        else if(h==4){
            //System.out.println("Using heuristic 4");
            degreeAssignfromArray(var);
            for (int i = ix; i < var.length; i++) {
                for (int j = ix; j < var.length - 1 - i; j++) {
                    if (var[j].h4 > var[j + 1].h4) {
                        variable temp = var[j];
                        var[j] = var[j + 1];
                        var[j + 1] = temp;

                    }
                }
            }

        }
        else {
            //System.out.println("Using heuristic 5");
        }



        return var;
    }

}

