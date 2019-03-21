package lc371_380;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

//372. Super Pow
//Runtime: 3 ms, faster than 88.71% of Java online submissions for Super Pow.
//Memory Usage: 37.7 MB, less than 65.52% of Java online submissions for Super Pow.
class Solution372
{
	final static int MODU = 1337;

	int pow(int b, int x)
	{
		int res = 1;
		while (x > 0)
		{
			if (x % 2 == 1)
				res = (res * b) % MODU;
			x /= 2;
			b = (b * b) % MODU;
		}
		return res;
	}

	public int superPow(int a, int[] b)
	{
		int res = 1;
		a %= MODU;
		for (int i = b.length - 1; i >= 0; i--)
		{
			res = (res * pow(a, b[i])) % MODU;
			a = pow(a, 10);
		}
		return res;
	}
}

//373. Find K Pairs with Smallest Sums
//Runtime: 6 ms, faster than 77.07% of Java online submissions for Find K Pairs with Smallest Sums.
//Memory Usage: 40.9 MB, less than 47.58% of Java online submissions for Find K Pairs with Smallest Sums.
class MP implements Comparable<MP>
{
	int x1, x2;

	MP(int _1, int _2)
	{
		x1 = _1;
		x2 = _2;
	}

	@Override
	public int compareTo(MP o)
	{
		return o.x1 + o.x2 - x1 - x2;
	}
}

class Solution373
{
	public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k)
	{
		List<int[]> ans = new ArrayList<int[]>();
		if (k == 0)
			return ans;
		PriorityQueue<MP> queue = new PriorityQueue<MP>();
		for (int i = 0; i < nums1.length; i++)
		{
			boolean added = false;
			for (int j = 0; j < nums2.length; j++)
			{
				MP p = new MP(nums1[i], nums2[j]);
				if (queue.size() < k)
				{
					added = true;
					queue.add(p);
				} else
				{
					if (queue.peek().compareTo(p) < 0)
					{
						queue.remove(queue.peek());
						queue.add(p);
						added = true;
					} else
						break;
				}
			}
			if (!added)
				break;
		}
		while (!queue.isEmpty())
		{
			MP p = queue.poll();
			ans.add(new int[]
					{ p.x1, p.x2 });
		}
		Collections.reverse(ans);
		return ans;

	}
}

//374. Guess Number Higher or Lower
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Guess Number Higher or Lower.
//Memory Usage: 34.3 MB, less than 76.15% of Java online submissions for Guess Number Higher or Lower.
/*
 * The guess API is defined in the parent class GuessGame.
 * 
 * @param num, your guess
 * 
 * @return -1 if my number is lower, 1 if my number is higher, otherwise return
 * 0 int guess(int num);
 */

class GuessGame
{
	int realNum;

	int guess(int g)
	{
		if (g > realNum)
			return -1;
		if (g < realNum)
			return 1;
		return 0;
	}
}

class Solution374 extends GuessGame
{
	public int guessNumber(int n)
	{
		int l = 1, r = n;
		while (l < r)
		{
			if (r == l + 1)
			{
				if (guess(r) == 0)
					return r;
				else
					return l;
			}
			int m = (int) (((long) l + r) / 2);
			int f = guess(m);
			if (f == 0)
				return m;
			if (f > 0)
			{
				l = m + 1;
			} else
			{
				r = m - 1;
			}
		}
		return l;
	}
}

//375. Guess Number Higher or Lower II
//Runtime: 6 ms, faster than 76.92% of Java online submissions for Guess Number Higher or Lower II.
//Memory Usage: 34.8 MB, less than 6.78% of Java online submissions for Guess Number Higher or Lower II.
class Solution375
{
	public int getMoneyAmount(int n)
	{
		int[][] d = new int[n + 1][n + 1];
		for (int i = 1; i < n; i++)
			d[i][i + 1] = i;
		for (int len = 3; len <= n; len++)
			for (int i = 1; i + len - 1 <= n; i++)
			{
				d[i][i + len - 1] = i + d[i + 1][i + len - 1];
				if (i + len - 1 + d[i][i + len - 2] < d[i][i + len - 1])
					d[i][i + len - 1] = i + len - 1 + d[i][i + len - 2];
				for (int j = i + 1; j <= i + len - 2; j++)
				{
					int c = d[i][j - 1] > d[j + 1][i + len - 1] ? d[i][j - 1] : d[j + 1][i + len - 1];
					if (j + c < d[i][i + len - 1])
						d[i][i + len - 1] = j + c;
				}
			}
		return d[1][n];
	}
}

