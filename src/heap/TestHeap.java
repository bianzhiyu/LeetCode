package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import test.Test;

class Mp implements Comparable<Mp>
{
	int x, y;

	@Override
	public int compareTo(Mp o)
	{
		if (x != o.x)
			return x - o.x;
		return y - o.y;
	}

	public Mp(int _x, int _y)
	{
		x = _x;
		y = _y;
	}

	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
}

public class TestHeap
{
	public static void test1()
	{
		Heap<Integer> hp = new Heap<Integer>();
		int len = 20, Ran = 15;
		int[] a = test.Test.genRandomArr(len, Ran);
		Test.dispArr(a);
		for (int i = 0; i < len; i++)
			hp.offer(a[i]);
		Arrays.parallelSort(a);
		Test.dispArr(a);
		for (int i = 0; i < len; i++)
		{
			int n = hp.poll();
			System.out.print(n + ",");
			if (n != a[i])
				System.out.println("\nWrong sort!\n");
		}
		System.out.println();
		System.out.println("hp.size()=" + hp.size());
	}

	public static void test2()
	{
		int len = 20, amp = 15;
		List<Mp> l = new ArrayList<Mp>();
		Heap<Mp> hp = new Heap<Mp>();
		Random rd = new Random();
		for (int i = 0; i < len; i++)
		{
			int x = rd.nextInt(amp), y = rd.nextInt(amp);
			l.add(new Mp(x, y));
			hp.offer(new Mp(x, y));
		}
		Collections.sort(l);
		System.out.println(l);
		for (int i = 0; i < len; i++)
		{
			Mp p = hp.poll();
			System.out.print(p + ", ");
			if (p.compareTo(l.get(i)) != 0)
				System.out.println("\nWrong sort!\n");
		}
		System.out.println();
		System.out.println("hp.size()=" + hp.size());
	}

	public static void test3()
	{
		Heap<Integer> hp = new Heap<Integer>((x, y) -> y - x);
		int len = 20, Ran = 15;
		int[] a = test.Test.genRandomArr(len, Ran);
		Test.dispArr(a);
		for (int i = 0; i < len; i++)
			hp.offer(a[i]);
		Arrays.parallelSort(a);
		Test.dispArr(a);
		for (int i = 0; i < len; i++)
		{
			int n = hp.poll();
			System.out.print(n + ",");
			if (n != a[len - i - 1])
				System.out.println("\nWrong sort!\n");
		}
		System.out.println();
		System.out.println("hp.size()=" + hp.size());
	}
	// the 4th test: Solution973_6
	
	// the 5th test: Solution630_3
	
	// the 6th test: MedianFinder_3 295_3
	
	// the 7th test: Solution658_2

	public static void main(String[] args)
	{
		test3();
	}
}
