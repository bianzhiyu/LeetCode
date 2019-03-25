package lc621_630;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import heap.Heap;

//622. Design Circular Queue
//Runtime: 49 ms, faster than 99.72% of Java online submissions for Design Circular Queue.
//Memory Usage: 39.6 MB, less than 51.49% of Java online submissions for Design Circular Queue.
class MyCircularQueue
{

	private int[] q;
	private int f = 0, r = 0, num = 0, cap;

	/** Initialize your data structure here. Set the size of the queue to be k. */
	public MyCircularQueue(int k)
	{
		q = new int[k + 2];
		cap = k;
	}

	/**
	 * Insert an element into the circular queue. Return true if the operation is
	 * successful.
	 */
	public boolean enQueue(int value)
	{
		if (num == cap)
			return false;
		q[r] = value;
		r = (r + 1) % q.length;
		num++;
		return true;
	}

	/**
	 * Delete an element from the circular queue. Return true if the operation is
	 * successful.
	 */
	public boolean deQueue()
	{
		if (num == 0)
			return false;
		f = (f + 1) % q.length;
		num--;
		return true;
	}

	/** Get the front item from the queue. */
	public int Front()
	{
		if (num > 0)
			return q[f];
		else
			return -1;
	}

	/** Get the last item from the queue. */
	public int Rear()
	{
		if (num > 0)
		{
			if (r == 0)
				return q[q.length - 1];
			else
				return q[r - 1];
		} else
			return -1;
	}

	/** Checks whether the circular queue is empty or not. */
	public boolean isEmpty()
	{
		return num == 0;
	}

	/** Checks whether the circular queue is full or not. */
	public boolean isFull()
	{
		return num == cap;
	}
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k); boolean param_1 =
 * obj.enQueue(value); boolean param_2 = obj.deQueue(); int param_3 =
 * obj.Front(); int param_4 = obj.Rear(); boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
//--------

//630. Course Schedule III
//Runtime: 79 ms, faster than 88.14% of Java online submissions for Course Schedule III.
//Memory Usage: 61.9 MB, less than 36.36% of Java online submissions for Course Schedule III.
class MPair implements Comparable<MPair>
{
	int Length, Deadline;

	MPair(int l, int d)
	{
		Length = l;
		Deadline = d;
	}

	@Override
	public int compareTo(MPair o)
	{
		if (Deadline != o.Deadline)
			return Deadline - o.Deadline;
		else
			return Length - o.Length;
	}

	@Override
	public String toString()
	{
		return "[" + Length + ", " + Deadline + "]";
	}

}

class Solution630
{
	public int scheduleCourse(int[][] courses)
	{
		int clen = courses.length;
		List<MPair> l = new ArrayList<MPair>();
		for (int i = 0; i < clen; i++)
			l.add(new MPair(courses[i][0], courses[i][1]));
		Collections.sort(l);

		PriorityQueue<MPair> pq = new PriorityQueue<MPair>(clen, (MPair sv1, MPair sv2) -> sv2.Length - sv1.Length);

		int cur = 0;
		for (int i = 0; i < clen; i++)
		{
			MPair p = l.get(i);
			if (cur + p.Length <= p.Deadline)
			{
				cur += p.Length;
				pq.add(p);
			} else if (!pq.isEmpty() && pq.peek().Length > p.Length)
			{
				cur += p.Length - pq.peek().Length;
				pq.remove();
				pq.add(p);
			}
		}

		return pq.size();
	}
}

//Only thing changed is that
//using an anonymous class instead of a lambda expression,
//but the time efficiency has been improved largely
//according to the LeetCome online judger.
//Runtime: 48 ms, faster than 99.55% of Java online submissions for Course Schedule III.
//Memory Usage: 47.1 MB, less than 100.00% of Java online submissions for Course Schedule III.
class Solution630_2
{
	public int scheduleCourse(int[][] courses)
	{
		int clen = courses.length;
		List<MPair> l = new ArrayList<MPair>();
		for (int i = 0; i < clen; i++)
			l.add(new MPair(courses[i][0], courses[i][1]));
		Collections.sort(l);

		PriorityQueue<MPair> pq = new PriorityQueue<MPair>(clen, new Comparator<MPair>()
		{
			@Override
			public int compare(MPair o1, MPair o2)
			{
				return o2.Length - o1.Length;
			}
		});

		int cur = 0;
		for (int i = 0; i < clen; i++)
		{
			MPair p = l.get(i);
			if (cur + p.Length <= p.Deadline)
			{
				cur += p.Length;
				pq.add(p);
			} else if (!pq.isEmpty() && pq.peek().Length > p.Length)
			{
				cur += p.Length - pq.peek().Length;
				pq.remove();
				pq.add(p);
			}
		}

		return pq.size();
	}
}

//test self implemented class:  Heap
//Runtime: 86 ms, faster than 65.16% of Java online submissions for Course Schedule III.
//Memory Usage: 60.1 MB, less than 70.91% of Java online submissions for Course Schedule III.
class Solution630_3
{
	public int scheduleCourse(int[][] courses)
	{
		int clen = courses.length;
		List<MPair> l = new ArrayList<MPair>();
		for (int i = 0; i < clen; i++)
			l.add(new MPair(courses[i][0], courses[i][1]));
		Collections.sort(l);

		Heap<MPair> pq = new Heap<MPair>((MPair sv1, MPair sv2) -> sv2.Length - sv1.Length);

		int cur = 0;
		for (int i = 0; i < clen; i++)
		{
			MPair p = l.get(i);
			if (cur + p.Length <= p.Deadline)
			{
				cur += p.Length;
				pq.offer(p);
			} else if (!(pq.size() == 0) && pq.peek().Length > p.Length)
			{
				cur += p.Length - pq.peek().Length;
				pq.poll();
				pq.offer(p);
			}
		}

		return pq.size();
	}
}

public class LC621_630
{
	public static void main(String[] args)
	{
		List<MPair> l = new ArrayList<MPair>();
		l.add(new MPair(1, 2));
		l.add(new MPair(2, 3));
		Collections.sort(l);
		System.out.println(l);
	}

}
