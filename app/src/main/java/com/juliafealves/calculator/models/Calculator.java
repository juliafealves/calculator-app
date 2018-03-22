package com.juliafealves.calculator.models;

public class Calculator
{
    /**
     * Add two numbers.
     * @param number1
     * @param number2
     * @return
     */
    public static double sum(double number1, double number2)
    {
        return number1 + number2;
    }

    /**
     * Subtract two numbers.
     * @param number1
     * @param number2
     * @return
     */
    public static double minus(double number1, double number2)
    {
        return number1 - number2;
    }

    /**
     * Multiplies two numbers.
     * @param number1
     * @param number2
     * @return
     */
    public static double multiplication(double number1, double number2)
    {
        return number1 * number2;
    }

    /**
     * Divide two numbers.
     * @param number1
     * @param number2
     * @return
     */
    public static double division(double number1, double number2)
    {
        double result = number1 / number2;

        if(result == Double.POSITIVE_INFINITY || result == Double.NEGATIVE_INFINITY)
            throw new ArithmeticException("Cannot divide by zero.");
        else
            return result;
    }
}
