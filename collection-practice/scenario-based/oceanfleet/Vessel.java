package oceanfleet;

public class Vessel {
	String vesselId;
	String vesselName;
	double averageSpeed;
	String vesselType;
	
	public Vessel() {
		this.vesselId = "";
		this.vesselName = "";
		this.averageSpeed = 0.0d;
		this.vesselType = "";
	}
	public Vessel(String vesselId, String vesselName, double averageSpeed, String vesselType) {
		this.vesselId = vesselId;
		this.vesselName = vesselName;
		this.averageSpeed = averageSpeed;
		this.vesselType = vesselType;
	}
	public String getVesselId() {
		return vesselId;
	}
	public String getVesselName() {
		return vesselName;
	}
	public double getAverageSpeed() {
		return averageSpeed;
	}
	public String getVesselType() {
		return vesselType;
	}
	@Override
	public String toString() {
		return "Vessel [vesselId=" + vesselId + ", vesselName=" + vesselName + ", averageSpeed=" + averageSpeed
				+ ", vesselType=" + vesselType + "]";
	}

}
