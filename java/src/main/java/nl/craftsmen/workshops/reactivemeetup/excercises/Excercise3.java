package nl.craftsmen.workshops.reactivemeetup.excercises;

import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Excercise3 {

	public static void main(String[] args) throws InterruptedException {
		Observable<Integer> number$ = ExampleStreams.number$();
		number$.filter((i) -> i % 2 == 0).subscribe(System.out::println);
		
		Thread.sleep(6000);
	}
}
