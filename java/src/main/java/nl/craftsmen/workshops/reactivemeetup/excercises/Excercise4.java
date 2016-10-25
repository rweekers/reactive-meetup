package nl.craftsmen.workshops.reactivemeetup.excercises;

import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import nl.craftsmen.workshops.reactivemeetup.util.Utils;
import rx.Observable;

public class Excercise4 {

	public static void main(String[] args) throws InterruptedException {
		Observable<Integer> number$ = ExampleStreams.number$();
		number$.filter((i) -> Utils.isPrime(i)).subscribe(System.out::println);

		Thread.sleep(6000);
	}
}
