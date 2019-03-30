package bbst;

//705. Design HashSet
public class MHashSet<T>
{
	public static class HPair<P> implements Comparable<HPair<P>>
	{
		private int hash;
		private P data;

		@Override
		public int compareTo(HPair<P> o)
		{
			if (hash < o.hash)
				return -1;
			if (hash == o.hash)
				return 0;
			return 1;
		}

		public HPair(P d)
		{
			data = d;
			hash = d.hashCode();
		}

		public P getData()
		{
			return data;
		}
	}

	private BBST<HPair<T>> root = null;

	public MHashSet()
	{
	}

	public void add(T x)
	{
		HPair<T> p = new HPair<T>(x);
		if (root == null)
			root = new BBST<HPair<T>>(p);
		else if (!root.containData(p))
		{
			root = root.insert(p);
		}
	}

	public void remove(T x)
	{
		if (root == null)
			return;
		HPair<T> p = new HPair<T>(x);
		if (root.containData(p))
			root = root.removeNodeByData(p);
	}

	public boolean contains(T x)
	{
		if (root == null)
			return false;
		HPair<T> p = new HPair<T>(x);
		return root.containData(p);
	}

}
