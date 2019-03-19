package test;

class C1
{
	int x=0;
	C1(){}
	C1(int _x){x=_x;}
	int f(C1 o)
	{
		return x-o.x;
	}
}

class C2 extends C1
{
	int y=0;
	C2(){}
	C2(int _y){y=_y;}
	int f(C2 o)
	{
		return y-o.y;
	}
}

public class OutClasses {

}
