package nl.craftsmen.workshops.reactivemeetup.domain.railway;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.subjects.PublishSubject;

public class CompositeTrainSimulation implements TrainSimulation {
	
	private final List<TrainSimulation> simulations;
	
	public CompositeTrainSimulation(TrainSimulation... simulations) {
		this.simulations = Arrays.asList(simulations);
	}

	@Override
	public Observable<TrainMetrics> trainMetrics$(TrainSimulationParameters parameters, long startTime) {
		
		PublishSubject<TrainMetrics> publisher = PublishSubject.create();
		
		SimulationConcatenator concatenator = new SimulationConcatenator(simulations.iterator(), publisher, parameters, startTime);
		
		concatenator.concatNextSimulation();
		
		return publisher.asObservable();
	}
	
	private static class SimulationConcatenator {

		private final Iterator<TrainSimulation> simulations;
		private final Observer<TrainMetrics> publisher;
		private final TrainSimulationParameters parameters;
		private long nextStartTime;
		
		public SimulationConcatenator(
			Iterator<TrainSimulation> simulations,
			Observer<TrainMetrics> publisher,
			TrainSimulationParameters parameters,
			long startTime
		) {
			this.simulations = simulations;
			this.publisher = publisher;
			this.parameters = parameters;
			this.nextStartTime = startTime;
		}
		
		public void concatNextSimulation() {
			if (!simulations.hasNext()) {
				publisher.onCompleted();
				return;
			}
			
			TrainSimulation simulation = simulations.next();
			
			simulation.trainMetrics$(parameters, nextStartTime).subscribe(
				(metrics) -> {
					nextStartTime = metrics.getTimestamp();
					publisher.onNext(metrics);
				},
				publisher::onError,
				this::concatNextSimulation
			);
		}
		
	}

}
