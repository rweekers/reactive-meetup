package nl.craftsmen.workshops.reactivemeetup.util;

import rx.Observable;

import java.text.NumberFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Utils {
	
	public static final NumberFormat NUMBER_FORMAT;
	
	static {
		NUMBER_FORMAT = NumberFormat.getNumberInstance();
		NUMBER_FORMAT.setMaximumFractionDigits(2);
		NUMBER_FORMAT.setMinimumFractionDigits(2);
	}

	static <T> Observable<T> sample(Observable<T> stream, int interval) {
		return Observable.interval(interval, TimeUnit.MILLISECONDS).zipWith(stream, (Long a, T b) -> b);
	}

	public static ZonedDateTime getZonedDateTime(int hour, int minute) {
		return ZonedDateTime.of(2016, 12, 16, hour, minute, 0, 0, ZoneId.of("Europe/Amsterdam"));
	}

	public static boolean isPrime(Integer n) {
		if (n < 1) {
			return false;
		}
		if (n < 4) {
			return true;
		}
		if (n % 2 == 0) {
			return false;
		}
		double sqrt = Math.sqrt(n);
		int sqrtCeiling = (int) Math.ceil(sqrt);

		for (int i = 3; i <= sqrtCeiling; i += 2) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static <T> void waitForStreamToComplete(Observable<T> stream) {
		stream
			.onErrorReturn((error) -> null)
			.toBlocking().last();
	}
	
	public static <T> Observable<T> unknown() {
		return Observable.error(new NoSuchElementException("You need to remove the unknown() call and add your own implementation there"));
	}
	
	public static void runDelayedAsync(long waitAmount, Runnable action) {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(waitAmount);
					action.run();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}).start();
		
	}
}
