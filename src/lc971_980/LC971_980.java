package lc971_980;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import bbst.BBST;
import heap.Heap;
import treeCodec.*;

//971. Flip Binary Tree To Match Preorder Traversal
//still wrong
class Solution971
{
	private List<Integer> ans=new ArrayList<Integer>();
	private static class State
	{
		int end=0;
	}
	private void flip(TreeNode rt)
	{
		TreeNode t=rt.left;
		rt.left=rt.right;
		rt.right=t;
		ans.add(rt.val);
	}
	private boolean trav(TreeNode rt,int[] v,int start,State stat)
	{
		if (rt==null) return true;
		if (start>=v.length) return false;
		if (rt.val!=v[start]) return false;
		stat.end++;
		start++;
		if (rt.left!=null)
		{
			if (start>=v.length) return false;
			if (v[start]!=rt.left.val) flip(rt);
			if (rt.left==null || v[start]!=rt.left.val) return false;
			if (!trav(rt.left,v,start,stat)) return false;
		}
//		else {}
		if (rt.right!=null)
		{
			start=stat.end;
			if (start+1>=v.length) return false;
			if (v[start+1]!=rt.left.val) return false;
			if (!trav(rt.left,v,start+1,stat)) return false;
		}
//		else {}
		return true;
	}
	public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage)
	{
		if (!trav(root,voyage,0,new State()))
		{
			ans=new ArrayList<Integer>();
			ans.add(-1);
			return ans;
		}
		return ans;
	}
}

//973. K Closest Points to Origin
//Runtime: 58 ms, faster than 41.04% of Java online submissions for K Closest Points to Origin.
//Memory Usage: 65.8 MB, less than 7.84% of Java online submissions for K Closest Points to Origin.
class Point implements Comparable<Point>
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

class Solution973
{
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

//980. Unique Paths III
//Runtime: 2 ms, faster than 100.00% of Java online submissions for Unique Paths III.
//Memory Usage: 36.7 MB, less than 90.83% of Java online submissions for Unique Paths III.
class Solution980
{
	int[] start = new int[2];
	int[] end = new int[2];
	int m, n, num = 0, zeros = 0;
	boolean[][] used;
	int[][] map;
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
	public static void main(String[] args)
	{
		int[][] g = new int[][]
		{
				{ 1, 0, 0, 0 },
				{ 0, 0, 0, 0 },
				{ 0, 0, 2, -1 } };
		System.out.println(new Solution980().uniquePathsIII(g));
	}
}
