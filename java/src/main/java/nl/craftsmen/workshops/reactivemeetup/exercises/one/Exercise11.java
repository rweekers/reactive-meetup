package nl.craftsmen.workshops.reactivemeetup.exercises.one;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.*;

import nl.craftsmen.workshops.reactivemeetup.domain.geometry.Shape;
import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Exercise11 {

	public static void main(String[] args) {
		Observable<Shape> shape$ = ExampleStreams.shape$();
		
		// ASSIGNMENT: Use the reduce operator to find the shape that has the largest surface area. Store the result in the
		// largestShape$ stream.
		//
		// HINT: Reduce does not emit intermediate results, it may therefore take some time before the result is available and
		// printed to the console.
		
		Observable<Shape> largestShape$ = unknown(); // ???
		
		// If implemented correctly, only one line is printed to console:
		//   Circle 40: 5026.548245743669
		
		largestShape$.map((i) -> i.getName() + ": " + i.calculateArea())
			.subscribe(System.out::println);

		waitForStreamToComplete(largestShape$);
	}
}
