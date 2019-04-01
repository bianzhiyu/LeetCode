package lc831_840;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

//833. Find And Replace in String
//Runtime: 4 ms, faster than 62.55% of Java online submissions for Find And Replace in String.
//Memory Usage: 37.7 MB, less than 33.98% of Java online submissions for Find And Replace in String.
class Solution833
{
	private static class Mp implements Comparable<Mp>
	{
		public int strInd, arrInd;

		public Mp(int si, int ai)
		{
			strInd = si;
			arrInd = ai;
		}

		public int compareTo(Mp o)
		{
			return strInd - o.strInd;
		}
	}

	private boolean match(String S, int start, String p)
	{
		if (start + p.length() > S.length())
			return false;
		for (int i = 0; i < p.length(); i++)
			if (p.charAt(i) != S.charAt(start + i))
				return false;
		return true;
	}

	public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets)
	{
		List<Mp> lt = new ArrayList<Mp>();
		for (int i = 0; i < indexes.length; i++)
			lt.add(new Mp(indexes[i], i));
		Collections.sort(lt);
		int strp = 0, patp = 0;
		StringBuilder sb = new StringBuilder();
		while (strp < S.length() && patp < lt.size())
		{
			if (strp == lt.get(patp).strInd)
			{
				int arrind = lt.get(patp).arrInd;
				if (match(S, strp, sources[arrind]))
				{
					sb.append(targets[arrind]);
					strp += sources[arrind].length();
				}
				patp++;
			} else if (strp < lt.get(patp).strInd)
			{
				sb.append(S.charAt(strp));
				strp++;
			} else
				patp++;
		}
		while (strp < S.length())
			sb.append(S.charAt(strp++));
		return sb.toString();
	}
}

//835. Image Overlap
//Runtime: 74 ms, faster than 35.07% of Java online submissions for Image Overlap.
//Memory Usage: 51.8 MB, less than 5.66% of Java online submissions for Image Overlap.
class Solution835
{
	public int largestOverlap(int[][] A, int[][] B)
	{
		int len = A.length;
		int max = 0;
		for (int s1 = -len - 1; s1 <= len + 1; s1++)
			for (int s2 = -len - 1; s2 <= len + 1; s2++)
			{
				int ct = 0;
				for (int x = 0; x < len; x++)
					for (int y = 0; y < len; y++)
					{
						if (B[x][y] == 0)
							continue;
						int fx = x - s1, fy = y - s2;
						if (fx >= 0 && fy >= 0 && fx < len && fy < len && A[fx][fy] == 1)
							ct++;
					}
				if (ct > max)
					max = ct;
			}
		return max;
	}
}

//836. Rectangle Overlap
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Rectangle Overlap.
//Memory Usage: 36.6 MB, less than 92.47% of Java online submissions for Rectangle Overlap.
class Solution836
{
	public boolean isRectangleOverlap(int[] rec1, int[] rec2)
	{
		long xl = Math.max(rec1[0], rec2[0]), xr = Math.min(rec1[2], rec2[2]), yd = Math.max(rec1[1], rec2[1]),
				yu = Math.min(rec1[3], rec2[3]);
		if (xl > xr)
			xl = xr;
		if (yd > yu)
			yd = yu;
		return (xr - xl) * (yu - yd) > 0;
	}
}

//837. New 21 Game
//Time Limit Exceeded
class Solution837
{
	private double f(int N, int K, int W)
	{
		if (N <= 0)
			return 1;
		double p = 0;
		for (int x = 1; x <= W; x++)
		{
			if (x < K)
				p += f(N - x, K - x, W) / W;
			else if (x <= N)
				p += 1.0 / W;
		}
		return p;
	}

	public double new21Game(int N, int K, int W)
	{
		return f(N, K, W);
	}
}

//Time Limit Exceeded
//Stack Overflow
class Solution837_2
{
	private HashMap<Integer, Double> r;

	private double f(int N, int K, int W)
	{
		if (N <= 0 || K <= 0)
			return 1;
		int state = N * 10001 + K;
		if (r.containsKey(state))
			return r.get(state);
		double p = 0;
		for (int x = 1; x <= W; x++)
		{
			if (x < K)
				p += f(N - x, K - x, W) / W;
			else if (x <= N)
				p += 1.0 / W;
		}
		r.put(state, p);
		return p;
	}

