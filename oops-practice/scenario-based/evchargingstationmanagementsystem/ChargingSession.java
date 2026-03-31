package evchargingstationmanagementsystem;

class ChargingSession {
    private static int counter = 200;

    private final String          sessionId;
    private final Vehicle         vehicle;
    private final int             slotId;
    private final double          unitsConsumed;
    private final PricingStrategy pricing;
    private final double          bill;

    public ChargingSession(Vehicle vehicle, int slotId,
                            double unitsConsumed, PricingStrategy pricing) {
        this.sessionId     = "SES-" + (++counter);
        this.vehicle       = vehicle;
        this.slotId        = slotId;
        this.unitsConsumed = unitsConsumed;
        this.pricing       = pricing;
        this.bill          = pricing.calculateBill(unitsConsumed);
    }

    public String getSessionId()     { return sessionId;     }
    public Vehicle getVehicle()      { return vehicle;       }
    public int    getSlotId()        { return slotId;        }
    public double getUnitsConsumed() { return unitsConsumed; }
    public double getBill()          { return bill;          }
    public PricingStrategy getPricing() { return pricing;   }

    @Override
    public String toString() {
        return String.format("Session[%s, %s, slot=%d, units=%.2f kWh, pricing=%s, bill=Rs.%.2f]",
                sessionId, vehicle.getOwnerName(), slotId,
                unitsConsumed, pricing.getPricingName(), bill);
    }
}
