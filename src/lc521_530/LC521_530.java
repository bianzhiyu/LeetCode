package lc521_530;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import treeCodec.TreeNode;

//521. Longest Uncommon Subsequence I
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Longest Uncommon Subsequence I .
//Memory Usage: 35.2 MB, less than 94.94% of Java online submissions for Longest Uncommon Subsequence I .
class Solution521
{
	public int findLUSlength(String a, String b)
	{
		if (a.equals(b))
			return -1;
		else
			return a.length() > b.length() ? a.length() : b.length();
	}
}

//522. Longest Uncommon Subsequence II
//Runtime: 5 ms, faster than 78.31% of Java online submissions for Longest Uncommon Subsequence II.
//Memory Usage: 37.3 MB, less than 40.00% of Java online submissions for Longest Uncommon Subsequence II.
class Solution522
{
	private boolean search(String sub, String str, int p1, int p2)
	{
		if (p1 == sub.length())
			return true;
		if (p2 == str.length())
			return false;
		boolean a1 = search(sub, str, p1, p2 + 1);
		if (a1)
			return true;
		if (sub.charAt(p1) == str.charAt(p2))
			a1 = search(sub, str, p1 + 1, p2 + 1);
		return a1;
	}

	private boolean isSubstr(String sub, String str)
	{
		return search(sub, str, 0, 0);
	}

	public int findLUSlength(String[] strs)
	{
		int max = -1;
		for (int i = 0; i < strs.length; i++)
			for (int j = 0; j < strs[i].length(); j++)
				for (int k = j + 1; k <= strs[i].length(); k++)
				{
					if (k - j <= max)
						continue;
					boolean exi = false;
					String sub = strs[i].substring(j, k);
					for (int l = 0; l < strs.length; l++)
						if (l != i && isSubstr(sub, strs[l]))
						{
							exi = true;
							break;
						}
					if (!exi)
						max = k - j;
				}
		return max;
	}
}

//523. Continuous Subarray Sum
//Runtime: 37 ms, faster than 15.22% of Java online submissions for Continuous Subarray Sum.
//Memory Usage: 41.4 MB, less than 6.29% of Java online submissions for Continuous Subarray Sum.
class Solution523
{
	public boolean checkSubarraySum(int[] nums, int k)
	{
		if (k < 0)
			k = -k;
		HashMap<Integer, List<Integer>> hm = new HashMap<Integer, List<Integer>>();
		int len = nums.length;
		int[] s = new int[len + 1];
		s[0] = 0;
		for (int i = 1; i <= len; i++)
		{
			if (k > 0)
				s[i] = (s[i - 1] + nums[i - 1]) % k;
			else
				s[i] = s[i - 1] + nums[i - 1];
			if (!hm.containsKey(s[i]))
				hm.put(s[i], new ArrayList<Integer>());
			hm.get(s[i]).add(i);
		}
		int tot = 0;
		for (int i = 1; i <= len; i++)
		{
			List<Integer> l = hm.get(s[i - 1]);
			if (l == null)
				continue;
			if (l.get(l.size() - 1) < i + 1)
				continue;
			int f = 0, r = l.size() - 1;
			while (f < r)
			{
				if (r == f + 1)
				{
					if (l.get(f) >= i + 1)
						r = f;
					else
						f = r;
					break;
				}
				int m = (f + r) / 2;
				if (l.get(m) >= i + 1)
					r = m;
				else
					f = m + 1;
			}
			tot += l.size() - r;
		}
		return tot > 0;
	}
}

//524. Longest Word in Dictionary through Deleting
//Runtime: 41 ms, faster than 45.67% of Java online submissions for Longest Word in Dictionary through Deleting.
//Memory Usage: 39.9 MB, less than 90.55% of Java online submissions for Longest Word in Dictionary through Deleting.
class Solution524
{
	boolean reachable(String s, String ss)
	{
		int sp = 0, ssp = 0;
		while (sp < s.length() && ssp < ss.length())
		{
			if (s.charAt(sp) == ss.charAt(ssp))
			{
				sp++;
				ssp++;
			} else
				sp++;
		}
		return ssp == ss.length();
	}

