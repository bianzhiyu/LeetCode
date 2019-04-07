package lc981_990;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import bbst.MTreeSet;
import treeCodec.*;

//981. Time Based Key-Value Store
//Runtime: 240 ms, faster than 82.95% of Java online submissions for Time Based Key-Value Store.
//Memory Usage: 142 MB, less than 65.22% of Java online submissions for Time Based Key-Value Store.
class TimeMap
{
	private static class DP implements Comparable<DP>
	{
		private int time;
		private String value;

		public DP(int t, String v)
		{
			time = t;
			value = v;
		}

		public int compareTo(DP o)
		{
			return time - o.time;
		}
	}

	private HashMap<String, MTreeSet<DP>> h = new HashMap<String, MTreeSet<DP>>();

	/** Initialize your data structure here. */
	public TimeMap()
	{
	}

	public void set(String key, String value, int timestamp)
	{
		if (!h.containsKey(key))
			h.put(key, new MTreeSet<DP>());
		h.get(key).insert(new DP(timestamp, value));
	}

	public String get(String key, int timestamp)
	{
		if (!h.containsKey(key))
			return "";
		DP d = h.get(key).getNoLargerThanAndMax(new DP(timestamp, null));
		if (d == null)
			return "";
		else
			return d.value;
	}
}

/**
 * Your TimeMap object will be instantiated and called as such: TimeMap obj =
 * new TimeMap(); obj.set(key,value,timestamp); String param_2 =
 * obj.get(key,timestamp);
 */

//982. Triples with Bitwise AND Equal To Zero
//Runtime: 175 ms, faster than 72.04% of Java online submissions for Triples with Bitwise AND Equal To Zero.
//Memory Usage: 43.2 MB, less than 14.28% of Java online submissions for Triples with Bitwise AND Equal To Zero.
class Solution982
{
	public int countTriplets(int[] A)
	{
		HashMap<Integer,Integer> f=new HashMap<Integer,Integer>();
		int len=A.length;
		for (int i=0;i<len;i++)
			for (int j=0;j<len;j++)
				f.put(A[i]&A[j],f.getOrDefault(A[i]&A[j], 0)+1);
		int ct=0;
		for (int d:f.keySet())
			for (int i=0;i<len;i++)
				if ((A[i]&d)==0) ct+=f.get(d);
		return ct;
	}
}

//983. Minimum Cost For Tickets
//Runtime: 9 ms, faster than 10.77% of Java online submissions for Minimum Cost For Tickets.
//Memory Usage: 41.4 MB, less than 5.24% of Java online submissions for Minimum Cost For Tickets.
class Solution983
{
	private int[] d, c;// 1,7,30
	private HashMap<Integer, Integer> r = new HashMap<Integer, Integer>();

	private int dfs(int p, int covered)
	{
		if (p >= d.length)
			return 0;
		int state = p * 500 + covered;
		if (r.containsKey(state))
			return r.get(state);
		int min;
		if (d[p] <= covered)
			min = dfs(p + 1, covered);
		else
		{
			min = c[0] + dfs(p + 1, d[p]);
			min = Math.min(min, c[1] + dfs(p + 1, d[p] + 6));
			min = Math.min(min, c[2] + dfs(p + 1, d[p] + 29));
		}
		r.put(state, min);
		return min;
	}

	public int mincostTickets(int[] days, int[] costs)
	{
		d = days;
		c = costs;
		return dfs(0, -1);
	}
}

