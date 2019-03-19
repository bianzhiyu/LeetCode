package lc841_850;

import java.util.ArrayList;
import java.util.List;

//Split Array into Fibonacci Sequence
//Runtime: 6 ms, faster than 71.48% of Java online submissions for Split Array into Fibonacci Sequence.
//Memory Usage: 40.5 MB, less than 5.88% of Java online submissions for Split Array into Fibonacci Sequence.
class Solution842
{
	public List<Integer> splitIntoFibonacci(String S)
	{
		int len = S.length();
		List<Integer> ans;
		for (int i = 1; i <= len - 2; i++)
		{
			try
			{
				Integer.parseInt(S.substring(0, i));
			} 
			catch (NumberFormatException e)
			{
				break;
			}
			for (int j = 1; j <= len - i - 1; j++)
			{
				try
				{
					Integer.parseInt(S.substring(i, i + j));
				} catch (NumberFormatException e)
				{
					break;
				}
				if ((ans = check(S, i, j)) != null)
					return ans;
			}
		}
		return new ArrayList<Integer>();
	}

	List<Integer> check(String num, int len1, int len2)
	{
		List<Integer> ans = new ArrayList<Integer>();
		if (len1 > 1 && num.charAt(0) == '0')
			return null;
		if (len2 > 1 && num.charAt(len1) == '0')
			return null;
		ans.add(Integer.parseInt(num.substring(0, len1)));
		ans.add(Integer.parseInt(num.substring(len1, len1 + len2)));
		int len = num.length();
		int[] n1 = new int[len + 2], n2 = new int[len + 2];
		for (int i = 0; i < len1; i++)
			n1[len1 - i - 1] = num.charAt(i) - '0';
		for (int i = 0; i < len2; i++)
			n2[len2 - i - 1] = num.charAt(len1 + i) - '0';
		int readpos = len1 + len2;
		int digitlen1 = len1, digitlen2 = len2, digitlen3 = 0;
		int[] n3 = new int[len + 2];
		while (readpos < len)
		{
			digitlen3 = Math.max(digitlen1, digitlen2);
			for (int i = 0; i < digitlen3; i++)
			{
				n3[i] = 0;
				if (i < digitlen1)
					n3[i] += n1[i];
				if (i < digitlen2)
					n3[i] += n2[i];
			}
			for (int i = 0; i < digitlen3 - 1; i++)
			{
				n3[i + 1] += n3[i] / 10;
				n3[i] %= 10;
			}
			while (n3[digitlen3 - 1] > 9)
			{
				n3[digitlen3] = n3[digitlen3 - 1] / 10;
				n3[digitlen3 - 1] %= 10;
				digitlen3++;
			}
			if (readpos + digitlen3 > num.length())
				return null;
			for (int i = 0; i < digitlen3; i++)
				if (num.charAt(readpos + i) - '0' != n3[digitlen3 - 1 - i])
					return null;
			try
			{
				ans.add(Integer.parseInt(num.substring(readpos, readpos + digitlen3)));
			} 
			catch(NumberFormatException e)
			{
				return null;
			}
			readpos += digitlen3;
			for (int i = 0; i < digitlen2; i++)
				n1[i] = n2[i];
			digitlen1 = digitlen2;
			for (int i = 0; i < digitlen3; i++)
				n2[i] = n3[i];
			digitlen2 = digitlen3;

		}
		return ans;
	}
}

//845. Longest Mountain in Array
//Runtime: 4 ms, faster than 100.00% of Java online submissions for Longest Mountain in Array.
//Memory Usage: 39 MB, less than 94.39% of Java online submissions for Longest Mountain in Array.
class Solution845
{
	public int longestMountain(int[] A)
	{
		int max = 0;
		for (int i = 1; i < A.length - 1; i++)
		{
			if (A[i] > A[i - 1] && A[i] > A[i + 1])
			{
				int t = 3, j = i - 1;
				while (j > 0 && A[j - 1] < A[j])
				{
					j--;
					t++;
				}
				j = i + 1;
				while (j < A.length - 1 && A[j + 1] < A[j])
				{
					j++;
					t++;
				}
				if (t > max)
					max = t;
			}
		}
		return max;
	}
}

public class LC841_850
{
	public static void main(String[] args)
	{
		// System.out.println(new Solution845().longestMountain(
		// new int[] {2,1,4,7,3,2,5}
		// ));
		  
	}
}
