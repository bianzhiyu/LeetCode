package lc351_360;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import bbst.BBST;

//352. Data Stream as Disjoint Intervals

//Runtime: 82 ms, faster than 99.42% of Java online submissions for Data Stream as Disjoint Intervals.
//Memory Usage: 49.7 MB, less than 77.78% of Java online submissions for Data Stream as Disjoint Intervals.

/**
 * Definition for an interval.
 */
class Interval implements Comparable<Interval>
{
	int start;
	int end;

	Interval()
	{
		start = 0;
		end = 0;
	}

	Interval(int s, int e)
	{
		start = s;
		end = e;
	}

	@Override
	public int compareTo(Interval o)
	{
		if (end < o.start)
			return -1;
		if (start > o.end)
			return 1;
		return 0;
	}

	@Override
	public String toString()
	{
		return "(" + start + ", " + end + ")";
	}
}

class SummaryRanges
{
	BBST352 rt;

	/** Initialize your data structure here. */
	public SummaryRanges()
	{
		rt = null;
	}

	public void addNum(int val)
	{
		if (rt == null)
			rt = new BBST352(new Interval(val, val));
		else
			rt = rt.insert(val);
	}

	public List<Interval> getIntervals()
	{
		List<Interval> ans = new ArrayList<Interval>();
		if (rt == null)
			return ans;
		rt.collectAll(ans);
		return ans;
	}
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges(); obj.addNum(val); List<Interval>
 * param_2 = obj.getIntervals();
 */

class BBST352 extends BBST<Interval>
{
	public BBST352(Interval x)
	{
		super(x);
	}

	public BBST352 insert(int x)
	{
		if (dataLink.end >= x && x >= dataLink.start)
			return this;
		if (dataLink.end + 1 == x)
		{
			dataLink.end++;
			if (right == null)
				return this;
			Interval rmin = right.getMinData();
			if (rmin.start > dataLink.end + 1)
				return this;
			setRight(right.removeMin());
			dataLink.end = rmin.end;
			updateHeight();
			return (BBST352) balance();
		}
		if (dataLink.end + 2 <= x)
		{
			if (right == null)
			{
				setRight(new BBST352(new Interval(x, x)));
				updateHeight();
				return this;
			}
			setRight(((BBST352) right).insert(x));
			updateHeight();
			return (BBST352) balance();
		}
		if (dataLink.start - 1 == x)
		{
			dataLink.start--;
			if (left == null)
				return this;
			Interval lmax = left.getMaxData();
			if (lmax.end < dataLink.start - 1)
				return this;
			setLeft(left.removeMax());
			dataLink.start = lmax.start;
			updateHeight();
			return (BBST352) balance();
		}
		if (dataLink.start - 1 > x)
		{
			if (left == null)
			{
				setLeft(new BBST352(new Interval(x, x)));
				updateHeight();
				return this;
			}
			setLeft(((BBST352) left).insert(x));
			updateHeight();
			return (BBST352) balance();
		}
		return this;
	}

	public void collectAll(List<Interval> l)
	{
		if (left != null)
			((BBST352) left).collectAll(l);
		l.add(dataLink);
		if (right != null)
			((BBST352) right).collectAll(l);
	}
}

//354. Russian Doll Envelopes
//Runtime: 2362 ms, faster than 5.02% of Java online submissions for Russian Doll Envelopes.
//Memory Usage: 340 MB, less than 5.77% of Java online submissions for Russian Doll Envelopes.
class Solution354
{
	public int maxEnvelopes(int[][] envelopes)
	{
		int ne = envelopes.length;
		List<List<Integer>> FL = new ArrayList<List<Integer>>();
		List<List<Integer>> SL = new ArrayList<List<Integer>>();
		for (int i = 0; i < ne; i++)
		{
			FL.add(new ArrayList<Integer>());
			SL.add(new ArrayList<Integer>());
		}
		int[] remf = new int[ne];
		for (int i = 0; i < ne; i++)
			for (int j = 0; j < ne; j++)
				if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1])
				{
					SL.get(j).add(i);
					FL.get(i).add(j);
					remf[i]++;
				}
		int[] q = new int[ne];
		int[] maxl = new int[ne];
		boolean[] used = new boolean[ne];
		int f = 0, r = 0;
		for (int i = 0; i < ne; i++)
			if (remf[i] == 0)
			{
				q[r++] = i;
				maxl[i] = 1;
				used[i] = true;
			}
		int maxA = 0;
		while (f < r)
		{
			if (maxl[q[f]] > maxA)
				maxA = maxl[q[f]];
			for (int i : SL.get(q[f]))
			{
				if (maxl[q[f]] + 1 > maxl[i])
					maxl[i] = maxl[q[f]] + 1;
				remf[i]--;
				if (remf[i] == 0 && !used[i])
				{
					used[i] = true;
					q[r++] = i;
				}
			}
			f++;
		}
		return maxA;
	}
}