//984. String Without AAA or BBB
//Runtime: 0 ms, faster than 100.00% of Java online submissions for String Without AAA or BBB.
//Memory Usage: 35.7 MB, less than 100.00% of Java online submissions for String Without AAA or BBB.
class Solution984
{
	public String strWithout3a3b(int A, int B)
	{
		StringBuilder sb = new StringBuilder();
		int bef = -1;
		while (A > 0 || B > 0)
		{
			if (A > B)
			{
				if (bef == -1)
				{
					if (A >= 2)
					{
						sb.append('a').append('a');
						A -= 2;
						bef = 0;
					} else
					{
						sb.append('a');
						A--;
						bef = 0;
					}
				} else if (bef == 0) // a
				{
					sb.append('b');
					B--;
					bef = 1;
				} else // bef=1, b
				{
					if (A >= 2)
					{
						sb.append('a').append('a');
						A -= 2;
						bef = 0;
					} else
					{
						sb.append('a');
						A--;
						bef = 0;
					}
				}
			} else if (A < B)
			{
				if (bef == -1)
				{
					if (B >= 2)
					{
						sb.append('b').append('b');
						B -= 2;
						bef = 1;
					} else
					{
						sb.append('B');
						B--;
						bef = 1;
					}
				} else if (bef == 1) // b
				{
					sb.append('a');
					A--;
					bef = 0;
				} else // bef=2, a
				{
					if (B >= 2)
					{
						sb.append('b').append('b');
						B -= 2;
						bef = 1;
					} else
					{
						sb.append('b');
						B--;
						bef = 1;
					}
				}
			} else// A==B
			{
				if (bef == 0) // a
				{
					sb.append('b');
					B--;
					bef = 1;
				} else
				{
					sb.append('a');
					A--;
					bef = 0;
				}
			}
		}
		return sb.toString();
	}
}

//986. Interval List Intersections
//Runtime: 2 ms, faster than 100.00% of Java online submissions for Interval List Intersections.
//Memory Usage: 46.6 MB, less than 50.69% of Java online submissions for Interval List Intersections.
class Interval
{
	int start;
	int end;

	Interval()
	{
		start = 0;
		end = 0;
	}

	Interval(int s, int e)
	{
		start = s;
		end = e;
	}
}

class Solution986
{
	List<Interval> t = new ArrayList<Interval>();

	private Interval[] toArr()
	{
		Interval[] a = new Interval[t.size()];
		for (int i = 0; i < t.size(); i++)
			a[i] = t.get(i);
		return a;
	}

	private Interval joint(Interval a, Interval b)
	{
		if (a.end < b.start || a.start > b.end)
			return null;
		return new Interval(Math.max(a.start, b.start), Math.min(a.end, b.end));
	}

	private int add(Interval[] A, Interval ib, int pre)
	{
		int i = pre;
		Interval j;
		while (i < A.length && A[i].end < ib.start)
			i++;
		int np = i;
		while (i < A.length && (j = joint(A[i++], ib)) != null)
			t.add(j);
		return np;
	}

	public Interval[] intervalIntersection(Interval[] A, Interval[] B)
	{
		if (B.length == 0 || A.length == 0)
			return toArr();
		int pre = 0;
		for (Interval ib : B)
			pre = add(A, ib, pre);
		return toArr();
	}
}

//987. Vertical Order Traversal of a Binary Tree
//Runtime: 3 ms, faster than 98.13% of Java online submissions for Vertical Order Traversal of a Binary Tree.
//Memory Usage: 37.1 MB, less than 95.65% of Java online submissions for Vertical Order Traversal of a Binary Tree.
class Solution987
{
	private HashMap<Integer, List<R>> h = new HashMap<Integer, List<R>>();

	private static class R implements Comparable<R>
	{
		private int y, v;

		public R(int _y, int _v)
		{
			y = _y;
			v = _v;
		}

		public int compareTo(R o)
		{
			if (y != o.y)
				return o.y - y;
			return v - o.v;
		}
	}

	private void trav(TreeNode rt, int x, int y)
	{
		if (rt == null)
			return;
		if (!h.containsKey(x))
			h.put(x, new ArrayList<R>());
		h.get(x).add(new R(y, rt.val));
		trav(rt.left, x - 1, y - 1);
		trav(rt.right, x + 1, y - 1);
	}

