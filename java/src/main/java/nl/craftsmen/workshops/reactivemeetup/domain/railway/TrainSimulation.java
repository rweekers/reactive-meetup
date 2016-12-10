package nl.craftsmen.workshops.reactivemeetup.domain.railway;

import rx.Observable;

public interface TrainSimulation {
	
	Observable<TrainMetrics> trainMetrics$(TrainSimulationParameters parameters, long startTime);

}
