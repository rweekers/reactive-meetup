var Rx = require('rxjs/Rx');

const MotionType = require('../../domain/railway/motion-type');
const TrainMovementAction = require('../../domain/railway/train-movement-action');
const RailwayStation = require('../../domain/railway/railway-station');
const velocity$ = require('../../util/railway-streams.js').velocity$;
const trainMetrics$ = require('../../util/railway-streams.js').trainMetrics$;

const motion$ = velocity$
    .bufferCount(2, 1)
    .filter((velocities) => velocities.length > 1)
    .map((velocities) => velocities[1] - velocities[0])
    .zip(velocity$.skip(1), (acceleration, velocity) => {
        if (Math.abs(acceleration) < 0.1) {
            return velocity < 0.1 ? MotionType.STATIONARY : MotionType.CONSTANT_SPEED;
        }
        return acceleration < 0 ? MotionType.DECELERATING : MotionType.ACCELERATING;
    })
    .distinctUntilChanged();

const trainAction$ = motion$
    .bufferCount(2, 1)
    .filter((motions) => motions.length > 1)
    .map((motions) => {
        const a = motions[0];
        const b = motions[1];

        if (a === MotionType.STATIONARY && b === MotionType.ACCELERATING) {
            return TrainMovementAction.DEPARTING;
        } else if (a === MotionType.DECELERATING && b === MotionType.STATIONARY) {
            return TrainMovementAction.ARRIVING;
        } else {
            return TrainMovementAction.UNKNOWN;
        }
    })
    .filter((result) => result !== TrainMovementAction.UNKNOWN);

const messages$ = trainAction$
    .withLatestFrom(trainMetrics$, (action, trainMetrics) => {
        const station = RailwayStation.closestTo(trainMetrics.getPosition());

        if (action === TrainMovementAction.DEPARTING) {
            return "Departing from " + station;
        } else {
            return "Arriving at " + station;
        }
    })

messages$.subscribe(console.log);