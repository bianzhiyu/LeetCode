package lc91_100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import treeCodec.TreeNode;

//91. Decode Ways
//Runtime: 442 ms, faster than 6.84% of Java online submissions for Decode Ways.
//Memory Usage: 36.1 MB, less than 100.00% of Java online submissions for Decode Ways.
class Solution91
{
	int tot = 0;
	char[] str;

	static char[] Convert(String s)
	{
		char[] str = new char[s.length()];
		for (int i = 0; i < str.length; i++)
			str[i] = s.charAt(i);
		return str;
	}

	void dfs(int sp)
	{
		if (sp == str.length)
		{
			tot++;
			return;
		}
		if (str[sp] >= '1' && str[sp] <= '9')
			dfs(sp + 1);
		if (sp < str.length - 1 && ((str[sp] == '1') || (str[sp] == '2' && str[sp + 1] <= '6')))
			dfs(sp + 2);
	}

	public int numDecodings(String s)
	{
		if (s.length() == 0)
			return 0;
		str = Convert(s);
		dfs(0);
		return tot;
	}
}

//Runtime: 1 ms, faster than 93.05% of Java online submissions for Decode Ways.
//Memory Usage: 36 MB, less than 100.00% of Java online submissions for Decode Ways.
class Solution91_2
{
	static char[] Convert(String s)
	{
		char[] str = new char[s.length()];
		for (int i = 0; i < str.length; i++)
			str[i] = s.charAt(i);
		return str;
	}

	public int numDecodings(String s)
	{
		if (s.length() == 0)
			return 0;
		char[] str = Convert(s);
		int[] d = new int[str.length + 1];
		d[0] = 1;
		if (str[0] >= '1' && str[0] <= '9')
			d[1] = 1;
		else
			d[1] = 0;
		for (int i = 1; i < str.length; i++)
		{
			if (str[i] >= '1' && str[i] <= '9')
				d[i + 1] = d[i];
			else
				d[i + 1] = 0;
			if (str[i - 1] == '1')
				d[i + 1] += d[i - 1];
			if (str[i - 1] == '2' && str[i] <= '6')
				d[i + 1] += d[i - 1];
		}
		return d[str.length];
	}
}

///92. Reverse Linked List II
class ListNode
{
	int val;
	ListNode next;

	ListNode(int x)
	{
		val = x;
	}
}

//Runtime: 2 ms, faster than 97.39% of Java online submissions for Reverse Linked List II.
//Memory Usage: 37.1 MB, less than 5.07% of Java online submissions for Reverse Linked List II.
class Solution92
{
	public ListNode reverseBetween(ListNode head, int m, int n)
	{
		ListNode fakehead = new ListNode(0);
		fakehead.next = head;
		ListNode p = fakehead;
		for (int i = 1; i < m; i++)
			p = p.next;
		ListNode q = p;
		for (int i = m; i < n; i++)
			q = q.next;

		System.out.println(p.val + " " + q.val);

		ListNode r = q.next.next;

		ListNode p1 = p.next;
		for (int i = m; i <= n; i++)
		{
			ListNode q1 = p1.next;
			p1.next = r;
			r = p1;
			p1 = q1;
		}
		p.next = r;
		return fakehead.next;
	}
}

//93. Restore IP Addresses
//Runtime: 1 ms, faster than 99.72% of Java online submissions for Restore IP Addresses.
//Memory Usage: 34.9 MB, less than 69.55% of Java online submissions for Restore IP Addresses.
class Solution93
{
	char[] str;
	int[] ips = new int[4];
	List<String> ans = new ArrayList<String>();

	void dfs(int sp, int l)
	{
		if (sp == 4)
		{
			if (l < str.length)
				return;
			String s = "";
			s = s + ips[0] + "." + ips[1] + "." + ips[2] + "." + ips[3];
			ans.add(s);
			return;
		}
		if (l >= str.length)
			return;
		if (str[l] == '0')
		{
			ips[sp] = 0;
			dfs(sp + 1, l + 1);
			return;
		}
		int x = 0;
		for (int i = l; i < str.length; i++)
		{
			x = x * 10 + (str[i] - '0');
			if (x > 255)
				break;
			else
			{
				ips[sp] = x;
				dfs(sp + 1, i + 1);
			}
		}
	}

	public List<String> restoreIpAddresses(String s)
	{
		str = s.toCharArray();
		dfs(0, 0);
		return ans;
	}
}

