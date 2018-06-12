package atm;
import java.util.ArrayList;
import java.util.List;

/*
 https://www.sanfoundry.com/java-program-knapsack-algorithm/
 */
public class KnapsackUtil {
	public static List<Integer> solve(int[] notes, int W) {
		int[] abc = new int[notes.length + 1];
		for (int i = 0; i < notes.length; i++) {
			abc[i + 1] = notes[i];
		}
		int[] val = notes;
		int[] wt = notes;
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

		int[] selected = new int[N + 1];
		for (int n = N, w = W; n > 0; n--) {
			if (sol[n][w] != 0) {
				selected[n] = 1;
				w = w - wt[n];
			} else
				selected[n] = 0;
		}
		
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 1; i < N + 1; i++) {
			if (selected[i] == 1) {
				result.add(wt[i]);
			}
		}
		
		if (result.size() > 0 && result.stream().mapToInt(i -> i).sum() != W) {
			result.remove(0);
		}
		
		return result;
	}

}
