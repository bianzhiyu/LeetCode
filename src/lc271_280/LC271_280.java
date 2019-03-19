package lc271_280;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//273. Integer to English Words
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Integer to English Words.
//Memory Usage: 37.1 MB, less than 28.61% of Java online submissions for Integer to English Words.
class Solution273 
{
	private final static String[] w1=
		{"Zero","One","Two","Three","Four","Five","Six","Seven","Eight","Nine",
				"Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen",
				"Sixteen","Seventeen","Eighteen","Nineteen"};
	private final static String[] w2=
		{"","","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety"};
	private final static String[] w3=
		{"","Thousand","Million","Billion"};
	private String ntw(int x)
	{
		int h=x/100,
			t=x%100/10,
			o=x%10;
		String ans="";
		if (x<20)
		{
			ans=w1[x];
		}
		else if (x<=99)
		{
			ans=w2[t];
			if (o!=0) ans=ans+" "+w1[o];
		}
		else
		{
			String str=ntw(t*10+o);
			if (str.compareTo("Zero")==0)
			{
				ans=w1[h]+" Hundred";
			}
			else
			{
				ans=w1[h]+" Hundred"+" "+str;
			}
		}
		return ans;
	}
    public String numberToWords(int num) 
    {
        int[] part=new int[] {num%1000,
        num/1000%1000,
        num/1000/1000%1000,
        num/1000/1000/1000};
        String[] str=new String[] {ntw(part[0]),
        		ntw(part[1]),
        		ntw(part[2]),
        		ntw(part[3])};
        String ans=str[0];
        for (int i=1;i<=3;i++)
        if (part[i]>0)
        {
        	if (ans.compareTo("Zero")==0) ans=str[i]+" "+w3[i];
        	else ans=str[i]+" "+w3[i]+" "+ans;
        }
        
        return ans;
    }
}

//274. H-Index
//Runtime: 5 ms, faster than 64.45% of Java online submissions for H-Index.
//Memory Usage: 37.3 MB, less than 100.00% of Java online submissions for H-Index.
class Solution274
{
	int sch(int[] a, int l, int r)
	{
		if (l == r)
			return l;
		if (r == l + 1)
		{
			if (a[a.length - r] >= r)
				return r;
			else
				return l;
		}
		int m = (l + r) / 2;
		if (a[a.length - m] >= m)
			return sch(a, m, r);
		return sch(a, 0, m - 1);
	}

	public int hIndex(int[] citations)
	{
		Arrays.sort(citations);
		return sch(citations, 0, citations.length);
	}
}

//275. H-Index II
//Runtime: 3 ms, faster than 100.00% of Java online submissions for H-Index II.
//Memory Usage: 42.6 MB, less than 100.00% of Java online submissions for H-Index II.
class Solution275
{
	int sch(int[] a, int l, int r)
	{
		if (l == r)
			return l;
		if (r == l + 1)
		{
			if (a[a.length - r] >= r)
				return r;
			else
				return l;
		}
		int m = (l + r) / 2;
		if (a[a.length - m] >= m)
			return sch(a, m, r);
		return sch(a, 0, m - 1);
	}

	public int hIndex(int[] citations)
	{
		return sch(citations, 0, citations.length);
	}
}

/*
 * The isBadVersion API is defined in the parent class VersionControl. boolean
 * isBadVersion(int version);
 */

class VersionControl
{
	int b;
	VersionControl(int t){b=t;}

	boolean isBadVersion(int x)
	{
		return x>=b;
	}
}
//278. First Bad Version
//Runtime: 10 ms, faster than 99.98% of Java online submissions for First Bad Version.
//Memory Usage: 34.3 MB, less than 93.50% of Java online submissions for First Bad Version.
class Solution278 extends VersionControl
{
	Solution278(int t)
	{
		super(t);
	}

	public int firstBadVersion(int n)
	{
		int l=1,r=n;
		while (true)
		{
			if (l>=r) return r;
			if (r==l+1)
			{
				if (isBadVersion(l)) return l;
				else return r;
			}
			int m=(int)(((long)l+r)/2);
			if (isBadVersion(m))
				r=m;
			else l=m;
		}
	}
}

//https://leetcode.com/problems/perfect-squares/discuss/248065/A-Java-DP-solution-7-lines
//Memory dfs search.
//279. Perfect Squares
//Runtime: 37 ms, faster than 44.36% of Java online submissions for Perfect Squares.
//Memory Usage: 36.6 MB, less than 72.25% of Java online submissions for Perfect Squares.
class Solution279 
{
	int[]d;
    public int numSquares(int n) 
    {
    	d=new int[n];
        return dfs(n);
    }
    int dfs(int n)
    {
    	if (n<=1) return n;
    	if (d[n-1]!=0) return d[n-1];
    	int s=(int)Math.sqrt(n),min=Integer.MAX_VALUE,t;
    	for (int i=1;i<=s;i++)
    	{
    		t=1+dfs(n-i*i);
    		if (t<min) min=t;
    	}
    	d[n-1]=min;
    	return min;
    }
}
//https://leetcode.com/problems/perfect-squares/discuss/248783/This-is-just-shortest-path
//Runtime: 140 ms, faster than 18.05% of Java online submissions for Perfect Squares.
//Memory Usage: 44 MB, less than 9.53% of Java online submissions for Perfect Squares.
class Solution279_2
{
    public int numSquares(int n) 
    {
    	Queue<Integer> q1,q2;
    	q1=new LinkedList<Integer>();
    	q2=new LinkedList<Integer>();
    	HashSet<Integer> used=new HashSet<Integer>();
    	q1.add(0);
    	q2.add(0);
    	used.add(0);
    	while (!q1.isEmpty())
    	{
    		int i=q1.remove(),s=q2.remove();
    		if (i==n) return s;
    		int j=1,t=i+j*j;
    		while (t<=n)
    		{
    			if (!used.contains(t))
    			{
    				used.add(t);
    				q1.add(t);
    				q2.add(s+1);
    			}
    			j++;
    			t=i+j*j;
    		}
    	}
    	return 0;
    }
}
//Amazing,
//Just use array insead of LinkedList,HashSet,
//Time efficiency is greatly improved.
//Runtime: 13 ms, faster than 96.15% of Java online submissions for Perfect Squares.
//Memory Usage: 36.6 MB, less than 59.11% of Java online submissions for Perfect Squares.
class Solution279_3
{
    public int numSquares(int n) 
    {
    	int[] q1,q2;
    	boolean[] used=new boolean[n+1];
    	q1=new int[n+1];
    	q2=new int[n+1];
    	q1[0]=0;
    	q2[0]=0;
    	used[0]=true;
    	int h=0,r=1;
    	
    	while (h<r)
    	{
    		int i=q1[h],s=q2[h];
    		h++;
    		if (i==n) return s;
    		int j=1,t=i+j*j;
    		while (t<=n)
    		{
    			if (!used[t])
    			{
    				used[t]=true;
    				q1[r]=t;
    				q2[r]=s+1;
    				r++;
    			}
    			j++;
    			t=i+j*j;
    		}
    	}
    	return 0;
    }
}

public class LC271_280
{
	public static void main(String[] args)
	{
		Solution273 s=new Solution273();
		System.out.println(s.numberToWords(1234567891));
	}
}
