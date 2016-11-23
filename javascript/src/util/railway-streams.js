var Rx = require('rxjs/Rx');
var sample = require('./utils').sample;
var GateCheckEvent = require('../domain/railway/gate-check-event');

let gte;

exports.gateCheckEvents$ = gateCheckEvent$();

// TODO implement in JS
/*
public static Observable<GateCheckEvent> gateCheckEvent$() {
    
    if (gateCheckEvent$ == null) {
        gateCheckEvent$ = Observable.merge(Arrays.asList(
            singleGateCheckEvent$(true,    233),
            singleGateCheckEvent$(true,    978),
            singleGateCheckEvent$(false,  1313),
            singleGateCheckEvent$(true,   2105),
            singleGateCheckEvent$(false,  3643),
            singleGateCheckEvent$(false,  4411),
            singleGateCheckEvent$(true,   5556),
            singleGateCheckEvent$(false,  8123),
            singleGateCheckEvent$(false,  9722),
            singleGateCheckEvent$(true,  10880)
        ));
    }
    
    return gateCheckEvent$;
}

private static Observable<GateCheckEvent> singleGateCheckEvent$(boolean isCheckin, long delay) {
    return Observable.from(Arrays.asList(new GateCheckEvent(isCheckin, System.currentTimeMillis() + delay, RailwayStation.AMR)))
        .delay(delay, TimeUnit.MILLISECONDS);
}*/

function gateCheckEvent$() {
    if (gte == null) {
        gte = sample(Rx.Observable.of(1, 2, 3));
    }
    return gte;
}

function singleGateCheckEvent$(isCheckin, delay) {
    return Rx.Observable.of(new GateCheckEvent(isCheckin, System.now() + delay, 'AMR'));
}