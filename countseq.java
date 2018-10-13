import java.util.Arrays;
import java.util.Scanner;

public class countseq {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int cases = sc.nextInt();

		for (int i =0 ; i < cases; i++) {

			char[] a = sc.next().toCharArray();
			char[] find = sc.next().toCharArray();

			long[][] cnt = new long[find.length][a.length];

			for (int j = 0; j < find.length; j++) {

				int curfind = find[j];

				for (int k = 0; k < a.length; k++) { 
					if (k-1 >= 0) {
						cnt[j][k] = cnt[j][k-1];
					}
					if (a[k]==curfind) {
//						System.out.println("here");
						if (j ==0 ) {
							if (k-1 < 0) {
								cnt[j][k]++;

							}
							else {
								cnt[j][k] = cnt[j][k-1]+1;
							}
						}else {
							if (k-1 < 0) {

							}
							else {
								cnt[j][k] = cnt[j][k-1] + cnt[j-1][k-1];
							}
						}
					}
				}

			}

//			for (int j = 0; j < cnt.length; j++) {
//				System.out.println(Arrays.toString(cnt[j]));
//			}

			System.out.println(cnt[find.length-1][a.length-1]);

		}
	}

}
