package lc181_190;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//187. Repeated DNA Sequences
//Runtime: 19 ms, faster than 60.23% of Java online submissions for Repeated DNA Sequences.
//Memory Usage: 42.2 MB, less than 94.60% of Java online submissions for Repeated DNA Sequences.
class Solution187
{
	private final int MOD = 1 << 18;

	private int toInt(char c)
	{
		if (c == 'A')
			return 0;
		else if (c == 'T')
			return 1;
		else if (c == 'C')
			return 2;
		else
			return 3;
	}

	private char toChar(int x)
	{
		if (x == 0)
			return 'A';
		else if (x == 1)
			return 'T';
		else if (x == 2)
			return 'C';
		return 'G';
	}

	public List<String> findRepeatedDnaSequences(String s)
	{
		HashSet<Integer> col = new HashSet<Integer>();
		HashSet<Integer> anscol = new HashSet<Integer>();
		List<String> ans = new ArrayList<String>();

		if (s == null || s.length() < 10)
			return ans;

		int state = 0;
		for (int i = 0; i < 9; i++)
			state = (state << 2) + toInt(s.charAt(i));

		for (int i = 9; i < s.length(); i++)
		{
			state = ((state % MOD) << 2) + toInt(s.charAt(i));
			if (col.contains(state))
				anscol.add(state);
			else
				col.add(state);
		}
		char[] tmp = new char[10];
		for (int x : anscol)
		{
			int y = x;
			for (int i = 9; i >= 0; i--)
			{
				tmp[i] = toChar(y % 4);
				y /= 4;
			}
			ans.add(new String(tmp));
		}
		return ans;
	}
}

//189. Rotate Array
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Rotate Array.
//Memory Usage: 38.1 MB, less than 35.11% of Java online submissions for Rotate Array.
class Solution189
{
	public void rotate(int[] nums, int k)
	{
		int l = nums.length;
		k = k % l;
		int[] a = new int[l];
		for (int i = 0; i < l; i++)
			a[i] = nums[(i + (l - k)) % l];
		for (int i = 0; i < l; i++)
			nums[i] = a[i];

	}
}

public class LC181_190
{
	public static void test187()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input187.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output187.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				inLine = inLine.substring(1, inLine.length() - 1);
				List<String> ans = new Solution187().findRepeatedDnaSequences(inLine);

				bfw.write(ans.toString());
				bfw.newLine();
				
				System.out.println(ans);
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
		test187();
	}
}
