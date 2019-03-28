package lc391_400;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.Queue;
import bbst.BBST;
import qsort.Qsort;

//392. Is Subsequence
//Runtime: 402 ms, faster than 5.06% of Java online submissions for Is Subsequence.
//Memory Usage: 122.7 MB, less than 5.01% of Java online submissions for Is Subsequence.
class Solution392
{
	public boolean isSubsequence(String s, String t)
	{
		int lens = s.length(), lent = t.length();
		boolean[][] d = new boolean[lens + 1][lent + 1];
		for (int j = 0; j <= lent; j++)
			d[0][j] = true;
		for (int i = 1; i <= lens; i++)
			for (int j = 1; j <= lent; j++)
			{
				d[i][j] = d[i][j - 1];
				if (s.charAt(i - 1) == t.charAt(j - 1))
					d[i][j] = d[i][j] || d[i - 1][j - 1];
			}
		return d[lens][lent];
	}
}

//https://leetcode.com/problems/is-subsequence/discuss/253878/Clean-Easy-to-understand-Java-Solution-(100Time-and-95Space-optimized)
//	Runtime: 1 ms, faster than 100.00% of Java online submissions for Is Subsequence.
//	Memory Usage: 49.5 MB, less than 93.64% of Java online submissions for Is Subsequence.
class Solution392_2
{
	public boolean isSubsequence(String s, String t)
	{
		int flag = -10;
		for (int i = 0; i < s.length(); i++)
		{
			char temp = s.charAt(i);
			if (i == 0)
			{
				flag = t.indexOf(temp);
				if (flag == -1)
					return false;
			} else
			{
				flag = t.indexOf(temp, flag + 1);
				if (flag == -1)
					return false;
			}
		}

		return true;
	}
}

//394. Decode String
//Runtime: 2 ms, faster than 82.37% of Java online submissions for Decode String.
//Memory Usage: 37 MB, less than 13.68% of Java online submissions for Decode String.
class Token394
{
	int type;
	// 0: num, 1: str, 2:[
	int num;
	String str;

	Token394(int _t, int _n, String _s)
	{
		type = _t;
		num = _n;
		str = _s;
	}

	public String toString()
	{
		if (type == 0)
			return "" + num;
		else if (type == 1)
			return str;
		else
			return "[";
	}
}

class Solution394
{
	boolean isnum(char c)
	{
		return '0' <= c && c <= '9';
	}

	boolean isalphabet(char c)
	{
		return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
	}

	public String decodeString(String s)
	{
		if (s.length() == 0)
			return "";
		int rp = 0, slen = s.length();
		Stack<Token394> stack = new Stack<Token394>();
		while (rp < slen)
		{
			if (isnum(s.charAt(rp)))
			{
				int x = s.charAt(rp) - '0';
				int j = rp + 1;
				while (j < slen && isnum(s.charAt(j)))
				{
					x = x * 10 + (s.charAt(j) - '0');
					j++;
				}
				stack.push(new Token394(0, x, ""));
				rp = j;
			} else if (s.charAt(rp) == '[')
			{
				stack.push(new Token394(2, 0, ""));
				rp++;
			} else if (s.charAt(rp) == ']')
			{
				rp++;
				String subs = stack.pop().str;
				while (!stack.isEmpty() && stack.peek().type == 1)
					subs = stack.pop().str + subs;
				stack.pop();
				Token394 mul = stack.pop();
				String tmp = "";
				for (int i = 0; i < mul.num; i++)
					tmp = tmp + subs;
				stack.push(new Token394(1, 0, tmp));
			} else
			{
				int j = rp + 1;
				while (j < slen && isalphabet(s.charAt(j)))
					j++;
				stack.push(new Token394(1, 0, s.substring(rp, j)));
				rp = j;
			}
		}
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty())
		{
			sb.insert(0, stack.pop().str);
		}
		return sb.toString();
	}
}

