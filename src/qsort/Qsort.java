package qsort;

import java.util.List;

public class Qsort
{
	
	public static void swap(int[] a,int p1,int p2)
	{
		int t=a[p1];
		a[p1]=a[p2];
		a[p2]=t;
	}
	public static <T extends Comparable<? super T>> 
	void swap(List<T>a,int p1,int p2)
	{
		T t=a.get(p1);
		a.set(p1, a.get(p2));
		a.set(p2, t);
	}
	/** index l inclusive, index r exclusive. */
	public static int partition(int[] a,int l,int r,int pivotInd)
	{
		if (l>=r) return l;
		int pivot=a[pivotInd];
		swap(a,pivotInd,r-1);
		int storeInd=l;
		for (int i=l;i<r-1;i++)
			if (a[i]<pivot)
			{
				swap(a,storeInd,i);
				storeInd++;
			}
		swap(a,r-1,storeInd);
		return storeInd;
	}
	/** index l inclusive, index r exclusive. */
	public static <T extends Comparable<? super T>> 
	int partition(List<T> a,int l,int r,int pivotInd)
	{
		if (l>=r) return l;
		T pivot=a.get(pivotInd);
		swap(a,pivotInd,r-1);
		int storeInd=l;
		for (int i=l;i<r-1;i++)
			if (a.get(i).compareTo(pivot)<0)
			{
				swap(a,storeInd,i);
				storeInd++;
			}
		swap(a,r-1,storeInd);
		return storeInd;
	}
	/** index l inclusive, index r exclusive. */
	public static void qsort(int[] a,int l,int r)
	{
		if (l>=r) return;
		int pivotNewInd=partition(a,l,r,l+(r-1-l)/2);
		qsort(a,l,pivotNewInd);
		qsort(a,pivotNewInd+1,r);
	}
	/** index l inclusive, index r exclusive. */
	public static <T extends Comparable<? super T>> void qsort(List<T> a,int l,int r)
	{
		if (l>=r) return;
		int pivotNewInd=partition(a,l,r,l+(r-1-l)/2);
		qsort(a,l,pivotNewInd);
		qsort(a,pivotNewInd+1,r);
	}
	public static void qsort(int[] a)
	{
		qsort(a,0,a.length);
	}
	public static <T extends Comparable<? super T>> void qsort(List<T> l)
	{
		qsort(l,0,l.size());
	}
	
}
