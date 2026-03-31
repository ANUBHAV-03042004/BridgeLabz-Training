package warehousecoldstoragetemperaturemonitor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TemperatureLog {
    private final String              roomId;
    private final LocalDate           date;
    private final List<Double>        readings;
    private final double              minSafeTemp;
    private final double              maxSafeTemp;

    public TemperatureLog(String roomId, LocalDate date, double minSafeTemp, double maxSafeTemp) {
        this.roomId      = roomId;
        this.date        = date;
        this.minSafeTemp = minSafeTemp;
        this.maxSafeTemp = maxSafeTemp;
        this.readings    = new ArrayList<>();
    }

    public void addReading(double temp) throws SensorFailureException {
        if (!Double.isFinite(temp)) {
            throw new SensorFailureException(roomId, "Sensor returned non-finite value: " + temp);
        }
        if (temp < -50.0 || temp > 60.0) {
            throw new SensorFailureException(roomId, "Reading " + temp + "°C is physically impossible.");
        }
        readings.add(temp);
    }

    public double getDailyAverage() {
        if (readings.isEmpty()) return 0.0;
        double sum = 0.0;
        for (double r : readings) sum += r;
        return sum / readings.size();
    }

    public double getMaxReading() {
        return readings.stream().mapToDouble(Double::doubleValue).max().orElse(0.0);
    }

    public double getMinReading() {
        return readings.stream().mapToDouble(Double::doubleValue).min().orElse(0.0);
    }

    public AlertType getAlertType() {
        double avg = getDailyAverage();
        if (avg > maxSafeTemp) return AlertType.HIGH_TEMP;
        if (avg < minSafeTemp) return AlertType.LOW_TEMP;
        return AlertType.NORMAL;
    }

    public boolean isSafe() {
        return getAlertType() == AlertType.NORMAL;
    }

    public String    getRoomId()      { return roomId;      }
    public LocalDate getDate()        { return date;        }
    public List<Double> getReadings() { return Collections.unmodifiableList(readings); }
    public double    getMinSafeTemp() { return minSafeTemp; }
    public double    getMaxSafeTemp() { return maxSafeTemp; }

    @Override
    public String toString() {
        return String.format("TemperatureLog[room=%s, date=%s, avg=%.2f°C, min=%.2f°C, max=%.2f°C, alert=%s]",
                roomId, date, getDailyAverage(), getMinReading(), getMaxReading(), getAlertType());
    }
}
