package samples;

import junit.framework.*;

public class TestFactorial extends TestCase {
	Factorial f1;
	Factorial f2;

/*	public TestFactorial() {
			this("");
	}
	*/
	public TestFactorial( final String name ) {
		super(name);
	}

	public void setUp() {
		f1 = new Factorial();
		f2 = new Factorial();
	}

	public void testCompute() {
		assertEquals( 2, f1.compute(2) );
		assertEquals( 1, f1.compute(-1) );
	}

	public void testComputa() {
		assertEquals( 2, f1.compute(2) );
		assertEquals( 1, f1.compute(-1) );
	}

	public void testComputo() {
		assertEquals( 2, f1.compute(2) );
		assertEquals( 1, f1.compute(-1) );
	}
	
	public void testEqual() {
		Factorial f1 = new Factorial();
		Factorial f2 = new Factorial();
		assertEquals( f1.compute(0), f2.compute(-1) );
	}
	
	protected void xtearDown() {
	}
}
