public class FactorialIter {

  public FactorialIter() {
  }

public long factorial(int num) {
/* 00 */    if (num < 0)
/* 01 */      return -1;
/* 02 */    long temp_fact0 = 1;
/* 02 */    long temp_fact1 = 1;
/* 02 */    boolean out_of_bounds = false;
/* 03 */    while (num > 0 && !out_of_bounds) {
/* 04 */      temp_fact1 = temp_fact0;
/* 04 */      temp_fact0 = temp_fact0 * num;
/* 04 */      if (temp_fact1 > temp_fact0)
/* 05 */        out_of_bounds = true;
/* 06 */      num--;
/* 06 */    }
/* 07 */    if (out_of_bounds)
/* 08 */      return -2;
/* 09 */    return temp_fact0;
/* 09 */ }
 }