const Rx = require('rxjs/Rx');
const shape$ = require('../../util/example-streams.js').shape$;

// ASSIGNMENT: Use the reduce operator to find the shape that has the largest surface area.
//
// NOTE: RxJS has a max operator (not discussed in the presentation), which you normally would use for scenario's like this assignment.
// However, for this exercise we want you use the reduce operator to become more familiar with that operator.
//
// HINT: Reduce does not emit intermediate results, it may therefore take some time before the result is available and printed to the
// console.

const largestShape$ =shape$
	.reduce((a, b) => {
		if (a.calculateArea() > b.calculateArea()) {
			return a;
		}
		return b;
	});


// If implemented correctly, only one line is printed to console:
//   Circle 40: 5026.548245743669

largestShape$.map((i) => i.getName() + ': ' + i.calculateArea())
	.subscribe((i) => console.log(i));