import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class NumberPartitionTest {
	
	@Test
	public void partitionTest() throws Exception {
		assertEquals(3, numberOfPartition(3));
		assertEquals(7, numberOfPartition(5));
	}

	private int numberOfPartition(int number) {
		int[] numbers = new int[number-1];
		
		for(int i=1; i<number; i++)
			numbers[i-1] = i;
		
		return 1 + partition(number, numbers, number-1);
	}

	private int partition(int number, int[] numbers, int n) {
		if(1==n)
			return 1;
		
		int numberOfWays = number / numbers[n-1];
		
		int count = 0;
		
		for(int i=0; i<=numberOfWays; i++)
			count += partition(number - i*numbers[n-1], numbers, n-1);
		
		return count;
	}
}