//t94. Binary Tree Inorder Traversal
//t=0ms, m=36MB
//t:1, m:0.0099
class Solution94
{
	boolean n = true;
	List<Integer> l = new ArrayList<Integer>();

	public List<Integer> inorderTraversal(TreeNode root)
	{
		boolean b = false;
		if (n)
		{
			n = false;
			b = true;

		}
		if (root == null)
			return new ArrayList<Integer>();
		inorderTraversal(root.left);
		l.add(root.val);
		inorderTraversal(root.right);
		if (b)
			return l;
		return new ArrayList<Integer>();
	}
}

//t94_2
//Follow up: Recursive solution is trivial, could you do it iteratively?
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Inorder Traversal.
//Memory Usage: 37 MB, less than 100.00% of Java online submissions for Binary Tree Inorder Traversal.
class Solution94_2
{
	final static int MAXTREENODE = 1000;

	public List<Integer> inorderTraversal(TreeNode root)
	{
		TreeNode[] Stack = new TreeNode[MAXTREENODE];
		boolean[] LTChecked = new boolean[MAXTREENODE];

		int sp = 0;
		List<Integer> l = new ArrayList<Integer>();
		if (root == null)
			return l;

		Stack[0] = root;
		LTChecked[0] = false;

		sp = 1;
		while (sp > 0)
		{
			TreeNode tn = Stack[sp - 1];
			if (!LTChecked[sp - 1])
			{
				if (tn.left != null)
				{
					LTChecked[sp - 1] = true;
					sp++;
					Stack[sp - 1] = tn.left;
					LTChecked[sp - 1] = false;
				} else
				{
					LTChecked[sp - 1] = true;
				}
			} else
			{
				l.add(tn.val);
				sp--;

				if (tn.right != null)
				{
					sp++;
					Stack[sp - 1] = tn.right;
					LTChecked[sp - 1] = false;
				}
			}
		}
		return l;
	}
}

//95. Unique Binary Search Trees II
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Unique Binary Search Trees II.
//Memory Usage: 37.8 MB, less than 100.00% of Java online submissions for Unique Binary Search Trees II.
class Solution95
{
	void Trav(TreeNode r1, TreeNode t2, int shi)
	{
		if (r1.left != null)
			Trav(r1.left, t2.left = new TreeNode(r1.left.val + shi), shi);
		if (r1.right != null)
			Trav(r1.right, t2.right = new TreeNode(r1.right.val + shi), shi);
	}

	TreeNode cop(TreeNode r, int shi)
	{
		if (r == null)
			return null;
		TreeNode p = new TreeNode(r.val + shi);
		Trav(r, p, shi);
		return p;
	}

	public List<TreeNode> generateTrees(int n)
	{
		if (n == 0)
			return new ArrayList<TreeNode>();
		List<List<TreeNode>> res = new ArrayList<List<TreeNode>>();
		List<TreeNode> tmp = new ArrayList<TreeNode>();
		tmp.add(null);
		res.add(tmp);
		tmp = new ArrayList<TreeNode>();
		tmp.add(new TreeNode(1));
		res.add(tmp);
		for (int i = 2; i <= n; i++)
		{
			tmp = new ArrayList<TreeNode>();
			for (int j = 1; j <= i; j++)
			{
				List<TreeNode> LTL = res.get(j - 1);
				List<TreeNode> RTL = res.get(i - j);
				for (int k = 0; k < LTL.size(); k++)
					for (int l = 0; l < RTL.size(); l++)
					{
						TreeNode rt = new TreeNode(j);
						rt.left = LTL.get(k);
						rt.right = cop(RTL.get(l), j);
						tmp.add(rt);
					}
			}
			res.add(tmp);
		}

		return res.get(res.size() - 1);
	}
}

//96. Unique Binary Search Trees
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Binary Search Trees.
//Memory Usage: 34.4 MB, less than 100.00% of Java online submissions for Unique Binary Search Trees.
class Solution96
{
	public int numTrees(int n)
	{
		int[] f = new int[n + 1];
		f[0] = 1;
		f[1] = 1;
		for (int i = 2; i <= n; i++)
		{
			f[i] = 0;
			for (int j = 0; j <= i - 1; j++)
				f[i] += f[j] * f[i - 1 - j];
		}
		return f[n];
	}
}

