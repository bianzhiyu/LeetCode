package lc471_480;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

//473. Matchsticks to Square
//Just use the code of 698. Partition to K Equal Sum Subsets
//Runtime: 14 ms, faster than 88.57% of Java online submissions for Matchsticks to Square.
//Memory Usage: 37.3 MB, less than 78.13% of Java online submissions for Matchsticks to Square.
class Solution473
{
	// above are code from 698.
	int[] n;
	int s;
	boolean[] used;

	public boolean canPartitionKSubsets(int[] nums, int k)
	{
		s = 0;
		for (int i = 0; i < nums.length; i++)
			s += nums[i];
		if (s % k != 0)
			return false;
		s /= k;
		n = nums;
		used = new boolean[nums.length];
		Arrays.parallelSort(n);
		return dfs(k, s, n.length - 1);
	}

	private boolean dfs(int k, int sum, int maxRange)
	{
		if (k == 0)
			return true;
		if (sum == 0)
			return dfs(k - 1, s, n.length - 1);
		for (int i = maxRange; i >= 0; i--)
			if (!used[i] && sum >= n[i])
			{
				used[i] = true;
				if (dfs(k, sum - n[i], i - 1))
					return true;
				used[i] = false;
			}
		return false;
	}

	// Below are codes from 698
	public boolean makesquare(int[] nums)
	{
		if (nums.length == 0)
			return false;
		return canPartitionKSubsets(nums, 4);
	}
}

//474. Ones and Zeroes
//Runtime: 28 ms, faster than 64.91% of Java online submissions for Ones and Zeroes.
//Memory Usage: 37.1 MB, less than 46.15% of Java online submissions for Ones and Zeroes.
class Solution474_1
{
	public int findMaxForm(String[] strs, int m, int n)
	{
		int len = strs.length;
		int[] c0 = new int[len], c1 = new int[len];
		for (int i = 0; i < len; i++)
		{
			int ct0 = 0;
			for (int j = 0; j < strs[i].length(); j++)
				if (strs[i].charAt(j) == '0')
					ct0++;
			c0[i] = ct0;
			c1[i] = strs[i].length() - ct0;
		}
		int[][] d = new int[m + 1][n + 1];
		for (int i = 0; i < len; i++)
			for (int j = m; j >= c0[i]; j--)
				for (int k = n; k >= c1[i]; k--)
					d[j][k] = Math.max(d[j][k], d[j - c0[i]][k - c1[i]] + 1);
		return d[m][n];
	}
}

//Runtime: 11 ms, faster than 99.55% of Java online submissions for Ones and Zeroes.
//Memory Usage: 36.9 MB, less than 78.85% of Java online submissions for Ones and Zeroes.
class Solution474_2
{
	public int findMaxForm(String[] strs, int m, int n)
	{
		int len = strs.length;
		int[][] d = new int[m + 1][n + 1];
		for (int i = 0; i < len; i++)
		{
			int ct0 = 0;
			for (int j = 0; j < strs[i].length(); j++)
				if (strs[i].charAt(j) == '0')
					ct0++;
			int c0 = ct0, c1 = strs[i].length() - ct0;
			for (int j = m; j >= c0; j--)
				for (int k = n; k >= c1; k--)
					d[j][k] = Math.max(d[j][k], d[j - c0][k - c1] + 1);
		}
		return d[m][n];
	}
}

//477. Total Hamming Distance
//Runtime: 29 ms, faster than 35.32% of Java online submissions for Total Hamming Distance.
//Memory Usage: 41.3 MB, less than 29.00% of Java online submissions for Total Hamming Distance.
class Solution477
{
	public int totalHammingDistance(int[] nums)
	{
		int[] c0ct = new int[31];
		int len = nums.length;
		for (int i = 0; i < len; i++)
		{
			int x = nums[i];
			for (int j = 0; j < c0ct.length && x > 0; j++)
			{
				c0ct[j] += x % 2;
				x /= 2;
			}
		}
		int tot = 0;
		for (int j = 0; j < c0ct.length; j++)
			tot += c0ct[j] * (len - c0ct[j]);
		return tot;
	}
}

//478. Generate Random Point in a Circle
//Runtime: 200 ms, faster than 93.35% of Java online submissions for Generate Random Point in a Circle.
//Memory Usage: 62.6 MB, less than 89.66% of Java online submissions for Generate Random Point in a Circle.
class Solution478
{
	double r, x, y;
	Random rd = new Random();

