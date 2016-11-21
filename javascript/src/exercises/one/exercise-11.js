var Rx = require('rxjs/Rx');
var streams = require('../../util/example-streams.js');

streams.shape$
    .reduce((a, b) => {
        if (a.calculateArea() > b.calculateArea()) {
            return a;
        }
        return b;
    })
    .map((i) => i.getName() + ': ' + i.calculateArea())
    .subscribe((i) => console.log(i));