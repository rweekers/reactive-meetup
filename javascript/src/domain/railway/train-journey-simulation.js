const Rx = require('rxjs/Rx');
const TrainMetrics = require('./train-metrics');
const LatLong = require('./lat-long');

module.exports = class TrainJourneySimulation {

	constructor(startPosition, destinationPosition) {
		this.startPosition = startPosition;
		this.destinationPosition = destinationPosition;
	}

	trainMetrics$(parameters, startTime) {
				
		const totalDistance = this.startPosition.distanceTo(this.destinationPosition);
		
		const accelerationTime = parameters.getMaxVelocity() / parameters.getAcceleration();
		const accelerationDistance = 0.5 * parameters.getAcceleration() * accelerationTime * accelerationTime;
		
		// TODO adjust acceleration time and distance when unable to achieve max velocity for short distances.
		
		const unacceleratedDistance = totalDistance - 2 * accelerationDistance;
		const unacceleratedTime = unacceleratedDistance / parameters.getMaxVelocity();
		
		const totalTime = 2 * accelerationTime + unacceleratedTime;
		
		const tickDelay = 1000.0 / parameters.getTickFrequency();
		
		const requiredNumberOfFrames = Math.ceil(totalTime * 1000 / (tickDelay * parameters.getTimeDilation()));
		
		return Rx.Observable.interval(Math.round(tickDelay))
			.take(requiredNumberOfFrames)
			.map((frameIndex) => {
				
				const elapsedTime = ((frameIndex + 1) * tickDelay) / 1000.0 * parameters.getTimeDilation();
				
				let distance;
				if (elapsedTime < accelerationTime) {
					distance = 0.5 * parameters.getAcceleration() * elapsedTime * elapsedTime;
				} else if (elapsedTime < unacceleratedTime + accelerationTime) {
					distance = accelerationDistance + parameters.getMaxVelocity() * (elapsedTime - accelerationTime);
				} else {
					const t = Math.min(accelerationTime, elapsedTime - accelerationTime - unacceleratedTime);
					distance = Math.min(totalDistance, accelerationDistance + unacceleratedDistance +
						parameters.getMaxVelocity() * t - 0.5 * parameters.getAcceleration() * t * t);
				}
				
				const normalizedDistance = distance / totalDistance;
				
				const currentPosition = this.startPosition.interpolate(this.destinationPosition, normalizedDistance);
				
				return new TrainMetrics(parameters.getTrainId(), startTime + Math.floor(elapsedTime * 1000), currentPosition);
			});
	}
}
