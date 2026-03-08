package jobportalresumeshortlisting;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ShortlistingService {

    public void validate(Resume resume) throws InvalidResumeException {

        if (resume.getCandidateName() == null || resume.getCandidateName().isBlank())
            throw new InvalidResumeException("candidateName", resume.getCandidateName(),
                    "name must not be blank");

        if (resume.getEmail() == null || !resume.getEmail().contains("@"))
            throw new InvalidResumeException("email", String.valueOf(resume.getEmail()),
                    "email must contain '@'");

        if (resume.getExperienceYears() < 0)
            throw new InvalidResumeException("experienceYears",
                    String.valueOf(resume.getExperienceYears()),
                    "experience years cannot be negative");

        if (resume.getExperienceYears() > 50)
            throw new InvalidResumeException("experienceYears",
                    String.valueOf(resume.getExperienceYears()),
                    "experience years exceeds maximum allowed (50)");

        if (resume.getSkills() == null || resume.getSkills().isEmpty())
            throw new InvalidResumeException("skills", "[]",
                    "at least one skill is required");

        if (resume.getExpectedCtcLpa() < 0)
            throw new InvalidResumeException("expectedCtcLpa",
                    String.valueOf(resume.getExpectedCtcLpa()),
                    "expected CTC cannot be negative");
    }

  
    public List<Resume> shortlist(List<Resume> resumes, JobPosting job) {

        System.out.println("\n[Shortlisting] Job: " + job.getTitle()
                           + " | Required skills: " + job.getRequiredSkills());

        List<Resume> validResumes   = new ArrayList<>();
        List<Resume> invalidResumes = new ArrayList<>();

     
        for (Resume resume : resumes) {
            try {
                validate(resume);
                int score = computeScore(resume, job);
                resume.setMatchScore(score);
                validResumes.add(resume);
            } catch (InvalidResumeException e) {
                resume.setStatus(ShortlistStatus.REJECTED);
                invalidResumes.add(resume);
                System.out.println("  [INVALID] " + resume.getCandidateName()
                                   + " → " + e.getMessage());
            }
        }

       
        validResumes.sort(ResumeComparators.COMPOSITE);

      
        int limit = job.getShortlistLimit();
        for (int i = 0; i < validResumes.size(); i++) {
            Resume r = validResumes.get(i);
            if (i < limit && r.getMatchScore() > 0) {
                r.setStatus(ShortlistStatus.SHORTLISTED);
            } else {
                r.setStatus(ShortlistStatus.REJECTED);
            }
        }

    
        List<Resume> result = new ArrayList<>(validResumes);
        result.addAll(invalidResumes);
        return result;
    }

    private int computeScore(Resume resume, JobPosting job) {
        int score = 0;

        int matching = resume.countMatchingSkills(job.getRequiredSkills());
        score += matching * 10;

        if (matching == job.getRequiredSkills().size()) score += 5; // full match bonus

        int relevantExp = Math.min(resume.getExperienceYears(), job.getMinExperienceYears() + 5);
        score += relevantExp * 3;

        if (resume.getExpectedCtcLpa() > job.getBudgetCtcLpa()) score -= 2;

        return Math.max(score, 0);
    }

   
    public void printRankedResults(List<Resume> ranked, JobPosting job) {
        System.out.println("\nRanked Resumes for: " + job.getTitle()
                           + " @ " + job.getCompany());
        System.out.printf("   %-4s %-6s %-16s %-7s %-8s %-10s %-12s %s%n",
                "Rank", "ID", "Name", "Score", "ExpYrs", "Status", "CTC(LPA)", "Matching Skills");


        int rank = 1;
        for (Resume r : ranked) {
            if (r.getStatus() == ShortlistStatus.REJECTED && r.getMatchScore() == 0) continue;
            Set<String> matched = r.getMatchingSkills(job.getRequiredSkills());
            System.out.printf("   %-4d %-6s %-16s %-7d %-8d %-12s %-12.1f %s%n",
                    rank++, r.getResumeId(), r.getCandidateName(),
                    r.getMatchScore(), r.getExperienceYears(),
                    r.getStatus(), r.getExpectedCtcLpa(), matched);
        }
    }

    public void printShortlistedOnly(List<Resume> ranked) {
        System.out.println("\nShortlisted Candidates");
        ranked.stream()
              .filter(r -> r.getStatus() == ShortlistStatus.SHORTLISTED)
              .forEach(r -> System.out.printf("%-16s | score=%-3d | exp=%dy | skills=%s%n",
                      r.getCandidateName(), r.getMatchScore(),
                      r.getExperienceYears(), r.getSkills()));
    }
}
