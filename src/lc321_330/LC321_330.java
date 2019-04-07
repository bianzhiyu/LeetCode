package lc321_330;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//321. Create Maximum Number
//Time Limit Exceeded
//85 / 102 test cases passed.
class Solution321
{
	private int[] toIntArr(String str)
	{
		int len = str.length();
		int[] a = new int[len];
		for (int i = 0; i < len; i++)
			a[i] = str.charAt(i) - '0';
		return a;
	}

	private String max(String[] a)
	{
		String str = a[0];
		for (int i = 1; i < 4; i++)
			if (str.compareTo(a[i]) < 0)
				str = a[i];
		return str;
	}

	public int[] maxNumber(int[] nums1, int[] nums2, int k)
	{
		int len1 = nums1.length, len2 = nums2.length;
		String[][][] d = new String[len1 + 1][len2 + 1][k + 1];
		for (int i = 0; i <= len1; i++)
			for (int j = 0; j <= len2; j++)
				for (int l = 0; l <= k; l++)
					d[i][j][l] = "";
		for (int l = 1; l <= k && l <= len2; l++)
			d[len1][len2 - l][l] = nums2[len2 - l] + d[len1][len2 - l + 1][l - 1];
		for (int l = 1; l <= k && l <= len1; l++)
			d[len1 - l][len2][l] = nums1[len1 - l] + d[len1 - l + 1][len2][l - 1];

		for (int l = 1; l <= k; l++)
		{
			for (int i = len1; i >= 0; i--)
				for (int j = Math.min(len2, len1 - i + len2 - l); j >= 0; j--)
				{
					d[i][j][l] = max(new String[]
					{ j < len2 ? d[i][j + 1][l] : "", i < len1 ? d[i + 1][j][l] : "",
							i < len1 ? nums1[i] + d[i + 1][j][l - 1] : "",
							j < len2 ? nums2[j] + d[i][j + 1][l - 1] : "" });
				}
		}
		return toIntArr(d[0][0][k]);
	}
}

//Runtime: 280 ms, faster than 5.14% of Java online submissions for Create Maximum Number.
//Memory Usage: 77.6 MB, less than 7.69% of Java online submissions for Create Maximum Number.
class Solution321_2
{
	private int[] foo(int[] nums1, int[] nums2, int k)
	{
		int len1 = nums1.length, len2 = nums2.length;
		int[][] sel = new int[len1][len2];
		for (int i = len1 - 1; i >= 0; i--)
			for (int j = len2 - 1; j >= 0; j--)
			{
				if (nums1[i] > nums2[j])
					sel[i][j] = 1;
				else if (nums1[i] < nums2[j])
					sel[i][j] = 2;
				else if (i == len1 - 1)
					sel[i][j] = 2;
				else if (j == len2 - 1)
					sel[i][j] = 1;
				else
					sel[i][j] = sel[i + 1][j + 1];
			}
		int p1 = 0, p2 = 0, i = 0;
		int[] ans = new int[k];
		while (p1 < len1 && p2 < len2)
		{
			if (sel[p1][p2] == 1)
				ans[i++] = nums1[p1++];
			else
				ans[i++] = nums2[p2++];
		}
		while (p2 < len2)
			ans[i++] = nums2[p2++];
		while (p1 < len1)
			ans[i++] = nums1[p1++];
		return ans;
	}

	private List<int[]> extractBest(int[] n, int k)
	{
		int len = n.length;
		List<int[]> l = new ArrayList<int[]>();
		l.add(new int[0]);
		String[][] d = new String[Math.min(k, len) + 1][len];
		for (int i = 0; i < d.length; i++)
			for (int j = 0; j < len; j++)
				d[i][j] = "";
		for (int i = 1; i < d.length; i++)
			for (int j = len - 1; j >= 0; j--)
			{
				String s1 = j == len - 1 ? "" : d[i][j + 1];
				String s2 = n[j] + (j == len - 1 ? "" : d[i - 1][j + 1]);
				if (s1.length() < i)
					s1 = "";
				if (s2.length() < i)
					s2 = "";
				d[i][j] = s1.compareTo(s2) > 0 ? s1 : s2;
			}
		for (int i = 1; i < d.length; i++)
		{
			String s = d[i][0];
			int[] t = new int[s.length()];
			for (int j = 0; j < t.length; j++)
				t[j] = s.charAt(j) - '0';
			l.add(t);
		}
		return l;
	}

	private static class cmpArr implements Comparator<int[]>
	{
		private int k;

