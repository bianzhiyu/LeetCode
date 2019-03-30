package lc731_740;

import java.util.ArrayList;
import java.util.List;

//731. My Calendar II
//https://leetcode.com/problems/my-calendar-ii/submissions/
//Runtime: 125 ms, faster than 80.64% of Java online submissions for My Calendar II.
//Memory Usage: 51.4 MB, less than 60.94% of Java online submissions for My Calendar II.
class MyCalendarTwo
{
	List<int[]> calendar;
	List<int[]> overlaps;

	MyCalendarTwo()
	{
		calendar = new ArrayList<int[]>();
		overlaps = new ArrayList<int[]>();
	}

	public boolean book(int start, int end)
	{
		for (int[] iv : overlaps)
		{
			if (iv[0] < end && start < iv[1])
				return false;
		}
		for (int[] iv : calendar)
		{
			if (iv[0] < end && start < iv[1])
				overlaps.add(new int[]
				{ Math.max(start, iv[0]), Math.min(end, iv[1]) });
		}
		calendar.add(new int[]
		{ start, end });
		return true;
	}
}

//739. Daily Temperatures
//Runtime: 5 ms, faster than 99.98% of Java online submissions for Daily Temperatures.
//Memory Usage: 44.1 MB, less than 44.53% of Java online submissions for Daily Temperatures.
class Solution739
{
	public int[] dailyTemperatures(int[] T)
	{
		int len = T.length;
		int[] next = new int[len];
		for (int i = len - 2; i >= 0; i--)
		{
			int j = i + 1;
			while (j != 0 && T[j] <= T[i])
				j = next[j];
			next[i] = j;
		}
		for (int i = len - 1; i >= 0; i--)
			if (next[i] > 0)
				next[i] = next[i] - i;
		return next;
	}
}

public class LC731_740
{
	public static void main(String[] agrs)
	{

	}
}
