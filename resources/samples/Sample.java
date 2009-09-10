public class Sample {  
    public int count( int x, int y ) {
        try {
        	while ( x > 1 ) {
        		print( x );
        		x = x / y;
        	}
        } catch (Exception e) {
            x = 0;
        } finally {
            y = 0;
        }
        return x;
    }  
    
    public void print(int n) {
        System.out.print(n + "\n");
    }
}
