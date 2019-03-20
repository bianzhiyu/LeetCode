package lc141_150;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import bbst.BBST;

class Point {
	int x;
	int y;
	Point() { x = 0; y = 0; }
	Point(int a, int b) { x = a; y = b; }
}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}


//141. Linked List Cycle
//Runtime: 51 ms, faster than 5.63% of Java online submissions for Linked List Cycle.
//Memory Usage: 39.3 MB, less than 5.15% of Java online submissions for Linked List Cycle.
class Solution141 {
	public boolean hasCycle(ListNode head) {
		List<ListNode>a=new ArrayList<ListNode>();
		ListNode p=head;
		while (p!=null)
		{
			if (a.contains(p)) return true;
			a.add(p);
			p=p.next;
		}
		return false;
	}
}

//Interesting solution.
//https://leetcode.com/problems/linked-list-cycle/discuss/238441/Java-Solution-0ms-beats-100-in-speed-and-100-in-memory-usage
class Solution141_2{
	public boolean hasCycle(ListNode head) {
		ListNode n1 = head;
		ListNode n2 = head;
		while(n1 != null && n2 != null){
			n1 = n1.next;
			if(n1 == null) return false;
			n1 = n1.next;
			n2 = n2.next;
			if(n1 == n2){
				return true;
			}
		}
		return false;
	}
}
//HashSet can check faster than ArrayList (10% time consuming).
//Runtime: 5 ms, faster than 18.69% of Java online submissions for Linked List Cycle.
//Memory Usage: 38.6 MB, less than 38.43% of Java online submissions for Linked List Cycle.
class Solution141_3{
	public boolean hasCycle(ListNode head) {
		Set<ListNode> s=new HashSet<ListNode>();
		ListNode p=head;
		while (p!=null)
		{
			if (s.contains(p)) return true;
			s.add(p);
			p=p.next;
		}
		return false;
	}
}
//142. Linked List Cycle II
//Runtime: 6 ms, faster than 20.35% of Java online submissions for Linked List Cycle II.
//Memory Usage: 34.6 MB, less than 61.00% of Java online submissions for Linked List Cycle II.
class Solution142 
{
    public ListNode detectCycle(ListNode head) 
    {
        ListNode p=head;
        boolean found=false;
        Set<ListNode> s=new HashSet<ListNode>();
        while (p!=null)
        {
        	if (s.contains(p))
        	{
        		found=true;
        		break;
        	}
        	else
        	{
        		s.add(p);
        		p=p.next;
        	}
        }
        if (!found) return null;
        if (p.next==head) return head;
        return p;
    }
}
//143. Reorder List
class Solution143 
{
	public void reorderList(ListNode head) 
	{
		if(head == null || head.next == null) return;

		ListNode next = head.next;
		ListNode prev = head;
		ListNode end = head;


		while(end.next != null){ //calculate End node
			end = end.next;
		}
		ListNode prevEnd = null;

		while(next != null && next.next != null){
			prevEnd = getPreNode(head, end); // last but one node. this will be end once end node is moved 
			prev.next = end;
			end.next = next;
			next = next.next;
			prevEnd.next = null; // our purpose is done here. 

			//we are doing to repoint the pointers
			prev = end.next;
			next = prev.next;
			end = prevEnd;
		}


	}

