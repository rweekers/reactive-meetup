package nl.craftsmen.workshops.reactivemeetup.domain.railway;

public class TrainJourneySimulationParameters {
	
	private final String trainId;
	
	private final RailwayStation start;
	
	private final RailwayStation destination;
	
	private final double maxSpeed;
	
	private final double acceleration;
	
	private final double timeDelation;
	
	private final int tickFrequency;
	
	public TrainJourneySimulationParameters(
		String trainId,
		RailwayStation start,
		RailwayStation destination,
		double maxSpeed,
		double acceleration,
		double timeDelation,
		int tickFrequency
	) {
		this.trainId = trainId;
		this.start = start;
		this.destination = destination;
		this.maxSpeed = maxSpeed;
		this.acceleration = acceleration;
		this.timeDelation = timeDelation;
		this.tickFrequency = tickFrequency;
	}
	
	public String getTrainId() {
		return trainId;
	}
	
	public RailwayStation getStart() {
		return start;
	}
	
	public RailwayStation getDestination() {
		return destination;
	}
	
	public double getMaxSpeed() {
		return maxSpeed;
	}
	
	public double getAcceleration() {
		return acceleration;
	}
	
	public double getTimeDelation() {
		return timeDelation;
	}
	
	public int getTickFrequency() {
		return tickFrequency;
	}
	
}
