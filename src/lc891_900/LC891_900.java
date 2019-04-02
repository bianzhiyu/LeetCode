package lc891_900;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import treeCodec.*;

//891. Sum of Subsequence Widths
//Runtime: 5098 ms, faster than 5.35% of Java online submissions for Sum of Subsequence Widths.
//Memory Usage: 43 MB, less than 66.67% of Java online submissions for Sum of Subsequence Widths.
class Solution891
{
	public int sumSubseqWidths(int[] A)
	{
		Arrays.parallelSort(A);
		long sum = 0;
		long modulo = 1000000007;
		for (int i = 0; i < A.length; i++)
		{
			long base = 1;
			for (int j = i + 1; j < A.length; j++)
			{
				sum = (sum + (A[j] - A[i]) * base) % modulo;
				base = (base * 2) % modulo;
			}
		}
		return (int) sum;
	}
}
// Suppose we have a_0,... a_{n-1}
//Ans=\sum_{i=0,...,n-2} \sum_{j=i+1,...,n-1} (a_j-a_i) * 2^{j-i-1}
//Ans=\sum_{j=1,...,n-1} (2^j-1)*(a_j-a_{n-1-j})

//Runtime: 25 ms, faster than 90.37% of Java online submissions for Sum of Subsequence Widths.
//Memory Usage: 43 MB, less than 66.67% of Java online submissions for Sum of Subsequence Widths.
class Solution891_2
{
	public int sumSubseqWidths(int[] A)
	{
		Arrays.parallelSort(A);
		int n = A.length;
		long sum = 0;
		long modulo = 1000000007;
		long mul = 1;
		for (int j = 1; j < n; j++)
		{
			sum = (sum + mul * (A[j] - A[n - 1 - j])) % modulo;
			mul = (2 * mul + 1) % modulo;
		}
		return (int) sum;
	}
}

//894. All Possible Full Binary Trees
//Runtime: 6 ms, faster than 38.98% of Java online submissions for All Possible Full Binary Trees.
//Memory Usage: 48.5 MB, less than 15.53% of Java online submissions for All Possible Full Binary Trees.
class Solution894
{
	public List<TreeNode> allPossibleFBT(int N)
	{
		if (N == 1)
		{
			List<TreeNode> lt = new ArrayList<TreeNode>();
			lt.add(new TreeNode(0));
			return lt;
		}
		List<TreeNode> ans = new ArrayList<TreeNode>();
		for (int l = 1; N - 1 - l >= 1; l += 2)
		{
			List<TreeNode> ll = allPossibleFBT(l);
			List<TreeNode> lr = allPossibleFBT(N - 1 - l);
			for (TreeNode lt : ll)
				for (TreeNode rt : lr)
				{
					TreeNode nt = new TreeNode(0);
					nt.left = lt;
					nt.right = rt;
					ans.add(nt);
				}
		}
		return ans;
	}
}

//898. Bitwise ORs of Subarrays
//Runtime: 401 ms, faster than 23.03% of Java online submissions for Bitwise ORs of Subarrays.
//Memory Usage: 75.7 MB, less than 5.00% of Java online submissions for Bitwise ORs of Subarrays.
class Solution898
{
	public int subarrayBitwiseORs(int[] A)
	{
		HashSet<Integer> ans = new HashSet<Integer>();
		ans.add(A[0]);
		HashSet<Integer> cur = new HashSet<Integer>();
		cur.add(A[0]);
		for (int i = 1; i < A.length; i++)
		{
			HashSet<Integer> ns = new HashSet<Integer>();
			ns.add(A[i]);
			for (int x : cur)
				ns.add(A[i] | x);
			ans.addAll(ns);
			cur = ns;
		}
		return ans.size();
	}
}

//900. RLE Iterator
//Runtime: 54 ms, faster than 53.06% of Java online submissions for RLE Iterator.
//Memory Usage: 40.8 MB, less than 5.88% of Java online submissions for RLE Iterator.
class RLEIterator
{
	private int[] a;
	private int p=0;

	public RLEIterator(int[] A)
	{
		a = Arrays.copyOf(A, A.length);
	}

	public int next(int n)
	{
		while (p<a.length && a[p]<n)
		{
			n-=a[p];
			a[p]=0;
			p+=2;
		}
		if (p==a.length) return -1;
		a[p]-=n;
		return a[p+1];
	}
}

public class LC891_900
{
	public static void test900()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input900.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output900.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int[] nums = test.Test.parseIntArr(inLine);

				RLEIterator r=new RLEIterator(nums);
				
				int[][]ope=test.Test.parse2DIntArr(bfr.readLine());

				int[]ans=new int[ope.length];
				for (int i=0;i<ope.length;i++)
					ans[i]=r.next(ope[i][0]);
				String str=test.Test.intArrToString(ans);
				System.out.println(str);

				bfw.write(str);
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
		test900();
	}

}
