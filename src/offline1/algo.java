package offline1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class algo {
    node goal;
    public algo(node goal){
        this.goal=goal;
    }
    PriorityQueue <node> openList = new PriorityQueue<>(new myCompare());
    ArrayList<node> closedList=new ArrayList<>();
    //ArrayList<node> parent=new ArrayList<>();
    int expanded=0;
    int explored=0;

    void solve(node start,String mode){
        //System.out.println(start.f);
        openList.add(start);
        //parent.add(start);
        String h=mode;
        start.showGrid();
        //node prev=null;
//        node[] neighbour=createNeighbour(start,prev);
//        for (int i=0;i<4;i++){
//            if(neighbour[i]==null)
//                continue;
//            neighbour[i].showGrid();
//            System.out.println("he "+neighbour[i].hammHeuristic(goal));
//            System.out.println(neighbour[i].f);
//        }
        while(!openList.isEmpty()){
            node node=openList.poll();
            //System.out.println(node.h);
            closedList.add(node);
            node[] neighbour=createNeighbour(node);
            for(int i=0;i<4;i++){

                boolean inclL=false;
                boolean inOl=false;
                if(neighbour[i]!=null){
//                    neighbour[i].hammHeuristic(goal);
//                    System.out.println(neighbour[i].f);
                    if(neighbour[i].hammHeuristic(goal)==0){
                        closedList.add(node);
                        closedList.add(neighbour[i]);
                        //parent.add(neighbour[i]);
                        expanded++;
                        explored=closedList.size()+1;
                        end(neighbour[i]);
                        return;
                    }
                    for (node x:closedList){
                        if(neighbour[i].hammHeuristic(x)==0){
                            inclL=true;
                            break;
                        }
                    }
                    if(!inclL){
                        for (node y:openList){
                            if(neighbour[i].hammHeuristic(y)==0){
                                inOl=true;
                                break;
                            }
                        }
                    }
                    if(!inclL&&!inOl){
//                        int l=parent.size();
//                        for(int x=0;x<l;x++){
//
//                            if (node.hammHeuristic(parent.get(x))!=0)
//                                parent.add(node);
//                        }
                        int heuristic=0;
                        if(h.equalsIgnoreCase("ham")){
                            heuristic=neighbour[i].hammHeuristic(goal);
                            //System.out.println(heuristic+" "+neighbour[i].g);
                        }
                        else{
                            //System.out.println("here");
                            heuristic=neighbour[i].manhatan(goal);
                            //System.out.println(heuristic+" "+neighbour[i].g);
                        }
                        neighbour[i].costUpdate(heuristic);
                        expanded++;
                        //System.out.println(neighbour[i].f);
                        openList.add(neighbour[i]);
                    }

                }
            }
            //System.out.println("en");


        }

    }
//
    void end(node n){
        node x=n;
        while (n!=null){
            n.showGrid();
            System.out.println("| from |");
            n=n.parent;
        }

        System.out.println("Expanded: "+ expanded);
        System.out.println("Explored: "+explored);
        System.out.println("no of moves: "+x.g);
    }

    node[] createNeighbour(node node){
        node[] neighbour=new node[4];

        int k=node.grid[0].length;
        for(int i=0;i<4;i++){
            neighbour[i]=new node(k);
            neighbour[i].setParent(node);
        }

        int[] xy=findblank(node);
        //System.out.println("blank "+xy[0]+" "+xy[1]);
        if(xy[0]-1>=0){
            neighbour[0].copyGrid(node);
//            node.showGrid();
//            System.out.println("grid");
            int tm=neighbour[0].grid[xy[0]-1][xy[1]];
            neighbour[0].grid[xy[0]-1][xy[1]]=0;
            neighbour[0].grid[xy[0]][xy[1]]=tm;
        }else{
            neighbour[0]=null;

        }
        if(xy[0]+1<k){
//            node.showGrid();
//            System.out.println("grid");
            neighbour[1].copyGrid(node);
            int tm=neighbour[1].grid[xy[0]+1][xy[1]];
            neighbour[1].grid[xy[0]+1][xy[1]]=0;
            neighbour[1].grid[xy[0]][xy[1]]=tm;

        }else{
            neighbour[1]=null;
        }
        if(xy[1]+1<k){
//            node.showGrid();
//            System.out.println("grid");
            neighbour[2].copyGrid(node);
            int tm=neighbour[2].grid[xy[0]][xy[1]+1];
            neighbour[2].grid[xy[0]][xy[1]+1]=0;
            neighbour[2].grid[xy[0]][xy[1]]=tm;

        }else{
            neighbour[2]=null;
        }
        if(xy[1]-1>=0){
//            node.showGrid();
//            System.out.println("grid");
            neighbour[3].copyGrid(node);
            int tm=neighbour[3].grid[xy[0]][xy[1]-1];
            neighbour[3].grid[xy[0]][xy[1]-1]=0;
            neighbour[3].grid[xy[0]][xy[1]]=tm;

        }else{
            neighbour[3]=null;
        }

//        for(int i=0;i<4;i++){
//            if(prev!=null){
//                int d=neighbour[i].hammHeuristic(prev);
//                if(d==0){
//                   neighbour[i]=null;
//                }
//            }
//
//        }


        return neighbour;
    }
    int[] findblank(node n){
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
}
class myCompare implements Comparator<node> {

    @Override
    public int compare(node o1, node o2) {
        if(o1.f>o2.f)
            return 1;
        else
            return -1;
    }
}
