package lc461_470;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

//462. Minimum Moves to Equal Array Elements II
//Runtime: 5 ms, faster than 56.42% of Java online submissions for Minimum Moves to Equal Array Elements II.
//Memory Usage: 39.8 MB, less than 91.30% of Java online submissions for Minimum Moves to Equal Array Elements II.
class Solution462
{
	public int minMoves2(int[] nums)
	{
		Arrays.parallelSort(nums);
		int mid, len = nums.length;
		if (nums.length % 2 == 0)
		{
			mid = (nums[len / 2] + nums[len / 2 - 1]) / 2;
		} else
			mid = nums[len / 2];
		int ct = 0;
		for (int i = 0; i < nums.length; i++)
			if (nums[i] > mid)
				ct += nums[i] - mid;
			else
				ct += mid - nums[i];
		return ct;
	}
}

//463. Island Perimeter
//Runtime: 10 ms, faster than 46.00% of Java online submissions for Island Perimeter.
//Memory Usage: 58.9 MB, less than 99.49% of Java online submissions for Island Perimeter.
class Solution463
{
	private static final int[][] di=new int[][] {{1,0},{0,1},{-1,0},{0,-1}};
    public int islandPerimeter(int[][] grid) 
    {
        int c=0;
        for (int i=0;i<grid.length;i++)
        	for (int j=0;j<grid[i].length;j++)
        	{
        		if (grid[i][j]==0) continue;
        		for (int k=0;k<4;k++) 
        		{
        			int nx=i+di[k][0],ny=j+di[k][1];
        			if (nx<0 || ny<0 || nx>=grid.length || ny>=grid[i].length || grid[nx][ny]==0)
        				c++;
        		}
        	}
        return c;
    }
}

//464. Can I Win
//https://leetcode.com/problems/can-i-win/discuss/216992/Very-clean-Java-solution
//Runtime: 73 ms, faster than 71.12% of Java online submissions for Can I Win.
//Memory Usage: 46.7 MB, less than 79.22% of Java online submissions for Can I Win.
class Solution464
{
	private HashMap<Integer, Boolean> hm = new HashMap<Integer, Boolean>();
	private boolean[] used;

	public boolean canIWin(int maxChoosableInteger, int desiredTotal)
	{
		if (maxChoosableInteger >= desiredTotal)
			return true;
		if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal)
			return false;
		used = new boolean[maxChoosableInteger];
		Arrays.fill(used, false);
		return dfs(desiredTotal);
	}

	private boolean dfs(int target)
	{
		if (target <= 0)
			return false;
		int state = getState();
		if (hm.containsKey(state))
			return hm.get(state);
		boolean canWin = false;
		for (int i = 0; i < used.length; i++)
			if (!used[i])
			{
				used[i] = true;
				canWin = !dfs(target - 1 - i);
				used[i] = false;
				if (canWin)
					break;
			}
		hm.put(state, canWin);
		return canWin;
	}

	private int getState()
	{
		int mul = 1, res = 0;
		for (int i = 0; i < used.length; i++)
		{
			if (used[i])
				res |= mul;
			mul <<= 1;
		}
		return res;
	}
}

//https://leetcode.com/problems/unique-substrings-in-wraparound-string/discuss/172428/Java-O(n)-time-O(1)-space
//Runtime: 6 ms, faster than 99.69% of Java online submissions for Unique Substrings in Wraparound String.
//Memory Usage: 37.5 MB, less than 18.52% of Java online submissions for Unique Substrings in Wraparound String.
class Solution467
{
	public int findSubstringInWraproundString(String p)
	{
		int[] maxlen = new int[26];
		int i = 0;
		while (i < p.length())
		{
			int j = i + 1;
			while (j < p.length()
					&& (p.charAt(j - 1) + 1 == (int) p.charAt(j) || p.charAt(j - 1) == 'z' && p.charAt(j) == 'a'))
			{
				j++;
			}
			for (; i < j; i++)
			{
				if (j - i > maxlen[p.charAt(i) - 'a'])
					maxlen[p.charAt(i) - 'a'] = j - i;
			}
		}
		int tot = 0;
		for (i = 0; i < 26; i++)
			tot += maxlen[i];
		return tot;
	}
}

