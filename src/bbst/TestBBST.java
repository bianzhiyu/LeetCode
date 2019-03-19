package bbst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

public class TestBBST
{
	public static void test1()
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

		BBST<C> r = new BBST<C>(in.get(0));
		for (int i = 1; i < in.size(); i++)
		{
			System.out.print(in.get(i)+", ");
			r=r.insert(in.get(i));
		}
		System.out.println();

		for (int i=0;i<in.size();i++)
		{
			C t=r.getMinData();
			System.out.print(t+", ");
			r=r.removeNodeByData(new C(t.x,t.y));
		}
		System.out.println();

		Collections.sort(in);
		System.out.println(in);
	}
	public static void test2()
	{
		Random rd = new Random(System.currentTimeMillis());
		int Amp=20;
		int len=10;
		List<Integer> in=new ArrayList<Integer>();
		for (int i=0;i<10;i++)
		{
			in.add(rd.nextInt(Amp));

		}

		BBST<Integer> r = new BBST<Integer>(0);
		in.add(0);
		for (int i = 0; i < len; i++)
		{
			System.out.print(in.get(i)+", ");
			r=r.insert(in.get(i));
		}
		System.out.println();

		for (int i=0;i<len+1;i++)
		{
			int t=r.getMinData();
			System.out.print(t+"  ");
			r=r.removeMin();
		}
		System.out.println();

		Collections.sort(in);
		System.out.println(in);
	}
	public static void main(String[] args)
	{
		test1();
		//		HashMap<String,String> h=new HashMap<String,String>();
		//		String a="123";
		//		String b="123";
		//		String c="456";
		//		String d="567";
		//		h.put(a,c);
		//		h.put(b,d);
		////		System.out.println(h.get("123"));
		////		System.out.println(h.get(a));
		////		for (String s:h.keySet())
		////			System.out.print(s);
		//		
		//		HashMap<List<String>,String> h1=new HashMap<List<String>,String>();
		//		List<String> a1=new ArrayList<String>();
		//		a1.add("123");
		//		h1.put(a1,"a1");
		//		List<String> b1=new ArrayList<String>();
		//		b1.add("123");
		//		h1.put(b1,"b1");
		//		System.out.println(h1.get(a1));
		//		System.out.println(h1.get(b1));
		//		
		//		for (List<String> l:h1.keySet())
		//			System.out.println(l);
		//		
		//		
		//		HashMap<C,String> h2=new HashMap<C,String>();
		//		C a2=new C(1,2);
		//		h2.put(a2,"a2");
		//		C b2=new C(1,2);
		//		h2.put(b2,"b2");
		//		System.out.println(h2.get(a2));
		//		System.out.println(h2.get(b2));
		//		
		//		for (C l:h2.keySet())
		//			System.out.println(l);
		//		
		//		HashMap<int[],String> h3=new HashMap<int[],String>();
		//		int[] a3=new int[] {1,2};
		//		int[] b3=new int[] {1,2};
		//		h3.put(a3,"a3");
		//		h3.put(b3,"b3");
		//		System.out.println(h3.get(a3));
		//		System.out.println(h3.get(b3));


	}
}