		public cmpArr(int _k)
		{
			k = _k;
		}

		@Override
		public int compare(int[] o1, int[] o2)
		{
			for (int i = 0; i < k; i++)
				if (o1[i] < o2[i])
					return 1;
				else if (o1[i] > o2[i])
					return -1;
			return 0;
		}
	}

	public int[] maxNumber(int[] nums1, int[] nums2, int k)
	{
		List<int[]> s1 = extractBest(nums1, k);
		List<int[]> s2 = extractBest(nums2, k);
		List<int[]> cand = new ArrayList<int[]>();
		for (int i = Math.max(0, k - nums2.length); i <= nums1.length && i <= k; i++)
			cand.add(foo(s1.get(i), s2.get(k - i), k));
		Collections.sort(cand, new cmpArr(k));
		return cand.get(0);
	}
}

//other's
//6ms
class Solution321_3
{
	public int[] maxNumber(int[] nums1, int[] nums2, int k)
	{
		int len1 = nums1.length;
		int len2 = nums2.length;
		int[] result = new int[k];
		for (int l = Math.max(0, k - len2); l <= len1 && l <= k; l++)
		{
			int[] max1 = getMax(nums1, l);
			int[] max2 = getMax(nums2, k - l);
			int[] max = merge(max1, max2);
			if (isGreater(max, 0, result, 0))
			{
				result = max;
			}
		}
		return result;
	}

	private int[] getMax(int[] nums, int digits)
	{
		int[] result = new int[digits];
		int index = 0;
		for (int i = 0; i < nums.length; i++)
		{
			while (index > 0 && nums.length - i > digits - index && nums[i] > result[index - 1])
			{
				index--;
			}
			if (index < digits)
			{
				result[index++] = nums[i];
			}
		}
		return result;
	}

	private int[] merge(int[] max1, int[] max2)
	{
		int[] result = new int[max1.length + max2.length];
		int index = 0;
		int index1 = 0;
		int index2 = 0;
		while (index < result.length)
		{
			if (isGreater(max1, index1, max2, index2))
			{
				result[index++] = max1[index1++];
			} else
			{
				result[index++] = max2[index2++];
			}
		}
		return result;
	}

	private boolean isGreater(int[] nums1, int index1, int[] nums2, int index2)
	{
		while (index1 < nums1.length && index2 < nums2.length)
		{
			if (nums1[index1] == nums2[index2])
			{
				index1++;
				index2++;
			} else
			{
				return nums1[index1] > nums2[index2];
			}
		}
		return index2 == nums2.length;
	}
}

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
	final static int[][] di = new int[][]
	{
			{ 1, 0 },
			{ 0, 1 },
			{ -1, 0 },
			{ 0, -1 } };
	boolean[][] checked;
	int[][] maxLen;
	int[][] mat;
	int m, n;

	public int longestIncreasingPath(int[][] matrix)
	{
		if (matrix.length == 0 || matrix[0].length == 0)
			return 0;
		mat = matrix;
		m = matrix.length;
		n = matrix[0].length;
		maxLen = new int[m][n];
		checked = new boolean[m][n];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				dfs(i, j);
		int max = 0;
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				if (maxLen[i][j] > max)
					max = maxLen[i][j];
		return max;
	}

	void dfs(int x, int y)
	{
		if (checked[x][y])
			return;
		int max = 0;
		for (int i = 0; i < 4; i++)
		{
			int nx = x + di[i][0];
			int ny = y + di[i][1];
			if (nx >= 0 && ny >= 0 && nx < m && ny < n && mat[nx][ny] > mat[x][y])
			{
				dfs(nx, ny);
				if (maxLen[nx][ny] > max)
					max = maxLen[nx][ny];
			}
		}
		checked[x][y] = true;
		maxLen[x][y] = max + 1;
	}
}

public class LC321_330
{
	public static void test321()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input321.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output321.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int[] n1 = test.Test.parseIntArr(inLine);
				int[] n2 = test.Test.parseIntArr(bfr.readLine());
				int k = Integer.parseInt(bfr.readLine());

				Solution321_2 s = new Solution321_2();

				int[] ans = s.maxNumber(n1, n2, k);

				String out = test.Test.intArrToString(ans);

				System.out.println(out);

				bfw.write(out);
				bfw.newLine();
			}

			bfr.close();
			bfw.flush();
			bfw.close();
		} catch (IOException e)
		{
			System.out.println(e.toString());
		}
	}

	public static void main(String[] args)
	{
		test321();
	}
}
