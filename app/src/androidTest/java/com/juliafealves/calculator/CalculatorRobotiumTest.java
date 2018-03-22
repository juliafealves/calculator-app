package com.juliafealves.calculator;

import android.test.ActivityInstrumentationTestCase2;
import com.robotium.solo.Solo;

public class CalculatorRobotiumTest extends ActivityInstrumentationTestCase2<MainActivity>
{
    private Solo solo;

    public CalculatorRobotiumTest()
    {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception
    {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception
    {
        solo.finishOpenedActivities();
    }

    public void testSum()
    {
        solo.clickOnButton("7");
        solo.clickOnButton("+");
        solo.clickOnButton("3");
        solo.clickOnButton("=");
        solo.waitForText("7+3 = 10");
    }

    public void testMinus()
    {
        solo.clickOnButton("5");
        solo.clickOnButton("-");
        solo.clickOnButton("3");
        solo.clickOnButton("=");
        solo.waitForText("5-3 = 2");
    }

    public void testMultiplication()
    {
        solo.clickOnButton("6");
        solo.clickOnButton("*");
        solo.clickOnButton("3");
        solo.clickOnButton("=");
        solo.waitForText("6*3 = 18");
    }

    public void testDivision()
    {
        solo.clickOnButton("1");
        solo.clickOnButton("2");
        solo.clickOnButton("/");
        solo.clickOnButton("2");
        solo.clickOnButton("=");
        solo.waitForText("12/2 = 6");
    }

    public void testDivisionByZero()
    {
        solo.clickOnButton("1");
        solo.clickOnButton("/");
        solo.clickOnButton("0");
        solo.clickOnButton("=");
        assertTrue("Message not showed!", solo.searchText("Cannot divide by zero."));
    }
}
