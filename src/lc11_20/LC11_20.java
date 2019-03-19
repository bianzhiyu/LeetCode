package lc11_20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//11. Container With Most Water
//Runtime: 203 ms, faster than 27.64% of Java online submissions for Container With Most Water.
//Memory Usage: 40.7 MB, less than 13.29% of Java online submissions for Container With Most Water.
class Solution11 
{
    public int maxArea(int[] height) 
    {
    	int n=height.length,max=0;
    	for (int i=0;i<n;i++)
    	{
    		
    		for (int j=i+1;j<n;j++)
    		{
    			if (Math.min(height[i], height[j])*(j-i)>max)
    				max=Math.min(height[i], height[j])*(j-i);
    		}
    	}
    	return max;
    }
}
//Runtime: 132 ms, faster than 28.38% of Java online submissions for Container With Most Water.
//Memory Usage: 40.6 MB, less than 19.16% of Java online submissions for Container With Most Water.
class Solution11_2
{
    public int maxArea(int[] height) 
    {
    	int n=height.length,max=0;
    	for (int i=0;i<n;i++)
    	{
    		
    		for (int j=n-1;j>=i+1;j--)
    		{
    			int t=Math.min(height[i], height[j])*(j-i);
    			if (t>max) max=t;
    			if (height[j]>=height[i]) break;
    			if ((j-i-1)*height[i]<=max) break;
    		}
    	}
    	return max;
    }
}
//Runtime: 64 ms, faster than 29.32% of Java online submissions for Container With Most Water.
//Memory Usage: 40.7 MB, less than 14.19% of Java online submissions for Container With Most Water.
class Solution11_3
{
    public int maxArea(int[] height) 
    {
    	int n=height.length,max=0;
    	for (int i=0;i<n;i++)
    	{
    		for (int j=n-1;j>=i+1;j--)
    		{
    			int t=Math.min(height[i], height[j])*(j-i);
    			if (t>max) max=t;
    			if (height[j]>=height[i]) break;
    			if ((j-i-1)*height[i]<=max) break;
    		}
    	}
    	return max;
    }
}

//https://leetcode.com/problems/container-with-most-water/discuss/240969/My-O(n)-Java-solution-beats-98
//Runtime: 3 ms, faster than 100.00% of Java online submissions for Container With Most Water.
//Memory Usage: 40.1 MB, less than 75.63% of Java online submissions for Container With Most Water.
class Solution11_4 
{
    public int maxArea(int[] height) 
    {
        int max = 0;
        int s = 0;
        int l = height.length - 1;
        while(s<l)
        {
            int waterSize = (l - s)*Math.min(height[l], height[s]);
            if(waterSize > max) max = waterSize;
            if(height[s] > height[l]) l--;
            else  s++;
        }
        return max;
    }
}

class ListNode {
	int val;
	ListNode next;
	public ListNode(int x) { val = x; }
}