//376. Wiggle Subsequence
//Runtime: 3 ms, faster than 21.91% of Java online submissions for Wiggle Subsequence.
//Memory Usage: 33.2 MB, less than 81.97% of Java online submissions for Wiggle Subsequence.
class Solution376
{
	public int wiggleMaxLength(int[] nums)
	{
		int n = nums.length;
		if (n <= 1)
			return n;
		int[] dt = new int[n];
		int[] ut = new int[n];
		dt[0] = 1;
		ut[0] = 1;
		for (int i = 1; i < n; i++)
		{
			dt[i] = 1;
			ut[i] = 1;
			for (int j = 0; j < i; j++)
			{
				if (nums[j] > nums[i] && ut[j] + 1 > dt[i])
					dt[i] = ut[j] + 1;
				if (nums[j] < nums[i] && dt[j] + 1 > ut[i])
					ut[i] = dt[j] + 1;
			}
		}
		int max = 1;
		for (int i = 0; i < n; i++)
		{
			if (dt[i] > max)
				max = dt[i];
			if (ut[i] > max)
				max = ut[i];
		}
		return max;

	}
}

//https://leetcode.com/problems/wiggle-subsequence/discuss/237832/Java-O(n)-time-O(1)-space-Easy-Understandable
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Wiggle Subsequence.
//Memory Usage: 33.3 MB, less than 80.33% of Java online submissions for Wiggle Subsequence.
class Solution376_2
{
	public int wiggleMaxLength(int[] nums)
	{
		// DP
		if (nums.length <= 1)
			return nums.length;

		// 1. Meaning: upMax->max length ending with up; downMax->max length ending with
		// down
		// 2. Function: upMax = max(upMax, downMax + 1), downMax = max(downMax, upMax +
		// 1)
		// 3. Init: if up first, upMax = 2; if down first, downMax = 2; if equal, all
		// equals 1
		int upMax = 0, downMax = 0;
		if (nums[0] > nums[1])
			downMax = 2;
		else if (nums[0] < nums[1])
			upMax = 2;
		else
		{
			downMax = 1;
			upMax = 1;
		}

		for (int i = 1; i < nums.length - 1; i++)
		{
			if (nums[i] > nums[i + 1])
				downMax = Math.max(downMax, upMax + 1);
			else if (nums[i] < nums[i + 1])
				upMax = Math.max(upMax, downMax + 1);
		}
		return Math.max(upMax, downMax);
	}
}

//377. Combination Sum IV
//tle
class Solution377
{
	int dfs(int[] n, int tar)
	{
		if (tar == 0)
			return 1;
		int res = 0;
		for (int i = 0; i < n.length; i++)
			if (tar >= n[i])
				res += dfs(n, tar - n[i]);
		return res;
	}

	public int combinationSum4(int[] nums, int target)
	{
		return dfs(nums, target);
	}
}

//Time Limit Exceeded
class Solution377_2
{
	int tot = 0;
	int[] stack;
	int[] n;

	int fact()
	{
		int s = 0;
		for (int i = 0; i < stack.length; i++)
			s += stack[i];
		long p = 1;
		int[] pt = new int[stack.length];
		Arrays.fill(pt, 2);
		for (int i = 1; i <= s; i++)
		{
			p *= i;
			for (int j = 0; j < stack.length; j++)
				if (pt[j] <= stack[j] && p % pt[j] == 0)
				{
					p /= pt[j];
					pt[j]++;
				}
		}
		return (int) p;
	}

	void dfs(int tar, int sp)
	{
		if (sp == n.length)
		{
			if (tar == 0)
				tot += fact();
			return;
		}
		stack[sp] = 0;
		while (tar >= 0)
		{
			dfs(tar, sp + 1);
			stack[sp]++;
			tar -= n[sp];
		}

	}

