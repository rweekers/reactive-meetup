package nl.craftsmen.workshops.reactivemeetup.exercises.two;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.*;

import java.util.concurrent.TimeUnit;

import rx.Observable;

public class Exercise01 {
	
	private static final long EMIT_DELAY = 250;

	public static void main(String[] args) {
		
		// ASSIGNMENT: Create a number$ observable stream that emits a number every 0.25 seconds. The numbers that are to be emitted by this
		// stream should start with 1 and each subsequent number should be twice as much as the number that was previously emitted.
		//
		// HINT: To specify the 0.25 seconds, use the EMIT_DELAY constant in combination with TimeUnit.MILLISECONDS.
		//
		// HINT: The sequence of numbers that should be emitted by the stream is equivalent to the following sequence:
		// 2^0, 2^1, 2^2, 2^3, 2^4, ...
		//
		// HINT: Use the pow function below as variation of the Math.pow function, which operates on long values instead of double values.
		// With this function the equivalent of the sequence above is written as:
		// pow(2, 0), pow(2, 1), pow(2, 2), pow(2, 3), pow(2, 4), ...
		
		Observable<Long> number$ = unknown(); // ???
		
		// When implemented correctly you should see the following output:
		// 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024
		
		Observable<Long> limitedNumber$ = number$.take(11);
		
		limitedNumber$.subscribe(System.out::println);
		
		waitForStreamToComplete(limitedNumber$);
	}
	
	private static long pow(long base, long exponent) {
		return (long) Math.pow(base, exponent);
	}
	
}
