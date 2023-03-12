package offline3;

import java.util.ArrayList;

public class course {
    String courseId;
    String stNum;
    int inChain;
    public int timeSlot;
    public int saturation;
    ArrayList<course> conflictList;
    public course(String courseId,String stNum){
        this.courseId=courseId;
        this.stNum=stNum;
        conflictList=new ArrayList<>();
        timeSlot=-1;
        inChain=0;
        saturation=0;
    }
    public void addConflict(course course){
        conflictList.add(course);
    }
    int getCrsid(){
        return Integer.parseInt(courseId);
    }
    course[] getConflictCouses(){
        course[] conflictArray=new course[conflictList.size()];
        int i=0;
        for(course c:conflictList){
            conflictArray[i++]=c;
        }
        return conflictArray;
    }
    public int getOverLappingCourseNum(){
        return conflictList.size();
    }

    public int getStNum() {
        return Integer.parseInt(stNum);
    }
}
