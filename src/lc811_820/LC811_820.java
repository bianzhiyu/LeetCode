package lc811_820;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import treeCodec.*;

//813. Largest Sum of Averages
//Runtime: 7 ms, faster than 73.02% of Java online submissions for Largest Sum of Averages.
//Memory Usage: 36.8 MB, less than 88.57% of Java online submissions for Largest Sum of Averages.
class Solution813
{
	public double largestSumOfAverages(int[] A, int K)
	{
		double[][] d = new double[K + 1][A.length];
		double s = 0;
		for (int i = A.length - 1; i >= 0; i--)
		{
			s += A[i];
			d[1][i] = s / (A.length - i);
		}
		for (int i = 2; i <= K; i++)
			for (int j = 0; j <= A.length - i; j++)
			{
				s = 0;
				d[i][j] = 0;
				for (int k = j; k <= A.length - i; k++)
				{
					s += A[k];
					d[i][j] = Math.max(d[i][j], d[i - 1][k + 1] + s / (k - j + 1));
				}
			}
		return d[K][0];
	}
}

//814. Binary Tree Pruning
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Pruning.
//Memory Usage: 35.9 MB, less than 99.48% of Java online submissions for Binary Tree Pruning.
class Solution814
{
	private int dfs(TreeNode rt)
	{
		if (rt == null)
			return 0;
		int ls = dfs(rt.left);
		if (ls == 0)
			rt.left = null;
		int rs = dfs(rt.right);
		if (rs == 0)
			rt.right = null;
		return rt.val + ls + rs;
	}

	public TreeNode pruneTree(TreeNode root)
	{
		int s = dfs(root);
		if (s == 0)
			return null;
		else
			return root;
	}
}

//815. Bus Routes
//Runtime: 222 ms, faster than 27.32% of Java online submissions for Bus Routes.
//Memory Usage: 61.7 MB, less than 83.13% of Java online submissions for Bus Routes.
//This is as efficient as the official solution given by awice.
class Solution815
{
	public int numBusesToDestination(int[][] routes, int S, int T)
	{
		if (S == T)
			return 0;
		List<HashSet<Integer>> bs = new ArrayList<HashSet<Integer>>();
		for (int[] b : routes)
		{
			HashSet<Integer> n = new HashSet<Integer>();
			for (int i = 0; i < b.length; i++)
				n.add(b[i]);
			bs.add(n);
		}
		Queue<Integer> q = new LinkedList<Integer>();
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		HashSet<Integer> dest = new HashSet<Integer>();
		List<HashSet<Integer>> canTransfer = new ArrayList<HashSet<Integer>>(bs.size());
		for (int i = 0; i < bs.size(); i++)
			canTransfer.add(new HashSet<Integer>());
		for (int i = 0; i < bs.size(); i++)
		{
			for (int stop : bs.get(i))
			{
				for (int j = i + 1; j < bs.size(); j++)
					if (bs.get(j).contains(stop))
					{
						canTransfer.get(i).add(j);
						canTransfer.get(j).add(i);
					}
			}
		}
		for (int i = 0; i < bs.size(); i++)
		{
			if (bs.get(i).contains(T))
				dest.add(i);
			if (bs.get(i).contains(S))
			{
				q.add(i);
				hm.put(i, 1);
			}
			if (bs.get(i).contains(T) && bs.get(i).contains(S))
				return 1;
		}

		while (!q.isEmpty())
		{
			int bInd = q.remove();
			int step = hm.get(bInd);
			HashSet<Integer> tran = canTransfer.get(bInd);
			if (dest.contains(bInd))
				return step;
			for (int i = 0; i < bs.size(); i++)
				if (!hm.containsKey(i) && tran.contains(i))
				{
					hm.put(i, step + 1);
					q.add(i);
					if (dest.contains(i))
						return step + 1;
				}
		}
		return -1;
	}
}

//Runtime: 420 ms, faster than 13.81% of Java online submissions for Bus Routes.
//Memory Usage: 67.8 MB, less than 68.75% of Java online submissions for Bus Routes.
class Solution815_3
{
	public int numBusesToDestination(int[][] routes, int S, int T)
	{
		if (S == T)
			return 0;
		List<HashSet<Integer>> bs = new ArrayList<HashSet<Integer>>();
		int remBusNum = routes.length;
		for (int[] b : routes)
		{
			HashSet<Integer> n = new HashSet<Integer>();
			for (int i = 0; i < b.length; i++)
				n.add(b[i]);
			bs.add(n);
		}
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(S);
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		hm.put(S, 0);
		while (!q.isEmpty())
		{
			int stop = q.remove();
			int step = hm.get(stop);
			int k = 0;
			while (k < remBusNum)
			{
				HashSet<Integer> b = bs.get(k);
				if (b.contains(stop))
				{
					for (int i : b)
					{
						if (i == T)
							return step + 1;
						if (!hm.containsKey(i))
						{
							hm.put(i, step + 1);
							q.add(i);
						}
					}

					if (k == remBusNum - 1)
						remBusNum--;
					else
					{
						bs.set(k, bs.get(remBusNum - 1));
						remBusNum--;
					}
				} else
					k++;
			}
		}
		return -1;
	}
}

