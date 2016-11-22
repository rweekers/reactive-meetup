var Rx = require('rxjs/Rx');
var number$ = require('../../util/example-streams.js').number$;

// ASSIGNMENT: Compute the sum of all numbers emitted by the number$ stream. The result should be stored a new stream
// that also contains the intermediate sums, for example given a stream of numbers 1, 2, 3, then the resulting stream
// should emit the numbers 3 (1 + 2) and 6 (3 + 3).

number$
    .scan((sum, item) => sum + item)
    .subscribe((x) => console.log(x));

// When implemented correctly you should see the following numbers: 1, 10, 14, 21, 27

