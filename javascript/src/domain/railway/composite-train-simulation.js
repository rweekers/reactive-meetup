var Rx = require('rxjs/Rx');
var TrainMetrics = require('./train-metrics');
var LatLong = require('./lat-long');

module.exports = class CompositeTrainSimulation {

	constructor(...simulations) {
		this.simulations = simulations;
	}

	trainMetrics$(parameters, startTime) {
		
		const publisher = new Rx.Subject();

		this.concatNextSimulation(this.simulations, publisher, parameters, startTime);

		return publisher.asObservable();
	}

	concatNextSimulation(simulations, publisher, parameters, startTime) {
		if (!simulations.length) {
			publisher.complete();
			return;
		}
		
		const [simulation, ...rest] = simulations;
		let nextStartTime = startTime;
		
		simulation.trainMetrics$(parameters, nextStartTime).subscribe(
			(metrics) => {
				nextStartTime = metrics.getTimestamp();
				publisher.next(metrics);
			},
			publisher.error.bind(publisher),
			() => this.concatNextSimulation(rest, publisher, parameters, nextStartTime)
		);
	}
}
