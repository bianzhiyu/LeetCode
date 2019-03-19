package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIOTest
{
	public static void test377() throws IOException
	{
		File inFile=new File("input"+File.separator+"input377.txt");
		BufferedReader bfr=new BufferedReader(new FileReader(inFile));

		File outFile=new File("output"+File.separator+"output377.txt");
		BufferedWriter bfw=new BufferedWriter(new FileWriter(outFile));

		String inLine;
		while ((inLine=bfr.readLine())!=null && inLine.length()>0)
		{
			String[] data=inLine.substring(1, inLine.length()-1).split(",");
			int[] nums=new int[data.length];
			for (int i=0;i<nums.length;i++)
				nums[i]=Integer.parseInt(data[i]);
			
			inLine=bfr.readLine();
			int target=Integer.parseInt(inLine);
			
			int ans=0;
//			int ans=new Solution377_2().combinationSum4(nums, target);
			bfw.write(""+target+ans);
			bfw.newLine();
		}

		bfr.close();
		bfw.flush();bfw.close();
	}
}
