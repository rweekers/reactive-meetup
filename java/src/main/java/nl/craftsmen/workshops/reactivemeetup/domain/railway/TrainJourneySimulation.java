package nl.craftsmen.workshops.reactivemeetup.domain.railway;

import java.util.concurrent.TimeUnit;

import rx.Observable;

public class TrainJourneySimulation {
	
	private final TrainJourneySimulationParameters parameters;
	
	public TrainJourneySimulation(TrainJourneySimulationParameters simulationParameters) {
		this.parameters = simulationParameters;
	}
	
	public Observable<TrainMetrics> trainMetrics$() {
		
		LatLong startPosition = parameters.getStart().getLocation();
		LatLong destinationPosition = parameters.getDestination().getLocation();
		double totalDistance = startPosition.distanceTo(destinationPosition);
		
		double accelerationTime = parameters.getMaxVelocity() / parameters.getAcceleration();
		double accelerationDistance = 0.5 * parameters.getAcceleration() * accelerationTime * accelerationTime;
		
		// TODO adjust acceleration time and distance when unable to achieve max velocity for short distances.
		
		double unacceleratedDistance = totalDistance - 2 * accelerationDistance;
		double unacceleratedTime = unacceleratedDistance / parameters.getMaxVelocity();
		
		double totalTime = 2 * accelerationTime + unacceleratedTime;
		
		long startTime = System.currentTimeMillis();
		
		double tickDelay = 1000.0 / parameters.getTickFrequency();
		
		int requiredNumberOfFrames = (int) Math.ceil(totalTime * 1000 / (tickDelay * parameters.getTimeDilation()));
		
		return Observable.interval((long) Math.round(tickDelay), TimeUnit.MILLISECONDS)
			.take(requiredNumberOfFrames)
			.map((frameIndex) -> {
				
				double elapsedTime = (frameIndex * tickDelay) / 1000.0 * parameters.getTimeDilation();
				
				double distance;
				if (elapsedTime < accelerationTime) {
					distance = 0.5 * parameters.getAcceleration() * elapsedTime * elapsedTime;
				} else if (elapsedTime < unacceleratedTime + accelerationTime) {
					distance = accelerationDistance + parameters.getMaxVelocity() * (elapsedTime - accelerationTime);
				} else {
					double t = Math.min(accelerationTime, elapsedTime - accelerationTime - unacceleratedTime);
					distance = Math.min(totalDistance, accelerationDistance + unacceleratedDistance +
						parameters.getMaxVelocity() * t - 0.5 * parameters.getAcceleration() * t * t);
				}
				
				double normalizedDistance = distance / totalDistance;
				
				LatLong currentPosition = startPosition.interpolate(destinationPosition, normalizedDistance);
				
				return new TrainMetrics(parameters.getTrainId(), startTime + (long)(elapsedTime * 1000), currentPosition);
			});
	}
	
}
