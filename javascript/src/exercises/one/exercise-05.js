var Rx = require('rxjs/Rx');
var number$ = require('../../util/example-streams.js').number$;

// ASSIGNMENT: create a new stream that emits the square of each number emitted by the number$ stream.

number$
    .map((i) => i * i)
    .subscribe((x) => console.log(x));

// If implemented correctly, the application will output the following numbers: 1, 81, 16, 49, 36, 4, 4, 49, 9, 16, 64