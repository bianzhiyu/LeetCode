package lc121_130;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TreeNode
{
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x)
	{
		val = x;
	}
}

//t121
//t=1ms, m=39MB
//t:0.9988, m:1
class Solution121
{
	public int maxProfit(int[] prices)
	{
		if (prices.length == 0)
			return 0;
		int minprice = prices[0], maxearn = 0;
		for (int i = 1; i < prices.length; i++)
		{
			maxearn = Math.max(prices[i] - minprice, maxearn);
			minprice = Math.min(minprice, prices[i]);

		}
		return maxearn;
	}
}

//t122. Best Time to Buy and Sell Stock II
//t=325ms, m=39MB
//t:0.0101, m:1
class Solution122
{
	public int maxProfit(int[] prices)
	{
		if (prices.length == 0)
			return 0;
		int[] me = new int[prices.length];
		me[0] = 0;
		for (int i = 1; i < prices.length; i++)
		{
			me[i] = Math.max(0, prices[i] - prices[0]);
			for (int j = 1; j < i; j++)
			{
				me[i] = Math.max(me[i], Math.max(0, prices[i] - prices[j]) + me[j - 1]);
			}
			me[i] = Math.max(me[i], me[i - 1]);
		}

		return me[prices.length - 1];
	}
}

//Think about the difference D[i]=p[i+1]-p[i]
//Let D_plus=max(0,D)
//integrate D_plus 
//t=1ms, m=37MB
//t:0.9912, m:1
class Solution122_2
{
	public int maxProfit(int[] prices)
	{
		if (prices.length == 0)
			return 0;
		int ans = 0;
		for (int i = 1; i < prices.length; i++)
			ans += prices[i] - prices[i - 1] > 0 ? prices[i] - prices[i - 1] : 0;
		return ans;
	}
}

//t123. Best Time to Buy and Sell Stock III
//t=1ms, m=39.6MB
//t:1, m:1
class Solution123
{
	public int maxProfit(int[] prices)
	{
		if (prices.length <= 1)
			return 0;
		int pl = prices.length;
		int[] n = new int[pl];
		int pt = 0;
		int i = 1;
		while (i < pl && prices[i] - prices[i - 1] <= 0)
			i++;
		while (i < pl)
		{
			int ts = 0;
			while (i < pl && prices[i] - prices[i - 1] >= 0)
			{
				ts += prices[i] - prices[i - 1];
				i++;
			}
			if (ts > 0)
			{
				n[pt++] = ts;
			}
			ts = 0;
			while (i < pl && prices[i] - prices[i - 1] <= 0)
			{
				ts += prices[i] - prices[i - 1];
				i++;
			}
			if (ts < 0)
			{
				n[pt++] = ts;
			}
		}
		if (pt > 0 && n[pt - 1] < 0)
			pt--;
		if (pt == 0)
			return 0;
		if (pt == 1)
			return n[0];
		if (pt == 3)
			return n[0] + n[2];
		int[] maxyet = new int[pt];
		maxyet[0] = n[0];
		int minpri = 0, instpri = 0;
		for (i = 0; i < pt; i++)
		{
			instpri += n[i];
			minpri = Math.min(minpri, instpri);
			if (i % 2 == 0)
			{
				maxyet[i] = instpri - minpri;
				if (i >= 2 && maxyet[i - 2] > maxyet[i])
					maxyet[i] = maxyet[i - 2];
			}
		}
		int[] maxsince = new int[pt];
		int maxpri = 0;
		instpri = 0;
		maxsince[pt - 1] = n[pt - 1];
		for (i = pt - 1; i >= 0; i--)
		{
			instpri -= n[i];
			maxpri = Math.max(maxpri, instpri);
			if ((pt - i) % 2 == 1)
			{
				maxsince[i] = maxpri - instpri;
				if (i < pt - 2 && maxsince[i + 2] > maxsince[i])
					maxsince[i] = maxsince[i + 2];
			}
		}
		int maxa = 0;
		for (i = 0; i < pt - 2; i += 2)
			if (maxyet[i] + maxsince[i + 2] > maxa)
				maxa = maxyet[i] + maxsince[i + 2];
		return maxa;
	}
}

