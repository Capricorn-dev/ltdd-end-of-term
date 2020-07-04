package com.example.datmonan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Các biến dùng chung
    private TextView total;
    private Button buttonGiamBanhMi;
    private Button buttonTangBanhMi;
    private Button buttonGiamHam;
    private Button buttonTangHam;
    private TextView banhMiAmount;
    private TextView hamAmount;
    private RadioButton omeletteBreadRBtn;
    private RadioButton cheeseBreadRBtn;
    private RadioButton fishBallBreadRBtn;
    private RadioGroup banhMiRGroup;
    private CheckBox Hamburger_checkbox_moreCheese;
    private CheckBox Hamburger_checkbox_moreEggs;
    private CheckBox Hamburger_checkbox_moreMeat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ánh xạ
        total = findViewById(R.id.total);
        buttonGiamBanhMi = findViewById(R.id.buttonGiamBanhMi);
        buttonTangBanhMi = findViewById(R.id.buttonTangBanhMi);
        buttonGiamHam = findViewById(R.id.buttonGiamHam);
        buttonTangHam = findViewById(R.id.buttonTangHam);
        banhMiAmount = findViewById(R.id.banhMiAmount);
        hamAmount = findViewById(R.id.hamAmount);
        banhMiRGroup = findViewById(R.id.banhMiRGroup);
        total = findViewById(R.id.total);
        Hamburger_checkbox_moreCheese = findViewById(R.id.Hamburger_checkbox_moreCheese);
        Hamburger_checkbox_moreEggs = findViewById(R.id.Hamburger_checkbox_moreEggs);
        Hamburger_checkbox_moreMeat = findViewById(R.id.Hamburger_checkbox_moreMeat);
        //Bánh mì
        buttonGiamBanhMi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(banhMiAmount.getText().toString());
                if(value > 0)
                {
                    value--;
                    banhMiAmount.setText(String.valueOf(value));
                    total.setText(String.valueOf(value * checkRButtonAndReturnBanhMiPrice(banhMiRGroup)));
                }
            }
        });
        buttonTangBanhMi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(banhMiAmount.getText().toString());
                value++;
                banhMiAmount.setText(String.valueOf(value));
                total.setText(String.valueOf(value * checkRButtonAndReturnBanhMiPrice(banhMiRGroup)));
            }
        });
        banhMiRGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                double amount = Double.parseDouble(banhMiAmount.getText().toString());
                switch(checkedId)
                {
                    case R.id.omeletteBreadRBtn:
                        total.setText(String.format("%1$,.0f", amount * 25000));
                        break;
                    case R.id.cheeseBreadRBtn:
                        total.setText(String.format("%1$,.0f", amount * 50000));
                        break;
                    case R.id.fishBallBreadRBtn:
                        total.setText(String.format("%1$,.0f", amount * 30000));
                        break;
                }
            }
        });
        //Ham
        buttonGiamHam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(hamAmount.getText().toString());
                if(value > 0)
                {
                    value--;
                    if(value == 0)
                    {
                        resetHamCheckBox();
                        reduceMoneyAfterResetHamCheckBox();
                    }
                    hamAmount.setText(String.valueOf(value));
                    total.setText(String.format("%1$,.0f", hamPrice * value));
                }
            }
        });
        buttonTangHam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = Integer.parseInt(hamAmount.getText().toString());
                value++;
                hamAmount.setText(String.valueOf(value));
                total.setText(String.format("%1$,.0f", hamPrice * value));
            }
        });

        Hamburger_checkbox_moreEggs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!dontExtraEggs) {
                        hamTotal += hamburger_moreEggs_price;
                        dontExtraEggs = true;
                    }
                } else {
                    if (dontExtraEggs) {
                        hamTotal -= hamburger_moreEggs_price;
                        dontExtraEggs = false;
                    }
                }
                total.setText(String.valueOf(hamTotal));
            }
        });

        Hamburger_checkbox_moreMeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!dontExtraMeat) {
                        hamTotal += hamburger_moreMeat_price;
                        dontExtraMeat = true;
                    }
                } else {
                    if (dontExtraMeat) {
                        hamTotal -= hamburger_moreMeat_price;
                        dontExtraMeat = false;
                    }
                }
                total.setText(String.valueOf(hamTotal));
            }
        });

        Hamburger_checkbox_moreCheese.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!dontExtraCheese) {
                        hamTotal += hamburger_moreCheese_price;
                        dontExtraCheese = true;
                    }
                } else {
                    if (dontExtraCheese) {
                        hamTotal -= hamburger_moreCheese_price;
                        dontExtraCheese = false;
                    }
                }
                total.setText(String.valueOf(hamTotal));
            }
        });

    }
    //
    private int checkRButtonAndReturnBanhMiPrice(RadioGroup radioGroup)
    {
        int checked = radioGroup.getCheckedRadioButtonId();
        switch (checked)
        {
            case R.id.omeletteBreadRBtn:
                return 25000;
            case R.id.cheeseBreadRBtn:
                return 50000;
            case R.id.fishBallBreadRBtn:
                return 30000;
        }
        return 0;
    }
    int hamTotal = 0;
    double hamPrice = 45000;
    int hamburger_moreMeat_price = 35000;
    int hamburger_moreEggs_price = 25000;
    int hamburger_moreCheese_price = 15000;

    private void resetHamCheckBox() {
        Hamburger_checkbox_moreMeat.setChecked(false);
        Hamburger_checkbox_moreEggs.setChecked(false);
        Hamburger_checkbox_moreCheese.setChecked(false);
    }

    private void reduceMoneyAfterResetHamCheckBox() {
        if (Hamburger_checkbox_moreMeat.isChecked())
            hamTotal -= hamburger_moreMeat_price;
        if (Hamburger_checkbox_moreEggs.isChecked())
            hamTotal -= hamburger_moreEggs_price;
        if (Hamburger_checkbox_moreCheese.isChecked())
            hamTotal -= hamburger_moreCheese_price;
        total.setText(String.format("%1$,.0f",hamTotal));
    }

    boolean dontExtraMeat = false;
    boolean dontExtraEggs = false;
    boolean dontExtraCheese = false;

    private void hamburger_checbox_moreEggs_setOnCheckedChangeListener() {
        Hamburger_checkbox_moreEggs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!dontExtraEggs) {
                        hamTotal += hamburger_moreEggs_price;
                        dontExtraEggs = true;
                    }
                } else {
                    if (dontExtraEggs) {
                        hamTotal -= hamburger_moreEggs_price;
                        dontExtraEggs = false;
                    }
                }
                total.setText(String.valueOf(hamTotal));
            }
        });
    }

    private void hamburger_checbox_moreMeat_setOnCheckedChangeListener() {
        Hamburger_checkbox_moreMeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!dontExtraMeat) {
                        hamTotal += hamburger_moreMeat_price;
                        dontExtraMeat = true;
                    }
                } else {
                    if (dontExtraMeat) {
                        hamTotal -= hamburger_moreMeat_price;
                        dontExtraMeat = false;
                    }
                }
                total.setText(String.valueOf(hamTotal));
            }
        });
    }

    private void hamburger_checbox_moreCheese_setOnCheckedChangeListener() {
        Hamburger_checkbox_moreCheese.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!dontExtraCheese) {
                        hamTotal += hamburger_moreCheese_price;
                        dontExtraCheese = true;
                    }
                } else {
                    if (dontExtraCheese) {
                        hamTotal -= hamburger_moreCheese_price;
                        dontExtraCheese = false;
                    }
                }
                total.setText(String.valueOf(hamTotal));
            }
        });
    }

}
