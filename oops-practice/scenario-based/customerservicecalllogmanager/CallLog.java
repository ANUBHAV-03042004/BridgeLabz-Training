package customerservicecalllogmanager;

import java.util.ArrayList;

public class CallLog {
    long phonenumber;
    String message;
    String currenttime;

    public CallLog(long phonenumber, String message, String currenttime) {
        this.phonenumber = phonenumber;
        this.message = message;
        this.currenttime = currenttime;
    }

}
