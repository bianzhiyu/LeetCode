package lc591_600;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

//592. Fraction Addition and Subtraction
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Fraction Addition and Subtraction.
//Memory Usage: 35.8 MB, less than 100.00% of Java online submissions for Fraction Addition and Subtraction.
class Solution592
{
	private int gcd(int a, int b)
	{
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	public String fractionAddition(String expression)
	{
		int p = 0, len = expression.length(), sign;
		int up = 0, down = 1;
		while (p < len)
		{
			if (p == 0)
			{
				if (expression.charAt(0) == '-')
				{
					sign = -1;
					p++;
				} else
					sign = 1;
			} else
			{
				if (expression.charAt(p) == '-')
					sign = -1;
				else
					sign = 1;
				p++;
			}
			int x = 0;
			while (expression.charAt(p) != '/')
			{
				x = x * 10 + (expression.charAt(p) - '0');
				p++;
			}
			p++;
			int y = 0;
			while (p < len && expression.charAt(p) >= '0' && expression.charAt(p) <= '9')
			{
				y = y * 10 + (expression.charAt(p) - '0');
				p++;
			}

			int e = gcd(down, y);
			up = (y / e) * up + sign * x * (down / e);
			down = down * y / e;

			if (up == 0)
			{
				down = 1;
			} else
			{
				e = gcd(Math.abs(up), down);
				down /= e;
				up /= e;
			}
		}
		if (up == 0)
			return "0/1";
		return up + "/" + down;
	}
}

//594 Longest Harmonious Subsequence    
//Runtime: 34 ms, faster than 95.06% of Java online submissions for Longest Harmonious Subsequence.
//Memory Usage: 42.3 MB, less than 30.44% of Java online submissions for Longest Harmonious Subsequence.
class Solution594
{
	public int findLHS(int[] nums)
	{
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++)
			if (hm.containsKey(nums[i]))
				hm.put(nums[i], hm.get(nums[i]) + 1);
			else
				hm.put(nums[i], 1);
		int m = 0, t = 0;
		for (int i : hm.keySet())
			if (hm.containsKey(i + 1))
			{
				t = hm.get(i) + hm.get(i + 1);
				if (t > m)
					m = t;
			}
		return m;
	}
}

public class LC591_600
{
	public static void test594()
	{
		Solution594 s = new Solution594();
		System.out.println(s.findLHS(new int[]
		{ 1, 1, 1, 1 }));
	}

	public static void test592()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input592.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output592.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				Solution592 s = new Solution592();

				String ans = s.fractionAddition(inLine);
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

	public static void main(String[] args)
	{
		test592();
	}
}
