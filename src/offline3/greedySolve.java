package offline3;

import java.util.ArrayList;
import java.util.Arrays;

public class greedySolve {

    int solve(ArrayList<course> courses){
        int time_slots=0;
        int len=courses.size();
        boolean slots[]=new boolean[len];

        for(course c: courses){
            Arrays.fill(slots,true);
            course[] conflictA=c.getConflictCouses();
            for(int i=0;i<conflictA.length;i++){
                int temp_s=conflictA[i].timeSlot;
                if(temp_s!=-1){
                    slots[temp_s]=false;
                }
            }

            for(int i=0;i<slots.length;i++){
                if(slots[i]){
                    c.timeSlot=i;
                    break;
                }
            }

            //counting time slots
            for(int i=0;i<slots.length;i++){
                if(!slots[i]&&i>time_slots){
                    time_slots=i;
                }
            }
        }

        return time_slots;
    }
}
