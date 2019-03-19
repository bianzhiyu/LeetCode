package lc541_550;

//541. Reverse String II
//Runtime: 5 ms, faster than 36.97% of Java online submissions for Reverse String II.
//Memory Usage: 39.8 MB, less than 5.66% of Java online submissions for Reverse String II.
class Solution541
{
	public String reverseStr(String s, int k)
	{
		StringBuilder sb = new StringBuilder();
		int n = s.length();
		for (int i = 0; i < n; i += 2 * k)
		{
			int up = Math.min(i + k, n);
			for (int j = up - 1; j >= i; j--)
				sb.append(s.charAt(j));
			int up2 = Math.min(i + k + k, n);
			for (int j = up; j < up2; j++)
				sb.append(s.charAt(j));
		}
		return sb.toString();
	}
}

//Runtime: 5 ms, faster than 36.97% of Java online submissions for Reverse String II.
//Memory Usage: 39.1 MB, less than 84.91% of Java online submissions for Reverse String II.
class Solution541_2
{
	public String reverseStr(String s, int k)
	{
		char[] ss = s.toCharArray();
		StringBuilder sb = new StringBuilder();
		int n = s.length();
		for (int i = 0; i < n; i += 2 * k)
		{
			int up = Math.min(i + k, n);
			for (int j = up - 1; j >= i; j--)
				sb.append(ss[j]);
			int up2 = Math.min(i + k + k, n);
			for (int j = up; j < up2; j++)
				sb.append(ss[j]);
		}
		return sb.toString();
	}
}

//542. 01 Matrix
//Runtime: 474 ms, faster than 7.01% of Java online submissions for 01 Matrix.
//Memory Usage: 58.9 MB, less than 9.77% of Java online submissions for 01 Matrix.
class Solution542
{
	boolean[][] used;
	int[][] q;
	int[] mindist;
	final static int[][] di =
		{
				{ 1, 0 },
				{ 0, 1 },
				{ -1, 0 },
				{ 0, -1 } };

	void initbfs()
	{
		for (int i = 0; i < used.length; i++)
			for (int j = 0; j < used[0].length; j++)
				used[i][j] = false;
	}

	void bfs(int[][] m, int[][] a, int x, int y)
	{
		initbfs();
		int h = 0, r = 1;
		q[h][0] = x;
		q[h][1] = y;
		mindist[0] = 0;
		used[x][y] = true;
		while (h < r)
		{
			for (int i = 0; i < 4; i++)
			{
				int nx = q[h][0] + di[i][0], ny = q[h][1] + di[i][1];
				if (nx >= 0 && nx < a.length && ny >= 0 && ny < a[0].length && !used[nx][ny])
				{
					q[r][0] = nx;
					q[r][1] = ny;
					used[nx][ny] = true;
					mindist[r] = mindist[h] + 1;
					if (m[nx][ny] == 0)
					{
						a[x][y] = mindist[r];
						return;
					}
					r++;
				}
			}
			h++;
		}
	}

	public int[][] updateMatrix(int[][] matrix)
	{
		int[][] a = new int[matrix.length][matrix[0].length];
		q = new int[matrix.length * matrix[0].length][2];
		used = new boolean[matrix.length][matrix[0].length];
		mindist = new int[q.length];

		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[0].length; j++)
				if (matrix[i][j] == 0)
					a[i][j] = 0;
				else
					bfs(matrix, a, i, j);

		return a;
	}
}

//547. Friend Circles
//Runtime: 4 ms, faster than 100.00% of Java online submissions for Friend Circles.
//Memory Usage: 46.5 MB, less than 12.23% of Java online submissions for Friend Circles.
class Solution547
{
	public int findCircleNum(int[][] M)
	{
		int n=M.length;
		boolean[] u=new boolean[n];
		int[]q=new int[n];
		int ct=0;
		for (int i=0;i<n;i++)
			if (!u[i])
			{
				int f=0,r=1;
				q[0]=i;
				u[i]=true;
				while (f<r)
				{
					for (int j=0;j<n;j++)
						if (M[q[f]][j]==1 && !u[j])
						{
							q[r]=j;
							u[j]=true;
							r++;
						}
					f++;
				}
				ct++;
			}
		return ct;
	}
}

public class LC541_550
{
	public static void main(String[] args)
	{

	}

}
