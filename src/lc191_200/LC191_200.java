package lc191_200;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import treeCodec.*;

class Solution195
{
	public static void main(String args[]) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader(new File("file.txt")));
		for (int i = 0; i < 8; i++)
			in.readLine();
		System.out.println(in.readLine());
		in.close();
	}
}

//198. House Robber
//Runtime: 0 ms, 100%
//Memory Usage: 35.3 MB
class Solution198
{
	public int rob(int[] nums)
	{
		if (nums == null || nums.length == 0)
			return 0;
		int len = nums.length;
		int c1 = nums[0], c2 = 0;
		// c1: max number when to choose current, c2: max number when not to choose
		// current,
		int max = 0;
		for (int i = 1; i < len; i++)
		{
			if (max < c1)
				max = c1;
			if (max < c2)
				max = c2;
			int d1 = c2 + nums[i], d2 = Math.max(c1, c2);
			c1 = d1;
			c2 = d2;
		}
		return Math.max(c1, c2);
	}
}

//199. Binary Tree Right Side View
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Right Side View.
//Memory Usage: 37.2 MB, less than 100.00% of Java online submissions for Binary Tree Right Side View.
class Solution199
{
	boolean[] Cover = new boolean[500];
	List<Integer> ans = new ArrayList<Integer>();

	void Trav(TreeNode r, int layer)
	{
		if (r == null)
			return;
		if (!Cover[layer])
		{
			Cover[layer] = true;
			ans.add(r.val);
		}
		Trav(r.right, layer + 1);
		Trav(r.left, layer + 1);
	}

	public List<Integer> rightSideView(TreeNode root)
	{
		Trav(root, 0);
		return ans;
	}
}

//200. Number of Islands
//Runtime: 5 ms, faster than 38.52% of Java online submissions for Number of Islands.
//Memory Usage: 41 MB, less than 29.29% of Java online submissions for Number of Islands.
class Solution200
{
	final static int[][] di = new int[][]
	{
			{ 0, 1 },
			{ 1, 0 },
			{ 0, -1 },
			{ -1, 0 } };

	public int numIslands(char[][] grid)
	{
		if (grid.length == 0 || grid[0].length == 0)
			return 0;
		int n1 = grid.length, n2 = grid[0].length;
		boolean[][] used = new boolean[n1][n2];
		int[] qx = new int[n1 * n2];
		int[] qy = new int[n1 * n2];
		int ct = 0, f, r, nx, ny;
		for (int i = 0; i < n1; i++)
			for (int j = 0; j < n2; j++)
				if (grid[i][j] == '1' && !used[i][j])
				{
					ct++;
					qx[0] = i;
					qy[0] = j;
					used[i][j] = true;
					f = 0;
					r = 1;
					while (f < r)
					{
						for (int k = 0; k < 4; k++)
						{
							nx = qx[f] + di[k][0];
							ny = qy[f] + di[k][1];
							if (nx >= 0 && ny >= 0 && nx < n1 && ny < n2 && !used[nx][ny] && grid[nx][ny] == '1')
							{
								qx[r] = nx;
								qy[r] = ny;
								used[nx][ny] = true;
								r++;
							}
						}
						f++;
					}
				}
		return ct;
	}
}

public class LC191_200
{
	public static void main(String[] args)
	{

	}

}
