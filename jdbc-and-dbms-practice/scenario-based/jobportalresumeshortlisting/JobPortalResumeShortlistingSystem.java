package jobportalresumeshortlisting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class JobPortalResumeShortlistingSystem {

    public static void main(String[] args) {
        ShortlistingService service = new ShortlistingService();

        Set<String> javaSkills = new LinkedHashSet<>(Arrays.asList(
                "JAVA", "SPRING BOOT", "REST API", "SQL", "DOCKER"));
        JobPosting javaJob = new JobPosting(
                "J001", "Senior Java Developer", "TechCorp",
                javaSkills, 3, ExperienceLevel.MID, 18.0, 3);
        System.out.println(javaJob);

        Set<String> mlSkills = new LinkedHashSet<>(Arrays.asList(
                "PYTHON", "MACHINE LEARNING", "TENSORFLOW", "SQL"));
        JobPosting mlJob = new JobPosting(
                "J002", "ML Engineer", "AI Labs",
                mlSkills, 2, ExperienceLevel.JUNIOR, 22.0, 2);
        System.out.println(mlJob);

        List<Resume> resumePool = new ArrayList<>();

        resumePool.add(new Resume("R001", "Alice Johnson", "alice@mail.com", 5, ExperienceLevel.MID,
                new HashSet<>(Arrays.asList("JAVA", "SPRING BOOT", "REST API", "SQL", "DOCKER")), 15.0));

        resumePool.add(new Resume("R002", "Bob Smith", "bob@mail.com", 3, ExperienceLevel.MID,
                new HashSet<>(Arrays.asList("JAVA", "SPRING BOOT", "REST API", "SQL")), 12.0));

        resumePool.add(new Resume("R003", "Carol Lee", "carol@mail.com", 7, ExperienceLevel.SENIOR,
                new HashSet<>(Arrays.asList("JAVA", "SPRING BOOT", "REST API", "SQL", "DOCKER", "KUBERNETES")), 20.0));

        resumePool.add(new Resume("R004", "Dave Chen", "dave@mail.com", 1, ExperienceLevel.JUNIOR,
                new HashSet<>(Arrays.asList("JAVA", "SQL")), 8.0));

        resumePool.add(new Resume("R005", "Eva Williams", "eva@mail.com", 4, ExperienceLevel.MID,
                new HashSet<>(Arrays.asList("PYTHON", "DJANGO", "SQL")), 13.0));

        resumePool.add(new Resume("R006", "Frank Brown", "frank@mail.com", 3, ExperienceLevel.MID,
                new HashSet<>(Arrays.asList("JAVA", "SPRING BOOT", "REST API", "SQL", "DOCKER")), 16.0));

        resumePool.add(new Resume("R007", "Grace Kim", "grace@mail.com", 2, ExperienceLevel.JUNIOR,
                new HashSet<>(Arrays.asList("PYTHON", "MACHINE LEARNING", "TENSORFLOW", "SQL")), 18.0));

        resumePool.add(new Resume("R008", "Henry Patel", "henry@mail.com", 5, ExperienceLevel.SENIOR,
                new HashSet<>(Arrays.asList("PYTHON", "MACHINE LEARNING", "TENSORFLOW", "SQL", "KERAS")), 21.0));

        resumePool.forEach(r -> System.out.println("  Submitted: " + r.getCandidateName()
                + " | skills=" + r.getSkills()));

        resumePool.add(new Resume("R009", "", "bad@mail.com", 2,
                ExperienceLevel.JUNIOR, new HashSet<>(Arrays.asList("JAVA")), 5.0));

        resumePool.add(new Resume("R010", "NoSkills Guy", "noskills@mail.com", 3,
                ExperienceLevel.JUNIOR, new HashSet<>(), 5.0));

        resumePool.add(new Resume("R011", "OldTimer","old@mail.com", -1,
                ExperienceLevel.SENIOR, new HashSet<>(Arrays.asList("JAVA")), 5.0));

        resumePool.add(new Resume("R012", "BadEmail",     "notanemail", 2,
                ExperienceLevel.JUNIOR, new HashSet<>(Arrays.asList("JAVA", "SQL")), 8.0));

      
        Resume bad = new Resume("R013", "   ", "x@y.com", 200,
                ExperienceLevel.FRESHER, new HashSet<>(Arrays.asList("C++")), 5.0);
        try {
            service.validate(bad);
        } catch (InvalidResumeException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
            System.out.println("field='" + e.getField() + "', value='" + e.getValue() + "'");
        }

        List<Resume> javaResults = service.shortlist(new ArrayList<>(resumePool), javaJob);
        service.printRankedResults(javaResults, javaJob);
        service.printShortlistedOnly(javaResults);

        List<Resume> mlResults = service.shortlist(new ArrayList<>(resumePool), mlJob);
        service.printRankedResults(mlResults, mlJob);
        service.printShortlistedOnly(mlResults);

     
        resumePool.stream()
                  .filter(r -> r.getExperienceYears() >= 0 && !r.getCandidateName().isBlank()
                          && !r.getSkills().isEmpty())
                  .sorted(ResumeComparators.BY_CTC_ASC)
                  .forEach(r -> System.out.printf("   %-16s %.1f LPA%n",
                          r.getCandidateName(), r.getExpectedCtcLpa()));
    }
}