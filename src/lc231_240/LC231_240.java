package lc231_240;

import java.util.LinkedList;
import java.util.Stack;

import bbst.BBST;
import treeCodec.*;

class ListNode
{
	int val;
	ListNode next;

	ListNode(int x)
	{
		val = x;
	}
}

//231. Power of Two
// Runtime: 1 ms, 90.66%
// Memory Usage: 32.5 MB
class Solution231
{
	public boolean isPowerOfTwo(int n)
	{
		if (n <= 0)
			return false;
		while (n > 2)
		{
			if (n % 2 != 0)
				return false;
			n /= 2;
		}
		return true;
	}
}

//232. Implement Queue using Stacks
//Runtime: 47 ms, faster than 100.00% of Java online submissions for Implement Queue using Stacks.
//Memory Usage: 36.6 MB, less than 87.66% of Java online submissions for Implement Queue using Stacks.
class MyQueue
{

	private Stack<Integer> s1 = new Stack<Integer>();
	private Stack<Integer> s2 = new Stack<Integer>();

	/** Initialize your data structure here. */
	public MyQueue()
	{

	}

	/** Push element x to the back of queue. */
	public void push(int x)
	{
		s1.push(x);
	}

	/** Removes the element from in front of queue and returns that element. */
	public int pop()
	{
		if (s2.isEmpty())
		{
			while (!s1.isEmpty())
				s2.push(s1.pop());
		}
		return s2.pop();
	}

	/** Get the front element. */
	public int peek()
	{
		if (s2.isEmpty())
		{
			while (!s1.isEmpty())
				s2.push(s1.pop());
		}
		return s2.peek();
	}

	/** Returns whether the queue is empty. */
	public boolean empty()
	{
		return s1.isEmpty() && s2.isEmpty();
	}
}

/**
 * Your MyQueue object will be instantiated and called as such: MyQueue obj =
 * new MyQueue(); obj.push(x); int param_2 = obj.pop(); int param_3 =
 * obj.peek(); boolean param_4 = obj.empty();
 */

//223. Rectangle Area
//Runtime: 2 ms, faster than 100.00% of Java online submissions for Rectangle Area.
//Memory Usage: 37.4 MB, less than 30.53% of Java online submissions for Rectangle Area.
class Solution223
{
	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H)
	{
		int xl = Math.max(A, E), xr = Math.min(C, G), yd = Math.max(B, F), yu = Math.min(D, H);
		if (xl > xr)
			xl = xr;
		if (yd > yu)
			yd = yu;
		return (C - A) * (D - B) + (G - E) * (H - F) - (xr - xl) * (yu - yd);
	}
}

//234. Palindrome Linked List
//Runtime: 3 ms, 21.16%
//Memory Usage: 42.4 MB
class Solution234
{
	public boolean isPalindrome(ListNode head)
	{
		LinkedList<Integer> ino = new LinkedList<Integer>();
		LinkedList<Integer> rvo = new LinkedList<Integer>();
		while (head != null)
		{
			ino.addLast(head.val);
			rvo.addFirst(head.val);
			head = head.next;
		}
		while (!ino.isEmpty())
			if (((int)ino.removeFirst()) != ((int)rvo.removeFirst()))
//			if (ino.removeFirst() != rvo.removeFirst())
				// This is not right, because it will return an object of type Integer.
				return false;
		return true;
	}
}

//Runtime: 3 ms, faster than 21.16% of Java online submissions for Palindrome Linked List.
//Memory Usage: 42.4 MB, less than 42.13% of Java online submissions for Palindrome Linked List.
class Solution234_2
{
	public boolean isPalindrome(ListNode head)
	{
		LinkedList<Integer> ino = new LinkedList<Integer>();
		while (head != null)
		{
			ino.addLast(head.val);
			head = head.next;
		}
		int t;
		while (!ino.isEmpty())
		{
			t=ino.removeFirst();
			if (!ino.isEmpty() && ((int)ino.removeLast())!=t)
				return false;
		}
		return true;
	}
}

