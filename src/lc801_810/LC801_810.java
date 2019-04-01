package lc801_810;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

//801. Minimum Swaps To Make Sequences Increasing
//Runtime: 2 ms, faster than 97.24% of Java online submissions for Minimum Swaps To Make Sequences Increasing.
//Memory Usage: 39.1 MB, less than 94.55% of Java online submissions for Minimum Swaps To Make Sequences Increasing.
class Solution801
{
	public int minSwap(int[] A, int[] B)
	{
		int min1 = 0, min2 = 1;
		for (int i = 1; i < A.length; i++)
		{
			int nmin1 = Integer.MAX_VALUE, nmin2 = Integer.MAX_VALUE;
			if (A[i] > A[i - 1] && B[i] > B[i - 1])
			{
				nmin1 = Math.min(min1, nmin1);
				nmin2 = Math.min(min2 + 1, nmin2);
			}
			if (A[i] > B[i - 1] && B[i] > A[i - 1])
			{
				nmin1 = Math.min(min2, nmin1);
				nmin2 = Math.min(min1 + 1, nmin2);
			}
			min1 = nmin1;
			min2 = nmin2;
		}
		return Math.min(min1, min2);
	}
}

//802. Find Eventual Safe States
//Runtime: 32 ms, faster than 41.45% of Java online submissions for Find Eventual Safe States.
//Memory Usage: 66.7 MB, less than 36.79% of Java online submissions for Find Eventual Safe States.
class Solution802
{
	public List<Integer> eventualSafeNodes(int[][] graph)
	{
		int len = graph.length;
		List<List<Integer>> father = new ArrayList<List<Integer>>();
		for (int i = 0; i < len; i++)
			father.add(new ArrayList<Integer>());
		for (int i = 0; i < len; i++)
			for (int j = 0; j < graph[i].length; j++)
				father.get(graph[i][j]).add(i);
		List<Integer> q = new ArrayList<Integer>();
		boolean[] used = new boolean[len];
		int[] outD = new int[len];
		for (int i = 0; i < len; i++)
			if ((outD[i] = graph[i].length) == 0)
			{
				used[i] = true;
				q.add(i);
			}
		int f = 0;
		while (f < q.size())
		{
			int nt = q.get(f++);
			for (int pre : father.get(nt))
			{
				if (!used[pre])
				{
					outD[pre]--;
					if (outD[pre] == 0)
					{
						q.add(pre);
						used[pre] = true;
					}
				}
			}
		}
		Collections.sort(q);
		return q;
	}
}

//807. Max Increase to Keep City Skyline
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Max Increase to Keep City Skyline.
//Memory Usage: 42.8 MB, less than 26.74% of Java online submissions for Max Increase to Keep City Skyline.
class Solution807
{
	public int maxIncreaseKeepingSkyline(int[][] grid)
	{
		int len = grid.length;
		int[] tdh = new int[len];
		int[] lrh = new int[len];
		for (int i = 0; i < len; i++)
			for (int j = 0; j < len; j++)
			{
				if (grid[i][j] > tdh[j])
					tdh[j] = grid[i][j];
				if (grid[i][j] > lrh[i])
					lrh[i] = grid[i][j];
			}
		int tot = 0;
		for (int i = 0; i < len; i++)
			for (int j = 0; j < len; j++)
				tot += Math.min(tdh[j], lrh[i]) - grid[i][j];
		return tot;
	}
}

//808. Soup Servings
//TLE
class Solution808
{
	private double dfs(int A, int B)
	{
		if (A <= 0 && B <= 0)
			return 0.5;
		if (A <= 0)
			return 1;
		if (B <= 0)
			return 0;
		return 0.25 * (dfs(A - 100, B) + dfs(A - 75, B - 25) + dfs(A - 50, B - 50) + dfs(A - 25, B - 75));
	}

	public double soupServings(int N)
	{
		return dfs(N, N);
	}
}

class Solution808_2
{
	private HashMap<Integer, Double> r = new HashMap<Integer, Double>();

