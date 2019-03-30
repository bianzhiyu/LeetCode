package lc671_680;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//672. Bulb Switcher II
//Runtime: 4 ms, faster than 28.96% of Java online submissions for Bulb Switcher II.
//Memory Usage: 33.5 MB, less than 100.00% of Java online submissions for Bulb Switcher II.
class Solution672
{
	public int flipLights(int n, int m)
	{
		boolean[] done = new boolean[16];
		List<boolean[]> l = new ArrayList<boolean[]>();
		if (m > 4)
			m = 4 + (m - 4) % 2;
		for (int c1 = 0; c1 <= m; c1++)
			for (int c2 = 0; c2 <= m - c1; c2++)
				for (int c3 = 0; c3 <= m - c1 - c2; c3++)
				{
					int c4 = m - c1 - c2 - c3;
					int d1 = c1 % 2, d2 = c2 % 2, d3 = c3 % 2, d4 = c4 % 2;
					int state = d1 * 8 + d2 * 4 + d3 * 2 + d4;
					if (!done[state])
					{
						boolean[] b = new boolean[n];
						if (d1 == 1)
							for (int i = 0; i < n; i++)
								b[i] = !b[i];
						if (d2 == 1)
							for (int i = 0; i < n; i += 2)
								b[i] = !b[i];
						if (d3 == 1)
							for (int i = 1; i < n; i += 2)
								b[i] = !b[i];
						if (d4 == 1)
							for (int i = 0; i < n; i += 3)
								b[i] = !b[i];
						boolean fd = false;
						for (boolean[] b2 : l)
						{
							boolean same = true;
							for (int i = 0; i < n; i++)
								if (b[i] != b2[i])
								{
									same = false;
									break;
								}
							if (same)
							{
								fd = true;
								break;
							}
						}
						if (!fd)
							l.add(b);
					}
				}
		return l.size();
	}
}

//674. Longest Continuous Increasing Subsequence
//Runtime: 2 ms, faster than 100.00% of Java online submissions for Longest Continuous Increasing Subsequence.
//Memory Usage: 40.1 MB, less than 70.67% of Java online submissions for Longest Continuous Increasing Subsequence.
class Solution673
{
	public int findLengthOfLCIS(int[] nums)
	{
		int i = 0, max = 0;
		while (i < nums.length)
		{
			int j = i + 1;
			while (j < nums.length && nums[j] > nums[j - 1])
				j++;
			if (j - i > max)
				max = j - i;
			i = j;
		}
		return max;
	}
}

//676. Implement Magic Dictionary
//Runtime: 48 ms, faster than 99.54% of Java online submissions for Implement Magic Dictionary.
//Memory Usage: 36.8 MB, less than 95.77% of Java online submissions for Implement Magic Dictionary.
class MagicDictionary
{
	private static class TrieNode
	{
		boolean isWord = false;
		TrieNode[] son = new TrieNode[26];
	}

	TrieNode root;

	/** Initialize your data structure here. */
	public MagicDictionary()
	{
	}

	private void addWord(TrieNode rt, String str)
	{
		for (int pos = 0; pos < str.length(); pos++)
		{
			int c = str.charAt(pos) - 'a';
			if (rt.son[c] == null)
				rt.son[c] = new TrieNode();
			rt = rt.son[c];
		}
		rt.isWord = true;
	}

	/** Build a dictionary through a list of words */
	public void buildDict(String[] dict)
	{
		root = new TrieNode();
		for (int i = 0; i < dict.length; i++)
			addWord(root, dict[i]);
	}

	private boolean dfs(TrieNode rt, String str, int pos, boolean modified)
	{
		if (pos == str.length())
			return modified && rt.isWord;
		int c = str.charAt(pos) - 'a';
		if (rt.son[c] != null)
		{
			if (dfs(rt.son[c], str, pos + 1, modified))
				return true;
		}
		if (modified)
			return false;
		for (int i = 0; i < 26; i++)
			if (i != c && rt.son[i] != null && dfs(rt.son[i], str, pos + 1, true))
				return true;
		return false;
	}

	/**
	 * Returns if there is any word in the trie that equals to the given word after
	 * modifying exactly one character
	 */
	public boolean search(String word)
	{
		return dfs(root, word, 0, false);
	}
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary(); obj.buildDict(dict); boolean
 * param_2 = obj.search(word);
 */

//677. Map Sum Pairs
//Runtime: 48 ms, faster than 99.82% of Java online submissions for Map Sum Pairs.
//Memory Usage: 37 MB, less than 32.81% of Java online submissions for Map Sum Pairs.
class MapSum
{
	private static class TrieNode
	{
		boolean isKey = false;
		int value = 0;
		TrieNode[] son = new TrieNode[26];
	}

	TrieNode root;

	private int index(char c)
	{
		return c - 'a';
	}

	/** Initialize your data structure here. */
	public MapSum()
	{
		root = new TrieNode();
	}

	public void insert(String key, int val)
	{
		TrieNode rt = root;
		for (char c : key.toCharArray())
		{
			int id = index(c);
			if (rt.son[id] == null)
				rt.son[id] = new TrieNode();
			rt = rt.son[id];
		}
		rt.isKey = true;
		rt.value = val;
	}

	private int dfs(TrieNode rt)
	{
		if (rt == null)
			return 0;
		int sum = 0;
		if (rt.isKey)
			sum += rt.value;
		for (TrieNode s : rt.son)
			sum += dfs(s);
		return sum;
	}

