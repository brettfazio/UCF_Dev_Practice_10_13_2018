import java.util.Arrays;
import java.util.Scanner;

public class nqueens {

	static int[] dx = {1,-1,0,0,-1,-1,1,1};
	static int[] dy = {0,0,-1,1,-1,1,-1,1};
	
	static int[] last;
	static boolean found;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		for (int i =0; i < n; i++) { 
			
			int size = sc.nextInt();
			last = new int[size];
			found =false;
			rec(new int[size][size], 0);
			System.out.println();
		}
	}
	
	public static boolean rec(int[][] grid, int down) {
//		System.out.println(down);
		if (down == grid.length) {
			for (int andy : last) {
				System.out.print((andy+1) + " ");
			}
//			for (int i =0; i < grid.length; i++) {
//				System.out.println(Arrays.toString(grid[i]));
//			}
			return true;
		
		}
		if (found) return true;
//		if (last[0] == 0 && last[1] == 3 && last[2] == 2) {
//			for (int i =0; i < grid.length; i++) {
//				System.out.println("l "+Arrays.toString(grid[i]));
//			}
//		}
		for (int i = 0; i < grid.length; i++) {
			
			
			
			if (grid[down][i] == 0) {
				grid[down][i]++;
				for (int t = 0; t < dx.length; t++) {
					int nd = down+dx[t];
					int na = i+dy[t];
					
					while (nd >= 0 && nd < grid.length && na >= 0 && na < grid.length) {
						grid[nd][na]++;
						nd += dx[t];
						na += dy[t];
					}
				}
				last[down] = i;
				if (found) return true;
				if (rec(grid,down+1)) {
					if (found) return true;
	
					found = true;
					return true;
					
				}
				
				grid[down][i]--;
				for (int t = 0; t < dx.length; t++) {
					int nd = down+dx[t];
					int na = i+dy[t];
					
					while (nd >= 0 && nd < grid.length && na >= 0 && na < grid.length) {
						grid[nd][na]--;
						nd += dx[t];
						na += dy[t];
					}
				}
				
			}
			
		}
		
		return false;
		
	}

}
/*

3
4
5
5

1
1

1
4
*/