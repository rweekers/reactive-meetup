var Rx = require('rxjs/Rx');
var streams = require('../../util/example-streams.js');
var Subtotal = require('../../domain/calculate/subtotal');

streams.shape$
    .scan((acc, shape) => acc.add(shape.calculateArea()), new Subtotal(0, 0))
    .map((subtotal) => subtotal.getSum() / subtotal.getCount())
    .subscribe((i) => console.log(i.toFixed(2)));