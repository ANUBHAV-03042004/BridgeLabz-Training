package onlinehackathonsubmissionevaluator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

class HackathonEvaluator {

    private final List<Participant> participants = new ArrayList<>();
    private final LocalDateTime     deadline;

    public HackathonEvaluator(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public void registerParticipant(Participant p) {
        participants.add(p);
        System.out.println("Registered: " + p.getName() + " [" + p.getTeamName() + "]");
    }

    public void submitSolution(String participantId, LocalDateTime submittedAt,
                                Map<String, Boolean> results) throws LateSubmissionException {
        Participant p = findParticipant(participantId);
        p.setSubmittedAt(submittedAt);

        if (submittedAt.isAfter(deadline)) {
            p.setStatus(SubmissionStatus.LATE);
            throw new LateSubmissionException(participantId, submittedAt, deadline);
        }

        for (Map.Entry<String, Boolean> entry : results.entrySet()) {
            p.addTestResult(entry.getKey(), entry.getValue());
        }
        p.calculateScore();
        p.setStatus(SubmissionStatus.EVALUATED);
        System.out.println("[EVAL]   " + p.getName() + " score=" + p.getScore()
                + " passed=" + p.countPassed() + "/" + results.size());
    }

    public List<Participant> getRankedParticipants() {
        List<Participant> ranked = new ArrayList<>(participants);
        ranked.sort(Comparator.comparingInt(Participant::getScore).reversed()
                .thenComparing(Participant::getName));
        return ranked;
    }

    public Participant findParticipant(String participantId) {
        for (Participant p : participants) {
            if (p.getParticipantId().equals(participantId)) return p;
        }
        throw new NoSuchElementException("Participant not found: " + participantId);
    }

    public void printLeaderboard() {
        System.out.println("\nLeaderboard");
        System.out.printf("  %-4s %-6s %-18s %-15s %6s %7s %7s %12s%n",
                "Rank", "ID", "Name", "Team", "Score", "Passed", "Failed", "Status");
        List<Participant> ranked = getRankedParticipants();
        for (int i = 0; i < ranked.size(); i++) {
            Participant p = ranked.get(i);
            System.out.printf("  %-4d %-6s %-18s %-15s %6d %7d %7d %12s%n",
                    (i + 1), p.getParticipantId(), p.getName(), p.getTeamName(),
                    p.getScore(), p.countPassed(), p.countFailed(), p.getStatus());
        }
    }

    public void printTestCaseBreakdown(String participantId) {
        Participant p = findParticipant(participantId);
        System.out.println("\nTest Case Breakdown: " + p.getName());
        p.getTestCaseResults().forEach((qId, passed) ->
            System.out.printf("  %-12s : %s  (+%d pts)%n", qId, passed ? "PASS" : "FAIL", passed ? 10 : 0));
        System.out.println("  Total Score : " + p.getScore());
    }
}