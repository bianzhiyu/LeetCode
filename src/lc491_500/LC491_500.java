package lc491_500;


//495. Teemo Attacking
//Runtime: 4 ms, faster than 97.51% of Java online submissions for Teemo Attacking.
//Memory Usage: 40 MB, less than 91.67% of Java online submissions for Teemo Attacking.
class Solution495 
{
    public int findPoisonedDuration(int[] timeSeries, int duration) 
    {
        int tottimespan=0,lastpoisonedtime,laststarttime;
        if (timeSeries.length==0) return 0;
        lastpoisonedtime=timeSeries[0]+duration;
        laststarttime=timeSeries[0];
        for (int i=1;i<timeSeries.length;i++)
        {
        	if (timeSeries[i]<=lastpoisonedtime)
        	{
        		lastpoisonedtime=Math.max(lastpoisonedtime,
        				timeSeries[i]+duration);
        	}
        	else
        	{
        		tottimespan+=lastpoisonedtime-laststarttime;
        		laststarttime=timeSeries[i];
        		lastpoisonedtime=timeSeries[i]+duration;
        	}
        }
        tottimespan+=lastpoisonedtime-laststarttime;
        return tottimespan;
    }
}

public class LC491_500 {
	public static void main(String[] agrs)
	{
		
	}

}