	public List<List<Integer>> verticalTraversal(TreeNode root)
	{
		trav(root, 0, 0);
		List<Integer> xs = new ArrayList<Integer>(h.keySet());
		Collections.sort(xs);
		List<List<Integer>> ans = new ArrayList<List<Integer>>();
		for (int x : xs)
		{
			List<R> ns = h.get(x);
			Collections.sort(ns);
			List<Integer> t = new ArrayList<Integer>();
			for (R r : ns)
				t.add(r.v);
			ans.add(t);
		}
		return ans;
	}
}

//988. Smallest String Starting From Leaf
//Runtime: 1 ms, faster than 99.97% of Java online submissions for Smallest String Starting From Leaf.
//Memory Usage: 37.9 MB, less than 12.65% of Java online submissions for Smallest String Starting From Leaf.
class Solution988
{
	private int[] min = null;
	private int[] stack = new int[10000];

	private void checkMin(int len)
	{
		if (min == null)
		{
			min = Arrays.copyOf(stack, len);
			return;
		}
		int minp = min.length - 1, np = len - 1;
		boolean smaller = false;
		while (minp >= 0 && np >= 0)
		{
			if (stack[np] > min[minp])
				return;
			if (stack[np] < min[minp])
			{
				smaller = true;
				break;
			}
			minp--;
			np--;
		}
		if (smaller || (minp >= 0))
			min = Arrays.copyOf(stack, len);
	}

	private void trav(TreeNode rt, int st)
	{
		stack[st] = rt.val;
		if (rt.left == null && rt.right == null)
		{
			checkMin(st + 1);
			return;
		}
		if (rt.left != null)
			trav(rt.left, st + 1);
		if (rt.right != null)
			trav(rt.right, st + 1);
	}

	private String getAns()
	{
		StringBuilder sb = new StringBuilder();
		for (int i = min.length - 1; i >= 0; i--)
			sb.append((char) (min[i] + 'a'));
		return sb.toString();
	}

	public String smallestFromLeaf(TreeNode root)
	{
		trav(root, 0);
		return getAns();
	}
}

//990. Satisfiability of Equality Equations
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Satisfiability of Equality Equations.
//Memory Usage: 38.8 MB, less than 35.47% of Java online submissions for Satisfiability of Equality Equations.
class Solution990
{
	private int getTop(int[] f, int x)
	{
		int p = x;
		while (f[p] != p)
			p = f[p];
		while (f[x] != p)
		{
			int q = f[x];
			f[x] = p;
			x = q;
		}
		return p;
	}

	private void merge(int[] f, int x, int y)
	{
		f[getTop(f, x)] = getTop(f, y);
	}

	public boolean equationsPossible(String[] equations)
	{
		int[] father = new int[26];
		Arrays.fill(father, -1);
		for (String S : equations)
		{
			int l = S.charAt(0) - 'a', r = S.charAt(3) - 'a';
			if (father[l] == -1)
				father[l] = l;
			if (father[r] == -1)
				father[r] = r;
			if (S.charAt(1) == '=')
				merge(father, l, r);
		}
		for (String S : equations)
			if (S.charAt(1) == '!')
				if (getTop(father, S.charAt(0) - 'a') == getTop(father, S.charAt(3) - 'a'))
					return false;
		return true;
	}
}

public class LC981_990
{
	public static void test983()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input983.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output983.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int[] d = test.Test.parseIntArr(inLine);
				int[] c = test.Test.parseIntArr(bfr.readLine());

				Solution983 s = new Solution983();
				int ans = s.mincostTickets(d, c);
				System.out.println(ans);

				bfw.write("" + ans);
				bfw.newLine();
			}

			bfr.close();
			bfw.flush();
			bfw.close();
		} catch (IOException e)
		{
			System.out.println(e.toString());
		}
	}

	public static void test988()
	{
		String d = "[25,1,3,1,3,0,2]";
//				"[0,1,2,3,4,3,4]";
		TreeNode r = TreeCodec.deserialize(d);
		Solution988 s = new Solution988();
		String ans = s.smallestFromLeaf(r);
		System.out.println(ans);
	}

	public static void main(String[] args)
	{
		test988();
	}
}
