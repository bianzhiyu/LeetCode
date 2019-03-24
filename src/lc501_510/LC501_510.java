package lc501_510;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import treeCodec.*;

//503. Next Greater Element II
//Runtime: 8 ms, faster than 100.00% of Java online submissions for Next Greater Element II.
//Memory Usage: 41.8 MB, less than 95.02% of Java online submissions for Next Greater Element II.
class Solution503
{
	public int[] nextGreaterElements(int[] nums)
	{
		int len = nums.length;
		if (len == 0)
			return new int[0];
		if (len == 1)
			return new int[]
			{ -1 };
		int[] largerInd = new int[len];
		int ind = -1;
		for (int i = 0; i < len - 1; i++)
		{
			if (nums[i] > nums[len - 1])
			{
				ind = i;
				break;
			}
		}
		largerInd[len - 1] = ind;
		for (int i = len - 2; i >= 0; i--)
		{
			if (nums[i + 1] > nums[i])
				largerInd[i] = i + 1;
			else if (largerInd[i + 1] == -1)
				largerInd[i] = -1;
			else if (largerInd[i + 1] == i)
			{
				largerInd[i] = -1;
			} else
			{
				int p = largerInd[i + 1];
				while (largerInd[p] != -1 && nums[p] <= nums[i] && p >= i + 1)
					p = largerInd[p];
				if (nums[p] > nums[i])
					largerInd[i] = p;
				else

				if (p < i + 1)
				{
					ind = -1;
					for (int j = p + 1; j < i; j++)
						if (nums[j] > nums[i])
						{
							ind = j;
							break;
						}
					largerInd[i] = ind;
				} else
				{
					largerInd[i] = -1;
				}

			}
		}
		int[] ans = new int[len];
		for (int i = 0; i < len; i++)
			ans[i] = largerInd[i] == -1 ? largerInd[i] : nums[largerInd[i]];
		return ans;
	}
}

//507. Perfect Number
//Runtime: 6 ms, faster than 97.28% of Java online submissions for Perfect Number.
//Memory Usage: 36.9 MB, less than 59.38% of Java online submissions for Perfect Number.
class Solution507
{
	public static int getsqrt(int n)
	{
		int t = (int) Math.sqrt(n);
		if ((t + 1) * (t + 1) == n)
			return t + 1;
		return t;
	}

	public boolean checkPerfectNumber(int n)
	{

		if (n <= 2)
			return false;
		int sum = 1, tmp = getsqrt(n);
		if (tmp * tmp == n)
			sum += tmp;
		else
			tmp++;
		for (int i = 2; i < tmp; i++)
			if (n % i == 0)
				sum += i + n / i;
		return sum == n;
	}
}


//508. Most Frequent Subtree Sum
//Runtime: 9 ms, faster than 48.46% of Java online submissions for Most Frequent Subtree Sum.
//Memory Usage: 41.5 MB, less than 14.71% of Java online submissions for Most Frequent Subtree Sum.
class Solution508
{
	private void accumulate(TreeNode rt)
	{
		if (rt==null) return;
		int s=rt.val;
		if (rt.left!=null)
		{
			accumulate(rt.left);
			s+=rt.left.val;
		}
		if (rt.right!=null)
		{
			accumulate(rt.right);
			s+=rt.right.val;
		}
		rt.val=s;
	}
	private void travel(TreeNode rt,HashMap<Integer,Integer> freq)
	{
		if (rt==null) return;
		if (freq.containsKey(rt.val))
			freq.put(rt.val,freq.get(rt.val)+1);
		else freq.put(rt.val,1);
		travel(rt.left,freq);
		travel(rt.right,freq);
	}
	public int[] findFrequentTreeSum(TreeNode root)
	{
		if (root==null) return new int[0];
		accumulate(root);
		HashMap<Integer,Integer> freq=new HashMap<Integer,Integer>();
		travel(root,freq);
		List<int[]> l=new ArrayList<int[]>();
		for (int key:freq.keySet())
			l.add(new int[] {key,freq.get(key)});
		Collections.sort(l,new Comparator<int[]>()
				{
					public int compare(int[] o1, int[] o2)
					{
						if (o1[1]<o2[1]) return 1;
						if (o1[1]==o2[1]) return 0;
						return -1;
					}
				});
		int j=1;
		while (j<l.size() && l.get(j)[1]==l.get(0)[1]) j++;
		int[] ans=new int[j];
		for (int i=0;i<j;i++)
			ans[i]=l.get(i)[0];
		return ans;
	}
}

class Solution508_2
{
	private void accumulate(TreeNode rt)
	{
		if (rt==null) return;
		int s=rt.val;
		if (rt.left!=null)
		{
			accumulate(rt.left);
			s+=rt.left.val;
		}
		if (rt.right!=null)
		{
			accumulate(rt.right);
			s+=rt.right.val;
		}
		rt.val=s;
	}
	private void travel(TreeNode rt,HashMap<Integer,Integer> freq)
	{
		if (rt==null) return;
		if (freq.containsKey(rt.val))
			freq.put(rt.val,freq.get(rt.val)+1);
		else freq.put(rt.val,1);
		travel(rt.left,freq);
		travel(rt.right,freq);
	}
	private void copy(TreeNode originalRt,TreeNode newRt)
	{
		if (originalRt.left!=null)
		{
			newRt.left=new TreeNode(originalRt.left.val);
			copy(originalRt.left,newRt.left);
		}
		if (originalRt.right!=null)
		{
			newRt.right=new TreeNode(originalRt.right.val);
			copy(originalRt.right,newRt.right);
		}
	}
	public int[] findFrequentTreeSum(TreeNode root)
	{
		if (root==null) return new int[0];
		TreeNode copyrt=new TreeNode(root.val);
		copy(root,copyrt);
		accumulate(copyrt);
		HashMap<Integer,Integer> freq=new HashMap<Integer,Integer>();
		travel(copyrt,freq);
		List<int[]> l=new ArrayList<int[]>();
		for (int key:freq.keySet())
			l.add(new int[] {key,freq.get(key)});
		Collections.sort(l,new Comparator<int[]>()
				{
					public int compare(int[] o1, int[] o2)
					{
						if (o1[1]<o2[1]) return 1;
						if (o1[1]==o2[1]) return 0;
						return -1;
					}
				});
		int j=1;
		while (j<l.size() && l.get(j)[1]==l.get(0)[1]) j++;
		int[] ans=new int[j];
		for (int i=0;i<j;i++)
			ans[i]=l.get(i)[0];
		return ans;
	}
}

//509. Fibonacci Number
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Fibonacci Number.
//Memory Usage: 35.3 MB, less than 100.00% of Java online submissions for Fibonacci Number.
class Solution509
{
	public int fib(int n)
	{
		int[] f = new int[n + 3];
		f[0] = 0;
		f[1] = 1;
		for (int i = 2; i <= n; i++)
			f[i] = f[i - 1] + f[i - 2];
		return f[n];
	}
}

public class LC501_510
{
	public static void test503()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input503.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output503.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int[] nums = test.Test.parseIntArr(inLine);

				Solution503 solver = new Solution503();

				int[] arr = solver.nextGreaterElements(nums);

				String ans = test.Test.intArrToString(arr);

				System.out.println(ans);

				bfw.write(ans);
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
	public static void test508()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input508.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output508.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				TreeNode rt=TreeCodec.deserialize(inLine);

				Solution508 solver=new Solution508();
				
				int[] ans=solver.findFrequentTreeSum(rt);
				
				String out=test.Test.intArrToString(ans);
				
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
		test508();
	}
}
