package lc181_190;

//189. Rotate Array
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Rotate Array.
//Memory Usage: 38.1 MB, less than 35.11% of Java online submissions for Rotate Array.
class Solution189
{
    public void rotate(int[] nums, int k) 
    {
    	int l=nums.length;
        k=k%l;
        int[] a=new int[l];
        for (int i=0;i<l;i++)
        	a[i]=nums[(i+(l-k))%l];
        for (int i=0;i<l;i++)
        	nums[i]=a[i];
        
    }
}

public class LC181_190 {
	public static void main(String[] args)
	{
		
	}

}
