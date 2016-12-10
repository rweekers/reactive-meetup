package nl.craftsmen.workshops.reactivemeetup.exercises.two;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.*;

import rx.subjects.*;

public class Exercise002 {

	public static void main(String[] args) {
		
		// ASSIGNMENT: Create a Subject and make sure that the program prints the following output:
		//  - after 1 second: RxJava is cool :)
		//  - after 2 seconds: So reactive!
		//  - after 3 seconds: Much stream!
		//  - after 4 seconds: Goodbye!
		// You are only allowed to modify the lines with the ??? comment. Do not change the order the statements. Also make sure that the
		// program terminates and does not wait indefinitely for the stream to complete.
		//
		// HINT: Think of which kind of Subject you need to produce the desired output.
		
		Subject<String, String> subject = ReplaySubject.create();
		
		runDelayedAsync(1000, () -> {
			subject.onNext("RxJava is cool :)");
		});
		
		runDelayedAsync(2000, () -> {
			subject.onNext("So reactive!");
		});
		
		runDelayedAsync(3000, () -> {
			subject.onNext("Much stream!");
		});
		
		runDelayedAsync(4000, () -> {
			subject.onCompleted();
		});
		
		subject.subscribe(System.out::println, (error) -> {}, () -> System.out.println("Goodbye!"));		
		
		waitForStreamToComplete(subject);
	}

}