//Runtime: 2 ms, faster than 100.00% of Java online submissions for Rotate Function.
//Memory Usage: 39.5 MB, less than 52.94% of Java online submissions for Rotate Function.
class Solution396
{
	public int maxRotateFunction(int[] A)
	{
		int S = 0, S0 = 0;
		for (int i = 0; i < A.length; i++)
		{
			S += A[i];
			S0 += A[i] * i;
		}
		int max = S0;
		for (int i = 1; i < A.length; i++)
		{
			S0 += A.length * A[i - 1] - S;
			if (S0 > max)
				max = S0;
		}
		return max;
	}
}

//397. Integer Replacement
//Runtime: 3 ms, faster than 60.33% of Java online submissions for Integer Replacement.
//Memory Usage: 33.5 MB, less than 100.00% of Java online submissions for Integer Replacement.
class Solution397
{
	public int integerReplacement(int n)
	{
		if (n == 1)
			return 0;
		long start = n;
		Queue<Long> q1 = new LinkedList<Long>();
		Queue<Integer> q2 = new LinkedList<Integer>();
		HashSet<Long> h = new HashSet<Long>();
		q1.add(start);
		q2.add(0);
		h.add(start);
		while (!q1.isEmpty())
		{
			long nn = q1.poll();
			int step = q2.poll();
			if (nn == 1)
				return step;
			if (nn % 2 == 0)
			{
				if (!h.contains(nn / 2))
				{
					q1.add(nn / 2);
					q2.add(step + 1);
					h.add(nn / 2);
				}
			} else
			{
				if (!h.contains(nn + 1))
				{
					q1.add(nn + 1);
					q2.add(step + 1);
					h.add(nn + 1);
				}
				if (!h.contains(nn - 1))
				{
					q1.add(nn - 1);
					q2.add(step + 1);
					h.add(nn - 1);
				}
			}
		}
		return -1;
	}
}

//398. Random Pick Index
//https://leetcode.com/problems/random-pick-index/discuss/88150/Simple-JAVA-solution
//Runtime: 98 ms, faster than 96.36% of Java online submissions for Random Pick Index.
//Memory Usage: 53.7 MB, less than 68.71% of Java online submissions for Random Pick Index.
//I am amazed that such code can be faster than 96% posts.
class Solution398
{
	int[] input;
	Random rand;

	public Solution398(int[] nums)
	{
		input = nums;
		rand = new Random();
	}

	public int pick(int target)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < input.length; i++)
		{
			if (input[i] == target)
				list.add(i);
		}

		int index = rand.nextInt(list.size());
		return list.get(index);
	}
}

//Runtime: 165 ms, faster than 15.42% of Java online submissions for Random Pick Index.
//Memory Usage: 57.3 MB, less than 29.05% of Java online submissions for Random Pick Index.
class Mp implements Comparable<Mp>
{
	int val, ind;

	Mp(int _v, int _i)
	{
		val = _v;
		ind = _i;
	}

	@Override
	public int compareTo(Mp o)
	{
		if (val < o.val)
			return -1;
		if (val > o.val)
			return 1;
		return 0;
	}

	int compareTo(int x)
	{
		if (val < x)
			return -1;
		if (val > x)
			return 1;
		return 0;
	}

	public String toString()
	{
		return val + "";
	}
}

class Solution398_2
{
	Random rand;
	List<Mp> in;

	public Solution398_2(int[] nums)
	{
		in = new ArrayList<Mp>();
		rand = new Random();
		for (int i = 0; i < nums.length; i++)
			in.add(new Mp(nums[i], i));
		Collections.sort(in);
	}

	int fdleft(int tar)
	{
		int l = 0, r = in.size() - 1;
		while (l < r)
		{
			if (r == l + 1)
			{
				if (in.get(l).compareTo(tar) < 0)
					l = r;
				else
					r = l;
			} else
			{
				int m = (l + r) / 2;
				if (in.get(m).compareTo(tar) >= 0)
					r = m;
				else
					l = m + 1;
			}
		}
		return l;
	}

