package bbst;

public class MHashMap<K, V>
{
	private BBST<HPair<K, V>> root;

	public static class HPair<T, R> implements Comparable<HPair<T, R>>
	{
		private int hash;
		private T key;
		private R value;

		@Override
		public int compareTo(HPair<T, R> o)
		{
			if (hash < o.hash)
				return -1;
			if (hash == o.hash)
				return 0;
			return 1;
		}

		public HPair(T k, R v)
		{
			hash = k.hashCode();
			key = k;
			value = v;
		}
		public T getKey()
		{
			return key;
		}
		public R getValue()
		{
			return value;
		}
	}

	public MHashMap()
	{
	}

	public void put(K key, V val)
	{
		HPair<K, V> p = new HPair<K, V>(key, val);
		if (root == null)
			root = new BBST<HPair<K, V>>(p);
		else if (root.containData(p))
			root.replaceData(p);
		else
			root = root.insert(p);
	}

	public V get(K key)
	{
		HPair<K, V> p = new HPair<K, V>(key, null);
		if (root==null) return null;
		if (root.containData(p))
			return root.getData(p).value;
		else
			return null;
	}

	public void remove(K key)
	{
		HPair<K, V> p = new HPair<K, V>(key, null);
		if (root == null || !root.containData(p))
			return;
		root = root.removeNodeByData(p);
	}
}
