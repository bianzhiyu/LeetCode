package lc751_760;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//752. Open the Lock
//Runtime: 14 ms, faster than 99.27% of Java online submissions for Open the Lock.
//Memory Usage: 38.7 MB, less than 88.70% of Java online submissions for Open the Lock.
class Solution752
{
	private int toInt(String s)
	{
		return (s.charAt(0) - '0') * 1000 + (s.charAt(1) - '0') * 100 + (s.charAt(2) - '0') * 10 + (s.charAt(3) - '0');
	}

	public int openLock(String[] deadends, String target)
	{
		boolean[] used = new boolean[10002];
		for (int i = 0; i < deadends.length; i++)
			used[toInt(deadends[i])] = true;
		int tar = toInt(target);
		if (used[tar] || used[0])
			return -1;
		int[] q = new int[10002];
		int[] s = new int[10002];
		used[0] = true;
		q[0] = 0;
		int f = 0, r = 1;
		s[0] = 0;
		if (tar == 0)
			return 0;
		while (f < r)
		{
			int state = q[f], step = s[f];
			f++;
			int newstate;

			if (state / 1000 < 9)
				newstate = state + 1000;
			else
				newstate = state - 9000;
			if (!used[newstate])
			{
				if (newstate == tar)
					return step + 1;
				q[r] = newstate;
				s[r] = step + 1;
				r++;
				used[newstate] = true;
			}

			if (state / 1000 == 0)
				newstate = state + 9000;
			else
				newstate = state - 1000;
			if (!used[newstate])
			{
				if (newstate == tar)
					return step + 1;
				q[r] = newstate;
				s[r] = step + 1;
				r++;
				used[newstate] = true;
			}

			if (state / 100 % 10 < 9)
				newstate = state + 100;
			else
				newstate = state - 900;
			if (!used[newstate])
			{
				if (newstate == tar)
					return step + 1;
				q[r] = newstate;
				s[r] = step + 1;
				r++;
				used[newstate] = true;
			}

			if (state / 100 % 10 == 0)
				newstate = state + 900;
			else
				newstate = state - 100;
			if (!used[newstate])
			{
				if (newstate == tar)
					return step + 1;
				q[r] = newstate;
				s[r] = step + 1;
				r++;
				used[newstate] = true;
			}

			if (state / 10 % 10 == 9)
				newstate = state - 90;
			else
				newstate = state + 10;
			if (!used[newstate])
			{
				if (newstate == tar)
					return step + 1;
				q[r] = newstate;
				s[r] = step + 1;
				r++;
				used[newstate] = true;
			}

			if (state / 10 % 10 == 0)
				newstate = state + 90;
			else
				newstate = state - 10;
			if (!used[newstate])
			{
				if (newstate == tar)
					return step + 1;
				q[r] = newstate;
				s[r] = step + 1;
				r++;
				used[newstate] = true;
			}

			if (state % 10 == 9)
				newstate = state - 9;
			else
				newstate = state + 1;
			if (!used[newstate])
			{
				if (newstate == tar)
					return step + 1;
				q[r] = newstate;
				s[r] = step + 1;
				r++;
				used[newstate] = true;
			}
			if (state % 10 == 0)
				newstate = state + 9;
			else
				newstate = state - 1;
			if (!used[newstate])
			{
				if (newstate == tar)
					return step + 1;
				q[r] = newstate;
				s[r] = step + 1;
				r++;
				used[newstate] = true;
			}
		}

		return -1;
	}
}

//753. Cracking the Safe
//Runtime: 22 ms, faster than 7.28% of Java online submissions for Cracking the Safe.
//Memory Usage: 42.3 MB, less than 5.97% of Java online submissions for Cracking the Safe.
class Solution753
{
	private void genNodes(int n, int k, HashSet<String> nodes, String str, int st)
	{
		if (st == n)
		{
			nodes.add(str);
			return;
		}
		for (int i = 0; i < k; i++)
			genNodes(n, k, nodes, str + i, st + 1);
	}

	private int toInt(String s)
	{
		int x = 0;
		for (int i = 0; i < s.length(); i++)
			x = x * 10 + (s.charAt(i) - '0');
		return x;
	}

	private void genEdges(HashSet<String> nodes, int k, HashMap<Integer, HashSet<String>> edges)
	{
		for (String s : nodes)
		{
			HashSet<String> t = new HashSet<String>();
			for (int i = 0; i < k; i++)
				t.add("" + i);
			edges.put(toInt(s), t);
		}
	}

