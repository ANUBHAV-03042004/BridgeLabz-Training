package jobportalresumeshortlisting;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class JobPosting {

    private final String          jobId;
    private final String          title;
    private final String          company;
    private final Set<String>     requiredSkills;   // Set — no duplicate requirements
    private final int             minExperienceYears;
    private final ExperienceLevel requiredLevel;
    private final double          budgetCtcLpa;
    private final int             shortlistLimit;

    public JobPosting(String jobId, String title, String company,
                      Set<String> requiredSkills, int minExperienceYears,
                      ExperienceLevel requiredLevel, double budgetCtcLpa,
                      int shortlistLimit) {
        this.requiredSkills     = requiredSkills.stream()
                                               .map(String::toUpperCase)
                                               .collect(Collectors.toCollection(HashSet::new));
        this.jobId              = jobId;
        this.title              = title;
        this.company            = company;
        this.minExperienceYears = minExperienceYears;
        this.requiredLevel      = requiredLevel;
        this.budgetCtcLpa       = budgetCtcLpa;
        this.shortlistLimit     = shortlistLimit;
    }

    public String      getJobId()              { return jobId;              }
    public String      getTitle()              { return title;              }
    public String      getCompany()            { return company;            }
    public Set<String> getRequiredSkills()     { return Collections.unmodifiableSet(requiredSkills); }
    public int         getMinExperienceYears() { return minExperienceYears; }
    public double      getBudgetCtcLpa()       { return budgetCtcLpa;       }
    public int         getShortlistLimit()     { return shortlistLimit;     }

    @Override
    public String toString() {
        return String.format("JobPosting{id='%s', title='%s', company='%s', " +
                "skills=%s, minExp=%dy, budget=%.1f LPA}",
                jobId, title, company, requiredSkills, minExperienceYears, budgetCtcLpa);
    }
}
