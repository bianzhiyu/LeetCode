package lc551_560;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//560. Subarray Sum Equals K
//Runtime: 20 ms, faster than 57.87% of Java online submissions for Subarray Sum Equals K.
//Memory Usage: 42.1 MB, less than 5.12% of Java online submissions for Subarray Sum Equals K.
class Solution560
{
	public int subarraySum(int[] nums, int k)
	{
		HashMap<Integer,List<Integer>> hm=new HashMap<Integer,List<Integer>>();
		int len=nums.length;
		int[] s=new int[len+1];
		s[0]=0;
		for (int i=1;i<=len;i++)
		{
			s[i]=s[i-1]+nums[i-1];
			if (!hm.containsKey(s[i]))
				hm.put(s[i],new ArrayList<Integer>());
			hm.get(s[i]).add(i);
		}
		int tot=0;
		for (int i=1;i<=len;i++)
		{
			if (!hm.containsKey(s[i]+k-nums[i-1])) continue;
			List<Integer> l=hm.get(s[i]+k-nums[i-1]);
			if (l.get(l.size()-1)<i) continue;
			int f=0,r=l.size()-1;
			while (f<r)
			{
				if (r==f+1)
				{
					if (l.get(f)>=i) r=f;
					else f=r;
					break;
				}
				int m=(f+r)/2;
				if (l.get(m)>=i) r=m;
				else f=m+1;
			}
			tot+=l.size()-r;
		}
		return tot;
	}
}

public class LC551_560
{
	public static void main(String[] args)
	{

	}
}
