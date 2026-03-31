package smarttrafficfinecollectionsystem;

import java.time.LocalDate;

public class Violation {
    private static int counter = 3000;

    private final String        challanId;
    private final String        vehicleNumber;
    private final ViolationType type;
    private final LocalDate     date;
    private final String        location;
    private       double        baseFine;
    private       double        finalFine;
    private       PaymentStatus paymentStatus;

    public Violation(String vehicleNumber, ViolationType type,
                     LocalDate date, String location, double baseFine) {
        this.challanId     = "CH-" + (++counter);
        this.vehicleNumber = vehicleNumber;
        this.type          = type;
        this.date          = date;
        this.location      = location;
        this.baseFine      = baseFine;
        this.finalFine     = baseFine;
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public String        getChallanId()    { return challanId;    }
    public String        getVehicleNumber(){ return vehicleNumber;}
    public ViolationType getType()         { return type;         }
    public LocalDate     getDate()         { return date;         }
    public String        getLocation()     { return location;     }
    public double        getBaseFine()     { return baseFine;     }
    public double        getFinalFine()    { return finalFine;    }
    public PaymentStatus getPaymentStatus(){ return paymentStatus;}

    public void setFinalFine(double fine)            { this.finalFine = fine;     }
    public void setPaymentStatus(PaymentStatus status){ this.paymentStatus = status;}

    @Override
    public String toString() {
        return String.format("Violation[%s, vehicle=%s, type=%-18s date=%s, loc=%-15s baseFine=Rs.%-7.2f finalFine=Rs.%-7.2f status=%s]",
                challanId, vehicleNumber, type, date, location, baseFine, finalFine, paymentStatus);
    }
}