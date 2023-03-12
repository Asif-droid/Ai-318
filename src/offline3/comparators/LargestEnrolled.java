package offline3.comparators;

import offline3.course;

import java.util.Comparator;

public class LargestEnrolled implements Comparator<course> {

    @Override
    public int compare(course o1, course o2) {
        return o2.getStNum()-o1.getStNum();
    }
}
