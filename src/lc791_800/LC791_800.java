package lc791_800;

import java.util.ArrayList;
import java.util.List;
import treeCodec.*;

//791. Custom Sort String
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Custom Sort String.
//Memory Usage: 35.7 MB, less than 89.19% of Java online submissions for Custom Sort String.
class Solution791
{
	public String customSortString(String S, String T)
	{
		int[] ct = new int[26];
		for (int i = 0; i < T.length(); i++)
			ct[T.charAt(i) - 'a']++;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < S.length(); i++)
		{
			int t;
			if ((t = ct[S.charAt(i) - 'a']) > 0)
			{
				char c = S.charAt(i);
				for (int j = 0; j < t; j++)
					sb.append(c);
				ct[S.charAt(i) - 'a'] = 0;
			}
		}
		for (int i = 0; i < 26; i++)
			if (ct[i] > 0)
			{
				char c = (char) (i + 'a');
				for (int j = 0; j < ct[i]; j++)
					sb.append(c);
			}

		return sb.toString();
	}
}

//792. Number of Matching Subsequences
//Runtime: 69 ms, faster than 79.13% of Java online submissions for Number of Matching Subsequences.
//Memory Usage: 42.9 MB, less than 30.34% of Java online submissions for Number of Matching Subsequences.
class Solution792
{
	private int sch(List<Integer> lt, int b)
	{
		int l = 0, r = lt.size() - 1;
		if (r < 0 || lt.get(r) <= b)
			return -1;
		while (l < r)
		{
			if (r == l + 1)
			{
				if (lt.get(l) > b)
					return lt.get(l);
				else
					return lt.get(r);
			}
			int m = (l + r) / 2;
			if (lt.get(m) > b)
				r = m;
			else
				l = m + 1;
		}
		return lt.get(r);
	}

	public int numMatchingSubseq(String S, String[] words)
	{
		List<List<Integer>> l = new ArrayList<List<Integer>>(26);
		for (int i = 0; i < 26; i++)
			l.add(new ArrayList<Integer>());
		for (int i = 0; i < S.length(); i++)
			l.get(S.charAt(i) - 'a').add(i);
		int ct = 0;
		for (int i = 0; i < words.length; i++)
		{
			int before = -1;
			boolean succ = true;
			for (int j = 0; j < words[i].length(); j++)
			{
				before = sch(l.get(words[i].charAt(j) - 'a'), before);
				if (before == -1)
				{
					succ = false;
					break;
				}
			}
			if (succ)
				ct++;
		}
		return ct;
	}
}

//793. Preimage Size of Factorial Zeroes Function
//Runtime: 61 ms, faster than 50.12% of Java online submissions for Binary Search Tree Iterator.
//Memory Usage: 45 MB, less than 97.29% of Java online submissions for Binary Search Tree Iterator.

class BSTIterator
{
	public static class LN
	{
		int val;
		LN next;

		public LN(int x)
		{
			val = x;
		}

		public LN()
		{
		}
	}

	LN head;

	private LN Trav(TreeNode root, LN p)
	{
		if (root == null)
			return p;
		p = Trav(root.left, p);
		p.next = new LN(root.val);
		p = p.next;
		p = Trav(root.right, p);
		return p;
	}

	public BSTIterator(TreeNode root)
	{
		head = new LN();
		Trav(root, head);
	}

	/** @return the next smallest number */
	public int next()
	{
		head = head.next;
		if (head != null)
			return head.val;
		return 0;
	}

	/** @return whether we have a next smallest number */
	public boolean hasNext()
	{
		return head.next != null;
	}
}

//794. Valid Tic-Tac-Toe State
//Runtime: 453 ms, faster than 5.34% of Java online submissions for Valid Tic-Tac-Toe State.
//Memory Usage: 36.2 MB, less than 88.31% of Java online submissions for Valid Tic-Tac-Toe State.
class Solution794
{
	private int[] tar;

	private boolean win(int[] b, int t)
	{
		if (b[0] == t && b[3] == t && b[6] == t)
			return true;
		if (b[1] == t && b[4] == t && b[7] == t)
			return true;
		if (b[2] == t && b[5] == t && b[8] == t)
			return true;
		if (b[0] == t && b[1] == t && b[2] == t)
			return true;
		if (b[3] == t && b[4] == t && b[5] == t)
			return true;
		if (b[6] == t && b[7] == t && b[8] == t)
			return true;
		if (b[0] == t && b[4] == t && b[8] == t)
			return true;
		if (b[2] == t && b[4] == t && b[6] == t)
			return true;
		return false;
	}

	private boolean same(int[] b)
	{
		for (int i = 0; i < 9; i++)
			if (b[i] != tar[i])
				return false;
		return true;
	}

	private boolean play(int[] b, int t)
	{
		for (int i = 0; i < 9; i++)
			if (b[i] == 0)
			{
				b[i] = t;
				if (same(b))
					return true;
				if (!win(b, t) && play(b, 3 - t))
					return true;
				b[i] = 0;
			}
		return false;
	}

	public boolean validTicTacToe(String[] board)
	{
		tar = new int[9];
		for (int i = 0; i < 9; i++)
		{
			int x = i / 3, y = i % 3;
			if (board[x].charAt(y) == ' ')
				tar[i] = 0;
			else if (board[x].charAt(y) == 'X')
				tar[i] = 1;
			else
				tar[i] = 2;
		}
		if (same(new int[9]))
			return true;
		return play(new int[9], 1);
	}
}

