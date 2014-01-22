package util;

public class PrintUtil {
	public static void printArr(int[] arr, int arr_len) {
		for(int i=0; i<arr_len; i++)
			System.out.printf("%d ", arr[i]);
		System.out.println();
	}
}
