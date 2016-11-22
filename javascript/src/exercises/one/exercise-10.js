var Rx = require('rxjs/Rx');
var shape$ = require('../../util/example-streams.js').shape$;
var Subtotal = require('../../domain/calculate/subtotal');

// ASSIGNMENT: Compute the average surface area for all shapes that are emitted by the shape$ stream. Store the final
// and intermediate results in the averageArea$ stream. Use both the scan and map operator for this assignment.
//
// HINT: Use the Subtotal class as a storage for the sum of the surface area and the number of shapes.
//
// HINT: If the first value that is printed is a weird character, then you probably encountered a division by zero
// error. You can solve this with either the skip or the filter operator.

shape$
    .scan((acc, shape) => acc.add(shape.calculateArea()), new Subtotal(0, 0))
    .map((subtotal) => subtotal.getSum() / subtotal.getCount())
    .subscribe((i) => console.log(i.toFixed(2)));

// If implemented correctly you should see the following values:
//
//   2827.43, 3926.99, 2751.33, 2313.50, 1890.80, 1875.66, 1736.28, 1631.75, 1528.22