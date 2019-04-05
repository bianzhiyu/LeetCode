package lc211_220;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

//211. Add and Search Word - Data structure design
//Runtime: 115 ms, faster than 54.74% of Java online submissions for Add and Search Word - Data structure design.
//Memory Usage: 118.5 MB, less than 5.09% of Java online submissions for Add and Search Word - Data structure design.
class WordDictionary
{
	private boolean isEnd = false;
	private WordDictionary[] son = new WordDictionary[256];

	/** Initialize your data structure here. */
	public WordDictionary()
	{
	}

	private void pinsert(char[] str, int p)
	{
		if (p == str.length)
			isEnd = true;
		else
		{
			if (son[str[p]] == null)
				son[str[p]] = new WordDictionary();
			son[str[p]].pinsert(str, p + 1);
		}
	}

	/** Adds a word into the data structure. */
	public void addWord(String word)
	{
		pinsert(word.toCharArray(), 0);
	}

	private boolean psearch(char[] str, int p)
	{
		if (p == str.length)
			return isEnd;
		if (str[p] == '.')
		{
			for (int i = 0; i < son.length; i++)
				if (son[i] != null && son[i].psearch(str, p + 1))
					return true;
			return false;
		}
		return son[str[p]] != null && son[str[p]].psearch(str, p + 1);
	}

	/**
	 * Returns if the word is in the data structure. A word could contain the dot
	 * character '.' to represent any one letter.
	 */
	public boolean search(String word)
	{
		return psearch(word.toCharArray(), 0);
	}
}

//212. Word Search II
//Runtime: 718 ms, faster than 9.69% of Java online submissions for Word Search II.
//Memory Usage: 42 MB, less than 100.00% of Java online submissions for Word Search II.
class Solution212
{
	final static int[][] di =
	{
			{ 1, 0 },
			{ 0, 1 },
			{ -1, 0 },
			{ 0, -1 } };
	boolean used[][];

	boolean sch(char[][] b, char[] s, int x, int y, int sp)
	{
		if (sp == s.length)
			return true;
		for (int i = 0; i < 4; i++)
		{
			int nx = x + di[i][0], ny = y + di[i][1];
			if (nx >= 0 && nx < b.length && ny >= 0 && ny < b[0].length && !used[nx][ny] && b[nx][ny] == s[sp])
			{
				used[nx][ny] = true;
				if (sch(b, s, nx, ny, sp + 1))
					return true;
				used[nx][ny] = false;
			}
		}
		return false;
	}

	boolean found(char[][] b, char[] s)
	{
		for (int i = 0; i < used.length; i++)
			for (int j = 0; j < used[0].length; j++)
				used[i][j] = false;
		for (int i = 0; i < b.length; i++)
			for (int j = 0; j < b[0].length; j++)
				if (b[i][j] == s[0])
				{
					used[i][j] = true;
					if (sch(b, s, i, j, 1))
						return true;
					used[i][j] = false;
				}
		return false;
	}

	public List<String> findWords(char[][] board, String[] words)
	{
		List<String> fd = new ArrayList<String>();
		used = new boolean[board.length][board[0].length];
		for (int i = 0; i < words.length; i++)
			if (!fd.contains(words[i]) && found(board, words[i].toCharArray()))
				fd.add(words[i]);

		return fd;
	}
}

//212
//Using Trie Tree: prefix tree, a data structure
//Runtime: 20 ms, faster than 66.10% of Java online submissions for Word Search II.
//Memory Usage: 48.3 MB, less than 100.00% of Java online submissions for Word Search II.
class Solution212_2
{
	private static class Trie
	{
		boolean isKey;
		@SuppressWarnings("unused")
		char Val;
		Trie[] next;

		Trie()
		{
			isKey = false;
			next = new Trie[26];
		}

		static void insert(Trie prend, int strsp, char[] s)
		{
			if (s.length == 0 || strsp >= s.length)
				return;
			if (strsp == s.length - 1)
			{
				if (prend.next[s[strsp] - 'a'] == null)
				{
					prend.next[s[strsp] - 'a'] = new Trie();
					prend.next[s[strsp] - 'a'].isKey = true;
					prend.next[s[strsp] - 'a'].Val = s[strsp];
				} else
				{
					prend.next[s[strsp] - 'a'].isKey = true;
				}
				return;
			}
			if (prend.next[s[strsp] - 'a'] == null)
			{
				prend.next[s[strsp] - 'a'] = new Trie();
				prend.next[s[strsp] - 'a'].Val = s[strsp];
				insert(prend.next[s[strsp] - 'a'], strsp + 1, s);
			} else
			{
				insert(prend.next[s[strsp] - 'a'], strsp + 1, s);
			}
		}
	}

