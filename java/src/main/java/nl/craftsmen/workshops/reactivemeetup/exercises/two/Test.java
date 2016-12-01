package nl.craftsmen.workshops.reactivemeetup.exercises.two;

import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		List<Integer> data = Arrays.asList(1, 2, 3);
		
		String result = data.stream().reduce(
			// identity
			"(data)",
			
			//accumulator
			(String str, Integer nextValue) -> {
				
				return str + String.valueOf(nextValue);
			},
			
			//combiner
			(String a, String b) -> {
				System.out.println("Combining " + a + " and " + b);
				return a + ":" + b;
			}
		);
		
		System.out.print("result = " + result);

	}

}
