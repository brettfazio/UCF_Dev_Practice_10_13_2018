import java.util.Arrays;
import java.util.Scanner;

public class twelve {

	static int[][] edges;
	static boolean[] unseen;
	static int maxtime, end;
	static final int movietime = 134;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		edges = new int[n][n];
		

		
		int[][] dp = new int[n][n];
		
		for (int i =0 ; i < n; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE/2);

		}
		for (int i =0 ; i < n; i++) {
			dp[i][i] =0;
			for (int j =0 ; j < n; j++) {
				dp[i][j] = sc.nextInt();
			}
		}
		for (int k =0; k < n; k++) {
			for (int i =0 ; i < n; i++) {
				for (int j = 0; j < n; j++) {
					dp[i][j] = Math.min(dp[i][j], dp[i][k]+dp[k][j]);
				}
			}
		}
		
		int cases = sc.nextInt();
		unseen = new boolean[n];
		for (int i =0; i < cases; i++) {
			
			int start = sc.nextInt();
			end = sc.nextInt();
			maxtime = sc.nextInt();
			int good = 0;
			for (int j =1; j < n; j+=2) {
				if (j == start || j == end) {
					continue;
				}
				
				if (dp[start][j]+dp[j][end]+movietime <= maxtime) {
//					System.out.println(j);
					good++;
				}
			}
			
			System.out.println(good);
			
		}
		
		
		
	}
	
//	public static int recurse(int node, int t) {
//		
//		if (t > maxtime) {
//			return 0;
//		}
//		
//	}

}
