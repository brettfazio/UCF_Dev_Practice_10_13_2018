import java.util.ArrayDeque;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class box {

	static int[] dx = {0,0,-1,1}, dy = {1,-1,0,0};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {

			int d = sc.nextInt();
			int a= sc.nextInt();

			if (d == 0 || a == 0) {
				return;
			}
			
			char[][] map = new char[d][a];

			for (int dd =0 ;dd < d; dd++) {
				map[dd] = sc.next().toCharArray();
			}

			int startd = 0;
			int starta = 0;
			int endd = 0;
			int enda = 0;

			int[][] f = new int[10][2];

			int[][] s = new int[10][2];

			for (int i =0; i < 10; i++) {
				Arrays.fill(f[i], -1);
				Arrays.fill(s[i], -1);
			}

			for (int dd =0; dd < d; dd++) {
				for (int aa =0 ;aa < a; aa++) {
					if (map[dd][aa] == 'B') {
						startd = dd;
						starta = aa;
					}else if (map[dd][aa] == 'X') {
						endd = dd;
						enda = aa;
					}else if (map[dd][aa] >= '0' && map[dd][aa] <= '9') {
						int parse = map[dd][aa]-'0';
						if (f[parse][0] == -1) {
							f[parse][0] = dd;
							f[parse][1] = aa;
						}else {
							s[parse][0] = dd;
							s[parse][1] = aa;
						}
					}
				}
			}

			ArrayDeque<Integer> down = new ArrayDeque<>();
			ArrayDeque<Integer> across = new ArrayDeque<>();
			down.add(startd);
			across.add(starta);

			int[][]moves =new int[d][a];
			for (int i = 0; i < d; i++) {
				Arrays.fill(moves[i], -1);
			}
			moves[startd][starta] = 0;

			while (down.isEmpty() == false) {
				
				int cd = down.pollFirst();
				int ca = across.pollFirst();
//				System.out.println(cd + " " + ca);
				for (int t =0; t < 4; t++) {
					int nd = cd+dx[t];
					int na = ca+dy[t];

					if (nd >= 0 && nd < d && na >= 0 && na < a) {
						if (map[nd][na] == 'W') continue;
						if (moves[cd][ca] + 1 < moves[nd][na] || moves[nd][na] == -1) {
							moves[nd][na] = moves[cd][ca]+1;
							down.add(nd);
							across.add(na);
						}

					}
				}
				
				if (map[cd][ca] >= '0' && map[cd][ca] <= '9') {
					int parse = map[cd][ca]-'0';
//					System.out.println("here");
					if (f[parse][0] == cd && f[parse][1] == ca) {
						int nd = s[parse][0];
						int na = s[parse][1];
						if (moves[cd][ca] < moves[nd][na] || moves[nd][na] == -1) {
							moves[nd][na] = moves[cd][ca];
							down.add(nd);
							across.add(na);
						}
					}else {
						int nd = f[parse][0];
						int na = f[parse][1];
						if (moves[cd][ca] < moves[nd][na] || moves[nd][na] == -1) {
							moves[nd][na] = moves[cd][ca];
							down.add(nd);
							across.add(na);
						}
					}
				}
			}

//			for (int i = 0; i < d; i++) {
//				System.out.println(Arrays.toString(moves[i]));
//			}
			
			System.out.println("He got the Box in " + (moves[endd][enda]) + " steps!");
//			System.out.println(moves[endd][enda]);
		}
	}

}

/*

5 5
B....
....1
WWWWW
1....
....X
5 4
...B
WWW.
5XW.
WWW.
.5..
0 0


*/
