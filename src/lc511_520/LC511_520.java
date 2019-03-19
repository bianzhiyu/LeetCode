package lc511_520;

import java.util.ArrayList;
import java.util.List;

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

class TreeNode
{
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x)
	{
		val = x;
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
		dfs(root,0,ans);
		return ans;
	}
	void dfs(TreeNode rt,int layer,List<Integer> ans)
	{
		if (rt==null) return;
		if (ans.size()<=layer) ans.add(rt.val);
		dfs(rt.left,layer+1,ans);
		dfs(rt.right,layer+1,ans);
		if (rt.val>ans.get(layer)) ans.set(layer, rt.val);
	}
}

//517. Super Washing Machines
//https://leetcode.com/problems/super-washing-machines/discuss/235584/Explanation-of-Java-O(n)-solution
//Runtime: 5 ms, faster than 99.15% of Java online submissions for Super Washing Machines.
//Memory Usage: 39.8 MB, less than 100.00% of Java online submissions for Super Washing Machines.
class Solution517_2
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

public class LC511_520
{
	public static void main(String args[])
	{
		Solution514 s = new Solution514();
		String ring = "godding";
		String key = "gd";
		System.out.println(s.dt(0, 4, 5));
		System.out.println(s.findRotateSteps(ring, key));
	}

}
