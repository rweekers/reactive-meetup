var Rx = require('rxjs/Rx');
var sample = require('./utils').sample;
var GateCheckEvent = require('../domain/railway/gate-check-event');

let gte;

exports.gateCheckEvents$ = gateCheckEvent$();

exports.personalCheckinsCheckouts$ = sample(Rx.Observable.of(
    new GateCheckEvent(true, new Date(2016, 12, 16, 8, 4, 11, 345), 'UTR'), //08:04:11.345 16-12-2016"
    new GateCheckEvent(false,new Date(2016, 12, 16, 8, 41, 3, 409), 'AMS'),
	new GateCheckEvent(true,  new Date(2016, 12, 16, 17, 44, 56, 122), ''),
	new GateCheckEvent(false, new Date(2016, 12, 16, 18, 49, 4, 123), 'DH'),
	new GateCheckEvent(true,  new Date(2016, 12, 16, 22, 15, 44, 616), 'DH'),
	new GateCheckEvent(true,  new Date(2016, 12, 17, 8, 3, 54, 883), 'UTR'),
	new GateCheckEvent(false, new Date(2016, 12, 17, 8, 39, 21, 512), 'AMS')
));

function gateCheckEvent$() {
    if (gte == null) {
        gte = Rx.Observable.merge(
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
    return gte;
}

function singleGateCheckEvent$(isCheckin, delay) {
    return Rx.Observable.of(new GateCheckEvent(isCheckin, Date.now() + delay, 'AMR'))
        .delay(delay);
}
