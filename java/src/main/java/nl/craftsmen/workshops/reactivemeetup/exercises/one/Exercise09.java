package nl.craftsmen.workshops.reactivemeetup.exercises.one;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.*;

import nl.craftsmen.workshops.reactivemeetup.domain.geometry.Shape;
import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import nl.craftsmen.workshops.reactivemeetup.util.Utils;
import rx.Observable;

public class Exercise09 {

	public static void main(String[] args) {
		// The shape$ stream emits a sequence of varying shapes.
		Observable<Shape> shape$ = ExampleStreams.shape$();
		
		// ASSIGNMENT: Display the circumference for all shapes whose surface area is larger than 500.
		//
		// HINT: You do not need to compute the circumference and surface area yourself. Instead you can use the following
		// functions calculateCircumference() and calculateArea() provide by the IShape interface. 
		
		Observable<Double> circumference$ = shape$
			.filter((i) -> i.calculateArea() > 500)
            .map(Shape::calculateCircumference);
		
		// If implemented correctly you should see the following values:
		//   188.50, 251.33, 2000.00, 3600.00, 120.00, 145.21, 1400.00
		
		circumference$.map(Utils.NUMBER_FORMAT::format)
			.subscribe(System.out::println);

		waitForStreamToComplete(circumference$);
	}
}
