const Rx = require('rxjs/Rx');

const EMIT_DELAY = 250; // milliseconds

// ASSIGNMENT: Create a number$ observable stream that emits a number every 0.25 seconds. The numbers that are to be emitted by this
// stream should start with 1 and each subsequent number should be twice as much as the number that was previously emitted.
//
// HINT: To specify the 0.25 seconds, use the EMIT_DELAY constant.
//
// HINT: The sequence of numbers that should be emitted by the stream is equivalent to the following sequence:
// 2^0, 2^1, 2^2, 2^3, 2^4, ...
//
// HINT: Use the Math.pow function to generate the correct output. With this function the equivalent of the sequence above is written as:
// Math.pow(2, 0), Math.pow(2, 1), Math.pow(2, 2), Math.pow(2, 3), Math.pow(2, 4), ...

const number$ = Rx.Observable.interval(EMIT_DELAY)
	.map((count) => Math.pow(2, count));

// When implemented correctly you should see the following output:
// 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024

number$.take(11).subscribe(console.log);