//t12.Integer to Roman    
//t=36ms, o=28MS
//t:0.9491, o:0.4691
class Solution12 {
	public String intToRoman(int num) {
		int n=13;
		int[] d=new int[]{1,4,5,9,10,40,50,90,100,400,500,900,1000};
		String[] ch=new String[]{"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"};
		String ans="";
		for (int j=0;j<n;j++){
			for (int i=0;i<num/d[n-1-j];i++){
				ans=ans+ch[n-1-j];
			}
			num=num%d[n-1-j];
		}
		return ans;
	}

}
//t13.Roman to Integer    
//t=39ms, o=24MB
//t:0.8464, o:0.8900
class Solution13
{
	public int romanToInt(String s) {
		int i=0,ans=0;

		while (i<s.length())
		{
			if (i+1<s.length())
			{
				if (s.substring(i,i+2).compareTo("CM")==0){ans=ans+900;i=i+2;continue;}
				if (s.substring(i,i+2).compareTo("CD")==0){ans=ans+400;i=i+2;continue;}
				if (s.substring(i,i+2).compareTo("XC")==0){ans=ans+90;i=i+2;continue;}
				if (s.substring(i,i+2).compareTo("XL")==0){ans=ans+40;i=i+2;continue;}
				if (s.substring(i,i+2).compareTo("IX")==0){ans=ans+9;i=i+2;continue;}
				if (s.substring(i,i+2).compareTo("IV")==0){ans=ans+4;i=i+2;continue;}
			}
			if (s.charAt(i)=='M'){ans=ans+1000;i=i+1;continue;}
			if (s.charAt(i)=='D'){ans=ans+500;i=i+1;continue;}
			if (s.charAt(i)=='C'){ans=ans+100;i=i+1;continue;}
			if (s.charAt(i)=='L'){ans=ans+50;i=i+1;continue;}
			if (s.charAt(i)=='X'){ans=ans+10;i=i+1;continue;}
			if (s.charAt(i)=='V'){ans=ans+5;i=i+1;continue;}
			if (s.charAt(i)=='I'){ans=ans+1;i=i+1;continue;}
		}
		return ans;
	}
}
//t14.Longest Common Prefix    
//t=5ms, o=27MB
//t:0.8587, o:0.3600
class Solution14
{
	public String longestCommonPrefix(String[] strs) {
		if (strs.length==0) return "";

		int i=0;
		for (i=0;i<strs[0].length();i++)
		{
			int flag=1;
			for (int j=1;j<strs.length;j++)
			{
				if ((strs[j].length()<=i)||(strs[j].charAt(i)!=strs[0].charAt(i)))
				{
					flag=0;
					break;
				}
			}
			if (flag==0)
			{
				break;
			}
		}
		return strs[0].substring(0, i);
	}
}
//t15.3Sum     
//t=3105ms, o=38.6MB
//t:0.0101, o:0.2070
class Solution15 {
	public List<List<Integer>> threeSum(int[] nums) {


		Arrays.sort(nums);
		List<List<Integer>> ans=new ArrayList<List<Integer>>();
		List<Integer> n2=new ArrayList<Integer>();
		List<Integer> m2=new ArrayList<Integer>();


		int i=0,l3=0;
		while (i<nums.length)
		{
			l3=l3+1;
			int j=i;
			n2.add(nums[i]);
			m2.add(0);
			while ((j<nums.length)&&(nums[j]==nums[i])){j=j+1;}
			m2.set(l3-1,j-i);
			i=j;
		}
		for (i=0;i<l3;i++)
		{
			if ((m2.get(i)>=3)&&(3*n2.get(i)==0))
			{
				List<Integer> t2=new ArrayList<Integer>();
				t2.add(n2.get(i));t2.add(n2.get(i));t2.add(n2.get(i));
				ans.add(t2);
			}
			// if ((m2.get(i)>=2)&&(n2.indexOf(-2*n2.get(i))>-1)&&(n2.indexOf(-2*n2.get(i))!=i))
			if (m2.get(i)>=2)
			{
				boolean flag=false;
				for (int j=0;j<l3;j++)
				{
					if ((-2*n2.get(i)==n2.get(j))&&(j!=i))
					{
						flag=true;
						break;
					}
				}
				if (flag)
				{
					List<Integer> t2=new ArrayList<Integer>();
					t2.add(n2.get(i));t2.add(n2.get(i));t2.add(-2*n2.get(i));
					ans.add(t2);
				}
			}
			if (m2.get(i)>=1)
			{
				for (int j=i+1;j<l3;j++)
				{
					boolean flag=false;
					for (int k=j+1;k<l3;k++)
					{
						if (n2.get(k)==-n2.get(i)-n2.get(j))
						{
							flag=true;
							break;
						}
					}
					if (flag)
					{
						List<Integer> t2=new ArrayList<Integer>(3);
						t2.add(n2.get(i));t2.add(n2.get(j));t2.add(-n2.get(i)-n2.get(j));
						ans.add(t2);  
					}
				}
			}
		}
		return ans;
	}
}
//t15_2
//t=75, o=37MB
//t:0.3848, o:0.4886
//it seems that N^2 is the best
class Solution15_2 {
	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> ans=new ArrayList<List<Integer>>();
		List<Integer> n2=new ArrayList<Integer>();
		List<Integer> m2=new ArrayList<Integer>();

