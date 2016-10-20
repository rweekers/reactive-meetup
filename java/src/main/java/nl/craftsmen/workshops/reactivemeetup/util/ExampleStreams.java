package nl.craftsmen.workshops.reactivemeetup.util;

import java.util.concurrent.TimeUnit;

import rx.Observable;

public class ExampleStreams {

	public static Observable<Integer> number$() {
		return sample(Observable.from(new Integer[] { 1, 9, 4, 7, 6, 2, 2, 7, 3, 4, 8 }), 500);
	}
	
	private static <T> Observable<T> sample(Observable<T> stream, int interval) {
		return Observable.interval(interval, TimeUnit.MILLISECONDS).zipWith(stream, (Long a, T b) -> b);
	}
	
}
