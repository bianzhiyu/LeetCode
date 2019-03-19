package lc131_140;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//131. Palindrome Partitioning
//Runtime: 2 ms, faster than 100.00% of Java online submissions for Palindrome Partitioning.
//Memory Usage: 40.8 MB, less than 22.30% of Java online submissions for Palindrome Partitioning.
class Solution131
{
	boolean[][] ispld;
	int slen;
	String str;
	List<List<String>> ans = new ArrayList<List<String>>();
	int[] stack;

	void dfs(int left, int sp)
	{
		if (left == slen)
		{
			int l = 0;
			List<String> t = new ArrayList<String>();
			for (int i = 0; i < sp; i++)
			{
				t.add(str.substring(l, stack[i] + 1));
				l = stack[i] + 1;
			}
			ans.add(t);
			return;
		}
		for (int i = left; i < slen; i++)
			if (ispld[left][i])
			{
				stack[sp] = i;
				dfs(i + 1, sp + 1);
			}
	}

	public List<List<String>> partition(String s)
	{
		slen = s.length();
		str = s;
		ispld = new boolean[slen][slen];
		for (int i = 0; i < slen; i++)
			ispld[i][i] = true;
		for (int i = 0; i < slen - 1; i++)
			ispld[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
		for (int len = 3; len <= slen; len++)
			for (int j = 0; j < slen + 1 - len; j++)
				ispld[j][j + len - 1] = ispld[j + 1][j + len - 2] && s.charAt(j) == s.charAt(j + len - 1);
		stack = new int[slen];
		dfs(0, 0);
		return ans;
	}
}

//132. Palindrome Partitioning II
//Runtime: 14 ms, faster than 57.04% of Java online submissions for Palindrome Partitioning II.
//Memory Usage: 35 MB, less than 80.77% of Java online submissions for Palindrome Partitioning II.
class Solution132
{
	public int minCut(String s)
	{
		boolean[][] ispld;
		int slen;
		slen = s.length();
		ispld = new boolean[slen][slen];
		for (int i = 0; i < slen; i++)
			ispld[i][i] = true;
		for (int i = 0; i < slen - 1; i++)
			ispld[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
		for (int len = 3; len <= slen; len++)
			for (int j = 0; j < slen + 1 - len; j++)
				ispld[j][j + len - 1] = ispld[j + 1][j + len - 2] && s.charAt(j) == s.charAt(j + len - 1);

		int[] d = new int[slen];
		d[0] = 1;
		for (int i = 1; i < slen; i++)
		{
			if (ispld[0][i])
			{
				d[i] = 1;
				continue;
			}
			d[i] = Integer.MAX_VALUE;
			for (int j = i - 1; j >= 0; j--)
				if (ispld[j + 1][i] && d[j] + 1 < d[i])
					d[i] = d[j] + 1;
		}
		return d[slen - 1] - 1;
	}
}

/*
 * //Definition for a Node. class Node { public int val; public List<Node>
 * neighbors;
 * 
 * public Node() {}
 * 
 * public Node(int _val,List<Node> _neighbors) { val = _val; neighbors =
 * _neighbors; } };
 */
//133. Clone Graph
//Runtime: 9 ms, faster than 5.34% of Java online submissions for Clone Graph.
//Memory Usage: 34.9 MB, less than 100.00% of Java online submissions for Clone Graph.
class Solution133
{
	Set<Node> getAllNodes(Node node)
	{
		Set<Node> origNode = new HashSet<Node>();
		Deque<Node> q = new LinkedList<Node>();
		q.add(node);
		while (q.size() > 0)
		{
			Node nt = q.getFirst();
			origNode.add(nt);
			q.remove(nt);
			for (int i = 0; i < nt.neighbors.size(); i++)
			{
				Node t = nt.neighbors.get(i);
				if (!origNode.contains(t))
				{
					origNode.add(t);
					q.add(t);
				}
			}
		}
		return origNode;
	}

