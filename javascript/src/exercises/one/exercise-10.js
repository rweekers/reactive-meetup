const Rx = require('rxjs/Rx');
const shape$ = require('../../util/example-streams.js').shape$;
const Subtotal = require('../../domain/calculate/subtotal');

// ASSIGNMENT: Compute the average surface area for all shapes that are emitted by the shape$ stream. Store the final
// and intermediate results in the averageArea$ stream. Use both the scan and map operator for this assignment.
//
// HINT: Use the Subtotal class as a storage for the sum of the surface area and the number of shapes.

const averageArea$ = null; // ???

// If implemented correctly you should see the following values:
//   2827.43, 3926.99, 2751.33, 2313.50, 1890.80, 1875.66, 1736.28, 1631.75, 1528.22

averageArea$.subscribe((i) => console.log(i.toFixed(2)));