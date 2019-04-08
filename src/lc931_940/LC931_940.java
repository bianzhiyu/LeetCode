package lc931_940;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
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

//936. Stamping The Sequence
//136 / 262 test cases passed.
//Wrong Answer
class Solution936
{
	public int[] movesToStamp(String stamp, String target)
	{
		char[] stp = stamp.toCharArray(), tar = target.toCharArray();
		int len = tar.length;
		int[] pre = new int[len + 1];
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] used = new boolean[len + 1];
		q.add(0);
		used[0] = true;
		while (!q.isEmpty())
		{
			int h = q.remove();
			if (h + stp.length > len)
				continue;
			for (int i = 0; i < stp.length && h + i < len; i++)
			{
				if (tar[h + i] == stp[i])
				{
					if (!used[h + i + 1])
					{
						used[h + i + 1] = true;
						pre[h + i + 1] = h;
						q.add(h + i + 1);
					}
				} else
					break;
			}
		}
		if (!used[len])
			return new int[0];
		LinkedList<Integer> l = new LinkedList<Integer>();
		int p = len, ct = 0;
		while (p != 0)
		{
			l.addLast(pre[p]);
			p = pre[p];
			ct++;
		}
		int[] ans = new int[ct];
		p = 0;
		for (int i : l)
			ans[ct - (++p)] = i;
		return ans;
	}
}

//https://leetcode.com/problems/stamping-the-sequence/solution/
//awice
//Runtime: 53 ms, faster than 31.56% of Java online submissions for Stamping The Sequence.
//Memory Usage: 42.8 MB, less than 8.00% of Java online submissions for Stamping The Sequence.
class Solution936_2
{
	private static class Node
	{
		private Set<Integer> made, todo;

		private Node(Set<Integer> m, Set<Integer> t)
		{
			made = m;
			todo = t;
		}
	}

	public int[] movesToStamp(String stamp, String target)
	{
		int M = stamp.length(), N = target.length();
		Queue<Integer> queue = new ArrayDeque<Integer>();
		boolean[] done = new boolean[N];
		Stack<Integer> ans = new Stack<Integer>();
		List<Node> A = new ArrayList<Node>();

		for (int i = 0; i <= N - M; ++i)
		{
			// For each window [i, i+M), A[i] will contain
			// info on what needs to change before we can
			// reverse stamp at this window.

			Set<Integer> made = new HashSet<Integer>();
			Set<Integer> todo = new HashSet<Integer>();
			for (int j = 0; j < M; ++j)
			{
				if (target.charAt(i + j) == stamp.charAt(j))
					made.add(i + j);
				else
					todo.add(i + j);
			}

			A.add(new Node(made, todo));

			// If we can reverse stamp at i immediately,
			// enqueue letters from this window.
			if (todo.isEmpty())
			{
				ans.push(i);
				for (int j = i; j < i + M; ++j)
					if (!done[j])
					{
						queue.add(j);
						done[j] = true;
					}
			}
		}

		// For each enqueued letter (position),
		while (!queue.isEmpty())
		{
			int i = queue.poll();

			// For each window that is potentially affected,
			// j: start of window
			for (int j = Math.max(0, i - M + 1); j <= Math.min(N - M, i); ++j)
			{
				if (A.get(j).todo.contains(i))
				{ // This window is affected
					A.get(j).todo.remove(i);
					if (A.get(j).todo.isEmpty())
					{
						ans.push(j);
						for (int m : A.get(j).made)
							if (!done[m])
							{
								queue.add(m);
								done[m] = true;
							}
					}
				}
			}
		}

		for (boolean b : done)
			if (!b)
				return new int[0];

		int[] ret = new int[ans.size()];
		int t = 0;
		while (!ans.isEmpty())
			ret[t++] = ans.pop();

		return ret;
	}
}

//state_0: target, 
// for state_i
//find a window: window set as stamp can match state_0
// push its start to ans
// state_i+1 is the new state
// loop for i+1

//Runtime: 36 ms, faster than 40.00% of Java online submissions for Stamping The Sequence.
//Memory Usage: 38.1 MB, less than 100.00% of Java online submissions for Stamping The Sequence.

//I find this solution by myself, and it is inspired from awice's solution.
//My solution is more simpler.
//However, my solution is faster than awice's by the leet code judger.
//This is weird.

