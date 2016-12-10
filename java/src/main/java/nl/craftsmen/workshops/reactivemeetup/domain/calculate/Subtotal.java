package nl.craftsmen.workshops.reactivemeetup.domain.calculate;

public class Subtotal {

	private final int count;
	private final double sum;

	public Subtotal(int count, double sum) {
		this.count = count;
		this.sum = sum;
	}

	public Subtotal add(double value) {
		return new Subtotal(count + 1, sum + value);
	}

	public int getCount() {
		return count;
	}

	public double getSum() {
		return sum;
	}
}