//Runtime: 3 ms, faster than 93.12% of Java online submissions for Valid Tic-Tac-Toe State.
//Memory Usage: 37.7 MB, less than 5.19% of Java online submissions for Valid Tic-Tac-Toe State.
class Solution794_2
{
	private int[] tar = new int[9];
	private List<List<Integer>> poss = new ArrayList<List<Integer>>();

	private boolean win(int[] b, int t)
	{
		if (b[0] == t && b[3] == t && b[6] == t)
			return true;
		if (b[1] == t && b[4] == t && b[7] == t)
			return true;
		if (b[2] == t && b[5] == t && b[8] == t)
			return true;
		if (b[0] == t && b[1] == t && b[2] == t)
			return true;
		if (b[3] == t && b[4] == t && b[5] == t)
			return true;
		if (b[6] == t && b[7] == t && b[8] == t)
			return true;
		if (b[0] == t && b[4] == t && b[8] == t)
			return true;
		if (b[2] == t && b[4] == t && b[6] == t)
			return true;
		return false;
	}

	private boolean same(int[] b)
	{
		for (int i = 0; i < 9; i++)
			if (b[i] != tar[i])
				return false;
		return true;
	}

	private boolean play(int[] b, int t)
	{
		for (int i : poss.get(t))
			if (b[i] == 0)
			{
				b[i] = t;
				if (same(b))
					return true;
				if (!win(b, t) && play(b, 3 - t))
					return true;
				b[i] = 0;
			}
		return false;
	}

	public boolean validTicTacToe(String[] board)
	{
		for (int i = 0; i < 3; i++)
			poss.add(new ArrayList<Integer>());
		for (int i = 0; i < 9; i++)
		{
			int x = i / 3, y = i % 3;
			if (board[x].charAt(y) == ' ')
				tar[i] = 0;
			else if (board[x].charAt(y) == 'X')
			{
				tar[i] = 1;
				poss.get(1).add(i);
			} else
			{
				tar[i] = 2;
				poss.get(2).add(i);
			}
		}
		if (same(new int[9]))
			return true;
		return play(new int[9], 1);
	}
}

//795. Number of Subarrays with Bounded Maximum
//https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/discuss/117590/JAVA-2-O(n)-solutions
//Runtime: 3 ms, faster than 99.52% of Java online submissions for Number of Subarrays with Bounded Maximum.
//Memory Usage: 51.7 MB, less than 6.06% of Java online submissions for Number of Subarrays with Bounded Maximum.
class Solution795
{
	private int numMax(int[] a, int B)
	{
		int pre = 0, tot = 0;
		for (int i = 0; i < a.length; i++)
			if (a[i] > B)
				pre = i + 1;
			else
				tot += i - pre + 1;
		return tot;
	}

	public int numSubarrayBoundedMax(int[] A, int L, int R)
	{
		return numMax(A, R) - numMax(A, L - 1);
	}
}

//799. Champagne Tower
//Runtime: 14 ms, faster than 62.66% of Java online submissions for Champagne Tower.
//Memory Usage: 38.1 MB, less than 11.87% of Java online submissions for Champagne Tower.
class Solution799
{
	public double champagneTower(int poured, int query_row, int query_glass)
	{
		double p = ((double) poured);
		double[][] rm = new double[query_row + 2][2];
		rm[0][0] = p;
		int pre = 0;
		for (int i = 0; i <= query_row; i++)
		{

			boolean nothingrm = true;
			for (int j = 0; j <= i; j++)
			{
				if (rm[j][pre] > 1)
				{
					nothingrm = false;
					rm[j][1 - pre] += (rm[j][pre] - 1) / 2;
					rm[j + 1][1 - pre] += (rm[j][pre] - 1) / 2;
					rm[j][pre] = 1;
				}
				if (i == query_row && j == query_glass)
					return rm[j][pre];
			}
			if (nothingrm)
				return 0;
			pre = 1 - pre;
			for (int j = 0; j <= i + 1; j++)
				rm[j][1 - pre] = 0;
		}
		return 1;
	}
}

//797. All Paths From Source to Target
//Runtime: 2 ms, faster than 99.80% of Java online submissions for All Paths From Source to Target.
//Memory Usage: 42.7 MB, less than 46.80% of Java online submissions for All Paths From Source to Target.
class Solution797
{
	private int[] stack;
	private List<List<Integer>>ans;
	private int[][]g;
	private void dfs(int node,int st,int tar)
	{
		if (node==tar)
		{
			List<Integer> t=new ArrayList<Integer>();
			for (int i=0;i<st;i++)
				t.add(stack[i]);
			ans.add(t);
			return;
		}
		for (int i=0;i<g[node].length;i++)
		{
			stack[st]=g[node][i];
			dfs(g[node][i],st+1,tar);
		}
	}
	public List<List<Integer>> allPathsSourceTarget(int[][] graph)
	{
		stack=new int[graph.length+2];
		ans=new ArrayList<List<Integer>>();
		g=graph;
		dfs(0,1,graph.length-1);
		return ans;
	}
}

public class LC791_800
{
	public static void main(String[] args)
	{
//		System.out.println(new Solution793().preimageSizeFZF(5));
	}

}
