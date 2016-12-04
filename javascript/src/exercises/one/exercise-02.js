const Rx = require('rxjs/Rx');
const numbersWithErrors$ = require('../../util/example-streams.js').numbersWithErrors$;

// ASSIGNMENT: Subscribe to the number$ stream and log each event (next, error, complete) to the console.

numbersWithErrors$.subscribe(
    (value) => console.log('next: ' + value),
    (error) => console.log('error: ' + error),
    ()      => console.log('completed')
);

// If you have completed the assignment successfully you should see the an output that is similar to:
//
// next: 1
// next: 9
// next: 4
// next: 7
// error: Error: uh oh! an error!
// 
// This also illustrates that if a stream emits an error event, it will no longer emit a complete event, so a stream
// either terminates with an error event or a complete event.