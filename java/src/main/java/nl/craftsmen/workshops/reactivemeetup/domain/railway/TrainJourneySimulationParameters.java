package nl.craftsmen.workshops.reactivemeetup.domain.railway;

public class TrainJourneySimulationParameters {
	
	private String trainId;
	
	private RailwayStation start;
	
	private RailwayStation destination;
	
	private double maxVelocity;
	
	private double acceleration;
	
	private double timeDilation;
	
	private int tickFrequency;

	public String getTrainId() {
		return trainId;
	}

	public TrainJourneySimulationParameters setTrainId(String trainId) {
		this.trainId = trainId;
		return this;
	}

	public RailwayStation getStart() {
		return start;
	}

	public TrainJourneySimulationParameters setStart(RailwayStation start) {
		this.start = start;
		return this;
	}

	public RailwayStation getDestination() {
		return destination;
	}

	public TrainJourneySimulationParameters setDestination(RailwayStation destination) {
		this.destination = destination;
		return this;
	}

	public double getMaxVelocity() {
		return maxVelocity;
	}

	public TrainJourneySimulationParameters setMaxVelocity(double maxVelocity) {
		this.maxVelocity = maxVelocity;
		return this;
	}

	public double getAcceleration() {
		return acceleration;
	}

	public TrainJourneySimulationParameters setAcceleration(double acceleration) {
		this.acceleration = acceleration;
		return this;
	}

	public double getTimeDilation() {
		return timeDilation;
	}

	public TrainJourneySimulationParameters setTimeDilation(double timeDilation) {
		this.timeDilation = timeDilation;
		return this;
	}

	public int getTickFrequency() {
		return tickFrequency;
	}

	public TrainJourneySimulationParameters setTickFrequency(int tickFrequency) {
		this.tickFrequency = tickFrequency;
		return this;
	}
		
}
