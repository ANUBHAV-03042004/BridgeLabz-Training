package eventfeedbackratinganalyzer;

public class InvalidRatingException extends Exception {

    private final int    rating;
    private final String participantId;
    private final String eventId;

    public InvalidRatingException(String participantId, String eventId, int rating) {
        super(String.format(
            "Invalid rating %d by participant [%s] for event [%s] — must be between 1 and 5.",
            rating, participantId, eventId));
        this.rating        = rating;
        this.participantId = participantId;
        this.eventId       = eventId;
    }

    public int    getRating()        { return rating;        }
    public String getParticipantId() { return participantId; }
    public String getEventId()       { return eventId;       }
}