//Runtime: 741 ms, faster than 5.09% of Java online submissions for Bus Routes.
//Memory Usage: 59.4 MB, less than 88.13% of Java online submissions for Bus Routes.
class Solution815_2
{
	public int numBusesToDestination(int[][] routes, int S, int T)
	{
		if (S == T)
			return 0;
		List<HashSet<Integer>> bs = new ArrayList<HashSet<Integer>>();
		for (int[] b : routes)
		{
			HashSet<Integer> n = new HashSet<Integer>();
			for (int i = 0; i < b.length; i++)
				n.add(b[i]);
			bs.add(n);
		}
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(S);
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		hm.put(S, 0);
		while (!q.isEmpty())
		{
			int stop = q.remove();
			int step = hm.get(stop);
			for (HashSet<Integer> b : bs)
				if (b.contains(stop))
				{
					for (int i : b)
					{
						if (i == T)
							return step + 1;
						if (!hm.containsKey(i))
						{
							hm.put(i, step + 1);
							q.add(i);
						}
					}
				}
		}
		return -1;
	}
}

//817. Linked List Components
//Runtime: 8 ms, faster than 91.13% of Java online submissions for Linked List Components.
//Memory Usage: 40.6 MB, less than 90.00% of Java online submissions for Linked List Components.
class ListNode
{
	int val;
	ListNode next;

	ListNode(int x)
	{
		val = x;
	}
}

class Solution817
{
	public int numComponents(ListNode head, int[] G)
	{
		Set<Integer> s = new HashSet<Integer>();
		for (int i = 0; i < G.length; i++)
			s.add(G[i]);
		int tot = 0;
		while (head != null)
		{
			if (s.contains(head.val))
			{
				tot++;
				ListNode p = head.next;
				s.remove(head.val);
				while (p != null)
				{
					if (s.contains(p.val))
						s.remove(p.val);
					else
						break;
					p = p.next;
				}
				head = p;
			} else
				head = head.next;
		}

		return tot;
	}
}

//818. Race Car
//Runtime: 1010 ms, faster than 5.08% of Java online submissions for Race Car.
//Memory Usage: 316.4 MB, less than 11.87% of Java online submissions for Race Car.
class Solution818
{
	HashMap<Integer, HashMap<Integer, Integer>> h;
	HashMap<Integer, HashSet<Integer>> s;
	Queue<Integer> q;

	final static boolean debug = true;

	public int racecar(int target)
	{
		h = new HashMap<Integer, HashMap<Integer, Integer>>();
		s = new HashMap<Integer, HashSet<Integer>>();
		s.put(1, new HashSet<Integer>());
		s.get(1).add(target);
		h.put(1, new HashMap<Integer, Integer>());
		h.get(1).put(target, 0);
		q = new LinkedList<Integer>();
		q.add(1);
		q.add(target);
		while (!q.isEmpty())
		{
			int nowv = q.remove(), nowt = q.remove(), nstep = h.get(nowv).get(nowt);
			if (debug)
				System.out.print(nowv + " " + nowt + " " + nstep + " : ");
			int tv = nowv * 2, tt = nowt - nowv;
			if (tt == 0)
				return h.get(nowv).get(nowt) + 1;

			if (!s.containsKey(tv))
				s.put(tv, new HashSet<Integer>());
			if ((nowt > 0 || nowv <= -2 * nowt) && !s.get(tv).contains(tt))
			{
				s.get(tv).add(tt);
				q.add(tv);
				q.add(tt);
				if (debug)
					System.out.print(tv + " " + tt + " ; ");
				if (!h.containsKey(tv))
					h.put(tv, new HashMap<Integer, Integer>());
				h.get(tv).put(tt, nstep + 1);
			}

			tv = 1;
			tt = -nowt;

			if (!s.containsKey(tv))
				s.put(tv, new HashSet<Integer>());
			if (!s.get(tv).contains(tt))
			{
				s.get(tv).add(tt);
				q.add(tv);
				q.add(tt);
				if (debug)
					System.out.print(tv + " " + tt + " ; ");
				if (!h.containsKey(tv))
					h.put(tv, new HashMap<Integer, Integer>());
				h.get(tv).put(tt, nstep + 1);
			}
			if (debug)
				System.out.println();
		}
		return -1;
	}
}

