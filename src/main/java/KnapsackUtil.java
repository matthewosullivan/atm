import java.util.ArrayList;
import java.util.List;

public class KnapsackUtil {
	public List<Integer> solve(int[] wt, int[] val, int W) {
		int N = val.length - 1;
		int NEGATIVE_INFINITY = Integer.MIN_VALUE;
		int[][] m = new int[N + 1][W + 1];
		int[][] sol = new int[N + 1][W + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= W; j++) {
				int m1 = m[i - 1][j];
				int m2 = NEGATIVE_INFINITY;
				if (j >= wt[i])
					m2 = m[i - 1][j - wt[i]] + val[i];
				/** select max of m1, m2 **/
				m[i][j] = Math.max(m1, m2);
				sol[i][j] = m2 > m1 ? 1 : 0;
			}
		}
		/** make list of what all items to finally select **/
		int[] selected = new int[N + 1];
		for (int n = N, w = W; n > 0; n--) {
			if (sol[n][w] != 0) {
				selected[n] = 1;
				w = w - wt[n];
			} else
				selected[n] = 0;
		}
		/** Print finally selected items **/
		//System.out.println("\nItems selected : ");
		
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 1; i < N + 1; i++) {
			if (selected[i] == 1) {
				result.add(wt[i]);
			}
		}
		//System.out.println();
		
		//System.out.println(result);
		
		if (result.stream().mapToInt(i -> i).sum() != W) {
			result.remove(0);
		}
		
		return result;
	}

	/** Main function **/
	public static void main(String[] args) {
		/** Make an object of Knapsack class **/
		KnapsackUtil ks = new KnapsackUtil();

		int n = 10;

		int[] wt = {0, 50, 50, 50, 50, 50, 20, 20, 20, 20, 20};
		int[] val = {0, 50, 50, 50, 50, 50, 20, 20, 20, 20, 20};

		//System.out.println("\nEnter weight for " + n + " elements");
		//for (int i = 1; i <= n; i++)
		//	wt[i] = scan.nextInt();
		//System.out.println("\nEnter value for " + n + " elements");
		//for (int i = 1; i <= n; i++)
		//	val[i] = 1;

		//System.out.println("\nEnter knapsack weight ");
		int W = 110;

		System.out.println(ks.solve(wt, val, W));
	}
}
