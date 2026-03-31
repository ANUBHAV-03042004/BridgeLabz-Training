package onlinehackathonsubmissionevaluator;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

class Participant {
    private final String                   participantId;
    private final String                   name;
    private final String                   teamName;
    private       Map<String, Boolean>     testCaseResults;
    private       int                      score;
    private       SubmissionStatus         status;
    private       LocalDateTime            submittedAt;

    public Participant(String participantId, String name, String teamName) {
        this.participantId  = participantId;
        this.name           = name;
        this.teamName       = teamName;
        this.testCaseResults = new LinkedHashMap<>();
        this.score           = 0;
        this.status          = SubmissionStatus.SUBMITTED;
    }

    public void addTestResult(String questionId, boolean passed) {
        testCaseResults.put(questionId, passed);
    }

    public void setSubmittedAt(LocalDateTime time) {
        this.submittedAt = time;
    }

    public int calculateScore() {
        int total = 0;
        for (boolean passed : testCaseResults.values()) {
            if (passed) total += 10;
        }
        this.score = total;
        return total;
    }

    public long countPassed() {
        return testCaseResults.values().stream().filter(b -> b).count();
    }

    public long countFailed() {
        return testCaseResults.values().stream().filter(b -> !b).count();
    }

    public String                getParticipantId()   { return participantId;   }
    public String                getName()            { return name;            }
    public String                getTeamName()        { return teamName;        }
    public Map<String, Boolean>  getTestCaseResults() { return Collections.unmodifiableMap(testCaseResults); }
    public int                   getScore()           { return score;           }
    public SubmissionStatus      getStatus()          { return status;          }
    public LocalDateTime         getSubmittedAt()     { return submittedAt;     }
    public void                  setStatus(SubmissionStatus s) { this.status = s; }

    @Override
    public String toString() {
        return "Participant[" + participantId + ", " + name + ", team=" + teamName
                + ", score=" + score + ", status=" + status + "]";
    }
}
