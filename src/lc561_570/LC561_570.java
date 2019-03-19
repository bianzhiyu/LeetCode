package lc561_570;

import java.util.Arrays;

//561. Array Partition I
//Runtime: 22 ms, faster than 59.30% of Java online submissions for Array Partition I.
//Memory Usage: 41.4 MB, less than 68.50% of Java online submissions for Array Partition I.
class Solution561 
{
    public int arrayPairSum(int[] nums) 
    {
    	Arrays.parallelSort(nums);
    	int tot=0;
    	for (int i=0;i<nums.length;i+=2)
    		tot+=nums[i];
    	return tot;
    }
}

public class LC561_570 
{
	public static void main(String[] args)
	{
		
	}
}
