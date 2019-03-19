package lc671_680;

//674. Longest Continuous Increasing Subsequence
//Runtime: 2 ms, faster than 100.00% of Java online submissions for Longest Continuous Increasing Subsequence.
//Memory Usage: 40.1 MB, less than 70.67% of Java online submissions for Longest Continuous Increasing Subsequence.
class Solution673 
{
    public int findLengthOfLCIS(int[] nums) 
    {
    	int i=0,max=0;
    	while (i<nums.length)
    	{
    		int j=i+1;
    		while (j<nums.length && nums[j]>nums[j-1]) j++;
    		if (j-i>max) max=j-i;
    		i=j;
    	}
    	return max;
    }
} 

public class LC671_680 {

}
