package nl.craftsmen.workshops.reactivemeetup.util;

import java.text.NumberFormat;

import rx.Observable;

public class Utils {
	
	public static final NumberFormat NUMBER_FORMAT;
	
	static {
		NUMBER_FORMAT = NumberFormat.getNumberInstance();
		NUMBER_FORMAT.setMaximumFractionDigits(2);
		NUMBER_FORMAT.setMinimumFractionDigits(2);
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
		stream.toBlocking().last();
	}
}
