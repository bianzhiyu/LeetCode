package lc781_790;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

//785. Is Graph Bipartite?
//Runtime: 4 ms, faster than 90.02% of Java online submissions for Is Graph Bipartite?.
//Memory Usage: 40.6 MB, less than 15.51% of Java online submissions for Is Graph Bipartite?.
class Solution785 {
	public boolean isBipartite(int[][] graph) {
		int n = graph.length;
		int[] state = new int[n];
		int[] q = new int[n];
		int h = 0, r = 0, nt = 0;
		for (int i = 0; i < n; i++) {
			if (state[i] == 0) {
				q[0] = i;
				h = 0;
				r = 1;
				state[i] = 1;
				while (h < r) {
					nt = q[h];
					h++;
					for (int k = 0; k < graph[nt].length; k++) {
						if (state[graph[nt][k]] == 0) {
							state[graph[nt][k]] = 3 - state[nt];
							q[r] = graph[nt][k];
							r++;
						} else if (state[graph[nt][k]] + state[nt] != 3)
							return false;
					}
				}
			}
		}

		return true;

	}
}

// 786. K-th Smallest Prime Fraction
// TLE
class Solution786 {
	public int[] kthSmallestPrimeFraction(int[] A, int K) {
		BST_786 tr = new BST_786(A[0], A[1]);
		for (int i = 0; i < A.length; i++)
			for (int j = i + 1; j < A.length; j++)
				if (!(i == 0 && j == 1))
					tr = tr.insert(A[i], A[j]);
		for (int i = 1; i <= K - 1; i++)
			tr = tr.removeMin();
		tr = tr.getMinNode();
		return new int[] { tr.getX(), tr.getY() };
	}
}

// Ref:
// https://leetcode.com/problems/k-th-smallest-prime-fraction/discuss/228346/Java-Simple-code
// Runtime: 8 ms, faster than 91.10% of Java online submissions for K-th
// Smallest Prime Fraction.
// Memory Usage: 40.1 MB, less than 48.00% of Java online submissions for K-th
// Smallest Prime Fraction.
class Solution786_2 {
	private int[] countPairs(int[] A, double x) {
		int count = 0, n = A.length, p = 0, q = 0;
		double max = 0.0;
		for (int i = 0, j = 0; i < n; i++) {
			while (j < i && A[j] < A[i] * x)
				j++;
			if (j > 0) {
				double fraction = (double) A[j - 1] / (double) A[i];
				if (max < fraction) {
					max = fraction;
					p = A[j - 1];
					q = A[i];
				}
			}
			count += j;
		}
		return new int[] { count, p, q };
	}

	public int[] kthSmallestPrimeFraction(int[] A, int K) {
		int n = A.length, min = A[0], max = A[n - 1], p = 0, q = 0;
		double lo = (double) min / (double) max, hi = 1.0;
		while (lo < hi) {
			double mid = (lo + hi) / 2.0;
			int[] count = countPairs(A, mid);
			if (count[0] == K) {
				p = count[1];
				q = count[2];
				break;
			}
			if (count[0] < K)
				lo = mid;
			else
				hi = mid;
		}
		return new int[] { p, q };
	}
}

public class LC781_790 {
	public static void test786() {
		try {
			File inFile = new File("input" + File.separator + "input786.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			String instr;
			while ((instr = bfr.readLine()) != null && instr.length() > 0) 
			{
				String[] inarr = instr.substring(1, instr.length() - 1).split(",");

				int[] a = new int[inarr.length];
				for (int i = 0; i < inarr.length; i++)
					a[i] = Integer.parseInt(inarr[i].trim());

				int K = Integer.parseInt(bfr.readLine());

				Solution786 s = new Solution786();

				test.Test.dispArr(s.kthSmallestPrimeFraction(a, K));
			}
			bfr.close();
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	public static void main(String[] agrs) {
		test786();

	}
}
