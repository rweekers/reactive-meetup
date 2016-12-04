const Rx = require('rxjs/Rx');
const shape$ = require('../../util/example-streams.js').shape$;

// ASSIGNMENT: Use the reduce operator to find the shape that has the largest surface area. 
//
// HINT: Reduce does not emit intermediate results, it may therefore take some time before the result is available and
// printed to the console.

const largestShape$ = null; // ???


// If implemented correctly, only one line is printed to console:
//   Circle 40: 5026.548245743669

largestShape$.map((i) => i.getName() + ': ' + i.calculateArea())
    .subscribe((i) => console.log(i));