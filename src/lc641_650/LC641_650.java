package lc641_650;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Comparator;
import java.util.LinkedList;

import lc201_210.StrTrie;

//641. Design Circular Deque
//Runtime: 55 ms, faster than 68.98% of Java online submissions for Design Circular Deque.
//Memory Usage: 40.3 MB, less than 12.20% of Java online submissions for Design Circular Deque.
class MyCircularDeque
{

	private int[] q;
	private int f = 0, r = 0, num = 0, cap;

	private int pred(int x)
	{
		if (x == 0)
			return q.length - 1;
		else
			return x - 1;
	}

	private int succ(int x)
	{
		return (x + 1) % q.length;
	}

	/** Initialize your data structure here. Set the size of the deque to be k. */
	public MyCircularDeque(int k)
	{
		q = new int[k + 2];
		cap = k;
	}

	/**
	 * Adds an item at the front of Deque. Return true if the operation is
	 * successful.
	 */
	public boolean insertFront(int value)
	{
		if (num == cap)
			return false;
		f = pred(f);
		q[f] = value;
		num++;
		return true;

	}

	/**
	 * Adds an item at the rear of Deque. Return true if the operation is
	 * successful.
	 */
	public boolean insertLast(int value)
	{
		if (num == cap)
			return false;
		q[r] = value;
		r = succ(r);
		num++;
		return true;
	}

	/**
	 * Deletes an item from the front of Deque. Return true if the operation is
	 * successful.
	 */
	public boolean deleteFront()
	{
		if (num == 0)
			return false;
		f = succ(f);
		num--;
		return true;
	}

	/**
	 * Deletes an item from the rear of Deque. Return true if the operation is
	 * successful.
	 */
	public boolean deleteLast()
	{
		if (num == 0)
			return false;
		r = pred(r);
		num--;
		return true;
	}

	/** Get the front item from the deque. */
	public int getFront()
	{
		if (num > 0)
			return q[f];
		else
			return -1;
	}

	/** Get the last item from the deque. */
	public int getRear()
	{
		if (num > 0)
			return q[pred(r)];
		else
			return -1;
	}

	/** Checks whether the circular deque is empty or not. */
	public boolean isEmpty()
	{
		return num == 0;
	}

	/** Checks whether the circular deque is full or not. */
	public boolean isFull()
	{
		return num == cap;
	}
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k); boolean param_1 =
 * obj.insertFront(value); boolean param_2 = obj.insertLast(value); boolean
 * param_3 = obj.deleteFront(); boolean param_4 = obj.deleteLast(); int param_5
 * = obj.getFront(); int param_6 = obj.getRear(); boolean param_7 =
 * obj.isEmpty(); boolean param_8 = obj.isFull();
 */

//Runtime: 4 ms, faster than 99.57% of Java online submissions for Set Mismatch.
//Memory Usage: 41.4 MB, less than 23.13% of Java online submissions for Set Mismatch.
class Solution645
{
	public int[] findErrorNums(int[] nums)
	{
		int l = nums.length;
		int[] b = new int[l + 1];
		for (int i = 0; i < l; i++)
			b[nums[i]]++;
		int d = 0, m = 0;
		for (int i = 1; i <= l; i++)
			if (b[i] == 0)
				m = i;
			else if (b[i] == 2)
				d = i;
		return new int[]
		{ d, m };

	}
}

//646. Maximum Length of Pair Chain
//Runtime: 45 ms, faster than 87.27% of Java online submissions for Maximum Length of Pair Chain.
//Memory Usage: 50.9 MB, less than 44.83% of Java online submissions for Maximum Length of Pair Chain.
class Solution646
{
	public int findLongestChain(int[][] pairs)
	{
		Arrays.parallelSort(pairs, new Comparator<int[]>()
		{
			public int compare(int[] o1, int[] o2)
			{
				if (o1[0] < o2[0])
					return -1;
				if (o1[0] == o2[0])
					return 0;
				return 1;
			}
		});
		int len = pairs.length;
		int[] maxLen = new int[len];
		if (len == 0)
			return 0;
		for (int i = len - 1; i >= 0; i--)
		{
			maxLen[i] = 1;
			for (int j = i + 1; j < len; j++)
				if (pairs[j][0] > pairs[i][1] && maxLen[j] + 1 > maxLen[i])
					maxLen[i] = maxLen[j] + 1;
		}
		int ans = 0;
		for (int i = 0; i < len; i++)
			if (maxLen[i] > ans)
				ans = maxLen[i];
		return ans;
	}
}

//648. Replace Words
//Runtime: 205 ms, faster than 20.19% of Java online submissions for Replace Words.
//Memory Usage: 52.9 MB, less than 55.93% of Java online submissions for Replace Words.
class Solution648
{
	public String replaceWords(List<String> dict, String sentence)
	{
		int len = sentence.length();
		int i = 0;
		String ans = "";
		while (i < len)
		{
			while (i < len && sentence.charAt(i) == ' ')
			{
				i++;
				ans += " ";
			}
			if (i == len)
				break;
			int j = i + 1;
			while (j < len && sentence.charAt(j) != ' ')
				j++;
			String ts = sentence.substring(i, j);
			int found = -1;
			for (int k = 0; k < dict.size(); k++)
				if (ts.indexOf(dict.get(k)) == 0 && (found == -1 || dict.get(k).length() > dict.get(k).length()))
				{
					found = k;
				}
			if (found == -1)
				ans += ts;
			else
				ans += dict.get(found);
			i = j;
		}
		return ans;
	}
}

