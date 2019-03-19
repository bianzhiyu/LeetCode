package lc961_970;

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

//968. Binary Tree Cameras
//I tried to solve this upside to downside, which is very redundant.
//This method downside to upside is more clear and comprehensive.
//Runtime: 5 ms, faster than 98.47% of Java online submissions for Binary Tree Cameras.
//Memory Usage: 40.6 MB, less than 45.45% of Java online submissions for Binary Tree Cameras.
//https://leetcode.com/problems/binary-tree-cameras/discuss/211180/JavaC%2B%2BPython-Greedy-DFS
class Solution968
{
	int tot=0;
	public int minCameraCover(TreeNode root)
	{
		return (dfs(root) < 1 ? 1 : 0) + tot;
	}
	
	int dfs(TreeNode rt)
	{
		if (rt==null) return 2;
		int left=dfs(rt.left),right=dfs(rt.right);
		if (left==0 || right==0)
		{
			tot++;
			return 1;
		}
		if (left==1 || right==1) return 2;
		//otherwise: left==2 && right==2
		return 0;
	}
}

public class LC961_970
{
	public static void main(String[] args)
	{
//		int a=2;
		System.out.println();
	}
}
