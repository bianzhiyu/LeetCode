package lc931_940;

//931. Minimum Falling Path Sum
//Runtime: 3 ms, faster than 98.25% of Java online submissions for Minimum Falling Path Sum.
//Memory Usage: 38.9 MB, less than 90.32% of Java online submissions for Minimum Falling Path Sum.
class Solution931
{
	public int minFallingPathSum(int[][] A)
	{
		int len = A.length;
		for (int i = 1; i < len; i++)
		{
			for (int j = 0; j < len; j++)
			{
				int t = A[i - 1][j];
				if (j > 0 && A[i - 1][j - 1] < t)
					t = A[i - 1][j - 1];
				if (j < len - 1 && A[i - 1][j + 1] < t)
					t = A[i - 1][j + 1];
				A[i][j] += t;
			}
		}
		int min = A[len - 1][0];
		for (int i = 1; i < len; i++)
			if (A[len - 1][i] < min)
				min = A[len - 1][i];
		return min;
	}
}

public class LC931_940
{
	public static void main(String[] args)
	{

	}
}