	int fdright(int tar)
	{
		int l = 0, r = in.size() - 1;
		while (l < r)
		{
			if (r == l + 1)
			{
				if (in.get(r).compareTo(tar) > 0)
					r = l;
				else
					l = r;
			} else
			{
				int m = (l + r) / 2;
				if (in.get(m).compareTo(tar) <= 0)
					l = m;
				else
					r = m - 1;
			}
		}
		return r;
	}

	public int pick(int target)
	{
		int l = fdleft(target), r = fdright(target);
		return in.get(l + rand.nextInt(r - l + 1)).ind;
	}
}

// Runtime: 140 ms, faster than 39.12% of Java online submissions for Random Pick Index.
// Memory Usage: 56.4 MB, less than 41.34% of Java online submissions for Random Pick Index.

class Solution398_3
{
	Random rand;
	List<Mp> in;

	public Solution398_3(int[] nums)
	{
		in = new ArrayList<Mp>();
		rand = new Random();

		BBST<Mp> rt = new BBST<Mp>(new Mp(nums[0], 0));
		for (int i = 1; i < nums.length; i++)
			rt = rt.insert(new Mp(nums[i], i));
		while (rt != null)
		{
			in.add(rt.getMinData());
			rt = rt.removeMin();
		}
	}

	int fdleft(int tar)
	{
		int l = 0, r = in.size() - 1;
		while (l < r)
		{
			if (r == l + 1)
			{
				if (in.get(l).compareTo(tar) < 0)
					l = r;
				else
					r = l;
			} else
			{
				int m = (l + r) / 2;
				if (in.get(m).compareTo(tar) >= 0)
					r = m;
				else
					l = m + 1;
			}
		}
		return l;
	}

	int fdright(int tar)
	{
		int l = 0, r = in.size() - 1;
		while (l < r)
		{
			if (r == l + 1)
			{
				if (in.get(r).compareTo(tar) > 0)
					r = l;
				else
					l = r;
			} else
			{
				int m = (l + r) / 2;
				if (in.get(m).compareTo(tar) <= 0)
					l = m;
				else
					r = m - 1;
			}
		}
		return r;
	}

	public int pick(int target)
	{
		int l = fdleft(target), r = fdright(target);
		return in.get(l + rand.nextInt(r - l + 1)).ind;
	}
}

// Runtime: 140 ms, faster than 39.12% of Java online submissions for Random Pick Index.
// Memory Usage: 56.4 MB, less than 41.34% of Java online submissions for Random Pick Index.
class Solution398_4
{
	Random rand;
	List<Mp> in;

	public Solution398_4(int[] nums)
	{
		in = new ArrayList<Mp>();
		rand = new Random();

		for (int i = 0; i < nums.length; i++)
			in.add(new Mp(nums[i], i));

		Qsort.qsort(in);
	}

	int fdleft(int tar)
	{
		int l = 0, r = in.size() - 1;
		while (l < r)
		{
			if (r == l + 1)
			{
				if (in.get(l).compareTo(tar) < 0)
					l = r;
				else
					r = l;
			} else
			{
				int m = (l + r) / 2;
				if (in.get(m).compareTo(tar) >= 0)
					r = m;
				else
					l = m + 1;
			}
		}
		return l;
	}

	int fdright(int tar)
	{
		int l = 0, r = in.size() - 1;
		while (l < r)
		{
			if (r == l + 1)
			{
				if (in.get(r).compareTo(tar) > 0)
					r = l;
				else
					l = r;
			} else
			{
				int m = (l + r) / 2;
				if (in.get(m).compareTo(tar) <= 0)
					l = m;
				else
					r = m - 1;
			}
		}
		return r;
	}

	public int pick(int target)
	{
		int l = fdleft(target), r = fdright(target);
		return in.get(l + rand.nextInt(r - l + 1)).ind;
	}
}

/**
 * Your Solution object will be instantiated and called as such: Solution obj =
 * new Solution(nums); int param_1 = obj.pick(target);
 */

public class LC391_400
{
	public static void main(String[] args)
	{

		int[] a = new int[]
		{ 1, 2, 3, 3, 3 };
		Solution398_4 s = new Solution398_4(a);
		System.out.println(s.pick(3));
	}
}
