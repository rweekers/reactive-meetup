package nl.craftsmen.workshops.reactivemeetup.domain.railway;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GateCheckEvent {
	
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss.SSS dd-MM-YYYY");
	
	private final boolean isCheckIn;
	
	private final Date timestamp;
	
	private final RailwayStation railwayStation;

	public GateCheckEvent(boolean isCheckIn, RailwayStation railwayStation) {
		this(isCheckIn, System.currentTimeMillis(), railwayStation);
	}
	
	public GateCheckEvent(boolean isCheckIn, long timestamp, RailwayStation railwayStation) {
		this.isCheckIn = isCheckIn;
		this.timestamp = new Date(timestamp);
		this.railwayStation = railwayStation;
	}
	
	public boolean isCheckIn() {
		return isCheckIn;
	}
	
	public boolean isCheckOut() {
		return !isCheckIn;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public RailwayStation getRailwayStation() {
		return railwayStation;
	}
	
	@Override
	public String toString() {
		return DATE_FORMAT.format(timestamp) + " - " + (isCheckIn ? "check-in" : "check-out") + " @ " + railwayStation;
	}
	
}
