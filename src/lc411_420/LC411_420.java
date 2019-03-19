package lc411_420;

import java.util.ArrayList;
import java.util.List;

//413. Arithmetic Slices
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Arithmetic Slices.
//Memory Usage: 37.4 MB, less than 36.76% of Java online submissions for Arithmetic Slices.
class Solution413
{
	public int numberOfArithmeticSlices(int[] A)
	{
		int ct = 0, len = A.length;
		int i = 0;
		while (i < len - 1)
		{
			int k = i + 2;
			int d = A[i + 1] - A[i];
			while (k < len && A[k] - A[k - 1] == d)
			{
				k++;
			}
			if (k == i + 2)
			{
				i++;
			} else
			{
				ct += (k - i - 2) * (k - i - 1) / 2;
				i = k - 1;
			}
		}
		return ct;
	}
}

//416. Partition Equal Subset Sum
//Runtime: 15 ms, faster than 69.92% of Java online submissions for Partition Equal Subset Sum.
//Memory Usage: 40.3 MB, less than 6.31% of Java online submissions for Partition Equal Subset Sum.
class Solution416
{
	public boolean canPartition(int[] nums)
	{
		int s = 0;
		for (int i = 0; i < nums.length; i++)
			s += nums[i];
		if (s % 2 == 1)
			return false;
		else
			s /= 2;
		boolean[] d = new boolean[s + 1];
		d[0] = true;
		for (int i = 0; i < nums.length; i++)
			for (int j = s; j >= nums[i]; j--)
				d[j] = d[j] || d[j - nums[i]];
		return d[s];
	}
}

//417. Pacific Atlantic Water Flow
//Runtime: 13 ms, faster than 45.02% of Java online submissions for Pacific Atlantic Water Flow.
//Memory Usage: 43 MB, less than 66.67% of Java online submissions for Pacific Atlantic Water Flow.
class Solution417
{
	final static int[][] di = new int[][]
	{
			{ 0, 1 },
			{ 1, 0 },
			{ 0, -1 },
			{ -1, 0 } };

	public List<int[]> pacificAtlantic(int[][] matrix)
	{
		List<int[]> ans = new ArrayList<int[]>();
		if (matrix.length == 0 || matrix[0].length == 0)
			return ans;
		int m = matrix.length, n = matrix[0].length;
		boolean[][] u1 = new boolean[m][n], u2 = new boolean[m][n];
		int[][] q = new int[m * n + 2][2];

		int f = 0, r = 0;
		for (int i = 0; i < m; i++)
		{
			q[r][0] = i;
			q[r][1] = 0;
			r++;
			u1[i][0] = true;
		}
		for (int i = 1; i < n; i++)
		{
			q[r][0] = 0;
			q[r][1] = i;
			r++;
			u1[0][i] = true;
		}
		bfs(matrix, m, n, u1, f, r, q);
		f = 0;
		r = 0;
		for (int i = 0; i < m; i++)
		{
			q[r][0] = i;
			q[r][1] = n - 1;
			r++;
			u2[i][n - 1] = true;
		}
		for (int i = 0; i < n - 1; i++)
		{
			q[r][0] = m - 1;
			q[r][1] = i;
			r++;
			u2[m - 1][i] = true;
		}
		bfs(matrix, m, n, u2, f, r, q);
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				if (u1[i][j] && u2[i][j])
					ans.add(new int[]
					{ i, j });
		return ans;
	}

	void bfs(int[][] mat, int m, int n, boolean[][] u, int f, int r, int[][] q)
	{
		while (f < r)
		{
			int nx = q[f][0], ny = q[f][1];
			f++;
			for (int i = 0; i < 4; i++)
			{
				int tx = nx + di[i][0], ty = ny + di[i][1];
				if (tx >= 0 && ty >= 0 && tx < m && ty < n && !u[tx][ty] && mat[tx][ty] >= mat[nx][ny])
				{
					q[r][0] = tx;
					q[r][1] = ty;
					r++;
					u[tx][ty] = true;
				}
			}
		}
	}
}

//419. Battleships in a Board
//Runtime: 5 ms, faster than 6.69% of Java online submissions for Battleships in a Board.
//Memory Usage: 43.6 MB, less than 5.00% of Java online submissions for Battleships in a Board.

class Solution419
{
	final static int[][] di=new int[][] {{1,0},{0,1},{-1,0},{0,-1}};
	public int countBattleships(char[][] board)
	{
		if (board.length==0 || board[0].length==0) return 0;
		int m=board.length,n=board[0].length;
		int[][]q=new int[m*n+1][2];
		int f,r,ct=0;
		boolean[][] checked=new boolean[m][n];
		for (int i=0;i<m;i++)
			for (int j=0;j<n;j++)
				if (!checked[i][j] && board[i][j]=='X')
				{
					f=0;
					r=1;
					q[0][0]=i;
					q[0][1]=j;
					while(f<r)
					{
						for (int k=0;k<4;k++)
						{
							int nx=q[f][0]+di[k][0];
							int ny=q[f][1]+di[k][1];
							if (nx>=0 && ny>=0 && nx<m && ny<n && !checked[nx][ny] 
									&& board[nx][ny]=='X')
							{
								q[r][0]=nx;
								q[r][1]=ny;
								checked[nx][ny]=true;
								r++;
							}
						}
						f++;
					}
					if (r==1) ct++;
					else
					{
						int diffKind0=0,diffKind1=0;
						for (int k=1;k<r;k++)
						{
							if (q[k][0]-q[0][0]!=0) diffKind0=1;
							if (q[k][1]-q[0][1]!=0) diffKind1=1;
						}
						if (diffKind0==0 || diffKind1==0) ct++;
					}
				}
		return ct;
	}
}

public class LC411_420
{
	public static void main(String[] agrs)
	{

	}
}
