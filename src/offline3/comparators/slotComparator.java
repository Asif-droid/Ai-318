package offline3.comparators;

import offline3.course;

import java.util.Comparator;

public class slotComparator implements Comparator<course> {
    @Override
    public int compare(course o1, course o2) {
        return o1.timeSlot-o2.timeSlot;
    }
}
