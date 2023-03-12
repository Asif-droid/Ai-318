package offline3;

import offline3.comparators.DegreeComparator;
import offline3.comparators.SaturationComaparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DsatureAlgo {





    int solve(ArrayList<course> courses){
        int total_slot=0;
        Collections.sort(courses,new SaturationComaparator());
        courses.get(0).timeSlot=0;
        int totalCourse=courses.size();

        for(int i=1;i<courses.size();i++){

            updateSaturation(courses.get(i-1));
            Collections.sort(courses,new SaturationComaparator());
            course current=courses.get(0);
            int tempSlots=assignTime(current,totalCourse);
            current.saturation=0;
            if(total_slot<tempSlots){
                total_slot=tempSlots;
            }
        }

        return total_slot;
    }

    int assignTime(course c,int sz){
        int time_slots=0;
        boolean[] slots=new boolean[sz];
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

        for(int i=0;i<slots.length;i++){
            if(!slots[i]&&i>time_slots){
                time_slots=i;
            }
        }
        //System.out.println(time_slots);
        return time_slots;
    }
    void updateSaturation(course course){
        course[] overLapped=course.getConflictCouses();
        for(int i=0;i<overLapped.length;i++){
            if(overLapped[i].timeSlot==-1){
                overLapped[i].saturation++;
            }
        }
    }




}