//////////////////////355start
//355. Design Twitter
//Runtime: 76 ms, faster than 88.85% of Java online submissions for Design Twitter.
//Memory Usage: 48.7 MB, less than 58.44% of Java online submissions for Design Twitter.
class Twitter
{
	class CMP implements Comparator<Integer>
	{
		@Override
		public int compare(Integer o1, Integer o2)
		{
			return postIdToTime.get(o1) - postIdToTime.get(o2);
		}
	}

	private HashMap<Integer, List<Integer>> userIdToPostIdList;
	private HashMap<Integer, List<Integer>> userToFolloweeList;
	private HashMap<Integer, Integer> postIdToTime;
	private int time;
	private CMP cmp;

	/** Initialize your data structure here. */
	public Twitter()
	{
		userIdToPostIdList = new HashMap<Integer, List<Integer>>();
		userToFolloweeList = new HashMap<Integer, List<Integer>>();
		postIdToTime = new HashMap<Integer, Integer>();
		time = 0;
		cmp = new CMP();
	}

	/** Compose a new tweet. */
	public void postTweet(int userId, int tweetId)
	{
		if (!userIdToPostIdList.containsKey(userId))
			userIdToPostIdList.put(userId, new ArrayList<Integer>());
		userIdToPostIdList.get(userId).add(tweetId);
		postIdToTime.put(tweetId, time++);
	}

	/**
	 * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in
	 * the news feed must be posted by users who the user followed or by the user
	 * herself. Tweets must be ordered from most recent to least recent.
	 */

	private final static int maxFeedNum = 10;

	private void addFeed(PriorityQueue<Integer> queue, List<Integer> PostList)
	{
		if (PostList == null)
			return;
		for (int postId : PostList)
		{
			if (queue.contains(postId))
				continue;
			if (queue.size() < maxFeedNum)
			{
				queue.add(postId);
			} else
			{
				int oldestPostId = queue.peek();
				if (postIdToTime.get(oldestPostId) < postIdToTime.get(postId))
				{
					queue.remove();
					queue.add(postId);
				}
			}
		}
	}

	public List<Integer> getNewsFeed(int userId)
	{
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>(cmp);
		addFeed(queue, userIdToPostIdList.get(userId));
		if (!userToFolloweeList.containsKey(userId))
			userToFolloweeList.put(userId, new ArrayList<Integer>());
		for (int followeeId : userToFolloweeList.get(userId))
			addFeed(queue, userIdToPostIdList.get(followeeId));

		List<Integer> feedList = new ArrayList<Integer>();
		while (!queue.isEmpty())
			feedList.add(queue.remove());
		Collections.reverse(feedList);
		return feedList;
	}

	/**
	 * Follower follows a followee. If the operation is invalid, it should be a
	 * no-op.
	 */
	public void follow(int followerId, int followeeId)
	{
		if (!userToFolloweeList.containsKey(followerId))
			userToFolloweeList.put(followerId, new ArrayList<Integer>());
		if (!userToFolloweeList.get(followerId).contains(followeeId))
			userToFolloweeList.get(followerId).add(followeeId);
	}

	/**
	 * Follower unfollows a followee. If the operation is invalid, it should be a
	 * no-op.
	 */
	public void unfollow(int followerId, int followeeId)
	{
		if (userToFolloweeList.containsKey(followerId))
		{
			if (userToFolloweeList.get(followerId).contains(followeeId))
				userToFolloweeList.get(followerId).remove(userToFolloweeList.get(followerId).indexOf(followeeId));
		}
	}
}

/**
 * Your Twitter object will be instantiated and called as such: Twitter obj =
 * new Twitter(); obj.postTweet(userId,tweetId); List<Integer> param_2 =
 * obj.getNewsFeed(userId); obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
////////////////////355end

class Solution357
{
	public int countNumbersWithUniqueDigits(int n)
	{
		if (n == 0)
			return 1;
		int sum = 0;
		for (int i = 1; i <= n && i <= 10; i++)
		{
			int prod = 9;
			for (int j = 2; j <= i; j++)
				prod *= 10 - j + 1;
			sum += prod;
		}
		sum++;
		return sum;

	}
}

public class LC351_360
{
	public static void main(String args[])
	{
		//		["Twitter","postTweet","getNewsFeed","follow","postTweet","getNewsFeed","unfollow","getNewsFeed"]
		//				[[],[1,5],[1],[1,2],[2,6],[1],[1,2],[1]]
		Twitter a = new Twitter();
		a.postTweet(1, 5);
		System.out.println(a.getNewsFeed(1));
		a.follow(1, 5);
		a.postTweet(2, 6);
		System.out.println(a.getNewsFeed(1));
		a.unfollow(1, 2);
		System.out.println(a.getNewsFeed(1));

		PriorityQueue<Integer> b = new PriorityQueue<Integer>();
		b.add(1);
		System.out.println(b.contains(2));
		b.add(1);
		System.out.println(b);
		//		List<Integer> b=new ArrayList<Integer>();
		//		for (int i:b)
		//		{
		//			System.out.println(i);
		//		}
		//		b.remove(1);
	}

}
