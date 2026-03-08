package eventfeedbackratinganalyzer;

public class Event {

    private final String        eventId;
    private final String        title;
    private final EventCategory category;
    private final String        venue;
    private final String        date;
    private       EventStatus   status;

    public Event(String eventId, String title, EventCategory category,
                 String venue, String date) {
        this.eventId  = eventId;
        this.title    = title;
        this.category = category;
        this.venue    = venue;
        this.date     = date;
        this.status   = EventStatus.UPCOMING;
    }

    public String        getEventId()  { return eventId;  }
    public String        getTitle()    { return title;    }
    public EventCategory getCategory() { return category; }
    public String        getVenue()    { return venue;    }
    public String        getDate()     { return date;     }
    public EventStatus   getStatus()   { return status;   }
    public void          setStatus(EventStatus s) { this.status = s; }

    @Override
    public String toString() {
        return String.format("Event{id='%s', title='%s', category=%s, venue='%s', date='%s', status=%s}",
                eventId, title, category, venue, date, status);
    }
}