package lc971_980;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import bbst.BBST;
import heap.Heap;
import treeCodec.*;

//971. Flip Binary Tree To Match Preorder Traversal
//Runtime: 1 ms, faster than 94.76% of Java online submissions for Flip Binary Tree To Match Preorder Traversal.
//Memory Usage: 38.7 MB, less than 5.09% of Java online submissions for Flip Binary Tree To Match Preorder Traversal.
class Solution971
{
	private List<Integer> ans = new ArrayList<Integer>();

	private static class State
	{
		private int rpos = 0;
	}

	private void flip(TreeNode rt)
	{
		TreeNode t = rt.left;
		rt.left = rt.right;
		rt.right = t;
		ans.add(rt.val);
	}

	private boolean trav(TreeNode rt, int[] v, State stat)
	{
		if (rt == null)
			return true;
		int p = stat.rpos;
		if (p >= v.length)
			return false;
		if (rt.val != v[p])
			return false;
		stat.rpos++;
		p++;
		if (rt.left != null)
		{
			if (p >= v.length)
				return false;
			if (rt.left.val != v[p])
				flip(rt);
		}
		if (!trav(rt.left, v, stat))
			return false;
		if (!trav(rt.right, v, stat))
			return false;
		return true;
	}

	public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage)
	{
		if (!trav(root, voyage, new State()))
		{
			ans = new ArrayList<Integer>();
			ans.add(-1);
			return ans;
		}
		return ans;
	}
}

//972. Equal Rational Numbers
//Runtime: 2 ms, faster than 97.39% of Java online submissions for Equal Rational Numbers.
//Memory Usage: 35.5 MB, less than 87.50% of Java online submissions for Equal Rational Numbers.
class Solution972
{
	public static class Token
	{
		private int i, u, d;

		public Token(int _i, int _u, int _d)
		{
			i = _i;
			u = _u;
			d = _d;
		}

		public void simp()
		{
			int x = gcd(u, d);
			u /= x;
			d /= x;
		}

		public Token add(Token an)
		{
			Token ans = new Token(0, 0, 1);
			ans.i = i + an.i;
			int m = d / gcd(d, an.d) * an.d;
			ans.d = m;
			ans.u = u * (m / d) + an.u * (m / an.d);
			ans.simp();
			while (ans.u >= ans.d)
			{
				ans.i++;
				ans.u -= ans.d;
			}
			return ans;
		}

		public boolean equal(Token an)
		{
			if (i != an.i)
				return false;
			int m = d / gcd(d, an.d) * an.d;
			return u * (m / d) == an.u * (m / an.d);
		}

		public String toString()
		{
			return "" + i + ("(" + u + "/" + d + ")");
		}
	}

	public static int gcd(int a, int b)
	{
		return b == 0 ? a : gcd(b, a % b);
	}

	public static Token parse(String s)
	{
		if (s.indexOf('.') == -1)
			return new Token(Integer.parseInt(s), 0, 1);
		if (s.indexOf('.') == s.length() - 1)
			return new Token(Integer.parseInt(s.substring(0, s.length() - 1)), 0, 1);
		String[] d = s.split("\\.");
		Token a = new Token(Integer.parseInt(d[0]), 0, 1);
		if (d[1].indexOf('(') == -1)
		{
			a.u = Integer.parseInt(d[1]);
			a.d = 1;
			for (int i = 1; i <= d[1].length(); i++)
				a.d *= 10;
			a.simp();
			return a;
		} else
		{
			int p = d[1].indexOf('(');
			Token part1 = new Token(0, 0, 1), part2 = new Token(0, 0, 1);
			if (p == -1)
			{
				part1.u = Integer.parseInt(d[1]);
				for (int i = 1; i <= d[1].length(); i++)
					part1.d *= 10;
				part1.simp();
				return a.add(part1);
			} else
			{
				String l = d[1].substring(p + 1, d[1].length() - 1);
				part2.u = Integer.parseInt(l);
				part2.d = 0;
				for (int i = 1; i <= l.length(); i++)
					part2.d = part2.d * 10 + 9;
				for (int i = 0; i < p; i++)
					part2.d *= 10;
				l = d[1].substring(0, p);
				if (p != 0)
				{
					part1.u = Integer.parseInt(l);
					part1.d = 1;
					for (int i = 1; i <= l.length(); i++)
						part1.d *= 10;
					part1.simp();
				}
				part2.simp();
				return part1.add(part2).add(a);
			}
		}
	}

