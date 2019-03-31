package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIOTest
{
	public static void test377()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input377.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output377.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int[] nums = test.Test.parseIntArr(inLine);

				inLine = bfr.readLine();
				int target = Integer.parseInt(inLine);

				int ans = 0;

				bfw.write("" + test.Test.intArrToString(nums) + target + ans);
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
	@SuppressWarnings("unused")
	public static void test802()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input802.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output802.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int[][] g=test.Test.parse2DIntArr(inLine);

//				Solution802 s=new Solution802();

//				List<Integer> ans = s.eventualSafeNodes(g);
				
				int ans=0;
				
				System.out.println(ans);
				bfw.write(ans+"");
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
}
