package lc611_620;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

//611. Valid Triangle Number
//Runtime: 33 ms, faster than 34.12% of Java online submissions for Valid Triangle Number.
//Memory Usage: 45 MB, less than 25.00% of Java online submissions for Valid Triangle Number.
class Solution611
{
	public int triangleNumber(int[] nums)
	{
		int tot = 0;
		Arrays.sort(nums);
		int len = nums.length;
		for (int i = 0; i < len - 2; i++)
			for (int j = i + 1; j < len - 1; j++)
			{
				int s = nums[i] + nums[j];
				if (s <= nums[j + 1])
					continue;
				int l = j + 1, r = len - 1;
				while (l < r)
				{
					if (r == l + 1)
					{
						if (nums[r] < s)
							l = r;
						else
							r = l;
					}
					int m = (l + r) / 2;
					if (nums[m] >= s)
						r = m;
					else
						l = m;
				}
				tot += l - j;
			}
		return tot;
	}
}

public class LC611_620
{
	public static void test611()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input611.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output611.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int[] nums = test.Test.parseIntArr(inLine);

				Solution611 solver = new Solution611();

				int ans = solver.triangleNumber(nums);
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
		test611();
	}
}
