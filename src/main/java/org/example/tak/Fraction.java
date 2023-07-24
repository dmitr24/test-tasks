package org.example.tak;

public class Fraction implements Comparable {
    final int nominator;
    final int denominator;

    public Fraction(int nominator, int denominator) {
        this.nominator = nominator;
        this.denominator = denominator;
    }

    public Fraction add(Fraction otherFraction) {
        int lcm = lcm(this.denominator, otherFraction.denominator);
        int a = getComparableNominator(this, lcm);
        int b = getComparableNominator(otherFraction, lcm);


        return new Fraction(a + b, lcm);
    }

    public Fraction subtract(Fraction otherFraction) {
        int lcm = lcm(this.denominator, otherFraction.denominator);
        int a = getComparableNominator(this, lcm);
        int b = getComparableNominator(otherFraction, lcm);


        return new Fraction(a - b, lcm);
    }

    public Fraction multiply(Fraction otherFraction) {
        return new Fraction(this.nominator * otherFraction.nominator,
                this.denominator * otherFraction.denominator);
    }

    public Fraction divide(Fraction otherFraction) {
        return new Fraction(this.nominator * otherFraction.denominator,
                this.denominator * otherFraction.nominator);
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Fraction)) {
            return false;
        }

        Fraction otherFraction = (Fraction) obj;

        int lcm = lcm(this.denominator, otherFraction.denominator);

        int a = getComparableNominator(this, lcm);
        int b = getComparableNominator(otherFraction, lcm);

       if (a != b) {
           return false;
       }

        return true;
    }

    @Override
    public String toString() {
        return nominator + "/" + denominator;
    }

    @Override
    public int compareTo(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Comparing to null is impossible");
        }

        Fraction otherFraction = (Fraction) obj;

        int lcm = lcm(this.denominator, otherFraction.denominator);

        int a = getComparableNominator(this, lcm);
        int b = getComparableNominator(otherFraction, lcm);

        if (a < b) {
            return -1;
        } else if (a > b) {
            return 1;
        } else {
            return 0;
        }
    }

    private int getComparableNominator(Fraction fraction, int lcm) {
        int timesBiiger = lcm / fraction.denominator;

        return fraction.nominator * timesBiiger;
    }

    private int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    private int gcd(int a, int b) {
        if (a < b) {
           int c = a;
           a = b;
           b = c;
        }

        while (b > 0) {
            a %= b;
            int c = a;
            a = b;
            b = c;
        }

        return a;
    }

    public static Fraction fromString(String str) {
        String[] strings = str.split("/");

        return new Fraction(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
    }
}
