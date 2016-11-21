var Rx = require('rxjs/Rx');
var streams = require('../../util/example-streams.js');

streams.number$
    .map((i) => i * i)
    .subscribe((x) => console.log(x));