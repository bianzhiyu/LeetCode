package lc931_940;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeSet;

import treeCodec.TreeNode;

//931. Minimum Falling Path Sum
//Runtime: 3 ms, faster than 98.25% of Java online submissions for Minimum Falling Path Sum.
//Memory Usage: 38.9 MB, less than 90.32% of Java online submissions for Minimum Falling Path Sum.
class Solution931
{
	public int minFallingPathSum(int[][] A)
	{
		int len = A.length;
		for (int i = 1; i < len; i++)
		{
			for (int j = 0; j < len; j++)
			{
				int t = A[i - 1][j];
				if (j > 0 && A[i - 1][j - 1] < t)
					t = A[i - 1][j - 1];
				if (j < len - 1 && A[i - 1][j + 1] < t)
					t = A[i - 1][j + 1];
				A[i][j] += t;
			}
		}
		int min = A[len - 1][0];
		for (int i = 1; i < len; i++)
			if (A[len - 1][i] < min)
				min = A[len - 1][i];
		return min;
	}
}

//932. Beautiful Array
//Time Limit Exceeded
class Solution932
{
	private int[] dfs(int[] stack, int st, TreeSet<Integer> cand, int N)
	{
		if (st == stack.length)
			return stack;
		for (int i = 1; i <= N; i++)
			if (cand.contains(i))
			{
				boolean canBe = true;
				for (int j = 0; j < st; j++)
					if (cand.contains(i + i - stack[j]))
					{
						canBe = false;
						break;
					}
				if (canBe)
				{
					cand.remove(i);
					stack[st] = i;
					int[] ans = dfs(stack, st + 1, cand, N);
					if (ans.length > 0)
						return ans;
					cand.add(i);
				}
			}
		return new int[0];
	}

	public int[] beautifulArray(int N)
	{
		TreeSet<Integer> h = new TreeSet<Integer>();
		for (int i = 0; i < N; i++)
			h.add(i + 1);
		return dfs(new int[N], 0, h, N);
	}
}

//A genius solution:
//https://leetcode.com/problems/beautiful-array/solution/
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Beautiful Array.
//Memory Usage: 34.2 MB, less than 100.00% of Java online submissions for Beautiful Array.
class Solution932_2
{
	Map<Integer, int[]> memo;

	public int[] beautifulArray(int N)
	{
		memo = new HashMap<Integer, int[]>();
		return f(N);
	}

	public int[] f(int N)
	{
		if (memo.containsKey(N))
			return memo.get(N);

		int[] ans = new int[N];
		if (N == 1)
		{
			ans[0] = 1;
		} else
		{
			int t = 0;
			for (int x : f((N + 1) / 2)) // odds
				ans[t++] = 2 * x - 1;
			for (int x : f(N / 2)) // evens
				ans[t++] = 2 * x;
		}
		memo.put(N, ans);
		return ans;
	}
}

//934. Shortest Bridge
//Runtime: 426 ms, faster than 5.06% of Java online submissions for Shortest Bridge.
//Memory Usage: 55 MB, less than 5.10% of Java online submissions for Shortest Bridge.
class Solution934
{
	private final static int[][] di = new int[][]
	{
			{ 1, 0 },
			{ 0, 1 },
			{ -1, 0 },
			{ 0, -1 } };

	private HashSet<Integer> bfs(int[][] A)
	{
		int N = A.length;
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (A[i][j] == 1)
				{
					Queue<Integer> q = new LinkedList<Integer>();
					HashSet<Integer> used = new HashSet<Integer>();
					q.add(i * 111 + j);
					used.add(i * 111 + j);
					while (!q.isEmpty())
					{
						int state = q.remove();
						int y = state % 111;
						int x = (state - y) / 111;
						for (int k = 0; k < 4; k++)
						{
							int nx = x + di[k][0];
							int ny = y + di[k][1];
							int ns = nx * 111 + ny;
							if (nx >= 0 && ny >= 0 && nx < N && ny < N && A[nx][ny] == 1 && !used.contains(ns))
							{
								used.add(ns);
								q.add(ns);
							}
						}
					}
					return used;
				}
		return null;
	}

