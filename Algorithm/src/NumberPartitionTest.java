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
		List<Integer> num = new ArrayList<Integer>();
		
		for(int i=1; i<=number; i++)
			num.add(i);
		
		return partition(number, num);
	}

	private int partition(int number, List<Integer> num) {
		if(1 == number)
			return 1;
		
		int count = 0;
		int numberOfWays = number / num.get(num.size()-1);
		
		for(int i=0; i<numberOfWays; i++)
			count += partition(number - i*num.get(num.size()-1), num.subList(0, num.size()-1));
		
		return count;
	}
}