	public Node cloneGraph(Node node)
	{
		Set<Node> origNode = getAllNodes(node);
		HashMap<Node, Node> hm = new HashMap<Node, Node>();
		for (Node i : origNode)
			hm.put(i, new Node());
		Set<Node> copiedNode = new HashSet<Node>();
		Deque<Node> q = new LinkedList<Node>();
		Node rot = hm.get(node);
		q.add(node);
		while (q.size() > 0)
		{
			Node nt = q.getFirst();
			Node copiednt = hm.get(nt);
			copiedNode.add(nt);
			q.removeFirst();
			copiednt.val = nt.val;
			copiednt.neighbors = new ArrayList<Node>();
			for (Node i : nt.neighbors)
			{
				copiednt.neighbors.add(hm.get(i));
				if (!copiedNode.contains(i))
					q.add(i);
			}
		}
		return rot;
	}
}

//Runtime: 2 ms, faster than 100.00% of Java online submissions for Clone Graph.
//Memory Usage: 34.5 MB, less than 100.00% of Java online submissions for Clone Graph.
class Solution133_2
{
	public Node cloneGraph(Node node)
	{
		Set<Node> origNode = new HashSet<Node>();
		HashMap<Node, Node> hm = new HashMap<Node, Node>();
		Deque<Node> q = new LinkedList<Node>();
		q.add(node);
		origNode.add(node);
		while (q.size() > 0)
		{
			Node nt = q.getFirst();
			if (hm.get(nt) == null)
				hm.put(nt, new Node());
			Node copiednt = hm.get(nt);
			q.removeFirst();
			copiednt.val = nt.val;
			copiednt.neighbors = new ArrayList<Node>();
			for (Node i : nt.neighbors)
			{
				if (hm.get(i) == null)
					hm.put(i, new Node());
				copiednt.neighbors.add(hm.get(i));
				if (!origNode.contains(i))
				{
					q.add(i);
					origNode.add(i);
				}
			}
		}
		return hm.get(node);
	}
}

//Runtime: 65 ms, faster than 20.78% of Java online submissions for Gas Station.
//Memory Usage: 34.6 MB, less than 99.59% of Java online submissions for Gas Station.
class Solution134
{
	public int canCompleteCircuit(int[] gas, int[] cost)
	{
		int len = gas.length;
		for (int i = 0; i < len; i++)
		{
			int sum = 0, stop = 0, succ = 1;
			for (int k = 0; k < len; k++)
			{
				stop = (i + k) % len;
				sum += gas[stop];
				if (sum < cost[stop])
				{
					succ = 0;
					break;
				} else
				{
					sum -= cost[stop];
				}
			}

			if (succ == 1)
				return i;
		}
		return -1;
	}
}

//Runtime: 0 ms, faster than 100.00% of Java online submissions for Gas Station.
//Memory Usage: 35.6 MB, less than 93.44% of Java online submissions for Gas Station.
class Solution134_2
{
	public int canCompleteCircuit(int[] gas, int[] cost)
	{
		int len = gas.length;
		int start = 0, end = 0, sum = 0, pass = 0, round = 0, minlevel = 0;
		while (pass < len && round < 3)
		{
			if (end == 0)
				round++;
			sum += gas[end] - cost[end];
			pass++;
			end = (end + 1) % len;
			if (sum < minlevel)
				minlevel = sum;
			while (minlevel < 0)
			{
				sum -= gas[start] - cost[start];
				minlevel -= gas[start] - cost[start];
				start = (start + 1) % len;
				pass--;
			}
		}
		if (pass == len)
			return end;
		else
			return -1;
	}
}

//t135. Candy
//t=6ms, m=40MB
//t:0.2042, m:1
//This problem can be solved by graph topology sort algorithm.
class Solution135
{
	public int candy(int[] r)
	{
		int l = r.length;
		if (l == 0)
			return 0;
		if (l == 1)
			return 1;
		int[] c = new int[l];
		// topology sort
		int[] unreadyf = new int[l];
		boolean[] lf = new boolean[l], rf = new boolean[l];
		if (r[0] > r[1])
		{
			rf[0] = true;
			unreadyf[0] = 1;
		}
		if (r[l - 1] > r[l - 2])
		{
			lf[l - 1] = true;
			unreadyf[l - 1] = 1;
		}
		for (int i = 1; i < l - 1; i++)
		{
			if (r[i] > r[i - 1])
			{
				lf[i] = true;
				unreadyf[i]++;
			}
			if (r[i] > r[i + 1])
			{
				rf[i] = true;
				unreadyf[i]++;
			}
		}
		int[] q = new int[l];
		boolean used[] = new boolean[l];
		int head = 0, tail = 0;
		for (int i = 0; i < l; i++)
			if (!lf[i] && !rf[i])
			{
				q[tail++] = i;
				c[i] = 1;
				used[i] = true;
			}
		while (head < tail)
		{
			int nt = q[head++];
			if (nt >= 1 && lf[nt])
				c[nt] = Math.max(c[nt], c[nt - 1] + 1);
			if (nt <= l - 2 && rf[nt])
				c[nt] = Math.max(c[nt], c[nt + 1] + 1);

			if (nt >= 1 && rf[nt - 1])
				unreadyf[nt - 1]--;
			if (nt <= l - 2 && lf[nt + 1])
				unreadyf[nt + 1]--;

			if (nt >= 1 && rf[nt - 1] && !used[nt - 1] && unreadyf[nt - 1] == 0)
			{
				q[tail++] = nt - 1;
				used[nt - 1] = true;
			}
			if (nt <= l - 2 && lf[nt + 1] && !used[nt + 1] && unreadyf[nt + 1] == 0)
			{
				q[tail++] = nt + 1;
				used[nt + 1] = true;
			}

		}
		int cum = 0;
		for (int i = 0; i < l; i++)
			cum += c[i];
		return cum;
	}
}

//136. Single Number
//Runtime: 4 ms, faster than 42.50% of Java online submissions for Single Number.
//Memory Usage: 36.8 MB, less than 92.78% of Java online submissions for Single Number.
class Solution136
{
	public int singleNumber(int[] nums)
	{
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i += 2)
		{
			if (i == nums.length - 1 || nums[i] != nums[i + 1])
				return nums[i];
		}
		return 0;

	}
}

//Other's solution. Perfect.
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Single Number.
//Memory Usage: 41 MB, less than 31.09% of Java online submissions for Single Number.
class Solution136_2
{
	public int singleNumber(int[] nums)
	{
		int ans = 0;
		for (int i = 0; i < nums.length; i++)
			ans ^= nums[i];
		return ans;
	}
}

//137. Single Number II
//Runtime: 5 ms, faster than 34.62% of Java online submissions for Single Number II.
//Memory Usage: 35.9 MB, less than 77.72% of Java online submissions for Single Number II.
class Solution137
{
	public int singleNumber(int[] nums)
	{
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++)
			if (hm.get(nums[i]) == null)
				hm.put(nums[i], 1);
			else
				hm.put(nums[i], hm.get(nums[i]) + 1);
		for (int i : hm.keySet())
			if (hm.get(i) == 1)
				return i;
		return 0;
	}
}

