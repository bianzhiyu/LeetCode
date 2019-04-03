package lc441_450;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import treeCodec.*;
import bbst.BBST;

//442. Find All Duplicates in an Array
// Runtime: 38 ms, faster than 11.97% of Java online submissions for Find All Duplicates in an Array.
// Memory Usage: 53.4 MB, less than 8.50% of Java online submissions for Find All Duplicates in an Array.
class Solution442
{
	public List<Integer> findDuplicates(int[] nums)
	{
		HashMap<Integer, Integer> hs = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++)
			if (hs.containsKey(nums[i]))
				hs.put(nums[i], hs.get(nums[i]) + 1);
			else
				hs.put(nums[i], 1);
		List<Integer> ans = new ArrayList<Integer>();
		for (int i : hs.keySet())
			if (hs.get(i) == 2)
				ans.add(i);
		return ans;
	}
}

// Time Limit Exceeded
class Solution442_2
{
	private static class Mp implements Comparable<Mp>
	{
		int key, ct;

		Mp(int _k, int _c)
		{
			key = _k;
			ct = _c;
		}

		@Override
		public int compareTo(Mp o)
		{
			return key - o.key;
		}
	}

	public List<Integer> findDuplicates(int[] nums)
	{
		List<Integer> ans = new ArrayList<Integer>();
		if (nums.length == 0)
			return ans;
		BBST<Mp> rt = new BBST<Mp>(new Mp(nums[0], 1));
		for (int i = 1; i < nums.length; i++)
		{
			Mp p = new Mp(nums[i], 1);
			Mp p2 = rt.getData(p);
			if (p2 == null)
			{
				rt = rt.insert(p);
			} else
			{
				p2.ct++;
				rt.replaceData(p2);
			}
		}
		while (rt != null)
		{
			Mp p = rt.getMinData();
			rt = rt.removeMin();
			if (p.ct == 2)
			{
				ans.add(p.key);
			} else if (p.ct > 2)
				break;
		}
		return ans;
	}
}

// Runtime: 26 ms, faster than 24.55% of Java online submissions for Find All
// Duplicates in an Array.
// Memory Usage: 53.2 MB, less than 11.85% of Java online submissions for Find
// All Duplicates in an Array.
class Solution442_3
{
	public List<Integer> findDuplicates(int[] nums)
	{
		List<Integer> ans = new ArrayList<Integer>();
		HashSet<Integer> hs = new HashSet<Integer>();
		for (int i = 0; i < nums.length; i++)
			if (hs.contains(nums[i]))
				ans.add(nums[i]);
			else
				hs.add(nums[i]);
		return ans;
	}
}

//445. Add Two Numbers II
// Runtime: 20 ms, faster than 95.98% of Java online submissions for Add Two Numbers II.
// Memory Usage: 47.5 MB, less than 35.24% of Java online submissions for Add Two Numbers II.
class ListNode
{
	int val;
	ListNode next;

	ListNode(int x)
	{
		val = x;
	}
}

class Solution445
{
	public ListNode addTwoNumbers(ListNode l1, ListNode l2)
	{
		int len1 = 0, len2 = 0;
		ListNode p = l1;
		while (p != null)
		{
			len1++;
			p = p.next;
		}
		p = l2;
		while (p != null)
		{
			len2++;
			p = p.next;
		}
		if (len2 > len1)
		{
			p = l2;
			l2 = l1;
			l1 = p;
			int t = len2;
			len2 = len1;
			len1 = t;
		}
		ListNode ans = new ListNode(l1.val), p2 = ans;
		p = l1;
		for (int i = 1; i < len1; i++)
		{
			p = p.next;
			p2.next = new ListNode(p.val);
			p2 = p2.next;
		}
		p = ans;
		for (int i = 0; i < len1 - len2; i++)
			p = p.next;
		p2 = l2;
		for (int i = 0; i < len2; i++)
		{
			p.val += p2.val;
			p = p.next;
			p2 = p2.next;
		}
		ListNode[] stack = new ListNode[len1];
		p = ans;
		for (int i = 0; i < len1; i++)
		{
			stack[i] = p;
			p = p.next;
		}
		for (int i = len1 - 1; i >= 0; i--)
		{
			if (i > 0)
			{
				stack[i - 1].val += stack[i].val / 10;
				stack[i].val %= 10;
			}
		}
		if (ans.val > 9)
		{
			p = new ListNode(ans.val / 10);
			p.next = ans;
			ans.val %= 10;
			ans = p;
		}
		return ans;
	}
}

// 449. Serialize and Deserialize BST
// Runtime: 10 ms, faster than 53.07% of Java online submissions for Serialize
// and Deserialize BST.
// Memory Usage: 39 MB, less than 85.33% of Java online submissions for
// Serialize and Deserialize BST.

class Codec extends TreeCodec
{

}

// 450. Delete Node in a BST
// Runtime: 3 ms, faster than 100.00% of Java online submissions for Delete Node
// in a BST.
// Memory Usage: 40.5 MB, less than 67.61% of Java online submissions for Delete
// Node in a BST.
class Solution450
{
	int getMin(TreeNode root)
	{
		if (root.left != null)
			return getMin(root.left);
		return root.val;
	}

	int getMax(TreeNode root)
	{
		if (root.right != null)
			return getMin(root.right);
		return root.val;
	}

	public TreeNode deleteNode(TreeNode root, int key)
	{
		if (root == null)
			return root;
		if (key > root.val)
		{
			root.right = deleteNode(root.right, key);
			return root;
		}
		if (key < root.val)
		{
			root.left = deleteNode(root.left, key);
			return root;
		}
		if (root.right != null)
		{
			root.val = getMin(root.right);
			root.right = deleteNode(root.right, root.val);
			return root;
		}
		if (root.left != null)
		{
			root.val = getMax(root.left);
			root.left = deleteNode(root.left, root.val);
			return root;
		}
		return null;
	}
}

public class LC441_450
{
	public static void main(String[] ags)
	{
		int[] a = new int[]
		{ 4, 3, 2, 7, 8, 2, 3, 1 };
		System.out.println(new Solution442_2().findDuplicates(a));
	}

}
