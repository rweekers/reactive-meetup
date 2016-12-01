package nl.craftsmen.workshops.reactivemeetup.domain.railway;

public class TrainSimulationParameters {
	
	private String trainId;
	
	private double maxVelocity;
	
	private double acceleration;
	
	private double timeDilation;
	
	private int tickFrequency;

	public String getTrainId() {
		return trainId;
	}

	public TrainSimulationParameters setTrainId(String trainId) {
		this.trainId = trainId;
		return this;
	}

	public double getMaxVelocity() {
		return maxVelocity;
	}

	public TrainSimulationParameters setMaxVelocity(double maxVelocity) {
		this.maxVelocity = maxVelocity;
		return this;
	}

	public double getAcceleration() {
		return acceleration;
	}

	public TrainSimulationParameters setAcceleration(double acceleration) {
		this.acceleration = acceleration;
		return this;
	}

	public double getTimeDilation() {
		return timeDilation;
	}

	public TrainSimulationParameters setTimeDilation(double timeDilation) {
		this.timeDilation = timeDilation;
		return this;
	}

	public int getTickFrequency() {
		return tickFrequency;
	}

	public TrainSimulationParameters setTickFrequency(int tickFrequency) {
		this.tickFrequency = tickFrequency;
		return this;
	}
		
}
