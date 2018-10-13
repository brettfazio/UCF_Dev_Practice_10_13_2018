import java.util.Arrays;
import java.util.Scanner;

public class numways {

	static int mod = 1_000_000_000 + 7;

	static long[][] memo;
	static long[] memorec;
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		int cases = sc.nextInt();

//		memo = new long[10001][10s];

		for (int i =0 ; i < cases; i++) {

			int sum = sc.nextInt();
			int coins = sc.nextInt();
			int[] c= new int[coins];

			for (int j = 0; j < coins; j++) {
				c[j] =  sc.nextInt();
			}

//			for (int  j =0 ;  j < memo.length; j++) {
//				Arrays.fill(memo[j], -1);
//			}
			memorec = new long[10001];
			memorec[0] = 1;
			
			for (int j = 0; j < coins; j++) {
				 for (int k = c[j]; k <= sum; k++) {
					 memorec[k] += memorec[k - c[j]] % mod;
					 memorec[k] %= mod;
				 }
			}
			
			System.out.println(memorec[sum]);
			
//			System.out.println(recurse(c,sum,0)%mod);


		}

	}


	public static long recurse(int[] c, int left, int coin) {


		if (left == 0) {
			return 1;
		}
		if (left < 0) {
			return 0;
		}
		if (left > 0 && coin == c.length) {
			return 0;
		}
		//		System.out.println(left + " " + c[coin]);
		if (memo[left][coin] != -1) {
			return memo[left][coin];
		}
		long res = 0;
		if (coin + 1 < c.length && memo[left][coin+1] != -1) {
			res = memo[left][coin+1];
		}else {
			res = recurse(c,left,coin+1);
		}
		int templeft = left;
		while (templeft-c[coin] >= 0) {
			if (coin + 1 < c.length && memo[templeft-c[coin]][coin+1] != -1) {
				res += memo[templeft-c[coin]][coin+1] % mod;
			}else {
				if (coin+1 == c.length && templeft-c[coin] != 0) {

				}else {
					res += recurse(c,templeft-c[coin],coin+1) % mod;
				}
			}
			res %= mod;
			templeft -= c[coin];
		}
		//		System.out.println("memo " +left + " " + coin + " = " + res);
		memo[left][coin] = res;
		return res;

	}
}
/*

2
11 4 1 5 10 25
3 9 1 2 3 4 5 6 7 8 9

1
10000 9 1 2 3 4 5 6 7 8 9

 */