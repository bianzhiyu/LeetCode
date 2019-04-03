package lc981_990;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import bbst.MTreeSet;

//981. Time Based Key-Value Store
//Runtime: 240 ms, faster than 82.95% of Java online submissions for Time Based Key-Value Store.
//Memory Usage: 142 MB, less than 65.22% of Java online submissions for Time Based Key-Value Store.
class TimeMap
{
	private static class DP implements Comparable<DP>
	{
		private int time;
		private String value;

		public DP(int t, String v)
		{
			time = t;
			value = v;
		}

		public int compareTo(DP o)
		{
			return time - o.time;
		}
	}

	private HashMap<String, MTreeSet<DP>> h = new HashMap<String, MTreeSet<DP>>();

	/** Initialize your data structure here. */
	public TimeMap()
	{
	}

	public void set(String key, String value, int timestamp)
	{
		if (!h.containsKey(key))
			h.put(key, new MTreeSet<DP>());
		h.get(key).insert(new DP(timestamp, value));
	}

	public String get(String key, int timestamp)
	{
		if (!h.containsKey(key))
			return "";
		DP d = h.get(key).getNoLargerThanAndMax(new DP(timestamp, null));
		if (d == null)
			return "";
		else
			return d.value;
	}
}

/**
 * Your TimeMap object will be instantiated and called as such: TimeMap obj =
 * new TimeMap(); obj.set(key,value,timestamp); String param_2 =
 * obj.get(key,timestamp);
 */

//983. Minimum Cost For Tickets
//Runtime: 9 ms, faster than 10.77% of Java online submissions for Minimum Cost For Tickets.
//Memory Usage: 41.4 MB, less than 5.24% of Java online submissions for Minimum Cost For Tickets.
class Solution983
{
	private int[] d, c;// 1,7,30
	private HashMap<Integer, Integer> r = new HashMap<Integer, Integer>();

	private int dfs(int p, int covered)
	{
		if (p >= d.length)
			return 0;
		int state = p * 500 + covered;
		if (r.containsKey(state))
			return r.get(state);
		int min;
		if (d[p] <= covered)
			min = dfs(p + 1, covered);
		else
		{
			min = c[0] + dfs(p + 1, d[p]);
			min = Math.min(min, c[1] + dfs(p + 1, d[p] + 6));
			min = Math.min(min, c[2] + dfs(p + 1, d[p] + 29));
		}
		r.put(state, min);
		return min;
	}

	public int mincostTickets(int[] days, int[] costs)
	{
		d = days;
		c = costs;
		return dfs(0, -1);
	}
}

//984. String Without AAA or BBB
//Runtime: 0 ms, faster than 100.00% of Java online submissions for String Without AAA or BBB.
//Memory Usage: 35.7 MB, less than 100.00% of Java online submissions for String Without AAA or BBB.
class Solution984
{
	public String strWithout3a3b(int A, int B)
	{
		StringBuilder sb = new StringBuilder();
		int bef = -1;
		while (A > 0 || B > 0)
		{
			if (A > B)
			{
				if (bef == -1)
				{
					if (A >= 2)
					{
						sb.append('a').append('a');
						A -= 2;
						bef = 0;
					} else
					{
						sb.append('a');
						A--;
						bef = 0;
					}
				} else if (bef == 0) // a
				{
					sb.append('b');
					B--;
					bef = 1;
				} else // bef=1, b
				{
					if (A >= 2)
					{
						sb.append('a').append('a');
						A -= 2;
						bef = 0;
					} else
					{
						sb.append('a');
						A--;
						bef = 0;
					}
				}
			} else if (A < B)
			{
				if (bef == -1)
				{
					if (B >= 2)
					{
						sb.append('b').append('b');
						B -= 2;
						bef = 1;
					} else
					{
						sb.append('B');
						B--;
						bef = 1;
					}
				} else if (bef == 1) // b
				{
					sb.append('a');
					A--;
					bef = 0;
				} else // bef=2, a
				{
					if (B >= 2)
					{
						sb.append('b').append('b');
						B -= 2;
						bef = 1;
					} else
					{
						sb.append('b');
						B--;
						bef = 1;
					}
				}
			} else// A==B
			{
				if (bef == 0) // a
				{
					sb.append('b');
					B--;
					bef = 1;
				} else
				{
					sb.append('a');
					A--;
					bef = 0;
				}
			}
		}
		return sb.toString();
	}
}

//986. Interval List Intersections
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
}

class Solution986
{
	public Interval[] intervalIntersection(Interval[] A, Interval[] B)
	{
		return null;
	}
}

public class LC981_990
{
	public static void test983()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input983.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output983.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int[] d = test.Test.parseIntArr(inLine);
				int[] c = test.Test.parseIntArr(bfr.readLine());

				Solution983 s = new Solution983();
				int ans = s.mincostTickets(d, c);
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

	public static void main(String[] args)
	{
		test983();
	}
}