	//method to find previous node
	public ListNode getPreNode(ListNode head, ListNode end){
		ListNode prevEnd = head;
		while(prevEnd.next != end){
			prevEnd = prevEnd.next;
		}
		return prevEnd;
	}
}
//Runtime: 1 ms, faster than 100.00% of Java online submissions for Reorder List.
//Memory Usage: 39.9 MB, less than 51.67% of Java online submissions for Reorder List.
class Solution143_2 
{
    public void reorderList(ListNode head) 
    {
        if (head==null) return;
        int n=0;
        ListNode p=head;
        while (p!=null)
        {
        	n++;
        	p=p.next;
        }
        ListNode[] a=new ListNode[n];
        p=head;
        for (int i=0;i<n;i++)
        {
        	a[i]=p;
        	p=p.next;
        }
        p=a[0];
        head=p;
        int s=1,l=1,r=n-1;
        while (l<=r)
        {
        	if (s==1)
        	{
        		p.next=a[r];
        		p=p.next;
        		r--;
        	}
        	else
        	{
        		p.next=a[l];
        		p=p.next;
        		l++;
        	}
        	s=1-s;
        }
        p.next=null;
        
    }
}
//144. Binary Tree Preorder Traversal
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Preorder Traversal.
//Memory Usage: 36 MB, less than 100.00% of Java online submissions for Binary Tree Preorder Traversal.
class Solution144 {
	List<Integer> ans=new ArrayList<Integer>();
	void Trav(TreeNode r)
	{
		if (r==null) return;
		ans.add(r.val);
		Trav(r.left);
		Trav(r.right);
	}
	public List<Integer> preorderTraversal(TreeNode root) {
		Trav(root);
		return ans;
	}
}

//145. Binary Tree Postorder Traversal
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Postorder Traversal.
//Memory Usage: 36.1 MB, less than 100.00% of Java online submissions for Binary Tree Postorder Traversal.
class Solution145 {
	List<Integer> ans=new ArrayList<Integer>();
	void Trav(TreeNode r)
	{
		if (r==null) return;
		Trav(r.left);
		Trav(r.right);
		ans.add(r.val);
	}
	public List<Integer> postorderTraversal(TreeNode root) {
		Trav(root);
		return ans;
	}
}
//Follow up: Recursive solution is trivial, 
//could you do it iteratively?
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Postorder Traversal.
//Memory Usage: 37.1 MB, less than 100.00% of Java online submissions for Binary Tree Postorder Traversal.
class Solution145_2 {
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer>a=new ArrayList<Integer>();
		if (root==null) return a;
		int maxLen=1000;
		TreeNode[] stack=new TreeNode[maxLen];
		boolean[] LC=new boolean[maxLen],RC=new boolean[maxLen];
		int sp=0;
		stack[sp++]=root;
		while (sp>0)
		{
			TreeNode nt=stack[sp-1];
			if (nt.left==null && nt.right==null)
			{
				a.add(nt.val);
				sp--;
			}
			else 
			{
				if (!LC[sp-1])
				{
					LC[sp-1]=true;
					if (nt.left!=null)
					{
						sp++;
						stack[sp-1]=nt.left;
						LC[sp-1]=false;
						RC[sp-1]=false;
						continue;
					}
				}
				if (!RC[sp-1])
				{
					RC[sp-1]=true;
					if (nt.right!=null)
					{
						sp++;
						stack[sp-1]=nt.right;
						LC[sp-1]=false;
						RC[sp-1]=false;
						continue;
					}
				}
				a.add(nt.val);
				sp--;
			}

		}
		return a;

	}
}
//Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Postorder Traversal.
//Memory Usage: 37 MB, less than 100.00% of Java online submissions for Binary Tree Postorder Traversal.
class Solution145_3 {
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer>a=new ArrayList<Integer>();
		int maxLen=1000;
		TreeNode[] stack=new TreeNode[maxLen];
		boolean[] LC=new boolean[maxLen],RC=new boolean[maxLen];
		int sp=0;
		stack[sp++]=root;
		while (sp>0)
		{
			TreeNode nt=stack[sp-1];
			if (nt==null)
			{
				sp--;
				continue;
			}
			if (!LC[sp-1])
			{
				LC[sp-1]=true;
				stack[sp]=nt.left;
				LC[sp]=false;
				RC[sp]=false;
				sp++;
				continue;
			}
			if (!RC[sp-1])
			{
				RC[sp-1]=true;
				stack[sp]=nt.right;
				LC[sp]=false;
				RC[sp]=false;
				sp++;
				continue;
			}
			a.add(nt.val);
			sp--;

		}
		return a;
	}
}
//146. LRU Cache
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//Runtime: 162 ms, faster than 14.32% of Java online submissions for LRU Cache.
//Memory Usage: 63 MB, less than 100.00% of Java online submissions for LRU Cache.
class LRUCache {
	private int[] keyList;
	private int[] vals;
	private int len;
	/**
	 * This will not change anything.
	 * @param key
	 * @return index of key in keyList.
	 */
	private int searchInd(int key)
	{
		for (int i=0;i<len;i++)
			if (keyList[i]==key) return i;
		return -1;
	}
	public LRUCache(int capacity) {
		keyList=new int[capacity];
		vals=new int[capacity];
		len=0;
	}
	public int get(int key) {
		for (int i=0;i<len;i++)
			if (keyList[i]==key)
			{
				int tv=vals[i];
				for (int j=i;j>0;j--)
				{
					keyList[j]=keyList[j-1];
					vals[j]=vals[j-1];
				}
				keyList[0]=key;
				vals[0]=tv;
				return tv;
			}
		return -1;
	}

