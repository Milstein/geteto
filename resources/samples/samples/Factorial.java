package samples;


public class Factorial {
    int dummy;
    int g = 9;

    Factorial() {
        dummy = 15;
        if (dummy == g) {
            g = dummy * 2;
        }
        System.out.println("Instrumentado: " + getClass().hashCode());
    }

    long compute(int x) {
        if (x <= 10) {
            long r = 1;

            for (int k = 2; k <= x; k++) {
                r *= k;
            }
            return r;
        } else {
            return (long) x * compute(x - 1);
        }
    }

    static public void main(String args[]) { {
            Factorial f1 = new Factorial();

            System.out.println(f1.compute(10));
            System.out.println(f1.compute(15));
        } {
            Factorial f2 = new Factorial();

            System.out.println(f2.compute(20));
        }
    }
}
