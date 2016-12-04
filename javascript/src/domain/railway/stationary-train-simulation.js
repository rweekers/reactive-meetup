const Rx = require('rxjs/Rx');
const TrainMetrics = require('./train-metrics');

module.exports = class StationaryTrainSimulation {

	constructor(position, stationaryTime) {
		this.position = position;
		this.stationaryTime = stationaryTime;
	}

	trainMetrics$(parameters, startTime) {
				
		const tickDelay = 1000.0 / parameters.getTickFrequency();
		
		const requiredNumberOfFrames = Math.ceil(this.stationaryTime * 1000 / (tickDelay * parameters.getTimeDilation()));
		
		return Rx.Observable.interval(Math.round(tickDelay))
			.take(requiredNumberOfFrames)
			.map((frameIndex) => {
				const elapsedTime = ((frameIndex + 1) * tickDelay) / 1000.0 * parameters.getTimeDilation();
				
				return new TrainMetrics(parameters.getTrainId(), startTime + Math.floor(elapsedTime * 1000), this.position);
			});
	}
}	
