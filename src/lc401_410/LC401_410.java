package lc401_410;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//401. Binary Watch
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Watch.
//Memory Usage: 37.1 MB, less than 97.96% of Java online submissions for Binary Watch.
class Solution401
{
	private void dfs(List<String> ans, int st, int[] stack, int num, int t)
	{
		if (st == 10)
		{
			if (t != num)
				return;
			int h = (stack[0] << 3) + (stack[1] << 2) + (stack[2] << 1) + stack[3];
			int m = (stack[4] << 5) + (stack[5] << 4) + (stack[6] << 3) + (stack[7] << 2) + (stack[8] << 1)
					+ (stack[9]);
			if (h > 11)
				return;
			if (m > 59)
				return;
			String s = Integer.toString(h);
			if (m < 10)
				s = s + ":0" + m;
			else
				s = s + ":" + m;
			ans.add(s);
			return;
		}
		if (t == num)
		{
			stack[st] = 0;
			dfs(ans, st + 1, stack, num, t);
		} else
		{
			if (9 - st >= num - t)
			{
				stack[st] = 0;
				dfs(ans, st + 1, stack, num, t);
			}
			stack[st] = 1;
			dfs(ans, st + 1, stack, num, t + 1);
		}
	}

	public List<String> readBinaryWatch(int num)
	{
		List<String> ans = new ArrayList<String>();
		dfs(ans, 0, new int[10], num, 0);
		return ans;
	}
}

//402. Remove K Digits
//Runtime: 3 ms, faster than 98.22% of Java online submissions for Remove K Digits.
//Memory Usage: 37.5 MB, less than 82.45% of Java online submissions for Remove K Digits.

//https://leetcode.com/problems/remove-k-digits/discuss/88708/Straightforward-Java-Solution-Using-Stack
//He gives the intuition.
//I do come calculation to make sure that is right.
class Solution402
{
	public String removeKdigits(String num, int k)
	{
		char[] stack = new char[10003];
		int sp = 0, rp = 0, slen = num.length();
		while (rp < slen)
		{
			if (sp == 0)
			{
				stack[sp++] = num.charAt(rp++);
			} else
			{
				while (k > 0 && sp > 0 && num.charAt(rp) < stack[sp - 1])
				{
					k--;
					sp--;
				}
				stack[sp++] = num.charAt(rp++);
			}
		}
		StringBuilder s = new StringBuilder();
		sp -= k;
		int i = 0;
		while (i < sp && stack[i] == '0')
			i++;
		if (i == sp)
			return "0";
		while (i < sp)
			s.append(stack[i++]);
		return s.toString();
	}
}

//403. Frog Jump
//Runtime: 184 ms, faster than 9.32% of Java online submissions for Frog Jump.
//Memory Usage: 50.2 MB, less than 100.00% of Java online submissions for Frog Jump.
class Solution403
{
	public boolean canCross(int[] stones)
	{
		// Arrays.sort(stones);
		int L = stones.length;
		if (L <= 1 || stones[1] - stones[0] > 1)
			return false;
		int[][] D = new int[L + 1][L + 1];
		int[] lenD = new int[L + 1];
		lenD[0] = 0;
		lenD[1] = 1;
		D[1][0] = 0;
		for (int i = 2; i < L; i++)
			for (int j = 1; j < i; j++)
			{
				for (int k = 0; k < lenD[j]; k++)
				{
					int jumplen = stones[j] - D[j][k];
					if (jumplen - 1 <= stones[i] - stones[j] && jumplen + 1 >= stones[i] - stones[j])
					{
						lenD[i]++;
						D[i][lenD[i] - 1] = stones[j];
						break;
					}
				}
			}
		return lenD[L - 1] > 0;
	}
}

