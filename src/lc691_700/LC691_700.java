package lc691_700;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//692. Top K Frequent Words
//Runtime: 8 ms, faster than 100.00% of Java online submissions for Top K Frequent Words.
//Memory Usage: 40.3 MB, less than 49.87% of Java online submissions for Top K Frequent Words.

class Solution692
{
	private static class Trie
	{
		int f = 0;
		Trie[] s = new Trie[26];
	}

	private static class P692 implements Comparable<P692>
	{
		String wd;
		int fq;

		@Override
		public int compareTo(P692 o)
		{
			if (fq != o.fq)
				return o.fq - fq;
			else
				return wd.compareTo(o.wd);
		}

		public P692(String w, int f)
		{
			wd = w;
			fq = f;
		}
	}

	void insert(Trie rt, String s)
	{
		int l = s.length(), c;
		for (int i = 0; i < l; i++)
		{
			c = s.charAt(i) - 'a';
			if (rt.s[c] == null)
				rt.s[c] = new Trie();
			rt = rt.s[c];
		}
		rt.f++;
	}

	void Trav(Trie rt, List<P692> l, String pref)
	{
		if (rt.f > 0)
			l.add(new P692(pref, rt.f));
		for (int i = 0; i < 26; i++)
			if (rt.s[i] != null)
				Trav(rt.s[i], l, pref + ((char) (i + 'a')));
	}

	public List<String> topKFrequent(String[] words, int k)
	{
		Trie rt = new Trie();
		for (int i = 0; i < words.length; i++)
			insert(rt, words[i]);
		List<P692> l = new ArrayList<P692>();
		Trav(rt, l, "");
		Collections.sort(l);
		List<String> ans = new ArrayList<String>();
		for (int i = 0; i < k; i++)
			ans.add(l.get(i).wd);
		return ans;
	}
}

//695. Max Area of Island
//Runtime: 15 ms, faster than 86.38% of Java online submissions for Max Area of Island.
//Memory Usage: 46.6 MB, less than 10.23% of Java online submissions for Max Area of Island.
class Solution695
{
	final static int[][] di = new int[][]
	{
			{ 0, 1 },
			{ 1, 0 },
			{ 0, -1 },
			{ -1, 0 } };

	public int maxAreaOfIsland(int[][] grid)
	{
		if (grid.length == 0 || grid[0].length == 0)
			return 0;
		int n1 = grid.length, n2 = grid[0].length;
		boolean[][] used = new boolean[n1][n2];
		int[] qx = new int[n1 * n2];
		int[] qy = new int[n1 * n2];
		int max = 0, f, r, nx, ny;
		for (int i = 0; i < n1; i++)
			for (int j = 0; j < n2; j++)
				if (grid[i][j] == 1 && !used[i][j])
				{
					qx[0] = i;
					qy[0] = j;
					used[i][j] = true;
					f = 0;
					r = 1;
					while (f < r)
					{
						for (int k = 0; k < 4; k++)
						{
							nx = qx[f] + di[k][0];
							ny = qy[f] + di[k][1];
							if (nx >= 0 && ny >= 0 && nx < n1 && ny < n2 && !used[nx][ny] && grid[nx][ny] == 1)
							{
								qx[r] = nx;
								qy[r] = ny;
								used[nx][ny] = true;
								r++;
							}
						}
						f++;
					}
					if (r > max)
						max = r;
				}
		return max;
	}
}

//Wrong Answer
class Solution698
{
	public boolean canPartitionKSubsets(int[] nums, int k)
	{
		if (k == 1)
			return true;
		int s = 0;
		for (int i = 0; i < nums.length; i++)
			s += nums[i];
		if (s % k != 0)
			return false;
		s /= k;
		boolean[] d = new boolean[s + 1];
		boolean[] used = new boolean[nums.length];
		int[] complement = new int[s + 1];
		for (int i = 0; i < k; i++)
		{
			Arrays.fill(d, false);
			Arrays.fill(complement, -1);
			d[0] = true;
			boolean fd = false;
			for (int j = 0; j < nums.length; j++)
			{
				if (used[j])
					continue;
				for (int u = s; u >= nums[j]; u--)
				{
					if (d[u - nums[j]] && !d[u])
					{
						d[u] = true;
						complement[u] = j;
					}
				}
				if (d[s])
				{
					fd = true;
					break;
				}
			}
			if (!fd)
				return false;
			int tot = s;
			while (tot > 0)
			{
				used[complement[tot]] = true;
				tot = tot - nums[complement[tot]];
			}
			for (int j = 0; j < nums.length; j++)
				if (used[j])
					System.out.print(nums[j] + " ");
			System.out.println();
		}
		return true;
	}
}