		int i=0,l3=0;
		while (i<nums.length)
		{
			l3=l3+1;
			int j=i;
			n2.add(nums[i]);
			m2.add(0);
			while ((j<nums.length)&&(nums[j]==nums[i])){j=j+1;}
			m2.set(l3-1,j-i);
			i=j;
		}
		for (i=0;i<l3;i++)
		{
			if ((m2.get(i)>=3)&&(3*n2.get(i)==0))
			{
				List<Integer> t2=new ArrayList<Integer>();
				t2.add(n2.get(i));t2.add(n2.get(i));t2.add(n2.get(i));
				ans.add(t2);
			}
			// if ((m2.get(i)>=2)&&(n2.indexOf(-2*n2.get(i))>-1)&&(n2.indexOf(-2*n2.get(i))!=i))
			if (m2.get(i)>=2)
			{
				for (int j=0;j<l3;j++)
				{
					if ((-2*n2.get(i)==n2.get(j))&&(j!=i))
					{
						List<Integer> t2=new ArrayList<Integer>();
						t2.add(n2.get(i));t2.add(n2.get(i));t2.add(-2*n2.get(i));
						ans.add(t2);
						break;
					}
				}
			}
			if (m2.get(i)>=1)
			{
				int j=i+1,k=l3-1;
				while (j<k)
				{
					int d=n2.get(j)+n2.get(k)+n2.get(i);
					if (d==0)
					{
						List<Integer> t2=new ArrayList<Integer>(3);
						t2.add(n2.get(i));t2.add(n2.get(j));t2.add(-n2.get(i)-n2.get(j));
						ans.add(t2); 
						j++;
					}
					else if (d>0)
					{
						k--;

					}
					else {
						j++;
					}
				}

			}
		}
		return ans;
	}
}
//t16.3Sum Closest   
//t=22ms, o=25MB
//t:0.1672, o:0.5899
class Solution16 {
	public int threeSumClosest(int[] nums, int target) {


		Arrays.sort(nums);   
		int ans=0,dist=Integer.MAX_VALUE;
		List<Integer> n2=new ArrayList<Integer>();
		List<Integer> m2=new ArrayList<Integer>();

		int i=0,l3=0;
		while (i<nums.length)
		{
			l3=l3+1;
			int j=i;
			n2.add(nums[i]);
			m2.add(0);
			while ((j<nums.length)&&(nums[j]==nums[i])){j=j+1;}
			m2.set(l3-1,j-i);
			i=j;
		}

		System.out.println(n2.toString());
		System.out.println(m2.toString());
		System.out.println(l3);
		for (i=0;(i<l3)&&(dist>0);i++)
		{
			int numt=n2.get(i);
			if ((m2.get(i)>=3)&&
					(Math.abs(3*numt-target)<dist))
			{
				dist=Math.abs(3*numt-target);
				ans=3*n2.get(i);
			}
			if (m2.get(i)>=2)
			{
				for (int j=0;j<l3;j++)
				{
					if ((j!=i)&&(Math.abs(2*numt+n2.get(j)-target)<dist))
					{
						dist=Math.abs(2*numt+n2.get(j)-target);
						ans=2*numt+n2.get(j);    						  
					}
				}
			}
			int lp=i+1,rp=l3-1;
			while ((lp<rp)&&(dist>0))
			{
				if (Math.abs(numt+n2.get(lp)+n2.get(rp)-target)<dist)
				{
					dist=Math.abs(numt+n2.get(lp)+n2.get(rp)-target);
					ans=numt+n2.get(lp)+n2.get(rp);
				}
				if (numt+n2.get(lp)+n2.get(rp)<target)
				{
					lp++;
				}
				if (numt+n2.get(lp)+n2.get(rp)>target)
				{
					rp--;
				}
			}
		}

		return ans;
	}
}
//t17.Letter Combinations of a Phone Number   
//t=1ms, o=26MB
//t:1, o:0.4152
class Solution17 {
	public List<String> letterCombinations(String digits) {
		List<String> ans=new ArrayList<String>();
		if (digits.length()==0)	return ans;
		int l=digits.length();
		int ds[]=new int[l];
		for (int i=0;i<l;i++)
		{
			ds[i]=digits.charAt(i);
			ds[i]-=(int)'2';
		}
		int[][] ml=new int[][] {
			{0,1,2},
			{3,4,5},
			{6,7,8},
			{9,10,11},
			{12,13,14},
			{15,16,17,18},
			{19,20,21},
			{22,23,24,25}
		};
		char[] ca=new char[l];
		int[] stat=new int[l];
		for (int i=0;i<l;i++) {stat[i]=0;}
		for (;;)
		{
			for (int i=0;i<l;i++)
			{
				ca[i]=(char)((int)'a'+ml[ds[i]][stat[i]]);
			}
			ans.add(new String(ca));
			int i=l-1;
			stat[l-1]++;
			while ((i>=0)&&(stat[i]==ml[ds[i]].length))
			{
				stat[i]=0;
				i--;
				if (i>=0)	stat[i]++;
				else break;
			}
			if (i==-1)	break;
		}
		return ans;
	}
}
//t18.4Sum    
//t=48ms, o=38MB
//t:0.5141, o:0.0388
class Solution18 {
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> ans=new ArrayList<List<Integer>>();
		Arrays.sort(nums);

		int [] n=new int[nums.length];
		int [] m=new int[nums.length];
		int l=0,i=0;

