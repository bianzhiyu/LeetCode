package lc851_860;


//852. Peak Index in a Mountain Array
//Runtime: 2 ms, faster than 75.06% of Java online submissions for Peak Index in a Mountain Array.
//Memory Usage: 40.3 MB, less than 87.32% of Java online submissions for Peak Index in a Mountain Array.
class Solution852 
{
    public int peakIndexInMountainArray(int[] A) 
    {
        for (int i=1;i<=A.length-2;i++)
            if (A[i]>A[i-1] && A[i]>A[i+1])
                return i;
        return 0;
    }
}

public class LC851_860 {

}
