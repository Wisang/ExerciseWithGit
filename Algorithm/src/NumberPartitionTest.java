import static org.junit.Assert.*;
import static util.PrintUtil.printArr;
import org.junit.Test;

public class NumberPartitionTest {

	@Test
	public void partitionTest() throws Exception {
		assertEquals(3, numberOfPartition(3));
		assertEquals(7, numberOfPartition(5));
	}

	@Test
	public void generalPartitionTest() throws Exception {
		assertEquals(3, generalPartition(5, 2));
		assertEquals(5, generalPartition(5, 3));
	}

	@Test
	public void mathPartitionTest() throws Exception {
		assertEquals(3, recurrenceFormulaPartition(5, 2));
		assertEquals(5, recurrenceFormulaPartition(5, 3));
		assertEquals(7, recurrenceFormulaPartition(5, 5));
		assertEquals(154280588, recurrenceFormulaPartition(200, 200));
	}
	
	int[][] memo = new int[300][300];
	
	private int recurrenceFormulaPartition(int n, int m) {
		
		if(memo[n][m] > 0)
			return memo[n][m];
		
		if(0 == n)
			return memo[n][m] = 1;
		
		if(m>n)
			m = n;
		
		int count = 0;
		
		for(int i=1; i<=m; i++)
			count += recurrenceFormulaPartition(n-i, i); // n-(n-i) -> i
		
		return memo[n][m] = count;
	}

	@Test
	public void partitionPrintTest() throws Exception {
		int[] arr = new int[10];
		assertEquals(7, partitionPrint(5, 5, arr, 0));
	}
	
	private int partitionPrint(int n, int m, int[] arr, int arr_len) {
		if(0 == n) {
			printArr(arr, arr_len);
			return 1;
		}
		
		if(m>n)
			m = n;
		
		int count = 0;
		
		for(int i=1; i<=m; i++) {
			arr[arr_len] = i;
			count += partitionPrint(n-i, i, arr, arr_len+1);
		}
		
		return count;
	}

	private int generalPartition(int number, int set) {
		int[] numbers = new int[set];

		for (int i = 1; i <= set; i++)
			numbers[i - 1] = i;

		return partition(number, numbers, set);
	}

	private int numberOfPartition(int number) {
		return generalPartition(number, number);
	}

	private int partition(int number, int[] numbers, int n) {
		if (1 == n)
			return 1;

		int numberOfWays = number / numbers[n - 1];

		int count = 0;

		for (int i = 0; i <= numberOfWays; i++)
			count += partition(number - i * numbers[n - 1], numbers, n - 1);

		return count;
	}
}
