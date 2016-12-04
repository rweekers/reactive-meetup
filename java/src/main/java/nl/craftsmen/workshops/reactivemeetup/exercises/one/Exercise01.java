package nl.craftsmen.workshops.reactivemeetup.exercises.one;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.waitForStreamToComplete;

import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Exercise01 {

	public static void main(String[] args) {
		// The number$ stream below will emit short a sequence of numbers with an interval of 1 second.
		Observable<Integer> number$ = ExampleStreams.number$();
		
		// ASSIGNMENT: Subscribe to the number$ stream and print each number to the console.
		//
		// HINT: The following two expressions are equivalent:
		//   (value) -> System.out.print(value)
		//   System.out::println
		
		//number$.???
		
		// The standard RxJava scheduler uses daemon threads by default. To make sure the application does not terminate
		// immediately we have to block the main thread until the stream completes. For this reason we created a the
		// waitForStreamToComplete utility method.
		waitForStreamToComplete(number$);
	}

}
