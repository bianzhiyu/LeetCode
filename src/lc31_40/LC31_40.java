package lc31_40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class BubbleSort
{
	public static void BS(int[] nums)
	{
		BS(nums, 0, nums.length);
	}

	public static void BS(int[] nums, int i, int l)
	{
		int tmp;
		for (int j = i; j < l; j++)
			for (int k = j + 1; k < l; k++)
				if (nums[k] < nums[j])
				{
					tmp = nums[j];
					nums[j] = nums[k];
					nums[k] = tmp;
				}
	}
}

//t31. Next Permutation
//t=9ms, m=39MB//t:0.6445, m:1
//t=8ms, m=39MB//t:0.9854, m:1
class Solution31
{
	public void nextPermutation(int[] nums)
	{
		int l = nums.length, i = l - 1, j, tmp;
		while (i > 0 && nums[i] <= nums[i - 1])
			i--;
		if (i > 0)
		{
			j = i;
			for (int k = i + 1; k < l; k++)
				if (nums[k] > nums[i - 1] && nums[k] < nums[j])
					j = k;
			tmp = nums[i - 1];
			nums[i - 1] = nums[j];
			nums[j] = tmp;
		}
		Arrays.sort(nums, i, l);
	}
}

//t32
//TLE:Time Limit Exceeded
//N^3
class Solution32
{
	public int longestValidParentheses(String s)
	{
		int l = s.length();
		int[] ds = new int[l];
		for (int i = 0; i < l; i++)
		{
			ds[i] = s.charAt(i) == '(' ? 0 : 1;
		}
		boolean a[][] = new boolean[l + 1][];
		for (int i = 0; i <= l; i++)
		{
			a[i] = new boolean[l + 1];
			for (int j = 0; j <= l; j++)
				a[i][j] = false;
		}
		for (int i = 0; i <= l; i++)
			a[i][i] = true;
		for (int i = 0; i + 1 < l; i++)
		{
			if ((ds[i] == 0) && (ds[i + 1] == 1))
			{
				a[i][i + 2] = true;
			}
		}
		for (int j = 4; j <= l; j += 2)
		{
			for (int i = 0; i + j <= l; i++)
			{
				if ((ds[i] == 0) && (ds[i + j - 1] == 1) && a[i + 1][i + j - 1])
					a[i][i + j] = true;
				for (int k = 2; i + k < i + j; k += 2)
					if (a[i][i + k] && a[i + k][i + j])
						a[i][i + j] = true;

			}
		}
		for (int j = l / 2 * 2; j >= 0; j -= 2)
		{
			for (int i = 0; i + j <= l; i++)
			{
				if (a[i][i + j])
					return j;
			}
		}
		return 0;
	}
}

//t32_2
//t=114ms, o=24MB
//t:0.0606, o:0.7225
//O(T)=O(N^2)
class Solution32_2
{
	public int longestValidParentheses(String s)
	{
		int l = s.length();
		int[] ds = new int[l];
		for (int i = 0; i < l; i++)
		{
			ds[i] = s.charAt(i) == '(' ? 1 : -1;
		}
		int maxl = 0;
		for (int i = 0; i < l; i++)
		{
			int u = 0;
			for (int j = i; j < l; j++)
			{
				u = u + ds[j];
				if (u < 0)
				{
					break;
				} else if ((u == 0) && (j - i + 1 > maxl))
				{
					maxl = j - i + 1;
				}
			}
		}
		return maxl;
	}
}

//t32_3
//t=8ms, m=23MB
//t:0.9944, m:0.8626
//O(T)=O(N)
class Solution32_3
{
	public int longestValidParentheses(String s)
	{
		int l = s.length();
		int maxl = 0;
		int[] stack = new int[l];
		int sp = 0, left = -1;
		// left means the position at the left of the first left parenthese
		for (int i = 0; i < l; i++)
		{
			if (s.charAt(i) == '(')
			{
				stack[sp++] = i;
			} else
			{
				if (sp == 0)
				{
					left = i;
				} else
				{
					sp--;
					if (sp > 0)
					{
						if (i - stack[sp - 1] > maxl)
							maxl = i - stack[sp - 1];
					} else
					{
						if (i - left > maxl)
							maxl = i - left;
					}
				}
			}
		}
		return maxl;
	}
}

