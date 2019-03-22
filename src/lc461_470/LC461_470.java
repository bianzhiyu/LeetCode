package lc461_470;

import java.util.Arrays;
import java.util.HashMap;

//464. Can I Win
//https://leetcode.com/problems/can-i-win/discuss/216992/Very-clean-Java-solution
//Runtime: 73 ms, faster than 71.12% of Java online submissions for Can I Win.
//Memory Usage: 46.7 MB, less than 79.22% of Java online submissions for Can I Win.
class Solution464
{
	private HashMap<Integer,Boolean> hm=new HashMap<Integer,Boolean>();
	private boolean[] used;
	public boolean canIWin(int maxChoosableInteger, int desiredTotal)
	{
		if (maxChoosableInteger>=desiredTotal) 
			return true;
		if ((1+maxChoosableInteger)*maxChoosableInteger/2<desiredTotal) 
			return false;
		used=new boolean[maxChoosableInteger];
		Arrays.fill(used,false);
		return dfs(desiredTotal);
	}
	private boolean dfs(int target)
	{
		if (target<=0) return false;
		int state=getState();
		if (hm.containsKey(state)) 
			return hm.get(state);
		boolean canWin=false;
		for (int i=0;i<used.length;i++)
			if (!used[i])
			{
				used[i]=true;
				canWin=!dfs(target-1-i);
				used[i]=false;
				if (canWin) break;
			}
		hm.put(state,canWin);
		return canWin;
	}
	private int getState()
	{
		int mul=1,res=0;
		for (int i=0;i<used.length;i++)
		{
			if (used[i]) res|=mul;
			mul<<=1;
		}
		return res;
	}
}

public class LC461_470
{
	public static void main(String[] args)
	{

	}
}
