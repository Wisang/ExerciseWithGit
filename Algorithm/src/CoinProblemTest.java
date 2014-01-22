import static org.junit.Assert.*;
import static util.PrintUtil.printArr;

import org.junit.Test;

public class CoinProblemTest {

	@Test
	public void numberOfPaymentTest() throws Exception {
		int[] coins = {1000, 100, 10};
		assertEquals(12, numberOfPaymentLoop(1000, coins));
	}
	
	@Test
	public void numberOfPaymentRecursionTest() throws Exception {
		int[] coins = {1,2,5,10,20,50};
		assertEquals(4562, numberOfPaymentRecursion(100, coins, 6));
	}
	
	@Test
	public void paymentsPrint() throws Exception {
		int[] coins = {1000, 500, 100};
		int[] arr = new int[20];
		assertEquals(4, paymentPrint(1000, coins, 3, arr, 0));
	}

	private int paymentPrint(int money, int[] coins, int n, int[] arr, int arr_len) {
		if(1 == n) {
			if(0 == money % coins[0]) {
				printArr(arr, arr_len);
				return 1;
			}
			return 0;
		}
		
		int numberOfWays = money / coins[n-1];
		
		int count = 0;
		
		for(int i=0; i<= numberOfWays; i++) {
			for(int j=0; j<i; j++)
				arr[arr_len + j] = coins[n-1];
			count  += paymentPrint(money - i * coins[n-1], coins, n-1, arr, arr_len+i);
		}
		
		return count;
	}

	private int numberOfPaymentRecursion(int money, int[] coins, int n) {
		
		if(1 == n) {
			if(0 == money % coins[0])
				return 1;
			return 0;
		}
		
		int numberOfWays = money / coins[n-1];
		
		int count = 0;
		
		for(int i=0; i<= numberOfWays; i++)
			count  += numberOfPaymentRecursion(money - i * coins[n-1], coins, n-1);
			
		return count;
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
