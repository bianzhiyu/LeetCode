package lc911_920;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bbst.BBST;
import bbst.MTreeSet;
import treeCodec.*;

//911. Online Election
//Runtime: 201 ms, faster than 32.12% of Java online submissions for Online Election.
//Memory Usage: 71.7 MB, less than 76.04% of Java online submissions for Online Election.

//Actually, if my BBST<T> can support 
// new BBST<T>(new Comparator<T>(){...})
//The code will be much simpler.
class TopVotedCandidate
{
	private int[] top, times;

	private static class Data implements Comparable<Data>
	{
		private int person, vote, lastTime;

		public Data(int p, int v, int t)
		{
			person = p;
			vote = v;
			lastTime = t;
		}

		public int compareTo(Data o)
		{
			if (vote != o.vote)
				return o.vote - vote;
			return o.lastTime - lastTime;
		}

		public String toString()
		{
			return "(" + person + ", " + vote + ", " + lastTime + ")";
		}
	}

	private static class PWD implements Comparable<PWD>
	{
		private int person;
		private Data data;

		public PWD(int p, Data d)
		{
			person = p;
			data = d;
		}

		public int compareTo(PWD o)
		{
			return person - o.person;
		}

		public String toString()
		{
			return "(" + person + ", " + data + ")";
		}
	}

	public TopVotedCandidate(int[] persons, int[] times)
	{
		this.times = times;
		int len = persons.length;
		top = new int[len];
		BBST<Data> data;
		BBST<PWD> pwd;
		top[0] = persons[0];
		PWD t;
		Data d;
		pwd = new BBST<PWD>(t = new PWD(persons[0], new Data(persons[0], 1, times[0])));
		data = new BBST<Data>(t.data);
		for (int i = 1; i < len; i++)
		{
			t = new PWD(persons[i], null);
			if (pwd.containData(t))
			{
				t = pwd.getData(t);
				d = data.getData(t.data);
				data = data.removeNodeByData(d);
				d.vote++;
				d.lastTime = times[i];
				if (data == null)
					data = new BBST<Data>(d);
				else
					data = data.insert(d);
			} else
			{
				t.data = new Data(persons[i], 1, times[i]);
				data = data.insert(t.data);
				pwd = pwd.insert(t);
			}
			top[i] = data.getMinData().person;
		}
	}

	public int q(int t)
	{
		if (t >= times[times.length - 1])
			return top[top.length - 1];
		int l = 0, r = top.length - 1;
		while (l < r)
		{
			if (r == l + 1)
			{
				if (t >= times[r])
					l = r;
				else
					r = l;
			} else
			{
				int m = (l + r) / 2;
				if (t >= times[m])
					l = m;
				else
					r = m - 1;
			}
		}
		return top[l];
	}
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times); int param_1 =
 * obj.q(t);
 */

//https://leetcode.com/problems/online-election/solution/
//Runtime: 149 ms, faster than 88.34% of Java online submissions for Online Election.
//Memory Usage: 71.5 MB, less than 80.21% of Java online submissions for Online Election.

//Note that: the leader change event can be detected easily.
//We don't need to record the total order of all persons.
//We just need to record the leader.
//This saves us the us of a BBST.
class TopVotedCandidate_2
{
	private int[] top, times;

	public TopVotedCandidate_2(int[] persons, int[] times)
	{
		this.times = times;
		top = new int[times.length];
		Map<Integer, Integer> count = new HashMap<Integer, Integer>();
		int leader = -1; // current leader
		int m = 0; // current number of votes for leader
		for (int i = 0; i < persons.length; ++i)
		{
			int p = persons[i];
			int c = count.getOrDefault(p, 0) + 1;
			count.put(p, c);
			if (c >= m)
			{
				leader = p;// lead change
				m = c;
			}
			top[i] = leader;
		}
	}

	public int q(int t)
	{
		if (t >= times[times.length - 1])
			return top[top.length - 1];
		int l = 0, r = top.length - 1;
		while (l < r)
		{
			if (r == l + 1)
			{
				if (t >= times[r])
					l = r;
				else
					r = l;
			} else
			{
				int m = (l + r) / 2;
				if (t >= times[m])
					l = m;
				else
					r = m - 1;
			}
		}
		return top[l];
	}
}

