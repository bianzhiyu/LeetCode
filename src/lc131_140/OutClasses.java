package lc131_140;

import java.util.List;

class Node
{
	public int val;
	public List<Node> neighbors;

	public Node()
	{
	}

	public Node(int _val, List<Node> _neighbors)
	{
		val = _val;
		neighbors = _neighbors;
	}
}


class RNode 
{
    public int val;
    public RNode next;
    public RNode random;

    public RNode() {}

    public RNode(int _val,RNode _next,RNode _random) {
        val = _val;
        next = _next;
        random = _random;
    }
}

public class OutClasses
{
	public static void main(String[] args)
	{

	}

}
