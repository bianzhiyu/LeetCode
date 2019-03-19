package lc111_120;

import java.util.ArrayList;
import java.util.List;
import treeCodec.*;

//111. Minimum Depth of Binary Tree
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Minimum Depth of Binary Tree.
//Memory Usage: 37.6 MB, less than 100.00% of Java online submissions for Minimum Depth of Binary Tree.
class Solution111
{
	int mind = Integer.MAX_VALUE;

	void Trav(TreeNode r, int d)
	{
		if (r.left == null && r.right == null && d < mind)
			mind = d;
		if (r.left != null)
			Trav(r.left, d + 1);
		if (r.right != null)
			Trav(r.right, d + 1);
	}

	public int minDepth(TreeNode root)
	{
		if (root == null)
			return 0;
		Trav(root, 1);
		return mind;
	}
}

//112. Path Sum
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Path Sum.
//Memory Usage: 38.4 MB, less than 100.00% of Java online submissions for Path Sum.
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class Solution112
{
	boolean check(TreeNode r, int sum, int ps)
	{
		if (r.left == null && r.right == null)
		{
			return ps + r.val == sum;
		}
		if (r.left != null && check(r.left, sum, ps + r.val))
			return true;
		if (r.right != null && check(r.right, sum, ps + r.val))
			return true;
		return false;
	}

	public boolean hasPathSum(TreeNode root, int sum)
	{
		if (root == null)
			return false;
		return check(root, sum, 0);
	}
}

//113. Path Sum II
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Path Sum II.
//Memory Usage: 40.3 MB, less than 100.00% of Java online submissions for Path Sum II.
class Solution113
{
	List<List<Integer>> ans;
	int[] Stack = new int[10000];

	void check(TreeNode r, int sum, int ps, int sp)
	{
		if (r.left == null && r.right == null)
		{
			if (ps + r.val == sum)
			{
				List<Integer> t = new ArrayList<Integer>();
				for (int i = 0; i < sp; i++)
					t.add(Stack[i]);
				t.add(r.val);
				ans.add(t);
			}
			return;
		}
		Stack[sp] = r.val;
		if (r.left != null)
			check(r.left, sum, ps + r.val, sp + 1);
		if (r.right != null)
			check(r.right, sum, ps + r.val, sp + 1);
	}

	public List<List<Integer>> pathSum(TreeNode root, int sum)
	{
		ans = new ArrayList<List<Integer>>();
		if (root == null)
			return ans;
		check(root, sum, 0, 0);
		return ans;
	}
}

//114. Flatten Binary Tree to Linked List
//Runtime: 6 ms, faster than 99.92% of Java online submissions for Flatten Binary Tree to Linked List.
//Memory Usage: 38.3 MB, less than 5.09% of Java online submissions for Flatten Binary Tree to Linked List.
class Solution114
{
	public void flatten(TreeNode root)
	{
		if (root==null) return;
		if (root.left==null && root.right==null) return;
		flatten(root.left);
		flatten(root.right);
		TreeNode p=root.right;
		root.right=root.left;
		root.left=null;
		while (root.right!=null) root=root.right;
		root.right=p;
	}
}

//t115. Distinct Subsequences
//t=2346, m=37MB
//t:0.01, m:1
class Solution115
{
	public int numDistinct(String s, String t)
	{
		char[] sca = s.toCharArray();
		char[] tca = t.toCharArray();
		int ls = sca.length, lt = tca.length;
		int[][] d = new int[lt + 1][ls + 1];
		d[1][0] = 0;
		for (int j = 1; j <= ls; j++)
			d[1][j] = d[1][j - 1] + (sca[j - 1] == tca[0] ? 1 : 0);
		for (int i = 2; i <= lt; i++)
			for (int j = i; j <= ls; j++)
				for (int k = 0; k < j; k++)
					d[i][j] += sca[k] == tca[i - 1] ? d[i - 1][k] : 0;
		return d[lt][ls];
	}
}

//Right answer, but TLE.
//O(T)=ls^lt
class Solution115_2
{
	char[] sca, tca;
	int ls, lt, ct;
	int[] stack;

	public void dfs(int sp, int start)
	{
		if (sp == lt)
		{
			ct++;
			return;
		}
		for (int i = start; i < ls - lt + sp + 1; i++)
		{
			if (sca[i] == tca[sp])
			{
				stack[sp] = i;
				dfs(sp + 1, i + 1);
			}
		}
	}

	public int numDistinct(String s, String t)
	{
		sca = s.toCharArray();
		tca = t.toCharArray();
		ls = sca.length;
		lt = tca.length;
		ct = 0;
		stack = new int[lt];
		dfs(0, 0);
		return ct;
	}
}

//
//With the same thoughts in Solution 115_1
//O(T): ls^2*lt -> ls*lt
//see ref:https://leetcode.com/problems/distinct-subsequences/discuss/37327/Easy-to-understand-DP-in-Java
//t=3ms, m=37MB
//t:0.9921, m:1
class Solution115_4
{
	public int numDistinct(String s, String t)
	{
		char[] sca = s.toCharArray();
		char[] tca = t.toCharArray();
		int ls = sca.length, lt = tca.length;
		int[][] d = new int[lt + 1][ls + 1];
		d[1][0] = 0;
		for (int j = 1; j <= ls; j++)
			d[1][j] = d[1][j - 1] + (sca[j - 1] == tca[0] ? 1 : 0);
		for (int i = 2; i <= lt; i++)
			for (int j = i; j <= ls; j++)
				d[i][j] = d[i][j - 1] + // s[j-1] deleted
						(sca[j - 1] == tca[i - 1] ? d[i - 1][j - 1] : 0);// s[j-1] included
		return d[lt][ls];
	}
}

