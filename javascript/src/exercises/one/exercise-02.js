var Rx = require('rxjs/Rx');
var streams = require('../../util/example-streams.js');

streams.numbersWithErrors$.subscribe(
    (value) => console.log("next: " + value),
    (error) => console.log("error: " + error),
    ()      => console.log("completed")
);