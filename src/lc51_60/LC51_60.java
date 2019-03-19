package lc51_60;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


class Interval {
	int start;
	int end;
	Interval() { start = 0; end = 0; }
	Interval(int s, int e) { start = s; end = e; }
}

	//t51
	//t=2ms, m=28MB
	//t:1, m:0.3036
class Solution51 {
	boolean b[];
	boolean c[];
	boolean d[];
	char[] t;
	int[] p;
	void dfs(int sp,int n,List<List<String>> ans)
	{
		if (sp==n)
		{
			List<String> na=new ArrayList<String>();
			for (int i=0;i<n;i++)
			{
				t[p[i]]='Q';
				na.add(new String(t));
				t[p[i]]='.';
			}
			ans.add(na);
			return;
		}
		for (int i=0;i<n;i++)
		{
			if (!b[i] && !c[sp+i] && !d[sp-i+(n-1)])
			{
				b[i]=true;
				c[sp+i]=true;
				d[sp-i+(n-1)]=true;
				p[sp]=i;
				dfs(sp+1,n,ans);
				b[i]=false;
				c[sp+i]=false;
				d[sp-i+(n-1)]=false;            
			}
		}
	}
	public List<List<String>> solveNQueens(int n) {
		b=new boolean[n];
		c=new boolean[2*n-1];
		d=new boolean[2*n-1];
		t=new char[n];
		p=new int[n];
		for (int i=0;i<n;i++) t[i]='.';
		List<List<String>> ans=new ArrayList<List<String>>();
		dfs(0,n,ans);
		return ans;
	}
}
//t52
//t=1ms, m=24MB
//t:0.9960, m:0.5339
class Solution52 {
	boolean b[];
	boolean c[];
	boolean d[];
	int tot=0;
	int[] p;
	void dfs(int sp,int n)
	{
		if (sp==n)
		{
			tot++;
			return;
		}
		for (int i=0;i<n;i++)
		{
			if (!b[i] && !c[sp+i] && !d[sp-i+(n-1)])
			{
				b[i]=true;
				c[sp+i]=true;
				d[sp-i+(n-1)]=true;
				p[sp]=i;
				dfs(sp+1,n);
				b[i]=false;
				c[sp+i]=false;
				d[sp-i+(n-1)]=false;            
			}
		}
	}
	public int totalNQueens(int n) {
		b=new boolean[n];
		c=new boolean[2*n-1];
		d=new boolean[2*n-1];
		p=new int[n];
		dfs(0,n);
		return tot;
	}
}
//53. Maximum Subarray
//Runtime: 35 ms, faster than 6.53% of Java online submissions for Maximum Subarray.
//Memory Usage: 39.2 MB, less than 100.00% of Java online submissions for Maximum Subarray.
class Solution53 {
	List<Integer> merge(int[] n)
	{
		List<Integer> l=new ArrayList<Integer>();
		
		int i=0;
		while (i<n.length)
		{
			if (n[i]==0)
			{
				int j=i+1;
				while (j<n.length && n[j]==0)
				{
					j++;
				}
				l.add(0);
				i=j;
			}
			else if (n[i]>0)
			{
				int j=i+1,s=n[i];
				while (j<n.length && n[j]>0) 
				{
					s+=n[j];
					j++;
				}
				l.add(s);
				i=j;
			}
			else 
			{
				int j=i+1,s=n[i];
				while (j<n.length && n[j]<0) 
				{
					s+=n[j];
					j++;
				}
				l.add(s);
				i=j;
			}
		}
		return l;
	}
    public int maxSubArray(int[] nums) {
    	
    	List<Integer> l=merge(nums);

    	int max=Integer.MIN_VALUE;
    	if (l.size()==1 && l.get(0)<0)
    	{
    		for(int i=0;i<nums.length;i++)
    			if (nums[i]>max) max=nums[i];
    		return max;
    	}
    	for (int i=0;i<l.size();i++)
    	{
    		int t=0;
    		for (int j=i;j<l.size();j++)
    		{
    			t=t+l.get(j);
    			if (t>max) max=t;
    		}
    	}
    	return max;
        
    }
}
//Runtime: 6 ms, faster than 99.94% of Java online submissions for Maximum Subarray.
//Memory Usage: 39.6 MB, less than 100.00% of Java online submissions for Maximum Subarray
class Solution53_2 {
    public int maxSubArray(int[] nums) {
        int[] r=new int[nums.length];
        r[0]=nums[0];
        for (int i=1;i<nums.length;i++)
        	r[i]=Math.max(r[i-1]+nums[i],nums[i]);
        int max=Integer.MIN_VALUE;
        for (int i=0;i<r.length;i++)
        	if (r[i]>max) max=r[i];
        return max;
        		
    }
}
//54. Spiral Matrix
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Spiral Matrix.
//Memory Usage: 37 MB, less than 100.00% of Java online submissions for Spiral Matrix.
class Solution54 {
    public List<Integer> spiralOrder(int[][] matrix) {
    	if (matrix.length==0||matrix[0].length==0) 
    		return new ArrayList<Integer>();
        int m=matrix.length,n=matrix[0].length;
        boolean[][] used=new boolean[m][n];
        List<Integer> ans=new ArrayList<Integer>();
        int[][] Del= {{0,1},{1,0},{0,-1},{-1,0}};
        int x=0,y=0,di=0;
        for (int i=0;i<m*n;i++)
        {
        	ans.add(matrix[x][y]);
        	used[x][y]=true;
        	while (i<m*n-1)
        	{
        		int nx=x+Del[di][0],ny=y+Del[di][1];
        		if (nx<0 || nx>=m ||
        				ny<0 || ny>=n || used[nx][ny])
        			di=(di+1)%4;
        		else 
        		{
        			x=nx;y=ny;
        			break;
        		}
        	}
        }
        return ans;
    }
}
//55. Jump Game
//Runtime: 4 ms, faster than 92.80% of Java online submissions for Jump Game.
//Memory Usage: 40.6 MB, less than 36.37% of Java online submissions for Jump Game.
class Solution55 {
    public boolean canJump(int[] nums) {
    	int m=0;
    	for (int i=0;i<nums.length;i++)
    	{
    		if (m<i) return false;
    		m=Math.max(m,i+nums[i]);
    	}
    	return true;
    }
}

