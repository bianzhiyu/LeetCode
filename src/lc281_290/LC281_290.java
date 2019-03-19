package lc281_290;

import java.util.Arrays;
import java.util.Iterator;

//283. Move Zeroes
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Move Zeroes.
//Memory Usage: 39.6 MB, less than 54.60% of Java online submissions for Move Zeroes.
class Solution283 
{
    public void moveZeroes(int[] nums) 
    {
    	int l=nums.length,wp=0,rp=0;
    	while (rp<nums.length)
    	{
    		if (nums[rp]!=0)
    		{
    			nums[wp]=nums[rp];
    			wp++;rp++;
    		}
    		else rp++;
    	}
    	while (wp<l) nums[wp++]=0;
        
    }
}

///284. Peeking Iterator
//Runtime: 48 ms, faster than 95.29% of Java online submissions for Peeking Iterator.
//Memory Usage: 37 MB, less than 25.99% of Java online submissions for Peeking Iterator.
//Java Iterator interface reference:
//https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
 Iterator<Integer> ite;
 boolean stored;
 int temp;
 

	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
     ite=iterator;
     stored=false;
	    temp=0;
	}

 // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
     if (!stored)
     {
         temp=ite.next();
         stored=true;
     }
     return temp;
     
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
     if (stored)
     {
         stored=false;
         return temp;
     }
     else
     {
         return ite.next();
     }
	    
	}

	@Override
	public boolean hasNext() {
     return stored || ite.hasNext();
     
	    
	}
}


//287. Find the Duplicate Number
//Runtime: 4 ms, faster than 38.04% of Java online submissions for Find the Duplicate Number.
//Memory Usage: 36 MB, less than 77.58% of Java online submissions for Find the Duplicate Number.

class Solution287 
{
	public int findDuplicate(int[] nums) 
	{
		Arrays.sort(nums);
		for (int i=0;i<nums.length-1;i++)
			if (nums[i]==nums[i+1]) return nums[i];
		return 0;
	}
}

//289. Game of Life
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Game of Life.
//Memory Usage: 37.4 MB, less than 17.31% of Java online submissions for Game of Life.
class Solution289 
{
	final static int[][]di=new int[][]
			{{1,-1},{1,0},{1,1},{0,1},{0,-1},{-1,-1},{-1,0},{-1,1}};
    public void gameOfLife(int[][] board) 
    {
        int m=board.length,n=board[0].length;
        int[][] nb=new int[m][n];
        for (int i=0;i<m;i++)
        	for (int j=0;j<n;j++)
        	{
        		int t=0;
        		for (int k=0;k<8;k++)
        		{
        			int nx=i+di[k][0];
        			int ny=j+di[k][1];
        			if (nx>=0 && ny>=0 && nx<m && ny<n) t+=board[nx][ny];
        		}
        		if (board[i][j]==1)
        		{
        			if (2<=t && t<=3) nb[i][j]=1;
        			else nb[i][j]=0;
        		}
        		else
        		{
        			if (t==3) nb[i][j]=1;
        		}
        	}
        for (int i=0;i<m;i++)
        	for (int j=0;j<n;j++)
        		board[i][j]=nb[i][j];
    }
}


public class LC281_290 {
	public static void main(String[] args)
	{
//		String a="ddd";
	}
}
