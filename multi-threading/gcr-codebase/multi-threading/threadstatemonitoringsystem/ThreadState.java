package threadstatemonitoringsystem;

import java.text.SimpleDateFormat;
import java.util.*;

class TaskRunner extends Thread {
    public TaskRunner(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);

            int sum = 0;
            for (int i = 0; i < 1000000; i++) {
                sum += i;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class StateMonitor extends Thread {
    private Thread[] threads;
    private Map<String, Set<Thread.State>> stateHistory = new HashMap<>();

    public StateMonitor(Thread[] threads) {
        this.threads = threads;
    }

    @Override
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        boolean allTerminated = false;
        while (!allTerminated) {
            allTerminated = true;
            for (Thread t : threads) {
                Thread.State state = t.getState();
                String timestamp = sdf.format(new Date());

                System.out.println("[Monitor] " + t.getName() + " is in " + state + " state at " + timestamp);

    
                stateHistory.putIfAbsent(t.getName(), new HashSet<>());
                stateHistory.get(t.getName()).add(state);

                if (state != Thread.State.TERMINATED) {
                    allTerminated = false;
                }
            }

            try {
                Thread.sleep(500); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nSummary of state changes:");
        for (String name : stateHistory.keySet()) {
            System.out.println(name + " went through " + stateHistory.get(name).size() + " states: " + stateHistory.get(name));
        }
    }
}

public class ThreadState {
    public static void main(String[] args) {
        TaskRunner task1 = new TaskRunner("Task-1");
        TaskRunner task2 = new TaskRunner("Task-2");

        
        StateMonitor monitor = new StateMonitor(new Thread[]{task1, task2});
        monitor.start();

       
        task1.start();
        task2.start();

        try {
            task1.join();
            task2.join();
            monitor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}