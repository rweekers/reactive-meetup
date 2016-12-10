const Rx = require('rxjs/Rx');
const number$ = require('../../util/example-streams.js').number$;

// ASSIGNMENT: Create a new stream based on the number$ stream that only emits even numbers.

const evenNumber$ = number$
	.filter((i) => i % 2 == 0);

// If implemented correctly, the application will output the following numbers: 4, 6, 2, 2, 4, 8

evenNumber$.subscribe((x) => console.log(x));