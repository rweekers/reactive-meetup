const Rx = require('rxjs/Rx');
const number$ = require('../../util/example-streams.js').number$;

// ASSIGNMENT: create a new stream that emits the square of each number emitted by the number$ stream.

const squaredNumber$ = number$
	.map((i) => i * i);

// If implemented correctly, the application will output the following numbers: 1, 81, 16, 49, 36, 4, 4, 49, 9, 16, 64

squaredNumber$.subscribe((x) => console.log(x));