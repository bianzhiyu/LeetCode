package lc711_720;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

//712. Minimum ASCII Delete Sum for Two Strings
//Runtime: 9 ms, faster than 99.26% of Java online submissions for Minimum ASCII Delete Sum for Two Strings.
//Memory Usage: 38.1 MB, less than 77.59% of Java online submissions for Minimum ASCII Delete Sum for Two Strings.
class Solution712
{
	public int minimumDeleteSum(String s1, String s2)
	{
		int len1 = s1.length(), len2 = s2.length();
		int[][] d = new int[len1 + 1][len2 + 1];
		for (int i = 1; i <= len1; i++)
			d[i][0] = d[i - 1][0] + s1.charAt(i - 1);
		for (int i = 1; i <= len2; i++)
			d[0][i] = d[0][i - 1] + s2.charAt(i - 1);
		for (int i = 1; i <= len1; i++)
			for (int j = 1; j <= len2; j++)
			{
				d[i][j] = Math.min(d[i - 1][j] + s1.charAt(i - 1), d[i][j - 1] + s2.charAt(j - 1));
				if (s1.charAt(i - 1) == s2.charAt(j - 1) && d[i - 1][j - 1] < d[i][j])
					d[i][j] = d[i - 1][j - 1];
			}
		return d[len1][len2];
	}
}

//713. Subarray Product Less Than K
//Runtime: 24 ms, faster than 19.71% of Java online submissions for Subarray Product Less Than K.
//Memory Usage: 52.7 MB, less than 19.30% of Java online submissions for Subarray Product Less Than K.
class Solution713
{
	public int numSubarrayProductLessThanK(int[] nums, int k)
	{
		if (k <= 1)
			return 0;
		long prod = 1;
		int i = 0, j = 0;
		long sum = 0;
		while (i < nums.length)
		{
			while (prod < k && j < nums.length)
			{
				prod *= nums[j];
				j++;
			}
			if (prod >= k)
			{
				if (j == i + 1)
				{
					i++;
					prod = 1;
				} else
				{
					j--;
					prod /= nums[j];
					sum += j - i;
					prod /= nums[i];
					i++;
				}
			} else
			{
				sum += ((long) (j - i + 1)) * (j - i) / 2;
				i = j;
			}
		}
		return (int) sum;
	}
}

//714. Best Time to Buy and Sell Stock with Transaction Fee
//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/solution/
//Runtime: 6 ms, faster than 95.85% of Java online submissions for Best Time to Buy and Sell Stock with Transaction Fee.
//Memory Usage: 53.8 MB, less than 5.04% of Java online submissions for Best Time to Buy and Sell Stock with Transaction Fee.
class Solution714
{
	public int maxProfit(int[] prices, int fee)
	{
		int cash = 0, hold = -prices[0];
		for (int i = 1; i < prices.length; i++)
		{
			cash = Math.max(cash, hold + prices[i] - fee);
			hold = Math.max(hold, cash - prices[i]);
		}
		return cash;
	}
}

//the same
//Runtime: 5 ms, faster than 99.32% of Java online submissions for Best Time to Buy and Sell Stock with Transaction Fee.
//Memory Usage: 53.5 MB, less than 6.72% of Java online submissions for Best Time to Buy and Sell Stock with Transaction Fee.
class Solution714_2
{
	public int maxProfit(int[] prices, int fee)
	{
		int cash = 0, hold = -prices[0] - fee;
		for (int i = 1; i < prices.length; i++)
		{
			cash = Math.max(cash, hold + prices[i]);
			hold = Math.max(hold, cash - prices[i] - fee);
		}
		return cash;
	}
}

//718. Maximum Length of Repeated Subarray
//Runtime: 57 ms, faster than 26.71% of Java online submissions for Maximum Length of Repeated Subarray.
//Memory Usage: 53.6 MB, less than 5.68% of Java online submissions for Maximum Length of Repeated Subarray.
class Solution718
{
	public int findLength(int[] A, int[] B)
	{
		int len1 = A.length, len2 = B.length, max = 0;
		int[][] d = new int[len1 + 1][len2 + 1];
		for (int i = 1; i <= len1; i++)
			d[i][0] = 0;
		for (int i = 1; i <= len2; i++)
			d[0][i] = 0;
		for (int i = 1; i <= len1; i++)
			for (int j = 1; j <= len2; j++)
			{
				if (A[i - 1] == B[j - 1])
					d[i][j] = 0;
				else
					d[i][j] = 1 + d[i - 1][j - 1];
				if (d[i][j] > max)
					max = d[i][j];
			}
		return max;
	}
}

//719. Find K-th Smallest Pair Distance
//Runtime: 549 ms, faster than 8.93% of Java online submissions for Find K-th Smallest Pair Distance.
//Memory Usage: 39.9 MB, less than 100.00% of Java online submissions for Find K-th Smallest Pair Distance.
//Rk: When I want to malloc a int array with 10^8 length,
//-> MLE
class Solution719
{
	public int smallestDistancePair(int[] nums, int k)
	{
		int n = nums.length;
		int[] x = new int[1000000 + 2];
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++)
				x[Math.abs(nums[i] - nums[j])]++;
		int tot = x[0], a = 0;
		while (tot < k)
		{
			a++;
			tot += x[a];
		}
		return a;
	}
}

public class LC711_720
{
	public static void test712()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input712.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output712.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				String s1 = inLine.substring(1, inLine.length() - 1);

				inLine = bfr.readLine();
				String s2 = inLine.substring(1, inLine.length() - 1);

				Solution712 s = new Solution712();

				int ans = s.minimumDeleteSum(s1, s2);
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

	public static void test713()
	{
		int[] a = new int[48750];
		Arrays.fill(a, 1);
		System.out.println(new Solution713().numSubarrayProductLessThanK(a, 9));
	}

	public static void main(String[] args)
	{
		test712();
	}

}
