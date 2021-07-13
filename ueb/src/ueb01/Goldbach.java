package ueb01;


/**
 * For a given even integer number n in the proven ueb01.Goldbach range,
 * the methods in this class can be used to find two prime integer numbers whose sum eqauls n.
 */
public class Goldbach {

    public boolean evenNumber(double z) {
        return (z % 2 == 0 && z > 2);
    }

    public Pair<Double> goldbachAlgorithm(double z) {
        if (evenNumber(z)) {
            for (double a = 2; a <= z/2; a++) {
                double b = z-a;
                if (summationOfPrimes(z, a, b)) {
                    return new Pair(a, b);
                }

            }
        }
        throw new IllegalArgumentException("Wrong Input. Input is not an even number >2");
    }

    public boolean isPrime(double p) {
        boolean isPrime = true;
        for (int i = 2; i < p/2; i++) {
            if (p%i==0) {
                isPrime = false;
                break;
            }

        }
        return isPrime;
    }

    public boolean summationOfPrimes(double z, double n, double m) {
        return (isPrime(n) && isPrime(m) && (n + m == z));
    }
}

