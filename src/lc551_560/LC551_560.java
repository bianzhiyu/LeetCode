package lc551_560;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//553. Optimal Division
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Optimal Division.
//Memory Usage: 35.9 MB, less than 91.30% of Java online submissions for Optimal Division.
class Solution553
{
	public String optimalDivision(int[] nums)
	{
		if (nums.length == 0)
			return "";
		if (nums.length == 1)
			return Integer.toString(nums[0]);
		if (nums.length == 2)
			return nums[0] + "/" + nums[1];
		StringBuilder sb = new StringBuilder();
		sb.append(nums[0]).append('/').append('(');
		for (int i = 1; i < nums.length - 1; i++)
			sb.append(nums[i]).append('/');
		sb.append(nums[nums.length - 1]).append(')');
		return sb.toString();
	}
}

//554. Brick Wall
//https://leetcode.com/problems/brick-wall/discuss/212914/java-simple-code-with-hashmap
//Runtime: 11 ms, faster than 99.04% of Java online submissions for Brick Wall.
//Memory Usage: 49 MB, less than 67.44% of Java online submissions for Brick Wall.
class Solution554
{
	public int leastBricks(List<List<Integer>> wall)
	{
		HashMap<Integer, Integer> gapNumsAtPos = new HashMap<Integer, Integer>();
		int max = 0;
		for (List<Integer> row : wall)
		{
			int pos = 0;
			for (int i = 0; i < row.size() - 1; i++)
			{
				pos += row.get(i);
				if (gapNumsAtPos.containsKey(pos))
					gapNumsAtPos.put(pos, gapNumsAtPos.get(pos) + 1);
				else
					gapNumsAtPos.put(pos, 1);
				if (gapNumsAtPos.get(pos) > max)
					max = gapNumsAtPos.get(pos);
			}
		}
		return wall.size() - max;
	}
}

//556. Next Greater Element III
//https://leetcode.com/problems/next-greater-element-iii/discuss/258183/Java-Simple-Code%3A-1ms-beats-100
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Next Greater Element III.
//Memory Usage: 31.9 MB, less than 100.00% of Java online submissions for Next Greater Element III.
class Solution556
{
	public int nextGreaterElement(int num)
	{
		char[] cs = Integer.toString(num).toCharArray();
		int n = cs.length, i = n - 2;

		// - find the first digit from the right that is smaller than the previous digit
		while (i >= 0 && cs[i] >= cs[i + 1])
			i--;
		if (i < 0)
			return -1;

		// - find the smallest digit from the right that is greater than cs[i]
		int j = i + 1;
		while (j < n && cs[j] > cs[i])
			j++;

		swap(cs, i, j - 1);
		reverse(cs, i + 1, n - 1);
		return toInt(cs);
	}

	private void swap(char[] cs, int i, int j)
	{
		if (i == j)
			return;
		char tmp = cs[i];
		cs[i] = cs[j];
		cs[j] = tmp;
	}

	private void reverse(char[] cs, int i, int j)
	{
		for (int k = 0; k < (j - i + 1) / 2; k++)
		{
			swap(cs, i + k, j - k);
		}
	}

	private int toInt(char[] cs)
	{
		long res = 0;
		for (int i = 0; i < cs.length; i++)
		{
			res = res * 10 + cs[i] - '0';
			if (res > Integer.MAX_VALUE)
				return -1;
		}
		return (int) res;
	}
}

//This is the same as constructing the next greater permutation.
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Next Greater Element III.
//Memory Usage: 31.6 MB, less than 100.00% of Java online submissions for Next Greater Element III.
class Solution556_2
{
	public int nextGreaterElement(int num)
	{
		if (num < 10)
			return -1;
		int[] dgs = new int[35];
		int sp = 0;
		for (char c : Integer.toString(num).toCharArray())
			dgs[sp++] = c - '0';
		int i = sp - 2;
		while (i >= 0 && dgs[i] >= dgs[i + 1])
			i--;
		if (i == -1)
			return -1;
		int j = i + 1;
		while (j < sp && dgs[j] > dgs[i])
			j++;

		int t = dgs[i];
		dgs[i] = dgs[j - 1];
		dgs[j - 1] = t;

		i++;
		j = sp - 1;
		while (i < j)
		{
			t = dgs[i];
			dgs[i] = dgs[j];
			dgs[j] = t;
			i++;
			j--;
		}

		long sum = 0;
		for (i = 0; i < sp; i++)
			sum = sum * 10 + dgs[i];
		if (sum > (long) Integer.MAX_VALUE)
			return -1;
		return (int) sum;
	}
}

//560. Subarray Sum Equals K
//Runtime: 20 ms, faster than 57.87% of Java online submissions for Subarray Sum Equals K.
//Memory Usage: 42.1 MB, less than 5.12% of Java online submissions for Subarray Sum Equals K.
class Solution560
{
	public int subarraySum(int[] nums, int k)
	{
		HashMap<Integer, List<Integer>> hm = new HashMap<Integer, List<Integer>>();
		int len = nums.length;
		int[] s = new int[len + 1];
		s[0] = 0;
		for (int i = 1; i <= len; i++)
		{
			s[i] = s[i - 1] + nums[i - 1];
			if (!hm.containsKey(s[i]))
				hm.put(s[i], new ArrayList<Integer>());
			hm.get(s[i]).add(i);
		}
		int tot = 0;
		for (int i = 1; i <= len; i++)
		{
			if (!hm.containsKey(s[i] + k - nums[i - 1]))
				continue;
			List<Integer> l = hm.get(s[i] + k - nums[i - 1]);
			if (l.get(l.size() - 1) < i)
				continue;
			int f = 0, r = l.size() - 1;
			while (f < r)
			{
				if (r == f + 1)
				{
					if (l.get(f) >= i)
						r = f;
					else
						f = r;
					break;
				}
				int m = (f + r) / 2;
				if (l.get(m) >= i)
					r = m;
				else
					f = m + 1;
			}
			tot += l.size() - r;
		}
		return tot;
	}
}

public class LC551_560
{
	public static void main(String[] args)
	{
		System.out.println();
	}
}
