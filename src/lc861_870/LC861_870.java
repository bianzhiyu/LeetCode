package lc861_870;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import treeCodec.*;

//861. Score After Flipping Matrix
//Runtime: 1 ms, faster than 94.31% of Java online submissions for Score After Flipping Matrix.
//Memory Usage: 38.6 MB, less than 6.12% of Java online submissions for Score After Flipping Matrix.
class Solution861
{
	private void f(int[][] A, int r)
	{
		for (int i = 0; i < A[r].length; i++)
			A[r][i] = 1 - A[r][i];
	}

	private int s(int[][] A)
	{
		int t = 0;
		for (int i = 0; i < A.length; i++)
		{
			int t2 = 0, m = 1;
			for (int j = A[i].length - 1; j >= 0; j--)
			{
				t2 += m * A[i][j];
				m = m << 1;
			}
			t += t2;
		}
		return t;
	}

	private void g(int[][] A, int c)
	{
		for (int i = 0; i < A.length; i++)
			A[i][c] = 1 - A[i][c];
	}

	public int matrixScore(int[][] A)
	{
		for (int i = 0; i < A.length; i++)
			if (A[i][0] == 0)
				f(A, i);
		for (int i = 1; i < A[0].length; i++)
		{
			int t = 0;
			for (int j = 0; j < A.length; j++)
				t += A[j][i];
			if (t < A.length - t)
				g(A, i);
		}
		int sum = s(A);
		for (int i = 0; i < A.length; i++)
			f(A, i);
		for (int i = 0; i < A.length; i++)
			A[i][0] = 1;
		for (int i = 1; i < A[0].length; i++)
		{
			int t = 0;
			for (int j = 0; j < A.length; j++)
				t += A[j][i];
			if (t < A.length - t)
				g(A, i);
		}
		int s2 = s(A);
		if (sum > s2)
			return sum;
		return s2;
	}
}

///863. All Nodes Distance K in Binary Tree
//Runtime: 1 ms, faster than 100.00% of Java online submissions for All Nodes Distance K in Binary Tree.
//Memory Usage: 39.8 MB, less than 5.38% of Java online submissions for All Nodes Distance K in Binary Tree.
class Solution863
{
	private List<Integer> ans = new ArrayList<Integer>();

	private int tra(TreeNode rt, boolean fd, int d, TreeNode tar, int K)
	{
		if (rt == null)
			return -2;
		if (rt == tar)
		{
			tra(rt.left, true, 0, tar, K);
			tra(rt.right, true, 0, tar, K);
			if (0 == K)
				ans.add(rt.val);
			return 0;
		}
		if (fd)
		{
			if (d + 1 == K)
				ans.add(rt.val);
			tra(rt.left, true, d + 1, tar, K);
			tra(rt.right, true, d + 1, tar, K);
			return d + 1;
		}
		int dist = tra(rt.left, false, -1, tar, K);
		if (dist >= 0)
		{
			if (dist + 1 == K)
				ans.add(rt.val);
			tra(rt.right, true, dist + 1, tar, K);
			return dist + 1;
		} else
		{
			dist = tra(rt.right, false, -1, tar, K);
			if (dist >= 0)
			{
				if (dist + 1 == K)
					ans.add(rt.val);
				tra(rt.left, true, dist + 1, tar, K);
				return dist + 1;
			} else
				return -1;
		}
	}

	public List<Integer> distanceK(TreeNode root, TreeNode target, int K)
	{
		tra(root, false, -1, target, K);
		return ans;
	}
}

//865. Smallest Subtree with all the Deepest Nodes
//Runtime: 2 ms, faster than 94.89% of Java online submissions for Smallest Subtree with all the Deepest Nodes.
//Memory Usage: 36.8 MB, less than 82.88% of Java online submissions for Smallest Subtree with all the Deepest Nodes.
class Solution865
{
	private HashMap<TreeNode, Integer> hd = new HashMap<TreeNode, Integer>();
	private TreeNode ans = null;

	private void tra(TreeNode rt, int d)
	{
		if (rt == null)
			return;
		tra(rt.left, d + 1);
		tra(rt.right, d + 1);
		hd.put(rt, d);
	}

	private boolean containDeepest(TreeNode rt, int md)
	{
		if (rt == null)
			return false;
		boolean l = containDeepest(rt.left, md), r = containDeepest(rt.right, md);
		if (hd.get(rt) == md)
			ans = rt;
		else if (l && r)
			ans = rt;
		return l || r || hd.get(rt) == md;
	}

	public TreeNode subtreeWithAllDeepest(TreeNode root)
	{
		tra(root, 0);
		int maxd = -1;
		for (TreeNode k : hd.keySet())
			maxd = Math.max(maxd, hd.get(k));
		containDeepest(root, maxd);
		return ans;
	}
}

//869. Reordered Power of 2
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Reordered Power of 2.
//Memory Usage: 32 MB, less than 100.00% of Java online submissions for Reordered Power of 2.
class Solution869
{
	private int[] grp(int x)
	{
		int[] a = new int[10];
		if (x == 0)
		{
			a[0] = 1;
			return a;
		}
		while (x > 0)
		{
			a[x % 10]++;
			x /= 10;
		}
		return a;
	}

	private boolean same(int[] g1, int[] g2)
	{
		for (int i = 0; i < 10; i++)
			if (g1[i] != g2[i])
				return false;
		return true;
	}

	public boolean reorderedPowerOf2(int N)
	{
		int[] ng = grp(N);
		int x = 1;
		int b = Integer.MAX_VALUE / 2;
		while (x < b)
		{
			if (same(grp(x), ng))
				return true;
			x *= 2;
		}
		return false;
	}
}

//870. Advantage Shuffle
//Runtime: 33 ms, faster than 97.65% of Java online submissions for Advantage Shuffle.
//Memory Usage: 43.5 MB, less than 34.78% of Java online submissions for Advantage Shuffle.
class Solution870
{
	private static class Mp implements Comparable<Mp>
	{
		int num, ind;

		public Mp(int n, int i)
		{
			num = n;
			ind = i;
		}

		public int compareTo(Mp o)
		{
			return num - o.num;
		}
	}

	public int[] advantageCount(int[] A, int[] B)
	{
		int len=A.length;
		int[] rem=new int[len];
		int remlen=0;
		boolean[] used=new boolean[len];
		Mp[] nb=new Mp[len];
		for (int i=0;i<len;i++)
			nb[i]=new Mp(B[i],i);
		Arrays.parallelSort(nb);
		Arrays.parallelSort(A);
		int Ap=0;
		int[] ans=new int[len];
		for (int i=0;i<len;i++)
		{
			while (Ap<len && A[Ap]<=nb[i].num)
			{
				rem[remlen]=A[Ap];
				Ap++;
				remlen++;
			}
			if (Ap==len) break;
			ans[nb[i].ind]=A[Ap];
			Ap++;
			used[nb[i].ind]=true;
		}
		Ap=0;
		for (int i=0;i<remlen;i++)
		{
			while (used[Ap]) Ap++;
			ans[Ap]=rem[i];
			Ap++;
		}
		return ans;
	}
}

public class LC861_870
{
	public static void main(String[] args)
	{

	}
}
