package lc961_970;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import treeCodec.TreeNode;

//962. Maximum Width Ramp
//https://leetcode.com/problems/maximum-width-ramp/solution/
//by awice
//After modification.
//Runtime: 36 ms, faster than 65.80% of Java online submissions for Maximum Width Ramp.
//Memory Usage: 50.5 MB, less than 91.43% of Java online submissions for Maximum Width Ramp.
class Solution962
{
	private static class Cmp implements Comparator<Integer>
	{
		private int[] a;

		public Cmp(int[] A)
		{
			a = A;
		}

		@Override
		public int compare(Integer o1, Integer o2)
		{
			if (a[o1] != a[o2])
				return a[o1] - a[o2];
			return o1 - o2;
		}
	}

	public int maxWidthRamp(int[] A)
	{
		int N = A.length;
		Integer[] B = new Integer[N];
		for (int i = 0; i < N; ++i)
			B[i] = i;
		Arrays.sort(B, new Cmp(A));
		int ans = 0, m = N;
		for (int i : B)
		{
			ans = Math.max(ans, i - m);
			m = Math.min(m, i);
		}
		return ans;
	}
}

//963. Minimum Area Rectangle II
//Runtime: 55 ms, faster than 45.35% of Java online submissions for Minimum Area Rectangle II.
//Memory Usage: 39.6 MB, less than 52.38% of Java online submissions for Minimum Area Rectangle II.
class Solution963
{
	private static double ERR = 1e-8;

	private int[] vec(int[] p1, int[] p2)
	{
		return new int[]
		{ p2[0] - p1[0], p2[1] - p1[1] };
	}

	private int ip(int[] v1, int[] v2)
	{
		return v1[0] * v2[0] + v1[1] * v2[1];
	}

	private int[] add(int[] p, int[] v)
	{
		return new int[]
		{ p[0] + v[0], p[1] + v[1] };
	}

	private double ln(int[] v)
	{
		return Math.sqrt(v[0] * v[0] + v[1] * v[1]);
	}

	public double minAreaFreeRect(int[][] points)
	{
		HashMap<Integer, HashSet<Integer>> h = new HashMap<Integer, HashSet<Integer>>();
		for (int[] p : points)
		{
			if (!h.containsKey(p[0]))
				h.put(p[0], new HashSet<Integer>());
			h.get(p[0]).add(p[1]);
		}
		double min = -1;
		int[] v1, v2;
		for (int[] p1 : points)
			for (int[] p2 : points)
				if (ln(v1 = vec(p1, p2)) > ERR)
					for (int[] p3 : points)
						if (ln(v2 = vec(p1, p3)) > ERR && ip(v1, v2) == 0 && ln(vec(p2, p3)) > ERR)
						{
							int[] p4 = add(p2, v2);
							if (h.containsKey(p4[0]) && h.get(p4[0]).contains(p4[1]))
							{
								double ar = ln(v1) * ln(v2);
								if (min < -0.5 || min > ar)
									min = ar;
							}
						}
		return min < -0.5 ? 0 : min;
	}
}

//967. Numbers With Same Consecutive Differences
//Runtime: 4 ms, faster than 100.00% of Java online submissions for Numbers With Same Consecutive Differences.
//Memory Usage: 40.5 MB, less than 6.82% of Java online submissions for Numbers With Same Consecutive Differences.
class Solution967
{
	private int[] toArr(List<Integer> l)
	{
		int[] a = new int[l.size()];
		for (int i = 0; i < l.size(); i++)
			a[i] = l.get(i);
		return a;
	}

	private void dfs(List<Integer> ans, int[] stack, int st, int N, int K, int bef)
	{
		if (st == 0)
		{
			for (int i = 1; i <= 9; i++)
			{
				stack[0] = i;
				dfs(ans, stack, 1, N, K, i);
			}
			return;
		}
		if (st == N)
		{
			int x = 0;
			for (int i = 0; i < N; i++)
				x = x * 10 + stack[i];
			ans.add(x);
			return;
		}
		if (bef + K <= 9)
		{
			stack[st] = bef + K;
			dfs(ans, stack, st + 1, N, K, bef + K);
		}
		if (K != 0 && bef - K >= 0)
		{
			stack[st] = bef - K;
			dfs(ans, stack, st + 1, N, K, bef - K);
		}
	}

	public int[] numsSameConsecDiff(int N, int K)
	{
		List<Integer> ans = new ArrayList<Integer>();
		if (N == 1)
			return new int[]
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		dfs(ans, new int[N], 0, N, K, 0);
		return toArr(ans);
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
	int tot = 0;

	public int minCameraCover(TreeNode root)
	{
		return (dfs(root) < 1 ? 1 : 0) + tot;
	}

	int dfs(TreeNode rt)
	{
		if (rt == null)
			return 2;
		int left = dfs(rt.left), right = dfs(rt.right);
		if (left == 0 || right == 0)
		{
			tot++;
			return 1;
		}
		if (left == 1 || right == 1)
			return 2;
		// otherwise: left==2 && right==2
		return 0;
	}
}

//969. Pancake Sorting
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Pancake Sorting.
//Memory Usage: 37.9 MB, less than 90.29% of Java online submissions for Pancake Sorting.
class Solution969
{
	private void flip(int[] A,int l, int r)
	{
		int t;
		while (l<r)
		{
			t=A[l];
			A[l]=A[r];
			A[r]=t;
			l++;r--;
		}
	}
	public List<Integer> pancakeSort(int[] A)
	{
		List<Integer> ans=new ArrayList<Integer>();
		int len=A.length;
		for (int i=0;i<len-1;i++)
		{
			int maxind=0;
			for (int j=1;j<len-i;j++)
				if (A[j]>=A[maxind]) maxind=j;
			if (maxind==len-1-i) continue;
			ans.add(maxind+1);
			ans.add(len-i);
			flip(A,0,maxind);
			flip(A,0,len-i-1);
		}
		return ans;
	}
}

public class LC961_970
{
	public static void main(String[] args)
	{
		Solution962 s = new Solution962();
		int[] a = new int[]
		{ 6, 6, 6, 6, 6, 6 };
//				{6,0,8,2,1,5};
		int ans = s.maxWidthRamp(a);
		System.out.println(ans);
	}
}