//56. Merge Intervals
//Runtime: 11 ms, faster than 95.09% of Java online submissions for Merge Intervals.
//Memory Usage: 38.8 MB, less than 99.19% of Java online submissions for Merge Intervals.
class Solution56 
{
	boolean overlap(Interval i1,Interval i2)
	{
		if (i1.start>i2.end || i1.end<i2.start) return false;
		return true;
	}
	Interval mergeint(Interval i1,Interval i2)
	{
		return new Interval(
				Math.min(i1.start, i2.start),
				Math.max(i1.end,i2.end)
				);
	}
    public List<Interval> merge(List<Interval> intervals) 
    {
    	List<Interval> ans=new ArrayList<Interval>();
        int n=intervals.size();
        if (n==0) return ans;
        while (true)
        {
        	int i=1;
        	boolean nothinghappened=true;
        	while (i<n)
        	{
        		boolean merge=false;
        		for (int j=0;j<i;j++)
        			if (overlap(intervals.get(i),intervals.get(j)))
        			{
        				intervals.set(j, mergeint(intervals.get(i),
        								intervals.get(j)));
        				intervals.set(i, intervals.get(n-1));
        				n--;
        				merge=true;
        				nothinghappened=false;
        				break;
        			}
        		if (!merge) i++;
        	}
        	if (nothinghappened) break;
        }
        for (int i=0;i<n;i++)
        	ans.add(intervals.get(i));
        return ans;
    }
}
//t57
//t=6ms, m=41MB,
//t:0.9964, m:0.0098
class Solution57
{
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		if (intervals.isEmpty())
		{
			List<Interval> ans=new ArrayList<Interval>();
			ans.add(new Interval(newInterval.start,newInterval.end));
			return ans;
		}

