package lc531_540;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import treeCodec.*;

//537. Complex Number Multiplication
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Complex Number Multiplication.
//Memory Usage: 35.7 MB, less than 95.92% of Java online submissions for Complex Number Multiplication.
class Solution537
{
	public String complexNumberMultiply(String a, String b)
	{
		int p1 = a.indexOf("+"), p2 = b.indexOf("+");
		int r1 = Integer.parseInt(a.substring(0, p1));
		int i1 = Integer.parseInt(a.substring(p1 + 1, a.length() - 1));
		int r2 = Integer.parseInt(b.substring(0, p2));
		int i2 = Integer.parseInt(b.substring(p2 + 1, b.length() - 1));
		int r = r1 * r2 - i1 * i2;
		int i = r1 * i2 + r2 * i1;
		String ans = r + "+" + i + "i";
		return ans;
	}
}

//538. Convert BST to Greater Tree
//Runtime: 6 ms, faster than 66.71% of Java online submissions for Convert BST to Greater Tree.
//Memory Usage: 39.1 MB, less than 80.18% of Java online submissions for Convert BST to Greater Tree.
class Solution538
{
	private void get(TreeNode rt, List<Integer> l)
	{
		if (rt == null)
			return;
		get(rt.left, l);
		l.add(rt.val);
		get(rt.right, l);
	}

	private void reset(TreeNode rt, List<Integer> l, int[] b)
	{
		if (rt == null)
			return;
		rt.val += b[Collections.binarySearch(l, rt.val)];
		reset(rt.left, l, b);
		reset(rt.right, l, b);
	}

	private int[] getSum(List<Integer> l)
	{
		int[] b = new int[l.size()];
		int i = b.length - 1, sum = 0;
		while (i >= 0)
		{
			int j = i - 1, t = 0;
			while (j >= 0 && l.get(j) == l.get(i))
				j--;
			while (i > j)
			{
				b[i] = sum;
				t += l.get(i);
				i--;
			}
			sum += t;
		}
		return b;
	}

	public TreeNode convertBST(TreeNode root)
	{
		List<Integer> l = new ArrayList<Integer>();
		get(root, l);
		int[] b = getSum(l);
		reset(root, l, b);
		return root;
	}
}

//539. Minimum Time Difference
//Runtime: 10 ms, faster than 76.72% of Java online submissions for Minimum Time Difference.
//Memory Usage: 39.1 MB, less than 83.10% of Java online submissions for Minimum Time Difference.
class Solution539
{
	private static class HM implements Comparable<HM>
	{
		public final int h, m;

		public HM(String in)
		{
			h = (in.charAt(0) - '0') * 10 + (in.charAt(1) - '0');
			m = (in.charAt(3) - '0') * 10 + (in.charAt(4) - '0');
		}

		@Override
		public int compareTo(HM o)
		{
			if (h != o.h)
				return h - o.h;
			return m - o.m;
		}

		public String toString()
		{
			return h + ":" + m;
		}
	}

	private int minDiff(HM t1, HM t2)
	{
		if (t1.compareTo(t2) > 0)
		{
			HM t = t1;
			t1 = t2;
			t2 = t;
		}
		int d = (t2.h - t1.h) * 60 + t2.m - t1.m;
		if (1440 - d < d)
			return 1440 - d;
		return d;
	}

	public int findMinDifference(List<String> timePoints)
	{
		List<HM> l = new ArrayList<HM>();
		for (int i = 0; i < timePoints.size(); i++)
			l.add(new HM(timePoints.get(i)));
		Collections.sort(l);
		int min = minDiff(l.get(0), l.get(l.size() - 1));
		for (int i = 0; i < l.size() - 1; i++)
		{
			int d = minDiff(l.get(i), l.get(i + 1));
			if (d < min)
				min = d;
		}
		return min;
	}
}

//Runtime: 10 ms, faster than 76.72% of Java online submissions for Minimum Time Difference.
//Memory Usage: 37.8 MB, less than 88.73% of Java online submissions for Minimum Time Difference.
class Solution539_2
{
	private static class HM implements Comparable<HM>
	{
		public final int h, m;

		public HM(String in)
		{
			h = (in.charAt(0) - '0') * 10 + (in.charAt(1) - '0');
			m = (in.charAt(3) - '0') * 10 + (in.charAt(4) - '0');
		}

		@Override
		public int compareTo(HM o)
		{
			if (h != o.h)
				return h - o.h;
			return m - o.m;
		}

		public String toString()
		{
			return h + ":" + m;
		}
	}

	private int minDiff(HM t1, HM t2)
	{
		if (t1.compareTo(t2) > 0)
		{
			HM t = t1;
			t1 = t2;
			t2 = t;
		}
		int d = (t2.h - t1.h) * 60 + t2.m - t1.m;
		if (1440 - d < d)
			return 1440 - d;
		return d;
	}

	public int findMinDifference(List<String> timePoints)
	{
		int len = timePoints.size();
		Object[] l = new Object[len];
		for (int i = 0; i < timePoints.size(); i++)
			l[i] = new HM(timePoints.get(i));
		Arrays.parallelSort(l, new Comparator<Object>()
		{
			@Override
			public int compare(Object o1, Object o2)
			{
				return ((HM) o1).compareTo((HM) o2);
			}

		});
		int min = minDiff((HM) l[0], (HM) l[len - 1]);
		for (int i = 0; i < len - 1; i++)
		{
			int d = minDiff((HM) l[i], (HM) l[i + 1]);
			if (d < min)
				min = d;
		}
		return min;
	}
}

//540. Single Element in a Sorted Array
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Single Element in a Sorted Array.
//Memory Usage: 38.3 MB, less than 84.83% of Java online submissions for Single Element in a Sorted Array.
class Solution540
{
	public int singleNonDuplicate(int[] nums)
	{
		int x = 0;
		for (int i = 0; i < nums.length; i++)
			x ^= nums[i];
		return x;

	}
}

public class LC531_540
{
	public static void test538()
	{
		String in = "[5,2,13,2]";
		TreeNode intn = TreeCodec.deserialize(in);
		TreeNode outtn = new Solution538().convertBST(intn);
		String out = TreeCodec.serialize(outtn);
		System.out.println(out);
	}

	public static void main(String[] args)
	{
		test538();
	}
}
