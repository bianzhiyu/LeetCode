package lc571_580;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import treeCodec.*;

//572. Subtree of Another Tree
//Runtime: 6 ms, faster than 85.88% of Java online submissions for Subtree of Another Tree.
//Memory Usage: 40 MB, less than 99.21% of Java online submissions for Subtree of Another Tree.
class Solution572
{
	private boolean checkSubtree(TreeNode s, TreeNode t)
	{
		if (t == null)
			return s == null;
		return s != null && checkSubtree(s.left, t.left) && checkSubtree(s.right, t.right) && s.val == t.val;
	}

	public boolean isSubtree(TreeNode s, TreeNode t)
	{
		if (t == null)
			return true;
		if (s == null)
			return false;
		return checkSubtree(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
	}
}

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
	public static void test572()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input572.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output572.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				TreeNode s = TreeCodec.deserialize(inLine);
				TreeNode t = TreeCodec.deserialize(bfr.readLine());

				boolean ans = new Solution572().isSubtree(s, t);

				System.out.println(ans);

				bfw.write("" + ans);
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

	public static void test576()
	{
		Solution576 s = new Solution576();
		System.out.println(s.findPaths(2, 2, 2, 0, 0));
		System.out.println(s.findPaths(8, 50, 23, 5, 26));
		System.out.println(s.findPaths(1, 3, 3, 0, 1));
	}

	public static void main(String[] args)
	{
		test572();
	}
}
