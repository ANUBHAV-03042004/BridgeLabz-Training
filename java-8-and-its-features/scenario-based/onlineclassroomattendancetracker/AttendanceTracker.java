package onlineclassroomattendancetracker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AttendanceTracker {
	  private Map<String, Set<String>> sessionAttendance = new HashMap<>();

	public void markAttendance(String sessionId,String studentId) throws DuplicateAttendanceException{
		 sessionAttendance.putIfAbsent(sessionId, new HashSet<>());
	        Set<String> students = sessionAttendance.get(sessionId);

	        if (!students.add(studentId)) {
	            throw new DuplicateAttendanceException("Student " + studentId + " already marked for session " + sessionId);
	        }
	        System.out.println("Marked attendance: " + studentId + " for session " + sessionId);
	    }
	public void removeAttendance(String sessionId, String studentId) {
        if (sessionAttendance.containsKey(sessionId)) {
            sessionAttendance.get(sessionId).remove(studentId);
            System.out.println("Removed attendance: " + studentId + " from session " + sessionId);
        }
    }
	   public void displayAttendance(String sessionId) {
	        if (sessionAttendance.containsKey(sessionId)) {
	            System.out.println("Attendance for session " + sessionId + ": " + sessionAttendance.get(sessionId));
	        } else {
	            System.out.println("No attendance recorded for session " + sessionId);
	        }
	    }

	    public static void main(String[] args) {
	        AttendanceTracker tracker = new AttendanceTracker();

	        try {
	            tracker.markAttendance("Session1", "StudentA");
	            tracker.markAttendance("Session1", "StudentB");
	            tracker.markAttendance("Session1", "StudentA"); 
	        } catch (DuplicateAttendanceException e) {
	            System.out.println(e.getMessage());
	        }

	        tracker.displayAttendance("Session1");

	        tracker.removeAttendance("Session1", "StudentB");
	        tracker.displayAttendance("Session1");
	    }
}
