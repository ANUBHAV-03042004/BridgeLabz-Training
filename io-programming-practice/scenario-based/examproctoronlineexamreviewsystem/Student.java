package examproctoronlineexamreviewsystem;

import java.util.HashMap;
import java.util.Stack;

public abstract class Student {
    int studentId;
    HashMap<Integer,String> answersMap;

    Student(int studentId){
        this.studentId = studentId;
        this.answersMap = new HashMap<>();
    }

    abstract int checkAnswer();
}