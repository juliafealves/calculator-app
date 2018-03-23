package com.juliafealves.calculator.pageObject;

import android.widget.Button;
import android.widget.TextView;

import com.juliafealves.calculator.R;
import com.robotium.solo.Solo;

public class CalculatorPageObject
{
    private static final CharSequence PLUS = "+";
    private static final CharSequence MINUS = "-";
    private static final CharSequence MULTIPLY = "*";
    private static final CharSequence DIVISION = "/";
    private Solo solo;
    private TextView resultText;
    private Button buttonEqual;

    public CalculatorPageObject(Solo solo)
    {
        this.solo = solo;
        updateViews();
    }

    public void updateViews()
    {
        this.resultText = (TextView) solo.getView(R.id.infoTextView);
        this.buttonEqual = (Button) solo.getView(R.id.buttonEqual);
    }

    public void sum(int number1, int number2)
    {
        this._calculator(number1, number2, PLUS);
    }

    public void minus(int number1, int number2)
    {
        this._calculator(number1, number2, MINUS);
    }

    public void multiplication(int number1, int number2)
    {
        this._calculator(number1, number2, MULTIPLY);
    }

    public void division(int number1, int number2)
    {
        this._calculator(number1, number2, DIVISION);
    }

    /**
     * Simple calculator with just one character (one number).
     *
     * @param number1
     * @param number2
     * @param operator
     */
    private void _calculator(int number1, int number2, CharSequence operator)
    {
        solo.clickOnButton(String.valueOf(number1));
        solo.clickOnButton(operator.toString());
        solo.clickOnButton(String.valueOf(number2));
        solo.clickOnView(buttonEqual);
    }

    /**
     * Return result in format String.
     * @return
     */
    public String getTextResult()
    {
        updateViews();
        return resultText.getText().toString();
    }
}