//t97. Interleaving String
//t=6ms, m=1
//t:0.3754, m:1
class Solution97
{
	public boolean isInterleave(String s1, String s2, String s3)
	{
		if (s3.length() != s1.length() + s2.length())
			return false;
		boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
		dp[0][0] = true;
		for (int j = 1; j <= s2.length(); j++)
			dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
		for (int i = 1; i <= s1.length(); i++)
		{
			dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
			for (int j = 1; j <= s2.length(); j++)
			{
				dp[i][j] = (s3.charAt(i + j - 1) == s1.charAt(i - 1) && dp[i - 1][j])
						|| (s3.charAt(i + j - 1) == s2.charAt(j - 1) && dp[i][j - 1]);
			}
		}
		return dp[s1.length()][s2.length()];
	}
}

//t97_2
//t=3ms, m=35MB
//t:0.7308, m:1
//This solution only change a string to string.toCharArray(),
//and it time efficiency has been improved a lot!
class Solution97_2
{
	public boolean nf(char[] s1, char[] s2, char[] s3)
	{
		boolean dp[][] = new boolean[s1.length + 1][s2.length + 1];
		dp[0][0] = true;
		for (int j = 1; j <= s2.length; j++)
			dp[0][j] = dp[0][j - 1] && s2[j - 1] == s3[j - 1];
		for (int i = 1; i <= s1.length; i++)
		{
			dp[i][0] = dp[i - 1][0] && s1[i - 1] == s3[i - 1];
			for (int j = 1; j <= s2.length; j++)
			{
				dp[i][j] = (s3[i + j - 1] == s1[i - 1] && dp[i - 1][j]) || (s3[i + j - 1] == s2[j - 1] && dp[i][j - 1]);
			}
		}
		return dp[s1.length][s2.length];
	}

	public boolean isInterleave(String s1, String s2, String s3)
	{
		if (s3.length() != s1.length() + s2.length())
			return false;
		if (s1.length() == 0)
			return s2.compareTo(s3) == 0;
		return nf(s1.toCharArray(), s2.toCharArray(), s3.toCharArray());
	}
}

//98. Validate Binary Search Tree
//Runtime: 1 ms, faster than 44.75% of Java online submissions for Validate Binary Search Tree.
//Memory Usage: 37.5 MB, less than 100.00% of Java online submissions for Validate Binary Search Tree.
class Solution98
{
	private static class R98
	{
		boolean v;
		long min, max;

		R98(boolean v1, long min1, long max1)
		{
			v = v1;
			min = min1;
			max = max1;
		}
	}

	R98 check(TreeNode root)
	{
		if (root == null)
			return new R98(true, (long) (22) + Integer.MAX_VALUE, (long) (-22) + Integer.MIN_VALUE);
		R98 LR = check(root.left), RR = check(root.right);
		R98 R = new R98(true, root.val, root.val);
		R.v = LR.v && RR.v && root.val > LR.max && root.val < RR.min;
		R.min = Math.min(root.val, Math.min(RR.min, LR.min));
		R.max = Math.max(root.val, Math.max(RR.max, LR.max));
		return R;
	}

	public boolean isValidBST(TreeNode root)
	{
		R98 R = check(root);
		return R.v;
	}
}

//t99. Recover Binary Search Tree
//t=24ms, m=48MB
//t:0.9208, m:1
//RK: O(T)=O(n*log n), O(M)=O(1)
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */

class Solution99
{
	private static class R99
	{
		@SuppressWarnings("unused")
		TreeNode ori, maxNode, minNode;
		boolean legal;
	}

	public R99 check(TreeNode root)
	{
		if (root.left == null && root.right == null)
		{
			R99 R = new R99();
			R.ori = root;
			R.maxNode = root;
			R.minNode = root;
			R.legal = true;
			return R;
		}
		R99 RL = null, RR = null;
		if (root.left != null)
			RL = check(root.left);
		if (root.right != null)
			RR = check(root.right);

		R99 R = new R99();
		R.ori = root;
		R.maxNode = RL != null && RL.maxNode.val > root.val ? RL.maxNode : root;
		R.maxNode = RR != null && RR.maxNode.val > R.maxNode.val ? RR.maxNode : R.maxNode;
		R.minNode = RL != null && RL.minNode.val < root.val ? RL.minNode : root;
		R.minNode = RR != null && RR.minNode.val < R.minNode.val ? RR.minNode : R.minNode;

		R.legal = true;
		if (RL != null && (!RL.legal || RL.maxNode.val > root.val))
			R.legal = false;
		if (RR != null && (!RR.legal || RR.minNode.val < root.val))
			R.legal = false;
		return R;
	}

