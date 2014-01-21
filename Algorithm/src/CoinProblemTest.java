import static org.junit.Assert.*;

import org.junit.Test;

public class CoinProblemTest {

	@Test
	public void numberOfPaymentTest() throws Exception {
		int[] coins = {1000, 100, 10};
		assertEquals(12, numberOfPaymentLoop(1000, coins));
	}

	private int numberOfPaymentLoop(int money, int[] coins) {
		int count = 0;
		for(int i=money; i>=0; i -= coins[0])
			for(int j=i; j>=0; j -= coins[1])
				if(0 == j % coins[2])
					count++;
		
		return count;
	}
}
