package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class qs
{
	public static void swap(int[] a, int p1, int p2)
	{
		int t = a[p1];
		a[p1] = a[p2];
		a[p2] = t;
	}

	public static void qst(int[] a, int l, int r)
	{
		if (l >= r)
			return;
		int pi = l;
		int p = a[pi];
		swap(a, pi, r - 1);
		int storeind = l;
		for (int i = l; i < r - 1; i++)
			if (a[i] < p)
			{
				swap(a, storeind, i);
				storeind++;
			}
		swap(a, storeind, r - 1);
		qst(a, l, storeind);
		qst(a, storeind + 1, r);
	}
}

public class Test
{
	public static void dispArr(int[] a)
	{
		for (int i = 0; i < a.length; i++)
		{
			System.out.print(a[i] + ",");
		}
		System.out.println();
	}

	public static void dispArr(double[] a)
	{
		for (int i = 0; i < a.length; i++)
		{
			System.out.print(a[i] + ",");
		}
		System.out.println();
	}

	public static void dist2DArr(int[][] a)
	{
		for (int i = 0; i < a.length; i++)
		{
			for (int j = 0; j < a[i].length; j++)
				System.out.print(a[i][j] + ",");
			System.out.println();
		}
		System.out.println();
	}

	public static int[] genRandomArr(int len, int Ran)
	{
		int[] a = new int[len];
		Random rd = new Random(System.currentTimeMillis());
		for (int i = 0; i < len; i++)
			a[i] = rd.nextInt(Ran);
		return a;
	}

	public static void testFinally()
	{
		try
		{
			System.out.println("try block");
			return;
		} finally
		{
			System.out.println("finally block");
			// return "finally";
		}
	}

	public static int[] copyArr(int[] a)
	{
		int[] b = new int[a.length];
		for (int i = 0; i < a.length; i++)
			b[i] = a[i];
		return b;
	}

	/** model: [1,2,3] */
	public static int[] parseIntArr(String str)
	{
		str = str.trim();
		str = str.substring(1, str.length() - 1);
		str = str.trim();
		if (str.length() == 0)
			return new int[0];
		String[] strarr = str.split(",");
		int[] a = new int[strarr.length];
		for (int i = 0; i < strarr.length; i++)
			a[i] = Integer.parseInt(strarr[i].trim());
		return a;
	}

	/** model: ["a","b","c"] */
	public static String[] parseStrArr(String str)
	{
		str = str.trim();
		str = str.substring(1, str.length() - 1);
		str = str.trim();
		if (str.length() == 0)
			return new String[0];
		String[] strarr = str.split(",");
		for (int i = 0; i < strarr.length; i++)
			strarr[i] = strarr[i].substring(1, strarr[i].length() - 1);
		return strarr;
	}

	public static String int2DArrToString(int[][] arr)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < arr.length; i++)
		{
			sb.append("[");
			for (int j = 0; j < arr[i].length; j++)
			{
				sb.append(arr[i][j]);
				if (j != arr[i].length - 1)
					sb.append(", ");
			}
			sb.append("]");
			if (i != arr.length - 1)
				sb.append(", ");
		}
		sb.append("]");
		return sb.toString();
	}

	public static String intArrToString(int[] arr)
	{
		if (arr == null || arr.length == 0)
			return "[]";
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (int i = 0; i < arr.length - 1; i++)
			sb.append(arr[i]).append(',');
		sb.append(arr[arr.length - 1]).append(']');
		return sb.toString();
	}

	/** model: "[1,2,3]" */
	public static List<Integer> parseIntList(String str)
	{
		str = str.trim();
		str = str.substring(1, str.length() - 1);
		str = str.trim();
		if (str.length() == 0)
			return new ArrayList<Integer>();
		String[] strarr = str.split(",");
		List<Integer> a = new ArrayList<Integer>(strarr.length);
		for (int i = 0; i < strarr.length; i++)
			a.add(Integer.parseInt(strarr[i].trim()));
		return a;
	}

	public static List<List<Integer>> parse2DIntList(String in)
	{
		List<List<Integer>> out = new ArrayList<List<Integer>>();
		int p = 1;
		while (p < in.length() - 1)
		{
			if (in.charAt(p) == '[')
			{
				int k = p + 1;
				while (in.charAt(k) != ']')
					k++;
				out.add(parseIntList(in.substring(p, k + 1)));
				p = k + 1;
			} else
				p++;
		}
		return out;
	}

	public static int[][] parse2DIntArr(String in)
	{
		List<List<Integer>> out = parse2DIntList(in);
		int[][] o2 = new int[out.size()][];
		for (int i = 0; i < o2.length; i++)
		{
			o2[i] = new int[out.get(i).size()];
			for (int j = 0; j < o2[i].length; j++)
				o2[i][j] = out.get(i).get(j);
		}
		return o2;
	}

	public static void main(String[] args)
	{
		System.out.println();
	}

}