//more clearly
//t=3ms, m=35.8MB
//t:0.6428, m:1
class Solution123_2
{
	public int maxProfit(int[] prices)
	{
		int pl = prices.length;
		if (pl <= 1)
			return 0;
		int[] maxyet = new int[pl];
		maxyet[0] = 0;
		int minpri = prices[0];
		for (int i = 1; i < pl; i++)
		{
			maxyet[i] = Math.max(maxyet[i - 1], prices[i] - minpri);
			minpri = Math.min(minpri, prices[i]);
		}
		// LC121_130.disp(maxyet);
		int[] maxsince = new int[pl];
		maxsince[pl - 1] = 0;
		int maxpri = prices[pl - 1];
		for (int i = pl - 2; i >= 0; i--)
		{
			maxsince[i] = Math.max(maxsince[i + 1], maxpri - prices[i]);
			maxpri = Math.max(maxpri, prices[i]);
		}
		// LC121_130.disp(maxsince);
		int maxa = 0;
		for (int i = 0; i < pl - 1; i++)
			if (maxyet[i] + maxsince[i + 1] > maxa)
				maxa = maxyet[i] + maxsince[i + 1];
		if (maxyet[pl - 1] > maxa)
			maxa = maxyet[pl - 1];
		return maxa;
	}
}

//t124. Binary Tree Maximum Path Sum
//t=1ms, m=41MB
//t:0.9986, m:1
/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class RST124
{
	int FinMaxPath, LinkMaxPath;
}

class Solution124
{
	public RST124 travel(TreeNode root)
	{
		RST124 r = new RST124();
		r.FinMaxPath = root.val;
		r.LinkMaxPath = root.val;
		if (root.left == null && root.right == null)
			return r;
		RST124 LR = null, RR = null;
		if (root.left != null)
			LR = travel(root.left);
		if (root.right != null)
			RR = travel(root.right);

		if (LR != null && LR.LinkMaxPath + root.val > r.LinkMaxPath)
			r.LinkMaxPath = LR.LinkMaxPath + root.val;
		if (RR != null && RR.LinkMaxPath + root.val > r.LinkMaxPath)
			r.LinkMaxPath = RR.LinkMaxPath + root.val;

		if (r.LinkMaxPath > r.FinMaxPath)
			r.FinMaxPath = r.LinkMaxPath;
		if (LR != null && LR.FinMaxPath > r.FinMaxPath)
			r.FinMaxPath = LR.FinMaxPath;
		if (RR != null && RR.FinMaxPath > r.FinMaxPath)
			r.FinMaxPath = RR.FinMaxPath;
		if (LR != null && RR != null && LR.LinkMaxPath + RR.LinkMaxPath + root.val > r.FinMaxPath)
			r.FinMaxPath = LR.LinkMaxPath + RR.LinkMaxPath + root.val;
		return r;
	}

	public int maxPathSum(TreeNode root)
	{
		if (root == null)
			return 0;
		RST124 r = travel(root);
		return r.FinMaxPath;
	}
}

//125. Valid Palindrome
//Runtime: 5 ms, faster than 77.86% of Java online submissions for Valid Palindrome.
//Memory Usage: 38.8 MB, less than 28.13% of Java online submissions for Valid Palindrome.
class Solution125
{
	public boolean isPalindrome(String s)
	{
		if (s.length() == 0)
			return true;
		s = s.toLowerCase();
		int n = s.length();
		int m = 0;
		for (int i = 0; i < n; i++)
			if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z' || s.charAt(i) >= '0' && s.charAt(i) <= '9')
				m++;

		char[] str = new char[m];

		m = 0;
		for (int i = 0; i < n; i++)
			if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z' || s.charAt(i) >= '0' && s.charAt(i) <= '9')
				str[m++] = s.charAt(i);

		for (int i = 0; i < m / 2; i++)
			if (str[i] != str[m - 1 - i])
				return false;
		return true;
	}
}

//t126. Word Ladder II
//t=358ms, m=147.8MB
//t:0.1834, m:1
class Solution126
{
	List<List<Integer>> LL;
	List<String> AWL;
	List<List<String>> ans;
	int[] stack, q, dist, prelen;
	int[][] pre;

	private boolean conn(String s1, String s2)
	{
		int c = 0;
		for (int i = 0; i < s1.length(); i++)
			c += s1.charAt(i) != s2.charAt(i) ? 1 : 0;
		if (c == 1)
			return true;
		else
			return false;
	}

	private void establishLinkList()
	{
		LL = new ArrayList<List<Integer>>(AWL.size());
		// 0->begin, 1->end, 2,...->others
		for (int i = 0; i < AWL.size(); i++)
			LL.add(new ArrayList<Integer>());
		for (int i = 0; i < AWL.size(); i++)
			for (int j = i + 1; j < AWL.size(); j++)
				if (conn(AWL.get(i), AWL.get(j)))
				{
					LL.get(i).add(j);
					LL.get(j).add(i);
				}
	}

