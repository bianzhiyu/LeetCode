package lc731_740;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

//735. Asteroid Collision
//Runtime: 5 ms, faster than 99.80% of Java online submissions for Asteroid Collision.
//Memory Usage: 40.2 MB, less than 92.66% of Java online submissions for Asteroid Collision.
class Solution735
{
	static public class LN
	{
		public int M, D;
		public LN next = null;

		public LN(int m)
		{
			if (m > 0)
			{
				M = m;
				D = 1;
			} else
			{
				M = -m;
				D = -1;
			}
		}
	}

	public int[] asteroidCollision(int[] asteroids)
	{
		int len = asteroids.length;
		int remCt = len;
		LN fakeHead = new LN(0), p = fakeHead;
		for (int i = 0; i < len; i++)
		{
			p.next = new LN(asteroids[i]);
			p = p.next;
		}
		while (true)
		{
			boolean col = false;
			p = fakeHead;
			while (p.next != null && p.next.next != null)
			{
				LN q = p.next;
				if (p.next.D > 0 && q.next.D < 0)
				{
					col = true;
					if (p.next.M == q.next.M)
					{
						p.next = q.next.next;
						remCt -= 2;
					} else if (p.next.M > q.next.M)
					{
						p.next.next = q.next.next;
						remCt--;
						p = p.next;
					} else
					{
						p.next = q.next;
						p = q.next;
						remCt--;
					}
				} else
				{
					p = p.next;
				}
			}
			if (!col)
				break;
		}
		int[] ans = new int[remCt];
		p = fakeHead;
		for (int i = 0; i < remCt; i++)
		{
			p = p.next;
			ans[i] = p.M * p.D;
		}
		return ans;
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

//738. Monotone Increasing Digits
//https://leetcode.com/problems/monotone-increasing-digits/solution/
//refer the solution 2.
//Runtime: 1 ms, faster than 99.77% of Java online submissions for Monotone Increasing Digits.
//Memory Usage: 32.1 MB, less than 100.00% of Java online submissions for Monotone Increasing Digits.
class Solution738
{
	public int monotoneIncreasingDigits(int N)
	{
		char[] str = Integer.toString(N).toCharArray();
		while (true)
		{
			boolean inv = false;
			for (int i = str.length - 1; i >= 1; i--)
			{
				if (str[i] < str[i - 1])
				{
					inv = true;
					str[i - 1]--;
					for (int j = i; j < str.length; j++)
						str[j] = '9';
					break;
				}
			}
			if (!inv)
			{
				int x = 0;
				for (int i = 0; i < str.length; i++)
					x = x * 10 + (str[i] - '0');
				return x;
			}
		}
	}
}

//740. Delete and Earn
//Runtime: 9 ms, faster than 23.28% of Java online submissions for Delete and Earn.
//Memory Usage: 39.4 MB, less than 44.30% of Java online submissions for Delete and Earn.
class Solution740
{
	private static class Mp implements Comparable<Mp>
	{
		private int num, ct;

		public Mp(int n, int c)
		{
			num = n;
			ct = c;
		}

		public int compareTo(Mp o)
		{
			return num - o.num;
		}

		public int totV()
		{
			return num * ct;
		}

		public String toString()
		{
			return "(" + num + "," + ct + ")";
		}
	}

	public int deleteAndEarn(int[] nums)
	{
		if (nums.length == 0)
			return 0;
		HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++)
			freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
		List<Mp> l = new ArrayList<Mp>();
		for (int i : freq.keySet())
			l.add(new Mp(i, freq.get(i)));
		Collections.sort(l);
		int len = l.size();
		int[] maxSel = new int[len];
		int[] maxDis = new int[len];
		maxSel[0] = l.get(0).totV();
		maxDis[0] = 0;
		for (int i = 1; i < len; i++)
		{
			if (l.get(i - 1).num == l.get(i).num - 1)
			{
				maxSel[i] = maxDis[i - 1] + l.get(i).totV();
				maxDis[i] = Math.max(maxSel[i - 1], maxDis[i - 1]);
			} else
			{
				maxSel[i] = l.get(i).totV() + Math.max(maxSel[i - 1], maxDis[i - 1]);
				maxDis[i] = Math.max(maxSel[i - 1], maxDis[i - 1]);
			}
		}
		return Math.max(maxSel[len - 1], maxDis[len - 1]);
	}
}

public class LC731_740
{
	public static void test735()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input735.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output735.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int[] nums = test.Test.parseIntArr(inLine);

				Solution735 s = new Solution735();
				int[] ans = s.asteroidCollision(nums);

				String ansstr = test.Test.intArrToString(ans);
				System.out.println(ansstr);
				bfw.write(ansstr);
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

	public static void test740()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input740.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output740.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int[] nums = test.Test.parseIntArr(inLine);

				Solution740 s = new Solution740();

				int ans = s.deleteAndEarn(nums);
				System.out.println(ans);

				bfw.write("" + ans);
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

	public static void main(String[] agrs)
	{
		test740();
	}
}
