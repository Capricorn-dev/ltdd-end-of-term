package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //
    TextView showNumber;
    //Số
    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;
    Button zero;
    //Caculated
    Button add;
    Button minus;
    Button multiple;
    Button division;
    Button equal;
    Button square;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ánh xạ
        //Số
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        zero = (Button) findViewById(R.id.zero);
        //Show number
        showNumber = (TextView) findViewById(R.id.showNumber);
        //Caculated
        add = (Button) findViewById(R.id.add);
        minus = (Button) findViewById(R.id.minus);
        multiple = (Button) findViewById(R.id.multiple);
        division = (Button) findViewById(R.id.division);
        equal = (Button) findViewById(R.id.equal);
        square = (Button) findViewById(R.id.square);
        //Handle Number
        handleNumberButton();
        handleCalculatedButton();
    }
    String numberString = "";
    public void handleNumberButton()
    {
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberString += "1";
                showNumber.setText(numberString);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberString += "2";
                showNumber.setText(numberString);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberString += "3";
                showNumber.setText(numberString);
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberString += "4";
                showNumber.setText(numberString);
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberString += "5";
                showNumber.setText(numberString);
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberString += "6";
                showNumber.setText(numberString);
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberString += "7";
                showNumber.setText(numberString);
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberString += "8";
                showNumber.setText(numberString);
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberString += "9";
                showNumber.setText(numberString);
            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberString += "0";
                showNumber.setText(numberString);
            }
        });
    }
    private String calculated = "";
    private float value1;
    private float value2;
    private boolean isValue1 = true;
    public void handleCalculatedButton()
    {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isValue1OrValue2();
                calculated = "+";
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isValue1OrValue2();
                calculated = "-";
            }
        });
        multiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isValue1OrValue2();
                calculated = "*";
            }
        });
        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isValue1OrValue2();
                calculated = "/";
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(calculated != "")
               {
                   isValue1OrValue2();
                   showNumber.setText(String.valueOf(calculation(calculated, value1, value2)));
                   value1 = calculation(calculated, value1, value2);
               }
            }
        });
        square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isValue1OrValue2();
                value1 = squareCalculated(value1);
                numberString = String.valueOf(value1);
                showNumber.setText(numberString);
            }
        });
    }
    public float calculation(String calculated, float value1, float value2)
    {
        switch (calculated)
        {
            case "+":
                return value1 + value2;
            case "-":
                return value1 - value2;
            case "*":
                return value1 * value2;
            case "/":
                return value1 / value2;
        }
        return 0;
    }
    public float squareCalculated(float value)
    {
        return value * value;
    }
    private void isValue1OrValue2()
    {
        if(isValue1 == true)
        {
            if(numberString != "")
            {
                value1 = Float.parseFloat(numberString);
                isValue1 = false;
                numberString = "";
                showNumber.setText("");
            }
        }
        else
        {
            if(numberString != "")
            {
                value2 = Float.parseFloat(numberString);
                numberString = "";
                showNumber.setText("");
            }
        }
    }
}