	private void establishAllWordList(String beginWord, String endWord, List<String> wordList)
	{
		AWL = new ArrayList<String>();
		AWL.add(beginWord);
		AWL.add(endWord);
		for (int i = 0; i < wordList.size(); i++)
		{
			if (wordList.get(i).compareTo(beginWord) != 0 && wordList.get(i).compareTo(endWord) != 0)
				AWL.add(wordList.get(i));
		}
	}

	private boolean BFS()
	{
		q = new int[AWL.size()];
		dist = new int[AWL.size()];
		for (int i = 0; i < dist.length; i++)
			dist[i] = Integer.MAX_VALUE;
		prelen = new int[AWL.size()];
		pre = new int[AWL.size()][AWL.size()];
		boolean[] used = new boolean[AWL.size()];
		for (int i = 0; i < used.length; i++)
			used[i] = false;
		q[0] = 0;
		prelen[0] = 0;
		used[0] = true;
		dist[0] = 0;
		int endInd = Integer.MAX_VALUE;
		int head = 0;
		int tail = 1;
		while (head < tail && head < endInd)
		{
			int nt = q[head++];
			for (int j = 0; j < LL.get(nt).size(); j++)
			{
				int i = LL.get(nt).get(j);
				if (!used[i])
				{
					q[tail] = i;
					dist[i] = dist[nt] + 1;
					prelen[i] = 1;
					pre[i][0] = nt;
					used[i] = true;
					if (i == 1)
						endInd = tail;
					tail++;
				} else if (dist[nt] + 1 == dist[i])
				{
					pre[i][prelen[i]++] = nt;
				}
			}
		}
		return endInd < Integer.MAX_VALUE;
	}

	private void DFS(int sp, int x)
	{
		if (x == 0)
		{
			List<String> t = new ArrayList<String>();
			for (int i = sp - 1; i >= 0; i--)
				t.add(AWL.get(stack[i]));
			t.add(AWL.get(1));
			ans.add(t);
			return;
		}
		for (int i = 0; i < prelen[x]; i++)
		{
			stack[sp] = pre[x][i];
			DFS(sp + 1, pre[x][i]);
		}
	}

	private boolean EWInWL(String endWord, List<String> wordList)
	{
		for (int i = 0; i < wordList.size(); i++)
			if (wordList.get(i).compareTo(endWord) == 0)
				return true;
		return false;
	}

	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList)
	{
		ans = new ArrayList<List<String>>();
		if (!EWInWL(endWord, wordList))
			return ans;
		establishAllWordList(beginWord, endWord, wordList);
		establishLinkList();
		if (BFS())
		{
			stack = new int[AWL.size()];
			DFS(0, 1);
		}
		return ans;

	}
}

//127. Word Ladder
//Runtime: 287 ms, faster than 31.33% of Java online submissions for Word Ladder.
//Memory Usage: 40.6 MB, less than 68.68% of Java online submissions for Word Ladder.
class Solution127
{
	public int ladderLength(String beginWord, String endWord, List<String> wordList)
	{
		wordList.add(beginWord);
		int wlen = wordList.size();
		List<List<Integer>> nbl = new ArrayList<List<Integer>>();

		int target = -1;
		for (int i = 0; i < wlen; i++)
			if (wordList.get(i).compareTo(endWord) == 0)
			{
				target = i;
				break;
			}
		if (target == -1)
			return 0;

		for (int i = 0; i < wlen; i++)
			nbl.add(new ArrayList<Integer>());
		for (int i = 0; i < wlen; i++)
		{
			String si = wordList.get(i);
			for (int j = i + 1; j < wlen; j++)
			{
				int d = 0;
				String sj = wordList.get(j);
				for (int k = 0; k < si.length(); k++)
					if (si.charAt(k) != sj.charAt(k))
						d++;
				if (d == 1)
				{
					nbl.get(i).add(j);
					nbl.get(j).add(i);
				}
			}
		}

		int[] q = new int[wlen];
		int h = 0, r = 1;
		q[0] = wlen - 1;
		int[] dist = new int[wlen];
		dist[wlen - 1] = 1;
		boolean[] used = new boolean[wlen];
		used[wlen - 1] = true;
		while (h < r)
		{
			int nt = q[h];
			h++;
			for (int j : nbl.get(nt))
			{
				if (!used[j])
				{
					q[r] = j;
					r++;
					used[j] = true;
					dist[j] = dist[nt] + 1;
					if (j == target)
						return dist[j];
				}
			}
		}
		return 0;
	}
}

