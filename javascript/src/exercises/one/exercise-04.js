var Rx = require('rxjs/Rx');
var isPrime = require('../../util/utils').isPrime;
var streams = require('../../util/example-streams.js');

streams.number$
    .filter((i) => isPrime(i))
    .subscribe((x) => console.log(x));