//33. Search in Rotated Sorted Array
//Runtime: 5 ms, faster than 100.00% of Java online submissions for Search in Rotated Sorted Array.
//Memory Usage: 40.2 MB, less than 9.48% of Java online submissions for Search in Rotated Sorted Array.
class Solution33
{
	int[] n;
	int t;

	int sch2(int l, int r)
	{
		if (l > r)
			return -1;
		if (r == l)
			if (n[l] == t)
				return l;
			else
				return -1;
		if (r == l + 1)
			if (n[r] == t)
				return r;
			else if (n[l] == t)
				return l;
			else
				return -1;
		int m = (l + r) / 2;
		if (n[m] == t)
			return m;
		if (n[m] > t)
			return sch2(l, m - 1);
		return sch2(m + 1, r);
	}

	int sch1(int l, int r)
	{
		if (l > r)
			return -1;
		if (n[l] < n[r])
			return sch2(l, r);
		if (r == l)
			if (n[l] == t)
				return l;
			else
				return -1;
		if (r == l + 1)
			if (n[r] == t)
				return r;
			else if (n[l] == t)
				return l;
			else
				return -1;
		int m = (l + r) / 2;
		if (n[m] == t)
			return m;
		if (n[r] >= t)
		{
			if (n[m] > n[r])
				return sch1(m + 1, r);
			else if (n[m] > t)
				return sch1(l, m - 1);
			else
				return sch2(m + 1, r);
		}
		if (n[l] <= t)
		{
			if (n[m] > n[l])
				if (n[m] > t)
					return sch2(l, m - 1);
				else
					return sch1(m + 1, r);
			else
				return sch1(l, m - 1);
		}
		return -1;
	}

	public int search(int[] nums, int target)
	{
		n = nums;
		t = target;
		return sch1(0, nums.length - 1);
	}
}
//34. Find First and Last Position of Element in Sorted Array
//Runtime: 3 ms, faster than 100.00% of Java online submissions for Find First and Last Position of Element in Sorted Array.
//Memory Usage: 42.6 MB, less than 5.66% of Java online submissions for Find First and Last Position of Element in Sorted Array.
class Solution34
{
	int fl(int[] n,int l, int r,int t)
	{
        if (l>r) return -1;
		if (l==r) return n[l]==t?l:-1;
		if (r==l+1) 
		{
			if (n[l]==t) return l;
			else if(n[r]==t) return r;
			else return -1;
		}
		int m=(l+r)/2;
		if (n[m]>=t) return fl(n,l,m,t);
		else return fl(n,m+1,r,t);
	}
	int fr(int[] n,int l, int r,int t)
	{
        if (l>r) return -1;
		if (l==r) return n[l]==t?l:-1;
		if (r==l+1) 
		{
			if (n[r]==t) return r;
			else if (n[l]==t) return l;
			else return -1;
		}
		int m=(l+r)/2;
		if (n[m]<=t) return fr(n,m,r,t);
		else return fr(n,l,m-1,t);
	}
	public int[] searchRange(int[] nums, int target)
	{
		return new int[] {fl(nums,0,nums.length-1,target),
				fr(nums,0,nums.length-1,target)};
	}
}

//35. Search Insert Position
//Runtime: 2 ms, faster than 100.00% of Java online submissions for Search Insert Position.
//Memory Usage: 39.3 MB, less than 100.00% of Java online submissions for Search Insert Position.
class Solution35
{
	public int searchInsert(int[] nums, int target)
	{
		int i = 0;
		while (i < nums.length)
		{
			if (target <= nums[i])
				return i;
			while (i < nums.length && target > nums[i])
				i++;
		}
		return i;
	}
}