//116. Populating Next Right Pointers in Each Node
//Runtime: 1 ms, faster than 39.05% of Java online submissions for Populating Next Right Pointers in Each Node.
//Memory Usage: 35.5 MB, less than 100.00% of Java online submissions for Populating Next Right Pointers in Each Node.
class Node
{
	public int val;
	public Node left;
	public Node right;
	public Node next;

	public Node()
	{
	}

	public Node(int _val, Node _left, Node _right, Node _next)
	{
		val = _val;
		left = _left;
		right = _right;
		next = _next;
	}
};

class Solution116
{
	void Trav(Node r)
	{
		r.left.next = r.right;
		r.right.next = r.next == null ? null : r.next.left;
		if (r.left.left != null)
		{
			Trav(r.left);
			Trav(r.right);
		}
	}

	public Node connect(Node root)
	{
		if (root == null)
			return null;
		root.next = null;
		if (root.left != null)
			Trav(root);
		return root;

	}
}

//117. Populating Next Right Pointers in Each Node II
//Runtime: 3 ms, faster than 18.40% of Java online submissions for Populating Next Right Pointers in Each Node II.
//Memory Usage: 55.6 MB, less than 100.00% of Java online submissions for Populating Next Right Pointers in Each Node II.
class Solution117
{
	Node getSon(Node x)
	{
		if (x == null)
			return null;
		if (x.left != null)
			return x.left;
		if (x.right != null)
			return x.right;
		return getSon(x.next);
	}

	void Trav(Node r)
	{
		if (r.left != null && r.right != null)
		{
			r.left.next = r.right;
			r.right.next = getSon(r.next);
			Trav(r.right);
			Trav(r.left);
		}
		if (r.left != null && r.right == null)
		{
			r.left.next = getSon(r.next);
			Trav(r.left);
		}
		if (r.left == null && r.right != null)
		{
			r.right.next = getSon(r.next);
			Trav(r.right);
		}
	}

	public Node connect(Node root)
	{
		if (root == null)
			return null;
		root.next = null;
		Trav(root);
		return root;

	}
}

//118. Pascal's Triangle
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Pascal's Triangle.
//Memory Usage: 35.4 MB, less than 100.00% of Java online submissions for Pascal's Triangle.
class Solution118
{
	public List<List<Integer>> generate(int numRows)
	{
		List<List<Integer>> ans = new ArrayList<List<Integer>>();
		if (numRows == 0)
			return ans;
		List<Integer> t = new ArrayList<Integer>(), pre;
		t.add(1);
		ans.add(t);
		for (int i = 1; i < numRows; i++)
		{
			t = new ArrayList<Integer>();
			pre = ans.get(ans.size() - 1);
			t.add(1);
			for (int j = 1; j <= i; j++)
				if (j < i)
					t.add(pre.get(j) + pre.get(j - 1));
				else
					t.add(1);
			ans.add(t);
		}
		return ans;
	}
}

//119. Pascal's Triangle II
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Pascal's Triangle II.
//Memory Usage: 35 MB, less than 100.00% of Java online submissions for Pascal's Triangle II.
class Solution119
{
	public List<Integer> getRow(int rowIndex)
	{
		int[][] a = new int[2][rowIndex + 2];
		a[0][0] = 1;
		int pre = 0;
		for (int i = 1; i <= rowIndex; i++)
		{
			a[1 - pre][0] = 1;
			for (int j = 1; j <= i; j++)
				a[1 - pre][j] = a[pre][j - 1] + a[pre][j];
			pre = 1 - pre;
		}
		List<Integer> t = new ArrayList<Integer>();
		for (int i = 0; i <= rowIndex; i++)
			t.add(a[pre][i]);
		return t;
	}
}

//120. Triangle
//Runtime: 7 ms, faster than 37.99% of Java online submissions for Triangle.
//Memory Usage: 38.5 MB, less than 30.07% of Java online submissions for Triangle.
class Solution120
{
	public int minimumTotal(List<List<Integer>> triangle)
	{
		int l = triangle.size();
		for (int i = 1; i < l; i++)
		{
			triangle.get(i).set(0, triangle.get(i - 1).get(0) + triangle.get(i).get(0));
			for (int j = 1; j < triangle.get(i).size() - 1; j++)
				triangle.get(i).set(j,
						Math.min(triangle.get(i - 1).get(j - 1), triangle.get(i - 1).get(j)) + triangle.get(i).get(j));
			triangle.get(i).set(i, triangle.get(i).get(i) + triangle.get(i - 1).get(i - 1));
		}
		int min = Integer.MAX_VALUE;
		for (int j = 0; j < l; j++)
			if (triangle.get(l - 1).get(j) < min)
				min = triangle.get(l - 1).get(j);
		return min;
	}
}

public class LC111_120
{
	public static void main(String[] args)
	{
		
		String in="[1,2,5,3,4,null,6]";
		System.out.println(in);
		TreeNode r=TreeCodec.deserialize(in);
		System.out.println(TreeCodec.serialize(r));
		
		Solution114 s=new Solution114();
		s.flatten(r);
		
		System.out.println(TreeCodec.serialize(r));
		
	}
}