	public String findLongestWord(String s, List<String> d)
	{
		String ans = "";
		int n = d.size();
		for (int i = 0; i < n; i++)
			if (reachable(s, d.get(i)) && (ans.length() < d.get(i).length()
					|| (ans.length() == d.get(i).length() && d.get(i).compareTo(ans) < 0)))
			{
				ans = d.get(i);
			}
		return ans;
	}
}

//525. Contiguous Array
//Runtime: 959 ms, faster than 5.05% of Java online submissions for Contiguous Array.
//Memory Usage: 53.2 MB, less than 12.70% of Java online submissions for Contiguous Array.
class Solution525
{
	private int[] s;

	private int partSum(int left, int right)
	{
		if (left == 0)
			return s[right];
		return s[right] - s[left - 1];
	}

	public int findMaxLength(int[] nums)
	{
		int len = nums.length;
		if (len == 0)
			return 0;
		s = new int[len];
		s[0] = nums[0];
		for (int i = 1; i < len; i++)
			s[i] = s[i - 1] + nums[i];
		for (int i = len / 2 * 2; i >= 2; i -= 2)
			for (int j = 0; j + i <= len; j++)
				if (partSum(j, j + i - 1) == i / 2)
					return i;
		return 0;
	}
}

//Runtime: 28 ms, faster than 96.21% of Java online submissions for Contiguous Array.
//Memory Usage: 52.9 MB, less than 31.75% of Java online submissions for Contiguous Array.
//https://leetcode.com/problems/contiguous-array/discuss/99646/Easy-Java-O(n)-Solution-PreSum-%2B-HashMap
class Solution525_2
{

	public int findMaxLength(int[] nums)
	{
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		int s = 0, max = 0;
		hm.put(0, -1);
		for (int i = 0; i < nums.length; i++)
		{
			if (nums[i] == 1)
				s += 1;
			else
				s += -1;
			if (hm.containsKey(s))
				max = Math.max(max, i - hm.get(s));
			else
				hm.put(s, i);
		}
		return max;
	}
}

//526. Beautiful Arrangement
//Runtime: 62 ms, faster than 57.62% of Java online submissions for Beautiful Arrangement.
//Memory Usage: 31.6 MB, less than 100.00% of Java online submissions for Beautiful Arrangement.
class Solution526
{
	private boolean[] used;
	private int tot = 0;

	private void dfs(int sp)
	{
		if (sp == used.length)
		{
			tot++;
			return;
		}
		for (int i = 0; i < used.length; i++)
			if (!used[i] && ((sp + 1) % (i + 1) == 0 || (i + 1) % (sp + 1) == 0))
			{
				used[i] = true;
				dfs(sp + 1);
				used[i] = false;
			}
	}

	public int countArrangement(int N)
	{
		used = new boolean[N];
		dfs(0);
		return tot;
	}
}

//528. Random Pick with Weight
//Runtime: 70 ms, faster than 94.84% of Java online submissions for Random Pick with Weight.
//Memory Usage: 53.3 MB, less than 42.91% of Java online submissions for Random Pick with Weight.
class Solution528
{
	Random rd;
	int[] s;
	int m;

	public Solution528(int[] w)
	{
		rd = new Random();
		int len = w.length;
		s = new int[len];
		m = -1;
		for (int i = 0; i < len; i++)
		{
			m += w[i];
			s[i] = m;
		}
	}

	int fd(int n)
	{
		int l = 0, r = s.length - 1;
		while (l < r)
		{
			if (r == l + 1)
			{
				if (s[l] >= n)
					r = l;
				else
					l = r;
			} else
			{
				int m = (l + r) / 2;
				if (s[m] >= n)
					r = m;
				else
					l = m + 1;
			}
		}
		return l;
	}

	public int pickIndex()
	{
		int n = rd.nextInt(m + 1);
		return fd(n);
	}
}

/**
 * Your Solution object will be instantiated and called as such: Solution obj =
 * new Solution(w); int param_1 = obj.pickIndex();
 */

