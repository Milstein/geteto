package base;

public class A {
	
	int a = 1;
	int b = 2;
	int c = 3;
	String str = "str";
	
	public A() {
		//initialize fields
	}
	
	public void setA(int _a) {
	  a = _a; 
	}
	
	public void setB(int _b) {
	  b = _b;
	}
	
	public void setC(int _c) {
	  c = _c; //sets c
	}
	
	public void changeStr(String s) {
	  str = s; //sets str
	}
	
	public void settings() {
	  System.out.println("These are the settings...");
	  //does not change object's state
	}
	
	public int getA() {
		return a; //returns a
	}
	
	public int getB() {
		return b; //returns b
	}
	
	public int getC() {
		return c; //returns c
	}
	
	public String getStr() {
		return str; //returns str
	}
	
	static public void main(String args[]) {
	  A a = new A();
	  a.setA(2);
	  a.setA(3);
	  a.setB(2);
	  a.setC(2);
	  a.setA(2);
	  a.setA(3);
	  a.setB(4);
	  a.changeStr("asd");
	  a.settings();
	}
}
