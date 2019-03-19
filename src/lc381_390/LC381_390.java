package lc381_390;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//381. Insert Delete GetRandom O(1) - Duplicates allowed
//Runtime: 268 ms, faster than 5.03% of Java online submissions for Insert Delete GetRandom O(1) - Duplicates allowed.
//Memory Usage: 59.3 MB, less than 10.94% of Java online submissions for Insert Delete GetRandom O(1) - Duplicates allowed.
class RandomizedCollection
{
	List<Integer> nums;
	Random rd;

	/** Initialize your data structure here. */
	public RandomizedCollection()
	{
		nums = new ArrayList<Integer>();
		rd = new Random();
	}

	/**
	 * Inserts a value to the collection. Returns true if the collection did not
	 * already contain the specified element.
	 */
	public boolean insert(int val)
	{
		int idx = nums.indexOf(val);
		nums.add(val);
		return idx < 0;
	}

	/**
	 * Removes a value from the collection. Returns true if the collection contained
	 * the specified element.
	 */
	public boolean remove(int val)
	{
		int ind = nums.indexOf(val);
		if (ind < 0)
			return false;
		if (ind == nums.size() - 1)
			nums.remove(ind);
		else
		{
			nums.set(ind, nums.get(nums.size() - 1));
			nums.remove(nums.size() - 1);
		}
		return true;
	}

	/** Get a random element from the collection. */
	public int getRandom()
	{
		return nums.get(rd.nextInt(nums.size()));
	}
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection(); boolean param_1 =
 * obj.insert(val); boolean param_2 = obj.remove(val); int param_3 =
 * obj.getRandom();
 */

//382. Linked List Random Node
//Runtime: 58 ms, faster than 85.32% of Java online submissions for Linked List Random Node.
//Memory Usage: 40.8 MB, less than 49.45% of Java online submissions for Linked List Random Node.
class ListNode
{
	int val;
	ListNode next;

	ListNode(int x)
	{
		val = x;
	}
}
class Solution382
{
	ListNode h,t;
	Random rd;
	int len;
	/**
	 * @param head The linked list's head. Note that the head is guaranteed to be
	 *             not null, so it contains at least one node.
	 */
	public Solution382(ListNode head)
	{
		rd=new Random();
		t=head;
		h=head;
		len=1;
		while (t.next!=null)
		{
			t=t.next;
			len++;
		}
		t.next=head;
	}

	/** Returns a random node's value. */
	public int getRandom()
	{
		int m=rd.nextInt(len);
		for (int i=0;i<m;i++)
			t=t.next;
		return t.val;
	}
}

/**
 * Your Solution object will be instantiated and called as such: Solution obj =
 * new Solution(head); int param_1 = obj.getRandom();
 */


//384. Shuffle an Array
//Runtime: 127 ms, faster than 80.62% of Java online submissions for Shuffle an Array.
//Memory Usage: 64.5 MB, less than 46.21% of Java online submissions for Shuffle an Array.
class Solution384 {

	int[] o;
	Random rd=new Random();
	int[] stack;
    public Solution384(int[] nums) {
        o=nums;
        stack=new int[o.length];
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
    	return o;
        
    }
    int[] dfs(int sp)
    {
    	if (sp==o.length)
    	{
    		for (int i=0;i<o.length;i++)
    			stack[i]=o[stack[i]];
    		return stack;
    	}
    	int i=rd.nextInt(o.length-sp);
    	//swap: stack at index: o.length-sp-1,i
    	int t=stack[i];
    	stack[i]=stack[o.length-sp-1];
    	stack[o.length-sp-1]=t;
    	return dfs(sp+1);
    	
    }
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
    	for (int i=0;i<o.length;i++)
    		stack[i]=i;
        return dfs(0);
    }
} 

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */

//389. Find the Difference
//Runtime: 3 ms, faster than 98.83% of Java online submissions for Find the Difference.
//Memory Usage: 37.4 MB, less than 68.49% of Java online submissions for Find the Difference.
class Solution389
{
	public char findTheDifference(String s, String t)
	{
		int x = 0;
		for (int i = 0; i < t.length(); i++)
			x ^= t.charAt(i) - 'a';
		for (int i = 0; i < s.length(); i++)
			x ^= s.charAt(i) - 'a';
		return (char) (x + 'a');
	}
}

public class LC381_390
{

}
