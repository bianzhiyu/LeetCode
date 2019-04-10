package lc251_260;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import treeCodec.TreeNode;

//257. Binary Tree Paths
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Binary Tree Paths.
//Memory Usage: 37.3 MB, less than 62.66% of Java online submissions for Binary Tree Paths.
class Solution257
{
	private void trav(TreeNode rt, List<String> ans, List<Integer> stack, int st)
	{
		stack.add(rt.val);
		if (rt.left == null && rt.right == null)
		{
			StringBuilder sb = new StringBuilder();
			sb.append(stack.get(0));
			for (int i = 1; i <= st; i++)
				sb.append("->").append(stack.get(i));
			ans.add(sb.toString());
			stack.remove(st);
			return;
		}
		if (rt.left != null)
			trav(rt.left, ans, stack, st + 1);
		if (rt.right != null)
			trav(rt.right, ans, stack, st + 1);
		stack.remove(st);
	}

	public List<String> binaryTreePaths(TreeNode root)
	{
		List<String> ans = new ArrayList<String>();
		if (root == null)
			return ans;
		trav(root, ans, new ArrayList<Integer>(), 0);
		return ans;
	}
}

//258. Add Digits
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Add Digits.
//Memory Usage: 35 MB, less than 61.06% of Java online submissions for Add Digits.
class Solution258
{
	public int addDigits(int num)
	{
		while (num >= 10)
		{
			int t = 0;
			while (num > 0)
			{
				t += num % 10;
				num /= 10;
			}
			num = t;
		}
		return num;
	}
}

//260. Single Number III
//Runtime: 6 ms, faster than 22.22% of Java online submissions for Single Number III.
//Memory Usage: 38.1 MB, less than 69.12% of Java online submissions for Single Number III.
class Solution260
{
	public int[] singleNumber(int[] nums)
	{
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++)
			if (hm.get(nums[i]) == null)
				hm.put(nums[i], 1);
			else
				hm.put(nums[i], hm.get(nums[i]) + 1);
		int sp = 0;
		int[] ans = new int[2];
		for (int i : hm.keySet())
			if (hm.get(i) == 1)
				ans[sp++] = i;
		return ans;
	}
}

public class LC251_260
{
	public static void main(String[] args)
	{

	}
}
