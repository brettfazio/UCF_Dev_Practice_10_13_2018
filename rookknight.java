import java.util.Scanner;

public class rookknight {

	static int[] dd = {2,2,-1,-1,1,1,-2,-2};
	static int[] da = {-1,1,2,-2,2,-2,-1,1};
	static int[] rookd = {1,-1,0,0};
	static int[] rooka = {0,0,-1,1};
	static int[] last;
	static boolean found;
	static int cnt;
	static int need;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		for (int i =0; i < n; i++) {
			int size = sc.nextInt();

			last = new int[size];
			need = sc.nextInt();
			cnt =0;
			found = false;
			rec(new int[size][size],0);
			System.out.println();
		}
	}

	public static void rec(int[][] grid, int down) {
		//		System.out.println(down);
		if (down == grid.length) {
			cnt++;

			if (cnt == need) {
				for (int andy : last) {
					System.out.print((andy+1) + " ");
				}
				found = true;
			}
			//			for (int i =0; i < grid.length; i++) {
			//				System.out.println(Arrays.toString(grid[i]));
			//			}
			return ;

		}
		if (found ) return;
		//		if (last[0] == 0 && last[1] == 3 && last[2] == 2) {
		//			for (int i =0; i < grid.length; i++) {
		//				System.out.println("l "+Arrays.toString(grid[i]));
		//			}
		//		}
		for (int i = 0; i < grid.length; i++) {



			if (grid[down][i] == 0) {
				grid[down][i]++;
				for (int t = 0; t < dd.length; t++) {
					int nd = down+dd[t];
					int na = i+da[t];
					if (nd >= 0 && na >= 0 && nd < grid.length && na <grid.length)
						grid[nd][na]++;
				}

				for (int t = 0; t < rookd.length; t++) {
					int nd = down+rookd[t];
					int na = i+rooka[t];

					while (nd >= 0 && nd < grid.length && na >= 0 && na < grid.length) {
						grid[nd][na]++;
						nd += rookd[t];
						na += rooka[t];
					}
				}
				last[down] = i;
				//				if (rec(grid,down+1)) {
				rec(grid,down+1);
				if (found) return;
				//					return true;

				//}

				grid[down][i]--;
				for (int t = 0; t < dd.length; t++) {
					int nd = down+dd[t];
					int na = i+da[t];
					if (nd >= 0 && na >= 0 && nd < grid.length && na <grid.length)
						grid[nd][na]--;
				}

				for (int t = 0; t < rookd.length; t++) {
					int nd = down+rookd[t];
					int na = i+rooka[t];

					while (nd >= 0 && nd < grid.length && na >= 0 && na < grid.length) {
						grid[nd][na]--;
						nd += rookd[t];
						na += rooka[t];
					}
				}

			}

		}

		//		return false;

	}
}
