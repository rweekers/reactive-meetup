package nl.craftsmen.workshops.reactivemeetup.exercises.one;

import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.*;

public class Exercise12 {

	public static void main(String[] args) {
		// The point$ stream represents a the number of earned points by a player/team for a number of games.
		Observable<Integer> point$ = ExampleStreams.point$();
		
		// ASSIGNMENT: Use the point$ stream to display the number of points for each game, including the total number of
		// points earned so far. The output should be formatted as following:
		//
		//   Points: 5 - total: 11
		//
		// HINT: Use the scan operator, followed by the zipWith operator.
		
		Observable<String> subtotals$ = unknown(); // ???
		
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
		
		subtotals$.subscribe(System.out::println);

		waitForStreamToComplete(subtotals$);
	}
}
