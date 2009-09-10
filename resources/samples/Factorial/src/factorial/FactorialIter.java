package factorial;

public class FactorialIter {

  public FactorialIter() {
  }

  public long factorial(int num)
  {
    //Se o número é válido, retorna o fatorial
    //Se o número está acima do limite, retorna -2
    //Se o número está abaixo do limite, retorna -1

    num = (int)num;
    if (num < 0) return -1;

    long temp_fact0 = 1;
    long temp_fact1 = 1;
    boolean out_of_bounds = false;

    while (num > 0 && !out_of_bounds) {
      temp_fact1 = temp_fact0;
      temp_fact0 = temp_fact0 * num;
      if (temp_fact1 > temp_fact0)
        out_of_bounds = true;
      num--;
    }

    if (out_of_bounds) return -2;

    return temp_fact0;
  }
}