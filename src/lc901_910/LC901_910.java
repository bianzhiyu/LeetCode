package lc901_910;

import java.util.Arrays;

//907. Sum of Subarray Minimums
//Runtime: 12124 ms, faster than 5.10% of Java online submissions for Sum of Subarray Minimums.
//Memory Usage: 45.8 MB, less than 51.56% of Java online submissions for Sum of Subarray Minimums.
class Solution907
{
	public int sumSubarrayMins(int[] A)
	{
		int min = 0;
		long tot = 0;
		final long modulo = 1000000007;
		for (int i = 0; i < A.length; i++)
		{
			min = Integer.MAX_VALUE;
			for (int j = i; j < A.length; j++)
			{
				if (A[j] < min)
					min = A[j];
				tot = (tot + min) % modulo;
			}
		}
		return (int) tot;
	}
}

//910. Smallest Range II
//https://leetcode.com/problems/smallest-range-ii/discuss/213953/C%2B%2B-6-lines-of-code
//Runtime: 12 ms, faster than 77.13% of Java online submissions for Smallest Range II.
//Memory Usage: 40.3 MB, less than 80.95% of Java online submissions for Smallest Range II.
class Solution910
{
	public int smallestRangeII(int[] A, int K)
	{
		Arrays.parallelSort(A);
		int N = A.length;
        int ret = A[N-1] - A[0];
        for(int i = 0; i < N - 1; i++)
            if(A[i] != A[i+1]) ret = Math.min(ret, 
            		Math.max(A[i]+K, A[N-1]-K) - Math.min(A[i+1]-K, A[0]+K));
        return ret;
	}
}

public class LC901_910
{
	public static void main(String[] args)
	{

	}

}
