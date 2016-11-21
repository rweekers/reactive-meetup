package nl.craftsmen.workshops.reactivemeetup.domain.railway;

public class TrainMetrics {

	private final String trainId;
	
	private final long timestamp;
	
	private final LatLong position;
	
	public TrainMetrics(String trainId, long timestamp, LatLong position) {
		this.trainId = trainId;
		this.timestamp = timestamp;
		this.position = position;
	}
	
	public String getTrainId() {
		return trainId;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public LatLong getPosition() {
		return position;
	}
	
}
