package lc61_70;

import java.util.List;

//61. Rotate List
//Runtime: 6 ms, faster than 100.00% of Java online submissions for Rotate List.
//Memory Usage: 38.2 MB, less than 11.99% of Java online submissions for Rotate List.
class ListNode
{
	int val;
	ListNode next;

	ListNode(int x)
	{
		val = x;
	}
}

class Solution61
{
	public ListNode rotateRight(ListNode head, int k)
	{
		if (head == null)
			return head;
		ListNode p = head;
		int n = 1;
		while (p.next != null)
		{
			p = p.next;
			n++;
		}
		k = k % n;
		k = n - k;
		p = head;
		for (int i = 1; i < k; i++)
			p = p.next;
		ListNode q = p;
		while (q.next != null)
			q = q.next;
		q.next = head;
		q = p.next;
		p.next = null;
		return q;
	}
}

//62. Unique Paths
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
//Memory Usage: 34.4 MB, less than 100.00% of Java online submissions for Unique Paths.
class Solution62
{
	public int uniquePaths(int m, int n)
	{
		int[][] a = new int[m][n];
		for (int j = 0; j < n; j++)
			a[0][j] = 1;
		for (int i = 0; i < m; i++)
			a[i][0] = 1;
		for (int i = 1; i < m; i++)
			for (int j = 1; j < n; j++)
				a[i][j] = a[i - 1][j] + a[i][j - 1];
		return a[m - 1][n - 1];
	}
}

//Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths.
//Memory Usage: 34.5 MB, less than 100.00% of Java online submissions for Unique Paths.
class Solution62_2
{
	static long Comb(int X, int Y)
	{
		long t = 1, p = 2;
		for (int i = X - Y + 1; i <= X; i++)
		{
			t = t * i;
			if (p <= Y && t % p == 0)
				t /= p++;
		}
		return t;
	}

	public int uniquePaths(int m, int n)
	{
		m--;
		n--;
		if (m > n)
			return (int) Comb(m + n, n);
		else
			return (int) Comb(m + n, m);
	}
}

//63. Unique Paths II
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Paths II.
//Memory Usage: 36.9 MB, less than 100.00% of Java online submissions for Unique Paths II.
class Solution63
{
	public int uniquePathsWithObstacles(int[][] obstacleGrid)
	{
		int m = obstacleGrid.length, n = obstacleGrid[0].length;
		int[][] a = new int[m][n];
		a[0][0] = obstacleGrid[0][0] == 0 ? 1 : 0;
		for (int j = 1; j < n; j++)
			a[0][j] = obstacleGrid[0][j] == 0 ? a[0][j - 1] : 0;
		for (int i = 1; i < m; i++)
			a[i][0] = obstacleGrid[i][0] == 0 ? a[i - 1][0] : 0;
		for (int i = 1; i < m; i++)
			for (int j = 1; j < n; j++)
				a[i][j] = obstacleGrid[i][j] == 0 ? a[i - 1][j] + a[i][j - 1] : 0;
		return a[m - 1][n - 1];
	}
}

//64. Minimum Path Sum
//Runtime: 4 ms, faster than 97.51% of Java online submissions for Minimum Path Sum.
//Memory Usage: 47.1 MB, less than 5.10% of Java online submissions for Minimum Path Sum.
class Solution64
{
	public int minPathSum(int[][] grid)
	{
		for (int i = 1; i < grid[0].length; i++)
			grid[0][i] += grid[0][i - 1];
		for (int i = 1; i < grid.length; i++)
		{
			grid[i][0] += grid[i - 1][0];
			for (int j = 1; j < grid[i].length; j++)
				grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
		}
		return grid[grid.length - 1][grid[0].length - 1];

	}
}

//t66. Plus One
//t=0ms, m=34.8MB
//t:1, m:1
class Solution66
{
	public int[] plusOne(int[] digits)
	{
		boolean all9 = true;
		for (int i = 0; i < digits.length; i++)
			if (digits[i] != 9)
			{
				all9 = false;
				break;
			}

		int[] a = new int[all9 ? digits.length + 1 : digits.length];
		int shift = all9 ? 1 : 0;
		for (int i = digits.length - 1; i >= 0; i--)
			a[i + shift] = digits[i];
		a[a.length - 1]++;
		for (int i = a.length - 1; i > 0; i--)
			if (a[i] > 9)
			{
				a[i - 1] = a[i - 1] + a[i] / 10;
				a[i] = a[i] % 10;
			}
		return a;
	}
}

class BigIntArith
{
	public static int[] toIntArr(String a)
	{
		int[] b = new int[a.length()];
		for (int i = 0; i < b.length; i++)
			b[i] = (int) a.charAt(b.length - 1 - i) - 48;
		return b;
	}

	public static String plus(int[] a, int[] b, int base)
	{
		int[] c = new int[Math.max(a.length, b.length) + 2];
		for (int i = 0; i < c.length - 1; i++)
		{
			if (i < a.length)
				c[i] += a[i];
			if (i < b.length)
				c[i] += b[i];
			c[i + 1] += c[i] / base;
			c[i] = c[i] % base;
		}
		int l = c.length - 1;
		while (l > 0 && c[l] == 0)
			l--;
		if (l == 0 && c[0] == 0)
			return "0";
		char[] d = new char[l + 1];
		for (int i = 0; i <= l; i++)
			d[i] = (char) (c[l - i] + 48);
		return new String(d);
	}

	public static String addBinary(String a, String b)
	{
		int[] aa = toIntArr(a);
		int[] bb = toIntArr(b);
		return plus(aa, bb, 2);
	}
}

//t67. Add Binary
//t=2ms, m=37MB
//t:0.9587, m:1
class Solution67
{
	public String addBinary(String a, String b)
	{
		return BigIntArith.addBinary(a, b);
	}
}

//t68. Text Justification
//I don't wanna do this.
class Solution68
{
	public List<String> fullJustify(String[] words, int maxWidth)
	{
		return null;
	}
}

//t69. Sqrt(x)
//Runtime: 16 ms, faster than 90.05% of Java online submissions for Sqrt(x).
//Memory Usage: 38 MB, less than 100.00% of Java online submissions for Sqrt(x).
class Solution69
{
	int sch(int l, int r, int x)
	// l<=r, l^2<=x<=r^2
	{
		if (l == r)
			return l;
		if (r == l + 1)
		{
			if (((long) r) * r == (long) x)
				return r;
			return l;
		}
		int m = (int) ((long) l + r) / 2;
		if (((long) m) * m >= (long) x)
			return sch(l, m, x);
		return sch(m, r, x);
	}

	public int mySqrt(int x)
	{
		return sch(0, x, x);
	}
}

//70. Climbing Stairs
//Runtime: 2 ms, faster than 92.85% of Java online submissions for Climbing Stairs.
//Memory Usage: 36.3 MB, less than 100.00% of Java online submissions for Climbing Stairs.
class Solution70
{
	public int climbStairs(int n)
	{
		int[] f = new int[n + 1];
		f[0] = 1;
		f[1] = 1;
		for (int i = 2; i <= n; i++)
			f[i] = f[i - 1] + f[i - 2];
		return f[n];
	}
}

public class LC61_70
{
	public static void main(String[] args)
	{
		System.out.println(new Solution63().uniquePathsWithObstacles(new int[][]
		{
				{ 1, 0 } }));
	}

}
