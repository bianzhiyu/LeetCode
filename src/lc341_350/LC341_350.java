package lc341_350;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import bbst.BBST;

//343. Integer Break
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Integer Break.
//Memory Usage: 34.4 MB, less than 68.15% of Java online submissions for Integer Break.
class Solution343
{
	public int integerBreak(int n)
	{
		if (n == 2)
			return 1;
		if (n == 3)
			return 2;
		if (n == 4)
			return 4;
		int prod = 1;
		while (n > 4)
		{
			prod *= 3;
			n -= 3;
		}
		return prod * n;
	}
}

//347. Top K Frequent Elements
//Runtime: 18 ms, faster than 60.95% of Java online submissions for Top K Frequent Elements.
//Memory Usage: 40.4 MB, less than 71.25% of Java online submissions for Top K Frequent Elements.
class MPair implements Comparable<MPair>
{
	int key, ct;

	MPair(int _key, int _ct)
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

class Solution347
{
	public List<Integer> topKFrequent(int[] nums, int k)
	{
		BBST<MPair> rt = new BBST<MPair>(new MPair(nums[0], 1));
		for (int i = 1; i < nums.length; i++)
		{
			MPair p = new MPair(nums[i], 1);
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
		List<Integer> ans = new ArrayList<Integer>();
		for (int i = 0; i < k; i++)
			ans.add(l1.get(i).key);
		return ans;
	}
}

//Use java.util.HashMap instead of BBST.
//The former solution is given by a class implemented by my self.
//Runtime: 14 ms, faster than 75.19% of Java online submissions for Top K Frequent Elements.
//Memory Usage: 40.3 MB, less than 71.75% of Java online submissions for Top K Frequent Elements.
class Solution347_2
{
	public List<Integer> topKFrequent(int[] nums, int k)
	{
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++)
			if (hm.containsKey(nums[i]))
				hm.put(nums[i], hm.get(nums[i]) + 1);
			else
				hm.put(nums[i], 1);
		List<MPair> l = new ArrayList<MPair>();
		for (int i : hm.keySet())
			l.add(new MPair(i, hm.get(i)));
		Collections.sort(l, new Comparator<MPair>()
		{
			@Override
			public int compare(MPair o1, MPair o2)
			{
				return o2.ct - o1.ct;
			}
		});
		List<Integer> ans = new ArrayList<Integer>();
		for (int i = 0; i < k; i++)
			ans.add(l.get(i).key);
		return ans;
	}
}



//349. Intersection of Two Arrays
//Runtime: 2 ms, faster than 98.71% of Java online submissions for Intersection of Two Arrays.
//Memory Usage: 37.5 MB, less than 33.16% of Java online submissions for Intersection of Two Arrays.
class Solution349
{
	public int[] intersection(int[] nums1, int[] nums2)
	{
		Set<Integer> s = new HashSet<Integer>();
		for (int i = 0; i < nums1.length; i++)
			s.add(nums1[i]);
		Set<Integer> s2 = new HashSet<Integer>();
		for (int i = 0; i < nums2.length; i++)
			if (s.contains(nums2[i]))
				s2.add(nums2[i]);
		int[] a = new int[s2.size()];
		int sp = 0;
		for (int i : s2)
			a[sp++] = i;
		return a;
	}
}

//350. Intersection of Two Arrays II
//Runtime: 2 ms, faster than 97.27% of Java online submissions for Intersection of Two Arrays II.
//Memory Usage: 35.2 MB, less than 66.88% of Java online submissions for Intersection of Two Arrays II.
class Solution350
{
	public int[] intersect(int[] nums1, int[] nums2)
	{
		Arrays.parallelSort(nums1);
		Arrays.parallelSort(nums2);
		List<Integer> l = new ArrayList<Integer>();
		int p1 = 0, p2 = 0;
		while (p1 < nums1.length && p2 < nums2.length)
		{
			while (p1 < nums1.length && nums1[p1] < nums2[p2])
			{
				p1++;
			}
			if (p1 == nums1.length)
				break;
			while (p2 < nums2.length && nums1[p1] > nums2[p2])
			{
				p2++;
			}
			if (p2 == nums2.length)
				break;
			if (nums1[p1] == nums2[p2])
			{
				l.add(nums1[p1]);
				p1++;
				p2++;
			}
		}
		int[] ans = new int[l.size()];
		int sp = 0;
		for (int i : l)
			ans[sp++] = i;
		return ans;
	}
}

public class LC341_350
{
	public static void main(String[] args)
	{

	}

}
