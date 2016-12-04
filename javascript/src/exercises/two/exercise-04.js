const Rx = require('rxjs/Rx');

const MotionType = require('../../domain/railway/motion-type');
const TrainMovementAction = require('../../domain/railway/train-movement-action');
const RailwayStation = require('../../domain/railway/railway-station');

// This is a bonus exercise that is bit more involved. Given are two streams. The first is trainMetrics$ stream, which is the same
// stream you have already used in exercise 2.3. The second stream is the velocity$ stream that is derived from the trainMetrics$
// stream (which is actually the solution of exercise 2.3). This exercise is split into three smaller exercises, which all need to
// be solved to accomplish the final goal: showing from which railway station the train departs and at which station it arrives.

const trainMetrics$ = require('../../util/railway-streams.js').trainMetrics$;

const velocity$ = require('../../util/railway-streams.js').velocity$(trainMetrics$);

// ASSIGNMENT: Using the velocity$ stream, define a new motion$ stream that describes the motion of the train using one of the 
// the following constants:
//  - MotionType.ACCELERATING    The train is accelerating (its velocity is increasing).
//  - MotionType.DECELERATING    The train is decelerating (its velocity is decreasing).
//  - MotionType.CONSTANT_SPEED  The train is moving at constant speed.
//  - MotionType.STATIONARY      The train is not moving at all.
//
// HINT: First try to get a stream that emits the acceleration for two subsequent emits of the velocity$ stream. 
//
// HINT: Use an epsilon value of 0.1 to determine if the train is accelerating or decelerating, i.e. the train is not accelerating /
// decelerating if the value of the acceleration is greater than -0.1 and less than 0.1.
//
// HINT: You cannot use the stream of acceleration values alone to differentiate between a train that is stationary and a train
// moving at constant speed, since the acceleration is 0 in both cases.
//
// HINT: Uncomment the "motion$.subscribe(console.log);" line below to test your stream.

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

//motion$.subscribe(console.log);

// ASSIGNMENT: With the motion$ stream, defined above, it is possible to detect whether a train is departing or arriving. Use the
// following constants and conditions to define a trainAction$ stream that indicates when a train is departing or arriving at a
// railway station:
//  - TrainMovementAction.DEPARTING  If the motion changes from STATIONARY to ACCELERATING.
//  - TrainMovementAction.ARRIVING   If the motion changes from DECELERATING to STATIONARY.
// For other motion transitions that do not match one the condition above the trainAction$ stream should not emit any value.
//
// HINT: We'll assume trains do not encounter any malfunctions or obstructions on the railway network and as such we can safely
// assume that whenever a train starts decelerating it will always stop at a railway station.
//
// HINT: Use undefined or null in case irrelevant motion transitions are detected and filter them out in a second step.
//
// HINT: Uncomment the "trainAction$.subscribe(console.log);" line below to test your stream.
const trainAction$ = motion$
    .bufferCount(2, 1)
    .filter((motions) => motions.length > 1)
    .map(([a, b]) => {

        if (a === MotionType.STATIONARY && b === MotionType.ACCELERATING) {
            return TrainMovementAction.DEPARTING;
        } else if (a === MotionType.DECELERATING && b === MotionType.STATIONARY) {
            return TrainMovementAction.ARRIVING;
        }

        return undefined;
    })
    .filter((result) => !!result);

//trainAction$.subscribe(console.log);

// ASSIGNMENT: Using the trainAction$ and trainMetrics$ streams, define a new message$ stream that emits a message whenever a
// train arrives or departs at a railway station including the name of that station. The output should be something like this:
//  - Departing from stationA
//  - Arriving at stationB
//
// HINT: For this exercise you have to use an operator that you (probably) have not used before in the other exercises.
//
// HINT: Use RailwayStation.closestTo(position) to find the railway station closest to the specified LatLong coordinate.
//
// HINT: Uncomment the "messages$.subscribe(console.log);" line below to test your stream.
const messages$ = trainAction$
    .withLatestFrom(trainMetrics$, (action, trainMetrics) => {
        const station = RailwayStation.closestTo(trainMetrics.getPosition());

        if (action === TrainMovementAction.DEPARTING) {
            return 'Departing from ' + station;
        } else {
            return 'Arriving at ' + station;
        }
    })

messages$.subscribe(console.log);