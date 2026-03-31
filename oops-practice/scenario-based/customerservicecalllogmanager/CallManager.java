package customerservicecalllogmanager;

import java.util.ArrayList;

class CallLogManager {
    ArrayList<CallLog> calllog = new ArrayList<>();

    public void addCall(CallLog call) {
        calllog.add(call);
    }

    public void searchByKeyword(String keyword) {
        for (CallLog call : calllog) {
            if (call.message.contains(keyword)) {
                System.out.println( "Call Details: " + call.phonenumber + ", " + call.message + ", " + call.currenttime);
            }
        }
    }

    public void filterByTime(String time) {
        for (CallLog call : calllog) {
            if (call.currenttime.contains(time)) {
                System.out.println( "Call Details: " + call.phonenumber + ", " + call.message + ", " + call.currenttime);
            }
        }
    }
}