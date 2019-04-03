package lc101_110;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class ListNode
{
	int val;
	ListNode next;

	ListNode(int x)
	{
		val = x;
	}
}

//101. Symmetric Tree
//Answer is right, but
//Not enough memory, or
//Memory Limit Exceeded
class Solution101
{
	final static int MaxNodeNum = 100000;
	int[] tarr = new int[MaxNodeNum];
	boolean[] exi = new boolean[MaxNodeNum];
	int maxL = -1;

	void trav(TreeNode r, int l, int x)
	{
		if (l > maxL)
			maxL = l;
		exi[x] = true;
		tarr[x] = r.val;
		if (r.left != null)
			trav(r.left, l + 1, 2 * x);
		if (r.right != null)
			trav(r.right, l + 1, 2 * x + 1);
	}

	public boolean isSymmetric(TreeNode root)
	{
		if (root == null)
			return true;
		trav(root, 0, 1);

		int start = 1;
		for (int i = 1; i <= maxL; i++)
		{
			start = start << 1;
			for (int j = 0; j < start / 2; j++)
			{
				if (exi[start + j] && !exi[start * 2 - 1 - j] || !exi[start + j] && exi[start * 2 - 1 - j])
					return false;
				if (exi[start + j] && tarr[start + j] != tarr[start * 2 - 1 - j])
					return false;
			}
		}
		return true;
	}
}

//https://leetcode.com/problems/symmetric-tree/discuss/235923/6ms-Java-with-simple-explanation
//Runtime: 6 ms, faster than 94.60% of Java online submissions for Symmetric Tree.
//Memory Usage: 38.1 MB, less than 100.00% of Java online submissions for Symmetric Tree.
class Solution101_2
{
	public boolean isSymmetric(TreeNode root)
	{
		// 1. Top empty, return symmetric!
		if (root == null)
			return true;

		// 2. Recursion method: Single null always symm, so just put kids in.
		return helper(root.left, root.right);
	}

	public boolean helper(TreeNode left, TreeNode right)
	{
		// 3. Both must be null, or false
		if (left == null || right == null)
			return left == right;

		// 4. Check values and next level of children
		return left.val == right.val && helper(left.left, right.right) && helper(left.right, right.left);
	}
}

//Similar thoughtw in 101_2
//Runtime: 5 ms, faster than 100.00% of Java online submissions for Symmetric Tree.
//Memory Usage: 40.2 MB, less than 100.00% of Java online submissions for Symmetric Tree.
class Solution101_3
{
	public boolean isSymmetric(TreeNode root)
	{
		if (root == null)
			return true;
		return check(root, root);
	}

	/**
	 * Check whether t1 is a reflection of t2.
	 */
	boolean check(TreeNode t1, TreeNode t2)
	{
		if (t1 == null || t2 == null)
			return t1 == t2;
		return t1.val == t2.val && check(t1.left, t2.right) && check(t1.right, t2.left);
	}
}
//102. Binary Tree Level Order Traversal
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Level Order Traversal.
//Memory Usage: 37.4 MB, less than 100.00% of Java online submissions for Binary Tree Level Order Traversal.

class Solution102
{
	List<List<Integer>> ans;

	void Trav(TreeNode r, int layer)
	{
		if (ans.size() == layer)
		{
			ans.add(new ArrayList<Integer>());
		}
		ans.get(layer).add(r.val);
		if (r.left != null)
			Trav(r.left, layer + 1);
		if (r.right != null)
			Trav(r.right, layer + 1);
	}

	public List<List<Integer>> levelOrder(TreeNode root)
	{
		ans = new ArrayList<List<Integer>>();
		if (root == null)
			return ans;
		Trav(root, 0);
		return ans;
	}
}

//103. Binary Tree Zigzag Level Order Traversal
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Zigzag Level Order Traversal.
//Memory Usage: 37.2 MB, less than 100.00% of Java online submissions for Binary Tree Zigzag Level Order Traversal.
class Solution103
{
	List<List<Integer>> ans;

	void Trav(TreeNode r, int layer)
	{
		if (ans.size() == layer)
			ans.add(new ArrayList<Integer>());
		ans.get(layer).add(r.val);
		if (r.left != null)
			Trav(r.left, layer + 1);
		if (r.right != null)
			Trav(r.right, layer + 1);
	}

