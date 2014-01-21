import static org.junit.Assert.*;

import org.junit.Test;

public class CoinProblemTest {

	@Test
	public void fibonacciLoopTest() {
		assertEquals(1, fibonacciLoop(1));
		assertEquals(1, fibonacciLoop(2));
		assertEquals(2, fibonacciLoop(3));
		assertEquals(3, fibonacciLoop(4));
		assertEquals(5, fibonacciLoop(5));
	}

	private long fibonacciLoop(int n) {
		if (n == 1 || n == 2)
			return 1;

		long first = 1;
		long second = 1;
		long result = 0;

		for(int i=3; i<=n; i++) {
			result = first + second;

			first = second;
			second = result;
		}

		return result;
	}
}
