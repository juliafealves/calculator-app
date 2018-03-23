package com.juliafealves.calculator;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import com.juliafealves.calculator.models.Calculator;
import com.juliafealves.calculator.pageObject.CalculatorPageObject;
import com.robotium.solo.Solo;

import java.io.Console;

public class CalculatorRobotiumTest extends ActivityInstrumentationTestCase2<MainActivity>
{
    private Solo solo;
    private CalculatorPageObject calculatorPO;

    public CalculatorRobotiumTest()
    {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception
    {
        solo = new Solo(getInstrumentation(), getActivity());
        calculatorPO = new CalculatorPageObject(solo);
    }

    @Override
    public void tearDown() throws Exception
    {
        solo.finishOpenedActivities();
    }

    public void testSum() throws Exception
    {
        calculatorPO.sum(7, 3);
        String result = calculatorPO.getTextResult();
        boolean isRight = result.equals("10");
        assertTrue("The value not equals (SUM): ", isRight);
    }

    public void testMinus() throws Exception
    {
        calculatorPO.minus(5, 3);
        String result = calculatorPO.getTextResult();
        boolean isRight = result.equals("2");
        assertTrue("The value not equals (MINUS): ", isRight);
    }

    public void testMultiplication() throws Exception
    {
        calculatorPO.multiplication(6, 3);
        String result = calculatorPO.getTextResult();
        boolean isRight = result.equals("18");
        assertTrue("The value not equals (MULTIPLICATION): ", isRight);
    }

    public void testDivision() throws Exception
    {
        calculatorPO.division(8, 2);
        String result = calculatorPO.getTextResult();
        boolean isRight = result.equals("4");
        assertTrue("The value not equals (DIVISION): ", isRight);
    }

    public void testDivisionByZero() throws Exception
    {
        calculatorPO.division(7, 0);
        assertTrue("Message not showed!", solo.searchText("Cannot divide by zero."));
    }
}
