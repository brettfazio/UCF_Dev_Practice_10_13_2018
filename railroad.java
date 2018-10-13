import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class railroad {

	static int[] n1, n2, m;

	static int[][][] memo;

	public static void main(String[] args) throws IOException {
		BufferedReader bu = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out =new PrintWriter(System.out);
		
		
		while (true) {
			String[] split = bu.readLine().split(" ");
			n1 = new int[Integer.parseInt(split[0])];
			n2 = new int[Integer.parseInt(split[1])];
			m = new int[n1.length+n2.length];

			split = bu.readLine().split(" ");
			for (int i =0; i < n1.length; i++) {
				n1[i] = Integer.parseInt(split[i]);
			}
			split = bu.readLine().split(" ");
			for (int i =0; i < n2.length; i++) {
				n2[i] = Integer.parseInt(split[i]);
			}
			split = bu.readLine().split(" ");
			for (int i = 0; i < m.length; i++) {
				m[i] = Integer.parseInt(split[i]);
			}

			if (n1.length == 0 && n2.length == 0) {
				return;
			}
			memo = new int[n1.length+1][n2.length+1][m.length+1];
//			if (pierce(0,0,0)) {
//				System.out.println("possible");
//			}else {
//				System.out.println("not possible");
//			}
			
			
			
		}
	}

	public static boolean pierce(int n1idx, int n2idx, int midx) {

		if (n1idx == n1.length && n2idx == n2.length) {
			return true;
		}
		if (midx == m.length) {
			return false;
		}

		if (memo[n1idx][n2idx][midx] != 0) {
			return memo[n1idx][n2idx][midx] == 1;
		}

		if (n1idx < n1.length) {
			if (m[midx] == n1[n1idx]) {
				if (memo[n1idx+1][n2idx][midx+1] != -1) {
					if (pierce(n1idx+1,n2idx,midx+1)) {
						memo[n1idx][n2idx][midx] = 1;
						return  true;
					}
				}
			}
		}
		if (n2idx < n2.length) {
			if (m[midx] == n2[n2idx]) {
				if (memo[n1idx][n2idx+1][midx+1] != -1) {
					if (pierce(n1idx, n2idx+1, midx+1)) {
						memo[n1idx][n2idx][midx] = 1;
						return true;
					}
				}
			}
		}

		memo[n1idx][n2idx][midx] = -1;
		return false;
	}

}