	void Rev()
	{
		for (int i = 1; i < ans.size(); i += 2)
		{
			List<Integer> t = ans.get(i);
			int l = t.size();
			for (int j = 0; j <= l / 2 - 1; j++)
			{
				int tp = t.get(j);
				t.set(j, t.get(l - 1 - j));
				t.set(l - 1 - j, tp);
			}
		}
	}

	public List<List<Integer>> zigzagLevelOrder(TreeNode root)
	{
		ans = new ArrayList<List<Integer>>();
		if (root == null)
			return ans;
		Trav(root, 0);
		Rev();
		return ans;
	}
}

//104. Maximum Depth of Binary Tree
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Depth of Binary Tree.
//Memory Usage: 39.4 MB, less than 100.00% of Java online submissions for Maximum Depth of Binary Tree.
class Solution104
{
	int maxd = 0;

	void Trav(TreeNode r, int layer)
	{
		if (r.left != null)
			Trav(r.left, layer + 1);
		if (r.right != null)
			Trav(r.right, layer + 1);
		if (layer > maxd)
			maxd = layer;
	}

	public int maxDepth(TreeNode root)
	{
		if (root == null)
			return 0;
		Trav(root, 1);
		return maxd;
	}
}

//105. Construct Binary Tree from Preorder and Inorder Traversal
//Runtime: 3 ms, faster than 91.61% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
//Memory Usage: 37.7 MB, less than 100.00% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
class Solution105
{
	int find(int[] a, int tar, int st, int ed)
	{
		for (int i = st; i < ed && i < a.length; i++)
			if (a[i] == tar)
				return i;
		return -1;
	}

	TreeNode bd(int[] po, int[] io, int pol, int por, int iol, int ior)
	{
		TreeNode r = new TreeNode(po[pol]);
		if (por == pol + 1)
			return r;
		int iortind = find(io, r.val, iol, ior);
		int ltreelen = iortind - iol;
		if (iortind > iol)
			r.left = bd(po, io, pol + 1, pol + ltreelen + 1, iol, iortind);
		if (iortind < ior - 1)
			r.right = bd(po, io, pol + ltreelen + 1, por, iortind + 1, ior);
		return r;
	}

	public TreeNode buildTree(int[] preorder, int[] inorder)
	{
		if (preorder.length == 0)
			return null;
		return bd(preorder, inorder, 0, preorder.length, 0, inorder.length);
	}
}

//106. Construct Binary Tree from Inorder and Postorder Traversal
//Runtime: 3 ms, faster than 87.33% of Java online submissions for Construct Binary Tree from Inorder and Postorder Traversa
//Memory Usage: 37.7 MB, less than 100.00% of Java online submissions for Construct Binary Tree from Inorder and Postorder Traversal
class Solution106
{
	int find(int[] a, int tar, int st, int ed)
	{
		for (int i = st; i < ed && i < a.length; i++)
			if (a[i] == tar)
				return i;
		return -1;
	}

	TreeNode bd(int[] io, int[] po, int iol, int ior, int pol, int por)
	{
		TreeNode r = new TreeNode(po[por - 1]);
		if (por == pol + 1)
			return r;
		int iortind = find(io, r.val, iol, ior);
		int ltreelen = iortind - iol;
		if (iortind > iol)
			r.left = bd(io, po, iol, iortind, pol, pol + ltreelen);
		if (iortind < ior - 1)
			r.right = bd(io, po, iortind + 1, ior, pol + ltreelen, por - 1);
		return r;
	}

	public TreeNode buildTree(int[] inorder, int[] postorder)
	{
		if (inorder.length == 0)
			return null;
		return bd(inorder, postorder, 0, inorder.length, 0, postorder.length);
	}
}

//107. Binary Tree Level Order Traversal II
//Runtime: 1 ms, faster than 98.30% of Java online submissions for Binary Tree Level Order Traversal II.
//Memory Usage: 37.5 MB, less than 100.00% of Java online submissions for Binary Tree Level Order Traversal II.
class Solution107
{
	List<List<Integer>> ans;

	void Trav(TreeNode r, int layer)
	{
		if (ans.size() == layer)
		{
			ans.add(new ArrayList<Integer>());
		}
		ans.get(layer).add(r.val);
		if (r.left != null)
			Trav(r.left, layer + 1);
		if (r.right != null)
			Trav(r.right, layer + 1);
	}

	void Rev()
	{
		for (int i = 0; i <= ans.size() / 2 - 1; i++)
		{
			List<Integer> t = ans.get(i);
			ans.set(i, ans.get(ans.size() - 1 - i));
			ans.set(ans.size() - 1 - i, t);
		}
	}

