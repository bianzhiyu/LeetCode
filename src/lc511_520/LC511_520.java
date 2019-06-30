package lc511_520;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import treeCodec.*;

//513. Find Bottom Left Tree Value
//Runtime: 3 ms, faster than 95.04% of Java online submissions for Find Bottom Left Tree Value.
//Memory Usage: 37.9 MB, less than 80.00% of Java online submissions for Find Bottom Left Tree Value.
class Solution513
{
	private void travel(int sp, TreeNode rt, List<Integer> l)
	{
		if (rt == null)
			return;
		if (l.size() == sp)
			l.add(rt.val);
		travel(sp + 1, rt.left, l);
		travel(sp + 1, rt.right, l);
	}

	public int findBottomLeftValue(TreeNode root)
	{
		List<Integer> layer = new ArrayList<Integer>();
		travel(0, root, layer);
		return layer.get(layer.size() - 1);
	}
}

//514. Freedom Trail
//Runtime: 17 ms, faster than 93.04% of Java online submissions for Freedom Trail.
//Memory Usage: 38.8 MB, less than 73.53% of Java online submissions for Freedom Trail.
class Solution514
{
	List<List<Integer>> map;
	boolean[][] fd;
	int[][] d;
	int rl;

	int dt(int s, int e, int l)
	{
		int r1 = e - s;
		if (r1 < 0)
			r1 += l;
		int r2 = s - e;
		if (r2 < 0)
			r2 += l;
		if (r1 < r2)
			return r1;
		else
			return r2;
	}

	int dfs(int sp, int charIndInRing)
	{
		if (sp == 0)
			return dt(charIndInRing, 0, rl);
		int min = Integer.MAX_VALUE;
		for (int c : map.get(sp - 1))
		{
			if (!fd[sp - 1][c])
			{
				d[sp - 1][c] = dfs(sp - 1, c);
				fd[sp - 1][c] = true;
			}
			int t = d[sp - 1][c] + dt(c, charIndInRing, rl);
			if (t < min)
				min = t;
		}
		return min;
	}

	public int findRotateSteps(String ring, String key)
	{
		char[] ra = ring.toCharArray(), ka = key.toCharArray();
		rl = ra.length;
		int kl = ka.length;
		map = new ArrayList<List<Integer>>(0);

		for (int i = 0; i < kl; i++)
		{
			map.add(new ArrayList<Integer>());
			for (int j = 0; j < rl; j++)
				if (ra[j] == ka[i])
					map.get(i).add(j);
		}

		d = new int[kl][rl];
		fd = new boolean[kl][rl];
		int min = Integer.MAX_VALUE;
		for (int c : map.get(kl - 1))
		{
			int t = dfs(kl - 1, c);
			if (t < min)
				min = t;
		}
		return min + kl;
	}
}

///515. Find Largest Value in Each Tree Row
//Runtime: 3 ms, faster than 100.00% of Java online submissions for Find Largest Value in Each Tree Row.
//Memory Usage: 40.8 MB, less than 21.05% of Java online submissions for Find Largest Value in Each Tree Row.
class Solution515
{
	public List<Integer> largestValues(TreeNode root)
	{
		List<Integer> ans = new ArrayList<Integer>();
		dfs(root, 0, ans);
		return ans;
	}

	void dfs(TreeNode rt, int layer, List<Integer> ans)
	{
		if (rt == null)
			return;
		if (ans.size() <= layer)
			ans.add(rt.val);
		dfs(rt.left, layer + 1, ans);
		dfs(rt.right, layer + 1, ans);
		if (rt.val > ans.get(layer))
			ans.set(layer, rt.val);
	}
}

//516. Longest Palindromic Subsequence
//Runtime: 56 ms, faster than 9.50% of Java online submissions for Longest Palindromic Subsequence.
//Memory Usage: 50.4 MB, less than 14.11% of Java online submissions for Longest Palindromic Subsequence.
class Solution516
{
	public int longestPalindromeSubseq(String s)
	{
		int len = s.length();
		if (len < 2)
			return len;
		int maxans = 1;
		int[][] d = new int[len][len];// init : false
		for (int i = 0; i < len; i++)
			d[i][i] = 1;
		for (int i = 0; i < len - 1; i++)
			if (s.charAt(i) == s.charAt(i + 1))
				d[i][i + 1] = 2;
			else
				d[i][i + 1] = 1;

		for (int i = 3; i <= len; i++)
			for (int j = 0; j + i - 1 < len; j++)
			{
				d[j][j + i - 1] = d[j][j + i - 2];
				if (d[j + 1][j + i - 1] > d[j][j + i - 1])
					d[j][j + i - 1] = d[j + 1][j + i - 1];
				if (s.charAt(j) == s.charAt(j + i - 1) && d[j + 1][j + i - 2] + 2 > d[j][j + i - 1])
					d[j][j + i - 1] = d[j + 1][j + i - 2] + 2;
			}
		for (int i = 1; i <= len; i++)
			for (int j = 0; j + i - 1 < len; j++)
				if (d[j][j + i - 1] > maxans)
					maxans = d[j][j + i - 1];
		return maxans;
	}
}

