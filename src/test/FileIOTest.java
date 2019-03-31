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
	public static void test787()
	{
		try
		{
			File inFile = new File("input" + File.separator + "input787.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output787.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int n=Integer.parseInt(inLine);
				
				inLine = bfr.readLine();
				
				String[] tmp=inLine.split("\\],\\[)|(\\]\\])");
				tmp[0]=tmp[0].substring(2);
				int[][]f=new int[tmp.length][];
				for (int i=0;i<tmp.length;i++)
				{
					String[] s=tmp[i].split(",");
					f[i]=new int[s.length];
					for (int j=0;j<s.length;j++)
						f[i][j]=Integer.parseInt(s[j]);
				}
				int src=Integer.parseInt(bfr.readLine());
				int dst=Integer.parseInt(bfr.readLine());
				int K=Integer.parseInt(bfr.readLine());

				//Solution787 s=new Solution787();

				//int ans = s.findCheapestPrice(n, f, src, dst, K);
				
				int ans=0;
				
				System.out.println(ans);

				bfw.write(""  + ans);
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
