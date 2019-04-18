package lc841_850;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

//841. Keys and Rooms
//Runtime: 2 ms, faster than 96.97% of Java online submissions for Keys and Rooms.
//Memory Usage: 44.5 MB, less than 6.40% of Java online submissions for Keys and Rooms.
class Solution841
{
	public boolean canVisitAllRooms(List<List<Integer>> rooms)
	{
		HashSet<Integer> used = new HashSet<Integer>();
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(0);
		int canGetNum = 1;
		used.add(0);
		while (!q.isEmpty())
		{
			int h = q.poll();
			for (int j : rooms.get(h))
				if (!used.contains(j))
				{
					used.add(j);
					q.add(j);
					canGetNum++;
				}
		}
		return canGetNum == rooms.size();
	}
}

//Split Array into Fibonacci Sequence
//Runtime: 6 ms, faster than 71.48% of Java online submissions for Split Array into Fibonacci Sequence.
//Memory Usage: 40.5 MB, less than 5.88% of Java online submissions for Split Array into Fibonacci Sequence.
class Solution842
{
	public List<Integer> splitIntoFibonacci(String S)
	{
		int len = S.length();
		List<Integer> ans;
		for (int i = 1; i <= len - 2; i++)
		{
			try
			{
				Integer.parseInt(S.substring(0, i));
			} catch (NumberFormatException e)
			{
				break;
			}
			for (int j = 1; j <= len - i - 1; j++)
			{
				try
				{
					Integer.parseInt(S.substring(i, i + j));
				} catch (NumberFormatException e)
				{
					break;
				}
				if ((ans = check(S, i, j)) != null)
					return ans;
			}
		}
		return new ArrayList<Integer>();
	}

	List<Integer> check(String num, int len1, int len2)
	{
		List<Integer> ans = new ArrayList<Integer>();
		if (len1 > 1 && num.charAt(0) == '0')
			return null;
		if (len2 > 1 && num.charAt(len1) == '0')
			return null;
		ans.add(Integer.parseInt(num.substring(0, len1)));
		ans.add(Integer.parseInt(num.substring(len1, len1 + len2)));
		int len = num.length();
		int[] n1 = new int[len + 2], n2 = new int[len + 2];
		for (int i = 0; i < len1; i++)
			n1[len1 - i - 1] = num.charAt(i) - '0';
		for (int i = 0; i < len2; i++)
			n2[len2 - i - 1] = num.charAt(len1 + i) - '0';
		int readpos = len1 + len2;
		int digitlen1 = len1, digitlen2 = len2, digitlen3 = 0;
		int[] n3 = new int[len + 2];
		while (readpos < len)
		{
			digitlen3 = Math.max(digitlen1, digitlen2);
			for (int i = 0; i < digitlen3; i++)
			{
				n3[i] = 0;
				if (i < digitlen1)
					n3[i] += n1[i];
				if (i < digitlen2)
					n3[i] += n2[i];
			}
			for (int i = 0; i < digitlen3 - 1; i++)
			{
				n3[i + 1] += n3[i] / 10;
				n3[i] %= 10;
			}
			while (n3[digitlen3 - 1] > 9)
			{
				n3[digitlen3] = n3[digitlen3 - 1] / 10;
				n3[digitlen3 - 1] %= 10;
				digitlen3++;
			}
			if (readpos + digitlen3 > num.length())
				return null;
			for (int i = 0; i < digitlen3; i++)
				if (num.charAt(readpos + i) - '0' != n3[digitlen3 - 1 - i])
					return null;
			try
			{
				ans.add(Integer.parseInt(num.substring(readpos, readpos + digitlen3)));
			} catch (NumberFormatException e)
			{
				return null;
			}
			readpos += digitlen3;
			for (int i = 0; i < digitlen2; i++)
				n1[i] = n2[i];
			digitlen1 = digitlen2;
			for (int i = 0; i < digitlen3; i++)
				n2[i] = n3[i];
			digitlen2 = digitlen3;

		}
		return ans;
	}
}

