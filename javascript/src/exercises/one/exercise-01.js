var Rx = require('rxjs/Rx');
var streams = require('../../util/example-streams.js');

streams.number$.subscribe((x) => console.log(x));