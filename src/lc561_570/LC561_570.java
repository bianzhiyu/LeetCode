package lc561_570;

import java.util.Arrays;

import treeCodec.*;

//561. Array Partition I
//Runtime: 22 ms, faster than 59.30% of Java online submissions for Array Partition I.
//Memory Usage: 41.4 MB, less than 68.50% of Java online submissions for Array Partition I.
class Solution561
{
	public int arrayPairSum(int[] nums)
	{
		Arrays.parallelSort(nums);
		int tot = 0;
		for (int i = 0; i < nums.length; i += 2)
			tot += nums[i];
		return tot;
	}
}

//563. Binary Tree Tilt
//Runtime: 1 ms, faster than 49.26% of Java online submissions for Binary Tree Tilt.
//Memory Usage: 39 MB, less than 84.30% of Java online submissions for Binary Tree Tilt.
class Solution563
{
	private static class Res
	{
		public int tiltTree = 0, nodeSum = 0;
	}

	private Res trav(TreeNode root)
	{
		if (root == null)
			return new Res();
		Res L = trav(root.left), R = trav(root.right);
		Res r = new Res();
		r.nodeSum = L.nodeSum + R.nodeSum + root.val;
		int tileNode = Math.abs(L.nodeSum - R.nodeSum);
		r.tiltTree = tileNode + L.tiltTree + R.tiltTree;
		return r;
	}

	public int findTilt(TreeNode root)
	{
		return trav(root).tiltTree;
	}
}

//565. Array Nesting
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Array Nesting.
//Memory Usage: 42.6 MB, less than 69.83% of Java online submissions for Array Nesting.
class Solution565
{
	public int arrayNesting(int[] nums)
	{
		int len = nums.length;
		boolean[] used = new boolean[len];
		int max = 0;
		for (int i = 0; i < len; i++)
			if (!used[i])
			{
				int state = i, ct = 0;
				while (!used[state])
				{
					used[state] = true;
					ct++;
					state = nums[state];
				}
				if (ct > max)
					max = ct;
			}
		return max;
	}
}

//566. Reshape the Matrix
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Reshape the Matrix.
//Memory Usage: 38.6 MB, less than 100.00% of Java online submissions for Reshape the Matrix.
class Solution566
{
	public int[][] matrixReshape(int[][] nums, int r, int c)
	{
		if (nums.length * nums[0].length != r * c)
		{
			return nums;
		}
		int[][] nm = new int[r][c];
		int p = 0, C = nums[0].length;
		for (int i = 0; i < r; i++)
		{
			for (int j = 0; j < c; j++)
			{
				nm[i][j] = nums[p / C][p % C];
				p++;
			}
		}
		return nm;
	}
}

//567. Permutation in String
//Runtime: 6 ms, faster than 99.82% of Java online submissions for Permutation in String.
//Memory Usage: 37.6 MB, less than 87.96% of Java online submissions for Permutation in String.
class Solution567
{
	public boolean checkInclusion(String s1, String s2)
	{
		if (s1.length() == 0)
			return true;
		if (s2.length() < s1.length())
			return false;
		int[] grp = new int[26];
		int len1 = s1.length();
		for (int i = 0; i < len1; i++)
			grp[s1.charAt(i) - 'a']++;
		int[] ct = new int[26];
		for (int i = 0; i < len1 - 1; i++)
			ct[s2.charAt(i) - 'a']++;
		int unmatched = 0;
		for (int i = 0; i < 26; i++)
			if (ct[i] != grp[i])
				unmatched++;
		for (int i = len1 - 1; i < s2.length(); i++)
		{
			int c = s2.charAt(i) - 'a';
			ct[c]++;
			if (ct[c] == grp[c])
				unmatched--;
			else if (ct[c] == grp[c] + 1)
				unmatched++;
			if (unmatched == 0)
				return true;
			c = s2.charAt(i + 1 - len1) - 'a';
			ct[c]--;
			if (ct[c] == grp[c])
				unmatched--;
			else if (ct[c] == grp[c] - 1)
				unmatched++;
		}
		return false;
	}
}

public class LC561_570
{
	public static void main(String[] args)
	{

	}
}
