package lc621_630;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import heap.Heap;
import treeCodec.*;

//621. Task Scheduler
//Wrong answer
class Solution621
{
	public int leastInterval(char[] tasks, int n)
	{
		int[] tks = new int[26];
		int[] et = new int[n + 1];
		for (int i = 0; i < tasks.length; i++)
			tks[tasks[i] - 'A']++;
		Arrays.sort(tks);
		for (int i = 25; i >= 0; i--)
		{
			if (tks[i] == 0)
				break;
			int minind = 0;
			for (int j = 1; j <= n; j++)
			{
				if (et[j] < et[minind])
					minind = j;
			}
			et[minind] += tks[i];
		}
		int mint = 0;
		for (int i = 0; i <= n; i++)
			if (et[i] > 0)
			{
				mint = Math.max(mint, (et[i] - 1) * (n + 1) + (i + 1));
			}
		return mint;
	}
}

//https://leetcode.com/problems/task-scheduler/discuss/259218/Java-solution-using-priority-queue-with-explanation
//Runtime: 37 ms, faster than 40.21% of Java online submissions for Task Scheduler.
//Memory Usage: 40 MB, less than 60.78% of Java online submissions for Task Scheduler.
class Solution621_2
{
	private static class Mp implements Comparable<Mp>
	{
		private int type, ct;

		public Mp(int _t, int _c)
		{
			type = _t;
			ct = _c;
		}

		public int compareTo(Mp o)
		{
			return o.ct - ct;
		}

		public String toString()
		{
			return "(" + type + ", " + ct + ")";
		}
	}

	public int leastInterval(char[] tasks, int n)
	{
		int[] tks = new int[26];
		for (int i = 0; i < tasks.length; i++)
		{
			tks[tasks[i] - 'A']++;
		}
		PriorityQueue<Mp> pq = new PriorityQueue<Mp>();
		for (int i = 0; i < 26; i++)
			if (tks[i] > 0)
				pq.offer(new Mp(i, tks[i]));
		int tot = 0;
		List<Mp> thisPeriod = new ArrayList<Mp>();
		while (!pq.isEmpty())
		{
			thisPeriod.clear();
			for (int i = 0; i < n + 1; i++)
			{
				if (pq.isEmpty())
				{
					if (!thisPeriod.isEmpty())
						tot++;// idle time;
					continue;
				}
				Mp t = pq.poll();
				t.ct--;
				tot++;
				if (t.ct > 0)
					thisPeriod.add(t);
			}
			for (int i = 0; i < thisPeriod.size(); i++)
				pq.offer(thisPeriod.get(i));
		}
		return tot;
	}
}

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

//623. Add One Row to Tree
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Add One Row to Tree.
//Memory Usage: 37.7 MB, less than 96.72% of Java online submissions for Add One Row to Tree.
class Solution623
{
	private void travel(TreeNode rt, int depth, int v, int d)
	{
		if (rt == null)
			return;
		if (depth < d - 1)
		{
			travel(rt.left, depth + 1, v, d);
			travel(rt.right, depth + 1, v, d);
			return;
		}
		if (depth == d - 1)
		{
			TreeNode tn = new TreeNode(v);
			tn.left = rt.left;
			rt.left = tn;
			tn = new TreeNode(v);
			tn.right = rt.right;
			rt.right = tn;
		}
	}

	public TreeNode addOneRow(TreeNode root, int v, int d)
	{
		if (d == 1)
		{
			TreeNode rt = new TreeNode(v);
			rt.left = root;
			return rt;
		}
		travel(root, 1, v, d);
		return root;
	}
}

//630. Course Schedule III
//Runtime: 79 ms, faster than 88.14% of Java online submissions for Course Schedule III.
//Memory Usage: 61.9 MB, less than 36.36% of Java online submissions for Course Schedule III.

class Solution630
{
	private static class MPair implements Comparable<MPair>
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
	private static class MPair implements Comparable<MPair>
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
	private static class MPair implements Comparable<MPair>
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
	public static void test621()
	{

		try
		{
			File inFile = new File("input" + File.separator + "input621.txt");
			BufferedReader bfr = new BufferedReader(new FileReader(inFile));

			File outFile = new File("output" + File.separator + "output621.txt");
			BufferedWriter bfw = new BufferedWriter(new FileWriter(outFile));

			String inLine;
			while ((inLine = bfr.readLine()) != null && inLine.length() > 0)
			{
				List<Character> l = new ArrayList<Character>();
				for (char c : inLine.toCharArray())
				{
					if (c >= 'A' && c <= 'Z')
						l.add(c);
				}
				char[] c = new char[l.size()];
				for (int i = 0; i < c.length; i++)
					c[i] = l.get(i);

				inLine = bfr.readLine();
				int n = Integer.parseInt(inLine);

				Solution621_2 s = new Solution621_2();
				int ans = s.leastInterval(c, n);
				System.out.println(ans);

				bfw.write("" + ans);
				bfw.newLine();
			}

			bfr.close();
			bfw.flush();
			bfw.close();
		} catch (IOException e)
		{
			System.out.println(e.toString());
		}
	}

	public static void main(String[] args)
	{
		test621();
	}

}
