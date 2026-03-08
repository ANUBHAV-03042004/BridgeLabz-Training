package eventfeedbackratinganalyzer;

public class EventFeedbackRatingAnalyzer {

    public static void main(String[] args) {

        FeedbackAnalyzer analyzer = new FeedbackAnalyzer();

        analyzer.registerEvent(new Event("E001", "Java Developer Conference",
                EventCategory.CONFERENCE, "Bangalore", "2025-06-10"));
        analyzer.registerEvent(new Event("E002", "AI/ML Workshop",
                EventCategory.WORKSHOP, "Hyderabad", "2025-06-15"));
        analyzer.registerEvent(new Event("E003", "Cloud Computing Seminar",
                EventCategory.SEMINAR, "Pune", "2025-06-20"));
        analyzer.registerEvent(new Event("E004", "Startup Networking Night",
                EventCategory.NETWORKING, "Mumbai", "2025-06-22"));
        analyzer.registerEvent(new Event("E005", "DevOps Summit",
                EventCategory.CONFERENCE, "Delhi", "2025-06-25"));

       
        String[] pIds   = {"P001","P002","P003","P004","P005","P006","P007","P008","P009","P010"};
        String[] pNames = {"Alice","Bob","Carol","Dave","Eva",
                           "Frank","Grace","Henry","Irene","James"};
        for (int i = 0; i < pIds.length; i++) {
            analyzer.registerParticipant(
                new Participant(pIds[i], pNames[i], pNames[i].toLowerCase() + "@mail.com"));
            System.out.println("[+] " + pIds[i] + " → " + pNames[i]);
        }

     
        int[][]          ratings  = {
    
            {   5,    4,    3,    2,    5 },  // Alice
            {   4,    5,    4,    3,    4 },  // Bob
            {   5,    5,    2,    4,    5 },  // Carol
            {   3,    4,    5,    5,    3 },  // Dave
            {   4,    3,    4,    4,    4 },  // Eva
            {   5,    4,    3,    2,    5 },  // Frank
            {   4,    5,    4,    3,    4 },  // Grace
            {   3,    4,    4,    5,    3 },  // Henry
            {   5,    5,    3,    4,    5 },  // Irene
            {   4,    4,    5,    4,    4 },  // James
        };
        String[] eventIds = {"E001","E002","E003","E004","E005"};
        String[][] comments = {
            {"Excellent keynotes!", "Great AI topics!", "Decent content", "Needs more speakers", "Best DevOps event"},
            {"Very informative", "Loved the hands-on", "Good basics", "Nice networking", "Practical sessions"},
            {"World-class speakers", "Top-notch content", "Average", "Fun atmosphere", "Very relevant"},
            {"Could be better", "Solid workshop", "Great labs!", "Met amazing people", "Needs improvement"},
            {"Good sessions", "Useful ML demos", "Good for beginners", "Well organized", "Really helpful"},
            {"Inspiring talks!", "Excellent content", "So-so", "Too short", "Loved it!"},
            {"Learned a lot", "Best workshop ever", "Informative", "Good connections", "Very practical"},
            {"Met great people", "Hands-on was great", "Okay content", "Loved the venue", "Solid event"},
            {"Fantastic!", "Mind-blowing demos", "Good overview", "Great food!", "Excellent coverage"},
            {"Great event", "Very useful", "Comprehensive", "Met future partners", "Top tier"},
        };

        try {
            for (int p = 0; p < pIds.length; p++) {
                for (int e = 0; e < eventIds.length; e++) {
                    analyzer.submitFeedback(pIds[p], eventIds[e], ratings[p][e], comments[p][e]);
                }
            }
        } catch (InvalidRatingException ex) {
            System.out.println("[ERROR] " + ex.getMessage());
        }

   
        try {
            analyzer.submitFeedback("P001", "E001", 0, "Zero is not valid");
        } catch (InvalidRatingException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
            System.out.printf( "         participantId=%s, eventId=%s, invalidRating=%d%n",
                    e.getParticipantId(), e.getEventId(), e.getRating());
        }

        try {
            analyzer.submitFeedback("P002", "E002", 6, "Six is too high");
        } catch (InvalidRatingException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

        try {
            analyzer.submitFeedback("P003", "E003", -3, "Negative is invalid");
        } catch (InvalidRatingException e) {
            System.out.println("[CAUGHT] " + e.getMessage());
        }

      
        try {
            analyzer.submitFeedback("P001", "E001", 3, "Changed my mind");
        } catch (InvalidRatingException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }

        analyzer.printEventSummary();

        
        analyzer.printTopRatedEvents(3);

       
        System.out.println("\nEvents Rated ≥ 4.0");
        analyzer.getEventsAboveRating(4.0)
                .forEach(e -> System.out.printf("   %-28s avg=%.2f%n",
                        e.getTitle(), analyzer.getAverageRating(e.getEventId())));

     
        System.out.println("\nMost Engaged Event");
        analyzer.getMostEngagedEvent().ifPresent(e ->
            System.out.printf("   %s (%d feedback submissions)%n",
                    e.getTitle(), analyzer.getRatingCount(e.getEventId())));

     
        analyzer.printDetailedEventReport("E001");
        analyzer.printDetailedEventReport("E005");

      
        analyzer.printRawRatings();
    }
}