	public List<List<Integer>> levelOrderBottom(TreeNode root)
	{
		ans = new ArrayList<List<Integer>>();
		if (root == null)
			return ans;
		Trav(root, 0);
		Rev();
		return ans;
	}
}

//108. Convert Sorted Array to Binary Search Tree
//Runtime: 2 ms, faster than 7.07% of Java online submissions for Convert Sorted Array to Binary Search Tree.
//Memory Usage: 37.6 MB, less than 100.00% of Java online submissions for Convert Sorted Array to Binary Search Tree.
class Solution108
{
	public TreeNode sortedArrayToBST(int[] nums)
	{
		if (nums.length == 0)
			return null;
		BBST R = new BBST(nums[0]);
		for (int i = 1; i < nums.length; i++)
			R = R.insert(nums[i]);
		return R;
	}
}

//109. Convert Sorted List to Binary Search Tree
//Runtime: 2 ms, faster than 30.63% of Java online submissions for Convert Sorted List to Binary Search Tree.
//Memory Usage: 40.9 MB, less than 100.00% of Java online submissions for Convert Sorted List to Binary Search Tree.
class Solution109
{
	public TreeNode sortedListToBST(ListNode head)
	{
		if (head == null)
			return null;
		BBST R = new BBST(head.val);
		while (head.next != null)
		{
			head = head.next;
			R = R.insert(head.val);
		}
		return R;
	}
}

//110. Balanced Binary Tree
//Runtime: 1 ms, faster than 93.45% of Java online submissions for Balanced Binary Tree.
//Memory Usage: 41.6 MB, less than 100.00% of Java online submissions for Balanced Binary Tree.
class R110
{
	int height;
	boolean balanced;

	public R110(int h, boolean b)
	{
		height = h;
		balanced = b;
	}
}

class Solution110
{
	R110 check(TreeNode r)
	{
		if (r == null)
			return new R110(0, true);
		R110 L = check(r.left), R = check(r.right);
		return new R110(Math.max(L.height, R.height) + 1,
				L.balanced && R.balanced && Math.abs(L.height - R.height) < 2);
	}

	public boolean isBalanced(TreeNode root)
	{
		return check(root).balanced;
	}
}

public class LC101_110
{
	public static class MainClass101
	{
		public static TreeNode stringToTreeNode(String input)
		{
			input = input.trim();
			input = input.substring(1, input.length() - 1);
			if (input.length() == 0)
			{
				return null;
			}

			String[] parts = input.split(",");
			String item = parts[0];
			TreeNode root = new TreeNode(Integer.parseInt(item));
			Queue<TreeNode> nodeQueue = new LinkedList<>();
			nodeQueue.add(root);

			int index = 1;
			while (!nodeQueue.isEmpty())
			{
				TreeNode node = nodeQueue.remove();

				if (index == parts.length)
				{
					break;
				}

				item = parts[index++];
				item = item.trim();
				if (!item.equals("null"))
				{
					int leftNumber = Integer.parseInt(item);
					node.left = new TreeNode(leftNumber);
					nodeQueue.add(node.left);
				}

				if (index == parts.length)
				{
					break;
				}

				item = parts[index++];
				item = item.trim();
				if (!item.equals("null"))
				{
					int rightNumber = Integer.parseInt(item);
					node.right = new TreeNode(rightNumber);
					nodeQueue.add(node.right);
				}
			}
			return root;
		}

		public static String booleanToString(boolean input)
		{
			return input ? "True" : "False";
		}

