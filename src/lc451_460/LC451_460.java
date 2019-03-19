package lc451_460;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import bbst.BBST;

//451. Sort Characters By Frequency
//Runtime: 17 ms, faster than 88.61% of Java online submissions for Sort Characters By Frequency.
//Memory Usage: 39 MB, less than 89.94% of Java online submissions for Sort Characters By Frequency.
class MPair implements Comparable<MPair>
{
	char key;
	int ct;

	MPair(char _key, int _ct)
	{
		key = _key;
		ct = _ct;
	}

	@Override
	public int compareTo(MPair o)
	{
		return key - o.key;
	}

}

class Solution451
{
	public String frequencySort(String s)
	{
		if (s.length() == 0)
			return "";
		BBST<MPair> rt = new BBST<MPair>(new MPair(s.charAt(0), 1));
		for (int i = 1; i < s.length(); i++)
		{
			MPair p = new MPair(s.charAt(i), 1);
			if (rt.containData(p))
			{
				p = rt.getData(p);
				p.ct++;
			} else
				rt = rt.insert(p);
		}
		List<MPair> l1 = new ArrayList<MPair>();
		while (rt != null)
		{
			MPair p = rt.getMinData();
			rt = rt.removeMin();
			l1.add(p);
		}
		Collections.sort(l1, new Comparator<MPair>()
		{
			@Override
			public int compare(MPair o1, MPair o2)
			{
				return o2.ct - o1.ct;
			}
		});
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < l1.size(); i++)
		{
			MPair p = l1.get(i);
			for (int j = 0; j < p.ct; j++)
				sb.append(p.key);
		}
		return sb.toString();
	}
}

//460. LFU Cache
//This question does not give a clear description.
//This annoys me.
//Especially for these two cases:
//["LFUCache","put","put","get","put","get","get","put","get","get","get"]
//[[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
//["LFUCache","put","put","get","get","get","put","put","get","get","get","get"]
//[[3],[2,2],[1,1],[2],[1],[2],[3,3],[4,4],[3],[2],[1],[4]]

//https://leetcode.com/problems/lfu-cache/discuss/212604/Easy-Java
//This guy gives a clear description of the question.
//And his code is clear and not redundant.

//题意：实现一个LFU最频繁使用的cache，超过容量，删除最不频繁使用的key
//思路：key->count, count->key，因为要删除最少频繁使用的，则需要将每个key使用的count存起来，并且知道最少的count对应的key
//使用两个hashmap，一个记录vals 一个记录count，和一个LinkedHashSet，记录相同count的keys，以及维护一个全局最小的countMin，
//
//get 操作：
//a.在count中将该key的次数加1；
//b.应该在list中将原来count的key删除，在count+1中添加这个新的key
//c.更新minCount
//set 操作：
//a.存在则直接重置，并调用一次get操作
//b.超过空间了，则将count为min处的key删除
//c.put操作，将key存入vals
//d.重置minCount=1

//Attention: the question does not say that you should call get in put.

//Runtime: 97 ms, faster than 60.19% of Java online submissions for LFU Cache.
//Memory Usage: 64.3 MB, less than 44.21% of Java online submissions for LFU Cache.
class LFUCache {
    Map<Integer, Integer> vals;
    Map<Integer, Integer> counts;
    Map<Integer, LinkedHashSet<Integer>> list;
    
    int minCount = 1, cap;

    public LFUCache(int capacity) {
        vals = new HashMap<>();
        counts = new HashMap<>();
        list = new HashMap<>();
        cap = capacity;
        list.put(1, new LinkedHashSet<>());
    }
    
    public int get(int key) {
        if (!vals.containsKey(key)) return -1;
        int count = counts.get(key);
        counts.put(key, count + 1);
        list.get(count).remove(key);
        if (!list.containsKey(count + 1)) list.put(count + 1, new LinkedHashSet<>());
        list.get(count + 1).add(key);
        if (count == minCount && list.get(count).size() == 0) minCount++;  //更新minCount
        return vals.get(key);
    }
    
    public void put(int key, int value) {
        if (cap <= 0) return;
        if (vals.containsKey(key)) {
            vals.put(key, value);
            get(key);
            return;
        }
        if (vals.size() >= cap) {
            int deleteKey = list.get(minCount).iterator().next();
            vals.remove(deleteKey);
            list.get(minCount).remove(deleteKey);
        }
        vals.put(key, value);
        counts.put(key, 1);
        minCount = 1;  //重置minCount
        list.get(1).add(key);
    }
}

public class LC451_460
{
	public static void testLFUCache()
	{
		LFUCache c = new LFUCache(3);
		c.put(2, 2);
		c.put(1, 1);
		c.get(2);
		c.get(1);
		c.get(2);
		c.put(3, 3);
		c.put(4, 4);
		c.get(3);
		c.get(2);
		c.get(1);
		c.get(4);
	}

	public static void main(String[] args)
	{
		testLFUCache();
	}

}