class SolBase
{
	Random rd = new Random();

	public int rand7()
	{
		return rd.nextInt(7) + 1;
	}
}

//470. Implement Rand10() Using Rand7()
//Runtime: 969 ms, faster than 5.20% of Java online submissions for Implement Rand10() Using Rand7().
//Memory Usage: 39.6 MB, less than 12.50% of Java online submissions for Implement Rand10() Using Rand7().
class Solution470 extends SolBase
{
	final static double[] invcdfv = new double[]
			{ -1.281551565544601, -0.841621233572914, -0.524400512708041, -0.253347103135800, 0, 0.253347103135800,
					0.524400512708041, 0.841621233572914, 1.281551565544601 };

	public int rand10()
	{
		int n = 1000;
		double x = 0;
		for (int i = 0; i < n; i++)
			x += rand7();
		x /= n;
		x -= 4;
		x = x / 2 * Math.sqrt(n);
		for (int i = 0; i < 9; i++)
			if (x <= invcdfv[i])
				return i + 1;
		return 10;
	}
}

//Runtime: 33 ms, faster than 5.20% of Java online submissions for Implement Rand10() Using Rand7().
//Memory Usage: 39.8 MB, less than 7.50% of Java online submissions for Implement Rand10() Using Rand7().
class Solution470_2 extends SolBase
{
	public int rand10()
	{
		double x = 0;
		double p = 1.0 / 7;
		double y = 0;
		for (int i = 0; i < 30; i++)
		{
			y += 6 * p;
			x += (rand7() - 1) * p;
			p /= 7;
		}
		x /= y;
		double s = 0.1;
		for (int i = 1; i <= 10; i++)
		{
			if (x <= s)
				return i;
			s += 0.1;
		}
		return 10;
	}
}

//Runtime: 14 ms, faster than 10.04% of Java online submissions for Implement Rand10() Using Rand7().
//Memory Usage: 39.5 MB, less than 20.00% of Java online submissions for Implement Rand10() Using Rand7().
class Solution470_3 extends SolBase
{
	public int rand10()
	{
		double x = 0;
		double p = 1.0 / 7;
		double y = 0;
		for (int i = 0; i < 10; i++)
		{
			y += 6 * p;
			x += (rand7() - 1) * p;
			p /= 7;
		}
		x /= y;
		double s = 0.1;
		for (int i = 1; i <= 10; i++)
		{
			if (x <= s)
				return i;
			s += 0.1;
		}
		return 10;
	}
}

//Runtime: 14 ms, faster than 10.04% of Java online submissions for Implement Rand10() Using Rand7().
//Memory Usage: 39.4 MB, less than 22.50% of Java online submissions for Implement Rand10() Using Rand7().
class Solution470_4 extends SolBase
{
	public int rand10()
	{
		int result = 11;
		while (result > 10)
		{
			result = 7 * (rand7() - 1) + rand7();
		}

		return result;
	}
}

// Runtime: 3 ms, faster than 100.00% of Java online submissions for Implement Rand10() Using Rand7().
// Memory Usage: 39.3 MB, less than 37.50% of Java online submissions for Implement Rand10() Using Rand7().
class Solution470_5 extends SolBase
{
	int cur = 0;

	public int rand10()
	{
		cur += rand7();
		cur %= 10;
		return cur + 1;
	}
}

public class LC461_470
{
	public static void test470()
	{
		int testNum = 100000;
		int[] ct = new int[10];
		Solution470_5 a = new Solution470_5();
		for (int i = 0; i < testNum; i++)
			ct[a.rand10() - 1]++;
		for (int i = 0; i < 10; i++)
			System.out.print((i + 1) + " " + ct[i] + " ");
	}

	public static void main(String[] args)
	{
		test470();
	}
}
