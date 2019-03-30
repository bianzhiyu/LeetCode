package bbst;

public class MHashSet<T>
{
	public static class Pair<P> implements Comparable<Pair<P>>
	{
		private int hash;
		private P data;

		@Override
		public int compareTo(Pair<P> o)
		{
			if (hash < o.hash)
				return -1;
			if (hash == o.hash)
				return 0;
			return 1;
		}

		public Pair(P d)
		{
			data = d;
			hash = d.hashCode();
		}

		public P getData()
		{
			return data;
		}
	}

	private BBST<Pair<T>> root = null;

	public MHashSet()
	{
	}

	public void add(T x)
	{
		Pair<T> p = new Pair<T>(x);
		if (root == null)
			root = new BBST<Pair<T>>(p);
		else if (!root.containData(p))
		{
			root = root.insert(p);
		}
	}

	public void remove(T x)
	{
		if (root == null)
			return;
		Pair<T> p = new Pair<T>(x);
		if (root.containData(p))
			root = root.removeNodeByData(p);
	}

	public boolean contains(T x)
	{
		if (root == null)
			return false;
		Pair<T> p = new Pair<T>(x);
		return root.containData(p);
	}

}