	public void put(int key, int value) {
		int ind=searchInd(key);
		if (ind>=0)
		{
			for (int i=ind;i>0;i--)
			{
				keyList[i]=keyList[i-1];
				vals[i]=vals[i-1];
			}
			vals[0]=value;
			keyList[0]=key;
		}
		else 
		{
			if (len<keyList.length)
				len++;
			for (int i=len-1;i>0;i--)
			{
				keyList[i]=keyList[i-1];
				vals[i]=vals[i-1];
			}
			vals[0]=value;
			keyList[0]=key;
		}
	}
}

//147. Insertion Sort List
//Runtime: 57 ms, faster than 6.84% of Java online submissions for Insertion Sort List.
//Memory Usage: 39.7 MB, less than 8.64% of Java online submissions for Insertion Sort List.
class Solution147 {
	public ListNode insertionSortList(ListNode head) {
		if (head==null) return head;
		ListNode q=head;
		while (q.next!=null)
		{
			ListNode q2=q.next;
			while (q2!=null)
			{
				if (q2.val<q.val)
				{
					int t=q.val;
					q.val=q2.val;
					q2.val=t;
				}
				q2=q2.next;
			}
			q=q.next;
		}
		return head;
	}
}

//148. Sort List
//Runtime: 14 ms, faster than 12.27% of Java online submissions for Sort List.
//Memory Usage: 43.6 MB, less than 5.20% of Java online submissions for Sort List.
class Solution148 {
	public ListNode sortList(ListNode head) {
		if (head==null) return null;
		BBST<Integer> a=new BBST<Integer>(head.val);
		head=head.next;
		while (head!=null)
		{
			a=a.insert(head.val);
			head=head.next;
		}
		ListNode h=new ListNode(a.getMinData()),h1=h;
		a=a.removeMin();
		while (a!=null)
		{
			h.next=new ListNode(a.getMinData());
			a=a.removeMin();
			h=h.next;
		}
		return h1;
	}
}

