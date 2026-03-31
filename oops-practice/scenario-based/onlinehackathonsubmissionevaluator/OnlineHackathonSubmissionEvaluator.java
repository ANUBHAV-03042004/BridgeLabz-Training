package onlinehackathonsubmissionevaluator;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class OnlineHackathonSubmissionEvaluator {

    public static void main(String[] args) {

        LocalDateTime deadline   = LocalDateTime.of(2025, 6, 15, 18, 0);
        HackathonEvaluator eval  = new HackathonEvaluator(deadline);

        System.out.println("Deadline: " + deadline);
        System.out.println("\nRegistering Participants");

        Participant p1 = new Participant("P001", "Alice",   "CodeCraft");
        Participant p2 = new Participant("P002", "Bob",     "ByteBuilders");
        Participant p3 = new Participant("P003", "Carol",   "NullPointers");
        Participant p4 = new Participant("P004", "Dave",    "StackOverflow");
        Participant p5 = new Participant("P005", "Eve",     "CodeCraft");

        eval.registerParticipant(p1);
        eval.registerParticipant(p2);
        eval.registerParticipant(p3);
        eval.registerParticipant(p4);
        eval.registerParticipant(p5);


        Map<String, Boolean> r1 = new LinkedHashMap<>();
        r1.put("Q1", true); r1.put("Q2", true); r1.put("Q3", false); r1.put("Q4", true); r1.put("Q5", true);

        Map<String, Boolean> r2 = new LinkedHashMap<>();
        r2.put("Q1", true); r2.put("Q2", false); r2.put("Q3", true); r2.put("Q4", true); r2.put("Q5", false);

        Map<String, Boolean> r3 = new LinkedHashMap<>();
        r3.put("Q1", true); r3.put("Q2", true); r3.put("Q3", true); r3.put("Q4", true); r3.put("Q5", true);

        Map<String, Boolean> r4 = new LinkedHashMap<>();
        r4.put("Q1", false); r4.put("Q2", false); r4.put("Q3", true); r4.put("Q4", false); r4.put("Q5", true);

        try {
            eval.submitSolution("P001", LocalDateTime.of(2025, 6, 15, 17, 30), r1);
            eval.submitSolution("P002", LocalDateTime.of(2025, 6, 15, 16, 45), r2);
            eval.submitSolution("P003", LocalDateTime.of(2025, 6, 15, 10, 0),  r3);
            eval.submitSolution("P004", LocalDateTime.of(2025, 6, 15, 17, 59), r4);
        } catch (LateSubmissionException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        Map<String, Boolean> r5 = new LinkedHashMap<>();
        r5.put("Q1", true); r5.put("Q2", true); r5.put("Q3", true); r5.put("Q4", true); r5.put("Q5", false);
        try {
            eval.submitSolution("P005", LocalDateTime.of(2025, 6, 15, 18, 30), r5);
        } catch (LateSubmissionException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
            System.out.println("participantId=" + e.getParticipantId());
            System.out.println("submittedAt="   + e.getSubmittedAt());
            System.out.println("deadline="      + e.getDeadline());
        }

        eval.printTestCaseBreakdown("P001");
        eval.printTestCaseBreakdown("P003");
        eval.printLeaderboard();
    }
}