//36. Valid Sudoku
//Runtime: 12 ms, faster than 99.56% of Java online submissions for Valid Sudoku.
//Memory Usage: 46.9 MB, less than 6.48% of Java online submissions for Valid Sudoku.
class Solution36
{
	boolean rowocc[][] = new boolean[9][9];// whether row i has number j
	boolean colocc[][] = new boolean[9][9];// whether column i has number j
	boolean bloocc[][] = new boolean[9][9];// whether block i has number j
	boolean toSolve[][] = new boolean[9][9];// whether i,j need to fill in
	final static int[][] blk = new int[][]
	{
			{ 0, 0, 0, 1, 1, 1, 2, 2, 2 },
			{ 0, 0, 0, 1, 1, 1, 2, 2, 2 },
			{ 0, 0, 0, 1, 1, 1, 2, 2, 2 },
			{ 3, 3, 3, 4, 4, 4, 5, 5, 5 },
			{ 3, 3, 3, 4, 4, 4, 5, 5, 5 },
			{ 3, 3, 3, 4, 4, 4, 5, 5, 5 },
			{ 6, 6, 6, 7, 7, 7, 8, 8, 8 },
			{ 6, 6, 6, 7, 7, 7, 8, 8, 8 },
			{ 6, 6, 6, 7, 7, 7, 8, 8, 8 } };

	public boolean isValidSudoku(char[][] board)
	{
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (board[i][j] != '.')
				{
					int nb = board[i][j] - '1';
					if (rowocc[i][nb])
						return false;
					rowocc[i][nb] = true;
					if (colocc[j][nb])
						return false;
					colocc[j][nb] = true;
					if (bloocc[blk[i][j]][nb])
						return false;
					bloocc[blk[i][j]][nb] = true;
				}
		return true;
	}
}

//t37.Sudoku Solver
//t=4ms, o=27MB
//t:0.9825, o:0.4011
class Solution37
{
	boolean rowocc[][] = new boolean[9][];// whether row i has number j
	boolean colocc[][] = new boolean[9][];// whether column i has number j
	boolean bloocc[][] = new boolean[9][];// whether block i has number j
	boolean toSolve[][] = new boolean[9][];// whether i,j need to fill in

	public boolean dfs37(char[][] board, int x, int y)
	{
		if (y > 8)
		{
			y = 0;
			x++;
		}
		if (x > 8)
		{
			return true;
		}
		if (!toSolve[x][y])
		{
			return dfs37(board, x, y + 1);
		}
		for (int i = 0; i < 9; i++)
		{
			if (!rowocc[x][i] && !colocc[y][i] && !bloocc[getBlockNum(x, y)][i])
			{
				board[x][y] = (char) (i + '1');
				rowocc[x][i] = true;
				colocc[y][i] = true;
				bloocc[getBlockNum(x, y)][i] = true;
				if (dfs37(board, x, y + 1))
				{
					return true;
				}
				board[x][y] = '.';
				rowocc[x][i] = false;
				colocc[y][i] = false;
				bloocc[getBlockNum(x, y)][i] = false;
			}
		}
		return false;
	}

	public static int getBlockNum(int x, int y)
	{
		return 3 * (x / 3) + (y / 3);
	}

	public void solveSudoku(char[][] board)
	{
		// initialize occupation position
		for (int i = 0; i < 9; i++)
		{
			rowocc[i] = new boolean[9];
			colocc[i] = new boolean[9];
			bloocc[i] = new boolean[9];
			toSolve[i] = new boolean[9];
			for (int j = 0; j < 9; j++)
			{
				rowocc[i][j] = false;
				colocc[i][j] = false;
				bloocc[i][j] = false;
				toSolve[i][j] = false;
			}
		}
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				if (board[i][j] == '.')
				{
					toSolve[i][j] = true;
				} else
				{
					rowocc[i][board[i][j] - '1'] = true;
					colocc[j][board[i][j] - '1'] = true;
					bloocc[getBlockNum(i, j)][board[i][j] - '1'] = true;
				}
			}
		}
		dfs37(board, 0, 0);
	}
}

//37end
//t38. Count and Say
//t=6ms, m=37MB
//t:0.3476, m:1
class Solution38
{
	char[][] ws = new char[30][];

