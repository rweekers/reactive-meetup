package nl.craftsmen.workshops.reactivemeetup.domain.railway;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GateCheckEvent {
	
	private final DateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss.SSS dd-MM-YYYY");
	
	private final boolean isCheckIn;
	
	private final Date timestamp;

	public GateCheckEvent(boolean isCheckIn) {
		this(isCheckIn, System.currentTimeMillis());
	}
	
	public GateCheckEvent(boolean isCheckIn, long timestamp) {
		this.isCheckIn = isCheckIn;
		this.timestamp = new Date(timestamp);
	}
	
	public boolean isCheckIn() {
		return isCheckIn;
	}
	
	public boolean isCheckOut() {
		return isCheckIn;
	}
	
	@Override
	public String toString() {
		return DATE_FORMAT.format(timestamp) + " - " + (isCheckIn ? "check-in" : "check-out");
	}
	
}
