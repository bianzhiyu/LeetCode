package lc311_320;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//312. Burst Balloons
//Runtime: 4 ms, faster than 99.89% of Java online submissions for Burst Balloons.
//Memory Usage: 34.3 MB, less than 90.70% of Java online submissions for Burst Balloons.
class Solution312
{
	public int maxCoins(int[] nums)
	{
		int len = nums.length;
		if (len == 0)
			return 0;
		int[][] d = new int[len][len];
		int lv = 1, rv = 1, mp = 1;
		for (int i = 0; i < len; i++)
		{
			if (i == 0)
				lv = 1;
			else
				lv = nums[i - 1];
			if (i == len - 1)
				rv = 1;
			else
				rv = nums[i + 1];
			d[i][i] = lv * nums[i] * rv;
		}
		for (int i = 2; i <= len; i++)
		{
			for (int j = 0; j + i - 1 < len; j++)
			{
				if (j == 0)
					lv = 1;
				else
					lv = nums[j - 1];
				if (j + i - 1 == len - 1)
					rv = 1;
				else
					rv = nums[j + i];
				mp = lv * rv;
				d[j][j + i - 1] = Math.max(d[j + 1][j + i - 1] + nums[j] * mp, d[j][j + i - 2] + nums[j + i - 1] * mp);

				for (int k = j + 1; k <= j + i - 2; k++)
					if (d[j][k - 1] + d[k + 1][j + i - 1] + nums[k] * mp > d[j][j + i - 1])
						d[j][j + i - 1] = d[j][k - 1] + d[k + 1][j + i - 1] + nums[k] * mp;

			}
		}
		
		return d[0][len - 1];
	}
}

//313. Super Ugly Number
//Runtime: 21 ms, faster than 47.91% of Java online submissions for Super Ugly Number.
//Memory Usage: 36.8 MB, less than 52.00% of Java online submissions for Super Ugly Number.
class Solution313 
{
	public int nthSuperUglyNumber(int n, int[] primes) 
	{
		int[] a=new int[n+2];
		a[0]=1;
		int[] rp=new int[primes.length];
		int srpidx=0,candnum;
		for (int i=1;i<n;i++)
		{
			while (true)
			{
				srpidx=0;
				candnum=a[rp[0]]*primes[0];
				for (int j=1;j<primes.length;j++)
				{
					if (candnum<0)
					{
						srpidx=j;
						candnum=a[rp[j]]*primes[j];
					}
					else if (a[rp[j]]*primes[j]<candnum)
					{
						srpidx=j;
						candnum=a[rp[j]]*primes[j];
					}
				}
				rp[srpidx]++;
				if (candnum>a[i-1]) break;
			}
			a[i]=candnum;
		}
		return a[n-1];
	}
}

//315. Count of Smaller Numbers After Self
//Runtime: 577 ms, faster than 5.66% of Java online submissions for Count of Smaller Numbers After Self.
//Memory Usage: 38.1 MB, less than 78.43% of Java online submissions for Count of Smaller Numbers After Self.
class Solution315
{
	public List<Integer> countSmaller(int[] nums)
	{
		List<Integer> ans = new ArrayList<Integer>();
		int len = nums.length;
		if (len == 0)
			return ans;
		for (int i = 0; i < len; i++)
		{
			int t = 0;
			for (int j = i + 1; j < len; j++)
				if (nums[j] < nums[i])
					t++;
			ans.add(t);
		}
		return ans;
	}
}