		while (i<nums.length)
		{
			n[l]=nums[i];
			int j=i;
			while ((j<nums.length)&&(nums[j]==nums[i]))
			{
				j++;
			}
			m[l]=j-i;
			i=j;
			l++;
		}
		for (i=0;i<l;i++)
		{
			if ((m[i]==4)&&(n[i]*4==target))
			{
				List<Integer> tmp=new ArrayList<Integer>();
				tmp.add(n[i]);tmp.add(n[i]);tmp.add(n[i]);tmp.add(n[i]);
				ans.add(tmp);
			}
			if (m[i]>=3)
			{
				for (int j=0;j<l;j++)
				{
					if ((n[i]*3+n[j]==target)&&(i!=j))
					{
						List<Integer> tmp=new ArrayList<Integer>();
						tmp.add(n[i]);tmp.add(n[i]);
						tmp.add(n[i]);tmp.add(n[j]);
						ans.add(tmp);
					}
				}
			}
			if (m[i]>=2)
			{
				for (int j=i+1;j<l;j++)
				{
					if ((m[j]>=2)&&(n[i]*2+n[j]*2==target))
					{
						List<Integer> tmp=new ArrayList<Integer>();
						tmp.add(n[i]);tmp.add(n[i]);
						tmp.add(n[j]);tmp.add(n[j]);
						ans.add(tmp);
					}
				}
				int lp=0,rp=l-1;
				while (lp<rp)
				{
					if (lp==i)
					{
						lp++;
						continue;
					}
					if (rp==i)
					{
						rp--;
						continue;
					}
					if (n[i]*2+n[lp]+n[rp]==target)
					{
						List<Integer> tmp=new ArrayList<Integer>();
						tmp.add(n[i]);tmp.add(n[i]);
						tmp.add(n[lp]);tmp.add(n[rp]);
						ans.add(tmp);
						lp++;
						continue;
					}
					if (n[i]*2+n[lp]+n[rp]<target)
					{
						lp++;
						continue;
					}
					if (n[i]*2+n[lp]+n[rp]>target)
					{
						rp--;
						continue;
					}
				}
			}
			for (int j=i+1;j<l;j++)
			{
				int lp=j+1,rp=l-1;
				while (lp<rp)
				{
					if (n[i]+n[j]+n[lp]+n[rp]==target)
					{
						List<Integer> tmp=new ArrayList<Integer>();
						tmp.add(n[i]);tmp.add(n[j]);
						tmp.add(n[lp]);tmp.add(n[rp]);
						ans.add(tmp);
						lp++;
						continue;
					}
					if (n[i]+n[j]+n[lp]+n[rp]<target)
					{
						lp++;
						continue;
					}
					if (n[i]+n[j]+n[lp]+n[rp]>target)
					{
						rp--;
						continue;
					}
				}
			}
		}
		return ans;        
	}
}
//t19.Remove Nth Node From End of List    
//t=6ms, o=27MB
//t:0.9870, o:0.3317
class Solution19
{
	public ListNode removeNthFromEnd(ListNode head, int n) {
		int len=1;
		ListNode ln=head;
		while (ln.next!=null)
		{
			ln=ln.next;
			len=len+1;
		}

		int stop=len-n;
		if (stop>0)
		{
			ln=head;
			for (int i=0;i<stop-1;i++){ln=ln.next;}
			ln.next=ln.next.next;
			return head;
		}
		else
		{
			ln=head.next;
			return ln;
		}
	}
}
//t20.Valid Parentheses    
//t=3ms, o=21MB,
//t:1, o:0.8815
class Solution20
{
	public boolean isValid(String s) {
		boolean flag=true;
		int[] ds=new int[s.length()];

		for (int i=0;i<s.length();i++)
		{
			char c=s.charAt(i);
			if (c=='(')  ds[i]=1;
			else if (c=='[') ds[i]=2;
			else if (c=='{') ds[i]=3;
			else if (c==')') ds[i]=-1;
			else if (c==']') ds[i]=-2;
			else if (c=='}') ds[i]=-3;
		}
		int[] stack=new int [s.length()];
		int sp=0;

		for (int i=0;i<s.length();i++)
		{
			if (ds[i]>0)
			{
				/*
				if ((sp==0)||(ds[i]<=stack[sp-1]))
				{
					stack[sp++]=ds[i];
				}
				else {
					flag=false;
					break;
				}
				 */
				stack[sp++]=ds[i];
			}
			else 
			{
				if ((sp>0)&&(ds[i]+stack[sp-1]==0))
				{
					sp--;
				}
				else {
					flag=false;
					break;
				}
			}
		}
		if (sp>0) return false;
		return flag;
	}
}
public class LC11_20 {
	public static void main(String args[])
	{

		System.out.println(
				new Solution11().maxArea(
						new int[] {1,8,6,2,5,4,8,3,7}
						)
				);
	}
}
