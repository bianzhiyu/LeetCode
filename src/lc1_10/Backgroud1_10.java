package lc1_10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Background t1
/*input
[2,7,11,15]
9
 */
class MainClass1
{
	public static int[] stringToIntegerArray(String input)
	{
		input = input.trim();
		input = input.substring(1, input.length() - 1);
		if (input.length() == 0)
		{
			return new int[0];
		}

		String[] parts = input.split(",");
		int[] output = new int[parts.length];
		for (int index = 0; index < parts.length; index++)
		{
			String part = parts[index].trim();
			output[index] = Integer.parseInt(part);
		}
		return output;
	}

	public static String integerArrayToString(int[] nums, int length)
	{
		if (length == 0)
		{
			return "[]";
		}

		String result = "";
		for (int index = 0; index < length; index++)
		{
			int number = nums[index];
			result += Integer.toString(number) + ", ";
		}
		return "[" + result.substring(0, result.length() - 2) + "]";
	}

	public static String integerArrayToString(int[] nums)
	{
		return integerArrayToString(nums, nums.length);
	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = in.readLine()) != null)
		{
			int[] nums = stringToIntegerArray(line);
			line = in.readLine();
			int target = Integer.parseInt(line);

			int[] ret = new Solution1().twoSum(nums, target);

			String out = integerArrayToString(ret);

			System.out.print(out);
		}
	}
}

//Background t2
/*
 * input: [2,4,3] [5,6,4]
 */
class MainClass2
{
	public static int[] stringToIntegerArray(String input)
	{
		input = input.trim();
		input = input.substring(1, input.length() - 1);
		if (input.length() == 0)
		{
			return new int[0];
		}

		String[] parts = input.split(",");
		int[] output = new int[parts.length];
		for (int index = 0; index < parts.length; index++)
		{
			String part = parts[index].trim();
			output[index] = Integer.parseInt(part);
		}
		return output;
	}

	public static ListNode stringToListNode(String input)
	{
		// Generate array from the input
		int[] nodeValues = stringToIntegerArray(input);

		// Now convert that list into linked list
		ListNode dummyRoot = new ListNode(0);
		ListNode ptr = dummyRoot;
		for (int item : nodeValues)
		{
			ptr.next = new ListNode(item);
			ptr = ptr.next;
		}
		return dummyRoot.next;
	}

	public static String listNodeToString(ListNode node)
	{
		if (node == null)
		{
			return "[]";
		}

		String result = "";
		while (node != null)
		{
			result += Integer.toString(node.val) + ", ";
			node = node.next;
		}
		return "[" + result.substring(0, result.length() - 2) + "]";
	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = in.readLine()) != null)
		{
			ListNode l1 = stringToListNode(line);
			line = in.readLine();
			ListNode l2 = stringToListNode(line);

			ListNode ret = new Solution2().addTwoNumbers(l1, l2);

			String out = listNodeToString(ret);

			System.out.print(out);
		}
	}
}

class MainClass10
{
	public static String booleanToString(boolean input)
	{
		return input ? "True" : "False";
	}

	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader(
				new File("input"+File.separator+"input10.txt")));
		String line;
		while ((line = in.readLine()) != null &&
				line.length()>0)
		{
			String s = line.substring(1,line.length()-1);
			line = in.readLine();
			String p = line.substring(1,line.length()-1);

			boolean ret = new Solution10().isMatch(s, p);

			String out = booleanToString(ret);

			System.out.print(out);
		}
		in.close();
	}
}

public class Backgroud1_10
{
	public static void main(String[] args) throws IOException
	{
		MainClass10.main(args);
	}

}
