package offline3;

import offline3.comparators.DegreeComparator;
import offline3.comparators.LargestEnrolled;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class main {


    /*: 1.src\offline3\data3\yor-f-83.crs
        2.src\offline3\data3\yor-f-83.stu
        3.src\offline3\data3\tre-s-92.stu
        4.src\offline3\data3\tre-s-92.crs
        5.src\offline3\data3\car-f-92.crs
        6.src\offline3\data3\car-f-92.stu
        7.src\offline3\data3\car-s-91.crs
        8.src\offline3\data3\car-s-91.stu
        9.src\offline3\data3\kfu-s-93.crs
        10.src\offline3\data3\kfu-s-93.stu

    */



    public static void main(String[] args) {
        File crs=new File("src\\offline3\\data3\\car-f-92.crs");
        File st=new File("src\\offline3\\data3\\car-f-92.stu");
        try {
            Scanner scCrs=new Scanner(crs);
            Scanner scSt=new Scanner(st);
            ArrayList<course> courseList=new ArrayList<>();
            ArrayList<student> students=new ArrayList<>();
            HashMap<String,course> hashMap=new HashMap<>();
            while(scCrs.hasNextLine()){
                String crsLine=scCrs.nextLine();
                String crsInfos[]=crsLine.split(" ");
                course course=new course(crsInfos[0],crsInfos[1]);
                courseList.add(course);
                hashMap.put(course.courseId,course);
               // System.out.println(crsInfos[0]+" has "+crsInfos[1]);
            }
            while(scSt.hasNextLine()){
                String crsLine=scSt.nextLine();
                String crses[]=crsLine.split(" ");
                student student=new student();
                for(String c:crses){
                    course course=hashMap.get(c);
                    student.add(course);
                    for(String c1:crses){
                        if(!c1.equalsIgnoreCase(c)){
                            course x= hashMap.get(c1);
                            course.addConflict(x);
                        }
                    }

                }
                students.add(student);
                
            }
//            greedySolve gs=new greedySolve();
//            //LargestEnrolment(courseList);
//            System.out.println(gs.solve(courseList));


            DsatureAlgo ds=new DsatureAlgo();
            System.out.println(ds.solve(courseList));

            System.out.println(averagePenalty(students));//exponential
            //System.out.println(linearPenalty(students));//linear
            PartuvativeHeuristic ph=new PartuvativeHeuristic();
            ph.Reduction(courseList,students,1);//1 for kempechain
            System.out.println(averagePenalty(students));//exponential
            //System.out.println(linearPenalty(students));//linear
            ph.Reduction(courseList,students,2);//2 for pairSwap
            System.out.println(averagePenalty(students));//exponential
            //System.out.println(linearPenalty(students));//linear



            for(course c:courseList){  // scheduling check
                for(course x:c.conflictList){
                    if(x.timeSlot==c.timeSlot){
                        System.out.println("problem");
                    }
                }

            }






        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    static double averagePenalty(ArrayList<student> students){
        double totalPenalty=0;
        for (student s:students){
            totalPenalty+=s.calculatePenalty();
        }
        return totalPenalty/students.size();
    }
    static double linearPenalty(ArrayList<student> students){
        double totalPenalty=0;
        for (student s:students){
            totalPenalty+=s.linearPenalty();
        }
        return totalPenalty/students.size();
    }
    static void LargestDegree(ArrayList<course> courses){
        Collections.sort(courses,new DegreeComparator());
    }
    static void LargestEnrolment(ArrayList<course> courses){
        Collections.sort(courses,new LargestEnrolled());
    }
}