//t128
//t=2ms, m=36MB
//t:1, m:1
//RK: there is no O(T)=O(N) solution,
//thus the requirement in question is impossible.
class Solution128
{
	public int longestConsecutive(int[] nums)
	{
		Arrays.sort(nums);
		int maxl = 0, i = 0;
		while (i < nums.length)
		{
			int j = i + 1;
			while (j < nums.length && nums[j] <= nums[j - 1] + 1)
				j++;
			if (nums[j - 1] - nums[i] + 1 > maxl)
				maxl = nums[j - 1] - nums[i] + 1;
			i = j;
		}
		return maxl;
	}
}

//t=167ms
class Solution128_2
{
	public int longestConsecutive(int[] nums)
	{
		for (int j = 0; j < nums.length; j++)
		{
			for (int k = j + 1; k < nums.length; k++)
				if (nums[k] < nums[j])
				{
					int t = nums[j];
					nums[j] = nums[k];
					nums[k] = t;
				}
		}
		int maxl = 0, i = 0;
		while (i < nums.length)
		{
			int j = i + 1;
			while (j < nums.length && nums[j] <= nums[j - 1] + 1)
				j++;
			if (nums[j - 1] - nums[i] + 1 > maxl)
				maxl = nums[j - 1] - nums[i] + 1;
			i = j;
		}
		return maxl;
	}
}

//129. Sum Root to Leaf Numbers
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Sum Root to Leaf Numbers.
//Memory Usage: 36.9 MB, less than 24.26% of Java online submissions for Sum Root to Leaf Numbers.
class Solution129
{
	int sum = 0;

	void trav(TreeNode r, int partsum)
	{
		partsum = partsum * 10 + r.val;
		if (r.left == null && r.right == null)
		{
			sum += partsum;
			return;
		}
		if (r.left != null)
		{
			trav(r.left, partsum);
		}
		if (r.right != null)
			trav(r.right, partsum);
	}

	public int sumNumbers(TreeNode root)
	{
		if (root == null)
			return 0;
		trav(root, 0);
		return sum;
	}
}

//130. Surrounded Regions
//Runtime: 9 ms, faster than 19.41% of Java online submissions for Surrounded Regions.
//Memory Usage: 47.6 MB, less than 5.20% of Java online submissions for Surrounded Regions.
class Solution130
{
	boolean[][] checked;
	final static int[][] di = new int[][]
	{
			{ 1, 0 },
			{ 0, 1 },
			{ -1, 0 },
			{ 0, -1 } };

	void bfs(char[][] board, int x, int y)
	{
		int l1 = board.length, l2 = board[0].length;
		int[][] q = new int[l1 * l2][2];
		q[0][0] = x;
		q[0][1] = y;
		checked[x][y] = true;
		int h = 0, r = 1;
		boolean onbd = false;

		while (h < r)
		{
			int nx = q[h][0], ny = q[h][1];
			h++;
			if (nx == 0 || ny == 0 || nx == l1 - 1 || ny == l2 - 1)
				onbd = true;
			for (int i = 0; i < 4; i++)
			{
				int mx = nx + di[i][0], my = ny + di[i][1];
				if (mx >= 0 && my >= 0 && mx < l1 && my < l2 && !checked[mx][my] && board[mx][my] == 'O')
				{
					checked[mx][my] = true;
					q[r][0] = mx;
					q[r][1] = my;
					r++;
				}
			}
		}
		if (!onbd)
		{
			for (int i = 0; i < r; i++)
				board[q[i][0]][q[i][1]] = 'X';
		}
	}

	public void solve(char[][] board)
	{
		if (board.length == 0 || board[0].length == 0)
			return;
		int l1 = board.length, l2 = board[0].length;
		checked = new boolean[l1][l2];
		for (int i1 = 0; i1 < l1; i1++)
			for (int i2 = 0; i2 < l2; i2++)
				if (board[i1][i2] == 'O' && !checked[i1][i2])
					bfs(board, i1, i2);
	}
}

public class LC121_130
{
	static void disp(int[] a)
	{
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}

	public static void main(String[] args)
	{

		Solution125 s = new Solution125();

		System.out.println(s.isPalindrome("0P"));

	}

}
