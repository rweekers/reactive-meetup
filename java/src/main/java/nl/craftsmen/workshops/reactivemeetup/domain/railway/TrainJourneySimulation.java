package nl.craftsmen.workshops.reactivemeetup.domain.railway;

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
		
		private final TrainJourneySimulationParameters simulationParameters;
		
		private final Subject<TrainMetrics, TrainMetrics> publisher;
		
		public SimulationRunner(TrainJourneySimulationParameters simulationParameters, Subject<TrainMetrics, TrainMetrics> publisher) {
			this.simulationParameters = simulationParameters;
			this.publisher = publisher;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
		}
		
		
	}
	
}
