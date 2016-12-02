var Rx = require('rxjs/Rx');
var TrainMetrics = require('./train-metrics');
var LatLong = require('./lat-long');

module.exports = class TrainJourneySimulation {

	constructor(startPosition, destinationPosition) {
		this.startPosition = startPosition;
		this.destinationPosition = destinationPosition;
	}

	trainMetics$(parameters, startTime) {
				
		totalDistance = startPosition.distanceTo(destinationPosition);
		
		accelerationTime = parameters.getMaxVelocity() / parameters.getAcceleration();
		accelerationDistance = 0.5 * parameters.getAcceleration() * accelerationTime * accelerationTime;
		
		// TODO adjust acceleration time and distance when unable to achieve max velocity for short distances.
		
		unacceleratedDistance = totalDistance - 2 * accelerationDistance;
		unacceleratedTime = unacceleratedDistance / parameters.getMaxVelocity();
		
		totalTime = 2 * accelerationTime + unacceleratedTime;
		
		tickDelay = 1000.0 / parameters.getTickFrequency();
		
		requiredNumberOfFrames = Math.ceil(totalTime * 1000 / (tickDelay * parameters.getTimeDilation())) + 1;
		
		return Observable.interval(Math.round(tickDelay))
			.take(requiredNumberOfFrames)
			.map((frameIndex) => {
				
				elapsedTime = (frameIndex * tickDelay) / 1000.0 * parameters.getTimeDilation();
				
				distance;
				if (elapsedTime < accelerationTime) {
					distance = 0.5 * parameters.getAcceleration() * elapsedTime * elapsedTime;
				} else if (elapsedTime < unacceleratedTime + accelerationTime) {
					distance = accelerationDistance + parameters.getMaxVelocity() * (elapsedTime - accelerationTime);
				} else {
					t = Math.min(accelerationTime, elapsedTime - accelerationTime - unacceleratedTime);
					distance = Math.min(totalDistance, accelerationDistance + unacceleratedDistance +
						parameters.getMaxVelocity() * t - 0.5 * parameters.getAcceleration() * t * t);
				}
				
				normalizedDistance = distance / totalDistance;
				
				currentPosition = startPosition.interpolate(destinationPosition, normalizedDistance);
				
				return new TrainMetrics(parameters.getTrainId(), startTime + (long)(elapsedTime * 1000), currentPosition);
			});
	}
}
