package lc451_460;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import bbst.BBST;

//451. Sort Characters By Frequency
//Runtime: 17 ms, faster than 88.61% of Java online submissions for Sort Characters By Frequency.
//Memory Usage: 39 MB, less than 89.94% of Java online submissions for Sort Characters By Frequency.

class Solution451
{
	private static class MPair implements Comparable<MPair>
	{
		char key;
		int ct;

		MPair(char _key, int _ct)
		{
			key = _key;
			ct = _ct;
		}

		@Override
		public int compareTo(MPair o)
		{
			return key - o.key;
		}

	}

	public String frequencySort(String s)
	{
		if (s.length() == 0)
			return "";
		BBST<MPair> rt = new BBST<MPair>(new MPair(s.charAt(0), 1));
		for (int i = 1; i < s.length(); i++)
		{
			MPair p = new MPair(s.charAt(i), 1);
			if (rt.containData(p))
			{
				p = rt.getData(p);
				p.ct++;
			} else
				rt = rt.insert(p);
		}
		List<MPair> l1 = new ArrayList<MPair>();
		while (rt != null)
		{
			MPair p = rt.getMinData();
			rt = rt.removeMin();
			l1.add(p);
		}
		Collections.sort(l1, new Comparator<MPair>()
		{
			@Override
			public int compare(MPair o1, MPair o2)
			{
				return o2.ct - o1.ct;
			}
		});
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < l1.size(); i++)
		{
			MPair p = l1.get(i);
			for (int j = 0; j < p.ct; j++)
				sb.append(p.key);
		}
		return sb.toString();
	}
}

// 452. Minimum Number of Arrows to Burst Balloons
//Time Limit Exceeded
//case:
//[[0,2100000000]]
class Solution452
{
	public int findMinArrowShots(int[][] points)
	{
		int len = points.length;
		boolean[] burst = new boolean[len];
		int ct = 0;
		for (int i = 0; i < len; i++)
			if (!burst[i])
			{
				ct++;
				int burstpos = 0, maxburstnum = -1;
				for (int j = points[i][0]; j <= points[i][1]; j++)
				{
					int tct = 0;
					for (int k = 0; k < len; k++)
						if (!burst[k] && points[k][0] <= j && j <= points[k][1])
							tct++;
					if (tct > maxburstnum)
					{
						burstpos = j;
						maxburstnum = tct;
					}
				}
				for (int k = 0; k < len; k++)
					if (!burst[k] && points[k][0] <= burstpos && burstpos <= points[k][1])
						burst[k] = true;
			}
		return ct;
	}
}

// Runtime: 22 ms, faster than 97.07% of Java online submissions for Minimum Number of Arrows to Burst Balloons.
// Memory Usage: 44 MB, less than 95.61% of Java online submissions for Minimum Number of Arrows to Burst Balloons.
//https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/discuss/248491/Java-Easy-sort-solution-beating-100
class Solution452_2
{
	public int findMinArrowShots(int[][] points)
	{
		if (points == null || points.length == 0)
			return 0;
		if (points.length == 1)
			return 1;
		Arrays.sort(points, new Comparator<int[]>()
		{
			public int compare(int[] o1, int[] o2)
			{
				if (o1[1] < o2[1])
					return -1;
				if (o1[1] > o2[1])
					return 1;
				return 0;
			}
		});
		int pos = Integer.MIN_VALUE, ct = 0;
		for (int i = 0; i < points.length; i++)
			if (pos < points[i][0])
			{
				ct++;
				pos = points[i][1];
			}
		return ct;
	}
}

//453. Minimum Moves to Equal Array Elements
//Runtime: 2 ms, faster than 95.05% of Java online submissions for Minimum Moves to Equal Array Elements.
//Memory Usage: 40.8 MB, less than 83.72% of Java online submissions for Minimum Moves to Equal Array Elements.
class Solution453
{
	public int minMoves(int[] nums)
	{
		int min = nums[0];
		for (int i = 1; i < nums.length; i++)
			if (nums[i] < min)
				min = nums[i];
		int s = 0;
		for (int i = 0; i < nums.length; i++)
			s += nums[i] - min;
		return s;
	}
}