//235. Lowest Common Ancestor of a Binary Search Tree
//Runtime: 6 ms, faster than 9.47% of Java online submissions for Lowest Common Ancestor of a Binary Search Tree.
//Memory Usage: 35.3 MB, less than 18.25% of Java online submissions for Lowest Common Ancestor of a Binary Search Tree.
class Solution235
{
	private static class R
	{
		boolean a1, a2;
		TreeNode tn;

		public R(boolean b1, boolean b2, TreeNode t)
		{
			a1 = b1;
			a2 = b2;
			tn = t;
		}
	}

	private R trav(TreeNode root, TreeNode p, TreeNode q)
	{
		if (root == null)
			return new R(false, false, null);
		R l = trav(root.left, p, q), r = trav(root.right, p, q);
		R c = new R(l.a1 || r.a1, l.a2 || r.a2, null);
		if (l.tn != null)
			return l;
		if (r.tn != null)
			return r;
		if (root == p)
			c.a1 = true;
		if (root == q)
			c.a2 = true;
		if (c.a1 && c.a2)
			c.tn = root;
		return c;
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
	{
		return trav(root, p, q).tn;
	}
}

//236. Lowest Common Ancestor of a Binary Tree
//Runtime: 8 ms, 27%
//Memory Usage: 33.3 MB
class Solution236
{
	private boolean findpath(TreeNode r, TreeNode p, int[] path)
	{
		if (r == null)
			return false;
		if (p.val == r.val)
			return true;
		int s = path[0];
		path[0] = s + 1;

		path[s + 1] = 0;
		if (findpath(r.left, p, path))
			return true;
		path[0] = s + 1;
		path[s + 1] = 1;
		return findpath(r.right, p, path);

	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
	{
		int[] path1 = new int[100000], path2 = new int[100000];
		findpath(root, p, path1);
		findpath(root, q, path2);
		int len1 = path1[0], len2 = path2[0];
		int i = 1;
		TreeNode tr = root;
		while (i <= len1 && i <= len2)
		{
			if (path1[i] == path2[i])
			{
				if (path1[i] == 0)
					tr = tr.left;
				else
					tr = tr.right;
			} else
				break;
			i++;
		}
		return tr;
	}
}

//237. Delete Node in a Linked List
//This problem has a wrong test setting, thus can not be triled.
class Solution237
{
	public void deleteNode(ListNode node, int v)
	{
		ListNode fakehead = new ListNode(0);
		fakehead.next = node;
		ListNode p = fakehead;
		while (p.next != null)
		{
			if (p.next.val == v)
			{
				p.next = p.next.next;
				break;
			}
			p = p.next;
		}
	}
}

//238. Product of Array Except Self
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Product of Array Except Self.
//Memory Usage: 40.5 MB, less than 95.03% of Java online submissions for Product of Array Except Self.
class Solution238
{
	public int[] productExceptSelf(int[] nums)
	{
		int len = nums.length;
		int[] lm = new int[len], rm = new int[len];
		lm[0] = 1;
		for (int i = 1; i < len; i++)
			lm[i] = nums[i - 1] * lm[i - 1];
		rm[len - 1] = 1;
		for (int i = len - 2; i >= 0; i--)
			rm[i] = rm[i + 1] * nums[i + 1];
		int[] ans = new int[len];
		for (int i = 0; i < len; i++)
			ans[i] = lm[i] * rm[i];
		return ans;
	}
}

/**
 * 239. Sliding Window Maximum RK: This method is brute force Maybe new data
 * structure can be applied. Candidates can be balanced binary search tree.
 * Fibonacci heap? LeetCode Test Performance Runtime: 46 ms, 24.33% Memory
 * Usage: 43.3 MB, 12.20%
 * 
 * @author Andrew
 */
class Solution239
{
	public int[] maxSlidingWindow(int[] nums, int k)
	{
		if (nums.length == 0)
			return new int[0];
		if (k == 1)
			return nums;
		int[] ans = new int[nums.length + 1 - k];
		for (int i = 0; i < ans.length; i++)
		{
			int temp = Integer.MIN_VALUE;
			for (int j = i; j < i + k; j++)
				if (nums[j] > temp)
					temp = nums[j];
			ans[i] = temp;
		}
		return ans;
	}
}

//Using Balanced Binary Search Tree, implemented by myself.
//Runtime: 25 ms, faster than 33.29% of Java online submissions for Sliding Window Maximum.
//Memory Usage: 44.6 MB, less than 7.20% of Java online submissions for Sliding Window Maximum.
class Solution239_2
{
	public int[] maxSlidingWindow(int[] nums, int k)
	{
		if (nums.length == 0)
			return new int[0];
		if (k == 1)
			return nums;
		int[] ans = new int[nums.length + 1 - k];
		BBST<Integer> rt = new BBST<Integer>(nums[0]);
		for (int i = 1; i < k; i++)
			rt = rt.insert(nums[i]);
		ans[0] = rt.getMaxData();
		for (int i = 1; i < ans.length; i++)
		{
			rt = rt.removeNodeByData(nums[i - 1]);
			rt = rt.insert(nums[i + k - 1]);
			ans[i] = rt.getMaxData();
		}
		return ans;
	}
}

//240. Search a 2D Matrix II
//Runtime: 6 ms, faster than 84.53% of Java online submissions for Search a 2D Matrix II.
//Memory Usage: 44.4 MB, less than 86.57% of Java online submissions for Search a 2D Matrix II.
class Solution240
{
	int srlargermin(int[][] ma, int t, int u, int d, int c)
	{
		if (u == d)
		{
			if (ma[u][c] >= t)
				return u;
			else
				return -1;
		}
		if (d == u + 1)
		{
			if (ma[u][c] >= t)
				return u;
			else if (ma[d][c] >= t)
				return d;
			else
				return -1;
		}
		int m = (u + d) / 2;
		if (ma[m][c] >= t)
			return srlargermin(ma, t, u, m, c);
		else
			return srlargermin(ma, t, m + 1, d, c);

	}

