package lc521_530;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

//523. Continuous Subarray Sum
//Runtime: 37 ms, faster than 15.22% of Java online submissions for Continuous Subarray Sum.
//Memory Usage: 41.4 MB, less than 6.29% of Java online submissions for Continuous Subarray Sum.
class Solution523
{
	public boolean checkSubarraySum(int[] nums, int k)
	{
		if (k<0) k=-k;
		HashMap<Integer,List<Integer>> hm=new HashMap<Integer,List<Integer>>();
		int len=nums.length;
		int[] s=new int[len+1];
		s[0]=0;
		for (int i=1;i<=len;i++)
		{
			if (k>0) s[i]=(s[i-1]+nums[i-1])%k;
			else s[i]=s[i-1]+nums[i-1];
			if (!hm.containsKey(s[i]))
				hm.put(s[i],new ArrayList<Integer>());
			hm.get(s[i]).add(i);
		}
		int tot=0;
		for (int i=1;i<=len;i++)
		{
			List<Integer> l=hm.get(s[i-1]);
			if (l==null) continue;
			if (l.get(l.size()-1)<i+1) continue;
			int f=0,r=l.size()-1;
			while (f<r)
			{
				if (r==f+1)
				{
					if (l.get(f)>=i+1) r=f;
					else f=r;
					break;
				}
				int m=(f+r)/2;
				if (l.get(m)>=i+1) r=m;
				else f=m+1;
			}
			tot+=l.size()-r;
		}
		return tot>0;
	}
}

//524. Longest Word in Dictionary through Deleting
//Runtime: 41 ms, faster than 45.67% of Java online submissions for Longest Word in Dictionary through Deleting.
//Memory Usage: 39.9 MB, less than 90.55% of Java online submissions for Longest Word in Dictionary through Deleting.
class Solution524
{
	boolean reachable(String s, String ss)
	{
		int sp = 0, ssp = 0;
		while (sp < s.length() && ssp < ss.length())
		{
			if (s.charAt(sp) == ss.charAt(ssp))
			{
				sp++;
				ssp++;
			} else
				sp++;
		}
		return ssp == ss.length();
	}

	public String findLongestWord(String s, List<String> d)
	{
		String ans = "";
		int n = d.size();
		for (int i = 0; i < n; i++)
			if (reachable(s, d.get(i)) && (ans.length() < d.get(i).length()
					|| (ans.length() == d.get(i).length() && d.get(i).compareTo(ans) < 0)))
			{
				ans = d.get(i);
			}
		return ans;
	}
}

//528. Random Pick with Weight
//Runtime: 70 ms, faster than 94.84% of Java online submissions for Random Pick with Weight.
//Memory Usage: 53.3 MB, less than 42.91% of Java online submissions for Random Pick with Weight.
class Solution528 
{
	Random rd;
	int[] s;
	int m;
	
    public Solution528(int[] w) 
    {
        rd=new Random();
        int len=w.length;
        s=new int[len];
        m=-1;
        for (int i=0;i<len;i++)
        {
        	m+=w[i];
        	s[i]=m;
        }
    }
    int fd(int n)
    {
    	int l=0,r=s.length-1;
        while (l<r)
        {
        	if (r==l+1)
        	{
        		if (s[l]>=n) r=l;
        		else l=r;
        	}
        	else
        	{
        		int m=(l+r)/2;
        		if (s[m]>=n) r=m;
        		else l=m+1;
        	}
        }
        return l;
    }
    public int pickIndex() 
    {
        int n=rd.nextInt(m+1);
        return fd(n);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */

public class LC521_530
{
	public static void main(String[] args)
	{
		Solution524 s = new Solution524();
		System.out.println(s.reachable("aaa", "aaa"));
		System.out.println("a".compareTo("b"));
	}

}
