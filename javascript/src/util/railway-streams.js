var Rx = require('rxjs/Rx');
var sample = require('./utils').sample;
var GateCheckEvent = require('../domain/railway/gate-check-event');

let gte;

exports.gateCheckEvents$ = gateCheckEvent$();

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