import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class party {

	static int[] items,perm;
	static int[] w,m;
	static boolean[] used;
	static int best;
	static int[][]level;
	static boolean man;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int party = 1;
		while (true) {

			int rules = sc.nextInt();
			if (rules == 0) return;
			TreeMap<Character,Integer> assign = new TreeMap<Character,Integer>();

			TreeSet<Character>men = new TreeSet<>();

			TreeSet<Character>women = new TreeSet<>();

			ArrayList<char[]> input = new ArrayList<>();

			for (int i =0 ; i < rules; i++) {
				char[] in = sc.next().toCharArray();
				input.add(in);
				char f = in[0];
				char type = in[1];
				char l = in[2];

				if (Character.isUpperCase(f)) {
					women.add(f);
					men.add(l);
				}else {
					men.add(f);
					women.add(l);
				}

				if (!assign.containsKey(f)) {
					assign.put(f,assign.size());
				}
				if (!assign.containsKey(l)) {
					assign.put(l, assign.size());
				}
			}
			//			int higher = Math.max(men.size(), women.size())+1;
			level = new int[assign.size()][assign.size()];

			m = new int[men.size()];
			w = new int[women.size()];

			for (int i =0; i < m.length; i++) {
				m[i] = assign.get(men.pollFirst());
			}
			for (int i =0 ; i < w.length; i++) {
				w[i] = assign.get(women.pollFirst());
			}

			for (int i = 0; i < rules; i++) {
				char[] in = input.remove(0);

				char f = in[0];
				char type = in[1];
				char l = in[2];

				if (type == 'L') {
					level[assign.get(f)][assign.get(l)] = 2;
				}else if (type == 'T') {
					//					System.out.println(assign.get(f) + " " + assign.get(l) +" " + assign.size());
					level[assign.get(f)][assign.get(l)] = 1;
				}else {
					// = 0;
				}
			}

			if (m.length > w.length) {
				items = m;
				perm = new int[m.length];
				used = new boolean[m.length];
				man = true;
			}else {
				items = w;
				perm = new int[w.length];
				used = new boolean[w.length];
				man = false;
			}
			best =0 ;
//			System.out.println(assign);
			
//			System.out.println("b -> C " + level[4][3]);
//			System.out.println("b -> C " + level[assign.get('b')][assign.get('C')]);
			//			System.out.println("here");
			permute(0);
			
			System.out.printf("Party %d has a maximum happiness quotient of %d%n", party, best);
			party++;
		}
	}

	public static void permute(int pos) {
		int j = 0;
		if (pos >= used.length) {
			// process current array

			int happiness = 0;

			if (man) {
				for (int i =0; i < perm.length; i++) {
					if (i >= w.length) break;

					if (level[perm[i]][w[i]] == 2 && level[w[i]][perm[i]] == 2) {
						happiness += 4;
					}else if (level[perm[i]][w[i]] >= 1 && level[w[i]][perm[i]] >= 1) {
						happiness += 3;
					}else if (level[perm[i]][w[i]] >= 1) {
						happiness += 1;
					}else if (level[w[i]][perm[i]] >= 1) {
						happiness += 2;
					}
				}
			}else {
				for (int i =0; i < perm.length; i++) {
					if (i >= m.length) break;

					if (level[perm[i]][m[i]] == 2 && level[m[i]][perm[i]] == 2) {
//						System.out.println("4");
						happiness += 4;
					}else if (level[perm[i]][m[i]] >= 1 && level[m[i]][perm[i]] >= 1) {
//						System.out.println("3");
						happiness += 3;
					}else if (level[perm[i]][m[i]] >= 1) {
//						System.out.println("2");
						happiness += 2;
					}else if (level[m[i]][perm[i]] >= 1) {
//						System.out.println("1");
						happiness += 1;
					}
				}
			}

//			if (happiness == 10) {
//				System.out.println(man);
//				System.out.println(Arrays.toString(perm));
//				System.out.println(Arrays.toString(m));
//			}
			best =Math.max(best, happiness);

		}else {
			for (j = 0; j < used.length; j++) {
				if (!used[j]) { 
					used[j] = true;
					perm[pos] = items[j];
					permute(pos+1);
					used[j] = false;
				}
			}
		}
	}

}

/*

12
fLJ
fLM
fTC
bTJ
gTM
JLf
MTf
JTb
MLb
MTg
CTf
CLb
4
ATa
ATb
aTA
bTA
6
ALa
CLa
CLb
aTA
aLC
bTC
0


12
fLJ
fLM
fTC
bTJ
gTM
JLf
MTf
JTb
MLb
MTg
CTf
CLb
0


 */
