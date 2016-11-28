package nl.craftsmen.workshops.reactivemeetup.domain.railway;

import java.util.concurrent.Callable;

import rx.Observable;
import rx.subjects.ReplaySubject;
import rx.subjects.Subject;

public class TrainJourneySimulation {
	
	private final TrainJourneySimulationParameters simulationParameters;
	
	public TrainJourneySimulation(TrainJourneySimulationParameters simulationParameters) {
		this.simulationParameters = simulationParameters;
	}
	
	public Observable<TrainMetrics> trainMetrics$() {
		
		Subject<TrainMetrics, TrainMetrics> publisher = ReplaySubject.create();
		
		SimulationRunner simulationRunner = new SimulationRunner(simulationParameters, publisher);
		
		Thread simulationThread = new Thread(simulationRunner);
		
		simulationThread.setDaemon(true);
		simulationThread.start();
		
		return publisher.asObservable();
	}
	
	private static class SimulationRunner implements Runnable {
		
		private final TrainJourneySimulationParameters parameters;
		
		private final Subject<TrainMetrics, TrainMetrics> publisher;
		
		public SimulationRunner(TrainJourneySimulationParameters simulationParameters, Subject<TrainMetrics, TrainMetrics> publisher) {
			this.parameters = simulationParameters;
			this.publisher = publisher;
		}

		@Override
		public void run() {
			
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
			
			try {
				
				loop(parameters.getTickFrequency(), () -> {
					
					double elapsedTime = (System.currentTimeMillis() - startTime) / 1000.0 * parameters.getTimeDilation();
					
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
					
					publisher.onNext(new TrainMetrics(parameters.getTrainId(), startTime + (long)(elapsedTime * 1000), currentPosition));
					
					return elapsedTime < totalTime;
				});
				
				publisher.onCompleted();
				
			} catch (Exception e) {
				
				publisher.onError(e);
				
			}
			
		}
		
		private static void loop(int frequency, Callable<Boolean> action) throws Exception {
			double tickDelay = 1000.0 / frequency;
			
			boolean shouldContinue = true;
			try {
				long startTime = System.currentTimeMillis() - (long)(tickDelay + 0.5);
				while (shouldContinue) {
					long elapsedTime = System.currentTimeMillis() - startTime;
					long sleepTime = Math.max(0, (long)(2 * tickDelay - elapsedTime + 0.5));
					
					startTime = System.currentTimeMillis();
					
					Thread.sleep(sleepTime);
					
					shouldContinue = action.call();
				}
			} catch (InterruptedException e) {
				// Interrupted. Silently quit loop.
			}
		}
	}
	
}