//517. Super Washing Machines
//https://leetcode.com/problems/super-washing-machines/discuss/235584/Explanation-of-Java-O(n)-solution
//Runtime: 5 ms, faster than 99.15% of Java online submissions for Super Washing Machines.
//Memory Usage: 39.8 MB, less than 100.00% of Java online submissions for Super Washing Machines.
class Solution517
{
	public int findMinMoves(int[] machines)
	{
		int total = 0;
		for (int clothes : machines)
		{
			total += clothes;
		}

		if (total % machines.length != 0)
		{
			return -1;
		}

		int avg = total / machines.length;
		int maxRunningBalance = 0;
		int maxOffLoad = 0;
		int runningBalance = 0;

		for (int clothes : machines)
		{
			int offload = clothes - avg;
			runningBalance += offload;
			maxRunningBalance = Math.max(maxRunningBalance, Math.abs(runningBalance));
			maxOffLoad = Math.max(maxOffLoad, offload);
		}

		return Math.max(maxOffLoad, maxRunningBalance);
	}
}

//bag question
//518. Coin Change 2
//Runtime: 2 ms, faster than 100.00% of Java online submissions for Coin Change 2.
//Memory Usage: 36.6 MB, less than 48.17% of Java online submissions for Coin Change 2.
class Solution518
{
	public int change(int amount, int[] coins)
	{
		int[] d = new int[amount + 1];
		d[0] = 1;
		for (int i = 0; i < coins.length; i++)
			for (int j = coins[i]; j <= amount; j++)
				d[j] += d[j - coins[i]];
		return d[amount];
	}
}

//519. Random Flip Matrix
//Runtime: 68 ms, faster than 96.28% of Java online submissions for Random Flip Matrix.
//Memory Usage: 35.9 MB, less than 100.00% of Java online submissions for Random Flip Matrix.
class Solution519
{
	private int rows, cols, selnum;
	private int[] selected = new int[1010];
	private Random rd = new Random();

	public Solution519(int n_rows, int n_cols)
	{
		rows = n_rows;
		cols = n_cols;
		selnum = 0;
	}

	public int[] flip()
	{
		int p = rd.nextInt(rows * cols - selnum);
		int j = 0;
		while (j < selnum && selected[j] <= p)
		{
			p++;
			j++;
		}
		if (j == selnum)
			selected[selnum++] = p;
		else
		{
			for (int i = selnum; i > j; i--)
				selected[i] = selected[i - 1];
			selnum++;
			selected[j] = p;
		}

		return new int[]
		{ p / cols, p % cols };
	}

	public void reset()
	{
		selnum = 0;
	}
}

/**
 * Your Solution object will be instantiated and called as such: Solution obj =
 * new Solution(n_rows, n_cols); int[] param_1 = obj.flip(); obj.reset();
 */

//520. Detect Capital
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Detect Capital.
//Memory Usage: 34.7 MB, less than 99.90% of Java online submissions for Detect Capital.
class Solution520 
{
	private static boolean isCap(char c)
	{
		return c>='A' && c<='Z';
	}
	private static boolean isSmall(char c)
	{
		return c>='a' && c<='z';
	}
	private static boolean isMod1(String s)
	{
		if (s==null || s.length()==0)
		{
			return true;
		}
		for (int i=0;i<s.length();i++)
		{
			if (!isCap(s.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}
	private static boolean isMod2(String s)
	{
		if (s==null || s.length()==0)
		{
			return true;
		}
		for (int i=0;i<s.length();i++)
		{
			if (!isSmall(s.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}
	private static boolean isMod3(String s)
	{
		if (s==null || s.length()==0)
		{
			return true;
		}
		if (!isCap(s.charAt(0)))
		{
			return false;
		}
		for (int i=1;i<s.length();i++)
		{
			if (!isSmall(s.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}
    public boolean detectCapitalUse(String word) 
    {
        return isMod1(word) || isMod2(word) || isMod3(word);
    }
}

public class LC511_520
{
	public static void test514()
	{
		Solution514 s = new Solution514();
		String ring = "godding";
		String key = "gd";
		System.out.println(s.dt(0, 4, 5));
		System.out.println(s.findRotateSteps(ring, key));
	}

	public static void test516()
	{
		String in = "bbbab";
		Solution516 solver = new Solution516();
		System.out.println(solver.longestPalindromeSubseq(in));
		in = "cbbd";
		System.out.println(solver.longestPalindromeSubseq(in));
	}

	public static void main(String args[])
	{
		test516();
	}
}
