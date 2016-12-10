const Rx = require('rxjs/Rx');

// The point$ stream represents a the number of earned points by a player/team for a number of games.
const point$ = require('../../util/example-streams.js').point$;

// ASSIGNMENT: Use the point$ stream to display the number of points for each game, including the total number of
// points earned so far. The output should be formatted as following:
//
//   Points: 5 - total: 11
//
// HINT: Use the scan operator, followed by the zip operator.

const subtotals$ = null; // ???

// If implemented correctly, the application should display the following output:
//
// Points: 0 - total: 0
// Points: 3 - total: 3
// Points: 0 - total: 3
// Points: 3 - total: 6
// Points: 1 - total: 7
// Points: 3 - total: 10
// Points: 0 - total: 10
// Points: 0 - total: 10
// Points: 3 - total: 13
// Points: 0 - total: 13

subtotals$.subscribe((i) => console.log(i));