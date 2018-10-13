import java.util.ArrayDeque;
import java.util.Scanner;

public class traffic {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int grids = sc.nextInt();
		
		for (int i =0 ;i  < grids; i++) {
			
			int intersections = sc.nextInt();
			
			boolean[][] connected = new boolean[intersections][intersections];
			
			for (int j = 0; j < intersections; j++) {
				int others = sc.nextInt();
				
				for (int k =0 ; k < others; k++) {
					int in = sc.nextInt()-1;
					
					connected[j][in] = connected[in][j] = true;
				}
			}
			
			int tries = sc.nextInt();
			System.out.println("City #"+(i+1));
			for (int j =0 ; j < tries; j++) {
				int skip = sc.nextInt()-1;
				
				if (good(connected,skip)) {
					System.out.println("yes");
				}else {
					System.out.println("no");
				}
			}
			
			System.out.println();
			
		}
	}
	
	public static boolean good(boolean[][] edges, int skip) {
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		
		if (skip == 0) {
			dq.push(1);
		}else {
			dq.push(0);
		}
		
		boolean[] seen = new boolean[edges.length];
		seen[dq.peekFirst()] = true;
		
		while (dq.isEmpty()==false) { 
			int pop = dq.pollFirst();
			
			for (int i =0 ; i < edges.length; i++) {
				if (i == skip) continue;
				if (edges[pop][i] && !seen[i]) {
					seen[i] = true;
					dq.push(i);
				}
			}
		}
		
		boolean ret = true;
		
		for (int i =0 ; i < seen.length; i++) {
			if (i==skip) continue;
			ret &= seen[i];
		}
		
		return !ret;
	}

}
