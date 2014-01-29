package seminar;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class RecursionExercise {

	@Test
	public void coinProblem() throws Exception {
		// 1000 {500, 100} -> 3
		List<Integer> coins = new ArrayList<Integer>();

		coins.add(10);
		coins.add(100);
		coins.add(500); //11, 6, 1,

		assertEquals(18, numberOfPayments(1000, coins));
	}

	private int numberOfPayments(int money, List<Integer> coins) {
		if(coins.size() == 1) {
			if(money % coins.get(0) == 0)
				return 1;
			return 0;
		}
		
		int count = 0;
		
		int lastIndex = coins.size() - 1;
		for (int i = 0; i <= money / coins.get(lastIndex); i++)
			count += numberOfPayments(money - i * coins.get(lastIndex),
					coins.subList(0, lastIndex));
		
		return count;
	}

	@Test
	public void fibonacciTest() throws Exception {
		assertEquals(1, fibonacci(1));
		assertEquals(1, fibonacci(2));
		assertEquals(2, fibonacci(3));
		assertEquals(3, fibonacci(4));
		assertEquals(12586269025L, fibonacci(50));
	}

	long[] memo = new long[100];

	private long fibonacci(int n) {
		if (memo[n] > 0)
			return memo[n];

		if (1 == n || 2 == n)
			return memo[n] = 1;

		return memo[n] = fibonacci(n - 1) + fibonacci(n - 2);
	}
}