//698. Partition to K Equal Sum Subsets
//Runtime: 5 ms, faster than 96.91% of Java online submissions for Partition to K Equal Sum Subsets.
//Memory Usage: 37.2 MB, less than 75.42% of Java online submissions for Partition to K Equal Sum Subsets.
class Solution698_3
{
	public boolean canPartitionKSubsets(int[] nums, int k)
	{
		int sum = 0;
		for (int i : nums)
			sum += i;
		if (sum % k != 0)
			return false;
		boolean[] isVisited = new boolean[nums.length];
		Arrays.sort(nums);
		return dfs(nums, k, nums.length - 1, 0, sum / k, isVisited);
	}

	private boolean dfs(int[] nums, int k, int index, int sum, int target, boolean[] isVisited)
	{
		if (k == 0)
			return true;
		if (sum == target)
		{
			return dfs(nums, k - 1, nums.length - 1, 0, target, isVisited);
		}
		for (int i = index; i >= 0; i--)
		{
			if (isVisited[i] || nums[i] + sum > target)
				continue;
			isVisited[i] = true;
			if (dfs(nums, k, i - 1, sum + nums[i], target, isVisited))
				return true;
			isVisited[i] = false;
		}
		return false;
	}
}

//Runtime: 5 ms, faster than 96.91% of Java online submissions for Partition to K Equal Sum Subsets.
//Memory Usage: 37.2 MB, less than 78.81% of Java online submissions for Partition to K Equal Sum Subsets.
class Solution698_2
{
	int[] n;
	int s;
	boolean[] used;

	public boolean canPartitionKSubsets(int[] nums, int k)
	{
		s = 0;
		for (int i = 0; i < nums.length; i++)
			s += nums[i];
		if (s % k != 0)
			return false;
		s /= k;
		n = nums;
		used = new boolean[nums.length];
		Arrays.parallelSort(n);
		return dfs(k, s, n.length - 1);
	}

	private boolean dfs(int k, int sum, int maxRange)
	{
		if (k == 0)
			return true;
		if (sum == 0)
			return dfs(k - 1, s, n.length - 1);
		for (int i = maxRange; i >= 0; i--)
			if (!used[i] && sum >= n[i])
			{
				used[i] = true;
				if (dfs(k, sum - n[i], i - 1))
					return true;
				used[i] = false;
			}
		return false;
	}
}

//TLE
class Solution698_4
{
	int[] n;
	int s;
	boolean[] used;

	public boolean canPartitionKSubsets(int[] nums, int k)
	{
		s = 0;
		for (int i = 0; i < nums.length; i++)
			s += nums[i];
		if (s % k != 0)
			return false;
		s /= k;
		n = nums;
		used = new boolean[nums.length];
		Arrays.parallelSort(n);
		return dfs(k, s);
	}

	private boolean dfs(int k, int sum)
	{
		if (k == 0)
			return true;
		if (sum == 0)
			return dfs(k - 1, s);
		for (int i = n.length - 1; i >= 0; i--)
			if (!used[i] && sum >= n[i])
			{
				used[i] = true;
				if (dfs(k, sum - n[i]))
					return true;
				used[i] = false;
			}
		return false;
	}
}

public class LC691_700
{
	public static void main(String[] args)
	{
		int[] n = new int[]
		{ 10, 10, 10, 7, 7, 7, 7, 7, 7, 6, 6, 6 };
		Solution698_3 s = new Solution698_3();
		System.out.println(s.canPartitionKSubsets(n, 3));
	}

}
