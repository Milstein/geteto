


public class y2 {
	
    k inst;
	
    public y2() {
        inst = new k(this);
    }
	
    static public void main(String[] x) {
        y2 g = new y2();

        System.out.println(g.inst);
    } 
}


class k {
    Object j = null;
	
    public k(Object h) {
        j = h;
    }
	
    public String toString() {
        return "Class k " + j;
    }
	
}

