package samples;


public class Teste
{

   public Teste()
   {
      int x = 0;
      if ( x > 0)
         x = 2;
      x = x + 1;
      if ( x == 0 )
        x = 4;
      else
        x = 3;
      x = 5;	
   }

   static public void main(String[] args)
   {
      new Teste();
   }
}