//529. Minesweeper
//Runtime: 5 ms, faster than 37.03% of Java online submissions for Minesweeper.
//Memory Usage: 41.5 MB, less than 40.82% of Java online submissions for Minesweeper.
class Solution529
{
	private final static int[][] di = new int[][]
	{
			{ 1, 0 },
			{ 1, 1 },
			{ 0, 1 },
			{ -1, 1 },
			{ -1, 0 },
			{ -1, -1 },
			{ 0, -1 },
			{ 1, -1 } };

	public char[][] updateBoard(char[][] board, int[] click)
	{
		if (board[click[0]][click[1]] == 'M')
		{
			board[click[0]][click[1]] = 'X';
			return board;
		}
		int row = board.length, col = board[0].length;
		int[][] queue = new int[row * col][2];
		Set<Integer> used = new HashSet<Integer>();
		queue[0][0] = click[0];
		queue[0][1] = click[1];
		used.add(click[0] * col + click[1]);
		int f = 0, r = 1;
		while (f < r)
		{
			int x = queue[f][0], y = queue[f][1];
			f++;
			int nearMineNum = 0;
			for (int i = 0; i < 8; i++)
			{
				int nx = x + di[i][0], ny = y + di[i][1];
				if (nx >= 0 && ny >= 0 && nx < row && ny < col && board[nx][ny] == 'M')
					nearMineNum++;
			}
			if (nearMineNum > 0)
				board[x][y] = (char) (nearMineNum + '0');
			else
			{
				board[x][y] = 'B';
				for (int i = 0; i < 8; i++)
				{
					int nx = x + di[i][0], ny = y + di[i][1];
					if (nx >= 0 && ny >= 0 && nx < row && ny < col && board[nx][ny] == 'E'
							&& !used.contains(nx * col + ny))
					{
						queue[r][0] = nx;
						queue[r][1] = ny;
						used.add(nx * col + ny);
						r++;
					}
				}
			}
		}

		return board;
	}
}

//530. Minimum Absolute Difference in BST
// Runtime: 2 ms, faster than 88.68% of Java online submissions for Minimum Absolute Difference in BST.
// Memory Usage: 42.4 MB, less than 11.89% of Java online submissions for Minimum Absolute Difference in BST.
class Solution530 
{
    private void trav(TreeNode rt,List<Integer> l)
    {
        if (rt==null) return;
        trav(rt.left,l);
        l.add(rt.val);
        trav(rt.right,l);
    }
    public int getMinimumDifference(TreeNode root) 
    {
        List<Integer> l=new ArrayList<Integer>();
        trav(root,l);
        int m=l.get(1)-l.get(0);
        for (int i=1;i<l.size()-1;i++)
            if (l.get(i+1)-l.get(i)<m)
                m=l.get(i+1)-l.get(i);
        return m;
    }
}

public class LC521_530
{
	public static void test529()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input529.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output529.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;

			Set<Character> nulchar = new HashSet<Character>(Arrays.asList(' ', ',', '[', ']', '"', '\''));

			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int row = Integer.parseInt(inLine);

				inLine = bfr.readLine();
				int col = Integer.parseInt(inLine);

				inLine = bfr.readLine();

				char[][] b = new char[row][col];

				int p = 0, st = 0;
				while (st < row * col)
				{
					if (nulchar.contains(inLine.charAt(p)))
						p++;
					else
					{
						b[st / col][st % col] = inLine.charAt(p);
						st++;
						p++;
					}
				}

				inLine = bfr.readLine();
				inLine = inLine.trim();
				inLine = inLine.substring(1, inLine.length() - 1);
				String[] nums = inLine.split(",");
				nums[0] = nums[0].trim();
				nums[1] = nums[1].trim();

				int[] click = new int[]
				{ Integer.parseInt(nums[0]), Integer.parseInt(nums[1]) };

				Solution529 solver = new Solution529();

				b = solver.updateBoard(b, click);

				StringBuilder out = new StringBuilder();
				for (int i = 0; i < b.length; i++)
				{
					for (int j = 0; j < b[i].length; j++)
					{
						out.append(b[i][j]).append(" ");
					}
					out.append("\n");
				}

				bfw.write(out.toString());
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
		test529();
	}

}
