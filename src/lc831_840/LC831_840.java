package lc831_840;

//836. Rectangle Overlap
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Rectangle Overlap.
//Memory Usage: 36.6 MB, less than 92.47% of Java online submissions for Rectangle Overlap.
class Solution836
{
	public boolean isRectangleOverlap(int[] rec1, int[] rec2)
	{
		long xl = Math.max(rec1[0], rec2[0]), 
				xr = Math.min(rec1[2], rec2[2]), 
				yd = Math.max(rec1[1], rec2[1]),
				yu = Math.min(rec1[3], rec2[3]);
		if (xl > xr)
			xl = xr;
		if (yd > yu)
			yd = yu;
		return (xr - xl) * (yu - yd) > 0;
	}
}

//Runtime: 4 ms, faster than 85.54% of Java online submissions for Magic Squares In Grid.
//Memory Usage: 37.1 MB, less than 58.06% of Java online submissions for Magic Squares In Grid.
class Solution840
{
	public static boolean isMagic(int a1, int a2, int a3, int a4, int a5, int a6, int a7, int a8, int a9)
	{
		return 3 * a1 - 2 * a7 - 2 * a8 + a9 == 0 && 3 * a2 - 2 * a7 + a8 - 2 * a9 == 0
				&& 3 * a3 + a7 - 2 * a8 - 2 * a9 == 0 && 3 * a4 + 2 * a7 - a8 - 4 * a9 == 0
				&& 3 * a5 - a7 - a8 - a9 == 0 && 3 * a6 - 4 * a7 - a8 + 2 * a9 == 0
				&& !(a1 == a2 && a2 == a3 && a3 == a4 && a4 == a5 && a5 == a6 && a6 == a7 && a7 == a8 && a8 == a9)
				&& (1 <= a1 && a1 <= 9) && (1 <= a2 && a2 <= 9) && (1 <= a3 && a3 <= 9) && (1 <= a4 && a4 <= 9)
				&& (1 <= a5 && a5 <= 9) && (1 <= a6 && a6 <= 9) && (1 <= a7 && a7 <= 9) && (1 <= a8 && a8 <= 9)
				&& (1 <= a8 && a9 <= 9);
	}

	public int numMagicSquaresInside(int[][] grid)
	{
		int tot = 0;
		for (int i = 0; i < grid.length - 2; i++)
			for (int j = 0; j < grid[0].length - 2; j++)
				if (isMagic(grid[i][j], grid[i][j + 1], grid[i][j + 2], grid[i + 1][j], grid[i + 1][j + 1],
						grid[i + 1][j + 2], grid[i + 2][j], grid[i + 2][j + 1], grid[i + 2][j + 2]))
					tot++;
		return tot;
	}
}

public class LC831_840
{
	public static void main(String[] args)
	{

	}

}
