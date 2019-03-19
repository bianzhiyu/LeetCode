package lc471_480;

import java.util.Arrays;

//473. Matchsticks to Square
//Just use the code of 698. Partition to K Equal Sum Subsets
//Runtime: 14 ms, faster than 88.57% of Java online submissions for Matchsticks to Square.
//Memory Usage: 37.3 MB, less than 78.13% of Java online submissions for Matchsticks to Square.
class Solution473
{
	//above are code from 698.
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
//Below are codes from 698
	public boolean makesquare(int[] nums)
	{
		if (nums.length == 0)
			return false;
		return canPartitionKSubsets(nums, 4);
	}
}

public class LC471_480
{

}