		int c1=0,c2=0,i=0,j=0,l=intervals.size(),c=newInterval.start,
				d=newInterval.end;

		if (c<intervals.get(0).start) c1=1;
		else if (c>intervals.get(l-1).end) c1=2;
		else {
			int isContained=0;
			for (i=0;i<l;i++)
				if (intervals.get(i).start<=c && intervals.get(i).end>=c)
				{
					isContained=1;
					break;
				}
			if (isContained==1) c1=3;
			else 
			{
				c1=4;
				for (i=0;i<l-1;i++)
					if (intervals.get(i+1).start>c) break;                        
			}
		}

		if (d<intervals.get(0).start) c2=1;
		else if (d>intervals.get(l-1).end) c2=2;
		else {
			int isContained=0;
			for (j=0;j<l;j++)
				if (intervals.get(j).start<=d && intervals.get(j).end>=d)
				{
					isContained=1;
					break;
				}
			if (isContained==1) c2=3;
			else
			{
				c2=4;
				for (j=0;j<l-1;j++)
					if (intervals.get(j+1).start>d) break;           
			}
		}
		// System.out.println("c="+c+" d="+d);
		// System.out.println("c1="+c1+" c2="+c2);
		// System.out.println("i="+i+" j="+j);

		if (c1==1 && c2==1)
		{
			List<Interval> ans=new ArrayList<Interval>();
			ans.add(new Interval(c,d));
			for (int k=0;k<l;k++)
				ans.add(new Interval(intervals.get(k).start,intervals.get(k).end));
			return ans;
		}
		else if (c1==1 && c2==2)
		{
			List<Interval> ans=new ArrayList<Interval>();
			ans.add(new Interval(c,d));
			return ans;
		}
		else if (c1==1 && c2==3)
		{
			List<Interval> ans=new ArrayList<Interval>();
			ans.add(new Interval(c,intervals.get(j).end));
			for (int k=j+1;k<l;k++)
				ans.add(new Interval(intervals.get(k).start,intervals.get(k).end));
			return ans;
		}
		else if (c1==1 && c2==4)
		{
			List<Interval> ans=new ArrayList<Interval>();
			ans.add(new Interval(c,d));
			for (int k=j+1;k<l;k++)
				ans.add(new Interval(intervals.get(k).start,intervals.get(k).end));
			return ans;
		}
		else if (c1==2 && c2==2)
		{
			intervals.add(new Interval(c,d));
			return intervals;
		}
		else if (c1==3 && c2==2)
		{
			List<Interval> ans=new ArrayList<Interval>();
			for (int k=0;k<=i-1;k++)
				ans.add(new Interval(intervals.get(k).start,intervals.get(k).end));
			ans.add(new Interval(intervals.get(i).start,d));
			return ans;
		}
		else if (c1==3 && c2==3)
		{
			if (i==j) return intervals;
			List<Interval> ans=new ArrayList<Interval>();
			for (int k=0;k<=i-1;k++)
				ans.add(new Interval(intervals.get(k).start,intervals.get(k).end));
			ans.add(new Interval(intervals.get(i).start,intervals.get(j).end));
			for (int k=j+1;k<l;k++)
				ans.add(new Interval(intervals.get(k).start,intervals.get(k).end));
			return ans;
		}
		else if (c1==3 && c2==4)
		{
			List<Interval> ans=new ArrayList<Interval>();
			for (int k=0;k<=i-1;k++)
				ans.add(new Interval(intervals.get(k).start,intervals.get(k).end));
			ans.add(new Interval(intervals.get(i).start,d));
			for (int k=j+1;k<l;k++)
				ans.add(new Interval(intervals.get(k).start,intervals.get(k).end));
			return ans;
		}
		else if (c1==4 && c2==2)
		{
			List<Interval> ans=new ArrayList<Interval>();
			for (int k=0;k<=i;k++)
				ans.add(new Interval(intervals.get(k).start,intervals.get(k).end));
			ans.add(new Interval(c,d));
			return ans;
		}
		else if (c1==4 && c2==3)
		{
			List<Interval> ans=new ArrayList<Interval>();
			for (int k=0;k<=i;k++)
				ans.add(new Interval(intervals.get(k).start,intervals.get(k).end));
			ans.add(new Interval(c,intervals.get(j).end));
			for (int k=j+1;k<l;k++)
				ans.add(new Interval(intervals.get(k).start,intervals.get(k).end));
			return ans;
		}
		else if (c1==4 && c2==4)
		{
			List<Interval> ans=new ArrayList<Interval>();
			for (int k=0;k<=i;k++)
				ans.add(new Interval(intervals.get(k).start,intervals.get(k).end));
			ans.add(new Interval(c,d));
			for (int k=j+1;k<l;k++)
				ans.add(new Interval(intervals.get(k).start,intervals.get(k).end));
			return ans;
		}
		return intervals;
	}
}
//t58. Length of Last Word
class Solution58 {
    public int lengthOfLastWord(String s) {
        int l=s.length(),i=l-1;
        while (i>=0 && s.charAt(i)==' ')
        {
         i--;l--;   
        }
        
        if (i<0) return 0;
        while (i>0 && s.charAt(i)!=' ') i--;
        if (i==0 && s.charAt(i)!=' ')
        return l-i;
        else return l-i-1;
    }
}
//59. Spiral Matrix II
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Spiral Matrix II.
//Memory Usage: 36.9 MB, less than 100.00% of Java online submissions for Spiral Matrix II.
class Solution59 {
    public int[][] generateMatrix(int n) {
    	if (n==0) return null;
        int[][] m=new int[n][n];
        int[][] Del= {{0,1},{1,0},{0,-1},{-1,0}};
        int x=0,y=0,di=0;
        for (int i=0;i<n*n;i++)
        {
        	m[x][y]=i+1;
        	while (i<n*n-1)
        	{
        		int nx=x+Del[di][0],ny=y+Del[di][1];
        		if (nx<0 || nx>=n ||
        				ny<0 || ny>=n || m[nx][ny]>0)
        			di=(di+1)%4;
        		else 
        		{
        			x=nx;y=ny;
        			break;
        		}
        	}
        }
        return m;
    }
}