	public double new21Game(int N, int K, int W)
	{
		r = new HashMap<Integer, Double>();
		return f(N, K, W);
	}
}

//Time Limit Exceeded
class Solution837_3
{
	public double new21Game(int N, int K, int W)
	{
		double[] f = new double[K + W + 2];
		Arrays.fill(f, 0);
		f[0] = 1;
		for (int i = 0; i < K; i++)
		{
			for (int j = i + 1; j <= i + W; j++)
				f[j] += f[i] / W;
			f[i] = 0;
		}
		double p = 0;
		for (int j = 0; j <= N; j++)
			p += f[j];
		return p;
	}
}

//Runtime: 3 ms, faster than 100.00% of Java online submissions for New 21 Game.
//Memory Usage: 34 MB, less than 100.00% of Java online submissions for New 21 Game.
class Solution837_4
{
	public double new21Game(int N, int K, int W)
	{
		if (N <= 0 || K <= 0)
			return 1;
		double[] f = new double[K + W + 2];
		f[0] = 1;
		double rem = 1;
		for (int i = 1; i < K; i++)
		{
			f[i] = rem / W;
			rem += f[i];
			if (i >= W)
				rem -= f[i - W];
		}
		for (int i = K; i <= K + W; i++)
		{
			f[i] = rem / W;
			if (i >= W)
				rem -= f[i - W];
		}
		double p = 0;
		for (int j = K; j <= N && j < K + W + 1; j++)
			p += f[j];
		return p;
	}
}

//Runtime: 4 ms, faster than 85.54% of Java online submissions for Magic Squares In Grid.
//Memory Usage: 37.1 MB, less than 58.06% of Java online submissions for Magic Squares In Grid.
class Solution840
{
	public static boolean isMagic(int a1, int a2, int a3, int a4, int a5, int a6, int a7, int a8, int a9)
	{
		return 3 * a1 - 2 * a7 - 2 * a8 + a9 == 0 && 3 * a2 - 2 * a7 + a8 - 2 * a9 == 0
				&& 3 * a3 + a7 - 2 * a8 - 2 * a9 == 0 && 3 * a4 + 2 * a7 - a8 - 4 * a9 == 0
				&& 3 * a5 - a7 - a8 - a9 == 0 && 3 * a6 - 4 * a7 - a8 + 2 * a9 == 0
				&& !(a1 == a2 && a2 == a3 && a3 == a4 && a4 == a5 && a5 == a6 && a6 == a7 && a7 == a8 && a8 == a9)
				&& (1 <= a1 && a1 <= 9) && (1 <= a2 && a2 <= 9) && (1 <= a3 && a3 <= 9) && (1 <= a4 && a4 <= 9)
				&& (1 <= a5 && a5 <= 9) && (1 <= a6 && a6 <= 9) && (1 <= a7 && a7 <= 9) && (1 <= a8 && a8 <= 9)
				&& (1 <= a8 && a9 <= 9);
	}

	public int numMagicSquaresInside(int[][] grid)
	{
		int tot = 0;
		for (int i = 0; i < grid.length - 2; i++)
			for (int j = 0; j < grid[0].length - 2; j++)
				if (isMagic(grid[i][j], grid[i][j + 1], grid[i][j + 2], grid[i + 1][j], grid[i + 1][j + 1],
						grid[i + 1][j + 2], grid[i + 2][j], grid[i + 2][j + 1], grid[i + 2][j + 2]))
					tot++;
		return tot;
	}
}

public class LC831_840
{
	public static void test837()
	{
		Solution837_4 s = new Solution837_4();
		System.out.println(s.new21Game(10, 1, 10));
		System.out.println(s.new21Game(6, 1, 10));
		System.out.println(s.new21Game(21, 17, 10));
		System.out.println(s.new21Game(1, 0, 2));
		System.out.println(s.new21Game(9676, 8090, 3056));
		System.out.println(s.new21Game(5819, 5165, 5424));
//		0.9999999999999999
//		0.6
//		0.732777787068608
//		1.0
//		0.7681312093000495
//		0.237332676906251

	}

	public static void main(String[] args)
	{
		test837();
	}

}