	public Solution478(double radius, double x_center, double y_center)
	{
		r = radius;
		x = x_center;
		y = y_center;
	}

	public double[] randPoint()
	{
		double t = rd.nextDouble() * 2 * Math.PI, p = Math.sqrt(rd.nextDouble());
		return new double[]
		{ x + p * r * Math.cos(t), y + p * r * Math.sin(t) };
	}
}

//480. Sliding Window Median
//Runtime: 30 ms, faster than 73.78% of Java online submissions for Sliding Window Median.
//Memory Usage: 44 MB, less than 36.98% of Java online submissions for Sliding Window Median.
class Solution480
{
	public double[] medianSlidingWindow(int[] nums, int k)
	{
		if (k==1)
		{
			double[] ans =new double[nums.length];
			for (int i=0;i<nums.length;i++)
				ans[i]=nums[i];
			return ans;
		}
		PriorityQueue<Integer> smallerPart=new PriorityQueue<Integer>(
				new Comparator<Integer>()
				{
					public int compare(Integer x,Integer y)
					{
						if (y<x) return -1;
						if (y==x) return 0;
						return 1;
					}
				});
		PriorityQueue<Integer> largerPart=new PriorityQueue<Integer>();
		double ans[]=new double[nums.length-k+1];
		for (int i=0;i<k-1;i++)
		{
			if (i==0)
			{
				smallerPart.offer(nums[i]);
			}
			else if (smallerPart.size()>largerPart.size())
			{
				if (nums[i]<smallerPart.peek())
				{
					largerPart.offer(smallerPart.poll());
					smallerPart.offer(nums[i]);
				}
				else largerPart.offer(nums[i]);
			}
			else
			{
				if (nums[i]>largerPart.peek())
				{
					smallerPart.offer(largerPart.poll());
					largerPart.offer(nums[i]);
				}
				else smallerPart.offer(nums[i]);
			}
		}
		for (int i=k-1;i<nums.length;i++)
		{
			if (smallerPart.size()>largerPart.size())
			{
				if (nums[i]<smallerPart.peek())
				{
					largerPart.offer(smallerPart.poll());
					smallerPart.offer(nums[i]);
				}
				else largerPart.offer(nums[i]);
			}
			else
			{
				if (nums[i]>largerPart.peek())
				{
					smallerPart.offer(largerPart.poll());
					largerPart.offer(nums[i]);
				}
				else smallerPart.offer(nums[i]);
			}
			if (smallerPart.size()==largerPart.size())
				ans[i-k+1]=((double)smallerPart.peek()+largerPart.peek())/2.0;
			else ans[i-k+1]=smallerPart.peek();
			if (smallerPart.size()>largerPart.size())
			{
				if (nums[i-k+1]<=smallerPart.peek())
					smallerPart.remove(nums[i-k+1]);
				else
				{
					largerPart.remove(nums[i-k+1]);
					largerPart.offer(smallerPart.poll());
				}
			}
			else
			{
				if (nums[i-k+1]>=largerPart.peek())
					largerPart.remove(nums[i-k+1]);
				else
				{
					smallerPart.remove(nums[i-k+1]);
					smallerPart.offer(largerPart.poll());
				}
			}
		}
		return ans;
	}
}

public class LC471_480
{
	public static void test474()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input474.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output474.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			Solution474_1 solver = new Solution474_1();
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				String[] data = inLine.substring(1, inLine.length() - 1).split(",");
				for (int i = 0; i < data.length; i++)
				{
					data[i] = data[i].trim();
					data[i] = data[i].substring(1, data[i].length() - 1);
				}

				int m = Integer.parseInt(bfr.readLine()), n = Integer.parseInt(bfr.readLine());

				int ans = solver.findMaxForm(data, m, n);

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

	public static void test480()
	{
		try
		{
			Solution480 s=new Solution480();

			File inFile = new File("input" + File.separator + "input480.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output480.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				String[] data = inLine.substring(1, inLine.length() - 1).split(",");
				int[] nums = new int[data.length];
				for (int i = 0; i < data.length; i++)
					nums[i] = Integer.parseInt(data[i]);

				inLine = bfr.readLine();
				int k = Integer.parseInt(inLine);

				double[] ans=s.medianSlidingWindow(nums, k);

				for (int i=0;i<ans.length;i++)
					bfw.write(ans[i]+" ");

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
		test480();
	}
}
