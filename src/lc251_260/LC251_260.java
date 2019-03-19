package lc251_260;

import java.util.HashMap;

//258. Add Digits
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Add Digits.
//Memory Usage: 35 MB, less than 61.06% of Java online submissions for Add Digits.
class Solution258 
{
    public int addDigits(int num) 
    {
        while (num>=10)
        {
            int t=0;
            while (num>0)
            {
                t+=num%10;
                num/=10;
            }
            num=t;
        }
        return num;
    }
}

//260. Single Number III
//Runtime: 6 ms, faster than 22.22% of Java online submissions for Single Number III.
//Memory Usage: 38.1 MB, less than 69.12% of Java online submissions for Single Number III.
class Solution260
{
	public int[] singleNumber(int[] nums) 
	{
		HashMap<Integer,Integer> hm=new HashMap<Integer,Integer>();
        for (int i=0;i<nums.length;i++)
        	if (hm.get(nums[i])==null) 
        		hm.put(nums[i],1);
        	else hm.put(nums[i],hm.get(nums[i])+1);
        int sp=0;
        int[]ans =new int[2];
        for (int i:hm.keySet())
        	if (hm.get(i)==1) ans[sp++]=i;
        return ans;
    }
}

public class LC251_260 {

}
