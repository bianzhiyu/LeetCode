package treeCodec;

public class TestTreeCodec
{
	public static void main(String[]args)
	{
		String in="[1,2,3]";
		TreeNode rt=TreeCodec.deserialize(in);
		String out=TreeCodec.serialize(rt);
		System.out.println(out);
	}
}