//60. Permutation Sequence
//Runtime: 72 ms, faster than 15.30% of Java online submissions for Permutation Sequence.
//Memory Usage: 37.7 MB, less than 29.68% of Java online submissions for Permutation Sequence.
class Solution60 
{
    public String getPermutation(int n, int k) 
    {
    	char[] x=new char[n];
    	for (int i=0;i<n;i++)
    		x[i]=(char)(i+'1');
    	for (int i=1;i<k;i++)
    	{
    		int j=n-1;
    		while (x[j-1]>x[j])
    			j--;
    		j--;
    		int minp=j+1,min=127;
    		for (int l=j+1;l<n;l++)
    			if (x[l]>x[j] && x[l]<min)
    			{
    				min=x[l];
    				minp=l;
    			}
    		char t=x[j];
    		x[j]=x[minp];
    		x[minp]=t;
    		Arrays.parallelSort(x, j+1, n);
    	}
        return new String(x);
    }
}
//Runtime: 6 ms, faster than 99.96% of Java online submissions for Permutation Sequence.
//Memory Usage: 37.6 MB, less than 32.11% of Java online submissions for Permutation Sequence.
class Solution60_2
{
    public String getPermutation(int n, int k) 
    {
    	char[] x=new char[n];
    	List<Character> nums=new ArrayList<Character>();
        int s=1;
    	for (int i=0;i<n;i++)
        {
            s*=(i+1);
    		nums.add((char)(i+'1'));
        }
    	
    	k--;
    	
    	for (int i=0;i<n;i++)
    	{
    		s/=n-i;
    		x[i]=nums.get(k/s);
    		nums.remove(k/s);
    		k%=s;
    	}
        return new String(x);
    }
}
public class LC51_60 {
	public static void main(String[] args)
	{
		Solution60 s=new Solution60();
		System.out.println(s.getPermutation(4, 9));

	}
}
