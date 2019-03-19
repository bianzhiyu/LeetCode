package lc711_720;

import java.util.Arrays;

//713. Subarray Product Less Than K
//Runtime: 24 ms, faster than 19.71% of Java online submissions for Subarray Product Less Than K.
//Memory Usage: 52.7 MB, less than 19.30% of Java online submissions for Subarray Product Less Than K.
class Solution713
{
	public int numSubarrayProductLessThanK(int[] nums, int k)
	{
		if (k<=1) return 0;
		long prod=1;
		int i=0,j=0;
		long sum=0;
		while (i<nums.length)
		{
			while (prod<k && j<nums.length)
			{
				prod*=nums[j];
				j++;
			}
			if (prod>=k)
			{
				if (j==i+1)
				{
					i++;
					prod=1;
				}
				else
				{
					j--;
					prod/=nums[j];
					sum+=j-i;
					prod/=nums[i];
					i++;
				}
			}
			else
			{
				sum+=((long)(j-i+1))*(j-i)/2;
				i=j;
			}
		}
		return (int)sum;
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
	public static void main(String[] args)
	{
		int[] a=new int[48750];
		Arrays.fill(a, 1);
		System.out.println(new Solution713().numSubarrayProductLessThanK(
				a,9
				));
	}

}
