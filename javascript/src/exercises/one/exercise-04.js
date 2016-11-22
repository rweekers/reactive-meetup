var Rx = require('rxjs/Rx');
var isPrime = require('../../util/utils').isPrime;
var number$ = require('../../util/example-streams.js').number$;

// ASSIGNMENT: Use to number$ to define a new stream that only contains those numbers that are prime.
//
// HINT: You can make use of the utility function utils.isPrime to check if a given number is a prime number.

number$
    .filter((i) => isPrime(i))
    .subscribe((x) => console.log(x));

// If implemented correctly, the application will output the following numbers: 1, 7, 2, 2, 7, 3