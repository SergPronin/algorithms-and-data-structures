package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Credit {
    public enum CreditType {
        ANNUITY,
        DIFFERENTIATED
    }

    private double principal;
    private double annualInterestRate;
    private int termMonths;
    private CreditType type;

    public Credit(double principal, double annualInterestRate, int termMonths, CreditType type) {
        this.principal = principal;
        this.annualInterestRate = annualInterestRate;
        this.termMonths = termMonths;
        this.type = type;
    }

    public double calculateAnnuityPayment() {
        if (type != CreditType.ANNUITY) return 0.0;
        double monthlyRate = annualInterestRate / 12 / 100;
        double denominator = 1 - Math.pow(1 + monthlyRate, -termMonths);
        if (denominator == 0) return 0.0;
        double annuity = (principal * monthlyRate) / denominator;
        return BigDecimal.valueOf(annuity)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public double calculateDifferentiatedPayment() {
        if (type != CreditType.DIFFERENTIATED) return 0.0;
        BigDecimal monthlyPrincipal = BigDecimal.valueOf(principal)
                .divide(BigDecimal.valueOf(termMonths), 2, RoundingMode.HALF_UP);
        BigDecimal monthlyRate = BigDecimal.valueOf(annualInterestRate / 12 / 100);
        BigDecimal firstPayment = monthlyPrincipal.add(
                BigDecimal.valueOf(principal).multiply(monthlyRate)
        );
        return firstPayment.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public double calculateTotalCreditSum() {
        double monthlyPayment = (type == CreditType.ANNUITY ? calculateAnnuityPayment() : calculateDifferentiatedPayment());
        double total = 0;
        if (type == CreditType.ANNUITY) {
            total = monthlyPayment * termMonths;
        } else {
            for (int month = 1; month <= termMonths; month++) {
                total += calculateMonthSum(month);
            }
        }
        return BigDecimal.valueOf(total)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public double calculateOverpayment() {
        double total = calculateTotalCreditSum();
        return BigDecimal.valueOf(total - principal)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public double calculateMonthSum(int month) {
        if (month < 1 || month > termMonths) return 0.0;
        if (type == CreditType.ANNUITY) {
            return calculateAnnuityPayment();
        } else {
            BigDecimal monthlyPrincipal = BigDecimal.valueOf(principal)
                    .divide(BigDecimal.valueOf(termMonths), 2, RoundingMode.HALF_UP);
            BigDecimal remainingPrincipal = BigDecimal.valueOf(principal)
                    .subtract(monthlyPrincipal.multiply(BigDecimal.valueOf(month - 1)));
            BigDecimal monthlyRate = BigDecimal.valueOf(annualInterestRate / 12 / 100);
            BigDecimal payment = monthlyPrincipal.add(remainingPrincipal.multiply(monthlyRate));
            return payment.setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
    }

    // Геттеры
    public CreditType getType() { return type; }
    public double getPrincipal() { return principal; }
    public double getAnnualInterestRate() { return annualInterestRate; }
    public int getTermMonths() { return termMonths; }
}