//454. 4Sum II
class Solution454
{
	HashMap<Integer, Integer> parse(int[] A)
	{
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0; i < A.length; i++)
			if (hm.containsKey(A[i]))
				hm.put(A[i], hm.get(A[i]) + 1);
			else
				hm.put(A[i], 1);
		return hm;
	}

	public int fourSumCount(int[] A, int[] B, int[] C, int[] D)
	{
		HashMap<Integer, Integer> h1 = parse(A), h2 = parse(B), h3 = parse(C), h4 = parse(D);
		int tot = 0;
		for (int i : h1.keySet())
			for (int j : h2.keySet())
				for (int k : h3.keySet())
					if (h4.containsKey(-i - j - k))
						tot += h1.get(i) * h2.get(j) * h3.get(k) * h4.get(-i - j - k);
		return tot;
	}
}

//https://leetcode.com/problems/4sum-ii/submissions/
//Runtime: 89 ms, faster than 61.49% of Java online submissions for 4Sum II.
//Memory Usage: 59.3 MB, less than 35.94% of Java online submissions for 4Sum II.
class Solution454_2
{
	public int fourSumCount(int[] A, int[] B, int[] C, int[] D)
	{
		Map<Integer, Integer> countAB = new HashMap<Integer, Integer>();
		int answer = 0;
		for (int a : A)
			for (int b : B)
				countAB.put(a + b, 1 + countAB.getOrDefault(a + b, 0));
		for (int c : C)
			for (int d : D)
				answer += countAB.getOrDefault(-c - d, 0);
		return answer;
	}
}

//455. Assign Cookies
//Runtime: 9 ms, faster than 39.07% of Java online submissions for Assign Cookies.
//Memory Usage: 39.5 MB, less than 99.73% of Java online submissions for Assign Cookies.
class Solution455 
{
    public int findContentChildren(int[] g, int[] s) 
    {
    	Arrays.parallelSort(g);
    	Arrays.parallelSort(s);
    	int gp=0,ans=0;
    	for (int sp=0;sp<s.length && gp<g.length;sp++)
    	{
    		if (s[sp]>=g[gp])
    		{
    			gp++;
    			ans++;
    		}
    	}
    	return ans;
    }
}

//456. 132 Pattern
//Runtime: 271 ms, faster than 30.78% of Java online submissions for 132 Pattern.
//Memory Usage: 40.7 MB, less than 34.33% of Java online submissions for 132 Pattern.
class Solution456
{
	public boolean find132pattern(int[] nums)
	{
		int len = nums.length;
		if (len < 3)
			return false;
		int[] leftmin = new int[len];
		leftmin[0] = Integer.MAX_VALUE;
		for (int i = 1; i < len; i++)
		{
			leftmin[i] = nums[i - 1];
			if (leftmin[i - 1] < leftmin[i])
				leftmin[i] = leftmin[i - 1];
		}
		for (int i = 1; i < len; i++)
			for (int j = i + 1; j < len; j++)
				if (nums[i] > nums[j] && nums[j] > leftmin[i])
					return true;
		return false;
	}
}

//457. Circular Array Loop
//Runtime: 2 ms, faster than 48.37% of Java online submissions for Circular Array Loop.
//Memory Usage: 36.8 MB, less than 19.83% of Java online submissions for Circular Array Loop.
class Solution457
{
	private int next(int now, int len, int step)
	{
		now = now + step;
		now %= len;
		if (now < 0)
			now += len;
		return now;
	}

	public boolean circularArrayLoop(int[] nums)
	{
		int len = nums.length;
		boolean[] checked = new boolean[len];
		HashSet<Integer> hs;
		int[] qe = new int[len];
		for (int i = 0; i < len; i++)
		{
			if (checked[i])
				continue;
			hs = new HashSet<Integer>();
			hs.add(i);
			int pos = i;
			boolean pm0 = nums[i] > 0 ? true : false;
			int st = 1;
			qe[0] = i;
			checked[i] = true;
			while (true)
			{
				pos = next(pos, len, nums[pos]);
				if (nums[pos] < 0 && pm0 || nums[pos] > 0 && !pm0)
					break;
				if (pos == qe[st - 1])
					break;
				if (hs.contains(pos))
					return true;
				hs.add(pos);
				checked[pos] = true;
				qe[st++] = pos;
			}
		}
		return false;
	}
}

//459. Repeated Substring Pattern
//Runtime: 13 ms, faster than 75.81% of Java online submissions for Repeated Substring Pattern.
//Memory Usage: 37 MB, less than 100.00% of Java online submissions for Repeated Substring Pattern.
class Solution459 
{
    public boolean repeatedSubstringPattern(String s) 
    {
    	char[] str=s.toCharArray();
    	int l=0;
    	for (int divids=2;divids<=str.length;divids++)
    	{
    		l=str.length%divids;
    		if (l!=0) continue;
    		l=str.length/divids;
    		boolean notMatch=false;
    		for (int i=0;i<l && !notMatch;i++)
    			for (int j=i+l;j<str.length && !notMatch;j+=l)
    			{
    				if (str[i]!=str[j]) notMatch=true;
    			}
    		if (!notMatch) return true;
    	}
        return false;
    }
}

