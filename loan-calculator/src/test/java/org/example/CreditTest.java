package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.example.Credit.CreditType;

public class CreditTest {
    @Test
    void testAnnuityPayment() {
        Credit credit = new Credit(100000, 12.0, 12, CreditType.ANNUITY);
        assertEquals(8884.88, credit.calculateAnnuityPayment(), 0.01);
    }

    @Test
    void testDifferentiatedPayment() {
        Credit credit = new Credit(100000, 12.0, 12, CreditType.DIFFERENTIATED);
        assertEquals(9333.33, credit.calculateDifferentiatedPayment(), 0.01);
    }

    @Test
    void testTotalCreditSum() {
        Credit credit = new Credit(100000, 12.0, 12, CreditType.ANNUITY);
        assertEquals(106618.56, credit.calculateTotalCreditSum(), 0.01);
    }

    @Test
    void testOverpayment() {
        Credit credit = new Credit(100000, 12.0, 12, CreditType.ANNUITY);
        assertEquals(6618.56, credit.calculateOverpayment(), 0.01);
    }

    @Test
    void testMonthSumAnnuity() {
        Credit credit = new Credit(100000, 12.0, 12, CreditType.ANNUITY);
        assertEquals(8884.88, credit.calculateMonthSum(1), 0.01);
        assertEquals(8884.88, credit.calculateMonthSum(12), 0.01);
    }

    @Test
    void testMonthSumDifferentiated() {
        Credit credit = new Credit(100000, 12.0, 12, CreditType.DIFFERENTIATED);
        assertEquals(9333.33, credit.calculateMonthSum(1), 0.01);
        assertEquals(8416.66, credit.calculateMonthSum(12), 0.01);
    }
}