		public static void main(String[] args) throws IOException
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String line;
			while ((line = in.readLine()) != null && line.length() > 0)
			{
				TreeNode root = stringToTreeNode(line);

				boolean ret = new Solution101().isSymmetric(root);

				String out = booleanToString(ret);

				System.out.print(out);
			}
		}
	}

	public static class MainClass105
	{
		public static int[] stringToIntegerArray(String input)
		{
			input = input.trim();
			input = input.substring(1, input.length() - 1);
			if (input.length() == 0)
			{
				return new int[0];
			}

			String[] parts = input.split(",");
			int[] output = new int[parts.length];
			for (int index = 0; index < parts.length; index++)
			{
				String part = parts[index].trim();
				output[index] = Integer.parseInt(part);
			}
			return output;
		}

		public static String treeNodeToString(TreeNode root)
		{
			if (root == null)
			{
				return "[]";
			}

			String output = "";
			Queue<TreeNode> nodeQueue = new LinkedList<>();
			nodeQueue.add(root);
			while (!nodeQueue.isEmpty())
			{
				TreeNode node = nodeQueue.remove();

				if (node == null)
				{
					output += "null, ";
					continue;
				}

				output += String.valueOf(node.val) + ", ";
				nodeQueue.add(node.left);
				nodeQueue.add(node.right);
			}
			return "[" + output.substring(0, output.length() - 2) + "]";
		}

		public static void main(String[] args) throws IOException
		{
//	        BufferedReader in = new BufferedReader(
//	    	new InputStreamReader(System.in));
			BufferedReader in = new BufferedReader(new FileReader(new File("in101_110.txt")));
			String line;
			while ((line = in.readLine()) != null && line.length() > 0)
			{
				int[] preorder = stringToIntegerArray(line);
				line = in.readLine();
				int[] inorder = stringToIntegerArray(line);

				TreeNode ret = new Solution105().buildTree(preorder, inorder);

				String out = treeNodeToString(ret) + "\n";

				System.out.print(out);
			}
			in.close();
		}
	}

	public static class MainClass106
	{
		public static int[] stringToIntegerArray(String input)
		{
			input = input.trim();
			input = input.substring(1, input.length() - 1);
			if (input.length() == 0)
			{
				return new int[0];
			}

			String[] parts = input.split(",");
			int[] output = new int[parts.length];
			for (int index = 0; index < parts.length; index++)
			{
				String part = parts[index].trim();
				output[index] = Integer.parseInt(part);
			}
			return output;
		}

		public static String treeNodeToString(TreeNode root)
		{
			if (root == null)
			{
				return "[]";
			}

			String output = "";
			Queue<TreeNode> nodeQueue = new LinkedList<>();
			nodeQueue.add(root);
			while (!nodeQueue.isEmpty())
			{
				TreeNode node = nodeQueue.remove();

				if (node == null)
				{
					output += "null, ";
					continue;
				}

				output += String.valueOf(node.val) + ", ";
				nodeQueue.add(node.left);
				nodeQueue.add(node.right);
			}
			return "[" + output.substring(0, output.length() - 2) + "]";
		}

		public static void main(String[] args) throws IOException
		{
//	        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader in = new BufferedReader(
					new FileReader(new File("input" + File.separatorChar + "input106.txt")));
			String line;
			while ((line = in.readLine()) != null && line.length() > 0)
			{
				int[] inorder = stringToIntegerArray(line);
				line = in.readLine();
				int[] postorder = stringToIntegerArray(line);

				TreeNode ret = new Solution106().buildTree(inorder, postorder);

				String out = treeNodeToString(ret);

				System.out.println(out);
			}
			in.close();
		}
	}

	public static class MainClass108
	{
		public static int[] stringToIntegerArray(String input)
		{
			input = input.trim();
			input = input.substring(1, input.length() - 1);
			if (input.length() == 0)
			{
				return new int[0];
			}

			String[] parts = input.split(",");
			int[] output = new int[parts.length];
			for (int index = 0; index < parts.length; index++)
			{
				String part = parts[index].trim();
				output[index] = Integer.parseInt(part);
			}
			return output;
		}

		public static String treeNodeToString(TreeNode root)
		{
			if (root == null)
			{
				return "[]";
			}

			String output = "";
			Queue<TreeNode> nodeQueue = new LinkedList<>();
			nodeQueue.add(root);
			while (!nodeQueue.isEmpty())
			{
				TreeNode node = nodeQueue.remove();

				if (node == null)
				{
					output += "null, ";
					continue;
				}

				output += String.valueOf(node.val) + ", ";
				nodeQueue.add(node.left);
				nodeQueue.add(node.right);
			}
			return "[" + output.substring(0, output.length() - 2) + "]";
		}

		public static void main(String[] args) throws IOException
		{
//	        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader in = new BufferedReader(
					new FileReader(new File("input" + File.separator + "in101_110.txt")));
			String line;
			while ((line = in.readLine()) != null && line.length() > 0)
			{
				int[] nums = stringToIntegerArray(line);

				TreeNode ret = new Solution108().sortedArrayToBST(nums);

				String out = treeNodeToString(ret);

				System.out.print(out);
			}
			in.close();
		}
	}

	public static void main(String[] args) throws IOException
	{
		MainClass108.main(args);

	}

}
