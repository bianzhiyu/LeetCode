package lc911_920;

import java.util.HashMap;
import java.util.Map;

import bbst.BBST;

//911. Online Election
//Runtime: 201 ms, faster than 32.12% of Java online submissions for Online Election.
//Memory Usage: 71.7 MB, less than 76.04% of Java online submissions for Online Election.

//Actually, if my BBST<T> can support 
// new BBST<T>(new Comparator<T>(){...})
//The code will be much simpler.
class TopVotedCandidate
{
	private int[] top, times;

	private static class Data implements Comparable<Data>
	{
		private int person, vote, lastTime;

		public Data(int p, int v, int t)
		{
			person = p;
			vote = v;
			lastTime = t;
		}

		public int compareTo(Data o)
		{
			if (vote != o.vote)
				return o.vote - vote;
			return o.lastTime - lastTime;
		}

		public String toString()
		{
			return "(" + person + ", " + vote + ", " + lastTime + ")";
		}
	}

	private static class PWD implements Comparable<PWD>
	{
		private int person;
		private Data data;

		public PWD(int p, Data d)
		{
			person = p;
			data = d;
		}

		public int compareTo(PWD o)
		{
			return person - o.person;
		}

		public String toString()
		{
			return "(" + person + ", " + data + ")";
		}
	}

	public TopVotedCandidate(int[] persons, int[] times)
	{
		this.times = times;
		int len = persons.length;
		top = new int[len];
		BBST<Data> data;
		BBST<PWD> pwd;
		top[0] = persons[0];
		PWD t;
		Data d;
		pwd = new BBST<PWD>(t = new PWD(persons[0], new Data(persons[0], 1, times[0])));
		data = new BBST<Data>(t.data);
		for (int i = 1; i < len; i++)
		{
			t = new PWD(persons[i], null);
			if (pwd.containData(t))
			{
				t = pwd.getData(t);
				d = data.getData(t.data);
				data = data.removeNodeByData(d);
				d.vote++;
				d.lastTime = times[i];
				if (data == null)
					data = new BBST<Data>(d);
				else
					data = data.insert(d);
			} else
			{
				t.data = new Data(persons[i], 1, times[i]);
				data = data.insert(t.data);
				pwd = pwd.insert(t);
			}
			top[i] = data.getMinData().person;
		}
	}

	public int q(int t)
	{
		if (t >= times[times.length - 1])
			return top[top.length - 1];
		int l = 0, r = top.length - 1;
		while (l < r)
		{
			if (r == l + 1)
			{
				if (t >= times[r])
					l = r;
				else
					r = l;
			} else
			{
				int m = (l + r) / 2;
				if (t >= times[m])
					l = m;
				else
					r = m - 1;
			}
		}
		return top[l];
	}
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times); int param_1 =
 * obj.q(t);
 */

//https://leetcode.com/problems/online-election/solution/
//Runtime: 149 ms, faster than 88.34% of Java online submissions for Online Election.
//Memory Usage: 71.5 MB, less than 80.21% of Java online submissions for Online Election.

//Note that: the leader change event can be detected easily.
//We don't need to record the total order of all persons.
//We just need to record the leader.
//This saves us the us of a BBST.
class TopVotedCandidate_2
{
	private int[] top, times;

	public TopVotedCandidate_2(int[] persons, int[] times)
	{
		this.times = times;
		top = new int[times.length];
		Map<Integer, Integer> count = new HashMap<Integer, Integer>();
		int leader = -1; // current leader
		int m = 0; // current number of votes for leader
		for (int i = 0; i < persons.length; ++i)
		{
			int p = persons[i];
			int c = count.getOrDefault(p, 0) + 1;
			count.put(p, c);
			if (c >= m)
			{
				leader = p;// lead change
				m = c;
			}
			top[i] = leader;
		}
	}

	public int q(int t)
	{
		if (t >= times[times.length - 1])
			return top[top.length - 1];
		int l = 0, r = top.length - 1;
		while (l < r)
		{
			if (r == l + 1)
			{
				if (t >= times[r])
					l = r;
				else
					r = l;
			} else
			{
				int m = (l + r) / 2;
				if (t >= times[m])
					l = m;
				else
					r = m - 1;
			}
		}
		return top[l];
	}
}

public class LC911_920
{
	public static void test911()
	{
		int[] p = new int[]
		{ 0, 1, 1, 0, 0, 1, 0 };
		int[] t = new int[]
		{ 0, 5, 10, 15, 20, 25, 30 };
		TopVotedCandidate s = new TopVotedCandidate(p, t);
		int[] opt = new int[]
		{ 3, 12, 25, 15, 24, 8 };
		for (int tm : opt)
			System.out.println(s.q(tm));
	}

	public static void main(String[] args)
	{
		test911();
	}
}
