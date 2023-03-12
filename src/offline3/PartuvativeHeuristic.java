package offline3;

import java.util.ArrayList;
import java.util.Random;

public class PartuvativeHeuristic {


    public void Reduction(ArrayList<course> courses,ArrayList<student> students,int h){
        Random random=new Random();
        if(h==1){

            for (int i=0;i<5000;i++){
                int r1=random.nextInt(courses.size());
                course c1=courses.get(r1);


                kempeChain(students,courses,c1);

                //PairSwap(students,c1,c2);
            }

        }
        else {
            for (int i=0;i<5000;i++){
                int r1=random.nextInt(courses.size());
                int r2=random.nextInt(courses.size());
                course c1=courses.get(r1);
                course c2=courses.get(r2);
                if(c1.timeSlot==c2.timeSlot){
                    i--;
                    continue;
                }

                //kempeChain(students,courses,c1);

                PairSwap(students,c1,c2);
            }
        }



    }

    void buildingKChain(course current,int neighborTimeSlot){
        current.inChain=1;
        course[] overLapping=current.getConflictCouses();
        for(int i=0;i<overLapping.length;i++){
            if(overLapping[i].inChain==0 && overLapping[i].timeSlot==neighborTimeSlot){
                buildingKChain(overLapping[i],current.timeSlot);
            }
        }
        return;

    }

    void kempeChain (ArrayList<student> students,ArrayList<course> courses,course current){

        double prevPenalty=main.averagePenalty(students);//for exponential penalty
//        double prevPenalty=main.linearPenalty(students);//for linear penalty

        Random rnd=new Random();
        if(current.conflictList.size()==0){
            return;
        }
        int r=rnd.nextInt(current.conflictList.size());
        int neighbourTimeSlot=current.conflictList.get(r).timeSlot;
        int currentSlot=current.timeSlot;


        buildingKChain(current,neighbourTimeSlot);

        for(course c: courses){
           if(c.inChain==1){
               if(c.timeSlot==currentSlot){
                   c.timeSlot=neighbourTimeSlot;
               }
               else {
                   c.timeSlot=currentSlot;
               }
           }
        }
        double curPenalty=main.averagePenalty(students);//for exponential penalty
//        double curPenalty=main.linearPenalty(students);//for linear penalty
        if(curPenalty>=prevPenalty){
            for(course c: courses){
                if(c.inChain==1){
                    if(c.timeSlot==currentSlot){
                        c.timeSlot=neighbourTimeSlot;
                    }
                    else {
                        c.timeSlot=currentSlot;
                    }
                }
            }

        }

        for(int i=0; i<courses.size(); i++) {
            if(courses.get(i).inChain==1) {
                courses.get(i).inChain=0;
            }
        }



    }



    public void PairSwap(ArrayList<student> students,course c1,course c2){


        int c1_time=c1.timeSlot;
        int c2_time=c2.timeSlot;

        course[] OverArr=c1.getConflictCouses();


        for(int i=0;i<OverArr.length;i++){
            if(OverArr[i].timeSlot==c2_time){
                return;
            }
        }
        OverArr=c2.getConflictCouses();
        for(int i=0;i<OverArr.length;i++){
            if(OverArr[i].timeSlot==c1_time){
                return;
            }
        }
        //System.out.println(c1_time+"he"+c2_time);
        double prevPenalty=main.averagePenalty(students);//exponential penalty
//        double prevPenalty=main.linearPenalty(students);//for linear
        c1.timeSlot=c2_time;
        c2.timeSlot=c1_time;
        //System.out.println("changed");
        double curPenalty=main.averagePenalty(students);//for exponential penalty
//        double curPenalty=main.linearPenalty(students);//for linear penalty
        if(prevPenalty < curPenalty){
            c1.timeSlot=c1_time;
            c2.timeSlot=c2_time;
        }

        return;

    }


}