	private double dfs(int A, int B)
	{
		int key = (A % 10177) * 10177 + (B % 10177);
		if (r.containsKey(key))
			return r.get(key);
		if (A <= 0 && B <= 0)
			return 0.5;
		if (A <= 0)
			return 1;
		if (B <= 0)
			return 0;
		double a = 0.25 * (dfs(A - 100, B) + dfs(A - 75, B - 25) + dfs(A - 50, B - 50) + dfs(A - 25, B - 75));
		r.put(key, a);
		return a;
	}

	public double soupServings(int N)
	{
		return dfs(N, N);
	}
}

//Runtime: 5 ms, faster than 56.22% of Java online submissions for Soup Servings.
//Memory Usage: 37.5 MB, less than 45.45% of Java online submissions for Soup Servings.
class Solution808_3
{
	private HashMap<Integer, Double> r = new HashMap<Integer, Double>();

	private double dfs(int A, int B)
	{
		int key = (A % 10177) * 10177 + (B % 10177);
		if (r.containsKey(key))
			return r.get(key);
		if (A <= 0 && B <= 0)
			return 0.5;
		if (A <= 0)
			return 1;
		if (B <= 0)
			return 0;
		double a = 0.25 * (dfs(A - 100, B) + dfs(A - 75, B - 25) + dfs(A - 50, B - 50) + dfs(A - 25, B - 75));
		r.put(key, a);
		return a;
	}

	public double soupServings(int N)
	{
		if (N <= 10000)
			return dfs(N, N);
		return 1;
	}
}

//809. Expressive Words
//Runtime: 2 ms, faster than 97.10% of Java online submissions for Expressive Words.
//Memory Usage: 37.2 MB, less than 82.14% of Java online submissions for Expressive Words.
class Solution809
{
	private static class Compre
	{
		public List<Character> ch = new ArrayList<Character>();
		public List<Integer> mu = new ArrayList<Integer>();

		public Compre(String str)
		{
			int len = str.length();
			int i = 0;
			while (i < len)
			{
				int j = i + 1;
				while (j < len && str.charAt(j) == str.charAt(i))
					j++;
				ch.add(str.charAt(i));
				mu.add(j - i);
				i = j;
			}
		}

		public boolean canStrechBy(String str)
		{
			int len = str.length();
			int i = 0, p = 0;
			while (i < len)
			{
				int j = i + 1;
				while (j < len && str.charAt(j) == str.charAt(i))
					j++;
				if (ch.get(p) != str.charAt(i))
					return false;
				if (mu.get(p) < 3 && j - i != mu.get(p))
					return false;
				if (mu.get(p) >= 3 && j - i > mu.get(p))
					return false;
				i = j;
				p++;
			}
			return p == ch.size() && i == len;
		}
	}

	public int expressiveWords(String S, String[] words)
	{
		Compre pt = new Compre(S);
		int tot = 0;
		for (String s : words)
			if (pt.canStrechBy(s))
				tot++;
		return tot;
	}
}

//810. Chalkboard XOR Game
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Chalkboard XOR Game.
//Memory Usage: 42.2 MB, less than 12.50% of Java online submissions for Chalkboard XOR Game.
class Solution810
{
	public boolean xorGame(int[] nums)
	{
		if (nums.length % 2 == 0)
			return true;
		int x = 0;
		for (int i = 0; i < nums.length; i++)
			x ^= nums[i];
		return x == 0;
	}
}

public class LC801_810
{
	public static void test802()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input802.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output802.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int[][] g = test.Test.parse2DIntArr(inLine);

				Solution802 s = new Solution802();

				List<Integer> ans = s.eventualSafeNodes(g);

				System.out.println(ans);
				bfw.write(ans.toString());
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

	public static void test808()
	{
		Solution808_2 s = new Solution808_2();
		System.out.println(s.soupServings(50));
		System.out.println(s.soupServings(100));
		System.out.println(s.soupServings(150));
		System.out.println(s.soupServings(200));
		System.out.println(s.soupServings(500));
		System.out.println(s.soupServings(1000));
//		0.625
//		0.71875
//		0.75781
//		0.79688
//		0.91634
//		0.97657
	}

	public static void main(String[] args)
	{
		test808();
	}
}
