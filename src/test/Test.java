package test;

import java.util.Arrays;
import java.util.Random;


class qs
{
	public static void swap(int[] a,int p1,int p2)
	{
		int t=a[p1];
		a[p1]=a[p2];
		a[p2]=t;
	}
	public static void qst(int[]a,int l,int r)
	{
		if (l>=r) return;
		int pi=l;
		int p=a[pi];
		swap(a,pi,r-1);
		int storeind=l;
		for (int i=l;i<r-1;i++)
			if (a[i]<p)
			{
				swap(a,storeind,i);
				storeind++;
			}
		swap(a,storeind,r-1);
		qst(a,l,storeind);
		qst(a,storeind+1,r);
	}
}

public class Test {
	public static void dispArr(int [] a)
	{
		for (int i=0;i<a.length;i++)
		{
			System.out.print(a[i]+",");
		}
		System.out.println();
	}
	public static void dist2DArr(int [][] a)
	{
		for (int i=0;i<a.length;i++)
		{
			for (int j=0;j<a[i].length;j++)
				System.out.print(a[i][j]+",");
			System.out.println();
		}
		System.out.println();
	}
	public static int[] genRandomArr(int len,int Ran)
	{
		int[] a=new int[len];
		Random rd=new Random(System.currentTimeMillis());
		for (int i=0;i<len;i++)
			a[i]=rd.nextInt(Ran);
		return a;
	}

	public static void testFinally()
	{
		try {  
			System.out .println("try block");  
			return;  
		} finally {  
			System.out .println("finally block");
			//return "finally";    
		}
	}
	public static int[] copyArr(int[]a)
	{
		int[]b=new int[a.length];
		for (int i=0;i<a.length;i++)
			b[i]=a[i];
		return b;
	}
	public static void main(String[] args)
	{
		int[] a=genRandomArr(20,20);
		dispArr(a);
		int[] b=copyArr(a);
		Arrays.parallelSort(b);
		dispArr(b);
		dispArr(a);
		qs.qst(a, 0, a.length);
		dispArr(a);
		
	}
}
