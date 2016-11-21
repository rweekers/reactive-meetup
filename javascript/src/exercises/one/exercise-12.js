var Rx = require('rxjs/Rx');
var streams = require('../../util/example-streams.js');

streams.point$
    .scan((a, b) => a + b)
    .zip(streams.point$, (total, points) => "Points: " + points + " - total: " + total)
    .subscribe((i) => console.log(i));