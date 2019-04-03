package lc201_210;

import java.util.HashSet;
import java.util.Set;

class ListNode
{
	int val;
	ListNode next;

	ListNode(int x)
	{
		val = x;
	}
}

//201. Bitwise AND of Numbers Range
//Runtime: 5 ms, faster than 100.00% of Java online submissions for Bitwise AND of Numbers Range.
//Memory Usage: 36.9 MB, less than 40.00% of Java online submissions for Bitwise AND of Numbers Range.
class Solution201
{
	static long f(long m, long n)
	{
		if (m == 0)
			return 0;
		long tm = m, l = 0;
		boolean ontail = true;
		while (tm > 0)
		{
			if (tm % 2 == 0 && ontail)
				l++;
			if (tm % 2 == 1)
				ontail = false;
			tm = tm / 2;
		}
		long n1 = m + (1 << l);
		if (n1 > n)
			return m;
		else
			return m & f(n1, n);
	}

	static long f2(long m, long n)
	{
		long[] stack = new long[40];
		int sp = 0;
		while (true)
		{
			if (m == 0)
				break;
			long tm = m, l = 0;
			boolean ontail = true;
			while (tm > 0)
			{
				if (tm % 2 == 0 && ontail)
					l++;
				if (tm % 2 == 1)
					ontail = false;
				tm = tm / 2;
			}
			long n1 = m + (1 << l);
			if (n1 > n)
				break;
			else
			{
				stack[sp++] = m;
				m = n1;
			}
		}

		while (sp > 0)
			m &= stack[--sp];

		return m;
	}

	public int rangeBitwiseAnd(int m, int n)
	{
		return (int) f(m, n);
	}
}

//202. Happy Number
//Runtime: 2 ms, faster than 72.29% of Java online submissions for Happy Number.
//Memory Usage: 35 MB, less than 53.85% of Java online submissions for Happy Number.
class Solution202
{
	int next(int p)
	{
		int t = 0;
		while (p > 0)
		{
			t += (p % 10) * (p % 10);
			p = p / 10;
		}
		return t;
	}

	public boolean isHappy(int n)
	{
		if (n == 1)
			return true;
		Set<Integer> s = new HashSet<Integer>();
		int p = n;
		s.add(p);
		while (true)
		{
			p = next(p);
			if (p == 1)
				return true;
			if (s.contains(p))
				return false;
			s.add(p);
		}
	}
}

//203. Remove Linked List Elements
//Runtime: 3 ms, faster than 100.00% of Java online submissions for Remove Linked List Elements.
//Memory Usage: 40.8 MB, less than 69.89% of Java online submissions for Remove Linked List Elements.
class Solution203
{
	public ListNode removeElements(ListNode head, int val)
	{
		if (head == null)
			return head;
		while (head != null && head.val == val)
		{
			head = head.next;
		}
		if (head == null)
			return head;
		ListNode p = head;
		while (p.next != null)
		{
			if (p.next.val == val)
			{
				p.next = p.next.next;
			} else
				p = p.next;
		}
		return head;
	}
}

class Lk204
{
	int val;
	Lk204 next;

	Lk204(int x)
	{
		val = x;
		next = null;
	}
}

//204. Count Primes
//TLE:999983
class Solution204
{
	Lk204 establk(int n)
	{
		Lk204 head = new Lk204(2);
		Lk204 tail = head;
		for (int i = 3; i < n; i++)
		{
			tail.next = new Lk204(i);
			tail = tail.next;
		}
		return head;
	}

	void prime(Lk204 head)
	{
		while (head != null)
		{
			Lk204 n = head;
			while (n.next != null)
			{
				if (n.next.val % head.val == 0)
				{
					n.next = n.next.next;
				} else
					n = n.next;
			}
			head = head.next;
		}
	}

	int count(Lk204 head)
	{
		int i = 0;
		while (head != null)
		{
			i++;
			head = head.next;
		}
		return i;
	}

	public int countPrimes(int n)
	{
		if (n < 3)
			return 0;
		Lk204 head = establk(n);
		prime(head);
		return count(head);
	}
}