	public boolean isRationalEqual(String S, String T)
	{
		return parse(S).equal(parse(T));
	}
}

//973. K Closest Points to Origin
//Runtime: 58 ms, faster than 41.04% of Java online submissions for K Closest Points to Origin.
//Memory Usage: 65.8 MB, less than 7.84% of Java online submissions for K Closest Points to Origin.
class Solution973
{
	private static class Point implements Comparable<Point>
	{
		int x, y;

		Point(int _x, int _y)
		{
			x = _x;
			y = _y;
		}

		@Override
		public int compareTo(Point o)
		{
			return x * x + y * y - o.x * o.x - o.y * o.y;
		}
	}

	public int[][] kClosest(int[][] points, int K)
	{
		BBST<Point> rt = new BBST<Point>(new Point(points[0][0], points[0][1]));
		for (int i = 1; i < points.length; i++)
			rt = rt.insert(new Point(points[i][0], points[i][1]));
		int[][] ans = new int[K][2];
		for (int i = 0; i < K; i++)
		{
			Point p = rt.getMinData();
			rt = rt.removeMin();
			ans[i][0] = p.x;
			ans[i][1] = p.y;
		}
		return ans;
	}
}

//Runtime: 38 ms, faster than 55.82% of Java online submissions for K Closest Points to Origin.
//Memory Usage: 62.9 MB, less than 23.87% of Java online submissions for K Closest Points to Origin.
class Solution973_2
{
	private static class Point implements Comparable<Point>
	{
		int x, y;

		Point(int _x, int _y)
		{
			x = _x;
			y = _y;
		}

		@Override
		public int compareTo(Point o)
		{
			return x * x + y * y - o.x * o.x - o.y * o.y;
		}
	}

	public int[][] kClosest(int[][] points, int K)
	{
		BBST<Point> rt = new BBST<Point>(new Point(points[0][0], points[0][1]));
		for (int i = 1; i < points.length; i++)
		{
			rt = rt.insert(new Point(points[i][0], points[i][1]));
			if (i > K)
				rt = rt.removeMax();
		}
		int[][] ans = new int[K][2];
		for (int i = 0; i < K; i++)
		{
			Point p = rt.getMinData();
			rt = rt.removeMin();
			ans[i][0] = p.x;
			ans[i][1] = p.y;
		}
		return ans;
	}
}

//https://leetcode.com/problems/k-closest-points-to-origin/discuss/220235/Java-Three-solutions-to-this-classical-K-th-problem.
//She gives three solutions

//Runtime: 56 ms, faster than 42.90% of Java online submissions for K Closest Points to Origin.
//Memory Usage: 54.2 MB, less than 92.75% of Java online submissions for K Closest Points to Origin.
class Solution973_3
{
	public int[][] kClosest(int[][] points, int K)
	{
		Arrays.sort(points, (p1, p2) -> p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1]);
		return Arrays.copyOfRange(points, 0, K);
	}
}

//Runtime: 68 ms, faster than 32.58% of Java online submissions for K Closest Points to Origin.
//Memory Usage: 60.9 MB, less than 47.25% of Java online submissions for K Closest Points to Origin.
class Solution973_4
{
	public int[][] kClosest(int[][] points, int K)
	{
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
				(p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);
		for (int[] p : points)
		{
			pq.offer(p);
			if (pq.size() > K)
			{
				pq.poll();
			}
		}
		int[][] res = new int[K][2];
		while (K > 0)
		{
			res[--K] = pq.poll();
		}
		return res;
	}
}

