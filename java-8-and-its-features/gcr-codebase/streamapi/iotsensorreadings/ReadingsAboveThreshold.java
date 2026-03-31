package iotsensorreadings;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

class SensorReading {
    private String sensorId;
    private String type;
    private double value;
    private LocalDateTime timestamp;

    public SensorReading(String sensorId, String type, double value, LocalDateTime timestamp) {
        this.sensorId = sensorId;
        this.type = type;
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getSensorId() { return sensorId; }
    public String getType() { return type; }
    public double getValue() { return value; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format("Sensor[%s] (%s): %.2f at %s", 
                              sensorId, type, value, timestamp);
    }
}
public class ReadingsAboveThreshold {
public static void main(String[] args) {
	 List<SensorReading> readings = Arrays.asList(
	            new SensorReading("S1", "Temperature", 25.6, LocalDateTime.now().minusMinutes(5)),
	            new SensorReading("S2", "Humidity", 60.2, LocalDateTime.now().minusMinutes(3)),
	            new SensorReading("S3", "Pressure", 1012.5, LocalDateTime.now().minusMinutes(2)),
	            new SensorReading("S4", "Temperature", 27.1, LocalDateTime.now().minusMinutes(1)),
	            new SensorReading("S5", "Light", 350.0, LocalDateTime.now())
	        );

    readings.stream().filter(r ->r.getValue() > 100).forEach(System.out::println);;
}
}