//406. Queue Reconstruction by Height
//Runtime: 15 ms, faster than 62.08% of Java online submissions for Queue Reconstruction by Height.
//Memory Usage: 45.4 MB, less than 66.80% of Java online submissions for Queue Reconstruction by Height.
class Solution406
{
	public static void swap(int[][] a, int p1, int p2)
	{
		int t = a[p1][0];
		a[p1][0] = a[p2][0];
		a[p2][0] = t;
		t = a[p1][1];
		a[p1][1] = a[p2][1];
		a[p2][1] = t;
	}

	public static int compareTo(int[] a, int[] b)
	{
		if (a[0] != b[0])
			return a[0] - b[0];
		return a[1] - b[1];
	}

	public static void qs(int[][] a, int l, int r)
	{
		if (l >= r)
			return;
		int pivotInd = l;
		int[] pivot = new int[]
		{ a[pivotInd][0], a[pivotInd][1] };
		swap(a, pivotInd, r - 1);
		int storeInd = l;
		for (int i = l; i < r - 1; i++)
			if (compareTo(a[i], pivot) < 0)
			{
				swap(a, i, storeInd);
				storeInd++;
			}
		swap(a, r - 1, storeInd);
		qs(a, l, storeInd);
		qs(a, storeInd + 1, r);
	}

	public int[][] reconstructQueue(int[][] people)
	{
		int len = people.length;
		qs(people, 0, len);
		int[][] ans = new int[len][2];
		boolean[] used = new boolean[len];
		int i = 0, h = 0, ion = 0, ct = 0, p = 0;
		while (i < len)
		{
			p = 0;
			ct = 0;
			ion = people[i][1];
			h = people[i][0];
			while (true)
			{
				if (used[p])
				{
					if (ans[p][0] >= h)
						ct++;
					p++;
				} else
				{
					if (ct == ion)
						break;
					ct++;
					p++;
				}
			}
			ans[p][0] = h;
			ans[p][1] = ct;
			used[p] = true;
			i++;
		}
		return ans;
	}
}

//Runtime: 12 ms, faster than 68.98% of Java online submissions for Queue Reconstruction by Height.
//Memory Usage: 39.9 MB, less than 98.74% of Java online submissions for Queue Reconstruction by Height.
class Solution406_2
{
	public static void swap(int[][] a, int p1, int p2)
	{
		int t = a[p1][0];
		a[p1][0] = a[p2][0];
		a[p2][0] = t;
		t = a[p1][1];
		a[p1][1] = a[p2][1];
		a[p2][1] = t;
	}

	public static int compareTo(int[] a, int[] b)
	{
		if (a[0] != b[0])
			return a[0] - b[0];
		return a[1] - b[1];
	}

	public static void qs(int[][] a, int l, int r)
	{
		if (l >= r)
			return;
		int pivotInd = l;
		int[] pivot = new int[]
		{ a[pivotInd][0], a[pivotInd][1] };
		swap(a, pivotInd, r - 1);
		int storeInd = l;
		for (int i = l; i < r - 1; i++)
			if (compareTo(a[i], pivot) < 0)
			{
				swap(a, i, storeInd);
				storeInd++;
			}
		swap(a, r - 1, storeInd);
		qs(a, l, storeInd);
		qs(a, storeInd + 1, r);
	}

	public int[][] reconstructQueue(int[][] people)
	{
		int len = people.length;
		qs(people, 0, len);
		int[][] ans = new int[len][2];
		boolean[] used = new boolean[len];
		int i = 0, h = 0, ion = 0, ct = 0, p = 0;
		while (i < len)
		{
			ion = people[i][1];
			h = people[i][0];
			if (i > 0 && h == people[i - 1][0])
			{
				p++;
				ct++;
			} else
			{
				p = 0;
				ct = 0;
			}
			while (true)
			{
				if (used[p])
				{
					if (ans[p][0] >= h)
						ct++;
					p++;
				} else
				{
					if (ct == ion)
						break;
					ct++;
					p++;
				}
			}
			ans[p][0] = h;
			ans[p][1] = ct;
			used[p] = true;
			i++;
		}
		return ans;
	}
}