//138. Copy List with Random Pointer
//Runtime: 1 ms, faster than 95.07% of Java online submissions for Copy List with Random Pointer.
//Memory Usage: 35.9 MB, less than 99.69% of Java online submissions for Copy List with Random Pointer.
class Solution138
{
	public RNode copyRandomList(RNode head)
	{
		if (head==null) return null;	
		HashMap<RNode,RNode> hm=new HashMap<RNode,RNode>();
		RNode h2=new RNode(head.val,head.next,head.random);
		hm.put(head,h2);
		RNode p=head,q=h2;
		while (p.next!=null)
		{
			p=p.next;
			q.next=new RNode(p.val,p.next,p.random);
			q=q.next;
			hm.put(p,q);
		}
		p=head;
		while (p!=null)
		{
			hm.get(p).random=hm.get(p.random);
			p=p.next;
		}
		return h2;
	}
}

//139. Word Break
//TLE
class Solution139
{
	boolean dfs(String s, List<String> wordDict)
	{
		if (s.length() == 0)
			return true;
		for (String i : wordDict)
		{
			if (s.indexOf(i) == 0 && dfs(s.substring(i.length()), wordDict))
				return true;
		}
		return false;

	}

	public boolean wordBreak(String s, List<String> wordDict)
	{
		return dfs(s, wordDict);
	}
}