//Based on quick sort alg
//Runtime: 8 ms, faster than 96.34% of Java online submissions for K Closest Points to Origin.
//Memory Usage: 60.9 MB, less than 47.54% of Java online submissions for K Closest Points to Origin.
class Solution973_5
{
	public int[][] kClosest(int[][] points, int K)
	{
		int len = points.length, l = 0, r = len - 1;
		while (l <= r)
		{
			int mid = helper(points, l, r);
			if (mid == K)
				break;
			if (mid < K)
			{
				l = mid + 1;
			} else
			{
				r = mid - 1;
			}
		}
		return Arrays.copyOfRange(points, 0, K);
	}

	private int helper(int[][] A, int l, int r)
	{
		int[] pivot = A[l];
		while (l < r)
		{
			while (l < r && compare(A[r], pivot) >= 0)
				r--;
			A[l] = A[r];
			while (l < r && compare(A[l], pivot) <= 0)
				l++;
			A[r] = A[l];
		}
		A[l] = pivot;
		return l;
	}

	private int compare(int[] p1, int[] p2)
	{
		return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
	}
}

//test self implementes heap
//Runtime: 98 ms, faster than 7.82% of Java online submissions for K Closest Points to Origin.
//Memory Usage: 61 MB, less than 42.04% of Java online submissions for K Closest Points to Origin.
class Solution973_6
{
	public int[][] kClosest(int[][] points, int K)
	{
		Heap<int[]> pq = new Heap<int[]>((p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);
		for (int[] p : points)
		{
			pq.offer(p);
			if (pq.size() > K)
			{
				pq.poll();
			}
		}
		int[][] res = new int[K][2];
		while (K > 0)
		{
			res[--K] = pq.poll();
		}
		return res;
	}
}

//974. Subarray Sums Divisible by K
//Runtime: 6622 ms, faster than 23.00% of Java online submissions for Subarray Sums Divisible by K.
//Memory Usage: 42 MB, less than 69.75% of Java online submissions for Subarray Sums Divisible by K.
class Solution974
{
	public int subarraysDivByK(int[] A, int K)
	{
		int tot = 0;
		for (int i = 0; i < A.length; i++)
		{
			int t = 0;
			for (int j = i; j < A.length; j++)
			{
				t += A[j];
				if (t % K == 0)
					tot++;
			}
		}
		return tot;
	}
}
//https://leetcode.com/problems/subarray-sums-divisible-by-k/discuss/237010/99-space%2Btime(6ms)-Java-solution
//Runtime: 7 ms, faster than 92.02% of Java online submissions for Subarray Sums Divisible by K.
//Memory Usage: 43.8 MB, less than 29.41% of Java online submissions for Subarray Sums Divisible by K.
//at i=A[p],
//before sentence sum+=i,
//residualclass[j]= { k; 0<=k<i, sum_{l=0,...,k}A[l] %K ==j }, j=0,...,K-1.

class Solution974_2
{
	public int subarraysDivByK(int[] A, int K)
	{
		int[] residualclass = new int[K];
		residualclass[0] = 1;
		int sum = 0, result = 0;
		for (int i : A)
		{
			sum += i;
			int mod = (sum % K + K) % K;
			result += residualclass[mod];
			residualclass[mod]++;
		}
		return result;
	}
}

//978. Longest Turbulent Subarray
//Runtime: 5 ms, faster than 98.64% of Java online submissions for Longest Turbulent Subarray.
//Memory Usage: 47.5 MB, less than 6.17% of Java online submissions for Longest Turbulent Subarray.
class Solution978
{
	public int maxTurbulenceSize(int[] A)
	{
		int len = A.length;
		int[] leftUp = new int[len];
		int[] leftDn = new int[len];
		leftUp[0] = 1;
		leftDn[0] = 1;
		int max = 1;
		for (int i = 1; i < len; i++)
		{
			if (A[i - 1] == A[i])
			{
				leftUp[i] = 1;
				leftDn[i] = 1;
			} else if (A[i - 1] > A[i])
			{
				leftUp[i] = leftDn[i - 1] + 1;
				leftDn[i] = 1;
			} else// A[i-1]<A[i]
			{
				leftUp[i] = 1;
				leftDn[i] = leftUp[i - 1] + 1;
			}
			if (leftUp[i] > max)
				max = leftUp[i];
			if (leftDn[i] > max)
				max = leftDn[i];
		}
		return max;
	}
}

//979. Distribute Coins in Binary Tree
//Runtime: 1 ms, faster than 91.76% of Java online submissions for Distribute Coins in Binary Tree.
//Memory Usage: 38.8 MB, less than 6.67% of Java online submissions for Distribute Coins in Binary Tree.
class Solution979
{
	private static class Rt
	{
		private int amount, rem, times;