//410. Split Array Largest Sum
//Time Limit Exceeded
class Solution410
{
	private int min = Integer.MAX_VALUE;
	private int[] sumUntil;

	private void dfs(int[] A, int b, int st, int max)
	{
		if (b == 0)
		{
			int lastSum = sumUntil[A.length - 1] - sumUntil[st - 1];
			if (lastSum > max)
				max = lastSum;
			if (max < min)
				min = max;
			return;
		}
		int tmp = 0;
		for (int i = st; i + b < A.length; i++)
		{
			tmp += A[i];
			if (tmp > min)
				break;
			dfs(A, b - 1, i + 1, max > tmp ? max : tmp);
		}
	}

	public int splitArray(int[] nums, int m)
	{
		sumUntil = new int[nums.length];
		sumUntil[0] = nums[0];
		for (int i = 1; i < nums.length; i++)
			sumUntil[i] = sumUntil[i - 1] + nums[i];
		if (m == 1)
			return sumUntil[nums.length - 1];
		dfs(nums, m - 1, 0, 0);
		return min;
	}
}

// Runtime: 26 ms, faster than 21.33% of Java online submissions for Split Array Largest Sum.
// Memory Usage: 36.2 MB, less than 94.44% of Java online submissions for Split Array Largest Sum.
class Solution410_2
{
	private int[] sumUntil;

	private int partSum(int l, int r)
	{
		if (l == 0)
			return sumUntil[r];
		return sumUntil[r] - sumUntil[l - 1];
	}

	public int splitArray(int[] nums, int m)
	{
		int len = nums.length;
		sumUntil = new int[len];
		sumUntil[0] = nums[0];
		for (int i = 1; i < len; i++)
			sumUntil[i] = sumUntil[i - 1] + nums[i];
		int[][] d = new int[m][len];
		for (int i = 0; i < len; i++)
			d[0][i] = partSum(0, i);
		for (int i = 1; i < m; i++)
			for (int j = 0; j < len; j++)
			{
				d[i][j] = Integer.MAX_VALUE;
				for (int k = i - 1; k < j; k++)
					d[i][j] = Math.min(d[i][j], Math.max(d[i - 1][k], partSum(k + 1, j)));
			}
		return d[m - 1][len - 1];
	}
}

//Same as 1011
// Runtime: 0 ms, faster than 100.00% of Java online submissions for Split Array Largest Sum.
// Memory Usage: 35.8 MB, less than 94.44% of Java online submissions for Split Array Largest Sum.
class Solution410_3
{
	private boolean judge(int[] A, int Lt, int grps)
	{
		int rem = 0, used = 0;
		for (int i = 0; i < A.length; i++)
		{
			if (rem >= A[i])
				rem -= A[i];
			else
			{
				rem = Lt - A[i];
				used++;
			}
			if (used > grps)
				return false;
		}
		return true;
	}

	public int splitArray(int[] nums, int m)
	{
		int L = 0, R = 0;
		for (int i = 0; i < nums.length; i++)
		{
			R += nums[i];
			if (nums[i] > L)
				L = nums[i];
		}
		while (L < R)
		{
			if (R == L + 1)
			{
				if (judge(nums, L, m))
					return L;
				else
					return R;
			} else
			{
				int mid = (L + R) / 2;
				if (judge(nums, mid, m))
					R = mid;
				else
					L = mid + 1;
			}
		}
		return L;
	}
}

public class LC401_410
{
	public static void test410()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input410.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output410.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int[] nums = test.Test.parseIntArr(inLine);

				int m = Integer.parseInt(bfr.readLine());

				Solution410_2 s = new Solution410_2();

				int ans = s.splitArray(nums, m);

				System.out.println("Ans=" + ans);
				System.out.println("Ans=" + new Solution410_3().splitArray(nums, m));

				bfw.write("" + ans);
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
		test410();
	}
}
