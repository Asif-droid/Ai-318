package offline3;

import java.util.ArrayList;

public class student {
    ArrayList<course> courseList;
    public student(){
        this.courseList=new ArrayList<>();
    }
    public void add(course course){
        courseList.add(course);
    }
    public course[] getCourseList(){
        course[] courseA=new course[courseList.size()];
        for(int i=0;i<courseA.length;i++){
            courseA[i]=courseList.get(i);
        }
        return courseA;
    }
    double calculatePenalty(){
        double penalty=0;
        for(int i=0;i<courseList.size();i++){
            for(int j=0;j<courseList.size();j++){
                if(j!=i){
                    int x=courseList.get(i).timeSlot-courseList.get(j).timeSlot;
                    int gap=Math.abs(x);
                    if(gap<5){
                        penalty+=Math.pow(2,5-gap);
                    }
                }

            }
        }

        return penalty;
    }

    double linearPenalty(){
        double penalty=0;
        for(int i=0;i<courseList.size();i++){
            for(int j=0;j<courseList.size();j++){
                if(j!=i){
                    int x=courseList.get(i).timeSlot-courseList.get(j).timeSlot;
                    int gap=Math.abs(x);
                    if(gap<5){
                        penalty+=2*(5-gap);
                    }
                }

            }
        }

        return penalty;
    }
}
