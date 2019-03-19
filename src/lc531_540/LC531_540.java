package lc531_540;

//540. Single Element in a Sorted Array
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Single Element in a Sorted Array.
//Memory Usage: 38.3 MB, less than 84.83% of Java online submissions for Single Element in a Sorted Array.
class Solution540 
{
    public int singleNonDuplicate(int[] nums) 
    {
        int x=0;
        for (int i=0;i<nums.length;i++) x^=nums[i];
        return x;
        
    }
}

public class LC531_540 {
	public static void main(String[] args)
	{
		
	}

}