//Runtime: 118 ms, faster than 17.52% of Java online submissions for Count Primes.
//Memory Usage: 37.2 MB, less than 12.78% of Java online submissions for Count Primes.
class Solution204_2
{
	public int countPrimes(int n)
	{
		if (n < 3)
			return 0;
		int[] pn = new int[500000];
		int sp = 0, ct = 0;
		for (int i = 2; i < n; i++)
		{
			boolean isprime = true;
			int maxb = 1 + (int) Math.sqrt(i);
			for (int j = 0; j < sp && pn[j] < maxb; j++)
				if (i % pn[j] == 0)
				{
					isprime = false;
					break;
				}
			if (isprime)
			{
				pn[sp++] = i;
				ct++;
			}
		}
		return ct;
	}
}

//Runtime: 11 ms, faster than 94.69% of Java online submissions for Count Primes.
//Memory Usage: 35.9 MB, less than 20.11% of Java online submissions for Count Primes.
class Solution204_3
{
	public int countPrimes(int n)
	{
		if (n < 3)
			return 0;
		boolean[] ns = new boolean[n];
		int ct = 0;
		for (int i = 2; i < n; i++)
			if (!ns[i])
			{
				ct++;
				for (int j = i + i; j < n; j += i)
					ns[j] = true;
			}
		return ct;
	}
}

//205. Isomorphic Strings
//Runtime: 3 ms, faster than 97.59% of Java online submissions for Isomorphic Strings.
//Memory Usage: 34.5 MB, less than 100.00% of Java online submissions for Isomorphic Strings.
class Solution205
{
	public boolean isIsomorphic(String s, String t)
	{
		if (s.length() != t.length())
			return false;
		boolean succ = true;
		int[] map = new int[256];
		int[] invmap = new int[256];
		for (int i = 0; i < s.length(); i++)
		{
			if (map[s.charAt(i)] == 0)
			{
				if (invmap[t.charAt(i)] != 0)
				{
					succ = false;
					break;
				}
				map[s.charAt(i)] = t.charAt(i);
				invmap[t.charAt(i)] = s.charAt(i);
			} else
			{
				if (map[s.charAt(i)] != t.charAt(i))
				{
					succ = false;
					break;
				}
			}
		}
		return succ;
	}
}

//206. Reverse Linked List
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List.
//Memory Usage: 37.6 MB, less than 64.19% of Java online submissions for Reverse Linked List.
class Solution206
{
	public ListNode reverseList(ListNode head)
	{
		ListNode tail = null;
		while (head != null)
		{
			ListNode p = new ListNode(head.val);
			p.next = tail;
			tail = p;
			head = head.next;
		}
		return tail;
	}
}

//207. Course Schedule
//Runtime: 28 ms, faster than 37.21% of Java online submissions for Course Schedule.
//Memory Usage: 79.7 MB, less than 5.08% of Java online submissions for Course Schedule.
//typical topology sort
class Solution207
{
	int[][] pred, succ;

	boolean work(int n)
	{
		int[] q = new int[n];
		boolean[] used = new boolean[n];
		int[] remfather = new int[n];
		int f = 0, r = 0;
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
				remfather[i] += pred[i][j];
			if (remfather[i] == 0)
			{
				q[r++] = i;
				used[i] = true;
			}
		}
		while (f < r)
		{
			for (int i = 0; i < n; i++)
				if (succ[q[f]][i] == 1)
				{
					remfather[i]--;
					if (remfather[i] == 0 && !used[i])
					{
						q[r++] = i;
						used[i] = true;
					}
				}
			f++;
		}
		return r == n;
	}

	void trans(int n, int[][] x)
	{
		pred = new int[n][n];
		succ = new int[n][n];
		for (int i = 0; i < x.length; i++)
		{
			int s = x[i][0];
			for (int j = 1; j < x[i].length; j++)
			{
				succ[x[i][j]][s] = 1;
				pred[s][x[i][j]] = 1;
			}
		}
	}

	public boolean canFinish(int numCourses, int[][] prerequisites)
	{
		trans(numCourses, prerequisites);
		return work(numCourses);
	}
}