	public int combinationSum4(int[] nums, int target)
	{
		n = nums;
		stack = new int[n.length];
		dfs(target, 0);
		return tot;
	}

}


//Runtime: 1 ms, faster than 88.44% of Java online submissions for Combination Sum IV.
//Memory Usage: 33 MB, less than 73.78% of Java online submissions for Combination Sum IV.
class Solution377_3
{
	public int combinationSum4(int[] nums, int target)
	{
		int[] d=new int[target+1];
		d[0]=1;
		for (int i=1;i<=target;i++)
			for (int j=0;j<nums.length;j++)
				if (i>=nums[j]) d[i]+=d[i-nums[j]];
		return d[target];
	}
}

//378. Kth Smallest Element in a Sorted Matrix
//Runtime: 18 ms, faster than 53.14% of Java online submissions for Kth Smallest Element in a Sorted Matrix.
//Memory Usage: 49.9 MB, less than 100.00% of Java online submissions for Kth Smallest Element in a Sorted Matrix.
class Solution378
{
	public int kthSmallest(int[][] matrix, int k)
	{
		int n = matrix.length;
		int[] d = new int[n * n];
		for (int i = 0; i < n * n; i++)
			d[i] = matrix[i / n][i % n];
		Arrays.sort(d);
		return d[k - 1];
	}
}



//380. Insert Delete GetRandom O(1)

//Runtime: 119 ms, faster than 17.50% of Java online submissions for Insert Delete GetRandom O(1).
//Runtime: 55 ms, faster than 97.97% of Java online submissions for Insert Delete GetRandom O(1).
//Memory Usage: 47.5 MB, less than 53.12% of Java online submissions for Insert Delete GetRandom O(1).
class RandomizedSet
{
	HashMap<Integer, Integer> valToIndex;
	List<Integer> valList;
	Random rd;

	/** Initialize your data structure here. */
	public RandomizedSet()
	{
		valToIndex = new HashMap<Integer, Integer>();
		valList = new ArrayList<Integer>();
		rd = new Random();
	}

	/**
	 * Inserts a value to the set. Returns true if the set did not already contain
	 * the specified element.
	 */
	public boolean insert(int val)
	{
		if (valToIndex.containsKey(val))
			return false;
		valList.add(val);
		valToIndex.put(val, valList.size() - 1);
		return true;
	}

	/**
	 * Removes a value from the set. Returns true if the set contained the specified
	 * element.
	 */
	public boolean remove(int val)
	{
		if (!valToIndex.containsKey(val))
			return false;
		int ind = valToIndex.get(val);
		valToIndex.remove(val);
		if (ind == valList.size() - 1)
		{
			valList.remove(ind);
		} else
		{
			valList.set(ind, valList.get(valList.size() - 1));
			valList.remove(valList.size() - 1);
			valToIndex.put(valList.get(ind), ind);
		}
		return true;
	}

	/** Get a random element from the set. */
	public int getRandom()
	{
		if (valList.size() == 0)
			return 0;
		return valList.get(rd.nextInt(valList.size()));
	}
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet(); boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val); int param_3 = obj.getRandom();
 */

public class LC371_380
{
	public static void test377() throws IOException
	{
		File inFile = new File("input" + File.separator + "input377.txt");
		BufferedReader bfr = new BufferedReader(new FileReader(inFile));

		File outFile = new File("output" + File.separator + "output377.txt");
		BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

		String inLine;
		while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
		{
			String[] data = inLine.substring(1, inLine.length() - 1).split(",");
			int[] nums = new int[data.length];
			for (int i = 0; i < nums.length; i++)
				nums[i] = Integer.parseInt(data[i]);

			inLine = bfr.readLine();
			int target = Integer.parseInt(inLine);

			int ans = new Solution377_3().combinationSum4(nums, target);
			//Solution377_1, Solution377_2: time limit exceeded
			
			bfw.write("" + ans);
			bfw.newLine();
			
			System.out.println(ans);
		}

		bfr.close();
		bfw.flush();
		bfw.close();
	}

	public static void main(String[] args) throws IOException
	{
		test377();
	}
}