	int srsmallermax(int[][] ma, int t, int u, int d, int c)
	{
		if (u > d)
			return -1;
		if (u == d)
		{
			if (ma[u][c] <= t)
				return u;
			else
				return -1;
		}
		if (d == u + 1)
		{
			if (ma[d][c] <= t)
				return d;
			else if (ma[u][c] <= t)
				return u;
			else
				return -1;
		}
		int m = (u + d) / 2;
		if (ma[m][c] <= t)
			return srsmallermax(ma, t, m, d, c);
		else
			return srsmallermax(ma, t, u, m - 1, c);

	}

	int sc(int[][] ma, int t, int l, int r, int row)
	{
		if (l > r)
			return -1;
		if (l == r)
		{
			if (ma[row][l] == t)
				return l;
			else
				return -1;
		}
		if (r == l + 1)
		{
			if (ma[row][r] == t)
				return r;
			else if (ma[row][l] == t)
				return l;
			else
				return -1;
		}
		int m = (l + r) / 2;
		if (ma[row][m] >= t)
			return sc(ma, t, l, m, row);
		else
			return sc(ma, t, m + 1, r, row);
	}

	public boolean searchMatrix(int[][] matrix, int target)
	{
		if (matrix.length == 0 || matrix[0].length == 0)
			return false;
		int m = matrix.length, n = matrix[0].length;
		if (matrix[m - 1][n - 1] < target)
			return false;
		int h1 = srlargermin(matrix, target, 0, m - 1, n - 1);
		if (matrix[0][0] > target)
			return false;
		int h2 = srsmallermax(matrix, target, 0, m - 1, 0);
		for (int i = h1; i <= h2; i++)
			if (sc(matrix, target, 0, n - 1, i) != -1)
				return true;
		return false;
	}
}

public class LC231_240
{
	public static void main(String[] args)
	{
		test.Test.dispArr(new Solution239_2().maxSlidingWindow(
				// new int[] {1,3,-1,-3,5,3,6,7}, 3
				new int[]
				{ 1, -1 }, 1));
	}

}
