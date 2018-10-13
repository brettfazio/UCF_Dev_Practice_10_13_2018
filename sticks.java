import java.util.Arrays;
import java.util.Scanner;

public class sticks {

	static int[][] memo;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		for (int i =0; i < n; i++) {
			int sum  = sc.nextInt();

			int c = sc.nextInt();

			int[] cuts =  new int[c];
			memo = new int[c+1][c+1];
			for (int j =0 ; j < memo.length; j++) {
				Arrays.fill(memo[j], -1);
			}
			for (int j =0; j < cuts.length; j++) {
				cuts[j] =sc.nextInt();
			}

			System.out.println(rec(cuts,sum, 0,c-1));
		}
	}

	public static int rec(int[] cuts, int cost, int lower, int higher)  {
		int best = Integer.MAX_VALUE/2;
//		System.out.println(cost + " " + lower + " " + higher);
		if (lower == higher) {

			return cost;

		}

		if (memo[lower][higher] != -1) {
			return memo[lower][higher];
		}

		for (int i =lower; i <= higher; i++) {

			if (i == lower) {
				int right = rec(cuts,cost-cuts[i],i+1,higher);
				best = Math.min(best, cost + right);
				continue;
			}
			if (i == higher) {
				int left = rec(cuts,cuts[i],lower,Math.max(lower, i-1));
				best = Math.min(best, cost + left);
				continue;
			}

			//System.out.println(i + " " + cost + " " + cuts[i]);
			int left = rec(cuts,cuts[i],lower,Math.max(lower, i-1));
			int right = rec(cuts,cost-cuts[i],i+1,higher);
			best = Math.min(best, cost + right + left);

		}
		memo[lower][higher] = best;
		return best;
	}

}
/*

2
100
3 25 50 75
10
3 2 5 6

1
10
1 5

1
100
4 20 40 60 80
 */