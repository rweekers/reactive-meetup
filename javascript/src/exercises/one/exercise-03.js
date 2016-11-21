var Rx = require('rxjs/Rx');
var streams = require('../../util/example-streams.js');

streams.number$
    .filter((i) => i % 2 == 0)
    .subscribe((x) => console.log(x));