	public int sum(String prefix)
	{
		TrieNode rt = root;
		for (char c : prefix.toCharArray())
		{
			int id = index(c);
			if (rt.son[id] == null)
				return 0;
			rt = rt.son[id];
		}
		return dfs(rt);
	}
}

/**
 * Your MapSum object will be instantiated and called as such: MapSum obj = new
 * MapSum(); obj.insert(key,val); int param_2 = obj.sum(prefix);
 */

//678. Valid Parenthesis String
//Runtime: 205 ms, faster than 15.44% of Java online submissions for Valid Parenthesis String.
//Memory Usage: 35.5 MB, less than 94.57% of Java online submissions for Valid Parenthesis String.
class Solution678
{
	private boolean dfs(char[] s, int pos, int unMatchedLeft)
	{
		if (pos == s.length)
			return unMatchedLeft == 0;
		if (s[pos] == '(')
			return dfs(s, pos + 1, unMatchedLeft + 1);
		if (s[pos] == ')')
		{
			if (unMatchedLeft == 0)
				return false;
			return dfs(s, pos + 1, unMatchedLeft - 1);
		}
		// *
		if (dfs(s, pos + 1, unMatchedLeft))
			return true;
		if (dfs(s, pos + 1, unMatchedLeft + 1))
			return true;
		if (unMatchedLeft > 0 && dfs(s, pos + 1, unMatchedLeft - 1))
			return true;
		return false;
	}

	public boolean checkValidString(String s)
	{
		return dfs(s.toCharArray(), 0, 0);
	}
}

//Runtime: 2 ms, faster than 96.65% of Java online submissions for Valid Parenthesis String.
//Memory Usage: 40.3 MB, less than 5.98% of Java online submissions for Valid Parenthesis String.
class Solution678_2
{
	private HashMap<Integer, Boolean> hm = new HashMap<Integer, Boolean>();

	private boolean dfs(char[] s, int pos, int unMatchedLeft)
	{
		if (pos == s.length)
			return unMatchedLeft == 0;
		int state = pos * 101 + unMatchedLeft;
		if (hm.containsKey(state))
			return hm.get(state);
		if (s[pos] == '(')
		{
			hm.put(state, dfs(s, pos + 1, unMatchedLeft + 1));
			return hm.get(state);
		}
		if (s[pos] == ')')
		{
			if (unMatchedLeft == 0)
			{
				hm.put(state, false);
				return false;
			}
			hm.put(state, dfs(s, pos + 1, unMatchedLeft - 1));
			return hm.get(state);
		}
		// *
		if (dfs(s, pos + 1, unMatchedLeft))
		{
			hm.put(state, true);
			return true;
		}
		if (dfs(s, pos + 1, unMatchedLeft + 1))
		{
			hm.put(state, true);
			return true;
		}
		if (unMatchedLeft > 0 && dfs(s, pos + 1, unMatchedLeft - 1))
		{
			hm.put(state, true);
			return true;
		}
		hm.put(state, false);
		return false;
	}

	public boolean checkValidString(String s)
	{
		return dfs(s.toCharArray(), 0, 0);
	}
}

//679. 24 Game
//Runtime: 11 ms, faster than 78.82% of Java online submissions for 24 Game.
//Memory Usage: 37.8 MB, less than 79.31% of Java online submissions for 24 Game.
//uncomment all the comments to see how to get 24
class Solution679
{
	private void copy(double[] to, int st, int en, double[] from, int shift)
	{
		for (int i = st; i <= en; i++)
			to[i] = from[i + shift];
	}

	private boolean judgePoint24(double[] nums)
	{
		if (nums.length == 1)
		{
			if (Math.abs(nums[0] - 24) < 1e-10)
			{
//				test.Test.dispArr(nums);
				return true;
			}
		}
		double[] t = new double[nums.length - 1];
		for (int i = 0; i < nums.length - 1; i++)
		{
			copy(t, i + 1, t.length - 1, nums, 1);
			t[i] = nums[i] + nums[i + 1];
			if (judgePoint24(t))
			{
//				test.Test.dispArr(nums);
				return true;
			}
			t[i] = nums[i] - nums[i + 1];
			if (judgePoint24(t))
			{
//				test.Test.dispArr(nums);
				return true;
			}
			t[i] = nums[i] * nums[i + 1];
			if (judgePoint24(t))
			{
//				test.Test.dispArr(nums);
				return true;
			}
			if (nums[i + 1] != 0)
			{
				t[i] = nums[i] / nums[i + 1];
				if (judgePoint24(t))
				{
//					test.Test.dispArr(nums);
					return true;
				}
			}
			t[i] = nums[i];
		}
		return false;
	}

	private boolean permAndJudge(double[] n, boolean[] used, int st, double[] newn)
	{
		if (st == n.length)
		{
			boolean succ = judgePoint24(newn);
			if (succ)
			{
//				test.Test.dispArr(newn);
			}
			return succ;
		}
		for (int i = 0; i < n.length; i++)
			if (!used[i])
			{
				used[i] = true;
				newn[st] = n[i];
				if (permAndJudge(n, used, st + 1, newn))
					return true;
				used[i] = false;
			}
		return false;
	}

	public boolean judgePoint24(int[] nums)
	{
		double[] n = new double[nums.length];
		for (int i = 0; i < n.length; i++)
			n[i] = nums[i];
		return permAndJudge(n, new boolean[n.length], 0, new double[n.length]);
	}
}

public class LC671_680
{
	public static void test678()
	{
		String in = "(";
		Solution678_2 s = new Solution678_2();
		System.out.println(s.checkValidString(in));
	}

	public static void test679()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input679.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output679.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int[] nums = test.Test.parseIntArr(inLine);
				Solution679 s = new Solution679();
				boolean ans = s.judgePoint24(nums);
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
		test679();
		;
	}
}
