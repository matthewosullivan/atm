import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		
		int[] nums = new int[99];
		Arrays.setAll(nums, i -> i + 8);
		System.out.println(Arrays.toString(nums));

	}

}
