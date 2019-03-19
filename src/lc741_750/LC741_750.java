package lc741_750;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//741. Cherry Pickup
//https://leetcode.com/problems/cherry-pickup/discuss/109917/JAVA-DP-Solution-with-O(n5)-Time-and-O(n2)-Space
//Runtime: 25 ms, faster than 51.25% of Java online submissions for Cherry Pickup.
//Memory Usage: 39 MB, less than 97.06% of Java online submissions for Cherry Pickup.
class Solution741
{
	public int cherryPickup(int[][] grid)
	{
		int n = grid.length;
		int[][] pre = new int[n][n];
		for (int[] dp : pre)
			Arrays.fill(dp, -1);
		pre[0][0] = 0;
		for (int i = 0; i < n; ++i)
		{
			int[][] curr = new int[n][n];
			for (int[] dp : curr)
				Arrays.fill(dp, -1);
			for (int left = 0; left < n; ++left)
			{
				for (int right = left; right < n; ++right)
				{
					if (pre[left][right] > -1)
					{
						for (int l = left, count = pre[left][right]; 
								l < n && grid[i][l] > -1; 
								++l)
						{
							count += grid[i][l];
							if (l >= right)
								curr[l][l] = Math.max(curr[l][l], count);
							for (int r = Math.max(l + 1, right), count2 = count; 
									r < n && grid[i][r] > -1; 
									++r)
							{
								count2 += grid[i][r];
								curr[l][r] = Math.max(curr[l][r], count2);
							}
						}
					}
				}
			}
			pre = curr;
		}
		return Math.max(pre[n - 1][n - 1], 0);
	}
}

//745. Prefix and Suffix Search
//https://leetcode.com/problems/prefix-and-suffix-search/discuss/110044/Three-ways-to-solve-this-problem-in-Java
//After I glanced at his codes, I really appreciate them.
//The first three solutions below are given by them.
//Yet, there is another solution which is more in line with my original thoughts:
//https://leetcode.com/problems/prefix-and-suffix-search/discuss/234816/Yet-another-solution%3A-Prefix-Trie-with-each-node-contains-a-Suffix-Trie-()
//At last I will give my own implementation: the fourth solution.

//Runtime: 296 ms, faster than 68.30% of Java online submissions for Prefix and Suffix Search.
//Memory Usage: 85.9 MB, less than 33.87% of Java online submissions for Prefix and Suffix Search.
//WordFilter: Time = O(NL^2)
//f: Time = O(1)
//Space = O(NL^2)
//Note: N is the size of input array and L is the max length of input strings.
class WordFilter745_1
{
	HashMap<String, Integer> map = new HashMap<>();

	public WordFilter745_1(String[] words)
	{
		for (int w = 0; w < words.length; w++)
		{
			for (int i = 0; i <= 10 && i <= words[w].length(); i++)
			{
				for (int j = 0; j <= 10 && j <= words[w].length(); j++)
				{
					map.put(words[w].substring(0, i) + "#" + words[w].substring(words[w].length() - j), w);
				}
			}
		}
	}

	public int f(String prefix, String suffix)
	{
		return (map.containsKey(prefix + "#" + suffix)) ? map.get(prefix + "#" + suffix) : -1;
	}
}

//Runtime: 219 ms, faster than 98.73% of Java online submissions for Prefix and Suffix Search.
//Memory Usage: 70.5 MB, less than 85.48% of Java online submissions for Prefix and Suffix Search.

//WordFilter: Time = O(NL)
//f: Time = O(N)
//Space = O(NL)
class WordFilter745_2
{
	HashMap<String, List<Integer>> mapPrefix = new HashMap<>();
	HashMap<String, List<Integer>> mapSuffix = new HashMap<>();

	public WordFilter745_2(String[] words)
	{

		for (int w = 0; w < words.length; w++)
		{
			for (int i = 0; i <= 10 && i <= words[w].length(); i++)
			{
				String s = words[w].substring(0, i);
				if (!mapPrefix.containsKey(s))
					mapPrefix.put(s, new ArrayList<>());
				mapPrefix.get(s).add(w);
			}
			for (int i = 0; i <= 10 && i <= words[w].length(); i++)
			{
				String s = words[w].substring(words[w].length() - i);
				if (!mapSuffix.containsKey(s))
					mapSuffix.put(s, new ArrayList<>());
				mapSuffix.get(s).add(w);
			}
		}
	}

