package lc451_460;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import bbst.BBST;

//451. Sort Characters By Frequency
//Runtime: 17 ms, faster than 88.61% of Java online submissions for Sort Characters By Frequency.
//Memory Usage: 39 MB, less than 89.94% of Java online submissions for Sort Characters By Frequency.
class MPair implements Comparable<MPair> {
	char key;
	int ct;

	MPair(char _key, int _ct) {
		key = _key;
		ct = _ct;
	}

	@Override
	public int compareTo(MPair o) {
		return key - o.key;
	}

}

class Solution451 {
	public String frequencySort(String s) {
		if (s.length() == 0)
			return "";
		BBST<MPair> rt = new BBST<MPair>(new MPair(s.charAt(0), 1));
		for (int i = 1; i < s.length(); i++) {
			MPair p = new MPair(s.charAt(i), 1);
			if (rt.containData(p)) {
				p = rt.getData(p);
				p.ct++;
			} else
				rt = rt.insert(p);
		}
		List<MPair> l1 = new ArrayList<MPair>();
		while (rt != null) {
			MPair p = rt.getMinData();
			rt = rt.removeMin();
			l1.add(p);
		}
		Collections.sort(l1, new Comparator<MPair>() {
			@Override
			public int compare(MPair o1, MPair o2) {
				return o2.ct - o1.ct;
			}
		});
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < l1.size(); i++) {
			MPair p = l1.get(i);
			for (int j = 0; j < p.ct; j++)
				sb.append(p.key);
		}
		return sb.toString();
	}
}

// 452. Minimum Number of Arrows to Burst Balloons
//Time Limit Exceeded
//case:
//[[0,2100000000]]
class Solution452 
{
	public int findMinArrowShots(int[][] points) 
	{
		int len=points.length;
		boolean[] burst=new boolean[len];
		int ct=0;
		for (int i=0;i<len;i++)
			if (!burst[i])
			{
				ct++;
				int burstpos=0,maxburstnum=-1;
				for (int j=points[i][0];j<=points[i][1];j++)
				{
					int tct=0;
					for (int k=0;k<len;k++)
						if (!burst[k] && points[k][0]<=j && j<=points[k][1])
							tct++;
					if (tct>maxburstnum)
					{
						burstpos=j;
						maxburstnum=tct;
					}
				}
				for (int k=0;k<len;k++)
					if (!burst[k] && points[k][0]<=burstpos && burstpos<=points[k][1])
						burst[k]=true;
			}
		return ct;
	}
}


// Runtime: 22 ms, faster than 97.07% of Java online submissions for Minimum Number of Arrows to Burst Balloons.
// Memory Usage: 44 MB, less than 95.61% of Java online submissions for Minimum Number of Arrows to Burst Balloons.
//https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/discuss/248491/Java-Easy-sort-solution-beating-100
class Solution452_2
{
	public int findMinArrowShots(int[][] points) 
	{
		if(points==null||points.length==0) return 0;
		if(points.length==1) return 1;
		Arrays.sort(points, new Comparator<int[]>()
		{
			public int compare(int[] o1,int[] o2)
			{
				if (o1[1]<o2[1]) return -1;
				if (o1[1]>o2[1]) return 1;
				return 0;
			}
		});
		int pos=Integer.MIN_VALUE,ct=0;
		for (int i=0;i<points.length;i++)
			if (pos<points[i][0])
			{
				ct++;
				pos=points[i][1];
			}
		return ct;
	}
}

// 460. LFU Cache
// This question does not give a clear description.
// This annoys me.
// Especially for these two cases:
// ["LFUCache","put","put","get","put","get","get","put","get","get","get"]
// [[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
// ["LFUCache","put","put","get","get","get","put","put","get","get","get","get"]
// [[3],[2,2],[1,1],[2],[1],[2],[3,3],[4,4],[3],[2],[1],[4]]

// https://leetcode.com/problems/lfu-cache/discuss/212604/Easy-Java
// This guy gives a clear description of the question.
// And his code is clear and not redundant.

// ���⣺ʵ��һ��LFU��Ƶ��ʹ�õ�cache������������ɾ���Ƶ��ʹ�õ�key
// ˼·��key->count,
// count->key����ΪҪɾ������Ƶ��ʹ�õģ�����Ҫ��ÿ��keyʹ�õ�count������������֪�����ٵ�count��Ӧ��key
// ʹ������hashmap��һ����¼vals
// һ����¼count����һ��LinkedHashSet����¼��ͬcount��keys���Լ�ά��һ��ȫ����С��countMin��
//
// get ������
// a.��count�н���key�Ĵ�����1��
// b.Ӧ����list�н�ԭ��count��keyɾ������count+1����������µ�key
// c.����minCount
// set ������
// a.������ֱ�����ã�������һ��get����
// b.�����ռ��ˣ���countΪmin����keyɾ��
// c.put��������key����vals
// d.����minCount=1

// Attention: the question does not say that you should call get in put.

// Runtime: 97 ms, faster than 60.19% of Java online submissions for LFU Cache.
// Memory Usage: 64.3 MB, less than 44.21% of Java online submissions for LFU
// Cache.
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
		if (!vals.containsKey(key))
			return -1;
		int count = counts.get(key);
		counts.put(key, count + 1);
		list.get(count).remove(key);
		if (!list.containsKey(count + 1))
			list.put(count + 1, new LinkedHashSet<>());
		list.get(count + 1).add(key);
		if (count == minCount && list.get(count).size() == 0)
			minCount++; // ����minCount
		return vals.get(key);
	}

	public void put(int key, int value) {
		if (cap <= 0)
			return;
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
		minCount = 1; // ����minCount
		list.get(1).add(key);
	}
}

public class LC451_460 {
	public static void testLFUCache() {
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

	public static void main(String[] args) {
		testLFUCache();
	}

}
