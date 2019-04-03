package lc431_440;

import java.util.Arrays;
import java.util.Comparator;

//433. Minimum Genetic Mutation
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Minimum Genetic Mutation.
//Memory Usage: 36.5 MB, less than 94.37% of Java online submissions for Minimum Genetic Mutation.
class Solution433
{
	int countDiff(String s1, String s2)
	{
		int ct = 0;
		for (int i = 0; i < 8; i++)
			if (s1.charAt(i) != s2.charAt(i))
				ct++;
		return ct;
	}

	public int minMutation(String start, String end, String[] bank)
	{
		if (countDiff(start, end) == 0)
			return 0;
		int endInd = -1;
		for (int i = 0; i < bank.length; i++)
			if (countDiff(end, bank[i]) == 0)
			{
				endInd = i;
				break;
			}
		if (endInd == -1)
			return -1;
		int len = bank.length;
		int[] q = new int[len + 1];
		int[] step = new int[len + 1];
		boolean[] used = new boolean[len + 1];
		int f = 0, r = 0;
		for (int i = 0; i < bank.length; i++)
			if (countDiff(start, bank[i]) == 1)
			{
				step[r] = 1;
				q[r++] = i;
				used[i] = true;
			}
		while (f < r)
		{
			if (q[f] == endInd)
				return step[f];
			for (int i = 0; i < len; i++)
				if (!used[i] && countDiff(bank[q[f]], bank[i]) == 1)
				{
					used[i] = true;
					step[r] = step[f] + 1;
					q[r++] = i;
				}
			f++;
		}
		return -1;
	}
}

//436. Find Right Interval

/**
 * Definition for an interval.
 */
class Interval
{
	int start;
	int end;

	Interval()
	{
		start = 0;
		end = 0;
	}

	Interval(int s, int e)
	{
		start = s;
		end = e;
	}

	public String toString()
	{
		return "(" + start + ", " + end + ")";
	}
}

//Runtime: 10 ms, faster than 98.07% of Java online submissions for Find Right Interval.
//Memory Usage: 47.8 MB, less than 58.93% of Java online submissions for Find Right Interval.
class Solution436
{
	private static class Mt
	{
		int s, e, idx;

		public Mt(int _s, int _e, int _idx)
		{
			s = _s;
			e = _e;
			idx = _idx;
		}

		public String toString()
		{
			return "(" + idx + ": " + s + ", " + e + ")";
		}
	}

	private int fd(Mt[] a, int l, int r, int t)
	{
		if (l > r)
			return -1;
		if (l == r)
		{
			if (a[l].s >= t)
				return l;
			else
				return -1;
		}
		if (r == l + 1)
		{
			if (a[l].s >= t)
				return l;
			else if (a[r].s >= t)
				return r;
			else
				return -1;
		}
		int m = (l + r) / 2;
		if (a[m].s >= t)
			return fd(a, l, m, t);
		else
			return fd(a, m + 1, r, t);
	}

	public int[] findRightInterval(Interval[] intervals)
	{
		int len = intervals.length;
		Mt[] a = new Mt[len];

		for (int i = 0; i < len; i++)
			a[i] = new Mt(intervals[i].start, intervals[i].end, i);
		Arrays.parallelSort(a, new Comparator<Mt>()
		{
			@Override
			public int compare(Mt o1, Mt o2)
			{
				return o1.s - o2.s;
			}
		});
		for (int i = 0; i < len; i++)
			System.out.println(a[i]);
		int[] next1 = new int[len];
		next1[len - 1] = -1;
		for (int i = len - 2; i >= 0; i--)
		{
			next1[i] = fd(a, i + 1, len - 1, a[i].e);
		}

		int[] next2 = new int[len];
		for (int i = 0; i < len; i++)
			next2[a[i].idx] = next1[i] == -1 ? -1 : a[next1[i]].idx;
		return next2;
	}
}

public class LC431_440
{
	public static void main(String[] args)
	{
		Solution436 s = new Solution436();
		Interval[] a = new Interval[]
		{ new Interval(3, 4), new Interval(2, 3), new Interval(1, 2) };
		s.findRightInterval(a);
	}
}