	public int f(String prefix, String suffix)
	{

		if (!mapPrefix.containsKey(prefix) || !mapSuffix.containsKey(suffix))
			return -1;
		List<Integer> p = mapPrefix.get(prefix);
		List<Integer> s = mapSuffix.get(suffix);
		int i = p.size() - 1, j = s.size() - 1;
		while (i >= 0 && j >= 0)
		{
			if (p.get(i) < s.get(j))
				j--;
			else if (p.get(i) > s.get(j))
				i--;
			else
				return p.get(i);
		}
		return -1;
	}
}

//Runtime: 1795 ms, faster than 13.40% of Java online submissions for Prefix and Suffix Search.
//Memory Usage: 70 MB, less than 88.71% of Java online submissions for Prefix and Suffix Search.

//WordFilter: Time = O(1)
//f: Time = O(NL)
//Space = O(1)
class WordFilter_3
{
	String[] input;

	public WordFilter_3(String[] words)
	{
		input = words;
	}

	public int f(String prefix, String suffix)
	{
		for (int i = input.length - 1; i >= 0; i--)
		{
			if (input[i].startsWith(prefix) && input[i].endsWith(suffix))
				return i;
		}
		return -1;
	}
}

//Runtime: 220 ms, faster than 98.01% of Java online submissions for Prefix and Suffix Search.
//Memory Usage: 113.1 MB, less than 6.45% of Java online submissions for Prefix and Suffix Search.
class WordFilter745_4
{
	private WordFilter745_4[] PrefixSon = new WordFilter745_4[26];
	private WordFilter745_4[] SuffixSon = new WordFilter745_4[26];
	private int MaxWeight = -1;
	private final static int MaxPrefixLength = 10, MaxSuffixLength = 10;

	private WordFilter745_4()
	{
	}

	private void insertSuffix(String s, int w)
	{
		WordFilter745_4 cur = this;
		if (w > cur.MaxWeight)
			cur.MaxWeight = w;
		for (int suffixlen = 1; suffixlen <= s.length() && suffixlen <= MaxSuffixLength; suffixlen++)
		{
			int c = s.charAt(s.length() - suffixlen) - 'a';
			if (cur.SuffixSon[c] == null)
				cur.SuffixSon[c] = new WordFilter745_4();
			cur = cur.SuffixSon[c];
			if (w > cur.MaxWeight)
				cur.MaxWeight = w;
		}
	}

	public WordFilter745_4(String[] words)
	{
		WordFilter745_4 cur;
		for (int i = 0; i < words.length; i++)
		{
			cur = this;
			if (i > cur.MaxWeight)
				cur.MaxWeight = i;
			cur.insertSuffix(words[i], i);
			for (int prefixlen = 1; prefixlen <= words[i].length() && prefixlen <= MaxPrefixLength; prefixlen++)
			{
				int c = words[i].charAt(prefixlen - 1) - 'a';
				if (cur.PrefixSon[c] == null)
					cur.PrefixSon[c] = new WordFilter745_4();
				cur = cur.PrefixSon[c];
				if (i > cur.MaxWeight)
					cur.MaxWeight = i;
				cur.insertSuffix(words[i], i);
			}
		}
	}

	public int f(String prefix, String suffix)
	{
		WordFilter745_4 cur = this;
		for (int i = 0; i < prefix.length(); i++)
		{
			int c = prefix.charAt(i) - 'a';
			if (cur.PrefixSon[c] == null)
				return -1;
			cur = cur.PrefixSon[c];
		}
		for (int j = suffix.length() - 1; j >= 0; j--)
		{
			int c = suffix.charAt(j) - 'a';
			if (cur.SuffixSon[c] == null)
				return -1;
			cur = cur.SuffixSon[c];
		}
		return cur.MaxWeight;
	}
}

public class LC741_750
{
	public static void main(String[] ars)
	{

	}
}
