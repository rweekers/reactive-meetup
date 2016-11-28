package nl.craftsmen.workshops.reactivemeetup.domain.railway;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TrainMetrics {
	
	private static final DateFormat TIMESTAMP_DATE_FORMAT = new SimpleDateFormat("HH:mm:ss.SSS dd-MM-YYYY");

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
	
	@Override
	public String toString() {
		return "( trainId = " + trainId + ", time = " + TIMESTAMP_DATE_FORMAT.format(new Date(timestamp)) + ", position = " + position + " )";
	}
	
}
