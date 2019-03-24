package lc481_490;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

//481. Magical String
//Runtime: 5 ms, faster than 90.96% of Java online submissions for Magical String.
//Memory Usage: 36.6 MB, less than 33.33% of Java online submissions for Magical String.
class Solution481
{
	public int magicalString(int n)
	{
		byte[] str = new byte[n + 2];
		str[0] = 1;
		int matchedLen = 0, p2 = 0, p1 = 1;
		while (p1 < n)
		{
			if (p1 == p2)
			{
				if (str[p1 - 1] == 1)
				{
					str[p1] = 2;
					p1++;
				} else
				{
					str[p1] = 1;
					p1++;
				}
			} else if (str[p2] == 1)
			{
				if (matchedLen + 1 <= p1)
				{
					p2++;
					matchedLen++;
				} else
				{
					str[p1] = (byte) (3 - str[p1 - 1]);
					p1++;
					p2++;
					matchedLen++;
				}
			} else // 2
			{
				if (matchedLen + 2 <= p1)
				{
					p2 += 2;
					matchedLen += 2;
				} else if (matchedLen + 1 == p1)
				{
					str[p1] = str[p1 - 1];
					p1++;
					matchedLen += 2;
					p2++;
				} else // matchedLen==p1
				{
					str[p1] = (byte) (3 - str[p1 - 1]);
					str[p1 + 1] = str[p1];
					p1 += 2;
					matchedLen += 2;
					p2++;
				}
			}
		}
		int ct = 0;
		for (int i = 0; i < n; i++)
			if (str[i] == 1)
				ct++;
		return ct;
	}
}

//486. Predict the Winner
//Runtime: 4 ms, faster than 42.57% of Java online submissions for Predict the Winner.
//Memory Usage: 40 MB, less than 5.69% of Java online submissions for Predict the Winner.
class Solution486
{
	private HashMap<Integer, Integer> record = new HashMap<Integer, Integer>();
	private int[] n, sum;

	public boolean PredictTheWinner(int[] nums)
	{
		n = nums;
		sum = new int[n.length];
		sum[0] = nums[0];
		for (int i = 1; i < nums.length; i++)
			sum[i] = sum[i - 1] + nums[i];
		int max = dfs(0, n.length - 1);
		return max >= sum[n.length - 1] - max;
	}

	private int partsum(int left, int right)
	{
		if (left == 0)
			return sum[right];
		return sum[right] - sum[left - 1];
	}

	private int dfs(int left, int right)
	{
		if (left > right)
			return 0;
		if (left == right)
			return n[left];
		int state = (left << 6) + right;
		if (record.containsKey(state))
			return record.get(state);
		int leftmax = n[left] + partsum(left + 1, right) - dfs(left + 1, right);
		int rightmax = n[right] + partsum(left, right - 1) - dfs(left, right - 1);
		if (leftmax > rightmax)
		{
			record.put(state, leftmax);
			return leftmax;
		} else
		{
			record.put(state, rightmax);
			return rightmax;
		}
	}
}

//Runtime: 3 ms, faster than 77.79% of Java online submissions for Predict the Winner.
//Memory Usage: 36.9 MB, less than 28.46% of Java online submissions for Predict the Winner.
class Solution486_2
{
	private int [] sum;
	public boolean PredictTheWinner(int[] nums)
	{
		int len = nums.length;
		sum = new int[len];
		sum[0] = nums[0];
		for (int i = 1; i < nums.length; i++)
			sum[i] = sum[i - 1] + nums[i];
		int[][]d=new int[len][len];
		for (int i=0;i<len;i++)
			d[i][i]=nums[i];
		for (int i=2;i<=len;i++)
			for (int j=0;j+i-1<len;j++)
			{
				d[j][j+i-1]=nums[i+j-1]+partsum(j,j+i-2)-d[j][j+i-2];
				int l=nums[j]+partsum(j+1,j+i-1)-d[j+1][j+i-1];
				if (l>d[j][j+i-1]) d[j][j+i-1]=l;
			}
		return d[0][len-1]>=sum[len-1]-d[0][len-1];
	}
	private int partsum(int left, int right)
	{
		if (left == 0)
			return sum[right];
		return sum[right] - sum[left - 1];
	}
}

public class LC481_490
{
	public static void test481()
	{
		Solution481 s = new Solution481();
		System.out.println("\n" + s.magicalString(100));
	}

	public static void test486()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input486.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output486.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				String[] data = inLine.substring(1, inLine.length() - 1).split(",");
				int[] nums = new int[data.length];
				for (int i = 0; i < data.length; i++)
					nums[i] = Integer.parseInt(data[i].trim());

				Solution486 s = new Solution486();

				boolean ans = s.PredictTheWinner(nums);

				bfw.write("" + ans);
				System.out.println(ans);
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
		// System.out.println("".trim());
		test486();
	}
}