	void sch(Trie prert, int x, int y, char[][] b, String strsta, List<String> res)
	{
		// if one can step into x,y
		if (x < 0 || y < 0 || x >= b.length || y >= b[0].length || b[x][y] == '$' || prert.next[b[x][y] - 'a'] == null)
			return;
		// step in
		char c = b[x][y];
		String newstr = strsta + c;
		if (prert.next[c - 'a'].isKey && !res.contains(newstr))
			res.add(newstr);
		// search next step
		b[x][y] = '$';// mark: Can't self intersect.
		sch(prert.next[c - 'a'], x + 1, y, b, newstr, res);
		sch(prert.next[c - 'a'], x, y + 1, b, newstr, res);
		sch(prert.next[c - 'a'], x - 1, y, b, newstr, res);
		sch(prert.next[c - 'a'], x, y - 1, b, newstr, res);
		b[x][y] = c;
	}

	public List<String> findWords(char[][] board, String[] words)
	{
		List<String> res = new ArrayList<>();
		Trie trt = new Trie();
		for (int i = 0; i < words.length; i++)
			if (words[i].length() > 0)
				Trie.insert(trt, 0, words[i].toCharArray());
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++)
				sch(trt, i, j, board, "", res);
		return res;
	}

}

//214. Shortest Palindrome
//Runtime: 233 ms, faster than 33.41% of Java online submissions for Shortest Palindrome.
//Memory Usage: 34.7 MB, less than 95.60% of Java online submissions for Shortest Palindrome.
class Solution214
{
	public String shortestPalindrome(String s)
	{
		int len = s.length(), addlen = 0;
		while (true)
		{
			int i = 0, j = len - 1 - addlen;
			boolean pld = true;
			while (i < j)
			{
				if (s.charAt(i) != s.charAt(j))
				{
					pld = false;
					break;
				}
				i++;
				j--;
			}
			if (pld)
			{
				return new StringBuilder(s.substring(len - addlen)).reverse().toString() + s;
			}
			addlen++;
		}
	}
}

//Runtime: 1 ms, faster than 100.00% of Java online submissions for Shortest Palindrome.
//Memory Usage: 34.4 MB, less than 100.00% of Java online submissions for Shortest Palindrome.
class Solution214_2
{
	public String shortestPalindrome(String s)
	{

		int i = 0, j = s.length() - 1;

		for (; j >= 0; j--)
		{
			if (s.charAt(i) == s.charAt(j))
			{
				i++;
			}
		}

		if (i == s.length())
			return s;

		return new StringBuilder(s.substring(i)).reverse().toString() + shortestPalindrome(s.substring(0, i))
				+ s.substring(i);
	}

}

//215. Kth Largest Element in an Array
//Runtime: 4 ms, faster than 83.21% of Java online submissions for Kth Largest Element in an Array.
//Memory Usage: 37.9 MB, less than 92.53% of Java online submissions for Kth Largest Element in an Array.
class Solution215
{
	public int findKthLargest(int[] nums, int k)
	{
		Arrays.parallelSort(nums);
		return nums[nums.length - k];
	}
}

//216. Combination Sum III
//Misunderstand of the question.
class Solution216
{
	List<List<Integer>> ans = new ArrayList<List<Integer>>();
	int[] stack;

	private void dfs(int sp, int start, int n)
	{
		if (sp == stack.length)
		{
			if (n == 0)
			{
				List<Integer> tmp = new ArrayList<Integer>();
				for (int i = 0; i < stack.length; i++)
					tmp.add(stack[i]);
				ans.add(tmp);
			}
			return;
		}
		for (int i = start; (2 * i + stack.length - 1 - sp) * (stack.length - sp) / 2 <= n; i++)
		{
			boolean contain0 = false;
			int x = i;
			while (x > 0)
			{
				if (x % 10 == 0)
				{
					contain0 = true;
					break;
				}
				x /= 10;
			}
			if (!contain0)
			{
				stack[sp] = i;
				dfs(sp + 1, i + 1, n - i);
			}

		}
	}

	public List<List<Integer>> combinationSum3(int k, int n)
	{
		stack = new int[k];
		dfs(0, 1, n);
		return ans;
	}
}

//Runtime: 0 ms, faster than 100.00% of Java online submissions for Combination Sum III.
//Memory Usage: 32.1 MB, less than 100.00% of Java online submissions for Combination Sum III.
class Solution216_2
{
	List<List<Integer>> ans = new ArrayList<List<Integer>>();
	int[] stack;

	private void dfs(int sp, int start, int n)
	{
		if (sp == stack.length)
		{
			if (n == 0)
			{
				List<Integer> tmp = new ArrayList<Integer>();
				for (int i = 0; i < stack.length; i++)
					tmp.add(stack[i]);
				ans.add(tmp);
			}
			return;
		}
		if (n <= 0)
			return;
		for (int i = start; i <= 9; i++)
		{
			stack[sp] = i;
			dfs(sp + 1, i + 1, n - i);
		}
	}

