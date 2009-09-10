package samples;


public class b3 {
int x;


	public static void main(String[] args)
	{
		b3 r, s;
		
		r = new b3();
		s = new b3();
		
		(r.x == 0? r : s).x = 20;
	}	
	
        public int x(int a)
        {
            int b;
            if ( a > 10 )
                b = 10;
            else
                b = 0;
            return b;
        }
	
	
}

