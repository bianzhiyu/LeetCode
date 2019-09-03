package lc41_50;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//t41.First Missing Positive    
//t=6ms, o=26MB
//t:0.6356, o:0.1543
class Solution41
{
	public static void swap(int[] nums, int a, int b)
	{
		int tmp = nums[a];
		nums[a] = nums[b];
		nums[b] = tmp;
	}

	public int firstMissingPositive(int[] nums)
	{
		int len = nums.length;
		for (int i = 0; i < len; i++)
		{
			while (nums[i] > 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i])
				swap(nums, i, nums[i] - 1);
		}
		for (int i = 0; i < len; i++)
		{
			if (nums[i] != i + 1)
			{
				return i + 1;
			}
		}
		return len + 1;
	}
}

//42. Trapping Rain Water
//Runtime: 1 ms, faster than 98.20% of Java online submissions for Trapping Rain Water.
//Memory Usage: 37.5 MB, less than 97.95% of Java online submissions for Trapping Rain Water.
class Solution42
{
	public int trap(int[] height)
	{
		if (height == null || height.length == 0)
		{
			return 0;
		}
		int len = height.length;
		int[] leftmax = new int[len], rightmax = new int[len];
		leftmax[0] = 0;
		for (int i = 1; i < len; i++)
		{
			leftmax[i] = height[i - 1];
			if (leftmax[i - 1] > leftmax[i])
			{
				leftmax[i] = leftmax[i - 1];
			}
		}
		rightmax[len - 1] = 0;
		for (int i = len - 2; i >= 0; i--)
		{
			rightmax[i] = height[i + 1];
			if (rightmax[i + 1] > rightmax[i])
			{
				rightmax[i] = rightmax[i + 1];
			}
		}
		int sum = 0;
		for (int i = 0; i < len; i++)
		{
			int m = leftmax[i];
			if (rightmax[i] < m)
			{
				m = rightmax[i];
			}
			if (m > height[i])
			{
				sum += m - height[i];
			}
		}
		return sum;
	}
}

//t43. Multiply Strings
//t=15ms, m=38.4MB
//t:0.6210, m:1
class Solution43
{
	public int[] Conv(String s)
	{
		int[] a = new int[s.length()];
		int sl = s.length();
		for (int i = 0; i < s.length(); i++)
			a[i] = (int) s.charAt(sl - 1 - i) - 48;
		return a;
	}

	public String multiply(String num1, String num2)
	{
		int[] n1 = Conv(num1), n2 = Conv(num2);
		int maxl = num1.length() + num2.length() + 1;
		int[] n3 = new int[maxl];
		for (int i = 0; i < n2.length; i++)
		{
			for (int j = 0; j < n1.length; j++)
				n3[i + j] += n1[j] * n2[i];
			for (int j = 0; j < maxl - 1; j++)
			{
				n3[j + 1] += n3[j] / 10;
				n3[j] = n3[j] % 10;
			}
		}
		int l = maxl - 1;
		while (l >= 0 && n3[l] == 0)
			l--;
		if (l == -1)
			return "0";
		char[] c = new char[l + 1];
		for (int i = 0; i <= l; i++)
			c[l - i] = (char) (n3[i] + 48);
		return new String(c);
	}
}

//t44.Wildcard Matching
//t=40ms, m=40MB
//t:0.4841, m:1
//dynamical programming
class Solution44
{
	public boolean isMatch(String s, String p)
	{
		int Ls = s.length(), Lp = p.length();

		boolean[][] d = new boolean[Lp + 1][Ls + 1];
		d[0][0] = true;
		for (int i = 1; i <= Lp; i++)
		{
			if (p.charAt(i - 1) == '?')
			{
				for (int j = 1; j <= Ls; j++)
					d[i][j] = d[i - 1][j - 1];
			} else if (p.charAt(i - 1) == '*')
			{
				if (i >= 2 && p.charAt(i - 2) == '*')
					for (int j = 0; j <= Ls; j++)
					{
						d[i][j] = d[i - 1][j];
					}
				else
					for (int j = 0; j <= Ls; j++)
					{
						for (int k = 0; k <= j; k++)
							if (d[i - 1][k])
							{
								d[i][j] = true;
								break;
							}
					}
			} else
			{
				for (int j = 1; j <= Ls; j++)
					d[i][j] = p.charAt(i - 1) == s.charAt(j - 1) && d[i - 1][j - 1];
			}
		}
		return d[Lp][Ls];
	}
}

//t45.Jump Game II
//t=436ms, o=29MB
//t:0.0633, o:0.2695
class Solution45
{
	public int jump(int[] nums)
	{
		int L = nums.length;
		int[] s = new int[L];
		int MAX = 1000000000;

		s[L - 1] = 0;
		for (int i = L - 2; i >= 0; i--)
		{
			s[i] = MAX;
			for (int j = i + 1; (j < L) && (j < i + nums[i] + 1); j++)
				if (s[j] + 1 < s[i])
					s[i] = s[j] + 1;
		}
		return s[0];
	}
}

