package nl.craftsmen.workshops.reactivemeetup.domain.railway;

import java.util.concurrent.TimeUnit;

import rx.Observable;

public class StationaryTrainSimulation implements TrainSimulation {
	
	private final LatLong position;
	
	private final double stationaryTime;
	
	public StationaryTrainSimulation(LatLong position, double stationaryTime) {
		this.position = position;
		this.stationaryTime = stationaryTime;
	}

	@Override
	public Observable<TrainMetrics> trainMetrics$(TrainSimulationParameters parameters, long startTime) {
		double tickDelay = 1000.0 / parameters.getTickFrequency();
		
		int requiredNumberOfFrames = (int) Math.ceil(stationaryTime * 1000 / (tickDelay * parameters.getTimeDilation()));
		
		return Observable.interval((long) Math.round(tickDelay), TimeUnit.MILLISECONDS)
			.take(requiredNumberOfFrames)
			.map((frameIndex) -> {
				double elapsedTime = ((frameIndex + 1) * tickDelay) / 1000.0 * parameters.getTimeDilation();
				
				return new TrainMetrics(parameters.getTrainId(), startTime + (long)(elapsedTime * 1000), position);
			});
	}

}
