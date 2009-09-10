package samples;


import util.*;


public class FactorialError {
    int dummy;
    int g = 9;

    FactorialError() {
        dummy = 15;
        if (dummy == g) {
            g = dummy * 2;
        }
    }

    long compute(int x) {
        if (x <= 10) {
            long r = 1;

            for (int k = 2; k <= x; k++) {
                r += k;
                // r *= k;
            }
            return r;
        } else {
            return (long) x * compute(x - 1);
        }
    }

    static public void main(String args[]) {
        try { {
                FactorialError f1 = new FactorialError();
				
                int num = GetInputField.getInt("Type an integer number: ");

                System.out.println(f1.compute(num));
				
                num = GetInputField.getInt("Type an integer number: ");
                System.out.println(f1.compute(num));
            } {
                FactorialError f2 = new FactorialError();
				
                int num = GetInputField.getInt("Type an integer number: ");

                System.out.println(f2.compute(num));
            }
        } catch (Exception e) {
            ToolConstants.reportException(e, ToolConstants.STDERR);
        }
    }
}

