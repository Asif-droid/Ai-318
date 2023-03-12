package offline3.comparators;

import offline3.course;

import java.util.Comparator;

public class SaturationComaparator implements Comparator<course> {
    @Override
    public int compare(course o1, course o2) {
        if(o1.saturation==o2.saturation){
            return o2.getOverLappingCourseNum()-o1.getOverLappingCourseNum();
        }
        else {
            return o2.saturation-o1.saturation;
        }


    }
}
