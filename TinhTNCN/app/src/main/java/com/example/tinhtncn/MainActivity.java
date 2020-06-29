package com.example.tinhtncn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    Context context = this;
    EditText incomeEditTxt;
    EditText numberPeopleDependEditTxt;
    Spinner monthSpin;
    EditText yearEditTxt;
    Button calculateTaxBtn;
    TextView incomeAfterTaxTxt;
    TextView taxTxt;
    int month = 0; //Default

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ánh xạ
        incomeEditTxt = (EditText) findViewById(R.id.incomeEditTxt);
        numberPeopleDependEditTxt = (EditText) findViewById(R.id.numberPeopleDependEditTxt);
        monthSpin = (Spinner) findViewById(R.id.monthSpin);
        yearEditTxt = (EditText) findViewById(R.id.yearEditTxt);
        calculateTaxBtn = (Button) findViewById(R.id.calculateTaxBtn);
        incomeAfterTaxTxt = (TextView) findViewById(R.id.incomeAfterTaxTxt);
        taxTxt = (TextView) findViewById(R.id.taxTxt);

        monthSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        calculateTaxBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (incomeEditTxt.getText().toString().isEmpty() == true) {
                    Toast.makeText(context, "Không được để thu nhập trống.", Toast.LENGTH_SHORT).show();
                } else if (numberPeopleDependEditTxt.getText().toString().isEmpty() == true) {
                    Toast.makeText(context, "Không được để số người phụ thuộc trống.", Toast.LENGTH_SHORT).show();
                } else {
                    //Tính khấu trừ
                    int peopleDepend = Integer.parseInt(numberPeopleDependEditTxt.getText().toString());
                    int year = Integer.parseInt(yearEditTxt.getText().toString());
                    BigDecimal calculateDiscount = calculateDiscount(peopleDepend, month, year);
                    Log.d("discount", String.valueOf(calculateDiscount));

                    //Thu nhập sau khấu trừ
                    double income = Double.parseDouble(incomeEditTxt.getText().toString());
                    BigDecimal calculateIncomeIncludeTax = calculateIncomeIncludeTax(income, calculateDiscount.doubleValue());
                    if (calculateIncomeIncludeTax.doubleValue() > 0) {
                        incomeAfterTaxTxt.setText(String.valueOf(calculateIncomeIncludeTax));
                    } else {
                        incomeAfterTaxTxt.setText("0");
                    }
                    Log.d("calculateIncomeIncludeTax", String.valueOf(calculateIncomeIncludeTax));

                    //Thuế sau thu nhập đã tính thuế
                    BigDecimal calculateTax = calculateTax(calculateIncomeIncludeTax.doubleValue());
                    Log.d("calculateTax", String.valueOf(calculateTax));
                    if (calculateTax.doubleValue() > 0) {
                        taxTxt.setText(String.valueOf(calculateTax));
                    } else {
                        taxTxt.setText("0");
                    }
                }
            }
        });
    }

    private BigDecimal calculateTax(double calculateAfterTax) {
        BigDecimal res;
        if (calculateAfterTax <= 5000000) {
            res = new BigDecimal(calculateAfterTax * 0.05);
        } else if (calculateAfterTax > 5000000 && calculateAfterTax <= 10000000) {
            res = new BigDecimal(calculateAfterTax * 0.05 - 250000);
        } else if (calculateAfterTax > 10000000 && calculateAfterTax <= 18000000) {
            res = new BigDecimal(calculateAfterTax * 0.15 - 750000);
        } else if (calculateAfterTax > 18000000 && calculateAfterTax <= 32000000) {
            res = new BigDecimal(calculateAfterTax * 0.2 - 1650000);
        } else if (calculateAfterTax > 32000000 && calculateAfterTax <= 52000000) {
            res = new BigDecimal(calculateAfterTax * 0.25 - 3250000);
        } else if (calculateAfterTax > 52000000 && calculateAfterTax <= 80000000) {
            res = new BigDecimal(calculateAfterTax * 0.3 - 5850000);
        } else {
            res = new BigDecimal(calculateAfterTax * 0.35 - 9850000);
        }
        res.setScale(1);
        return res;
    }

    private BigDecimal calculateIncomeIncludeTax(double incomeIncludeTax, double discount) {
        return new BigDecimal(incomeIncludeTax - discount);
    }

    private BigDecimal calculateDiscount(int peopleDepend, int month, int year) {
        int selfDiscount = 0;
        int peopleDependDiscount = 0;
        if (year < 2020) {
            selfDiscount = 9000000;
            peopleDependDiscount = 3600000;
        }
        else if(year == 2020)
        {
            if(month < 7)
            {
                selfDiscount = 9000000;
                peopleDependDiscount = 3600000;
            }
            else
            {
                selfDiscount = 11000000;
                peopleDependDiscount = 4400000;
            }
        }
        else{
            selfDiscount = 11000000;
            peopleDependDiscount = 4400000;
        }
        return new BigDecimal(selfDiscount + (peopleDependDiscount * peopleDepend));
    }
}
