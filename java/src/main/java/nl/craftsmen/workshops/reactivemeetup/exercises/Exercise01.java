package nl.craftsmen.workshops.reactivemeetup.exercises;

import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Exercise01 {

	public static void main(String[] args) throws InterruptedException {
		Observable<Integer> number$ = ExampleStreams.number$();
		
		number$.subscribe(System.out::println);
		
		Thread.sleep(6000);
	}

}