	private void Color(int[][] A, HashSet<Integer> i)
	{
		for (int s : i)
		{
			int y = s % 111;
			int x = (s - y) / 111;
			A[x][y] = 2;
		}
	}

	public int shortestBridge(int[][] A)
	{
		HashSet<Integer> ild1 = bfs(A);
		Color(A, ild1);
		HashSet<Integer> ild2 = bfs(A);
		int min = Integer.MAX_VALUE;
		for (int s1 : ild1)
			for (int s2 : ild2)
			{
				int y1 = s1 % 111;
				int x1 = (s1 - y1) / 111;
				int y2 = s2 % 111;
				int x2 = (s2 - y2) / 111;
				min = Math.min(min, Math.abs(x1 - x2) + Math.abs(y1 - y2));
			}
		return min - 1;
	}
}

//935. Knight Dialer
//Runtime: 26 ms, faster than 79.46% of Java online submissions for Knight Dialer.
//Memory Usage: 34.6 MB, less than 100.00% of Java online submissions for Knight Dialer.
class Solution935
{
	private final static int MOD = 1_000_000_007;

	public int knightDialer(int N)
	{
		int[][] d = new int[N][10];
		for (int i = 0; i < 10; i++)
			d[0][i] = 1;
		for (int i = 1; i < N; i++)
		{
			d[i][0] = (d[i - 1][4] + d[i - 1][6]) % MOD;
			d[i][1] = (d[i - 1][6] + d[i - 1][8]) % MOD;
			d[i][2] = (d[i - 1][7] + d[i - 1][9]) % MOD;
			d[i][3] = (d[i - 1][4] + d[i - 1][8]) % MOD;
			d[i][4] = ((d[i - 1][3] + d[i - 1][9]) % MOD + d[i - 1][0]) % MOD;
			d[i][5] = 0;
			d[i][6] = ((d[i - 1][1] + d[i - 1][7]) % MOD + d[i - 1][0]) % MOD;
			d[i][7] = (d[i - 1][2] + d[i - 1][6]) % MOD;
			d[i][8] = (d[i - 1][1] + d[i - 1][3]) % MOD;
			d[i][9] = (d[i - 1][2] + d[i - 1][4]) % MOD;

		}
		int s = 0;
		for (int i = 0; i < 10; i++)
			s = (s + d[N - 1][i]) % MOD;
		return s;
	}
}

//938. Range Sum of BST
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Range Sum of BST.
//Memory Usage: 48.7 MB, less than 5.13% of Java online submissions for Range Sum of BST.
class Solution938
{
	public int rangeSumBST(TreeNode root, int L, int R)
	{
		if (root == null)
			return 0;
		if (root.val < L)
			return rangeSumBST(root.right, L, R);
		if (root.val > R)
			return rangeSumBST(root.left, L, R);
		return root.val + rangeSumBST(root.right, L, R) + rangeSumBST(root.left, L, R);
	}
}

//939. Minimum Area Rectangle
//https://leetcode.com/problems/minimum-area-rectangle/solution/
//Approach 2: Count by Diagonal
//Runtime: 239 ms, faster than 68.44% of Java online submissions for Minimum Area Rectangle.
//Memory Usage: 53.8 MB, less than 51.24% of Java online submissions for Minimum Area Rectangle.
class Solution939
{
	public int minAreaRect(int[][] points)
	{
		HashMap<Integer, HashMap<Integer, Boolean>> h = new HashMap<Integer, HashMap<Integer, Boolean>>();
		for (int[] p : points)
		{
			if (!h.containsKey(p[0]))
				h.put(p[0], new HashMap<Integer, Boolean>());
			h.get(p[0]).put(p[1], true);
		}
		boolean hasRect = false;
		int min = Integer.MAX_VALUE;
		for (int[] a : points)
			for (int[] b : points)
				if (a[0] != b[0] && a[1] != b[1] && h.get(b[0]).getOrDefault(a[1], false)
						&& h.get(a[0]).getOrDefault(b[1], false))
				{
					hasRect = true;
					int ar = Math.abs(a[0] - b[0]) * Math.abs(a[1] - b[1]);
					if (ar < min)
						min = ar;
				}
		return hasRect ? min : 0;
	}
}

public class LC931_940
{
	public static void main(String[] args)
	{

	}
}
