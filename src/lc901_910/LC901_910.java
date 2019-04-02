package lc901_910;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//901. Online Stock Span
//Runtime: 88 ms, faster than 91.97% of Java online submissions for Online Stock Span.
//Memory Usage: 68.5 MB, less than 24.07% of Java online submissions for Online Stock Span.
class StockSpanner
{
	List<Integer> prs = new ArrayList<Integer>();
	List<Integer> bef = new ArrayList<Integer>();

	public StockSpanner()
	{

	}

	public int next(int price)
	{
		if (prs.size() == 0)
		{
			prs.add(price);
			bef.add(0);
			return 1;
		}
		prs.add(price);
		bef.add(bef.size());
		int p = prs.size() - 1;
		while (true)
		{
			if (bef.get(p) < p)
				p = bef.get(p);
			else if (p > 0 && prs.get(p - 1) <= price)
				p = bef.get(p - 1);
			else
				break;
		}
		bef.set(bef.size() - 1, p);
		return bef.size() - p;
	}
}

//904. Fruit Into Baskets
class Solution904
{
	public int totalFruit(int[] tree)
	{
		int len=tree.length;
		int[] contLen=new int[len];
		contLen[len-1]=1;
		for (int i=len-2;i>=0;i--)
			if (tree[i]==tree[i+1])
				contLen[i]=contLen[i+1]+1;
			else contLen[i]=1;
		int[] typeCont=new int[2];
		int max=1,p,count,typeCt;
		for (int i=0;i<len;i++)
		{
			typeCt=0;
			p=i;
			count=0;
			typeCont[0]=typeCont[1]=0;
			while (p<len)
			{
				if (typeCt==0)
				{
					typeCont[0]=tree[p];
					typeCt++;
					count+=contLen[p];
					p+=contLen[p];
				}
				else if (typeCt==1)
				{
					if (typeCont[0]==tree[p])
					{
						count+=contLen[p];
						p+=contLen[p];
					}
					else
					{
						typeCont[1]=tree[p];
						typeCt++;
						count+=contLen[p];
						p+=contLen[p];
					}
				}
				else
				{
					if (typeCont[0]==tree[p] || typeCont[1]==tree[p])
					{
						count+=contLen[p];
						p+=contLen[p];
					}
					else break;
				}
			}
			if (count>max) max=count;
		}
		
		return max;
	}
}

//907. Sum of Subarray Minimums
//Runtime: 12124 ms, faster than 5.10% of Java online submissions for Sum of Subarray Minimums.
//Memory Usage: 45.8 MB, less than 51.56% of Java online submissions for Sum of Subarray Minimums.
class Solution907
{
	public int sumSubarrayMins(int[] A)
	{
		int min = 0;
		long tot = 0;
		final long modulo = 1000000007;
		for (int i = 0; i < A.length; i++)
		{
			min = Integer.MAX_VALUE;
			for (int j = i; j < A.length; j++)
			{
				if (A[j] < min)
					min = A[j];
				tot = (tot + min) % modulo;
			}
		}
		return (int) tot;
	}
}

//910. Smallest Range II
//https://leetcode.com/problems/smallest-range-ii/discuss/213953/C%2B%2B-6-lines-of-code
//Runtime: 12 ms, faster than 77.13% of Java online submissions for Smallest Range II.
//Memory Usage: 40.3 MB, less than 80.95% of Java online submissions for Smallest Range II.
class Solution910
{
	public int smallestRangeII(int[] A, int K)
	{
		Arrays.parallelSort(A);
		int N = A.length;
		int ret = A[N - 1] - A[0];
		for (int i = 0; i < N - 1; i++)
			if (A[i] != A[i + 1])
				ret = Math.min(ret, Math.max(A[i] + K, A[N - 1] - K) - Math.min(A[i + 1] - K, A[0] + K));
		return ret;
	}
}

public class LC901_910
{
	public static void test904()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input904.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output904.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int[] nums = test.Test.parseIntArr(inLine);

				Solution904 s=new Solution904();

				int ans = s.totalFruit(nums);
				System.out.println(ans);

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
		test904();
	}

}
