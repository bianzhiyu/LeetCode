package lc71_80;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

//72. Edit Distance
//(Seriously) Time Limit Exceeded
class Solution72 {
  public int minDistance(String word1, String word2) {
	  if (word1.compareTo(word2)==0) return 0;
      Queue<String> Q=new LinkedList<String>();
      Queue<Integer> S=new LinkedList<Integer>();
      Set<String> H=new HashSet<String>();
      Q.add(word1);
      S.add(0);
      H.add(word1);
      while (Q.size()>0)
      {
    	  String w=Q.poll(),nw;
    	  int s=S.poll();
    	  
    	  //insert
    	  for (int j=0;j<26;j++)
    	  {
    		  nw=new String(new char[] {(char)(j+(int)'a')})+w;
    		  if (word2.compareTo(nw)==0) return s+1;
    		  if (!H.contains(nw))
    		  {
    			  Q.add(nw);
    			  H.add(nw);
    			  S.add(s+1);
    		  }
    		  nw=w+new String(new char[] {(char)(j+(int)'a')});
    		  if (word2.compareTo(nw)==0) return s+1;
    		  if (!H.contains(nw))
    		  {
    			  Q.add(nw);
    			  H.add(nw);
    			  S.add(s+1);
    		  }
    	  }
    	  for (int i=0;i<w.length()-1;i++)    	  
    		  for (int j=0;j<26;j++)
    		  {
    			  nw=w.substring(0,i+1)
    					  +new String(new char[] {(char)(j+(int)'a')})
    					  +w.substring(i+1,w.length());
    			  if (word2.compareTo(nw)==0) return s+1;
        		  if (!H.contains(nw))
        		  {
        			  Q.add(nw);
        			  H.add(nw);
        			  S.add(s+1);
        		  }
    		  }
    	  //delete
    	  for (int i=0;i<w.length();i++)    	  
    	  {
    			  nw=w.substring(0,i)
    					  +w.substring(i+1,w.length());
    			  if (word2.compareTo(nw)==0) return s+1;
        		  if (!H.contains(nw))
        		  {
        			  Q.add(nw);
        			  H.add(nw);
        			  S.add(s+1);
        		  }
    	  }
    	  //replace
    	  for (int i=0;i<w.length();i++)
    	  {
    		  for (int j=0;j<26;j++)
    		  {
    			  if (j==(int)w.charAt(i)-(int)'a') continue;
    			  nw=w.substring(0,i)
    					  +new String(new char[] {(char)(j+(int)'a')})
    					  +w.substring(i+1,w.length());
    			  if (word2.compareTo(nw)==0) return s+1;
        		  if (!H.contains(nw))
        		  {
        			  Q.add(nw);
        			  H.add(nw);
        			  S.add(s+1);
        		  }
    		  }
    	  }
      }
      return 0;
  }
}
//t72_2
//By:https://leetcode.com/problems/edit-distance/discuss/25846/C%2B%2B-O(n)-space-DP
//t=7ms, o=34MB
//t:0.7227, o:0.01
class Solution72_2 {
	  public int minDistance(String word1, String word2) {
		  int len1=word1.length(),len2=word2.length();
		  int [][]d=new int[len1+1][];//d[i][j]=minimum operation times for Word1[0,i) changing to Word2[0,j)
		  for (int i=0;i<=len1;i++) d[i]=new int[len2+1];
		  for (int j=0;j<=len2;j++) d[0][j]=j;
		  for (int i=1;i<=len1;i++)
		  {
			  d[i][0]=i;
			  for (int j=1;j<=len2;j++)
			  {
				  if (word1.charAt(i-1)==word2.charAt(j-1)) 
					  d[i][j]=d[i-1][j-1];
				  else	d[i][j]=1+Math.min(d[i-1][j-1],//replace
						  Math.min(d[i][j-1],//insert
								  d[i-1][j]//delete
								  ));
			  }

		  }
		  return d[len1][len2];
	  }
}
//t72_3, Space compressed
//t=4ms, m=37MB
//t:0.9890, m:?
//By:https://leetcode.com/problems/edit-distance/discuss/25846/C%2B%2B-O(n)-space-DP
//That is perfect
class Solution72_3 {
	public int minDistance(String word1, String word2) {
		int len1=word1.length(),len2=word2.length();
		int []d=new int[len2+1];
		int []e=new int[len2+1],f;
		for (int j=0;j<=len2;j++) d[j]=j;
		for (int i=1;i<=len1;i++)
		{
			e[0]=i;
			for (int j=1;j<=len2;j++)
			{
				if (word1.charAt(i-1)==word2.charAt(j-1)) 
					e[j]=d[j-1];
				else	e[j]=1+Math.min(e[j-1],Math.min(d[j-1],d[j]));

			}
			f=d;
			d=e;
			e=f;
		}
		return d[len2];
	}
}
//73. Set Matrix Zeroes
//Runtime: 1 ms, faster than 99.98% of Java online submissions for Set Matrix Zeroes.
//Memory Usage: 48.5 MB, less than 100.00% of Java online submissions for Set Matrix Zeroes.
class Solution73 {
    public void setZeroes(int[][] matrix) {
        if (matrix.length==0 || matrix[0].length==0) return;
        int m=matrix.length,n=matrix[0].length;
        boolean[] row=new boolean[m];
        boolean[] col=new boolean[n];
        for (int i=0;i<m;i++)
        	for (int j=0;j<n;j++)
        		if (matrix[i][j]==0)
        		{
        			row[i]=true;col[j]=true;
        		}
        for (int i=0;i<m;i++)
        	for (int j=0;j<n;j++)
        		if (row[i]||col[j])
        		{
        			matrix[i][j]=0;
        		}
    }
}
//74. Search a 2D Matrix
//Runtime: 5 ms, faster than 73.10% of Java online submissions for Search a 2D Matrix.
//Memory Usage: 41.5 MB, less than 100.00% of Java online submissions for Search a 2D Matrix.
class Solution74 {
	int getVal(int[][] m,int cn,int x)
	{
		return m[x/cn][x %cn];
	}
	boolean search(int[][]m, int cn,int l,int r,int target)
	{
		if (l==r) return getVal(m,cn,l)==target;
		if (r==l+1) return getVal(m,cn,l)==target||getVal(m,cn,r)==target;
		int mid=(l+r)/2;
		if (getVal(m,cn,mid)==target) return true;
		return search(m,cn,l,mid-1,target)||search(m,cn,mid+1,r,target);
	}
    public boolean searchMatrix(int[][] matrix, int target) {
    	if (matrix.length==0||matrix[0].length==0) return false;
    	return search(matrix,matrix[0].length,0,
    			matrix[0].length*matrix.length-1,target);
    }
}
//75. Sort Colors
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Sort Colors.
//Memory Usage: 34.8 MB, less than 83.14% of Java online submissions for Sort Colors.
class Solution75 
{
    public void sortColors(int[] nums) 
    {
        int[] b=new int[3];
        for (int i=0;i<nums.length;i++)
            b[nums[i]]++;
        int p=0;
        for (int i=0;i<3;i++)
            for (int j=0;j<b[i];j++)
                nums[p++]=i;
    }
}
//t76. Minimum Window Substring
//QuestionGiven a string S and a string T, 
//find the minimum window in S 
//which will contain all the characters in T 
//in complexity O(n).
//t=17ms, m=39MB
//t:0.6458, m:1
//This function refers the solution on leetcode,
//a sliding window method.
class Solution76 {
	int IndFirstSince(String s,int start,int[]appct)
	{
		for (int i=start;i<s.length();i++)
			if (appct[s.charAt(i)]>0) return i;
		return s.length();
	}
	boolean check(int[] ct,int appct[])
	{
		for (int i=0;i<256;i++)
			if (appct[i]>ct[i]) return false;
		return true;
	}
	void disp(int[]appct)
	{
		for (int i=0;i<256;i++)
			if (appct[i]>0) System.out.println((char)i+": "+appct[i]+"  ");
	}
    public String minWindow(String s, String t) 
    {
        int[] appct=new int[256];
        for (int i=0;i<t.length();i++) appct[t.charAt(i)]++;
        //disp(appct);
        int minlen=Integer.MAX_VALUE,minst=0,mined=0;
        int[] ct=new int[256];
        int lp=IndFirstSince(s,0,appct),rp=lp;
        //:keep rp>=lp, appct[lp]>0
        while (rp<s.length())
        {
        	ct[s.charAt(rp)]++;
        	if (check(ct,appct))
        	{
        		if (rp-lp+1<minlen)
        		{
        			minlen=rp-lp+1;
        			minst=lp;
        			mined=rp;
        		}
        		ct[s.charAt(lp)]--;
        		lp=IndFirstSince(s,lp+1,appct);
        		while (check(ct,appct))
        		{
            		if (rp-lp+1<minlen)
            		{
            			minlen=rp-lp+1;
            			minst=lp;
            			mined=rp;
            		}
            		ct[s.charAt(lp)]--;
            		lp=IndFirstSince(s,lp+1,appct);

        		}
        		rp=IndFirstSince(s,rp+1,appct);	
        	}
        	else
        	{
        		rp=IndFirstSince(s,rp+1,appct);
        	}
        }
        return minlen<Integer.MAX_VALUE?s.substring(minst,mined+1):"";
    }
}
//77. Combinations
//Runtime: 10 ms, faster than 77.28% of Java online submissions for Combinations.
//Memory Usage: 42.7 MB, less than 100.00% of Java online submissions for Combinations.
class Solution77 {
	List<List<Integer>> ans;
	int[] stack;
	void dfs(int sp,int st,int n)
	{
		if (sp==stack.length)
		{
			List<Integer> tmp=new ArrayList<Integer>();
			for (int i=0;i<sp;i++)
				tmp.add(stack[i]);
			ans.add(tmp);
			return;
		}
		for (int i=st;i<=n;i++)
		{
			stack[sp]=i;
			dfs(sp+1,i+1,n);
		}
	}
    public List<List<Integer>> combine(int n, int k) {
        ans=new ArrayList<List<Integer>>();
        if (k==0) return ans;
        stack=new int[k];
        dfs(0,1,n);
        return ans;
    }
}
//78. Subsets
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Subsets.
//Memory Usage: 38.9 MB, less than 6.90% of Java online submissions for Subsets.
class Solution78 
{
	int[] stack,ns;
	int n;
	List<List<Integer>>  ans;
	void dfs(int sp)
	{
		if (sp==n)
		{
			List<Integer> t=new ArrayList<Integer>();
			for (int i=0;i<n;i++)
				if (stack[i]==1) t.add(ns[i]);
			ans.add(t);
			return;
		}
		stack[sp]=0;
		dfs(sp+1);
		stack[sp]=1;
		dfs(sp+1);
	}
    public List<List<Integer>> subsets(int[] nums) 
    {
        ans=new ArrayList<List<Integer>>();
        n=nums.length;
        ns=nums;
        stack=new int[n];
        dfs(0);
        return ans;
    }
}
//79. Word Search
//Runtime: 7 ms, faster than 99.62% of Java online submissions for Word Search.
//Memory Usage: 40.2 MB, less than 100.00% of Java online submissions for Word Search.
class Solution79 {
	boolean fd(char[][]b,int x,int y,char[]s,int sp)
	{
		//check if one can step into b[x][y]
		if (x<0 || x>=b.length || y<0 || y>=b[0].length
			|| b[x][y]!=s[sp] || b[x][y]=='$') return false;
		//after one step, check if succeed
		if (sp==s.length-1) return true;
		//next step
		char c=b[x][y];
		b[x][y]='$';
		if (fd(b,x+1,y,s,sp+1)) return true;
		if (fd(b,x,y+1,s,sp+1)) return true;
		if (fd(b,x-1,y,s,sp+1)) return true;
		if (fd(b,x,y-1,s,sp+1)) return true;
		b[x][y]=c;
		return false;
	}
    public boolean exist(char[][] board, String word) {
		char[] nw=word.toCharArray();
        for (int i=0;i<board.length;i++)
        	for (int j=0;j<board[0].length;j++)
        		if (fd(board,i,j,nw,0))
        			return true;
        return false;
    }
}
//80. Remove Duplicates from Sorted Array II
//Runtime: 5 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted Array II.
//Memory Usage: 40.6 MB, less than 20.31% of Java online submissions for Remove Duplicates from Sorted Array II.
class Solution80 
{
    public int removeDuplicates(int[] nums) 
    {
    	int rp=0,wp=0,n=nums.length;
    	while (rp<n)
    	{
    		if (rp==n-1)
    		{
    			nums[wp]=nums[rp];
    			rp++;wp++;
    		}
    		else
    		{
    			if (nums[rp+1]!=nums[rp])
    			{
    				nums[wp]=nums[rp];
    				rp++;wp++;
    			}
    			else
    			{
    				nums[wp]=nums[rp];
    				nums[wp+1]=nums[rp+1];
    				rp+=2;
    				wp+=2;
    				while (rp<n && nums[rp]==nums[wp-1]) rp++;
    			}
    		}
    	}
        return wp;
    }
}
public class LC71_80 {
	public static void main(String[] args)
	{
		char[][]b= {{'A','B','C','E'},
				{'S','F','C','S'},
				{'A','D','E','E'}};
		System.out.println(new Solution79()
  			  .exist(b,"ABCCED"));

	}

}
