var Rx = require('rxjs/Rx');
var sample = require('./utils').sample;
var TravelCostMatrix = require('../domain/railway/travel-cost-matrix');
var GateCheckEvent = require('../domain/railway/gate-check-event');
var TrainSimulationParameters = require('../domain/railway/train-simulation-parameters');
var StationaryTrainSimulation = require('../domain/railway/stationary-train-simulation');
var TrainJourneySimulation = require('../domain/railway/train-journey-simulation');
var CompositeTrainSimulation = require('../domain/railway/composite-train-simulation');
var RailwayStation = require('../domain/railway/railway-station');

let gce;

exports.gateCheckEvents$ = gateCheckEvent$();

exports.personalCheckinsCheckouts$ = sample(Rx.Observable.of(
    new GateCheckEvent(true, new Date(2016, 12, 16, 8, 4, 11, 345), 'UTR'),
    new GateCheckEvent(false,new Date(2016, 12, 16, 8, 41, 3, 409), 'AMS'),
	new GateCheckEvent(true,  new Date(2016, 12, 16, 17, 44, 56, 122), 'AMS'),
	new GateCheckEvent(false, new Date(2016, 12, 16, 18, 49, 4, 123), 'DH'),
	new GateCheckEvent(true,  new Date(2016, 12, 16, 22, 15, 44, 616), 'DH'),
	new GateCheckEvent(true,  new Date(2016, 12, 17, 8, 3, 54, 883), 'UTR'),
	new GateCheckEvent(false, new Date(2016, 12, 17, 8, 39, 21, 512), 'AMS')
));

exports.travelCostMatrix = getTravelCostMatrix();

exports.trainMetrics$ =  getTrainMetrics$();

function getTrainMetrics$() {
    simulationParameters = new TrainSimulationParameters(100, 140 / 3.6, 2 / 3.6, '1042', 40.0);

    simulation = new CompositeTrainSimulation(
        new StationaryTrainSimulation(RailwayStation.AMR.location, 60.0),
        new TrainJourneySimulation(RailwayStation.AMR.location, RailwayStation.UTR.location),
        new StationaryTrainSimulation(RailwayStation.UTR.location, 60.0)
    );
    
    return simulation.trainMetrics$(simulationParameters, Date.now());
}

exports.velocity$ = (trainMetrics$) => trainMetrics$
    .bufferCount(10, 5)
    .filter((measurement) => measurement.length > 1)
    .map(([first, ...rest]) => {
        const last = rest[rest.length-1];
        const elapsedTime = last.getTimestamp() - first.getTimestamp();
        const distance = last.getPosition().distanceTo(first.getPosition());
        const velocity = distance * 1000 / elapsedTime;
        return velocity;
    })
    .map((velocity) => velocity * 3.6);

function gateCheckEvent$() {
    if (gce == null) {
        gce = Rx.Observable.merge(
            singleGateCheckEvent$(true, 233), 
            singleGateCheckEvent$(true, 978),
            singleGateCheckEvent$(false,  1313),
		    singleGateCheckEvent$(true,   2105),
			singleGateCheckEvent$(false,  3643),
			singleGateCheckEvent$(false,  4411),
			singleGateCheckEvent$(true,   5556),
			singleGateCheckEvent$(false,  8123),
			singleGateCheckEvent$(false,  9722),
			singleGateCheckEvent$(true,  10880)
        );
    }
    return gce;
}

function singleGateCheckEvent$(isCheckin, delay) {
    return Rx.Observable.of(new GateCheckEvent(isCheckin, Date.now() + delay, 'AMR'))
        .delay(delay);
}

function getTravelCostMatrix() {
    const travelCosts = new TravelCostMatrix();
    travelCosts.addCostEntry('UTR', 'AMS', 7.50);
    travelCosts.addCostEntry('UTR', 'DH', 11.00);
    travelCosts.addCostEntry('DH', 'AMS', 11.50);
    return travelCosts;
}