	public List<List<Integer>> combinationSum3(int k, int n)
	{
		stack = new int[k];
		dfs(0, 1, n);
		return ans;
	}
}

//217. Contains Duplicate
//Runtime: 9 ms, 62.60%
class Solution217
{
	public boolean containsDuplicate(int[] nums)
	{
		Set<Integer> a = new HashSet<Integer>();
		for (int i = 0; i < nums.length; i++)
			if (a.contains(nums[i]))
				return true;
			else
				a.add(nums[i]);
		return false;
	}
}

//218. The Skyline Problem
//https://leetcode.com/problems/the-skyline-problem/discuss/61358/Java-AC-solution-with-comments
//I totally don't know how to do this.
//Runtime: 16 ms, faster than 88.74% of Java online submissions for The Skyline Problem.
//Memory Usage: 44.7 MB, less than 75.96% of Java online submissions for The Skyline Problem.
class Solution218
{
	private static class Wall implements Comparable<Wall>
	{
		private int x, y, type, ind;

		// type=0:left, type=1:right.
		public Wall(int _x, int _y, int _t, int _i)
		{
			x = _x;
			y = _y;
			type = _t;
			ind = _i;
		}

		public int compareTo(Wall o)
		{
			if (x != o.x)
				return x < o.x ? -1 : 1;
			if (y != o.y)
				return y < o.y ? 1 : -1;
			if (type != o.type)
				return type == 0 ? -1 : 1;
			return ind - o.ind;
		}
	}

	public List<int[]> getSkyline(int[][] buildings)
	{
		List<int[]> ans = new ArrayList<int[]>();
		int i = 0;
		Wall[] arr = new Wall[buildings.length * 2];
		for (int[] b : buildings)
		{
			arr[i << 1] = new Wall(b[0], b[2], 0, i);
			arr[1 + (i << 1)] = new Wall(b[1], b[2], 1, i);
			i++;
		}
		Arrays.parallelSort(arr);

		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		for (Wall w : arr)
		{
			if (w.type == 0)
			{
				pq.offer(w.y);
				if (w.y == pq.peek())
				{
					if (ans.isEmpty())
						ans.add(new int[]
						{ w.x, w.y });
					else if (w.y != ans.get(ans.size() - 1)[1])
						ans.add(new int[]
						{ w.x, w.y });
				}
			} else
			{
				pq.remove(w.y);
				if (pq.isEmpty())
				{
					while (ans.get(ans.size() - 1)[0] == w.x)
						ans.remove(ans.size() - 1);
					ans.add(new int[]
					{ w.x, 0 });
				} else if (pq.peek() != ans.get(ans.size() - 1)[1])
				{
					ans.add(new int[]
					{ w.x, pq.peek() });
				}
			}
		}
		return ans;
	}
}

//219. Contains Duplicate II
//Runtime: 17 ms, faster than 35.00% of Java online submissions for Contains Duplicate II.
//Memory Usage: 44.1 MB, less than 29.34% of Java online submissions for Contains Duplicate II.
class Solution219
{
	public boolean containsNearbyDuplicate(int[] nums, int k)
	{
		HashMap<Integer, List<Integer>> hm = new HashMap<Integer, List<Integer>>();
		for (int i = 0; i < nums.length; i++)
			if (hm.containsKey(nums[i]))
				hm.get(nums[i]).add(i);
			else
			{
				hm.put(nums[i], new ArrayList<Integer>());
				hm.get(nums[i]).add(i);
			}
		for (int i : hm.keySet())
			for (int j = 0; j < hm.get(i).size() - 1; j++)
				if (hm.get(i).get(j + 1) - hm.get(i).get(j) <= k)
					return true;
		return false;
	}
}

//220. Contains Duplicate III
//Runtime: 700 ms, faster than 5.06% of Java online submissions for Contains Duplicate III.
//Memory Usage: 35.4 MB, less than 99.07% of Java online submissions for Contains Duplicate III.
class Solution220
{
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t)
	{
		for (int i = 0; i < nums.length; i++)
			for (int j = Math.max(0, i - k); j <= Math.min(nums.length - 1, i + k); j++)
				if (i != j && Math.abs((double) nums[i] - nums[j]) <= (double) t + 0.5)
					return true;
		return false;

	}
}

public class LC211_220
{
	public static void main(String[] args)
	{
		String[] words =
		{ "oath", "pea", "eat", "rain" };
		char[][] board =
		{
				{ 'o', 'a', 'a', 'n' },
				{ 'e', 't', 'a', 'e' },
				{ 'i', 'h', 'k', 'r' },
				{ 'i', 'f', 'l', 'v' } };
		System.out.println(new Solution212_2().findWords(board, words));

		System.out.println("a" + 'b');
	}

}
