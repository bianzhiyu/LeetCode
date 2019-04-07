package eulerianpath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestEulerianPath
{
	public static void testDirected()
	{
		try
		{
			File inFile = new File("input" + File.separator + "Input_EulerianPathDircect.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "Output_EulerianPathDircect.txt.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int n=Integer.parseInt(inLine);
				int[][] es=test.Test.parse2DIntArr(bfr.readLine());

				List<List<Integer>> ll=new ArrayList<List<Integer>>(n);
				for (int i=0;i<n;i++)
					ll.add(new ArrayList<Integer>());
				
				for (int[] b:es)
				{
					ll.get(b[0]).add(b[1]);
				}

				List<Integer> ans=EulerianPath.getPathDirectedGraph(n,ll);
								
				System.out.println(ans);
				bfw.write(ans.toString());
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
	public static void testUndirected()
	{
		//
		try
		{
			File inFile = new File("input" + File.separator + "Input_EulerianPathUndircect.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "Output_EulerianPathUndircect.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				int n=Integer.parseInt(inLine);
				int[][] es=test.Test.parse2DIntArr(bfr.readLine());


				List<Integer> ans=EulerianPath.getPathUndirectedGraph(n,es);
								
				System.out.println(ans);
				bfw.write(ans.toString());
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
	public static void main(String[]args)
	{
		testDirected();
//		testUndirected();
	}
}
