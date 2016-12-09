package nl.craftsmen.workshops.reactivemeetup.exercises.one;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.*;

import nl.craftsmen.workshops.reactivemeetup.domain.geometry.IShape;
import nl.craftsmen.workshops.reactivemeetup.domain.calculate.Subtotal;
import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Exercise10 {

    public static void main(String[] args) {
        Observable<IShape> shape$ = ExampleStreams.shape$();

        // ASSIGNMENT: Compute the average surface area for all shapes that are emitted by the shape$ stream. Store the final
        // and intermediate results in the averageArea$ stream. Use both the scan and map operator for this assignment.
        //
        // HINT: Use the Subtotal class as a storage for the sum of the surface area and the number of shapes.
        //
        // HINT: If the first value that is printed is a weird character, then you probably encountered a division by zero
        // error. You can solve this with either the skip or the filter operator.
        
        Observable<Double> averageArea$ = shape$
            .scan(new Subtotal(0, 0), (acc, shape) -> acc.add(shape.calculateArea()))
            .skip(1)
            .map((subtotal) -> subtotal.getSum() / subtotal.getCount());
        
		// If implemented correctly you should see the following values:
		//   2827.43, 3926.99, 2751.33, 2313.50, 1890.80, 1875.66, 1736.28, 1631.75, 1528.22
        
        averageArea$
            .map(NUMBER_FORMAT::format)
            .subscribe(System.out::println);

        waitForStreamToComplete(averageArea$);
    }
}
