package lc591_600;

import java.util.HashMap;


//594 Longest Harmonious Subsequence    
//Runtime: 34 ms, faster than 95.06% of Java online submissions for Longest Harmonious Subsequence.
//Memory Usage: 42.3 MB, less than 30.44% of Java online submissions for Longest Harmonious Subsequence.
class Solution594 
{
    public int findLHS(int[] nums) 
    {
        HashMap<Integer,Integer>hm=new HashMap<Integer,Integer>();
        for (int i=0;i<nums.length;i++)
        	if (hm.containsKey(nums[i]))
        		hm.put(nums[i],hm.get(nums[i])+1);
        	else hm.put(nums[i],1);
        int m=0,t=0;
        for (int i:hm.keySet())
        	if (hm.containsKey(i+1))
        	{
        		t= hm.get(i)+hm.get(i+1);
        		if (t>m) m=t;
        	}
        return m;
    }
}

public class LC591_600 {
	public static void main(String[] args)
	{
		Solution594 s=new Solution594();
		System.out.println(s.findLHS(new int[] {1,1,1,1}));
	}

}