//208. Implement Trie (Prefix Tree)
//Runtime: 85 ms, faster than 99.91% of Java online submissions for Implement Trie (Prefix Tree).
//Memory Usage: 113.2 MB, less than 5.10% of Java online submissions for Implement Trie (Prefix Tree).

//In Trie.java
class Trie extends StrTrie
{

}

/**
 * Your Trie object will be instantiated and called as such: Trie obj = new
 * Trie(); obj.insert(word); boolean param_2 = obj.search(word); boolean param_3
 * = obj.startsWith(prefix);
 */

//209. Minimum Size Subarray Sum
//Runtime: 6 ms, faster than 19.01% of Java online submissions for Minimum Size Subarray Sum.
//Memory Usage: 37.5 MB, less than 17.70% of Java online submissions for Minimum Size Subarray Sum.
class Solution209
{
	int[] ps;

	int ss(int l, int r)
	{
		if (l == 0)
			return ps[r];
		return ps[r] - ps[l - 1];
	}

	int sch(int s, int l, int r, int t)
	{
		if (l > r)
			return -1;
		if (l == r)
			if (ss(s, r) >= t)
				return r;
			else
				return -1;
		if (r == l + 1)
		{
			if (ss(s, l) >= t)
				return l;
			else if (ss(s, r) >= t)
				return r;
			else
				return -1;
		}
		int m = (l + r) / 2;
		if (ss(s, m) >= t)
			return sch(s, l, m, t);
		return sch(s, m + 1, r, t);
	}

	public int minSubArrayLen(int s, int[] nums)
	{
		if (nums.length == 0)
			return 0;
		ps = new int[nums.length];
		ps[0] = nums[0];
		for (int i = 1; i < nums.length; i++)
			ps[i] = ps[i - 1] + nums[i];
		int minl = nums.length;
		if (ps[nums.length - 1] < s)
			return 0;
		for (int i = 0; i < nums.length; i++)
		{
			if (ss(i, nums.length - 1) < s)
				break;
			int t = sch(i, i, nums.length - 1, s);
			if (t != -1 && t - i + 1 < minl)
				minl = t - i + 1;
		}
		return minl;
	}
}

//210. Course Schedule II
//Runtime: 25 ms, faster than 30.90% of Java online submissions for Course Schedule II.
//Memory Usage: 82.9 MB, less than 5.19% of Java online submissions for Course Schedule II.
class Solution210
{
	int[][] pred, succ;

	int[] work(int n)
	{
		int[] q = new int[n];
		boolean[] used = new boolean[n];
		int[] remfather = new int[n];
		int f = 0, r = 0;
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
				remfather[i] += pred[i][j];
			if (remfather[i] == 0)
			{
				q[r++] = i;
				used[i] = true;
			}
		}
		while (f < r)
		{
			for (int i = 0; i < n; i++)
				if (succ[q[f]][i] == 1)
				{
					remfather[i]--;
					if (remfather[i] == 0 && !used[i])
					{
						q[r++] = i;
						used[i] = true;
					}
				}
			f++;
		}
		if (r == n)
			return q;
		else
			return new int[0];
	}

	void trans(int n, int[][] x)
	{
		pred = new int[n][n];
		succ = new int[n][n];
		for (int i = 0; i < x.length; i++)
		{
			int s = x[i][0];
			for (int j = 1; j < x[i].length; j++)
			{
				succ[x[i][j]][s] = 1;
				pred[s][x[i][j]] = 1;
			}
		}
	}

	public int[] findOrder(int numCourses, int[][] prerequisites)
	{
		trans(numCourses, prerequisites);
		return work(numCourses);
	}
}

public class LC201_210
{
	public static void main(String[] args)
	{
		System.out.println(new Solution209().minSubArrayLen(15, new int[]
		{ 1, 2, 3, 4, 5 }));
	}

}