//In addition, the correctness of both awice's and my solutions are not proved.
class Solution936_3
{
	public int[] movesToStamp(String stamp, String target)
	{
		char[] state = target.toCharArray();
		char[] stp = stamp.toCharArray();
		int len = state.length, stplen = stp.length;
		int ct = 0;
		Stack<Integer> s = new Stack<Integer>();
		while (true)
		{
			int windst = -1;
			for (int i = 0; i <= len - stplen; i++)
			{
				int tmp = 0;
				boolean canmatch = true;
				for (int j = i; j < i + stplen; j++)
				{
					if (state[j] != '?')
					{
						if (state[j] == stp[j - i])
						{
							tmp++;
						} else
						{
							canmatch = false;
							break;
						}
					}
				}
				if (canmatch && tmp > 0)
				{
					windst = i;
					break;
				}
			}
			if (windst == -1)
				return new int[0];
			else
			{
				s.push(windst);
				for (int j = windst; j < windst + stplen; j++)
					if (state[j] != '?')
					{
						state[j] = '?';
						ct++;
					}
				if (ct == len)
					break;
			}
		}

		int[] ans = new int[s.size()];
		for (int i = 0; i < ans.length; i++)
			ans[i] = s.pop();
		return ans;
	}
}

//the fastest, other's
class Solution936_4
{
	public int[] movesToStamp(String stamp, String target)
	{
		char[] S = stamp.toCharArray();
		char[] T = target.toCharArray();
		List<Integer> list = new ArrayList<>();
		boolean[] visited = new boolean[T.length];
		int starCnt = 0;
		while (starCnt < T.length)
		{
			boolean findReplace = false;
			for (int i = 0; i <= T.length - S.length; i++)
			{
				if (!visited[i] && canReplace(S, T, i))
				{
					visited[i] = true;
					findReplace = true;
					starCnt = replace(T, i, S.length, starCnt);
					list.add(i);
				}
				if (starCnt == T.length)
					break;
			}
			if (!findReplace)
				return new int[0];
		}
		int[] res = new int[list.size()];
		int i = 0, j = list.size() - 1;
		while (j >= 0)
		{
			res[i++] = list.get(j--);
		}
		return res;
	}

	private boolean canReplace(char[] S, char[] T, int offset)
	{
		for (int i = 0; i < S.length; i++)
		{
			if (T[i + offset] != S[i] && T[i + offset] != '*')
				return false;
		}
		return true;
	}

	private int replace(char[] T, int offset, int len, int starCnt)
	{
		for (int i = 0; i < len; i++)
		{
			if (T[i + offset] != '*')
			{
				T[i + offset] = '*';
				starCnt++;
			}
		}
		return starCnt;
	}
}

//speed my own solution_3
//Runtime: 12 ms, faster than 75.11% of Java online submissions for Stamping The Sequence.
//Memory Usage: 38.4 MB, less than 100.00% of Java online submissions for Stamping The Sequence.
class Solution936_5
{
	public int[] movesToStamp(String stamp, String target)
	{
		char[] state = target.toCharArray();
		char[] stp = stamp.toCharArray();
		int len = state.length, stplen = stp.length;
		boolean[] skip = new boolean[len];
		int ct = 0;
		Stack<Integer> s = new Stack<Integer>();
		while (true)
		{
			int windst = -1;
			for (int i = 0; i <= len - stplen; i++)
			{
				if (skip[i])
					continue;
				int tmp = 0;
				boolean canmatch = true;
				for (int j = i; j < i + stplen; j++)
				{

					if (state[j] != '?')
					{
						if (state[j] == stp[j - i])
							tmp++;
						else
						{
							canmatch = false;
							break;
						}
					}
				}

				if (canmatch)
				{
					if (tmp > 0)
					{
						windst = i;
						break;
					} else
						skip[i] = true;
				}
			}
			if (windst == -1)
				return new int[0];
			else
			{
				s.push(windst);
				for (int j = windst; j < windst + stplen; j++)
					if (state[j] != '?')
					{
						state[j] = '?';
						ct++;
					}
				if (ct == len)
					break;
			}
		}

		int[] ans = new int[s.size()];
		for (int i = 0; i < ans.length; i++)
			ans[i] = s.pop();
		return ans;
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
	public static void test936()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input936.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output936.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				String stamp = inLine.substring(1, inLine.length() - 1);
				inLine = bfr.readLine();
				String target = inLine.substring(1, inLine.length() - 1);

				Solution936_3 s = new Solution936_3();

				int[] ans = s.movesToStamp(stamp, target);

				String out = test.Test.intArrToString(ans);

				System.out.println(out);

				bfw.write(out);
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

	public static void main(String[] args)
	{
		test936();
	}
}
