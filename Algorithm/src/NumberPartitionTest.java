import static org.junit.Assert.*;

import org.junit.Test;


public class NumberPartitionTest {
	@Test
	public void partitionTest() throws Exception {
		assertEquals(3, numberOfPartition(3));
		assertEquals(7, numberOfPartition(5));
	}

	private int numberOfPartition(int number) {
		return 3;
	}
}