//Runtime: 234 ms, faster than 16.42% of Java online submissions for Online Election.
//Memory Usage: 71.1 MB, less than 86.46% of Java online submissions for Online Election.
class TopVotedCandidate_3
{
	private int[] top, times;

	private static class Data implements Comparable<Data>
	{
		private int person, vote, lastTime;

		public Data(int p, int v, int t)
		{
			person = p;
			vote = v;
			lastTime = t;
		}

		public String toString()
		{
			return "(" + person + ", " + vote + ", " + lastTime + ")";
		}

		@Override
		public int compareTo(Data o)
		{
			return person - o.person;
		}
	}

	private static class CmpByVandT implements Comparator<Data>
	{
		public int compare(Data o1, Data o2)
		{
			if (o1.vote != o2.vote)
				return o1.vote - o2.vote;
			else
				return o1.lastTime - o2.lastTime;
		}

	}

	public TopVotedCandidate_3(int[] persons, int[] times)
	{
		this.times = times;
		int len = persons.length;
		top = new int[len];
		MTreeSet<Data> sortByPerson = new MTreeSet<Data>();
		MTreeSet<Data> sortByVandT = new MTreeSet<Data>(new CmpByVandT());
		top[0] = persons[0];
		Data d = new Data(persons[0], 1, times[0]);
		sortByPerson.insert(d);
		sortByVandT.insert(d);
		for (int i = 1; i < len; i++)
		{
			d = new Data(persons[i], 1, times[i]);
			if (sortByPerson.getData(d) != null)
			{
				d = sortByPerson.getData(d);
				sortByPerson.removeNodeByData(d);
				sortByVandT.removeNodeByData(d);
				d.vote++;
				d.lastTime = times[i];
			}
			sortByPerson.insert(d);
			sortByVandT.insert(d);

			top[i] = sortByVandT.getMaxData().person;
		}
	}

	public int q(int t)
	{
		if (t >= times[times.length - 1])
			return top[top.length - 1];
		int l = 0, r = top.length - 1;
		while (l < r)
		{
			if (r == l + 1)
			{
				if (t >= times[r])
					l = r;
				else
					r = l;
			} else
			{
				int m = (l + r) / 2;
				if (t >= times[m])
					l = m;
				else
					r = m - 1;
			}
		}
		return top[l];
	}
}

//914. X of a Kind in a Deck of Cards
//Runtime: 9 ms, faster than 70.77% of Java online submissions for X of a Kind in a Deck of Cards.
//Memory Usage: 40.2 MB, less than 72.22% of Java online submissions for X of a Kind in a Deck of Cards.
class Solution914
{
	private int gcd(int a, int b)
	{
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	public boolean hasGroupsSizeX(int[] deck)
	{
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0; i < deck.length; i++)
			hm.put(deck[i], hm.getOrDefault(deck[i], 0) + 1);
		int ct = -1;
		for (int x : hm.keySet())
			ct = ct == -1 ? hm.get(x) : gcd(ct, hm.get(x));
		if (ct == 1)
			return false;
		return true;
	}
}

//915. Partition Array into Disjoint Intervals
//Runtime: 2 ms, faster than 99.73% of Java online submissions for Partition Array into Disjoint Intervals.
//Memory Usage: 38.4 MB, less than 100.00% of Java online submissions for Partition Array into Disjoint Intervals.
class Solution915
{
	public int partitionDisjoint(int[] A)
	{
		int len = A.length;
		int[] maxUntil = new int[len];
		int[] minSince = new int[len];
		maxUntil[0] = A[0];
		for (int i = 1; i < len; i++)
			maxUntil[i] = A[i] > maxUntil[i - 1] ? A[i] : maxUntil[i - 1];
		minSince[len - 1] = A[len - 1];
		for (int i = len - 2; i >= 0; i--)
			minSince[i] = A[i] < minSince[i + 1] ? A[i] : minSince[i + 1];
		for (int i = 0; i < len - 1; i++)
			if (maxUntil[i] <= minSince[i + 1])
				return i + 1;
		return -1;
	}
}

