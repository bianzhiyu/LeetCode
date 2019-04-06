package lc211_220;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

//https://leetcode.com/articles/skyline-problem/

//Runtime: 7 ms, faster than 92.57% of Java online submissions for The Skyline Problem.
//Memory Usage: 43 MB, less than 88.46% of Java online submissions for The Skyline Problem.
class Solution218_2
{
	/**
	 * Divide-and-conquer algorithm to solve skyline problem, which is similar with
	 * the merge sort algorithm.
	 */
	public List<int[]> getSkyline(int[][] buildings)
	{
		int n = buildings.length;
		List<int[]> output = new ArrayList<int[]>();

		// The base cases
		if (n == 0)
			return output;
		if (n == 1)
		{
			int xStart = buildings[0][0];
			int xEnd = buildings[0][1];
			int y = buildings[0][2];

			output.add(new int[]
			{ xStart, y });
			output.add(new int[]
			{ xEnd, 0 });
			return output;
		}

		// If there is more than one building,
		// recursively divide the input into two subproblems.
		List<int[]> leftSkyline, rightSkyline;
		leftSkyline = getSkyline(Arrays.copyOfRange(buildings, 0, n / 2));
		rightSkyline = getSkyline(Arrays.copyOfRange(buildings, n / 2, n));

		// Merge the results of subproblem together.
		return mergeSkylines(leftSkyline, rightSkyline);
	}

	/**
	 * Merge two skylines together.
	 */
	public List<int[]> mergeSkylines(List<int[]> left, List<int[]> right)
	{
		int nL = left.size(), nR = right.size();
		int pL = 0, pR = 0;
		int currY = 0, leftY = 0, rightY = 0;
		int x, maxY;
		List<int[]> output = new ArrayList<int[]>();

		// while we're in the region where both skylines are present
		while ((pL < nL) && (pR < nR))
		{
			int[] pointL = left.get(pL);
			int[] pointR = right.get(pR);
			// pick up the smallest x
			if (pointL[0] < pointR[0])
			{
				x = pointL[0];
				leftY = pointL[1];
				pL++;
			} else
			{
				x = pointR[0];
				rightY = pointR[1];
				pR++;
			}
			// max height (i.e. y) between both skylines
			maxY = Math.max(leftY, rightY);
			// update output if there is a skyline change
			if (currY != maxY)
			{
				updateOutput(output, x, maxY);
				currY = maxY;
			}
		}

		// there is only left skyline
		currY = appendSkyline(output, left, pL, nL, currY);

		// there is only right skyline
		currY = appendSkyline(output, right, pR, nR, currY);

		return output;
	}

	/**
	 * Update the final output with the new element.
	 */
	public void updateOutput(List<int[]> output, int x, int y)
	{
		// if skyline change is not vertical -
		// add the new point
		if (output.isEmpty() || output.get(output.size() - 1)[0] != x)
			output.add(new int[]
			{ x, y });
		// if skyline change is vertical -
		// update the last point
		else
		{
			output.get(output.size() - 1)[1] = y;
		}
	}

	/**
	 * Append the rest of the skyline elements with indice (p, n) to the final
	 * output.
	 */
	public int appendSkyline(List<int[]> output, List<int[]> skyline, int p, int n, int currY)
	{
		while (p < n)
		{
			int[] point = skyline.get(p);
			int x = point[0];
			int y = point[1];
			p++;

			// update output
			// if there is a skyline change
			if (currY != y)
			{
				updateOutput(output, x, y);
				currY = y;
			}
		}
		return currY;
	}
}

//mimic the solution_2.
//Runtime: 4 ms, faster than 98.98% of Java online submissions for The Skyline Problem.
//Memory Usage: 43.2 MB, less than 88.46% of Java online submissions for The Skyline Problem.
class Solution218_3
{
	public List<int[]> getSkyline(int[][] buildings)
	{
		return dNc(buildings, 0, buildings.length);
	}

	/** divive and conquer */
	private List<int[]> dNc(int[][] B, int L, int R)
	{
		List<int[]> ans = new ArrayList<int[]>();
		if (L >= R)
			return ans;
		if (R == L + 1)
		{
			ans.add(new int[]
			{ B[L][0], B[L][2] });
			ans.add(new int[]
			{ B[L][1], 0 });
			return ans;
		}
		List<int[]> leftCont = dNc(B, L, L + (R - L) / 2);
		List<int[]> rightCont = dNc(B, L + (R - L) / 2, R);
		ans = merge(leftCont, rightCont);
		return ans;
	}

	private List<int[]> merge(List<int[]> left, List<int[]> right)
	{
		List<int[]> ans = new ArrayList<int[]>();
		int lLen = left.size(), rLen = right.size();
		int lp = 0, rp = 0, leftHeight = 0, rightHeight = 0, curHeight = 0;
		while (lp < lLen && rp < rLen)
		{
			int[] lb = left.get(lp), rb = right.get(rp);
			int x;
			if (lb[0] < rb[0])
			{
				x = lb[0];
				leftHeight = lb[1];
				lp++;
			} else
			{
				x = rb[0];
				rightHeight = rb[1];
				rp++;
			}
			int nH = leftHeight > rightHeight ? leftHeight : rightHeight;
			if (nH != curHeight)
			{
				append(ans, x, nH);
				curHeight = nH;
			}
		}
		while (lp < lLen)
		{
			int[] b = left.get(lp++);
			if (b[1] != curHeight)
			{
				append(ans, b[0], b[1]);
				curHeight = b[1];
			}
		}
		while (rp < rLen)
		{
			int[] b = right.get(rp++);
			if (b[1] != curHeight)
			{
				append(ans, b[0], b[1]);
				curHeight = b[1];
			}
		}
		return ans;
	}

	private void append(List<int[]> ans, int nx, int nh)
	{
		if (ans.size() == 0 || ans.get(ans.size() - 1)[0] != nx)
			ans.add(new int[]
			{ nx, nh });
		else
			ans.get(ans.size() - 1)[1] = nh;
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
	public static void test212()
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

	public static void test218()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input218.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output218.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int[][] g = test.Test.parse2DIntArr(inLine);

				Solution218_3 s = new Solution218_3();

				List<int[]> ans = s.getSkyline(g);

				String out = "[";
				for (int[] p : ans)
					out = out + "[" + p[0] + ", " + p[1] + "] ";
				out = out + "]";
				System.out.println(out);
				bfw.write(out);
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
		test218();
	}
}