//t45.Jump Game II
//t=5ms, o=29MB
//t:0.9255, o:0.3167
class Solution45_2
{
	public int jump(int[] nums)
	{
		// Actually, this is a BFS. So O(T)=O(N).
		// This question is special that we need not to save the queue.
		int j = 0, k = 0, L = nums.length, l = 0, i = 0, k2 = 0;
		while (k < L - 1)
		{
			j++;
			k2 = k;
			for (i = l; (i <= k) && (i < L); i++)
			{
				if (i + nums[i] > k2)
					k2 = i + nums[i];
				if (k2 >= L - 1)
					return j;
			}
			l = k + 1;
			k = k2;
		}
		return j;
	}
}

//46. Permutations
//t=2ms, o=27MB
//t:0.9989, o:1546
class Solution46
{
	boolean[] used;
	int[] stack;
	ArrayList<List<Integer>> data;
	int[] num;

	public void search(int p, int m)
	{
		if (p == m)
		{
			ArrayList<Integer> l = new ArrayList<Integer>();
			for (int i = 0; i < m; i++)
			{
				l.add(num[stack[i]]);
			}
			data.add(l);
			return;
		}
		for (int i = 0; i < m; i++)
		{
			if (!used[i])
			{
				stack[p] = i;
				used[i] = true;
				search(p + 1, m);
				used[i] = false;
			}
		}
	}

	public List<List<Integer>> permute(int[] nums)
	{
		int len = nums.length;
		num = nums;
		used = new boolean[len];
		data = new ArrayList<List<Integer>>();
		stack = new int[len];
		for (int i = 0; i < len; i++)
		{
			used[i] = false;
		}
		search(0, len);
		return data;
	}
}

//47. Permutations II
//Runtime: 3 ms, faster than 90.33% of Java online submissions for Permutations II.
//Memory Usage: 38.8 MB, less than 82.89% of Java online submissions for Permutations II.
class Solution47
{
	public List<List<Integer>> permuteUnique(int[] nums)
	{
		int len = nums.length;
		Arrays.sort(nums);
		List<List<Integer>> ans = new ArrayList<List<Integer>>();
		boolean fd;
		int ind = 0, ind2 = 0, tmp;
		while (true)
		{
			List<Integer> t = new ArrayList<Integer>();
			for (int i = 0; i < len; i++)
				t.add(nums[i]);
			ans.add(t);
			fd = false;
			for (int i = len - 1; i > 0; i--)
				if (nums[i] > nums[i - 1])
				{
					fd = true;
					ind = i - 1;
					ind2 = i;
					break;
				}
			if (!fd)
				break;
			for (int i = ind + 2; i < len; i++)
				if (nums[i] > nums[ind] && nums[i] < nums[ind2])
					ind2 = i;
			tmp = nums[ind];
			nums[ind] = nums[ind2];
			nums[ind2] = tmp;
			Arrays.sort(nums, ind + 1, len);
		}
		return ans;
	}
}

//t48. Rotate Image
//t=1ms, m=37MB
//t:1, m:1
class Solution48
{
	public void rotate(int[][] matrix)
	{
		int n = matrix.length, tmp;
		if (n % 2 == 0)
		{
			for (int i = 0; i < n / 2; i++)
			{

				for (int j = 0; j < n - 2 * i - 1; j++)
				{
					int x1 = i, y1 = i + j, x2 = i + j, y2 = n - 1 - i, x3 = n - 1 - i, y3 = n - 1 - i - j,
							x4 = n - 1 - i - j, y4 = i;
					System.out.println(
							"" + x1 + " " + y1 + "\n" + x2 + " " + y2 + "\n" + x3 + " " + y3 + "\n" + x4 + " " + y4);
					tmp = matrix[x1][y1];
					matrix[x1][y1] = matrix[x4][y4];
					matrix[x4][y4] = matrix[x3][y3];
					matrix[x3][y3] = matrix[x2][y2];
					matrix[x2][y2] = tmp;
				}
			}
		} else
		{
			for (int i = 0; i < n / 2; i++)
			{

				for (int j = 0; j < n - 2 * i - 1; j++)
				{
					int x1 = i, y1 = i + j, x2 = i + j, y2 = n - 1 - i, x3 = n - 1 - i, y3 = n - 1 - i - j,
							x4 = n - 1 - i - j, y4 = i;
					tmp = matrix[x1][y1];
					matrix[x1][y1] = matrix[x4][y4];
					matrix[x4][y4] = matrix[x3][y3];
					matrix[x3][y3] = matrix[x2][y2];
					matrix[x2][y2] = tmp;
				}
			}
		}
	}
}

