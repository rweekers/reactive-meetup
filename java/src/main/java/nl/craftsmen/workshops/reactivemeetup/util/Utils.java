package nl.craftsmen.workshops.reactivemeetup.util;

public class Utils {

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
}
