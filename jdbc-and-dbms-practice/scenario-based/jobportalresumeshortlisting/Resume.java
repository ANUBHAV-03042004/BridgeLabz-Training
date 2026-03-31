package jobportalresumeshortlisting;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Resume {

    private final String          resumeId;
    private final String          candidateName;
    private final String          email;
    private final int             experienceYears;
    private final ExperienceLevel level;
    private final Set<String>     skills;          // Set prevents duplicate skills
    private final double          expectedCtcLpa;
    private       ShortlistStatus status;
    private       int             matchScore;      // set during ranking

    public Resume(String resumeId, String candidateName, String email,
                  int experienceYears, ExperienceLevel level,
                  Set<String> skills, double expectedCtcLpa) {
        this.resumeId        = resumeId;
        this.candidateName   = candidateName;
        this.email           = email;
        this.experienceYears = experienceYears;
        this.level           = level;
        this.skills          = new HashSet<>(skills); // defensive copy
        this.expectedCtcLpa  = expectedCtcLpa;
        this.status          = ShortlistStatus.PENDING;
        this.matchScore      = 0;
    }

    public String          getResumeId()        { return resumeId;        }
    public String          getCandidateName()   { return candidateName;   }
    public String          getEmail()           { return email;           }
    public int             getExperienceYears() { return experienceYears; }
    public ExperienceLevel getLevel()           { return level;           }
    public Set<String>     getSkills()          { return Collections.unmodifiableSet(skills); }
    public double          getExpectedCtcLpa()  { return expectedCtcLpa;  }
    public ShortlistStatus getStatus()          { return status;          }
    public int             getMatchScore()      { return matchScore;      }



    public void setStatus(ShortlistStatus status)   { this.status     = status;     }
    public void setMatchScore(int matchScore)       { this.matchScore = matchScore; }

  
    public int countMatchingSkills(Set<String> requiredSkills) {
        long count = requiredSkills.stream()
                .filter(s -> skills.contains(s.toUpperCase()))
                .count();
        return (int) count;
    }

  
    public Set<String> getMatchingSkills(Set<String> requiredSkills) {
        return requiredSkills.stream()
                .filter(s -> skills.contains(s.toUpperCase()))
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return String.format(
            "Resume{id='%s', name='%-15s', exp=%dy, level=%-7s, score=%2d, " +
            "status=%-11s, ctc=%.1f LPA, skills=%s}",
            resumeId, candidateName, experienceYears, level,
            matchScore, status, expectedCtcLpa, skills);
    }
}
