package lc781_790;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class MainClass786 {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
          return new int[0];
        }
    
        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }
    
    public static String integerArrayToString(int[] nums, int length) {
        if (length == 0) {
            return "[]";
        }
    
        String result = "";
        for(int index = 0; index < length; index++) {
            int number = nums[index];
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }
    
    public static String integerArrayToString(int[] nums) {
        return integerArrayToString(nums, nums.length);
    }
    
    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in=new BufferedReader(
        		new FileReader(
        		new File("input"+File.separatorChar+"input786.txt")
        		));
        String line;
        while ((line = in.readLine()) != null
        		&& line.length()>0) {
            int[] A = stringToIntegerArray(line);
            line = in.readLine();
            int K = Integer.parseInt(line);
            
            int[] ret = new Solution786_2().kthSmallestPrimeFraction(A, K);
            
            String out = integerArrayToString(ret);
            
            System.out.print(out);
        }
        in.close();
    }
}

public class BackGround781_790 {
	public static void main(String[] args) throws IOException
	{
		MainClass786.main(args);
	}

}
