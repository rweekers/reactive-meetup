var Rx = require('rxjs/Rx');
var TrainMetrics = require('./train-metrics');

module.exports = class StationaryTrainSimulation {

	constructor(position, stationaryTime) {
		this.position = position;
		this.stationaryTime = stationaryTime;
	}

	trainMetics$() {
				
		tickDelay = 1000.0 / parameters.getTickFrequency();
		
		requiredNumberOfFrames = Math.ceil(this.stationaryTime * 1000 / (tickDelay * parameters.getTimeDilation())) + 1;
		
		return Rx.Observable.interval(Math.round(tickDelay))
			.take(requiredNumberOfFrames)
			.map((frameIndex) => {
				elapsedTime = (frameIndex * tickDelay) / 1000.0 * parameters.getTimeDilation();
				
				return new TrainMetrics(parameters.getTrainId(), startTime + (long)(elapsedTime * 1000), position);
			});
	}
}	