//916. Word Subsets
//Runtime: 18 ms, faster than 92.63% of Java online submissions for Word Subsets.
//Memory Usage: 47 MB, less than 96.72% of Java online submissions for Word Subsets.
class Solution916
{
	public List<String> wordSubsets(String[] A, String[] B)
	{
		List<String> ans = new ArrayList<String>();
		int[] min = new int[26];
		int[] tmp = new int[26];
		for (String S : B)
		{
			Arrays.fill(tmp, 0);
			for (int i = 0; i < S.length(); i++)
				tmp[S.charAt(i) - 'a']++;
			for (int i = 0; i < 26; i++)
				if (tmp[i] > min[i])
					min[i] = tmp[i];
		}
		for (String S : A)
		{
			Arrays.fill(tmp, 0);
			for (int i = 0; i < S.length(); i++)
				tmp[S.charAt(i) - 'a']++;
			boolean canBe = true;
			for (int i = 0; i < 26; i++)
				if (tmp[i] < min[i])
				{
					canBe = false;
					break;
				}
			if (canBe)
				ans.add(S);
		}
		return ans;
	}
}

//918. Maximum Sum Circular Subarray
//https://leetcode.com/problems/maximum-sum-circular-subarray/solution/
//Runtime: 5 ms, faster than 98.92% of Java online submissions for Maximum Sum Circular Subarray.
//Memory Usage: 46.3 MB, less than 10.00% of Java online submissions for Maximum Sum Circular Subarray.
class Solution918
{
	public int maxSubarraySumCircular(int[] A)
	{
		int max = A[0], cur = A[0], len = A.length;
		for (int i = 1; i < len; i++)
		{
			if (cur > 0)
				cur = cur + A[i];
			else
				cur = A[i];
			if (cur > max)
				max = cur;
		}
		int[] sumToLast = new int[len];
		sumToLast[len - 1] = A[len - 1];
		for (int i = len - 2; i >= 0; i--)
			sumToLast[i] = sumToLast[i + 1] + A[i];
		int[] maxCand = new int[len];
		maxCand[len - 1] = A[len - 1] > 0 ? A[len - 1] : 0;
		for (int i = len - 2; i >= 0; i--)
			maxCand[i] = sumToLast[i] > maxCand[i + 1] ? sumToLast[i] : maxCand[i + 1];
		cur = 0;
		for (int i = 0; i < len - 1; i++)
		{
			cur += A[i];
			if (cur + maxCand[i + 1] > max)
				max = cur + maxCand[i + 1];
		}
		return max;
	}
}

//919. Complete Binary Tree Inserter
//Runtime: 55 ms, faster than 99.40% of Java online submissions for Complete Binary Tree Inserter.
//Memory Usage: 45.6 MB, less than 16.98% of Java online submissions for Complete Binary Tree Inserter.
class CBTInserter
{
	private int totLayer;
	private TreeNode root;

	public CBTInserter(TreeNode root)
	{
		this.root = root;
		TreeNode t = root;
		totLayer = 1;
		while (t.left != null)
		{
			totLayer++;
			t = t.left;
		}
	}

	private boolean isFull(TreeNode rt, int l, int tl)
	{
		if (l == tl)
			return true;
		else if (rt.right == null)
			return false;
		else
			return isFull(rt.right, l + 1, tl);
	}

	public int insert(int v)
	{
		if (isFull(root, 1, totLayer))
		{
			TreeNode t = root;
			for (int i = 2; i <= totLayer; i++)
				t = t.left;
			t.left = new TreeNode(v);
			totLayer++;
			return t.val;
		}
		TreeNode t = root;
		for (int i = 1; i < totLayer - 1; i++)
		{
			if (isFull(t.left, i + 1, totLayer))
				t = t.right;
			else
				t = t.left;
		}
		if (t.left == null)
			t.left = new TreeNode(v);
		else
			t.right = new TreeNode(v);
		return t.val;
	}

	public TreeNode get_root()
	{
		return root;
	}
}

/**
 * Your CBTInserter object will be instantiated and called as such: CBTInserter
 * obj = new CBTInserter(root); int param_1 = obj.insert(v); TreeNode param_2 =
 * obj.get_root();
 */

public class LC911_920
{
	public static void test911()
	{
		int[] p = new int[]
		{ 0, 1, 1, 0, 0, 1, 0 };
		int[] t = new int[]
		{ 0, 5, 10, 15, 20, 25, 30 };
		TopVotedCandidate s = new TopVotedCandidate(p, t);
		int[] opt = new int[]
		{ 3, 12, 25, 15, 24, 8 };
		for (int tm : opt)
			System.out.println(s.q(tm));
	}

	public static void main(String[] args)
	{
		test911();
	}
}