//149. Max Points on a Line
//Integer Multiply overflow.  =>> Wrong Answer.
class Solution149 {
	static boolean jud(Point p1,Point p2,Point p3)
	{
		return ((long)p1.x)*p2.y+(long)p2.x*p3.y+(long)p3.x*p1.y
				-(long)p1.x*p3.y-(long)p2.x*p1.y-(long)p3.x*p2.y==0;
	}
	public int maxPoints(Point[] points) 
	{
		if (points.length<=2) return points.length;
		int l=points.length;
		int max=2;
		boolean[][] b=new boolean[l][l];
		for (int i=0;i<l;i++)
			for (int j=i+1;j<l;j++)
				if (!b[i][j])
				{
					b[i][j]=true;
					int t=2;
					for (int k=j+1;k<l;k++)
						if (jud(points[i],points[j],points[k]))
						{
							t++;
							b[i][k]=true;
							b[j][k]=true;
						}
					if (t>max) max=t;
				}
		return max;
	}
}
//Other's
//https://leetcode.com/problems/max-points-on-a-line/discuss/237574/My-java-solution-that-avoided-division
class Solution149_2 {
	public int maxPoints(Point[] points) {
		if (points.length < 3) {
			return points.length;
		}
		int max = 0;
		for(int i = 0; i < points.length; i++) {
			int samePoint = 0;
			int pointCount = 1;
			Map<Long, Integer> countMap = new HashMap<>();
			for (int j = i + 1; j < points.length; j++) {
				int dx = points[i].x - points[j].x;
				int dy = points[i].y - points[j].y;
				if(dx == 0 && dy == 0) {
					samePoint++;
					continue;
				}
				int gcd = getGCD(dx, dy);
				int commX = dx / gcd;
				int commY = dy / gcd;
				if(commX * commY < 0) {
					commX = Math.abs(commX);
					commY = - Math.abs(commY);
				}
				long key = getSlopeKey(commX, commY);
				if(countMap.get(key) == null) {
					countMap.put(key, 2);
				}
				else {
					countMap.put(key, countMap.get(key) + 1);
				}
				pointCount = Math.max(pointCount, countMap.get(key));
			}
			if(max < pointCount) {
				max = samePoint + pointCount;
			}
		}
		return max;
	}

	private long getSlopeKey(long x, long y) {
		return (x << 32) ^ y;
	}

	/**
	 * 辗转相除法获得最大公约数
	 * @param x
	 * @param y
	 * @return
	 */
	public int getGCD(int x, int y) {
		if(y == 0) {
			return x;
		}
		else {
			return getGCD(y, x % y);
		}
	}
}
//150. Evaluate Reverse Polish Notation
//Runtime: 3 ms, faster than 99.14% of Java online submissions for Evaluate Reverse Polish Notation.
//Memory Usage: 36 MB, less than 76.10% of Java online submissions for Evaluate Reverse Polish Notation.
class Solution150 
{
	public int evalRPN(String[] tokens) 
	{
		int[] stack=new int[100];
		int sp=0;
		for (int i=0;i<tokens.length;i++)
		{
			if (tokens[i].compareTo("+")==0)
			{
				stack[sp-2]=stack[sp-2]+stack[sp-1];
				sp--;
			}
			else if (tokens[i].compareTo("-")==0)
			{
				stack[sp-2]=stack[sp-2]-stack[sp-1];
				sp--;
			}
			else if (tokens[i].compareTo("*")==0)
			{
				stack[sp-2]=stack[sp-2]*stack[sp-1];
				sp--;
			}
			else if (tokens[i].compareTo("/")==0)
			{
				stack[sp-2]=stack[sp-2]/stack[sp-1];
				sp--;
			}
			else stack[sp++]=Integer.parseInt(tokens[i]);
		}
		return stack[0];
	}
}
public class LC141_150 {
	public static void test148()
	{
		String in="[4,2,1,3]";
		String[] data=in.substring(1,in.length()-1).split(",");
		ListNode head;
		if (data.length==0) head=null;
		else
		{
			head=new ListNode(Integer.parseInt(data[0]));
			ListNode p=head;
			for (int i=1;i<data.length;i++)
			{
				p.next=new ListNode(Integer.parseInt(data[i]));
				p=p.next;
			}
		}


		ListNode p=new Solution148().sortList(head);
		while (p!=null)
		{
			System.out.print(p.val+" ");
			p=p.next;
		}
	}
	public static void test149()
	{
		Point[]a=new Point[3];
		a[0]=new Point(0,0);
		a[1]=new Point(1,65536);
		a[2]=new Point(65536,0);
		System.out.println(Solution149.jud(a[0], a[1], a[2]));
		System.out.println(new Solution149().maxPoints(a ));
	}
	public static void main(String[] args)
	{
		test148();
	}

}
