package lc501_510;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import treeCodec.*;

//501. Find Mode in Binary Search Tree
//Runtime: 9 ms, faster than 18.67% of Java online submissions for Find Mode in Binary Search Tree.
//Memory Usage: 40 MB, less than 46.02% of Java online submissions for Find Mode in Binary Search Tree.
class Solution501
{
	private void trav(TreeNode root, HashMap<Integer, Integer> ct)
	{
		if (root == null)
			return;
		trav(root.left, ct);
		ct.put(root.val, ct.getOrDefault(root.val, 0) + 1);
		trav(root.right, ct);
	}

	public int[] findMode(TreeNode root)
	{
		HashMap<Integer, Integer> ct = new HashMap<Integer, Integer>();
		trav(root, ct);
		int maxCt = 0;
		for (int key : ct.keySet())
			if (ct.get(key) > maxCt)
				maxCt = ct.get(key);

		int times = 0;
		for (int key : ct.keySet())
			if (ct.get(key) == maxCt)
				times++;

		int[] ans = new int[times];
		int pos = 0;
		for (int key : ct.keySet())
			if (ct.get(key) == maxCt)
				ans[pos++] = key;

		return ans;
	}
}

//502. IPO
//Runtime: 29 ms, faster than 88.47% of Java online submissions for IPO.
//Memory Usage: 47.7 MB, less than 12.12% of Java online submissions for IPO.
class Solution502
{
	private static class Task implements Comparable<Task>
	{
		private int cap, prf;

		public Task(int c, int p)
		{
			cap = c;
			prf = p;
		}

		public int compareTo(Task o)
		{
			return o.prf - prf;
		}
	}

	private static class cmpTask implements Comparator<Task>
	{
		public int compare(Task o1, Task o2)
		{
			return o1.cap - o2.cap;
		}
	}

	public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital)
	{
		PriorityQueue<Task> pool = new PriorityQueue<Task>(new cmpTask());
		int len = Profits.length;
		for (int i = 0; i < len; i++)
			pool.offer(new Task(Capital[i], Profits[i]));
		PriorityQueue<Task> canUse = new PriorityQueue<Task>();
		for (int i = 0; i < k; i++)
		{
			while (!pool.isEmpty() && pool.peek().cap <= W)
				canUse.offer(pool.poll());
			if (canUse.isEmpty())
				break;
			W += canUse.poll().prf;
		}
		return W;
	}
}

//Runtime: 23 ms, faster than 95.10% of Java online submissions for IPO.
//Memory Usage: 46 MB, less than 75.76% of Java online submissions for IPO.
class Solution502_2
{
	private static class cmpTaskByCap implements Comparator<Integer>
	{
		private int[] cap;

		private cmpTaskByCap(int[] c)
		{
			cap = c;
		}

		public int compare(Integer o1, Integer o2)
		{
			return cap[o1] - cap[o2];
		}
	}

	private static class cmpTaskByPft implements Comparator<Integer>
	{
		private int[] pft;

		private cmpTaskByPft(int[] p)
		{
			pft = p;
		}

		public int compare(Integer o1, Integer o2)
		{
			return pft[o2] - pft[o1];
		}
	}

	public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital)
	{
		PriorityQueue<Integer> pool = new PriorityQueue<Integer>(new cmpTaskByCap(Capital));
		int len = Profits.length;
		for (int i = 0; i < len; i++)
			pool.offer(i);
		PriorityQueue<Integer> canUse = new PriorityQueue<Integer>(new cmpTaskByPft(Profits));
		for (int i = 0; i < k; i++)
		{
			while (!pool.isEmpty() && Capital[pool.peek()] <= W)
				canUse.offer(pool.poll());
			if (canUse.isEmpty())
				break;
			W += Profits[canUse.poll()];
		}
		return W;
	}
}

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

//504. Base 7
//Runtime: 1 ms, faster than 94.98% of Java online submissions for Base 7.
//Memory Usage: 34.3 MB, less than 100.00% of Java online submissions for Base 7.
class Solution504
{
	public String convertToBase7(int num)
	{
		if (num == 0)
		{
			return "0";
		}
		if (num < 0)
		{
			return "-" + convertToBase7(-num);
		}
		String ans = "";
		while (num > 0)
		{
			ans = (num % 7) + ans;
			num /= 7;
		}
		return ans;
	}
}

//506. Relative Ranks
//Runtime: 8 ms, faster than 56.01% of Java online submissions for Relative Ranks.
//Memory Usage: 39.2 MB, less than 90.28% of Java online submissions for Relative Ranks.
class Solution506
{
	private static class intP
	{
		int num, ind;

		public intP(int n, int i)
		{
			num = n;
			ind = i;
		}
	}

