var Rx = require('rxjs/Rx');
var number$ = require('../../util/example-streams.js').number$;

// ASSIGNMENT: Subscribe to the number$ stream and print each number to the console.

number$.subscribe((x) => console.log(x));