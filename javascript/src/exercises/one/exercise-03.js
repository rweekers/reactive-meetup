var Rx = require('rxjs/Rx');
var number$ = require('../../util/example-streams.js').number$;

// ASSIGNMENT: Create a new stream based on the number$ stream that only emits even numbers.

number$
    .filter((i) => i % 2 == 0)
    .subscribe((x) => console.log(x));

// If implemented correctly, the application will output the following numbers: 4, 6, 2, 2, 4, 8