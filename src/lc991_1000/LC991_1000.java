package lc991_1000;

import java.util.Arrays;

//996. Number of Squareful Arrays
//Runtime: 12818 ms, faster than 5.09% of Java online submissions for Number of Squareful Arrays.
//Memory Usage: 37.8 MB, less than 13.76% of Java online submissions for Number of Squareful Arrays.
class Solution996
{
	static boolean issq(int x)
	{
		double t = Math.sqrt(x);
		return t == (int) t;
	}

	public int numSquarefulPerms(int[] nums)
	{
		int len = nums.length, tot = 0;
		Arrays.sort(nums);
		boolean fd, succ;
		int ind = 0, ind2 = 0, tmp;
		while (true)
		{
			// check:
			succ = true;
			for (int i = 0; i < len - 1; i++)
				if (!issq(nums[i] + nums[i + 1]))
				{
					succ = false;
					break;
				}
			if (succ)
				tot++;
			// generate the next:
			fd = false;
			for (int i = len - 1; i > 0; i--)
				if (nums[i] > nums[i - 1])
				{
					fd = true;
					ind = i - 1;
					ind2 = i;
					break;
				}
			if (!fd)
				break;
			for (int i = ind + 2; i < len; i++)
				if (nums[i] > nums[ind] && nums[i] < nums[ind2])
					ind2 = i;
			tmp = nums[ind];
			nums[ind] = nums[ind2];
			nums[ind2] = tmp;
			Arrays.sort(nums, ind + 1, len);
		}
		return tot;
	}
}


//generate permutation fastly:
//Runtime: 3 ms, faster than 99.47% of Java online submissions for Number of Squareful Arrays.
//Memory Usage: 37 MB, less than 28.48% of Java online submissions for Number of Squareful Arrays.
//https://leetcode.com/problems/number-of-squareful-arrays/discuss/244838/Java3ms-Easy-Understand-Backtracking-Short-Solution
class Solution996_2
{
	public int numSquarefulPerms(int[] A)
	{
		Arrays.sort(A);
		return backtrack(0, 0,new int[A.length], A, new boolean[A.length]);
	}

	private int backtrack(int res,int sp,int[] st, int[] A, boolean[] used)
	{
		if (sp == A.length) return ++res;
		for (int i = 0; i < A.length; i++)
		{
			if (used[i] 
				|| i > 0 && A[i] == A[i - 1] && !used[i - 1]
				|| sp>0 && !isSquareful(st[sp-1]+A[i]))
				continue;
			used[i] = true;
			st[sp]=A[i];
			res = backtrack(res,sp+1,st,A,used);
			used[i] = false;
		}

		return res;
	}

	private boolean isSquareful(int x)
	{
		double t=Math.sqrt(x);
		return t == (int) t;
	}
}

public class LC991_1000
{
	public static void main(String[] args)
	{
		int[] a = new int[]
				{ 2,2};
		System.out.println(new Solution996_2().numSquarefulPerms(a));
	}

}
