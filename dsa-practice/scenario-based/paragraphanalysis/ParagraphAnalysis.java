package paragraphanalysis;
import java.util.Scanner;

public class ParagraphAnalysis {
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        String para = "Life is unpredictable, it throws challenges at us. Sometimes we stumble, but we keep moving forward. The journey teaches lessons, yet many ignore them. Success is not instant; it requires patience. Without effort, nothing changes, and still people hope for miracles. Reality demands persistence, but distractions are everywhere .";
        
        String words[] = para.split(" "), longestword = "";
        int max = Integer.MIN_VALUE;
        System.out.println("Number of words in this paragraph are : " + words.length);
        
        for(String i : words)
        {
            if(max < i.length()) {
                longestword = i;
                max = i.length();
            }
        }
        System.out.println("Longest word is :" + longestword);
        
        System.out.println("Enter the word you want to replace: ");
        String wordtogetreplaced = sc.next();
        System.out.println("Enter the word you want to replace it with: ");
        String willreplaceword = sc.next();


        StringBuilder updated = new StringBuilder();
        for (String w : words) {
            if (w.equalsIgnoreCase(wordtogetreplaced)) {
                updated.append(willreplaceword).append(" ");
            } else {
                updated.append(w).append(" ");
            }
        }
        para = updated.toString().trim();

        System.out.println("Text : " + para);
    }
}