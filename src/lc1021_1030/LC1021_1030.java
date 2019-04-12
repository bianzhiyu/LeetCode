package lc1021_1030;

import treeCodec.*;

//1022. Sum of Root To Leaf Binary Numbers
class Solution1022
{
	private static class Summer
	{
		public int s = 0;
	}

	private void trav(TreeNode rt, int pre, Summer s)
	{
		pre = (pre << 1) + rt.val;
		if (rt.left == null && rt.right == null)
		{
			s.s += pre;
			return;
		}
		if (rt.left != null)
			trav(rt.left, pre, s);
		if (rt.right != null)
			trav(rt.right, pre, s);
	}

	public int sumRootToLeaf(TreeNode root)
	{
		Summer s = new Summer();
		if (root == null)
			return 0;
		trav(root, 0, s);
		return s.s;
	}
}

public class LC1021_1030
{
	public static void main(String[] args)
	{
	}
}
