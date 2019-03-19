package lc321_330;

import java.util.Arrays;

//Runtime: 16 ms, faster than 79.75% of Java online submissions for Coin Change.
//Memory Usage: 38.1 MB, less than 26.65% of Java online submissions for Coin Change.
class Solution322
{
	public int coinChange(int[] coins, int amount)
	{
		if (amount == 0)
			return 0;
		int[] d = new int[amount + 1];
		Arrays.fill(d, -1);
		d[0] = 0;
		for (int i = 1; i <= amount; i++)
			for (int j = 0; j < coins.length; j++)
				if (i >= coins[j] && d[i - coins[j]] != -1)
					if (d[i] == -1)
						d[i] = d[i - coins[j]] + 1;
					else if (d[i - coins[j]] + 1 < d[i])
						d[i] = d[i - coins[j]] + 1;

		return d[amount];
	}
}

class ListNode
{
	int val;
	ListNode next;

	ListNode(int x)
	{
		val = x;
	}
}

//328. Odd Even Linked List
//Runtime: 3 ms, faster than 73.77% of Java online submissions for Odd Even Linked List.
//Memory Usage: 37.8 MB, less than 23.71% of Java online submissions for Odd Even Linked List.
class Solution328
{
	public ListNode oddEvenList(ListNode head)
	{
		if (head == null)
			return null;
		ListNode odd = new ListNode(0);
		ListNode even = new ListNode(0);
		ListNode p = head, po = odd, pe = even;
		while (p != null)
		{
			po.next = new ListNode(p.val);
			po = po.next;
			p = p.next;
			if (p != null)
			{
				pe.next = new ListNode(p.val);
				p = p.next;
				pe = pe.next;
			}
		}
		po.next = even.next;
		return odd.next;
	}
}

//329. Longest Increasing Path in a Matrix
//Runtime: 8 ms, faster than 84.19% of Java online submissions for Longest Increasing Path in a Matrix.
//Memory Usage: 34.4 MB, less than 100.00% of Java online submissions for Longest Increasing Path in a 
class Solution329
{
	final static int[][] di=new int[][] {{1,0},{0,1},{-1,0},{0,-1}};
	boolean[][]checked;
	int[][]maxLen;
	int[][]mat;
	int m,n;
	public int longestIncreasingPath(int[][] matrix)
	{
		if (matrix.length==0 || matrix[0].length==0) return 0;
		mat=matrix;
		m=matrix.length;n=matrix[0].length;
		maxLen=new int[m][n];
		checked=new boolean[m][n];
		for (int i=0;i<m;i++)
			for (int j=0;j<n;j++)
				dfs(i,j);
		int max=0;
		for (int i=0;i<m;i++)
			for (int j=0;j<n;j++)
				if (maxLen[i][j]>max)
					max=maxLen[i][j];
		return max;
	}
	void dfs(int x,int y)
	{
		if (checked[x][y]) return;
		int max=0;
		for (int i=0;i<4;i++)
		{
			int nx=x+di[i][0];
			int ny=y+di[i][1];
			if (nx>=0 && ny>=0 && nx<m && ny<n && mat[nx][ny]>mat[x][y])
			{
				dfs(nx,ny);
				if (maxLen[nx][ny]>max)
					max=maxLen[nx][ny];
			}
		}
		checked[x][y]=true;
		maxLen[x][y]=max+1;
	}
}

public class LC321_330
{
	public static void main(String[] args)
	{

	}
}