	public String[] findRelativeRanks(int[] nums)
	{
		int len = nums.length;
		intP[] data = new intP[len];
		for (int i = 0; i < len; i++)
		{
			data[i] = new intP(nums[i], i);
		}
		Arrays.parallelSort(data, new Comparator<intP>()
		{
			@Override
			public int compare(intP o1, intP o2)
			{
				if (o1.num < o2.num)
				{
					return 1;
				}
				if (o1.num > o2.num)
				{
					return -1;
				}
				return 0;
			}
		});
		String[] od = new String[len];
		for (int i = 0; i < len; i++)
		{
			if (i > 2)
			{
				od[data[i].ind]=""+(i+1);
			}
			else if (i==0)
			{
				od[data[i].ind]="Gold Medal";
			}
			else if (i==1)
			{
				od[data[i].ind]="Silver Medal";
			}
			else
			{
				od[data[i].ind]="Bronze Medal";
			}
		}
		return od;
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
		if (rt == null)
			return;
		int s = rt.val;
		if (rt.left != null)
		{
			accumulate(rt.left);
			s += rt.left.val;
		}
		if (rt.right != null)
		{
			accumulate(rt.right);
			s += rt.right.val;
		}
		rt.val = s;
	}

	private void travel(TreeNode rt, HashMap<Integer, Integer> freq)
	{
		if (rt == null)
			return;
		if (freq.containsKey(rt.val))
			freq.put(rt.val, freq.get(rt.val) + 1);
		else
			freq.put(rt.val, 1);
		travel(rt.left, freq);
		travel(rt.right, freq);
	}

	public int[] findFrequentTreeSum(TreeNode root)
	{
		if (root == null)
			return new int[0];
		accumulate(root);
		HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>();
		travel(root, freq);
		List<int[]> l = new ArrayList<int[]>();
		for (int key : freq.keySet())
			l.add(new int[]
			{ key, freq.get(key) });
		Collections.sort(l, new Comparator<int[]>()
		{
			public int compare(int[] o1, int[] o2)
			{
				if (o1[1] < o2[1])
					return 1;
				if (o1[1] == o2[1])
					return 0;
				return -1;
			}
		});
		int j = 1;
		while (j < l.size() && l.get(j)[1] == l.get(0)[1])
			j++;
		int[] ans = new int[j];
		for (int i = 0; i < j; i++)
			ans[i] = l.get(i)[0];
		return ans;
	}
}

class Solution508_2
{
	private void accumulate(TreeNode rt)
	{
		if (rt == null)
			return;
		int s = rt.val;
		if (rt.left != null)
		{
			accumulate(rt.left);
			s += rt.left.val;
		}
		if (rt.right != null)
		{
			accumulate(rt.right);
			s += rt.right.val;
		}
		rt.val = s;
	}

	private void travel(TreeNode rt, HashMap<Integer, Integer> freq)
	{
		if (rt == null)
			return;
		if (freq.containsKey(rt.val))
			freq.put(rt.val, freq.get(rt.val) + 1);
		else
			freq.put(rt.val, 1);
		travel(rt.left, freq);
		travel(rt.right, freq);
	}

	private void copy(TreeNode originalRt, TreeNode newRt)
	{
		if (originalRt.left != null)
		{
			newRt.left = new TreeNode(originalRt.left.val);
			copy(originalRt.left, newRt.left);
		}
		if (originalRt.right != null)
		{
			newRt.right = new TreeNode(originalRt.right.val);
			copy(originalRt.right, newRt.right);
		}
	}

	public int[] findFrequentTreeSum(TreeNode root)
	{
		if (root == null)
			return new int[0];
		TreeNode copyrt = new TreeNode(root.val);
		copy(root, copyrt);
		accumulate(copyrt);
		HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>();
		travel(copyrt, freq);
		List<int[]> l = new ArrayList<int[]>();
		for (int key : freq.keySet())
			l.add(new int[]
			{ key, freq.get(key) });
		Collections.sort(l, new Comparator<int[]>()
		{
			public int compare(int[] o1, int[] o2)
			{
				if (o1[1] < o2[1])
					return 1;
				if (o1[1] == o2[1])
					return 0;
				return -1;
			}
		});
		int j = 1;
		while (j < l.size() && l.get(j)[1] == l.get(0)[1])
			j++;
		int[] ans = new int[j];
		for (int i = 0; i < j; i++)
			ans[i] = l.get(i)[0];
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
				TreeNode rt = TreeCodec.deserialize(inLine);

				Solution508 solver = new Solution508();

				int[] ans = solver.findFrequentTreeSum(rt);

				String out = test.Test.intArrToString(ans);

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