	public String countAndSay(int n)
	{
		ws[0] = "1".toCharArray();
		ws[1] = "11".toCharArray();
		ws[2] = "21".toCharArray();
		ws[3] = "1211".toCharArray();
		ws[4] = "111221".toCharArray();
		for (int i = 5; i < n; i++)
		{
			List<Character> typelist = new ArrayList<Character>();
			List<Integer> count = new ArrayList<Integer>();
			int rp = 0;
			while (rp < ws[i - 1].length)
			{
				int ep = rp + 1;
				while (ep < ws[i - 1].length && ws[i - 1][ep] == ws[i - 1][rp])
					ep++;
				typelist.add(ws[i - 1][rp]);
				count.add(ep - rp);
				rp = ep;
			}
			int[] stack = new int[(int) (Math.round(Math.log10(ws[i - 1].length)) + 2)];
			Queue<Character> Q = new LinkedList<Character>();
			for (int j = 0; j < typelist.size(); j++)
			{
				int ct = count.get(j), sp = 0;
				while (ct > 0)
				{
					stack[sp++] = ct % 10;
					ct = ct / 10;
				}
				while (sp > 0)
					Q.add((char) (stack[--sp] + 48));
				Q.add((char) typelist.get(j));
			}
			ws[i] = new char[Q.size()];
			for (int j = 0; j < ws[i].length; j++)
				ws[i][j] = Q.remove();
		}

		return new String(ws[n - 1]);
	}
}

//t39. Combination Sum
//t=8ms, m=39MB
//t:0.9898, m:1
class Solution39
{
	int[] can;
	int[] stack;
	List<List<Integer>> ans = new ArrayList<List<Integer>>();

	void dfs(int sp, int tar)
	{
		if (tar == 0)
		{
			List<Integer> na = new ArrayList<Integer>();
			for (int i = 0; i < can.length; i++)

				for (int j = 0; j < stack[i]; j++)
					na.add(can[i]);

			ans.add(na);
			return;
		}
		if (sp >= can.length)
			return;
		for (int i = tar / can[sp]; i >= 0; i--)
		{
			stack[sp] = i;
			dfs(sp + 1, tar - i * can[sp]);
		}
	}

	public List<List<Integer>> combinationSum(int[] candidates, int target)
	{
		can = candidates;
		stack = new int[can.length];
		dfs(0, target);
		return ans;
	}
}

//40. Combination Sum II
//Runtime: 8 ms, faster than 98.43% of Java online submissions for Combination Sum II.
//Memory Usage: 39.6 MB, less than 50.50% of Java online submissions for Combination Sum II.
class Solution40
{
	List<List<Integer>> ans;
	int[] ns, ct, stack;
	int nums;

	void analyze(int[] candidates)
	{
		ns = new int[candidates.length];
		ct = new int[candidates.length];
		int i = 0;
		nums = 0;
		while (i < candidates.length)
		{
			int j = i + 1;
			while (j < candidates.length && candidates[j] == candidates[i])
				j++;
			ns[nums] = candidates[i];
			ct[nums] = j - i;
			i = j;
			nums++;
		}
	}

	void dfs(int sp, int partsum, int target)
	{

		if (partsum > target)
			return;
		if (partsum == target)
		{
			List<Integer> t = new ArrayList<Integer>();
			for (int i = 0; i < sp; i++)
				for (int j = 0; j < stack[i]; j++)
					t.add(ns[i]);
			ans.add(t);
			return;
		}
		if (sp >= nums)
			return;
		for (int i = 0; i <= ct[sp]; i++)
		{
			stack[sp] = i;
			dfs(sp + 1, partsum, target);
			partsum += ns[sp];
		}
	}

	public List<List<Integer>> combinationSum2(int[] candidates, int target)
	{
		Arrays.parallelSort(candidates);
		analyze(candidates);
		ans = new ArrayList<List<Integer>>();
		stack = new int[nums];
		dfs(0, 0, target);
		return ans;
	}
}

public class LC31_40
{

	public static void main(String[] agrs)
	{
		System.out.println(new Solution33().search(new int[]
		{ 5, 1, 2, 3, 4 }, 1));

	}

}
