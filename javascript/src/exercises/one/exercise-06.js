const Rx = require('rxjs/Rx');
const number$ = require('../../util/example-streams.js').number$.take(5);

// ASSIGNMENT: Compute the sum of all numbers emitted by the number$ stream. The result should be stored a new stream
// that also contains the intermediate sums, for example given a stream of numbers 1, 2, 3, then the resulting stream
// should emit the numbers 3 (1 + 2) and 6 (3 + 3).

const sum$ = null; // ???

// When implemented correctly you should see the following numbers: 1, 10, 14, 21, 27

sum$.subscribe((x) => console.log(x));
