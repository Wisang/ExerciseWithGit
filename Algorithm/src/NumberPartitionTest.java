import static org.junit.Assert.*;
import org.junit.Test;


public class NumberPartitionTest {
	
	@Test
	public void partitionTest() throws Exception {
		assertEquals(3, numberOfPartition(3));
		assertEquals(7, numberOfPartition(5));
	}
	
	@Test
	public void generalPartitionTest() throws Exception {
		assertEquals(3, generalPartition(5,2));
		assertEquals(5, generalPartition(5,3));
	}

	private int generalPartition(int number, int set) {
		int[] numbers = new int[set];
		
		for(int i=1; i<=set; i++)
			numbers[i-1] = i;
		
		return partition(number, numbers, set);
	}

	private int numberOfPartition(int number) {
		return generalPartition(number, number);
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
