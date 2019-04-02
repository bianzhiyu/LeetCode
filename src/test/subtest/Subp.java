package test.subtest;

class A
{
	int x=1;
	int foo()
	{
		return 1;
	}
}

class B extends A
{
	int x=2;
	int foo()
	{
		return 2;
	}
}

public class Subp
{
	public static void main(String[]args)
	{
		System.out.println("sp");
		A a=new B();
		B b=(B)a;
		System.out.println(a.x);
		System.out.println(a.foo());
		System.out.println(b.x);
		System.out.println(b.foo());
	}
}
