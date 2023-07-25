package siberteam.testperiod.first.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import siberteam.testperiod.first.exception.ForbiddenDenominatorException;
import siberteam.testperiod.first.task.Fraction;

public class FractionTests {
    @Test
    void zeroDenominatorThrowsExceptionTest() {
        Assertions.assertThrows(ForbiddenDenominatorException.class,
                () -> new Fraction(1, 0));
    }

    @Test
    void addOtherFractionTest() {
        Fraction firstFraction = new Fraction(1, 8);
        Fraction secondFraction = new Fraction(4, 5);

        Fraction resultFraction = firstFraction.add(secondFraction);

        Assertions.assertEquals("37/40", resultFraction.toString());
    }

    @Test
    void subtractOtherFractionTest() {
        Fraction firstFraction = new Fraction(3, 5);
        Fraction secondFraction = new Fraction(4, 5);

        Fraction resultFraction = firstFraction.subtract(secondFraction);

        Assertions.assertEquals("-1/5", resultFraction.toString());
    }

    @Test
    void multiplyOtherFractionTest() {
        Fraction firstFraction = new Fraction(1, 8);
        Fraction secondFraction = new Fraction(4, 5);

        Fraction resultFraction = firstFraction.multiply(secondFraction);

        Assertions.assertEquals("4/40", resultFraction.toString());
    }

    @Test
    void divideOtherFractionTest() {
        Fraction firstFraction = new Fraction(1, 8);
        Fraction secondFraction = new Fraction(4, 5);

        Fraction resultFraction = firstFraction.divide(secondFraction);

        Assertions.assertEquals("5/32", resultFraction.toString());
    }

    @Test
    void defaultEqualsTest() {
        Fraction firstFraction = new Fraction(1, 4);
        Fraction secondFraction = new Fraction(1, 4);

        Assertions.assertEquals(firstFraction, secondFraction);
    }

    @Test
    void notEqualsTest() {
        Fraction firstFraction = new Fraction(1, 8);
        Fraction secondFraction = new Fraction(2, 4);

        Assertions.assertNotEquals(firstFraction, secondFraction);
    }

    @Test
    void equalsWithDifferentSignaturesTest() {
        Fraction firstFraction = new Fraction(1, 8);
        Fraction secondFraction = new Fraction(2, 16);

        Assertions.assertEquals(firstFraction, secondFraction);
    }

    @Test
    void compareSmallWithBigTest() {
        Fraction firstFraction = new Fraction(4, 8);
        Fraction secondFraction = new Fraction(2, 16);

        int firstComparedToSecond = firstFraction.compareTo(secondFraction);

        Assertions.assertEquals(1, firstComparedToSecond);
    }

    @Test
    void compareEqualValuesTest() {
        Fraction firstFraction = new Fraction(1, 8);
        Fraction secondFraction = new Fraction(2, 16);

        int firstComparedToSecond = firstFraction.compareTo(secondFraction);

        Assertions.assertEquals(0, firstComparedToSecond);
    }

    @Test
    void compareBigWithSmallValueTest() {
        Fraction firstFraction = new Fraction(1, 8);
        Fraction secondFraction = new Fraction(22, 16);

        int firstComparedToSecond = firstFraction.compareTo(secondFraction);

        Assertions.assertEquals(-1, firstComparedToSecond);
    }

    @Test
    void getFractionFromStringTest() {
        Fraction fraction = Fraction.fromString("37/40");

        Assertions.assertEquals("37/40", fraction.toString());
    }
}
