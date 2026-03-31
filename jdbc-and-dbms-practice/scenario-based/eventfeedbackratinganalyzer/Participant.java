package eventfeedbackratinganalyzer;

public class Participant {

    private final String participantId;
    private final String name;
    private final String email;

    public Participant(String participantId, String name, String email) {
        this.participantId = participantId;
        this.name          = name;
        this.email         = email;
    }

    public String getParticipantId() { return participantId; }
    public String getName()          { return name;          }
    public String getEmail()         { return email;         }

    @Override
    public String toString() {
        return String.format("Participant{id='%s', name='%s'}", participantId, name);
    }
}
