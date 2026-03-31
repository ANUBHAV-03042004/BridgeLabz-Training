package printshopjobscheduler;

public class PrintJob implements Runnable {
	  private String jobName;
	    private int pages;
	    private int priority;

	    public PrintJob(String jobName, int pages, int priority) {
	        this.jobName = jobName;
	        this.pages = pages;
	        this.priority = priority;
	    }
	    private String getPriorityLabel(int priority) {
	        if (priority >= 8) return "High Priority";
	        else if (priority >= 5) return "Medium Priority";
	        else return "Low Priority";
	    }

	@Override
	public void run() {
		 String priorityLabel = getPriorityLabel(priority);
	        System.out.println("[" + priorityLabel + "] " + Thread.currentThread().getName() +
	                           " started " + jobName);
	        for (int i = 1; i <= pages; i++) {
	            try {
	                Thread.sleep(100); 
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println("[" + priorityLabel + "] Printing " + jobName +
	                               " - Page " + i + " of " + pages);
	        }

	        long startTime = System.currentTimeMillis();
	        long endTime = System.currentTimeMillis();
	        System.out.println("[" + priorityLabel + "] " + jobName +
	                           " completed in " + (endTime - startTime) + "ms");
	    }

		
	}
