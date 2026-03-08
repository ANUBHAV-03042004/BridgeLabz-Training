package eventfeedbackratinganalyzer;

public class Feedback {

    private final String participantId;
    private final String eventId;
    private final int    rating;          // 1–5
    private final String comment;
    private final String submittedAt;

    public Feedback(String participantId, String eventId, int rating, String comment) {
        this.participantId = participantId;
        this.eventId       = eventId;
        this.rating        = rating;
        this.comment       = comment;
        this.submittedAt   = java.time.LocalDateTime.now()
                               .format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    public String getParticipantId() { return participantId; }
    public String getEventId()       { return eventId;       }
    public int    getRating()        { return rating;        }
    public String getComment()       { return comment;       }
    public String getSubmittedAt()   { return submittedAt;   }

    
    
    
    public String getStarDisplay() {
        return "★".repeat(rating) + "☆".repeat(5 - rating);
    }

    @Override
    public String toString() {
        return String.format("Feedback{participant='%s', rating=%d %s, comment='%s', at=%s}",
                participantId, rating, getStarDisplay(), comment, submittedAt);
    }
}