//49. Group Anagrams
//Runtime: 185 ms, faster than 5.56% of Java online submissions for Group Anagrams.
//Memory Usage: 42.8 MB, less than 59.86% of Java online submissions for Group Anagrams.
class Solution49
{
	public List<List<String>> groupAnagrams(String[] strs)
	{
		List<char[]> l = new ArrayList<char[]>();
		int tlen = 0;
		List<List<String>> ans = new ArrayList<List<String>>();
		for (int i = 0; i < strs.length; i++)
		{
			char[] s = strs[i].toCharArray();
			Arrays.sort(s);
			int mt = -1;
			for (int j = 0; j < tlen; j++)
				if (l.get(j).length == s.length)
				{
					mt = j;
					for (int k = 0; k < s.length; k++)
						if (l.get(j)[k] != s[k])
						{
							mt = -1;
							break;
						}
					if (mt >= 0)
						break;
				}
			if (mt >= 0)
				ans.get(mt).add(strs[i]);
			else
			{
				l.add(s);
				ans.add(new ArrayList<String>());
				ans.get(tlen).add(strs[i]);
				tlen++;
			}
		}
		return ans;
	}
}

//with the same method,
//but faster:
//Runtime: 12 ms, faster than 96.03% of Java online submissions for Group Anagrams.
//Memory Usage: 45.8 MB, less than 18.51% of Java online submissions for Group Anagrams.
class Solution49_2
{
	public List<List<String>> groupAnagrams(String[] strs)
	{
		Map<String, List<String>> map = new HashMap<>();

		for (String s : strs)
		{
			char[] ca = s.toCharArray();
			Arrays.sort(ca);
			String keyStr = String.valueOf(ca);
			map.putIfAbsent(keyStr, new ArrayList<>()); // If no mapping for a key, then map a new key with an empty
														// list.
			map.get(keyStr).add(s);
		}
		return new ArrayList<>(map.values());
	}
}

//Runtime: 21 ms, faster than 38.73% of Java online submissions for Group Anagrams.
//Memory Usage: 48.7 MB, less than 5.17% of Java online submissions for Group Anagrams.
class Solution49_3
{
	public List<List<String>> groupAnagrams(String[] strs)
	{
		Map<String, List<String>> map = new HashMap<>();

		for (String s : strs)
		{
			String keyStr = encodeString(s);
			map.putIfAbsent(keyStr, new ArrayList<>());
			map.get(keyStr).add(s);
		}
		return new ArrayList<>(map.values());
	}

	private String encodeString(String s)
	{
		StringBuilder sb = new StringBuilder();
		int[] count = new int[26];
		Arrays.fill(count, 0);

		for (char c : s.toCharArray())
		{
			count[c - 'a'] += 1;
		}

		for (int i = 0; i < 26; i++)
		{
			sb.append(i + 'a').append(count[i]);
		}
		return sb.toString();
	}
}

//Runtime: 10 ms, faster than 99.90% of Java online submissions for Group Anagrams.
//Memory Usage: 43.1 MB, less than 53.09% of Java online submissions for Group Anagrams.
class Solution49_4
{
	public List<List<String>> groupAnagrams(String[] strs)
	{
		Map<Integer, List<String>> map = new HashMap<>();

		for (String s : strs)
		{
			int hashCode = computeHashCode(s);
			map.putIfAbsent(hashCode, new ArrayList<>());
			map.get(hashCode).add(s);
		}
		return new ArrayList<>(map.values());
	}

	private int computeHashCode(String s)
	{
		int[] count = new int[26];

		for (char c : s.toCharArray())
		{
			count[c - 'a'] += 1;
		}
		return Arrays.hashCode(count);
	}
}

//50. Pow(x, n)
//Runtime: 7 ms, faster than 100.00% of Java online submissions for Pow(x, n).
//Memory Usage: 38.1 MB, less than 12.80% of Java online submissions for Pow(x, n).
class Solution50
{
	public double myPow(double x, int n)
	{
		if (n == 0 || x == 1.0)
			return 1.0;

		if (Math.abs(x) != 1 && n == Integer.MIN_VALUE)
			return 0.0;

		if (n < 0)
		{
			n = -n;
			x = 1 / x;
		}

		int sp = 0;
		int[] s = new int[32];
		while (n > 0)
		{
			s[sp] = n % 2;
			sp++;
			n /= 2;
		}
		double ans = 1;
		for (int i = 0; i < sp; i++)
		{
			if (s[i] == 1)
				ans *= x;
			x *= x;
		}
		return ans;
	}
}

//other's
//Runtime: 7 ms, faster than 100.00% of Java online submissions for Pow(x, n).
//Memory Usage: 38.1 MB, less than 12.80% of Java online submissions for Pow(x, n).
class Solution50_2
{
	public double myPow(double x, int n)
	{
		if (n == 0 || x == 1.0)
			return 1.0;

		if (Math.abs(x) != 1 && n == Integer.MIN_VALUE)
			return 0.0;

		if (n < 0)
		{
			n = -n;
			x = 1 / x;
		}

		return (n % 2) == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
	}
}

public class LC41_50
{
	static void display2d(int[][] a)
	{
		for (int i = 0; i < a.length; i++)
		{
			for (int j = 0; j < a[i].length; j++)
			{
				System.out.print(a[i][j] + "  ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args)
	{
		int[] a = new int[]
		{ 1, 2, 3, 4 };
		Arrays.sort(a, 0, 4);
	}

}