//With a Char Trie
//Runtime: 22 ms, faster than 65.30% of Java online submissions for Replace Words.
//Memory Usage: 110.7 MB, less than 5.08% of Java online submissions for Replace Words.
class Solution648_2
{
	public String replaceWords(List<String> dict, String sentence)
	{
		int len = sentence.length();
		StrTrie trie = new StrTrie();
		for (int i = 0; i < dict.size(); i++)
			trie.insert(dict.get(i));
		int i = 0;
		StringBuilder ans = new StringBuilder();
		while (i < len)
		{
			while (i < len && sentence.charAt(i) == ' ')
			{
				i++;
				ans.append(' ');
			}
			if (i == len)
				break;
			int j = i + 1;
			while (j < len && sentence.charAt(j) != ' ')
				j++;
			String ts = sentence.substring(i, j);
			String foundprefix = trie.findShortestPrefix(ts);
			;
			if (foundprefix.length() == 0)
				ans.append(ts);
			else
				ans.append(foundprefix);
			i = j;
		}
		return ans.toString();
	}
}

//Runtime: 206 ms, faster than 19.95% of Java online submissions for Replace Words.
//Memory Usage: 52.3 MB, less than 62.71% of Java online submissions for Replace Words.
class Solution648_3
{
	public String replaceWords(List<String> dict, String sentence)
	{
		Collections.sort(dict, new Comparator<String>()
		{
			@Override
			public int compare(String o1, String o2)
			{
				return o1.length() - o2.length();
			}
		});

		int len = sentence.length();
		int i = 0;
		String ans = "";
		while (i < len)
		{
			while (i < len && sentence.charAt(i) == ' ')
			{
				i++;
				ans += " ";
			}
			if (i == len)
				break;
			int j = i + 1;
			while (j < len && sentence.charAt(j) != ' ')
				j++;
			String ts = sentence.substring(i, j);
			int found = -1;
			for (int k = 0; k < dict.size(); k++)
				if (ts.indexOf(dict.get(k)) == 0)
				{
					found = k;
					break;
				}
			if (found == -1)
				ans += ts;
			else
				ans += dict.get(found);
			i = j;
		}
		return ans;
	}
}

//649. Dota2 Senate
//Runtime: 3 ms, faster than 100.00% of Java online submissions for Dota2 Senate.
//Memory Usage: 37.4 MB, less than 100.00% of Java online submissions for Dota2 Senate.
//https://leetcode.com/problems/dota2-senate/discuss/244922/Java-beats-100-no-need-for-any-algorithm....
class Solution649
{
	public String predictPartyVictory(String senate)
	{
		int[] inQueue = new int[senate.length()];
		int[] outQueue = new int[inQueue.length];
		int[] live = new int[2];
		for (int i = 0; i < inQueue.length; i++)
			inQueue[i] = senate.charAt(i) == 'R' ? 0 : 1;
		int wp = 0, rlen = inQueue.length;
		int[] banRight = new int[2];
		while (rlen > 0)
		{
			for (int i = 0; i < rlen; i++)
			{
				int type = inQueue[i];
				if (banRight[1 - type] > 0)
				{
					banRight[1 - type]--;
				} else
				{
					live[type]++;
					banRight[type]++;
					outQueue[wp++] = type;
				}
			}
			if (live[0] == 0)
				return "Dire";
			else if (live[1] == 0)
				return "Radiant";
			live[0] = 0;
			live[1] = 0;
			int[] tmp = inQueue;
			inQueue = outQueue;
			outQueue = tmp;
			rlen = wp;
			wp = 0;
		}
		return "R";
	}
}

//650. 2 Keys Keyboard
//Runtime: 3 ms, faster than 98.22% of Java online submissions for 2 Keys Keyboard.
//Memory Usage: 33.7 MB, less than 100.00% of Java online submissions for 2 Keys Keyboard.
class Solution650
{
	public int minSteps(int n)
	{
		if (n == 1)
			return 0;
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[]
		{ 1, 1, 1 });
		// used step, copied length, text total length
		while (!q.isEmpty())
		{
			int[] state = q.remove();
			if (state[2] == n)
				return state[0];
			if (state[1] != state[2] && (n - state[2]) % state[2] == 0)
				q.add(new int[]
				{ state[0] + 1, state[2], state[2] });
			if (state[2] + state[1] <= n)
				q.add(new int[]
				{ state[0] + 1, state[1], state[2] + state[1] });
		}
		return -1;
	}
}

public class LC641_650
{
	public static void test649()
	{

		try
		{
			File inFile = new File("input" + File.separator + "input649.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output649.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				inLine = inLine.substring(1, inLine.length() - 1);

				Solution649 s = new Solution649();

				String ans = s.predictPartyVictory(inLine);
				System.out.println(ans);

				bfw.write(ans);
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
		test649();
	}
}
