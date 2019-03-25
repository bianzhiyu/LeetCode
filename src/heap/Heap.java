package heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("unchecked")
public class Heap<T>
{
	private Comparator<? super T> cmp;
	private List<T> list = new ArrayList<T>();

	public Heap(Comparator<? super T> __cmp)
	{
		cmp = __cmp;
	}

	public Heap()
	{
		this(null);
	}

	public void offer(T e)
	{
		list.add(e);
		if (cmp == null)
			siftUpComparable(list.size() - 1);
		else
			siftUpUsingComparator(list.size() - 1);

	}

	private void siftUpComparable(int pos)
	{
		Comparable<T> key = (Comparable<T>) list.get(pos);
		while (pos > 0)
		{
			int parentInd = (pos - 1) >>> 1;
			T parent = list.get(parentInd);
			if (key.compareTo(parent) >= 0)
				break;
			list.set(pos, parent);
			pos = parentInd;
		}
		list.set(pos, (T) key);
	}

	private void siftUpUsingComparator(int pos)
	{
		T key = list.get(pos);
		while (pos > 0)
		{
			int parentInd = (pos - 1) >>> 1;
			T parent = list.get(parentInd);
			if (cmp.compare(key, parent) >= 0)
				break;
			list.set(pos, parent);
			pos = parentInd;
		}
		list.set(pos, key);
	}

	public T peek()
	{
		return list.get(0);
	}

	public int size()
	{
		return list.size();
	}

	public boolean contains(T o)
	{
		return list.indexOf(o) != -1;
	}

	public boolean remove(T o)
	{
		int pos = list.indexOf(o);
		if (pos == -1)
			return false;
		if (pos == list.size() - 1)
		{
			list.remove(list.size() - 1);
			return true;
		}
		T last = list.get(list.size() - 1);
		list.remove(list.size() - 1);
		if (list.size() == 0)
			return true;
		if (cmp != null)
		{
			siftDownUsingComparator(pos, last);
			if (list.get(pos) == last)
				siftUpUsingComparator(pos);
		} else
		{
			siftDownComparable(pos, last);
			if (list.get(pos) == last)
				siftUpComparable(pos);
		}
		return true;
	}

	private void siftDownUsingComparator(int pos, T e)
	{
		int child;
		while ((child = (pos << 1) + 1) < list.size())
		{
			if (child + 1 < list.size() && cmp.compare(list.get(child + 1), list.get(child)) < 0)
				child++;
			if (cmp.compare(e, list.get(child)) <= 0)
				break;
			list.set(pos, list.get(child));
			pos = child;
		}
		list.set(pos, e);
	}

	private void siftDownComparable(int pos, T e)
	{
		Comparable<T> key = (Comparable<T>) e;
		int child;
		while ((child = (pos << 1) + 1) < list.size())
		{
			if (child + 1 < list.size() && ((Comparable<T>) list.get(child + 1)).compareTo(list.get(child)) < 0)
				child++;
			if (key.compareTo(list.get(child)) <= 0)
				break;
			list.set(pos, list.get(child));
			pos = child;

		}
		list.set(pos, e);
	}

	public T poll()
	{
		T head = list.get(0);
		T last = list.get(list.size() - 1);
		list.remove(list.size() - 1);
		if (list.size() == 0)
			return head;
		if (cmp == null)
			siftDownComparable(0, last);
		else
			siftDownUsingComparator(0, last);
		return head;
	}

	public Comparator<? super T> comparator()
	{
		return cmp;
	}

	public void removeAll()
	{
		list = new ArrayList<T>();
	}
}