//This is a dp solution which I have not understanded.
//https://leetcode.com/problems/race-car/discuss/246138/java-DP-solution-heavily-commented.
//Runtime: 4 ms, faster than 92.00% of Java online submissions for Race Car.
//Memory Usage: 36.4 MB, less than 91.53% of Java online submissions for Race Car.
class Solution818_2
{
	public int racecar(int target)
	{
		if (target == 0)
			return 0;

		int[] dp = new int[target + 1]; // min steps to get i
		dp[0] = 0;

		for (int i = 1; i <= target; i++)
		{
			// Perfect position is such only using AAAA,
			// which is 1, 3, 7, 15,... ( 2^N - 1)

			int rightPosition = 0; // location of perfect position on right side of i
			int rightSteps = 0; // steps to reach right perfect position
			while (rightPosition < i)
			{
				rightSteps++;
				rightPosition = (1 << rightSteps) - 1; // 1, 3, 7, 15,...,
			}
			// perfect right position for i
			if (rightPosition == i)
			{
				dp[i] = rightSteps;
				continue;
			}

			// first assuming car reaches right position
			// then reverse (+1), facing left
			// then go back to i;
			// for example, i = 5; rightPostion = 7, right steps = 3;
			dp[i] = rightSteps + 1 + dp[rightPosition - i]; // dp[5] = 3 + 1 + 4 = 8

			int leftSteps = rightSteps - 1; // steps to get o perfect position on left side of i,
			int leftPosition = (1 << leftSteps) - 1; // location of perfect position of left side of i
			// for example, i = 5; leftPostion = 3, left steps = 2;

			// assuming car reaches left position with leftSteps,
			// reverse (+1)
			// go back (j) steps to reversePosition(j could be 0), this is probably the most
			// difficult part.
			// reverse again (+1), facing right
			// then go to i
			//
			for (int j = 0; j < leftSteps; j++)
			{
				int reversePosition = leftPosition - ((1 << j) - 1);
				dp[i] = Math.min(dp[i], leftSteps + 1 + j + 1 + dp[i - reversePosition]);
				// for i = 5; gets the minimum in j = 1 with AARARAA (1-3-3-2-2-3-5).
				// dp[5] = 2 + 1 + 1 + 1 + 2 = 7
			}
		}
		return dp[target];
	}
}

//820. Short Encoding of Words
//Runtime: 516 ms, faster than 7.38% of Java online submissions for Short Encoding of Words.
//Memory Usage: 39.5 MB, less than 51.72% of Java online submissions for Short Encoding of Words.
class Solution820
{
	private boolean contains(String s1, String s2)
	{
		int len1 = s1.length(), len2 = s2.length();
		if (len1 < len2)
			return false;
		for (int i = 0; i < len2; i++)
			if (s2.charAt(len2 - 1 - i) != s1.charAt(len1 - 1 - i))
				return false;
		return true;
	}

	private int[] father;

	public int minimumLengthEncoding(String[] words)
	{
		int len = words.length;
		father = new int[len];
		Arrays.fill(father, -1);
		for (int i = 0; i < len; i++)
		{
			if (father[i] != -1)
				continue;
			for (int j = 0; j < len; j++)
				if (j != i && father[j] == -1 && contains(words[i], words[j]))
					father[j] = i;
		}
		int tot = 0;
		for (int i = 0; i < len; i++)
			if (father[i] == -1)
				tot += words[i].length() + 1;
		return tot;
	}
}

//Runtime: 12 ms, faster than 92.95% of Java online submissions for Short Encoding of Words.
//Memory Usage: 42.5 MB, less than 6.90% of Java online submissions for Short Encoding of Words.
class Solution820_2
{
	private static class TrieNode
	{
//		private boolean isWord=false;
		private TrieNode[] son = new TrieNode[26];
	}

	private void add(TrieNode rt, String str)
	{
		int len = str.length();
		for (int i = 0; i < len; i++)
		{
			int c = str.charAt(len - 1 - i) - 'a';
			if (rt.son[c] == null)
				rt.son[c] = new TrieNode();
			rt = rt.son[c];
		}
//		rt.isWord=true;
	}

	private int travel(TrieNode rt, int len)
	{
//		if (rt==null) return 0;
		boolean hasSon = false;
		int tot = 0;
		for (int i = 0; i < 26; i++)
		{
			if (rt.son[i] != null)
			{
				hasSon = true;
				tot += travel(rt.son[i], len + 1);
			}
		}
		if (!hasSon)
			return len + 1;
		else
			return tot;
	}

	public int minimumLengthEncoding(String[] words)
	{
		TrieNode root = new TrieNode();
		for (int i = 0; i < words.length; i++)
			add(root, words[i]);
		return travel(root, 0);
	}
}

public class LC811_820
{
	public static void main(String[] args)
	{
		String s = "0.00";
		System.out.println(Double.parseDouble(s));
	}
}
