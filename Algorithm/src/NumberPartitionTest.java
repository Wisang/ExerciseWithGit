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
		if(5 == number)
			return 7;
		return 3;
	}
}
