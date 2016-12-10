const Rx = require('rxjs/Rx');

// The shape$ stream emits a sequence of varying shapes.
const shape$ = require('../../util/example-streams.js').shape$;

// ASSIGNMENT: Display the circumference for all shapes whose surface area is larger than 500.
//
// HINT: You do not need to compute the circumference and surface area yourself. Instead you can use the following
// functions calculateCircumference() and calculateArea() provide by all shapes in the stream. 

const circumference$ = null; // ???

// If implemented correctly you should see the following values:
//   188.50, 251.33, 2000.00, 3600.00, 120.00, 145.21, 1400.00

circumference$.subscribe((i) => console.log(i.toFixed(2)));