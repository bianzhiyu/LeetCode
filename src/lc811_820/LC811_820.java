package lc811_820;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


//818. Race Car
//Runtime: 1010 ms, faster than 5.08% of Java online submissions for Race Car.
//Memory Usage: 316.4 MB, less than 11.87% of Java online submissions for Race Car.
class Solution818 
{
	HashMap<Integer,HashMap<Integer,Integer>> h;
	HashMap<Integer,HashSet<Integer>> s;
	Queue<Integer> q;

	final static boolean debug=true;

	public int racecar(int target) 
	{
		h=new HashMap<Integer,HashMap<Integer,Integer>>();
		s=new HashMap<Integer,HashSet<Integer>>();
		s.put(1,new HashSet<Integer>());
		s.get(1).add(target);
		h.put(1,new HashMap<Integer,Integer>());
		h.get(1).put(target,0);
		q=new LinkedList<Integer>();
		q.add(1);q.add(target);
		while(!q.isEmpty())
		{
			int nowv=q.remove(),nowt=q.remove(),nstep=h.get(nowv).get(nowt);
			if (debug) System.out.print(nowv+" "+nowt+" "+nstep+" : ");
			int tv=nowv*2,tt=nowt-nowv;
			if (tt==0) return h.get(nowv).get(nowt)+1;

			if (!s.containsKey(tv)) s.put(tv,new HashSet<Integer>());
			if ((nowt>0  || nowv<=-2*nowt) && !s.get(tv).contains(tt) )
			{
				s.get(tv).add(tt);
				q.add(tv);q.add(tt);
				if (debug) System.out.print(tv+" "+tt+" ; ");
				if (!h.containsKey(tv)) h.put(tv,new HashMap<Integer,Integer>());
				h.get(tv).put(tt,nstep+1);
			}

			tv=1;
			tt=-nowt;

			if (!s.containsKey(tv)) s.put(tv,new HashSet<Integer>());
			if (!s.get(tv).contains(tt) )
			{
				s.get(tv).add(tt);
				q.add(tv);q.add(tt);
				if (debug) System.out.print(tv+" "+tt+" ; ");
				if (!h.containsKey(tv)) h.put(tv,new HashMap<Integer,Integer>());
				h.get(tv).put(tt,nstep+1);
			}
			if (debug) System.out.println();
		}
		return -1;
	}
}


//This is a dp solution which I have not understanded.
//https://leetcode.com/problems/race-car/discuss/246138/java-DP-solution-heavily-commented.
//Runtime: 4 ms, faster than 92.00% of Java online submissions for Race Car.
//Memory Usage: 36.4 MB, less than 91.53% of Java online submissions for Race Car.
class Solution818_2
{
	public int racecar(int target) {
		if(target == 0)
			return 0;
		
		int[] dp = new int[target + 1] ; // min steps to get i
    	dp[0] = 0;
		
		for(int i = 1; i <= target ; i++) {
			// Perfect position is such only using AAAA,
			// which is 1, 3, 7, 15,... ( 2^N - 1)
			
 			int rightPosition = 0;  // location of perfect position on right side of i    
			int rightSteps = 0;  // steps to reach right perfect position 
			while(rightPosition < i) {
				rightSteps++;
				rightPosition = (1 << rightSteps) - 1; // 1, 3, 7, 15,..., 
			}
			// perfect right position for i  
			if(rightPosition == i) {
				dp[i] = rightSteps;
				continue;
			}
			
			// first assuming car reaches right position 
			// then reverse  (+1), facing left  
			// then go back to i;
			// for example, i = 5; rightPostion = 7, right steps = 3; 
			dp[i] = rightSteps + 1 + dp[rightPosition-i];  // dp[5] = 3 + 1 + 4 = 8
			

			int leftSteps = rightSteps - 1; // steps to get o perfect position on left side of i,  
			int leftPosition = (1 << leftSteps) - 1 ;  // location of perfect position of left side of i
			// for example, i = 5; leftPostion = 3, left steps = 2;
			
			
			// assuming car reaches left position with leftSteps, 
			// reverse (+1)
			// go back (j) steps to reversePosition(j could be 0), this is probably the most difficult part. 
			// reverse again (+1), facing right
			// then go to i
			// 
			for(int j = 0; j < leftSteps; j++) {
				int reversePosition = leftPosition - ((1 << j) - 1);
				dp[i] = Math.min(dp[i], leftSteps + 1 + j + 1 + dp[i-reversePosition]);
				// for i = 5; gets the minimum in j = 1 with  AARARAA (1-3-3-2-2-3-5). 
				// dp[5] = 2 + 1 + 1 + 1 + 2 = 7 
			}
		}
		return dp[target]; 
	}
}

public class LC811_820 
{
	public static void main(String[] args)
	{
		Solution818 s=new Solution818();
		System.out.println(s.racecar(6));
	}
}
