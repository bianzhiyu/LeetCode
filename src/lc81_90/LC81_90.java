package lc81_90;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution81
{
	public boolean search(int[] nums, int target)
	{
		for (int i = 0; i < nums.length; i++)
			if (nums[i] == target)
				return true;
		return false;
	}
}

//82. Remove Duplicates from Sorted List II
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted List II.
//Memory Usage: 37.4 MB, less than 70.29% of Java online submissions for Remove Duplicates from Sorted List II.
class Solution82
{
	public ListNode deleteDuplicates(ListNode head)
	{
		ListNode fakeHead = new ListNode(0);
		fakeHead.next = head;
		ListNode p = fakeHead;
		while (p != null && p.next != null)
		{
			ListNode q = p.next;
			if (q.next == null || q.next.val != q.val)
			{
				p = q;
			} else
			{
				while (q.next != null && q.next.val == p.next.val)
					q = q.next;
				p.next = q.next;
			}
		}
		return fakeHead.next;
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

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
//83. Remove Duplicates from Sorted List
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Remove Duplicates from Sorted List.
//Memory Usage: 37.6 MB, less than 100.00% of Java online submissions for Remove Duplicates from Sorted List.
class Solution83
{
	public ListNode deleteDuplicates(ListNode head)
	{
		ListNode p = head;
		while (p != null)
		{
			if (p.next != null)
			{
				ListNode q = p.next;
				while (q != null && q.val == p.val)
				{
					p.next = q.next;
					q = q.next;
				}
			}
			p = p.next;
		}
		return head;

	}
}

//t84. Largest Rectangle in Histogram
//t=235ms, m=39MB
//t:0.1898, m:0.0095
class Solution84
{
	public int largestRectangleArea(int[] heights)
	{
		int l = heights.length, maxarea = 0;
		for (int i = 0; i < l; i++)
		{
			int minh = Integer.MAX_VALUE;
			if (i > 0 && heights[i - 1] >= heights[i])
				continue;
			for (int j = i; j < l; j++)
			{
				if (heights[j] == 0)
					break;
				if (heights[j] < minh)
					minh = heights[j];
				if (minh * (j - i + 1) > maxarea)
					maxarea = minh * (j - i + 1);
			}
		}
		return maxarea;
	}
}

//t84_2
//t=2ms, m=41MB
//t:0.9930, m:0.0095
class Solution84_2
{
	public int largestRectangleArea(int[] heights)
	{
		if (heights.length == 0)
			return 0;
		int l = heights.length, maxarea = 0;
		int[] rb = new int[l];
		int[] lb = new int[l];
		lb[0] = -1;
		rb[l - 1] = l;
		for (int i = 1; i < l; i++)
		{
			int p = i - 1;
			while (p >= 0 && heights[p] >= heights[i])
				p = lb[p];
			lb[i] = p;
		}
		for (int j = l - 2; j >= 0; j--)
		{
			int p = j + 1;
			while (p < l && heights[p] >= heights[j])
				p = rb[p];
			rb[j] = p;
		}
		for (int i = 0; i < l; i++)
			if (maxarea < heights[i] * (rb[i] - lb[i] - 1))
				maxarea = heights[i] * (rb[i] - lb[i] - 1);
		return maxarea;
	}
}

//t85. Maximal Rectangle
//t=19ms, m=40MB
//t:0.5721, m:0.0133
class Solution85
{
	int[][] s;

	boolean filled(int i, int j, int x, int y)
	{
		return (x - i + 1) * (y - j + 1) == s[x][y] - (i > 0 ? s[i - 1][y] : 0) - (j > 0 ? s[x][j - 1] : 0)
				+ (i > 0 && j > 0 ? s[i - 1][j - 1] : 0);
	}

	public int maximalRectangle(char[][] matrix)
	{
		if (matrix.length == 0)
			return 0;
		int h = matrix.length, l = matrix[0].length;
		s = new int[h][];
		for (int i = 0; i < h; i++)
			s[i] = new int[l];
		s[0][0] = (int) matrix[0][0] - (int) '0';
		for (int j = 1; j < l; j++)
			s[0][j] = s[0][j - 1] + ((int) matrix[0][j] - (int) '0');
		for (int i = 1; i < h; i++)
		{
			s[i][0] = s[i - 1][0] + ((int) matrix[i][0] - (int) '0');
			for (int j = 1; j < l; j++)
				s[i][j] = s[i][j - 1] + s[i - 1][j] + ((int) matrix[i][j] - (int) '0') - s[i - 1][j - 1];
		}
		int maxarea = 0;
		for (int i = 0; i < h; i++)
			for (int j = 0; j < l; j++)
				for (int k = i; k < h; k++)
					if (matrix[k][j] == '0')
					{
						break;
					} else
					{
						for (int a = j + Math.max(0, maxarea / (k - i + 1) - 1); a < l; a++)
							if (!filled(i, j, k, a))
								break;
							else
								maxarea = Math.max(maxarea, (k - i + 1) * (a - j + 1));
					}
		return maxarea;
	}
}

//t85_2
//use the method of t84
//t=7ms, m=41MB
//t:0.9707, m:0.0118
class Solution85_2
{
	int[] heights;
	int[] rb;
	int[] lb;

	public int largestRectangleArea()
	{
		if (heights.length == 0)
			return 0;
		int l = heights.length, maxarea = 0;
		lb[0] = -1;
		rb[l - 1] = l;
		for (int i = 1; i < l; i++)
		{
			int p = i - 1;
			while (p >= 0 && heights[p] >= heights[i])
				p = lb[p];
			lb[i] = p;
		}
		for (int j = l - 2; j >= 0; j--)
		{
			int p = j + 1;
			while (p < l && heights[p] >= heights[j])
				p = rb[p];
			rb[j] = p;
		}
		for (int i = 0; i < l; i++)
			if (maxarea < heights[i] * (rb[i] - lb[i] - 1))
				maxarea = heights[i] * (rb[i] - lb[i] - 1);
		return maxarea;
	}

	public int maximalRectangle(char[][] matrix)
	{
		if (matrix.length == 0)
			return 0;
		int h = matrix.length, l = matrix[0].length;
		heights = new int[l];
		rb = new int[l];
		lb = new int[l];
		int maxarea = 0;
		for (int i = 0; i < h; i++)
		{
			for (int j = 0; j < l; j++)
			{
				if (matrix[i][j] == '1')
					heights[j] = heights[j] + 1;
				else
					heights[j] = 0;
			}
			maxarea = Math.max(maxarea, largestRectangleArea());
		}
		return maxarea;
	}
}

//86. Partition List
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Partition List.
//Memory Usage: 36.9 MB, less than 20.60% of Java online submissions for Partition List.
class Solution86
{
	public ListNode partition(ListNode head, int x)
	{
		ListNode ans = null, q = null;
		ListNode p = head;
		while (p != null)
		{
			if (p.val < x)
			{
				if (ans == null)
				{
					ans = new ListNode(p.val);
					q = ans;
				} else
				{
					q.next = new ListNode(p.val);
					q = q.next;
				}
			}
			p = p.next;
		}
		p = head;
		while (p != null)
		{
			if (p.val >= x)
			{
				if (ans == null)
				{
					ans = new ListNode(p.val);
					q = ans;
				} else
				{
					q.next = new ListNode(p.val);
					q = q.next;
				}
			}
			p = p.next;
		}
		return ans;
	}
}

//88. Merge Sorted Array
//Runtime: 2 ms, faster than 100.00% of Java online submissions for Merge Sorted Array.
//Memory Usage: 37.6 MB, less than 100.00% of Java online submissions for Merge Sorted Array.
class Solution88
{
	public void merge(int[] nums1, int m, int[] nums2, int n)
	{
		int p1 = m - 1, p2 = n - 1, p3 = m + n - 1;
		while (p2 >= 0)
		{
			if (p1 < 0)
			{
				while (p2 >= 0)
					nums1[p3--] = nums2[p2--];
				break;
			}
			if (nums2[p2] >= nums1[p1])
				nums1[p3--] = nums2[p2--];
			else
				nums1[p3--] = nums1[p1--];
		}

	}
}

//89. Gray Code
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Gray Code.
//Memory Usage: 35.6 MB, less than 100.00% of Java online submissions for Gray Code.
//let D[n,j] denote the j th number for int n, j=0,...,2^n-1.
//For D[n,2*k], D[n,2*k+1] to generate 
// D[n+1,4*k],D[n+1,4*k+1],D[n+1,4*k+2],D[n+1,4*k+3]:
// D[n+1,4*k]  =D[n,2*k]*2,  
// D[n+1,4*k+1]=D[n,2*k]*2+1,
// D[n+1,4*k+2]=D[n,2*k+1]*2+1,
// D[n+1,4*k+3]=D[n,2*k+1]*2
//In view of binary numbers:
// D[n+1,4*k]  =D[n,2*k]   append 0,  
// D[n+1,4*k+1]=D[n,2*k]   append 1,
// D[n+1,4*k+2]=D[n,2*k+1] append 1,
// D[n+1,4*k+3]=D[n,2*k+1] append 0
class Solution89
{
	public List<Integer> grayCode(int n)
	{
		int l = (int) Math.round(Math.pow(2, n));
		List<Integer> ans = new ArrayList<Integer>(l);
		if (n == 0)
		{
			ans.add(0);
			return ans;
		}
		if (n == 1)
		{
			ans.add(0);
			ans.add(1);
			return ans;
		}
		int[] ta = new int[l];
		ta[0] = 0;
		ta[1] = 1;
		int t = 2;
		for (int i = 2; i <= n; i++)
		{
			boolean f = true;
			for (int j = t - 1; j >= 0; j--)
			{
				if (f)
				{
					ta[2 * j] = ta[j] * 2 + 1;
					ta[2 * j + 1] = ta[j] * 2;
				} else
				{
					ta[2 * j] = ta[j] * 2;
					ta[2 * j + 1] = ta[j] * 2 + 1;
				}
				f = !f;
			}
			t = t * 2;
		}
		for (int i = 0; i < ta.length; i++)
			ans.add(ta[i]);
		return ans;
	}
}

//90. Subsets II
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Subsets II.
//Memory Usage: 38.4 MB, less than 44.22% of Java online submissions for Subsets II.
class Solution90
{
	int[] nbs = new int[1000];
	int[] cts = new int[1000];
	int[] stack;
	int nss;
	List<List<Integer>> ans;

	void dfs(int sp, int wp)
	{
		if (sp == nss)
		{
			List<Integer> tmp = new ArrayList<Integer>();
			for (int i = 0; i < wp; i++)
				tmp.add(stack[i]);
			ans.add(tmp);
			return;
		}
		dfs(sp + 1, wp);
		for (int i = 0; i < cts[sp]; i++)
		{
			stack[wp + i] = nbs[sp];
			dfs(sp + 1, wp + i + 1);
		}
	}

	public List<List<Integer>> subsetsWithDup(int[] nums)
	{
		ans = new ArrayList<List<Integer>>();
		Arrays.parallelSort(nums);
		stack = new int[nums.length];
		int i = 0;
		nss = 0;
		while (i < nums.length)
		{
			int j = i + 1;
			while (j < nums.length && nums[j] == nums[i])
				j++;
			nbs[nss] = nums[i];
			cts[nss] = j - i;
			nss++;
			i = j;
		}
		dfs(0, 0);
		return ans;
	}
}

public class LC81_90
{
	public static void main(String[] args)
	{
		int i = 0;
		for (i = 0; i < 3; i++)
			;
		Math.max(1, 2);
		System.out.println(i);
		System.out.println("Hello World! LC81_90.");
	}

}
