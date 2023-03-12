package offline1;

import java.util.PriorityQueue;

class point{
    int x;
    int y;
    point(){

    }
    point(int x,int y){
        this.x=x;
        this.y=y;
    }
}

public class node {

    int s;
    public int [][] grid;
    int h;
    int g;
    int f;
    node parent;
    public node(int s){
        this.s=s;
        grid=new int[s][s];
        h=0;
        g=0;
        f=0;
        parent=null;
    }
    void setParent(node p){
        parent=p;
    }
    public void  showGrid(){
        for(int i=0;i<s;i++){
            for(int j=0;j<s;j++){
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }
    int inG(){
        g++;

        return g;
    }
    public int hammHeuristic(node n){

        int k=0;

        for(int i=0;i<s;i++){
            for(int j=0;j<s;j++){
                if(grid[i][j]!=0){
                    if(grid[i][j]!=n.grid[i][j])
                        k++;
                }
            }

        }

        return k;
    }
    public int manhatan(node n){
        int nsz=n.s;
        int k1=0;
        point[] goalPoints=new point[nsz*nsz];
        point[] nodePoints=new point[nsz*nsz];
        for(int i=0;i<s;i++){
            for(int j=0;j<s;j++){
                int ix=n.grid[i][j];
                if(ix!=0){
                    goalPoints[ix]=new point(i,j);
                }
            }

        }

        for(int i=0;i<s;i++){
            for(int j=0;j<s;j++){
                int ix=grid[i][j];
                if(ix!=0){
                    nodePoints[ix]=new point(i,j);
                }
            }

        }
        int cost=0;
        for(int i=1;i<nsz*nsz;i++){
            int xc=Math.abs(goalPoints[i].x-nodePoints[i].x);
            int yc=Math.abs(goalPoints[i].y-nodePoints[i].y);
            cost+=(xc+yc);
        }
//        for(int i=1;i<nsz*nsz;i++){
//            System.out.println(i+" "+nodePoints[i].x+" "+nodePoints[i].y);
//        }


        return cost;
    }
    void costUpdate(int x){
        h=x;
        f=h+g;
    }
    void copyGrid(node n){
        for(int i=0;i<s;i++){
            for(int j=0;j<s;j++){
               grid[i][j]=n.grid[i][j];
            }

        }
        g=n.g+1;
    }

}
