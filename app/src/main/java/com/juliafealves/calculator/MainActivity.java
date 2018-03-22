package com.juliafealves.calculator;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.juliafealves.calculator.databinding.ActivityMainBinding;
import com.juliafealves.calculator.models.Calculator;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity
{
    private ActivityMainBinding binding;

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';

    private char CURRENT_ACTION;

    private double number1 = Double.NaN;
    private double number2;

    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        decimalFormat = new DecimalFormat("#.##########");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        /**
         * Buttons 0-9 e . set OnClickListener.
         */
        binding.buttonDot.setOnClickListener(_onClickConcatenatesTextListener);
        binding.buttonZero.setOnClickListener(_onClickConcatenatesTextListener);
        binding.buttonOne.setOnClickListener(_onClickConcatenatesTextListener);
        binding.buttonTwo.setOnClickListener(_onClickConcatenatesTextListener);
        binding.buttonThree.setOnClickListener(_onClickConcatenatesTextListener);
        binding.buttonFour.setOnClickListener(_onClickConcatenatesTextListener);
        binding.buttonFive.setOnClickListener(_onClickConcatenatesTextListener);
        binding.buttonSix.setOnClickListener(_onClickConcatenatesTextListener);
        binding.buttonSeven.setOnClickListener(_onClickConcatenatesTextListener);
        binding.buttonEight.setOnClickListener(_onClickConcatenatesTextListener);
        binding.buttonNine.setOnClickListener(_onClickConcatenatesTextListener);

        binding.buttonAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                _calculate();
                CURRENT_ACTION = ADDITION;
                binding.infoTextView.setText(decimalFormat.format(number1) + "+");
                binding.editText.setText(null);
            }
        });

        binding.buttonSubtract.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                _calculate();
                CURRENT_ACTION = SUBTRACTION;
                binding.infoTextView.setText(decimalFormat.format(number1) + "-");
                binding.editText.setText(null);
            }
        });

        binding.buttonMultiply.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                _calculate();
                CURRENT_ACTION = MULTIPLICATION;
                binding.infoTextView.setText(decimalFormat.format(number1) + "*");
                binding.editText.setText(null);
            }
        });

        binding.buttonDivide.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                _calculate();
                CURRENT_ACTION = DIVISION;
                binding.infoTextView.setText(decimalFormat.format(number1) + "/");
                binding.editText.setText(null);
            }
        });

        binding.buttonEqual.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                _calculate();
                binding.infoTextView.setText(decimalFormat.format(number1));
                number1 = Double.NaN;
            }
        });

        binding.buttonClear.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if(binding.editText.getText().length() > 0) {
                    CharSequence currentText = binding.editText.getText();
                    binding.editText.setText(currentText.subSequence(0, currentText.length()-1));
                }
                else {
                    number1 = Double.NaN;
                    number2 = Double.NaN;
                    binding.editText.setText("");
                    binding.infoTextView.setText("");
                }
            }
        });
    }

    /**
     * Concatenates the button text with EditText.
     */
    private View.OnClickListener _onClickConcatenatesTextListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            Button button = (Button) view;
            binding.editText.setText(binding.editText.getText() + button.getText().toString());
        }
    };

    /**
     * Calcule thow numbers.
     */
    private void _calculate()
    {
        if(!Double.isNaN(number1)) {
            number2 = Double.parseDouble(binding.editText.getText().toString());
            binding.editText.setText(null);

            switch (CURRENT_ACTION){
                case ADDITION:
                   number1 = Calculator.sum(this.number1, this.number2);
                   break;
                case SUBTRACTION:
                    number1 = Calculator.minus(this.number1, this.number2);
                    break;
                case MULTIPLICATION:
                    number1 = Calculator.multiplication(this.number1, this.number2);
                    break;
                case DIVISION:
                    try {
                        number1 = Calculator.division(this.number1, this.number2);
                    }catch (ArithmeticException arithmeticException){
                        Toast toast = Toast.makeText(getApplicationContext(), arithmeticException.getMessage(), Toast.LENGTH_LONG);
                        toast.show();
                        binding.infoTextView.setText("");
                        number1 = 0;
                    }
                    break;
            }
        } else {
            try {
                number1 = Double.parseDouble(binding.editText.getText().toString());
            } catch (Exception e){}
        }
    }
}
