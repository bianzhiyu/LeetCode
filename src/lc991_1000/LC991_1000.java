package lc991_1000;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import treeCodec.TreeNode;

//991. Broken Calculator
//TLE
//case: 1 10 0000 0000
class Solution991
{
	public int brokenCalc(int X, int Y)
	{
		if (X == Y)
			return 0;
		Queue<Integer> q1 = new LinkedList<Integer>();
		HashMap<Integer, Integer> h1 = new HashMap<Integer, Integer>();
		q1.add(X);
		h1.put(X, 0);

		Queue<Integer> q2 = new LinkedList<Integer>();
		HashMap<Integer, Integer> h2 = new HashMap<Integer, Integer>();
		q2.add(Y);
		h2.put(Y, 0);
		while (!q1.isEmpty() && !q2.isEmpty())
		{
			if (!q1.isEmpty())
			{
				int h = q1.remove(), sp = h1.get(h), ns;

				ns = h - 1;
				if (h2.containsKey(ns))
					return sp + 1 + h2.get(ns);
				if (ns > 0 && !h1.containsKey(ns))
				{
					h1.put(ns, sp + 1);
					q1.add(ns);
				}

				ns = h * 2;
				if (h2.containsKey(ns))
					return sp + 1 + h2.get(ns);
				if (ns < 20_0000_0007 && !h1.containsKey(ns))
				{
					h1.put(ns, sp + 1);
					q1.add(ns);
				}
			}
			if (!q2.isEmpty())
			{
				int h = q2.remove(), sp = h2.get(h), ns;

				ns = h + 1;
				if (h1.containsKey(ns))
					return sp + 1 + h1.get(ns);
				if (ns < 20_0000_0007 && !h2.containsKey(ns))
				{
					q2.add(ns);
					h2.put(ns, sp + 1);
				}

				ns = h / 2;
				if (h1.containsKey(ns))
					return sp + 1 + h1.get(ns);
				if (h % 2 == 0 && h != 0 && !h2.containsKey(ns))
				{
					q2.add(ns);
					h2.put(ns, sp + 1);
				}
			}
		}
		return -1;
	}
}

//TLE
//case: 1 10 0000 0000
class Solution991_2
{
	public int brokenCalc(int X, int Y)
	{
		Queue<Integer> q = new LinkedList<Integer>();
		Queue<Integer> s = new LinkedList<Integer>();
		Set<Integer> used = new HashSet<Integer>();
		q.add(X);
		s.add(0);
		used.add(X);
		int max = Integer.MAX_VALUE;
		while (!q.isEmpty())
		{
			int h = q.remove(), sp = s.remove();
			if (h == Y)
				return sp;
			if (h > Y)
				max = Math.min(max, sp + h - Y);
			if (sp >= max + 1)
				continue;
			if (h > 1 && !used.contains(h - 1))
			{
				used.add(h - 1);
				q.add(h - 1);
				s.add(sp + 1);
			}
			while (h < 10_0000_0003 && h < Y && !used.contains(h * 2))
			{
				h *= 2;
				sp++;
				used.add(h);
				q.add(h);
				s.add(sp);
			}
		}
		return max;
	}
}

//https://leetcode.com/problems/broken-calculator/solution/
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Broken Calculator.
//Memory Usage: 31.8 MB, less than 100.00% of Java online submissions for Broken Calculator.
//https://github.com/bianzhiyu/LeetCode/wiki/991_1000
class Solution991_3
{
	public int brokenCalc(int X, int Y)
	{
		int ct = 0;
		while (X != Y)
		{
			if (X > Y)
				return ct + X - Y;
			if (Y % 2 == 0)
				Y /= 2;
			else
				Y++;
			ct++;
		}
		return ct;
	}
}

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
		return backtrack(0, 0, new int[A.length], A, new boolean[A.length]);
	}

	private int backtrack(int res, int sp, int[] st, int[] A, boolean[] used)
	{
		if (sp == A.length)
			return ++res;
		for (int i = 0; i < A.length; i++)
		{
			if (used[i] || i > 0 && A[i] == A[i - 1] && !used[i - 1] || sp > 0 && !isSquareful(st[sp - 1] + A[i]))
				continue;
			used[i] = true;
			st[sp] = A[i];
			res = backtrack(res, sp + 1, st, A, used);
			used[i] = false;
		}

		return res;
	}

	private boolean isSquareful(int x)
	{
		double t = Math.sqrt(x);
		return t == (int) t;
	}
}

//998. Maximum Binary Tree II
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Maximum Binary Tree II.
//Memory Usage: 36.8 MB, less than 100.00% of Java online submissions for Maximum Binary Tree II.
class Solution998
{
	public TreeNode insertIntoMaxTree(TreeNode root, int val)
	{
		if (val > root.val) // no multiplication
		{
			TreeNode r = new TreeNode(val);
			r.left = root;
			return r;
		} else
		{
			root.right = root.right == null ? new TreeNode(val) : insertIntoMaxTree(root.right, val);
			return root;
		}
	}
}

public class LC991_1000
{
	public static void test996()
	{
		int[] a = new int[]
		{ 2, 2 };
		System.out.println(new Solution996_2().numSquarefulPerms(a));
	}

	public static void main(String[] args)
	{
		Solution991 s = new Solution991();
		System.out.println(s.brokenCalc(3, 10));
	}
}
