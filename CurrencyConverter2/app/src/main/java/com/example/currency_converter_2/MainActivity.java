package com.example.currency_converter_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    Spinner spinner1, spinner2;
    ImageButton imgBtnSwitch;

    ArrayAdapter<CharSequence> adapter1, adapter2;

    Button btnConvert;

    EditText editTxtInput;
    TextView txtViewInput, txtViewOutput;
    TextView txtViewInputCurrency, txtViewOutputCurrency;
    TextView txtViewOutput_1, txtViewInput_1;
    TextView txtViewResult;
    TextView txtView_1, txtView_2;
    TextView txtViewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        //KHỞI TẠO ITEMS CHO 2 SPINNER
        initSpinner1();
        initSpinner2();

        //BẮT SỰ KIỆN ẤN NÚT SWITCH SPINNER
        imgBtnSwitch_SetOnClickListener();

        //BẮT SỰ KIỆN NÚT KẾT QUẢ
        btnConvert_setOnClickListener();
    }

    private void btnConvert_setOnClickListener() {
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtViewInput.setText(editTxtInput.getText().toString());

                txtViewInputCurrency.setText(spinner1.getSelectedItem().toString());
                txtViewOutputCurrency.setText(spinner2.getSelectedItem().toString());

                BigDecimal numberOne = converter(editTxtInput.getText().toString(),
                        spinner1.getSelectedItem().toString(),
                        spinner2.getSelectedItem().toString());
                numberOne = scaleValue(numberOne);
                txtViewOutput.setText(String.valueOf(numberOne));

                txtView_1.setText(editTxtInput.getText().toString());

                BigDecimal numberTwo = converter(editTxtInput.getText().toString(),
                        spinner2.getSelectedItem().toString(),
                        spinner1.getSelectedItem().toString());
                numberTwo = scaleValue(numberTwo);
                txtView_2.setText(String.valueOf(numberTwo));

                txtViewOutput_1.setText(spinner2.getSelectedItem().toString());
                txtViewInput_1.setText(spinner1.getSelectedItem().toString());

                txtViewResult.setText(txtViewInput.getText().toString() + " " + txtViewInputCurrency.getText().toString() +
                        " = " + txtViewOutput.getText().toString() + " " + txtViewOutputCurrency.getText().toString());

                String title = "XE Currency Converter: ";
                String title1 = txtViewInput.getText().toString();
                String title2 = txtViewInputCurrency.getText().toString();
                String title3 = txtViewOutputCurrency.getText().toString();
                String title4 = txtViewOutput.getText().toString();
                txtViewTitle.setText(title + title1 + " " + title2 + "\n" + "to " + title3
                        + " = " + title4 + " " + title3);
            }
        });
    }

    private BigDecimal converter(String input, String spinner1, String spinner2) {
        double a = Double.parseDouble(input);

        if (spinner1.equals("VND (Việt Nam)")) {
            switch (spinner2) {
                case "USD (Mỹ)":
                    return new BigDecimal(a / 23000);
                case "THB (THAILAND BAHT)":
                    return new BigDecimal(a / 0.653);
                case "SGD (SINGAPORE DOLLAR)":
                    return new BigDecimal(a / 16.284);
                case "YEN (Japan)":
                    return new BigDecimal(a / 0.205);
                case "KRW (KOREAN WON)":
                    return new BigDecimal(a / 0.167);
            }
        } else {
            switch (spinner1) {
                case "USD (Mỹ)":
                    return new BigDecimal (a * 23000);
                case "THB (THAILAND BAHT)":
                    return new BigDecimal(a * 0.653);
                case "SGD (SINGAPORE DOLLAR)":
                    return new BigDecimal(a * 16.284);
                case "YEN (Japan)":
                    return new BigDecimal(a / 0.205);
                case "KRW (KOREAN WON)":
                    return new BigDecimal(a * 0.167);
            }
        }
        return new BigDecimal(0);
    }

    private void imgBtnSwitch_SetOnClickListener() {
        imgBtnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int positionSpinner1 = spinner1.getSelectedItemPosition();
                int positionSpinner2 = spinner2.getSelectedItemPosition();
                if (spinner1.getAdapter().equals(adapter1)) {
                    spinner1.setAdapter(adapter2);
                    spinner2.setAdapter(adapter1);
                } else {
                    spinner1.setAdapter(adapter1);
                    spinner2.setAdapter(adapter2);
                }
                spinner1.setSelection(positionSpinner2);
                spinner2.setSelection(positionSpinner1);
            }
        });
    }

    private void initSpinner2() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter2 = ArrayAdapter.createFromResource(this,
                R.array.curency, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);
    }

    private void initSpinner1() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter1 = ArrayAdapter.createFromResource(this,
                R.array.curency1, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter1);
    }

    private BigDecimal scaleValue(BigDecimal bigDecimal)
    {
        if(bigDecimal.intValue() >= 10000)
        {
            return bigDecimal.setScale(0, RoundingMode.CEILING);
        }
        return bigDecimal.setScale(5, RoundingMode.CEILING);
    }

    private void anhXa() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);

        imgBtnSwitch = (ImageButton) findViewById(R.id.imgBtnSwitch);

        btnConvert = (Button) findViewById(R.id.btnConvert);

        editTxtInput = (EditText) findViewById(R.id.editTxtInput);

        txtViewInput = (TextView) findViewById(R.id.txtViewInput);
        txtViewOutput = (TextView) findViewById(R.id.txtViewOutput);
        txtViewInputCurrency = (TextView) findViewById(R.id.txtViewInputCurrency);
        txtViewOutputCurrency = (TextView) findViewById(R.id.txtViewOutputCurrency);

        txtViewOutput_1 = (TextView) findViewById(R.id.txtViewOutput_1);
        txtViewInput_1 = (TextView) findViewById(R.id.txtViewInput_1);
        txtViewResult = (TextView) findViewById(R.id.txtViewResult);

        txtView_1 = (TextView) findViewById(R.id.txtView_1);
        txtView_2 = (TextView) findViewById(R.id.txtView_2);

        txtViewTitle = (TextView) findViewById(R.id.txtViewTitle);
    }
}