// 460. LFU Cache
// This question does not give a clear description.
// This annoys me.
// Especially for these two cases:
// ["LFUCache","put","put","get","put","get","get","put","get","get","get"]
// [[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
// ["LFUCache","put","put","get","get","get","put","put","get","get","get","get"]
// [[3],[2,2],[1,1],[2],[1],[2],[3,3],[4,4],[3],[2],[1],[4]]

// https://leetcode.com/problems/lfu-cache/discuss/212604/Easy-Java
// This guy gives a clear description of the question.
// And his code is clear and not redundant.

// Attention: the question does not say that you should call get in put.

// Runtime: 97 ms, faster than 60.19% of Java online submissions for LFU Cache.
// Memory Usage: 64.3 MB, less than 44.21% of Java online submissions for LFU
// Cache.
class LFUCache
{
	Map<Integer, Integer> vals;
	Map<Integer, Integer> counts;
	Map<Integer, LinkedHashSet<Integer>> list;

	int minCount = 1, cap;

	public LFUCache(int capacity)
	{
		vals = new HashMap<>();
		counts = new HashMap<>();
		list = new HashMap<>();
		cap = capacity;
		list.put(1, new LinkedHashSet<>());
	}

	public int get(int key)
	{
		if (!vals.containsKey(key))
			return -1;
		int count = counts.get(key);
		counts.put(key, count + 1);
		list.get(count).remove(key);
		if (!list.containsKey(count + 1))
			list.put(count + 1, new LinkedHashSet<>());
		list.get(count + 1).add(key);
		if (count == minCount && list.get(count).size() == 0)
			minCount++; // ����minCount
		return vals.get(key);
	}

	public void put(int key, int value)
	{
		if (cap <= 0)
			return;
		if (vals.containsKey(key))
		{
			vals.put(key, value);
			get(key);
			return;
		}
		if (vals.size() >= cap)
		{
			int deleteKey = list.get(minCount).iterator().next();
			vals.remove(deleteKey);
			list.get(minCount).remove(deleteKey);
		}
		vals.put(key, value);
		counts.put(key, 1);
		minCount = 1; // ����minCount
		list.get(1).add(key);
	}
}

public class LC451_460
{
	public static void testLFUCache()
	{
		LFUCache c = new LFUCache(3);
		c.put(2, 2);
		c.put(1, 1);
		c.get(2);
		c.get(1);
		c.get(2);
		c.put(3, 3);
		c.put(4, 4);
		c.get(3);
		c.get(2);
		c.get(1);
		c.get(4);
	}

	public static void test454()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input454.txt");
			File outFile = new File("output" + File.separator + "output454.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String str;

			Solution454_2 s = new Solution454_2();

			while ((str = bfr.readLine()) != null && str.length() > 0)
			{
				int ans = s.fourSumCount(test.Test.parseIntArr(str), test.Test.parseIntArr(bfr.readLine()),
						test.Test.parseIntArr(bfr.readLine()), test.Test.parseIntArr(bfr.readLine()));

				System.out.println(ans);

				bfw.write(ans + "");
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

	public static void test457()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input457.txt");
			File outFile = new File("output" + File.separator + "output457.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String str;

			Solution457 s = new Solution457();

			while ((str = bfr.readLine()) != null && str.length() > 0)
			{
				str = str.trim();
				str = str.substring(1, str.length() - 1);
				String[] spstr = str.split(",");
				int[] nums = new int[spstr.length];
				for (int i = 0; i < spstr.length; i++)
					nums[i] = Integer.parseInt(spstr[i].trim());

				boolean ans = s.circularArrayLoop(nums);
				System.out.println(ans);

				bfw.write(ans + "");
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

	public static void test453()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input453.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output453.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int ans = 0;

				System.out.println(ans = (new Solution453().minMoves(test.Test.parseIntArr(inLine))));

				bfw.write(Integer.toString(ans));
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
	public static void test459()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input459.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output459.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				boolean ans=new Solution459().repeatedSubstringPattern(inLine.substring(1,inLine.length()-1));

				bfw.write("" + ans);
				bfw.newLine();
				
				System.out.println(ans);
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
		test459();
	}

}
