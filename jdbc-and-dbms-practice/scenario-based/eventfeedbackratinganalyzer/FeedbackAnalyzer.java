package eventfeedbackratinganalyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public 
class FeedbackAnalyzer {

  
    private final Map<String, List<Integer>>  ratingsByEvent  = new LinkedHashMap<>();


    private final Map<String, List<Feedback>> feedbackByEvent = new LinkedHashMap<>();

   
    private final Map<String, Event>       events       = new LinkedHashMap<>();
    private final Map<String, Participant> participants = new LinkedHashMap<>();

  
    private final Map<String, Set<String>> ratedBy = new HashMap<>(); 

 
    public void registerEvent(Event event) {
        events.put(event.getEventId(), event);
        ratingsByEvent.put(event.getEventId(), new ArrayList<>());
        feedbackByEvent.put(event.getEventId(), new ArrayList<>());
        ratedBy.put(event.getEventId(), new HashSet<>());
        System.out.println("Event registered: " + event);
    }

    public void registerParticipant(Participant p) {
        participants.put(p.getParticipantId(), p);
    }

  
    public void submitFeedback(String participantId, String eventId,
                               int rating, String comment)
            throws InvalidRatingException {

      
        if (rating < 1 || rating > 5) {
            throw new InvalidRatingException(participantId, eventId, rating);
        }

        if (!events.containsKey(eventId))
            throw new NoSuchElementException("Event not found: " + eventId);

        if (!participants.containsKey(participantId))
            throw new NoSuchElementException("Participant not found: " + participantId);

        Set<String> raters = ratedBy.get(eventId);
        if (raters.contains(participantId)) {
            System.out.println("[WARN] " + participantId
                               + " has already rated event " + eventId + ". Skipping.");
            return;
        }

        ratingsByEvent.get(eventId).add(rating);

     
        Feedback feedback = new Feedback(participantId, eventId, rating, comment);
        feedbackByEvent.get(eventId).add(feedback);
        raters.add(participantId);

        String pName = participants.get(participantId).getName();
        System.out.printf("Feedback received | event=%-6s | %-12s | rating=%d %s%n",
                eventId, pName, rating, feedback.getStarDisplay());
    }

    public double getAverageRating(String eventId) {
        List<Integer> ratings = ratingsByEvent.getOrDefault(eventId, Collections.emptyList());
        return ratings.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    public int getRatingCount(String eventId) {
        return ratingsByEvent.getOrDefault(eventId, Collections.emptyList()).size();
    }

   
    public Map<Integer, Long> getRatingDistribution(String eventId) {
        List<Integer> ratings = ratingsByEvent.getOrDefault(eventId, Collections.emptyList());
        Map<Integer, Long> dist = new TreeMap<>();
        for (int i = 1; i <= 5; i++) dist.put(i, 0L);
        for (int r : ratings) dist.merge(r, 1L, Long::sum);
        return dist;
    }

    public List<Event> getTopRatedEvents(int topN) {
        return events.keySet().stream()
                .filter(id -> getRatingCount(id) > 0)
                .sorted((a, b) -> Double.compare(getAverageRating(b), getAverageRating(a)))
                .limit(topN)
                .map(events::get)
                .collect(Collectors.toList());
    }

    public List<Event> getEventsAboveRating(double threshold) {
        return events.keySet().stream()
                .filter(id -> getAverageRating(id) >= threshold)
                .map(events::get)
                .collect(Collectors.toList());
    }

    public List<Feedback> getSortedFeedback(String eventId) {
        return feedbackByEvent.getOrDefault(eventId, Collections.emptyList())
                .stream()
                .sorted(Comparator.comparingInt(Feedback::getRating).reversed())
                .collect(Collectors.toList());
    }

  
    public Optional<Event> getMostEngagedEvent() {
        return ratingsByEvent.entrySet().stream()
                .max(Comparator.comparingInt(e -> e.getValue().size()))
                .map(e -> events.get(e.getKey()));
    }

    public double getOverallAverage() {
        return ratingsByEvent.values().stream()
                .flatMap(List::stream)
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

 
    public void printEventSummary() {

        System.out.printf("   %-6s %-28s %-14s %-7s %-9s %s%n",
                "ID", "Title", "Category", "Ratings", "Avg", "Stars");
        System.out.println("   " + "─".repeat(78));

        events.forEach((id, event) -> {
            int    count = getRatingCount(id);
            double avg   = getAverageRating(id);
            int    stars = (int) Math.round(avg);
            System.out.printf("   %-6s %-28s %-14s %-7d %-9.2f %s%n",
                    id, event.getTitle(), event.getCategory(),
                    count, avg,
                    count > 0 ? "★".repeat(stars) + "☆".repeat(5 - stars) : "(no ratings)");
        });

        System.out.printf("%n   Overall average across all events: %.2f / 5.00%n",
                getOverallAverage());
    }

    public void printDetailedEventReport(String eventId) {
        Event event = events.get(eventId);
        if (event == null) { System.out.println("Event not found: " + eventId); return; }

        System.out.println("\nDetailed Report: " + event.getTitle());
        System.out.printf("   Venue: %s | Date: %s | Category: %s%n",
                event.getVenue(), event.getDate(), event.getCategory());
        System.out.printf("   Total Ratings : %d%n", getRatingCount(eventId));
        System.out.printf("   Average Rating: %.2f / 5.00%n", getAverageRating(eventId));

        // Rating distribution bar chart
        System.out.println("\n   Rating Distribution:");
        Map<Integer, Long> dist = getRatingDistribution(eventId);
        int total = getRatingCount(eventId);
        dist.forEach((stars, count) -> {
            int    bars    = total > 0 ? (int) ((count * 20.0) / total) : 0;
            double percent = total > 0 ? (count * 100.0) / total : 0;
            System.out.printf("     %d ★  │%s│ %2d (%.1f%%)%n",
                    stars, "█".repeat(bars) + " ".repeat(20 - bars), count, percent);
        });

       
        System.out.println("\n   Individual Feedback (sorted by rating):");
        List<Feedback> sorted = getSortedFeedback(eventId);
        sorted.forEach(fb -> {
            Participant p = participants.get(fb.getParticipantId());
            String name   = p != null ? p.getName() : fb.getParticipantId();
            System.out.printf("     %-14s %s  \"%s\"%n",
                    name, fb.getStarDisplay(), fb.getComment());
        });
    }

    public void printTopRatedEvents(int n) {
        System.out.println("\nTop " + n + " Rated Events");
        List<Event> top = getTopRatedEvents(n);
        if (top.isEmpty()) { System.out.println("   (no ratings yet)"); return; }
        for (int i = 0; i < top.size(); i++) {
            Event e = top.get(i);
            System.out.printf("   %d. %-28s avg=%.2f (%d ratings)%n",
                    i + 1, e.getTitle(), getAverageRating(e.getEventId()),
                    getRatingCount(e.getEventId()));
        }
    }

    public void printRawRatings() {
        System.out.println("\nRaw Map<EventID, List<Integer>>");
        ratingsByEvent.forEach((id, ratings) ->
            System.out.printf("   %-6s → %s%n", id, ratings));
    }
}
