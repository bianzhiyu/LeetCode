package lc631_640;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//632. Smallest Range
//Runtime: 53 ms, faster than 77.19% of Java online submissions for Smallest Range.
//Memory Usage: 49 MB, less than 81.67% of Java online submissions for Smallest Range.
class Solution632 
{
	int findMinIndexNoLessThan(List<Integer>arr,int tar)
	{
		int l=0,r=arr.size()-1;
		if (arr.get(r)<tar) return r+1;
		while (l<r)
		{
			if (r==l+1)
			{
				if (arr.get(l)>=tar) r=l;
				else l=r;
			}
			else
			{
				int m=(l+r)/2;
				if (arr.get(m)>=tar) r=m;
				else l=m;
			}
		}
		return l;	
	}
	int findMaxIndexNoLargerThan(List<Integer>arr,int tar)
	{
		int l=0,r=arr.size()-1;
		if (arr.get(0)>tar) return -1;
		while (l<r)
		{
			if (r==l+1)
			{
				if (arr.get(r)<=tar) l=r;
				else r=l;
			}
			else
			{
				int m=(l+r)/2;
				if (arr.get(m)<=tar) l=m;
				else r=m;
			}
		}
		return l;	
	}
	boolean joint(List<Integer>arr,int l,int r)
	{
		if (findMinIndexNoLessThan(arr,l)<=
				findMaxIndexNoLargerThan(arr,r))
			return true;
		else return false;
	}
    public int[] smallestRange(List<List<Integer>> nums) 
    {
    	Set<Integer> ns=new HashSet<Integer>();
        for (List<Integer> i:nums)
        	for (int j:i)
        		ns.add(j);
        int[] allns=new int[ns.size()];
        int j=0;
        for (int i:ns) allns[j++]=i;
        Arrays.parallelSort(allns);
        int[]ans=new int[2];
        int minlen=Integer.MAX_VALUE;
        for (int i=0;i<allns.length;i++)
        	for (j=i;j<allns.length;j++)
        	{
        		if (allns[j]-allns[i]>minlen) break;
        		boolean succ=true;
        		for (List<Integer> k:nums)
        		{
        			if (!joint(k,allns[i],allns[j]))
        			{
        				succ=false;
        				break;
        			}
        		}
        		if (succ)
        		{
        			if (allns[j]-allns[i]<minlen)
        			{
        				minlen=allns[j]-allns[i];
        				ans[0]=allns[i];
        				ans[1]=allns[j];
        			}
        			else if (allns[j]-allns[i]==minlen
        					&& allns[i]<ans[0])
        			{
        				ans[0]=allns[i];
        				ans[1]=allns[j];
        			}
        		}
        	}
        return ans;
    }
}

public class LC631_640 
{
	public static void main(String[] args)
	{
		Set<Integer> a=new HashSet<Integer>();
		a.add(1);
		a.add(1);
		System.out.println(a.toString());
	}

}
