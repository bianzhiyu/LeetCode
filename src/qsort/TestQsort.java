package qsort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import test.Test;


class C implements Comparable<C>
{
	int x, y;

	C(int a, int b)
	{
		x = a;
		y = b;
	}

	@Override
	public int compareTo(C o)
	{
		if (x != o.x)
			return x - o.x;
		return y - o.y;
	}

	@Override
	public String toString()
	{
		return "{"+x+", "+y+"}";
	}

}


public class TestQsort
{
	static void test1()
	{
		int len=20,Amp=20;
		int[] a=Test.genRandomArr(len, Amp);
		Test.dispArr(a);
		Qsort.qsort(a);
		Test.dispArr(a);
	}
	static void test2()
	{
		Random rd = new Random(System.currentTimeMillis());
		int Amp=20;
		int len=10;
		List<C> in=new ArrayList<C>();
		for (int i=0;i<len;i++)
		{
			in.add(new C(rd.nextInt(Amp),rd.nextInt(Amp)));
			if (i%3==0)
				in.add(in.get(in.size()-1));
		}

		System.out.println(in);

		Qsort.qsort(in);

		System.out.println(in);
	}
	public static void main(String[] args)
	{
		test2();
	}
}
