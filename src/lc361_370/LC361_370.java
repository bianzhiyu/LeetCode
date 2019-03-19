package lc361_370;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//367 Valid Perfect Square    
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Valid Perfect Square.
//Memory Usage: 34.4 MB, less than 72.33% of Java online submissions for Valid Perfect Square.
class Solution367
{
	public boolean isPerfectSquare(int num)
	{
		int k = 1, ct = 0, a = (k + num / k) / 2;
		while (k != a && ct < 500)
		{
			ct++;
			k = a;
			a = (k + num / k) / 2;
		}
		if (k * k == num)
			return true;
		return false;
	}
}

//368. Largest Divisible Subset
//Runtime: 19 ms, faster than 86.82% of Java online submissions for Largest Divisible Subset.
//Memory Usage: 38.4 MB, less than 41.67% of Java online submissions for Largest Divisible Subset.
class Solution368
{
	public List<Integer> largestDivisibleSubset(int[] nums)
	{
		List<Integer> ans=new ArrayList<Integer>();
		int len=nums.length;
		if (len==0) return ans;
		Arrays.parallelSort(nums);
		int[] mlen=new int[len],next=new int[len];
		next[len-1]=-1;
		mlen[len-1]=1;
		for (int i=len-2;i>=0;i--)
		{
			mlen[i]=1;
			next[i]=-1;
			for (int j=i+1;j<len;j++)
				if (nums[j]%nums[i]==0 && mlen[j]+1>mlen[i])
				{
					mlen[i]=mlen[j]+1;
					next[i]=j;
				}
		}
		int maxind=0;
		for (int i=1;i<len;i++)
			if (mlen[i]>mlen[maxind])
				maxind=i;
		while (maxind!=-1)
		{
			ans.add(nums[maxind]);
			maxind=next[maxind];
		}
		return ans;
	}
}

public class LC361_370
{
	public static void main(String[] args)
	{
		int[] nums=new int[] {1,2,3};
		System.out.println(new Solution368().largestDivisibleSubset(nums));
	}
}
