package examproctoronlineexamreviewsystem;

import java.util.HashMap;
import java.util.Stack;

public class ExamProctor extends Student {

    ExamProctor(int studentId) {
        super(studentId);
    }

    Stack<Integer> navigationStack = new Stack<>();
    HashMap<Integer,String> question = new HashMap<>();
    {
        question.put(1,"A");
        question.put(2,"B");
        question.put(3,"C");
        question.put(4,"D");
    }

    public void visitQuestion(int questionId) {
        navigationStack.push(questionId);
        System.out.println("Visited Question: " + questionId);
    }

    public void recordAnswer(int questionId, String answer) {
        answersMap.put(questionId, answer);
        System.out.println("Answer recorded for Q" + questionId + ": " + answer);
    }

    public void showNavigationHistory() {
        System.out.println("Navigation History (last visited on top): " + navigationStack.peek());
    }

    @Override
    int checkAnswer() {
        int anscount = 0;
        for(Integer entry: answersMap.keySet()) {
            String answer = answersMap.get(entry);
            String correctAnswer = question.get(entry);
            if(correctAnswer != null && correctAnswer.equalsIgnoreCase(answer)) {
                anscount++;
            }
        }
        return anscount;
    }

    public static void main(String[] args) {
        Student student1 = new ExamProctor(1);

        ExamProctor exam = (ExamProctor) student1;
        exam.visitQuestion(1);
        exam.recordAnswer(1, "A");

        exam.visitQuestion(2);
        exam.recordAnswer(2, "C");

        exam.showNavigationHistory();

        int score = exam.checkAnswer();
        System.out.println("Final Score: " + score);
    }
}