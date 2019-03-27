package lc571_580;

//576. Out of Boundary Paths
//Runtime: 12 ms, faster than 60.96% of Java online submissions for Out of Boundary Paths.
//Memory Usage: 33.6 MB, less than 100.00% of Java online submissions for Out of Boundary Paths.
class Solution576
{
	private final static int MODULO = 1000000000 + 7;
	private final static int[][] di = new int[][]
	{
			{ 1, 0 },
			{ 0, 1 },
			{ -1, 0 },
			{ 0, -1 } };

	public int findPaths(int m, int n, int N, int i, int j)
	{
		int[][][] d = new int[m][n][N];
		if (N == 0)
			return 0;
		for (int k = 0; k < m; k++)
		{
			d[k][0][0]++;
			d[k][n - 1][0]++;
		}
		for (int k = 0; k < n; k++)
		{
			d[0][k][0]++;
			d[m - 1][k][0]++;
		}
		for (int step = 1; step < N; step++)
		{
			for (int r = 0; r < m; r++)
				for (int c = 0; c < n; c++)
				{
					d[r][c][step] = 0;
					for (int k = 0; k < 4; k++)
					{
						int nr = r + di[k][0];
						int nc = c + di[k][1];
						if (nr >= 0 && nc >= 0 && nr < m && nc < n)
						{
							d[r][c][step] += d[nr][nc][step - 1];
							d[r][c][step] %= MODULO;
						}

					}
				}
		}
		int sum = 0;
		for (int step = 0; step < N; step++)
			sum = (sum + d[i][j][step]) % MODULO;
		return sum;
	}
}

public class LC571_580
{
	public static void test576()
	{
		Solution576 s = new Solution576();
		System.out.println(s.findPaths(2, 2, 2, 0, 0));
		System.out.println(s.findPaths(8, 50, 23, 5, 26));
		System.out.println(s.findPaths(1, 3, 3, 0, 1));
	}

	public static void main(String[] args)
	{
		test576();
		int MODULO = 1000000000 + 7;
		MODULO = MODULO + MODULO;
//		System.out.println(MODULO);
	}
}
