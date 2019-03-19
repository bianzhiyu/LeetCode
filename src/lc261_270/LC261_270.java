package lc261_270;

import java.util.HashSet;
import java.util.PriorityQueue;

//263. Ugly Number
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Ugly Number.
//Memory Usage: 34.9 MB, less than 87.08% of Java online submissions for Ugly Number.
class Solution263 
{
    public boolean isUgly(int num) {
    	while (num>0 && num%2==0)
    		num/=2;
    	while (num>0 && num%5==0)
    		num/=5;
    	while (num>0 && num%3==0)
    		num/=3;
    	return num==1;
    }
}

//264. Ugly Number II
//Runtime: 68 ms, faster than 16.29% of Java online submissions for Ugly Number II.
//Memory Usage: 39.5 MB, less than 12.50% of Java online submissions for Ugly Number II.
class Solution264 
{
    public int nthUglyNumber(int n) 
    {
    	PriorityQueue<Long> q=new PriorityQueue<Long>();
    	HashSet<Long> s=new HashSet<Long>();
    	int number=0;
    	q.add((long) 1);
    	while (!q.isEmpty())
    	{
    		long nt=q.remove(),t;
    		number++;
    		if (number==n) return (int)nt;
    		
    		t=nt*2;
    		if (t>0&&!s.contains(t))
    		{
    			q.add(t);
    			s.add(t);
    		}
    		t=nt*3;
    		if (t>0&&!s.contains(t))
    		{
    			q.add(t);
    			s.add(t);
    		}
    		t=nt*5;
    		if (t>0&&!s.contains(t))
    		{
    			q.add(t);
    			s.add(t);
    		}
    	}
    	return 0;
    }
}

//https://leetcode.com/problems/ugly-number-ii/discuss/216388/My-Java-Code-6ms-Readable-and-Clean
class Solution264_2
{
    public int nthUglyNumber(int n) {
		int[] res = new int[n];
		res[0] = 1;
		int t2 = 0, t3 = 0, t5 = 0, idx = 1;
		while (idx < n) {
			res[idx] = Math.min(res[t2] * 2, Math.min(res[t3] * 3, res[t5] * 5));
			t2 += res[idx] == res[t2] * 2 ? 1 : 0;
			t3 += res[idx] == res[t3] * 3 ? 1 : 0;
			t5 += res[idx] == res[t5] * 5 ? 1 : 0;
			++idx;
		}
		return res[n - 1];
	}
}



//268. Missing Number
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Missing Number.
//Memory Usage: 39 MB, less than 80.78% of Java online submissions for Missing Number.
class Solution268
{
    public int missingNumber(int[] nums) 
    {
        int x=0,y=0;
        for (int i=0;i<nums.length;i++)
        {
            x^=nums[i];
            y^=i;
        }
        y^=nums.length;
        return y^x;
    }
}
public class LC261_270 {
	public static void main(String[]args)
	{
		int i=536870912;
		long j=i;
		System.out.println(j);
		System.out.println((int)j);
	}

}