	private void initUsedEgdes(HashSet<String> nodes, HashMap<Integer, HashSet<Integer>> usedEdges)
	{
		for (String s : nodes)
			usedEdges.put(toInt(s), new HashSet<Integer>());
	}

	private void dfs(StringBuilder sb, String node, HashMap<Integer, HashSet<String>> edges,
			HashMap<Integer, HashSet<Integer>> usedEdges)
	{
		HashSet<Integer> used = usedEdges.get(toInt(node));
		for (String e : edges.get(toInt(node)))
			if (!used.contains(toInt(e)))
			{
				used.add(toInt(e));
				dfs(sb, node.substring(1) + e, edges, usedEdges);
				sb.append(e);
			}
	}

	public String crackSafe(int n, int k)
	{
		if (n == 1)
		{
			String s = "";
			for (int i = 0; i < k; i++)
				s += i;
			return s;
		}
		HashSet<String> nodes = new HashSet<String>();
		genNodes(n, k, nodes, "", 0);
		HashMap<Integer, HashSet<String>> edges = new HashMap<Integer, HashSet<String>>();
		genEdges(nodes, k, edges);
		HashMap<Integer, HashSet<Integer>> usedEdges = new HashMap<Integer, HashSet<Integer>>();
		initUsedEgdes(nodes, usedEdges);
		StringBuilder sb = new StringBuilder();

		String init = "";
		for (int i = 0; i < n - 1; i++)
			init += "0";

		dfs(sb, init, edges, usedEdges);

		sb.append(init);

		return sb.toString();
	}
}

//std solution
//Runtime: 47 ms, faster than 5.16% of Java online submissions for Cracking the Safe.
//Memory Usage: 45.9 MB, less than 5.97% of Java online submissions for Cracking the Safe.
class Solution753_2
{
	Set<String> seen;
	StringBuilder ans;

	public String crackSafe(int n, int k)
	{
		if (n == 1 && k == 1)
			return "0";
		seen = new HashSet<String>();
		ans = new StringBuilder();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n - 1; ++i)
			sb.append("0");
		String start = sb.toString();

		dfs(start, k);
		ans.append(start);
		return new String(ans);
	}

	public void dfs(String node, int k)
	{
		for (int x = 0; x < k; ++x)
		{
			String nei = node + x;
			if (!seen.contains(nei))
			{
				System.out.println(node + " " + nei);
				seen.add(nei);
				dfs(nei.substring(1), k);
				ans.append(x);
			}
		}
	}
}

//756. Pyramid Transition Matrix
//Runtime: 3 ms, faster than 99.60% of Java online submissions for Pyramid Transition Matrix.
//Memory Usage: 36.9 MB, less than 66.22% of Java online submissions for Pyramid Transition Matrix.
class Solution756
{
	int[][] pyr;
	HashMap<Integer, Set<Integer>> hm = new HashMap<Integer, Set<Integer>>();

	private boolean dfs(int l, int p)
	{
		if (l == -1)
			return true;
		if (p == l + 1)
			return dfs(l - 1, 0);
		int s = (pyr[l + 1][p] << 8) + pyr[l + 1][p + 1];
		if (!hm.containsKey(s))
			return false;
		for (int cand : hm.get(s))
		{
			pyr[l][p] = cand;
			if (dfs(l, p + 1))
				return true;
		}
		return false;
	}

	public boolean pyramidTransition(String bottom, List<String> allowed)
	{
		int len = bottom.length();
		pyr = new int[len][len];
		for (int i = 0; i < len; i++)
			pyr[len - 1][i] = bottom.charAt(i);
		for (int i = 0; i < allowed.size(); i++)
		{
			int l = allowed.get(i).charAt(0);
			int r = allowed.get(i).charAt(1);
			int t = allowed.get(i).charAt(2);
			int s = (l << 8) + r;
			if (!hm.containsKey(s))
				hm.put(s, new HashSet<Integer>());
			hm.get(s).add(t);
		}
		return dfs(len - 2, 0);
	}
}

public class LC751_760
{
	public static void test752()
	{
		String[] s = new String[]
		{ "0000" };
		String tar = "8888";
		Solution752 sol = new Solution752();
		System.out.println(sol.openLock(s, tar));
	}

	public static void test753()
	{
		System.out.println(new Solution753_2().crackSafe(2, 2));
	}

	public static void main(String[] args)
	{
		test753();
	}
}
