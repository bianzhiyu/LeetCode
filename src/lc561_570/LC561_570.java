package lc561_570;

import java.util.Arrays;

//561. Array Partition I
//Runtime: 22 ms, faster than 59.30% of Java online submissions for Array Partition I.
//Memory Usage: 41.4 MB, less than 68.50% of Java online submissions for Array Partition I.
class Solution561
{
	public int arrayPairSum(int[] nums)
	{
		Arrays.parallelSort(nums);
		int tot = 0;
		for (int i = 0; i < nums.length; i += 2)
			tot += nums[i];
		return tot;
	}
}

//565. Array Nesting
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Array Nesting.
//Memory Usage: 42.6 MB, less than 69.83% of Java online submissions for Array Nesting.
class Solution565
{
	public int arrayNesting(int[] nums)
	{
		int len = nums.length;
		boolean[] used = new boolean[len];
		int max = 0;
		for (int i = 0; i < len; i++)
			if (!used[i])
			{
				int state = i, ct = 0;
				while (!used[state])
				{
					used[state] = true;
					ct++;
					state = nums[state];
				}
				if (ct > max)
					max = ct;
			}
		return max;
	}
}

//567. Permutation in String
//Runtime: 6 ms, faster than 99.82% of Java online submissions for Permutation in String.
//Memory Usage: 37.6 MB, less than 87.96% of Java online submissions for Permutation in String.
class Solution567
{
	public boolean checkInclusion(String s1, String s2)
	{
		if (s1.length() == 0)
			return true;
		if (s2.length() < s1.length())
			return false;
		int[] grp = new int[26];
		int len1 = s1.length();
		for (int i = 0; i < len1; i++)
			grp[s1.charAt(i) - 'a']++;
		int[] ct = new int[26];
		for (int i = 0; i < len1 - 1; i++)
			ct[s2.charAt(i) - 'a']++;
		int unmatched=0;
		for (int i=0;i<26;i++)
			if (ct[i]!=grp[i]) unmatched++;
		for (int i = len1 - 1; i < s2.length(); i++)
		{
			int c=s2.charAt(i) - 'a';
			ct[c]++;
			if (ct[c]==grp[c]) unmatched--;
			else if (ct[c]==grp[c]+1) unmatched++;
			if (unmatched==0) return true;
			c=s2.charAt(i + 1 - len1) - 'a';
			ct[c]--;
			if (ct[c]==grp[c]) unmatched--;
			else if (ct[c]==grp[c]-1) unmatched++;
		}
		return false;
	}
}

public class LC561_570
{
	public static void main(String[] args)
	{

	}
}
