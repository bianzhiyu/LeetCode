package lc171_180;

import java.util.Arrays;
import java.util.Comparator;

//t174. Dungeon Game
//t=5ms, m=38MB
//t:0.4755, m:1
class Solution174
{
	private int[][] d;
	private int[][] map;

	private boolean feasible(int hp0)
	{
		d[0][0] = hp0 + map[0][0];
		for (int j = 1; j < d[0].length; j++)
			if (d[0][j - 1] <= 0)
				d[0][j] = 0;
			else
				d[0][j] = Math.max(d[0][j - 1] + map[0][j], 0);
		for (int i = 1; i < d.length; i++)
		{
			if (d[i - 1][0] <= 0)
				d[i][0] = 0;
			else
				d[i][0] = Math.max(d[i - 1][0] + map[i][0], 0);
			for (int j = 1; j < d[i].length; j++)
			{
				if (d[i - 1][j] > 0)
					d[i][j] = Math.max(0, d[i - 1][j] + map[i][j]);
				else
					d[i][j] = 0;
				if (d[i][j - 1] > 0)
					d[i][j] = Math.max(d[i][j], d[i][j - 1] + map[i][j]);
			}
		}
		return d[d.length - 1][d[0].length - 1] > 0;
	}

	private int search(int maxhp, int minhp)
	{
		if (maxhp == minhp + 1)
			return maxhp;
		int m = (maxhp + minhp) / 2;
		if (feasible(m))
			return search(m, minhp);
		return search(maxhp, m);
	}

	public int calculateMinimumHP(int[][] dungeon)
	{
		int m = dungeon.length, n = dungeon[0].length;
		map = dungeon;
		d = new int[m][n];
		int thp = 1;
		while (!feasible(thp))
			thp = thp * 2;
		if (thp == 1)
			return 1;
		return search(thp, thp / 2);
	}
}

//A dynamic programming solution.
//d[i][j]=the minimum hp before I enter dungeon[i][j] such that
//I can get to destination:
//With hp=d[i][j], I can enter dungeon[i][j],and can still find a 
//legal route to destination.
//Any hp<d[i][j] is not feasible.
//This dp should be solved backwards.
//I tried to get a forward dp, which failed.
//t=4ms, t:0.7211
class Solution174_2
{
	private int[][] d;

	public int calculateMinimumHP(int[][] dungeon)
	{
		int m = dungeon.length, n = dungeon[0].length;
		d = new int[m][n];
		d[m - 1][n - 1] = Math.max(1, 1 - dungeon[m - 1][n - 1]);
		for (int j = n - 2; j >= 0; j--)
			d[m - 1][j] = Math.max(1, d[m - 1][j + 1] - dungeon[m - 1][j]);
		for (int i = m - 2; i >= 0; i--)
		{
			d[i][n - 1] = Math.max(1, d[i + 1][n - 1] - dungeon[i][n - 1]);
			for (int j = n - 2; j >= 0; j--)
			{
				d[i][j] = Math.max(1, d[i][j + 1] - dungeon[i][j]);
				d[i][j] = Math.min(d[i][j], Math.max(1, d[i + 1][j] - dungeon[i][j]));
			}
		}
		return d[0][0];
	}
}

//179. Largest Number
//Runtime: 3 ms, faster than 99.94% of Java online submissions for Largest Number.
//Memory Usage: 37.8 MB, less than 91.45% of Java online submissions for Largest Number.
class Solution179
{
	public static class numCmp implements Comparator<String>
	{
		public int compare(String s1, String s2)
		{
			int p1 = 0, p2 = 0;
			while (true)
			{
				if (p1 == s1.length() && p2 == s2.length())
					return 0;
				if (p1 == s1.length())
					return compare(s1, s2.substring(p1));
				if (p2 == s2.length())
					return compare(s1.substring(p2), s2);
				if (s1.charAt(p1) < s2.charAt(p2))
					return -1;
				if (s1.charAt(p1) > s2.charAt(p2))
					return 1;
				p1++;
				p2++;
			}
		}

		public int compare(Integer o1, Integer o2)
		{
			return compare(Integer.toString(o1), Integer.toString(o2));
		}
	}

	public String largestNumber(int[] nums)
	{
		int len = nums.length;
		String[] str = new String[len];
		for (int i = 0; i < len; i++)
			str[i] = Integer.toString(nums[i]);
		Arrays.parallelSort(str, new numCmp());
		String ans = "";
		for (int i = len - 1; i >= 0; i--)
			ans = ans + str[i];
		while (ans.length() > 1 && ans.charAt(0) == '0')
			ans = ans.substring(1);
		return ans;
	}
}

public class LC171_180
{
	public static void main(String[] args)
	{
		System.out.println(new Solution174().calculateMinimumHP(new int[][]
		{
				{ -2, -3, 3 },
				{ -5, -10, 1 },
				{ 10, 30, -5 } }
//						{{2},{1}}
		));
	}

}