//316. Remove Duplicate Letters
//Runtime: 5 ms, faster than 63.21% of Java online submissions for Remove Duplicate Letters.
//Memory Usage: 35.6 MB, less than 69.81% of Java online submissions for Remove Duplicate Letters.
class Solution316
{
	// str: s to char Array.
	// n: str.length.
	// own[i]: whether str has char (i+'a').
	// totchars: the number of different chars which str has.
	// bubblelen[i]: the number of char (i+'a') which str has
	// bubblepos[i][j]: the index of j th char(i+'a') in str,
	// sorted by descending order.
	// analysis[i][j]: whether s[i+1,...,s.length) has char (j+'a').
	// Let newstr be the returned string.
	// selpos[i]=x: str[x]=newstr[i];
	// selected[j]: whether char (j+'a') is selected temporarily.
	public String removeDuplicateLetters(String s)
	{
		char[] str = s.toCharArray();
		int n = str.length;
		boolean[] own = new boolean[26];
		for (int i = 0; i < n; i++)
			own[str[i] - 'a'] = true;

		int totchars = 0;
		for (int i = 0; i < 26; i++)
			if (own[i])
				totchars++;
		int[][] bubblepos = new int[26][n];
		boolean[][] analysis = new boolean[n][26];
		int[] bubblelen = new int[26];
		boolean tmp[] = new boolean[26];
		for (int i = n - 1; i >= 0; i--)
		{
			int ord = str[i] - 'a';
			bubblepos[ord][bubblelen[ord]] = i;
			bubblelen[ord]++;
			for (int j = 0; j < 26; j++)
				if (own[j])
					if (tmp[j])
						analysis[i][j] = true;
			tmp[ord] = true;
		}

		int[] selpos = new int[totchars];
		boolean selected[] = new boolean[26];
		for (int i = 0; i < totchars; i++)
		{
			boolean notfound = true;
			for (int j = 0; j < 26 && notfound; j++)
				if (own[j] && !selected[j])
				{
					for (int k = bubblelen[j] - 1; notfound && k >= 0; k--)
					{
						if (i > 0 && bubblepos[j][k] <= selpos[i - 1])
							continue;
						boolean canselect = true;
						for (int l = 0; l < 26; l++)
						{
							if (own[l] && !selected[l] && l != j && !analysis[bubblepos[j][k]][l])
							{
								canselect = false;
								break;
							}
						}
						if (canselect)
						{
							notfound = false;
							selpos[i] = bubblepos[j][k];
							selected[j] = true;
						}
					}
				}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < totchars; i++)
			sb.append((char) (str[selpos[i]]));
		return sb.toString();
	}
}

//318. Maximum Product of Word Lengths
//Runtime: 27 ms, faster than 42.40% of Java online submissions for Maximum Product of Word Lengths.
//Memory Usage: 39.9 MB, less than 56.52% of Java online submissions for Maximum Product of Word Lengths.
class Solution318
{
    public int maxProduct(String[] words) 
    {
    	int len=words.length;
    	Arrays.parallelSort(words, new Comparator<String>()
    			{
					@Override
					public int compare(String o1, String o2)
					{
						return o2.length()-o1.length();
					}
    			});
    	
    	int[] mask=new int[len];
    	for (int i=0;i<len;i++)
    		for (int j=0;j<words[i].length();j++)
    			mask[i]|=1<<(words[i].charAt(j)-'a');
    	int max=0;
    	for (int i=0;i<len-1;i++)
    	{
    		for (int j=i+1;j<len;j++)
    		{
    			if ((mask[i]&mask[j])==0 && words[i].length()*words[j].length()>max)
    				max=words[i].length()*words[j].length();
    			if (words[i].length()*words[j].length()<max) break;
    		}
    	}
        return max;
    }
}

//TLE
class Solution319
{
	public int bulbSwitch(int n)
	{
		boolean[] b = new boolean[n];
		for (int i = 1; i <= n; i++)
			for (int j = i - 1; j < n; j += i)
				b[j] = !b[j];
		int s = 0;
		for (int i = 0; i < n; i++)
			if (b[i])
				s++;
		return s;
	}
}
//319. Bulb Switcher
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Bulb Switcher.
//Memory Usage: 34.6 MB, less than 10.94% of Java online submissions for Bulb Switcher.
class Solution319_2
{
	public int bulbSwitch(int n)
	{
		return (int) Math.sqrt(n);
	}
}

public class LC311_320
{
	public static void main(String args[])
	{
		int[] nums = new int[]
				{2,7,13,19};
		System.out.println(new Solution313().nthSuperUglyNumber(19, nums));
	}

}