	void swap(TreeNode r1, TreeNode r2)
	{
		int t = r1.val;
		r1.val = r2.val;
		r2.val = t;
	}

	public void recoverTree(TreeNode root)
	{
		R99 RL = null, RR = null;
		if (root.left != null)
			RL = check(root.left);
		if (root.right != null)
			RR = check(root.right);
		if (root.left != null)
		{
			if (root.right != null)
			{
				if (RL.maxNode.val <= root.val && RR.minNode.val < root.val)
				{
					swap(root, RR.minNode);
				} else if (RL.maxNode.val > root.val && RR.minNode.val >= root.val)
				{
					swap(root, RL.maxNode);
				} else if (RL.maxNode.val > root.val && RR.minNode.val < root.val)
				{
					swap(RR.minNode, RL.maxNode);
				} else
				{
					if (!RL.legal)
						recoverTree(root.left);
					else
						recoverTree(root.right);
				}
			} else
			{
				if (RL.maxNode.val > root.val)
					swap(RL.maxNode, root);
				else
					recoverTree(root.left);
			}
		} else if (root.right != null)
		{
			if (RR.minNode.val < root.val)
			{
				swap(RR.minNode, root);
			} else
			{
				recoverTree(root.right);
			}
		} else
			return;
	}
}

//100. Same Tree
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
//Runtime: 2 ms, faster than 100.00% of Java online submissions for Same Tree.
//Memory Usage: 36.8 MB, less than 100.00% of Java online submissions for Same Tree.
class Solution100
{
	public boolean isSameTree(TreeNode p, TreeNode q)
	{
		if (p == null)
			return q == null;
		if (q == null)
			return false;
		return q.val == p.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}
}

public class LC91_100
{
	public static class MainClass92
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

		public static ListNode stringToListNode(String input)
		{
			// Generate array from the input
			int[] nodeValues = stringToIntegerArray(input);

			// Now convert that list into linked list
			ListNode dummyRoot = new ListNode(0);
			ListNode ptr = dummyRoot;
			for (int item : nodeValues)
			{
				ptr.next = new ListNode(item);
				ptr = ptr.next;
			}
			return dummyRoot.next;
		}

		public static String listNodeToString(ListNode node)
		{
			if (node == null)
			{
				return "[]";
			}

			String result = "";
			while (node != null)
			{
				result += Integer.toString(node.val) + ", ";
				node = node.next;
			}
			return "[" + result.substring(0, result.length() - 2) + "]";
		}

		public static void main(String[] args) throws IOException
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String line;
			while ((line = in.readLine()) != null && line.length() > 0)
			{
				ListNode head = stringToListNode(line);
				line = in.readLine();
				int m = Integer.parseInt(line);
				line = in.readLine();
				int n = Integer.parseInt(line);

				ListNode ret = new Solution92().reverseBetween(head, m, n);

				String out = listNodeToString(ret);

				System.out.print(out);
			}
		}
	}

	public static class MainClass94
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

		public static String integerArrayListToString(List<Integer> nums, int length)
		{
			if (length == 0)
			{
				return "[]";
			}

			String result = "";
			for (int index = 0; index < length; index++)
			{
				Integer number = nums.get(index);
				result += Integer.toString(number) + ", ";
			}
			return "[" + result.substring(0, result.length() - 2) + "]";
		}

		public static String integerArrayListToString(List<Integer> nums)
		{
			return integerArrayListToString(nums, nums.size());
		}

		public static void main(String[] args) throws IOException
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String line;
			while ((line = in.readLine()) != null && line.length() > 0)
			{
				TreeNode root = stringToTreeNode(line);

				List<Integer> ret = new Solution94_2().inorderTraversal(root);

				String out = integerArrayListToString(ret);

				System.out.print(out);
			}
		}
	}

	public static class MainClass99
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
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String line;
			while ((line = in.readLine()) != null && line.length() > 0)
			{
				TreeNode root = stringToTreeNode(line);

				new Solution99().recoverTree(root);
				String out = treeNodeToString(root);

				System.out.print(out);
			}
		}
	}

	public static void main(String[] args) throws IOException
	{
		System.out.println(new Solution95().generateTrees(3).get(3));
	}
}
