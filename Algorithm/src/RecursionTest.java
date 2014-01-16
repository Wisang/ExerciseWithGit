import static org.junit.Assert.*;

import org.junit.Test;

public class RecursionTest {

	@Test
	public void fibonacciTest() {
		assertEquals(1, fibonacci(1));
		assertEquals(1, fibonacci(2));
		assertEquals(2, fibonacci(3));
		assertEquals(3, fibonacci(4));
		assertEquals(5, fibonacci(5));
		assertEquals(8, fibonacci(6));
	}
	
	private int fibonacci(int n) {
		if(n==1 || n==2)
			return 1;
		return fibonacci(n-1) + fibonacci(n-2);
	}
	
	@Test
	public void fasterFibonacci() throws Exception {
		assertEquals(8, fibonacciFast(6));
		assertEquals(12586269025L, fibonacciFast(50));
	}

	long[] memo = new long[200];
	
	private long fibonacciFast(int n) {
		
		if(memo[n]>0)
			return memo[n];
		
		if(n==1 || n==2)
			return memo[n] = 1;

		return memo[n] = fibonacciFast(n-1) + fibonacciFast(n-2); 
	}
}