		public Rt(int a, int d, int t)
		{
			amount = a;
			rem = d;
			times = t;
		}
	}

	private Rt trav(TreeNode rt)
	{
		if (rt == null)
			return new Rt(0, 0, 0);
		Rt l = trav(rt.left), r = trav(rt.right);
		Rt t = new Rt(l.amount + r.amount + 1, l.rem + r.rem + rt.val - 1, 0);
		t.times = l.times + r.times + Math.abs(l.rem) + Math.abs(r.rem);
		return t;
	}

	public int distributeCoins(TreeNode root)
	{
		return trav(root).times;
	}
}

//980. Unique Paths III
//Runtime: 2 ms, faster than 100.00% of Java online submissions for Unique Paths III.
//Memory Usage: 36.7 MB, less than 90.83% of Java online submissions for Unique Paths III.
class Solution980
{
	private int[] start = new int[2];
	private int[] end = new int[2];
	private int m, n, num = 0, zeros = 0;
	private boolean[][] used;
	private int[][] map;
	final static int[][] di = new int[][]
	{
			{ 1, 0 },
			{ 0, 1 },
			{ -1, 0 },
			{ 0, -1 } };

	void dfs(int x, int y)
	{
		for (int i = 0; i < 4; i++)
		{
			int nx = x + di[i][0], ny = y + di[i][1];
			if (nx >= 0 && ny >= 0 && nx < m && ny < n && !used[nx][ny] && map[nx][ny] != -1)
			{
				if (map[nx][ny] == 2 && zeros == 0)
				{
					num++;
				} else if (map[nx][ny] == 0)
				{
					used[nx][ny] = true;
					zeros--;
					dfs(nx, ny);
					used[nx][ny] = false;
					zeros++;
				}
			}
		}
	}

	public int uniquePathsIII(int[][] grid)
	{
		map = grid;
		m = grid.length;
		n = grid[0].length;
		used = new boolean[m][n];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				if (grid[i][j] == 1)
				{
					start[0] = i;
					start[1] = j;
				} else if (grid[i][j] == 2)
				{
					end[0] = i;
					end[1] = j;
				} else if (grid[i][j] == 0)
					zeros++;
		used[start[0]][start[1]] = true;
		dfs(start[0], start[1]);
		return num;
	}
}

public class LC971_980
{
	public static void test980()
	{
		int[][] g = new int[][]
		{
				{ 1, 0, 0, 0 },
				{ 0, 0, 0, 0 },
				{ 0, 0, 2, -1 } };
		System.out.println(new Solution980().uniquePathsIII(g));
	}

	public static void test972()
	{
		String s1 = "0.(52)";
		String s2 = "0.5(25)";
		System.out.println(Solution972.parse(s1));
		System.out.println(Solution972.parse(s2));
		Solution972 sl = new Solution972();
		System.out.println(sl.isRationalEqual(s1, s2));
	}

	public static void main(String[] args)
	{
		test972();
	}
}
