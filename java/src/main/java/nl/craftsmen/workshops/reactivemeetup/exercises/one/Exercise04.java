package nl.craftsmen.workshops.reactivemeetup.exercises.one;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.*;

import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Exercise04 {

	public static void main(String[] args) {
		Observable<Integer> number$ = ExampleStreams.number$();
		
		// ASSIGNMENT: Use to number$ to define a new stream that only contains those numbers that are prime.
		//
		// HINT: You can make use of the utility function Utils.isPrime to check if a given number is a prime number.
		
		Observable<Integer> primeNumber$ = unknown(); // ???
		
		// If implemented correctly, the application will output the following numbers: 1, 7, 2, 2, 7, 3
		
		primeNumber$.subscribe(System.out::println);

		waitForStreamToComplete(primeNumber$);
	}
}
