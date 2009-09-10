package br.jabuti.samples;


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
}