//845. Longest Mountain in Array
//Runtime: 4 ms, faster than 100.00% of Java online submissions for Longest Mountain in Array.
//Memory Usage: 39 MB, less than 94.39% of Java online submissions for Longest Mountain in Array.
class Solution845
{
	public int longestMountain(int[] A)
	{
		int max = 0;
		for (int i = 1; i < A.length - 1; i++)
		{
			if (A[i] > A[i - 1] && A[i] > A[i + 1])
			{
				int t = 3, j = i - 1;
				while (j > 0 && A[j - 1] < A[j])
				{
					j--;
					t++;
				}
				j = i + 1;
				while (j < A.length - 1 && A[j + 1] < A[j])
				{
					j++;
					t++;
				}
				if (t > max)
					max = t;
			}
		}
		return max;
	}
}

//846. Hand of Straights
//Runtime: 39 ms, faster than 71.85% of Java online submissions for Hand of Straights.
//Memory Usage: 39 MB, less than 87.81% of Java online submissions for Hand of Straights.
class Solution846
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
			if (num < o.num)
				return -1;
			if (num == o.num)
				return 0;
			return 1;
		}

	}

	public boolean isNStraightHand(int[] hand, int W)
	{
		HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>();
		for (int i = 0; i < hand.length; i++)
			freq.put(hand[i], freq.getOrDefault(hand[i], 0) + 1);
		PriorityQueue<Mp> heap = new PriorityQueue<Mp>();
		for (int num : freq.keySet())
			heap.offer(new Mp(num, freq.get(num)));
		List<Mp> arr = new ArrayList<Mp>(W);
		for (int i = 0; i < W; i++)
			arr.add(new Mp(0, 0));
		while (!heap.isEmpty())
		{
			for (int i = 0; i < W; i++)
			{
				if (heap.isEmpty())
					return false;
				arr.set(i, heap.poll());
			}
			int minNum = arr.get(0).num;
			for (int i = 0; i < W; i++)
			{
				Mp p = arr.get(i);
				if (p.num != minNum + i)
					return false;
				p.ct--;
				if (p.ct > 0)
					heap.offer(p);
			}
		}
		return true;
	}
}

//848. Shifting Letters
//Runtime: 4 ms, faster than 93.09% of Java online submissions for Shifting Letters.
//Memory Usage: 42.3 MB, less than 24.64% of Java online submissions for Shifting Letters.
class Solution848
{
	public String shiftingLetters(String S, int[] shifts)
	{
		int len = shifts.length;
		shifts[len - 1] %= 26;
		for (int i = len - 2; i >= 0; i--)
			shifts[i] = (shifts[i] % 26 + shifts[i + 1]) % 26;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++)
		{
			int c = S.charAt(i);
			c = ((c - 'a') + shifts[i]) % 26 + 'a';
			sb.append((char) c);
		}
		return sb.toString();
	}
}

//849. Maximize Distance to Closest Person
//Runtime: 2 ms, faster than 97.53% of Java online submissions for Maximize Distance to Closest Person.
//Memory Usage: 40.1 MB, less than 57.83% of Java online submissions for Maximize Distance to Closest Person.
class Solution849
{
	public int maxDistToClosest(int[] seats)
	{
		int len = seats.length;
		int[] a1 = new int[len], a2 = new int[len];
		a1[0] = seats[0] == 1 ? 0 : 1000000;
		for (int i = 1; i < len; i++)
			if (seats[i] == 1)
				a1[i] = 0;
			else
				a1[i] = a1[i - 1] + 1;
		a2[len - 1] = seats[len - 1] == 1 ? 0 : 1000000;
		for (int i = len - 2; i >= 0; i--)
			if (seats[i] == 1)
				a2[i] = 0;
			else
				a2[i] = a2[i + 1] + 1;
		int tarval = -1;
		for (int i = 0; i < len; i++)
			if (seats[i] == 0)
			{
				int t = a1[i] < a2[i] ? a1[i] : a2[i];
				if (t > tarval)
					tarval = t;
			}
		return tarval;
	}
}

public class LC841_850
{
	public static void test845()
	{
		System.out.println(new Solution845().longestMountain(new int[]
		{ 2, 1, 4, 7, 3, 2, 5 }));
	}

	public static void test849()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input849.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output849.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int[] seats = test.Test.parseIntArr(inLine);

				int ans = new Solution849().maxDistToClosest(seats);

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
		test849();
	}
}
