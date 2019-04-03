package lc941_950;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//942. DI String Match
//Runtime: 5 ms, faster than 99.66% of Java online submissions for DI String Match.
//Memory Usage: 38.6 MB, less than 46.74% of Java online submissions for DI String Match.
class Solution942
{
	public int[] diStringMatch(String S)
	{
		int upperbound = S.length(), lowerbound = 0;
		int[] a = new int[upperbound + 1];
		for (int i = 0; i < a.length - 1; i++)
		{
			if (S.charAt(i) == 'I')
			{
				a[i] = lowerbound;
				lowerbound++;
			} else
			{
				a[i] = upperbound;
				upperbound--;
			}
		}
		a[a.length - 1] = lowerbound;
		return a;
	}
}

//944. Delete Columns to Make Sorted
//Runtime: 11 ms, faster than 94.76% of Java online submissions for Delete Columns to Make Sorted.
//Memory Usage: 39.9 MB, less than 54.82% of Java online submissions for Delete Columns to Make Sorted.
class Solution944
{
	public int minDeletionSize(String[] A)
	{

		int n = A.length, m = A[0].length();
		int d = 0;
		for (int i = 0; i < m; i++)
		{
			for (int j = 0; j < n - 1; j++)
			{
				if (A[j].charAt(i) > A[j + 1].charAt(i))
				{
					d++;
					break;
				}
			}
		}
		return d;
	}
}

//945. Minimum Increment to Make Array Unique
//Runtime: 20 ms, faster than 81.53% of Java online submissions for Minimum Increment to Make Array Unique.
//Memory Usage: 45.6 MB, less than 81.25% of Java online submissions for Minimum Increment to Make Array Unique.
class Solution945
{
	public int minIncrementForUnique(int[] A)
	{
		if (A.length == 0)
			return 0;
		Arrays.parallelSort(A);
		int min = A[0], m = 0;
		for (int i = 0; i < A.length; i++)
		{
			if (A[i] <= min)
			{
				m += min - A[i];
				min++;
			} else
			{
				min = A[i] + 1;
			}
		}
		return m;
	}
}

//946. Validate Stack Sequences
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Validate Stack Sequences.
//Memory Usage: 44.3 MB, less than 24.51% of Java online submissions for Validate Stack Sequences.
class Solution946
{
	public boolean validateStackSequences(int[] pushed, int[] popped)
	{
		int i = 0, j = 0, len = pushed.length, st = 0;
		int[] stack = new int[len];
		while (i < len)
		{
			stack[st++] = pushed[i++];
			while (st > 0 && j < len && stack[st - 1] == popped[j])
			{
				st--;
				j++;
			}
		}
		while (st > 0)
			if (stack[--st] != popped[j++])
				return false;
		return true;
	}
}

//947. Most Stones Removed with Same Row or Column
//Runtime: 25 ms, faster than 53.01% of Java online submissions for Most Stones Removed with Same Row or Column.
//Memory Usage: 41.4 MB, less than 76.52% of Java online submissions for Most Stones Removed with Same Row or Column.
class Solution947
{
	private int[] father;

	private int getTop(int x)
	{
		int p = x;
		while (father[p] != p)
			p = father[p];
		while (x != p && father[x] != p)
		{
			int q = father[x];
			father[x] = p;
			x = q;
		}
		return p;
	}

	private void merge(int x, int y)
	{
		int p = getTop(x);
		int q = getTop(y);
		father[q] = p;
	}

	public int removeStones(int[][] stones)
	{
		int len = stones.length;
		father = new int[len];
		for (int i = 0; i < len; i++)
			father[i] = i;
		for (int i = 0; i < len; i++)
			for (int j = 0; j < i; j++)
				if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1])
					merge(i, j);
		HashMap<Integer, Integer> h = new HashMap<Integer, Integer>();
		int cps = 0;
		for (int i = 0; i < len; i++)
		{
			int p = getTop(i);
			if (!h.containsKey(p))
			{
				h.put(p, 1);
				cps++;
			} else
				h.put(p, h.get(p) + 1);
		}
		return len - cps;
	}
}

//std
class Solution947_2
{
	private static class DSU
	{
		int[] parent;

		public DSU(int N)
		{
			parent = new int[N];
			for (int i = 0; i < N; ++i)
				parent[i] = i;
		}

		public int find(int x)
		{
			if (parent[x] != x)
				parent[x] = find(parent[x]);
			return parent[x];
		}

		public void union(int x, int y)
		{
			parent[find(x)] = find(y);
		}
	}

	public int removeStones(int[][] stones)
	{
		int N = stones.length;
		DSU dsu = new DSU(20000);

		for (int[] stone : stones)
			dsu.union(stone[0], stone[1] + 10000);

		Set<Integer> seen = new HashSet<Integer>();
		for (int[] stone : stones)
			seen.add(dsu.find(stone[0]));

		return N - seen.size();
	}
}

public class LC941_950
{
	public static void test947()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input947.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output947.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int[][] g = test.Test.parse2DIntArr(inLine);

				Solution947 s = new Solution947();

				Solution947_2 s2 = new Solution947_2();

				int ans = s.removeStones(g);

				System.out.println(ans);
				System.out.println(s2.removeStones(g));
				bfw.write(ans + "");
				bfw.newLine();
			}

			bfr.close();
			bfw.flush();
			bfw.close();
		} catch (IOException e)
		{
			System.out.println(e.toString());
		}
	}

	public static void main(String[] args)
	{
		test947();
	}

}
