package com.example.currencycoverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    private Button converter;
    private Spinner nationalInput;
    private Spinner nationalOutput;
    private TextView resultInBanner;
    private EditText currencyInput;
    private TextView currencyInputResult;
    private TextView currencyOutputResult;
    private ImageButton buttonSwitch;
    private TextView currencyAtoB;
    private TextView currencyBtoA;

    private String inputString;
    private String outputString;
    private double intCurIn;

    ArrayAdapter<CharSequence> adapter1;
    ArrayAdapter<CharSequence> adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ánh xạ
        converter = findViewById(R.id.converter);
        nationalInput = findViewById(R.id.nationalInput);
        resultInBanner = findViewById(R.id.resultInBanner);
        nationalOutput = findViewById(R.id.nationalOutput);
        currencyInput = findViewById(R.id.currencyInput);
        currencyInputResult = findViewById(R.id.currencyInputResult);
        currencyOutputResult = findViewById(R.id.currencyOutputResult);
        buttonSwitch = findViewById(R.id.buttonSwitch);
        currencyAtoB = findViewById(R.id.currencyAtoB);
        currencyBtoA = findViewById(R.id.currencyBtoA);
        //


        Log.d("intCurIn", currencyInput.getText().toString());

        Log.d("intCurIn", String.valueOf(intCurIn));

        converter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    intCurIn = Double.parseDouble(currencyInput.getText().toString());
                }

                catch (Exception ex){
                    intCurIn = 0.0;
                }

                //Spinner
                inputString = nationalInput.getSelectedItem().toString();
                outputString = nationalOutput.getSelectedItem().toString();
                //
                //Line 1
                BigDecimal resultOneFirstLine = convertCurrency(intCurIn,
                        inputString, "VNĐ");
                BigDecimal resultTwoFirstLine =  convertCurrency(resultOneFirstLine.doubleValue(),
                        "VNĐ", outputString);
                //Set scale
                if(resultTwoFirstLine.intValue() >= 10000)
                {
                    resultTwoFirstLine = resultTwoFirstLine.setScale(0, RoundingMode.CEILING); //Làm tròn celi
                }
                else
                {
                    resultTwoFirstLine = resultTwoFirstLine.setScale(5, RoundingMode.CEILING); //Làm tròn celi
                }
                //Line 2
                BigDecimal resultOneSecondLine = convertCurrency(intCurIn,
                        outputString, "VNĐ");
                BigDecimal resultTwoSecondLine =  convertCurrency(resultOneSecondLine.doubleValue(),
                        "VNĐ", inputString);
                if(resultTwoSecondLine.intValue() >= 10000)
                {
                    resultTwoSecondLine = resultTwoSecondLine.setScale(0, RoundingMode.CEILING); //Làm tròn celi
                }
                else
                {
                    resultTwoSecondLine = resultTwoSecondLine.setScale(5, RoundingMode.CEILING); //Làm tròn celi
                }
                //Result In Banner
//                resultInBanner.setText(String.valueOf(intCurIn) + " " + inputString + " to " +
//                        outputString + " = " + String.valueOf(resultTwoFirstLine) + " " + outputString);
                resultInBanner.setText(String.format("%1$,.2f",intCurIn) + " " + inputString + " to " +
                        outputString + " = " + String.valueOf(resultTwoFirstLine) + " " + outputString);

                //Main result
                Log.d("Test", String.valueOf(resultTwoFirstLine));
                currencyInputResult.setText(String.format("%1$,.2f",intCurIn) + " " + inputString + " =");
                currencyOutputResult.setText(String.valueOf(resultTwoFirstLine) + " " + outputString);
                //The second result
                currencyAtoB.setText(String.format("%1$,.2f",intCurIn) + " " + inputString + " = " +
                        String.valueOf(resultTwoFirstLine) + " " + outputString);
                currencyBtoA.setText(String.format("%1$,.2f",intCurIn) + " " + outputString + " = " +
                        String.valueOf(resultTwoSecondLine) + " " + inputString);
            }
        });

        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter1 = ArrayAdapter.createFromResource(this,
                R.array.currency, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        nationalOutput.setAdapter(adapter1);

        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter2 = ArrayAdapter.createFromResource(this,
                R.array.currency2, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        nationalOutput.setAdapter(adapter2);
        //

        buttonSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int positionSpinner1 = nationalInput.getSelectedItemPosition() ;
                int positionSpinner2 = nationalOutput.getSelectedItemPosition() ;
                if (nationalInput.getAdapter().equals(adapter1)) {
                    nationalInput.setAdapter(adapter2);
                    nationalOutput.setAdapter(adapter1);
                } else {
                    nationalInput.setAdapter(adapter1);
                    nationalOutput.setAdapter(adapter2);
                }
                nationalInput.setSelection(positionSpinner2);
                nationalOutput.setSelection(positionSpinner1);
            }
        });

    }
    public BigDecimal convertCurrency(double input, String curIn, String curOut)
    {
        BigDecimal bigDecimal;
        if(curIn.equals("VNĐ"))
        {
            switch (curOut)
            {
                case "VNĐ":
                    return new BigDecimal(input);
                case "JPY":
                    return new BigDecimal(input * 0.00469);
                case "AUD":
                    return new BigDecimal (input * 0.000061);
                case "THB":
                    return new BigDecimal (input * 0.00135);
                case "USD":
                    return new BigDecimal (input * 0.000043);
            }
        }
        else if(curOut.equals("VNĐ"))
        {
            switch (curIn)
            {
                case "VNĐ":
                    return new BigDecimal(input);
                case "JPY":
                    return new BigDecimal(input * 215.72);
                case "AUD":
                    return new BigDecimal(input * 16087.51);
                case "THB":
                    return new BigDecimal(input * 742.10);
                case "USD":
                    return new BigDecimal(input * 23000);
            }
        }
        return new BigDecimal(0);
    }


}