//Runtime: 5 ms, faster than 88.82% of Java online submissions for Word Break.
//Memory Usage: 36.9 MB, less than 67.01% of Java online submissions for Word Break.
class Solution139_2
{
	boolean[][] match;

	void analysis(String s, List<String> wordDict)
	{
		int slen = s.length(), wdlen = wordDict.size();
		match = new boolean[slen][wdlen];
		for (int i = 0; i < slen; i++)
		{
			String ts = s.substring(i);
			for (int j = 0; j < wdlen; j++)
				match[i][j] = ts.indexOf(wordDict.get(j)) == 0;
		}
	}

	boolean judge(String s, List<String> wordDict)
	{
		int slen = s.length(), wdlen = wordDict.size();
		boolean[] r = new boolean[slen + 2];
		r[0] = true;
		for (int i = 0; i < slen; i++)
			if (r[i])
			{
				for (int j = 0; j < wdlen; j++)
					if (match[i][j])
						r[i + wordDict.get(j).length()] = true;
			}
		return r[slen];
	}

	public boolean wordBreak(String s, List<String> wordDict)
	{
		analysis(s, wordDict);
		return judge(s, wordDict);
	}
}

//140. Word Break II
//TLE
class Solution140
{
	List<String> ans = new ArrayList<String>();
	String[] stack = new String[1000];

	void dfs(String s, List<String> wordDict, int sp)
	{
		if (s.length() == 0)
		{
			String t = stack[0];
			for (int i = 1; i < sp; i++)
				t = t + " " + stack[i];
			ans.add(t);
			return;
		}
		for (String i : wordDict)
		{
			if (s.indexOf(i) == 0)
			{
				stack[sp] = i;
				dfs(s.substring(i.length()), wordDict, sp + 1);
			}
		}

	}

	public List<String> wordBreak(String s, List<String> wordDict)
	{
		dfs(s, wordDict, 0);
		return ans;
	}
}

//Runtime: 17 ms, faster than 22.84% of Java online submissions for Word Break II.
//Memory Usage: 38 MB, less than 57.37% of Java online submissions for Word Break II.
class Solution140_2
{
	List<String> ans = new ArrayList<String>();
	int[] stack = new int[1000];
	boolean[][] match;
	List<String> wd;

	void analysis(String s, List<String> wordDict)
	{
		int slen = s.length(), wdlen = wordDict.size();
		match = new boolean[slen][wdlen];
		for (int i = 0; i < slen; i++)
		{
			String ts = s.substring(i);
			for (int j = 0; j < wdlen; j++)
				match[i][j] = ts.indexOf(wordDict.get(j)) == 0;
		}
	}

	boolean judge(String s, List<String> wordDict)
	{
		int slen = s.length(), wdlen = wordDict.size();
		boolean[] r = new boolean[slen + 2];
		r[0] = true;
		for (int i = 0; i < slen; i++)
			if (r[i])
			{
				for (int j = 0; j < wdlen; j++)
					if (match[i][j])
						r[i + wordDict.get(j).length()] = true;
			}
		return r[slen];
	}

	void dfs(int spos, int sp)
	{
		if (spos == match.length)
		{
			StringBuilder t = new StringBuilder(wd.get(stack[0]));
			for (int i = 1; i < sp; i++)
			{
				t.append(" ");
				t.append(wd.get(stack[i]));
			}
			ans.add(t.toString());
			return;
		}
		for (int i = 0; i < wd.size(); i++)
		{
			if (match[spos][i])
			{
				stack[sp] = i;
				dfs(spos + wd.get(i).length(), sp + 1);
			}
		}
	}

	public List<String> wordBreak(String s, List<String> wordDict)
	{
		wd = wordDict;
		analysis(s, wordDict);
		if (!judge(s, wordDict))
			return ans;
		dfs(0, 0);
		return ans;
	}
}

public class LC131_140
{

	public static void main(String[] args)
	{
		String s = "abc";
		System.out.println("#" + s.substring(2) + "#");

		String s1 = "apple";
		List<String> wd = new ArrayList<String>();
		wd.add("pear");
		wd.add("apple");
		wd.add("peach");

		Solution139_2 sol = new Solution139_2();
		System.out.println(sol.wordBreak(s1, wd));
	}

}
