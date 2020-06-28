package com.example.demo662020;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Context context = this;
    CheckBox Pizza_checbox_moreCheese, Pizza_checbox_doubleCheese, Pizza_checbox_trippleCheese;
    RadioButton Pizza_radioBtn_deMong, Pizza_radioBtn_deDay, Pizza_radioBtn_deTruyenThong,
            Pizza_radioBtn_vienPhoMai, Pizza_radioBtn_vienXucXich;
    Button Pizza_btn_giam_mon, Pizza_btn_tang_mon;
    TextView Pizza_txtView_soLuong;

    CheckBox Hamburger_checbox_moreCheese, Hamburger_checbox_moreMeat, Hamburger_checbox_moreEggs;
    Button Hamburger_btn_giam_mon, Hamburger_btn_tang_mon;
    TextView Hamburger_txtView_soLuong;

    TextView txtViewGiamGia, txtViewTongTien;

    EditText editTxtMaGiamGia;

    Button btn_dat_hang, btn_lam_lai;

    //biến số lượng của pizza
    int amount_pizza = 0;
    //biến số lượng của hamburger
    int amount_hamburger = 0;

    //giá tiền của pizza
    double pizza_price = 150.000;
    double pizza_moreCheese_price = 10.000;
    double pizza_deMong_price = 5.000;
    double pizza_deDay_price = 8.000;
    double pizza_deTruyenThong_price = 12.000;

    //giá tiền của hamburger
    double hamburger_price = 45.000;
    double hamburger_moreCheese_price = 15.000;
    double hamburger_moreMeat_price = 35.000;
    double hamburger_moreEggs_price = 25.000;

    //Mã giảm giá
    double abc = 0.2;
    double xyz = 0.1;

    //Tổng tiền
    double tongTien = 0;
    double tongTienTheoAmountHamburger = 0;
    double tongTienTheoAmountPizza = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        pizza_setOnClickListener();
        hamburger_setOnClickListener();

        //      PIZZA ẤN CHỌN
        pizza_checbox_moreCheese_setOnCheckedChangeListener();
        pizza_checbox_doubleCheeses_etOnCheckedChangeListener();
        pizza_checbox_trippleCheeses_etOnCheckedChangeListener();

        pizza_radioBtn_deMong_setOnCheckedChangeListener();
        pizza_radioBtn_deDay_setOnCheckedChangeListener();
        pizza_radioBtn_deTruyenThong_setOnCheckedChangeListener();

        //      HAMBURGER ẤN CHỌN
        hamburger_checbox_moreCheese_setOnCheckedChangeListener();
        hamburger_checbox_moreMeat_setOnCheckedChangeListener();
        hamburger_checbox_moreEggs_setOnCheckedChangeListener();

        //      MÃ GIẢM GIÁ
        editTxtMaGiamGia_addTextChangedListener();

        //      NÚT LÀM LẠI
        btn_lam_lai_setOnClickListener();

        //      NÚT ĐẶT HÀNG
        btn_dat_hang_setOnClickListener();
    }

    private void btn_dat_hang_setOnClickListener() {
        btn_dat_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount_pizza != 0 || amount_hamburger != 0) {

                    Intent intent = new Intent(MainActivity.this, SuccessActivity.class);

                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "BẠN CHƯA CHỌN SỐ LƯỢNG !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void btn_lam_lai_setOnClickListener() {
        btn_lam_lai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //biến số lượng của pizza
                amount_pizza = 0;
                //biến số lượng của hamburger
                amount_hamburger = 0;

                //Tổng tiền
                tongTien = 0;
                tongTienTheoAmountHamburger = 0;
                tongTienTheoAmountPizza = 0;

                Pizza_txtView_soLuong.setText("0");
                Hamburger_txtView_soLuong.setText("0");

                txtViewTongTien.setText(String.valueOf(tongTien));
                txtViewGiamGia.setText("0.000");
            }
        });
    }

    private void editTxtMaGiamGia_addTextChangedListener() {
        editTxtMaGiamGia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editTxtMaGiamGia.getText().toString().equals("abc")) {
                    txtViewGiamGia.setText(String.valueOf(tongTien * (1 - abc)));
                } else if (editTxtMaGiamGia.getText().toString().equals("xyz")) {
                    txtViewGiamGia.setText(String.valueOf(tongTien * (1 - xyz)));
                }
            }
        });
    }

    private void resetHamCheckBox() {
        Hamburger_checbox_moreMeat.setChecked(false);
        Hamburger_checbox_moreEggs.setChecked(false);
        Hamburger_checbox_moreCheese.setChecked(false);

    }

    private void reduceMoneyAfterResetHamCheckBox() {
        if (Hamburger_checbox_moreMeat.isChecked())
            tongTien -= hamburger_moreMeat_price;
        if (Hamburger_checbox_moreEggs.isChecked())
            tongTien -= hamburger_moreEggs_price;
        if (Hamburger_checbox_moreCheese.isChecked())
            tongTien -= hamburger_moreCheese_price;
        txtViewTongTien.setText(String.valueOf(tongTien));
    }

    boolean dontExtraMeat = false;
    boolean dontExtraEggs = false;
    boolean dontExtraCheese = false;

    private void hamburger_checbox_moreEggs_setOnCheckedChangeListener() {
        Hamburger_checbox_moreEggs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!dontExtraEggs) {
                        tongTien += hamburger_moreEggs_price;
                        dontExtraEggs = true;
                    }
                } else {
                    if (dontExtraEggs) {
                        tongTien -= hamburger_moreEggs_price;
                        dontExtraEggs = false;
                    }
                }
                txtViewTongTien.setText(String.valueOf(tongTien));
            }
        });
    }

    private void hamburger_checbox_moreMeat_setOnCheckedChangeListener() {
        Hamburger_checbox_moreMeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!dontExtraMeat) {
                        tongTien += hamburger_moreMeat_price;
                        dontExtraMeat = true;
                    }
                } else {
                    if (dontExtraMeat) {
                        tongTien -= hamburger_moreMeat_price;
                        dontExtraMeat = false;
                    }
                }
                txtViewTongTien.setText(String.valueOf(tongTien));
            }
        });
    }

    private void hamburger_checbox_moreCheese_setOnCheckedChangeListener() {
        Hamburger_checbox_moreCheese.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!dontExtraCheese) {
                        tongTien += hamburger_moreCheese_price;
                        dontExtraCheese = true;
                    }
                } else {
                    if (dontExtraCheese) {
                        tongTien -= hamburger_moreCheese_price;
                        dontExtraCheese = false;
                    }
                }
                txtViewTongTien.setText(String.valueOf(tongTien));
            }
        });
    }

    private void pizza_checbox_moreCheese_isChecked() {
        if (Pizza_checbox_moreCheese.isChecked()) {
            tongTien += pizza_moreCheese_price;
        }
    }

    private void pizza_checbox_doubleCheese_isChecked() {
        if (Pizza_checbox_doubleCheese.isChecked()) {
            tongTien += pizza_moreCheese_price * 2;
        }
    }

    private void pizza_checbox_trippleCheese_isChecked() {
        if (Pizza_checbox_trippleCheese.isChecked()) {
            tongTien += pizza_moreCheese_price * 3;
        }
    }

    private void pizza_radioBtn_deTruyenThong_isChecked() {
        if (Pizza_radioBtn_deTruyenThong.isChecked()) {
            tongTien += pizza_deTruyenThong_price;
        }
    }

    private void pizza_radioBtn_deDay_isChecked() {
        if (Pizza_radioBtn_deDay.isChecked()) {
            tongTien += pizza_deDay_price;
        }
    }

    private void pizza_radioBtn_deMong_isChecked() {
        if (Pizza_radioBtn_deMong.isChecked()) {
            tongTien += pizza_deMong_price;
        }
    }

    private void pizza_radioBtn_deTruyenThong_setOnCheckedChangeListener() {
        Pizza_radioBtn_deTruyenThong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!Pizza_txtView_soLuong.getText().equals("0") && isChecked == true) {
                    tongTien += pizza_deTruyenThong_price;
                    txtViewTongTien.setText(String.valueOf(tongTien));
                } else if (!Pizza_txtView_soLuong.getText().equals("0") && isChecked == false) {
                    tongTien -= pizza_deTruyenThong_price;
                    txtViewTongTien.setText(String.valueOf(tongTien));
                }
            }
        });
    }

    private void pizza_radioBtn_deDay_setOnCheckedChangeListener() {
        Pizza_radioBtn_deDay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!Pizza_txtView_soLuong.getText().equals("0") && isChecked == true) {
                    tongTien += pizza_deDay_price;
                    txtViewTongTien.setText(String.valueOf(tongTien));
                } else if (!Pizza_txtView_soLuong.getText().equals("0") && isChecked == false) {
                    tongTien -= pizza_deDay_price;
                    txtViewTongTien.setText(String.valueOf(tongTien));
                }
            }
        });
    }

    private void pizza_radioBtn_deMong_setOnCheckedChangeListener() {
        Pizza_radioBtn_deMong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!Pizza_txtView_soLuong.getText().equals("0") && isChecked == true) {
                    tongTien += pizza_deMong_price;
                    txtViewTongTien.setText(String.valueOf(tongTien));
                } else if (!Pizza_txtView_soLuong.getText().equals("0") && isChecked == false) {
                    tongTien -= pizza_deMong_price;
                    txtViewTongTien.setText(String.valueOf(tongTien));
                }
            }
        });
    }

    private void pizza_checbox_trippleCheeses_etOnCheckedChangeListener() {
        Pizza_checbox_trippleCheese.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!Pizza_txtView_soLuong.getText().equals("0") && isChecked == true) {
                    tongTien += pizza_moreCheese_price * 3;
                    txtViewTongTien.setText(String.valueOf(tongTien));
                } else if (!Pizza_txtView_soLuong.getText().equals("0") && isChecked == false) {
                    tongTien -= pizza_moreCheese_price * 3;
                    txtViewTongTien.setText(String.valueOf(tongTien));
                }
            }
        });
    }

    private void pizza_checbox_doubleCheeses_etOnCheckedChangeListener() {
        Pizza_checbox_doubleCheese.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!Pizza_txtView_soLuong.getText().equals("0") && isChecked == true) {
                    tongTien += pizza_moreCheese_price * 2;
                    txtViewTongTien.setText(String.valueOf(tongTien));
                } else if (!Pizza_txtView_soLuong.getText().equals("0") && isChecked == false) {
                    tongTien -= pizza_moreCheese_price * 2;
                    txtViewTongTien.setText(String.valueOf(tongTien));
                }
            }
        });
    }

    private void pizza_checbox_moreCheese_setOnCheckedChangeListener() {
        Pizza_checbox_moreCheese.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!Pizza_txtView_soLuong.getText().equals("0") && isChecked == true) {
                    tongTien += pizza_moreCheese_price;
                    txtViewTongTien.setText(String.valueOf(tongTien));
                } else if (!Pizza_txtView_soLuong.getText().equals("0") && isChecked == false) {
                    tongTien -= pizza_moreCheese_price;
                    txtViewTongTien.setText(String.valueOf(tongTien));
                }
            }
        });
    }

    private void hamburger_setOnClickListener() {
        Hamburger_btn_giam_mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount_hamburger > 0) {
                    amount_hamburger--;
                    if (amount_hamburger == 0) {
                        resetHamCheckBox();
                        reduceMoneyAfterResetHamCheckBox();
                    }
                    Hamburger_txtView_soLuong.setText(String.valueOf(amount_hamburger));

                    tongTien -= hamburger_price;
                }

                txtViewTongTien.setText(String.valueOf(tongTien));
                Log.d("amount_hamburger", String.valueOf(amount_hamburger));
            }
        });

        Hamburger_btn_tang_mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tongTien += hamburger_price;

                txtViewTongTien.setText(String.valueOf(tongTien));

                amount_hamburger++;
                Hamburger_txtView_soLuong.setText(String.valueOf(amount_hamburger));
                Log.d("amount_hamburger", String.valueOf(amount_hamburger));
            }
        });

    }

    private void pizza_setOnClickListener() {
        Pizza_btn_giam_mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount_pizza > 0) {
                    amount_pizza--;
                    Pizza_txtView_soLuong.setText(String.valueOf(amount_pizza));

                    tongTien -= pizza_price;
                }

                txtViewTongTien.setText(String.valueOf(tongTien));
            }
        });

        Pizza_btn_tang_mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount_pizza++;
                Pizza_txtView_soLuong.setText(String.valueOf(amount_pizza));

                tongTienTheoAmountPizza = pizza_price * amount_pizza;
                tongTien += tongTienTheoAmountPizza;

                pizza_radioBtn_deDay_isChecked();
                pizza_radioBtn_deMong_isChecked();
                pizza_radioBtn_deTruyenThong_isChecked();

                pizza_checbox_moreCheese_isChecked();
                pizza_checbox_doubleCheese_isChecked();
                pizza_checbox_trippleCheese_isChecked();

                txtViewTongTien.setText(String.valueOf(tongTien));
            }
        });
    }

    private void anhXa() {
        //ÁNH XẠ PIZZA
        Pizza_checbox_moreCheese = (CheckBox) findViewById(R.id.Pizza_checbox_moreCheese);
        Pizza_checbox_doubleCheese = (CheckBox) findViewById(R.id.Pizza_checbox_doubleCheese);
        Pizza_checbox_trippleCheese = (CheckBox) findViewById(R.id.Pizza_checbox_trippleCheese);

        Pizza_radioBtn_deMong = (RadioButton) findViewById(R.id.Pizza_radioBtn_deMong);
        Pizza_radioBtn_deDay = (RadioButton) findViewById(R.id.Pizza_radioBtn_deDay);
        Pizza_radioBtn_deTruyenThong = (RadioButton) findViewById(R.id.Pizza_radioBtn_deTruyenThong);
        Pizza_radioBtn_vienPhoMai = (RadioButton) findViewById(R.id.Pizza_radioBtn_vienPhoMai);
        Pizza_radioBtn_vienXucXich = (RadioButton) findViewById(R.id.Pizza_radioBtn_vienXucXich);

        Pizza_btn_giam_mon = (Button) findViewById(R.id.Pizza_btn_giam_mon);
        Pizza_btn_tang_mon = (Button) findViewById(R.id.Pizza_btn_tang_mon);

        Pizza_txtView_soLuong = (TextView) findViewById(R.id.Pizza_txtView_soLuong);

        //ÁNH XẠ HAMBURGER
        Hamburger_checbox_moreCheese = (CheckBox) findViewById(R.id.Hamburger_checbox_moreCheese);
        Hamburger_checbox_moreMeat = (CheckBox) findViewById(R.id.Hamburger_checbox_moreMeat);
        Hamburger_checbox_moreEggs = (CheckBox) findViewById(R.id.Hamburger_checbox_moreEggs);

        Hamburger_btn_giam_mon = (Button) findViewById(R.id.Hamburger_btn_giam_mon);
        Hamburger_btn_tang_mon = (Button) findViewById(R.id.Hamburger_btn_tang_mon);

        Hamburger_txtView_soLuong = (TextView) findViewById(R.id.Hamburger_txtView_soLuong);

        //Giảm giá và tổng tiền
        txtViewGiamGia = (TextView) findViewById(R.id.txtViewGiamGia);
        txtViewTongTien = (TextView) findViewById(R.id.txtViewTongTien);

        //Mã giảm giá
        editTxtMaGiamGia = (EditText) findViewById(R.id.editTxtMaGiamGia);

        btn_lam_lai = (Button) findViewById(R.id.btn_lam_lai);
        btn_dat_hang = (Button) findViewById(R.id.btn_dat_hang);
    }
}
