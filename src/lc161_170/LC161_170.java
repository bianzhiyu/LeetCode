package lc161_170;

import java.util.Arrays;
import java.util.HashMap;

//162. Find Peak Element
//Runtime: 2 ms, faster than 100.00% of Java online submissions for Find Peak Element.
//Memory Usage: 39.2 MB, less than 18.75% of Java online submissions for Find Peak Element.
class Solution162
{
	public int findPeakElement(int[] nums)
	{
		int n = nums.length;
		if (n <= 1)
			return 0;
		if (nums[0] > nums[1])
			return 0;
		if (nums[n - 2] < nums[n - 1])
			return n - 1;
		for (int i = 1; i <= n - 2; i++)
			if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1])
				return i;
		return 0;
	}
}

//164. Maximum Gap
//Runtime: 3 ms, faster than 99.65% of Java online submissions for Maximum Gap.
//Memory Usage: 38.4 MB, less than 100.00% of Java online submissions for Maximum Gap.
class Solution164
{
	public int maximumGap(int[] nums)
	{
		int max = 0;
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 1; i++)
			if (nums[i + 1] - nums[i] > max)
				max = nums[i + 1] - nums[i];
		return max;

	}
}

//165. Compare Version Numbers
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Compare Version Numbers.
//Memory Usage: 33 MB, less than 99.11% of Java online submissions for Compare Version Numbers.
class Solution165
{
	public int compareVersion(String version1, String version2)
	{
		int p1 = 0, p2 = 0;
		while (p1 < version1.length() || p2 < version2.length())
		{
			int v1 = 0, v2 = 0;
			while (p1 < version1.length() && version1.charAt(p1) != '.')
			{
				v1 = v1 * 10 + (version1.charAt(p1) - '0');
				p1++;
			}
			p1++;

			while (p2 < version2.length() && version2.charAt(p2) != '.')
			{
				v2 = v2 * 10 + (version2.charAt(p2) - '0');
				p2++;
			}
			p2++;
			if (v1 != v2)
				return v1 - v2 < 0 ? -1 : 1;
		}
		return 0;
	}
}

//166. Fraction to Recurring Decimal
//Runtime: 3 ms, faster than 94.36% of Java online submissions for Fraction to Recurring Decimal.
//Memory Usage: 37.6 MB, less than 5.52% of Java online submissions for Fraction to Recurring Decimal.
class Solution166
{
	public String fractionToDecimal(long numerator, long denominator)
	{
		boolean posi;
		if (denominator<0L)
		{
			denominator=-denominator;
			numerator=-numerator;
		}
		if (numerator<(long)0) posi=false;
		else posi=true;
		if (numerator<(long)0) numerator=-numerator;
		long p1=numerator/denominator;
		long p2=numerator%denominator;
		if (p2==(long)0) return (posi?"":"-")+p1+"";
		long[] ans=new long[2000];
		long sp=1,r=p2;
		HashMap<Long,Long> remToPos=new HashMap<Long,Long>();
		remToPos.put(r,(long) 0);
		while (true)
		{
			r=r*10;
			ans[(int)sp]=r/denominator;
			sp++;
			r=r%denominator;
			if (r==0)
			{
				String res=p1+".";
				for (int i=1;i<sp;i++)
					res+=ans[i];
				if (!posi) res='-'+res;
				return res;
			}
			if (remToPos.containsKey(r))
			{
				long start=remToPos.get(r)+1;
				String res=p1+".";
				for (int i=1;i<start;i++)
					res+=ans[i];
				res+='(';
				for (long i=start;i<=sp-1;i++)
					res+=ans[(int)i];
				res+=')';
				if (!posi) res='-'+res;
				return res;
			}
			else
			{
				remToPos.put(r,sp-1);
			}
		}
	}
}

//168. Excel Sheet Column Title
//https://leetcode.com/problems/excel-sheet-column-title/discuss/51399/Accepted-Java-solution
//T=0MS, M=35.5mb
//T:1,M:1
class Solution168
{
	public String convertToTitle(int n)
	{
		StringBuilder result = new StringBuilder();

		while (n > 0)
		{
			n--;
			result.insert(0, (char) ('A' + n % 26));
			n /= 26;
		}

		return result.toString();

	}
}

//169. Majority Element
//Runtime: 5 ms, faster than 63.65% of Java online submissions for Majority Element.
//Memory Usage: 40.8 MB, less than 58.82% of Java online submissions for Majority Element.
class Solution169
{
	public int majorityElement(int[] nums)
	{
		Arrays.sort(nums);
		int i = 0;
		while (i < nums.length)
		{
			int j = i + 1;
			while (j < nums.length && nums[j] == nums[i])
				j++;
			if (j - i > nums.length / 2)
				return nums[i];
			i = j;
		}
		return 0;
	}
}

public class LC161_170
{
	public static void main(String[] args)
	{
		System.out.println(new Solution166().fractionToDecimal(2, 4));
		System.out.println(0xA111);
	}

}
