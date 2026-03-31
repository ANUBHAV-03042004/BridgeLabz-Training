package onlinehackathonsubmissionevaluator;

import java.time.LocalDateTime;

public class LateSubmissionException extends Exception {
    private final String participantId;
    private final LocalDateTime submittedAt;
    private final LocalDateTime deadline;

    public LateSubmissionException(String participantId,
                                    LocalDateTime submittedAt, LocalDateTime deadline) {
        super("Late submission by [" + participantId + "] — submitted at "
                + submittedAt + ", deadline was " + deadline);
        this.participantId = participantId;
        this.submittedAt   = submittedAt;
        this.deadline      = deadline;
    }

    public String        getParticipantId() { return participantId; }
    public LocalDateTime getSubmittedAt()   { return submittedAt;   }
    public LocalDateTime getDeadline()      { return deadline;      }
}