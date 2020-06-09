package com.example.datmonan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Các biến dùng chung
    private TextView total;
    private Button buttonGiamPizza;
    private Button buttoTangPizza;
    private Button buttonGiamHam;
    private Button buttonTangHam;
    private TextView pizzaAmount;
    private TextView hamAmount;
    private CheckBox oneCheese;
    private CheckBox twoCheese;
    private CheckBox threeCheese;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ánh xạ
        total = findViewById(R.id.total);
        buttonGiamPizza = findViewById(R.id.buttonGiamPizza);
        buttoTangPizza = findViewById(R.id.buttoTangPizza);
        buttonGiamHam = findViewById(R.id.buttonGiamHam);
        buttonTangHam = findViewById(R.id.buttonTangHam);
        pizzaAmount = findViewById(R.id.pizzaAmount);
        hamAmount = findViewById(R.id.hamAmount);
        oneCheese = findViewById(R.id.oneCheese);
        twoCheese = findViewById(R.id.twoCheese);
        threeCheese = findViewById(R.id.threeCheese);
        //Pizza
        buttonGiamPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(pizzaAmount.getText().toString());
                if(value > 0)
                {
                    value--;
                    pizzaAmount.setText(String.valueOf(value));
                }
            }
        });
        buttoTangPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(pizzaAmount.getText().toString());
                value++;
                pizzaAmount.setText(String.valueOf(value));
            }
        });
        final int pizzaPrice = 125;
        oneCheese.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(oneCheese.isChecked())
                {
                    total.setText(String.valueOf(pizzaPrice));
                }
            }
        });
        pizzaClicked(twoCheese, 2);
        pizzaClicked(threeCheese, 3);

        //Ham
        buttonGiamHam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(hamAmount.getText().toString());
                if(value > 0)
                {
                    value--;
                    hamAmount.setText(String.valueOf(value));
                }
            }
        });
        buttonTangHam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(hamAmount.getText().toString());
                value++;
                hamAmount.setText(String.valueOf(value));
            }
        });
    }
    //
    private void pizzaClicked(View v, int type) {
        int pizzaPrice = 125;
        if (((CheckBox) v).isChecked()) {
          switch (type)
          {
              case 2:
                  pizzaPrice += 10;
                  break;
              case 3:
                  pizzaPrice += 20;
          }
        }
        total.setText(String.valueOf(pizzaPrice));
    }
}
