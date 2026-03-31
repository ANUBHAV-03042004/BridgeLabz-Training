package jobportalresumeshortlisting;

import java.util.Comparator;

public class ResumeComparators {

    public static final Comparator<Resume> BY_MATCH_SCORE =
            Comparator.comparingInt(Resume::getMatchScore).reversed();

    public static final Comparator<Resume> BY_EXPERIENCE =
            Comparator.comparingInt(Resume::getExperienceYears).reversed();

   
    public static final Comparator<Resume> BY_CTC_ASC =
            Comparator.comparingDouble(Resume::getExpectedCtcLpa);

    public static final Comparator<Resume> COMPOSITE =
            BY_MATCH_SCORE
            .thenComparing(BY_EXPERIENCE)
            .thenComparing(BY_CTC_ASC);
}
