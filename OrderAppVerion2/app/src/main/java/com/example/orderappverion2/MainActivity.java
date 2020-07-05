package com.example.orderappverion2;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Context context = this;

    RadioGroup radioBtnGrBanhMi;
    RadioButton banh_mi_checbox_opLa, banh_mi_checbox_phoMaiThitNguoi, banh_mi_checbox_chaCa;
    Button banhMi_btn_giam_mon, banhMi_btn_tang_mon;
    TextView banhMi_txtView_soLuong;

    CheckBox Hamburger_checbox_moreCheese, Hamburger_checbox_moreMeat, Hamburger_checbox_moreEggs;
    Button Hamburger_btn_giam_mon, Hamburger_btn_tang_mon;
    TextView Hamburger_txtView_soLuong;

    TextView txtViewGiamGia, txtViewTongTien;

    EditText editTxtMaGiamGia;

    Button btn_dat_hang, btn_lam_lai;

    //biến số lượng của pizza
    int amount_banhMi = 0;
    //biến số lượng của hamburger
    int amount_hamburger = 0;

    //giá tiền của banh mi
    double opLa_price = 25000;
    double phoMaiThitNguoi_price = 50000;
    double chaCa_price = 30000;

    //giá tiền của hamburger
    double hamburger_price = 45000;
    double hamburger_moreCheese_price = 15000;
    double hamburger_moreMeat_price = 35000;
    double hamburger_moreEggs_price = 25000;

    //Mã giảm giá
    double abc = 0.2;
    double xyz = 0.1;

    //Tổng tiền
    double tongTien = 0;
    double tongTienTheoAmountHamburger = 0;
    double tongTienTheoAmountBanhMi = 0;

    boolean dontExtraMeat = false;
    boolean dontExtraEggs = false;
    boolean dontExtraCheese = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        hamburger_setOnClickListener();
        banhMi_setOnClickListener();

        //      HAMBURGER ẤN CHỌN
        hamburger_checbox_moreCheese_setOnCheckedChangeListener();
        hamburger_checbox_moreMeat_setOnCheckedChangeListener();
        hamburger_checbox_moreEggs_setOnCheckedChangeListener();
        
        //      BANHMI ẤN CHỌN
        banhMi_radioBtn_opLa_setOnCheckedChangeListener();
        banhMi_radioBtn_phoMaiThitNguoi_setOnCheckedChangeListener();
        banhMi_radioBtn_chaCa_setOnCheckedChangeListener();

        //      MÃ GIẢM GIÁ
        editTxtMaGiamGia_addTextChangedListener();

        //      NÚT LÀM LẠI
        btn_lam_lai_setOnClickListener();

        //      NÚT ĐẶT HÀNG
        btn_dat_hang_setOnClickListener();
    }

    private void banhMi_setOnClickListener() {
        banhMi_btn_giam_mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount_banhMi > 0) {
                    amount_banhMi--;
                    if (amount_banhMi == 0) {
                        reduceMoneyAfterResetBanhMiRadioBtn();
                        resetBanhMiRadioBtn();
                    }
                    banhMi_txtView_soLuong.setText(String.valueOf(amount_hamburger));

                    if (banh_mi_checbox_opLa.isChecked()) {
                        tongTien -= opLa_price;
                    } else if (banh_mi_checbox_phoMaiThitNguoi.isChecked()) {
                        tongTien -= phoMaiThitNguoi_price;
                    } else if (banh_mi_checbox_chaCa.isChecked()) {
                        tongTien -= chaCa_price;
                    }

                }

                txtViewTongTien.setText(String.format("%1$,.0f",tongTien));
                Log.d("amount_banhMi", String.valueOf(amount_banhMi));
            }
        });

        banhMi_btn_tang_mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount_banhMi++;
                banhMi_txtView_soLuong.setText(String.valueOf(amount_banhMi));

                if (banh_mi_checbox_opLa.isChecked()) {
                    tongTien += opLa_price;
                } else if (banh_mi_checbox_phoMaiThitNguoi.isChecked()) {
                    tongTien += phoMaiThitNguoi_price;
                } else if (banh_mi_checbox_chaCa.isChecked()) {
                    tongTien += chaCa_price;
                }

                txtViewTongTien.setText(String.format("%1$,.0f",tongTien));
                Log.d("amount_banhMi", String.valueOf(amount_banhMi));
            }
        });
    }

    private void banhMi_radioBtn_chaCa_setOnCheckedChangeListener() {
        banh_mi_checbox_chaCa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!banhMi_txtView_soLuong.getText().equals("0") && isChecked == true) {
                    tongTien += chaCa_price;
                    txtViewTongTien.setText(String.format("%1$,.0f",tongTien));
                } else if (!banhMi_txtView_soLuong.getText().equals("0") && isChecked == false) {
                    tongTien -= chaCa_price;
                    txtViewTongTien.setText(String.format("%1$,.0f",tongTien));
                }
            }
        });
    }

    private void banhMi_radioBtn_phoMaiThitNguoi_setOnCheckedChangeListener() {
        banh_mi_checbox_phoMaiThitNguoi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!banhMi_txtView_soLuong.getText().equals("0") && isChecked == true) {
                    tongTien += phoMaiThitNguoi_price;
                    txtViewTongTien.setText(String.format("%1$,.0f",tongTien));
                } else if (!banhMi_txtView_soLuong.getText().equals("0") && isChecked == false) {
                    tongTien -= phoMaiThitNguoi_price;
                    txtViewTongTien.setText(String.format("%1$,.0f",tongTien));
                }
            }
        });
    }

    private void banhMi_radioBtn_opLa_setOnCheckedChangeListener() {
        banh_mi_checbox_opLa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!banhMi_txtView_soLuong.getText().equals("0") && isChecked == true) {
                    tongTien += opLa_price;
                    txtViewTongTien.setText(String.format("%1$,.0f",tongTien));
                } else if (!banhMi_txtView_soLuong.getText().equals("0") && isChecked == false) {
                    tongTien -= opLa_price;
                    txtViewTongTien.setText(String.format("%1$,.0f",tongTien));
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

                txtViewTongTien.setText(String.format("%1$,.0f",tongTien));
                Log.d("amount_hamburger", String.valueOf(amount_hamburger));
            }
        });

        Hamburger_btn_tang_mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tongTien += hamburger_price;

                txtViewTongTien.setText(String.format("%1$,.0f",tongTien));

                amount_hamburger++;
                Hamburger_txtView_soLuong.setText(String.valueOf(amount_hamburger));
                Log.d("amount_hamburger", String.valueOf(amount_hamburger));
            }
        });
    }

    private void resetBanhMiRadioBtn() {
        banh_mi_checbox_chaCa.setChecked(false);
        banh_mi_checbox_phoMaiThitNguoi.setChecked(false);
        banh_mi_checbox_opLa.setChecked(false);
    }

    private void reduceMoneyAfterResetBanhMiRadioBtn() {
        if (banh_mi_checbox_chaCa.isChecked())
            tongTien -= chaCa_price;
        if (banh_mi_checbox_phoMaiThitNguoi.isChecked())
            tongTien -= phoMaiThitNguoi_price;
        if (banh_mi_checbox_opLa.isChecked())
            tongTien -= opLa_price;
        txtViewTongTien.setText(String.format("%1$,.0f",tongTien));
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
        txtViewTongTien.setText(String.format("%1$,.0f",tongTien));
    }

    private void btn_dat_hang_setOnClickListener() {
        btn_dat_hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount_banhMi != 0 || amount_hamburger != 0) {

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
                amount_banhMi = 0;
                //biến số lượng của hamburger
                amount_hamburger = 0;

                //Tổng tiền
                tongTien = 0;
                tongTienTheoAmountHamburger = 0;
                tongTienTheoAmountBanhMi = 0;

                banhMi_txtView_soLuong.setText("0");
                Hamburger_txtView_soLuong.setText("0");

                txtViewTongTien.setText(String.valueOf(tongTien));
                txtViewGiamGia.setText("0.000");

                editTxtMaGiamGia.setText("");
            }
        });
    }

    boolean abc_isChecked = false;
    boolean xyz_isChecked = false;

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
                double d1 = tongTien *  abc;
                double d2 = tongTien *  xyz;
                Log.d("d1 =", String.valueOf(d1));
                Log.d("d2 =", String.valueOf(d2));
                if (tongTien != 0) {
                    if (editTxtMaGiamGia.getText().toString().equals("ABC")) {
                        txtViewGiamGia.setText(String.format("%1$,.0f", d1));

                        tongTien -= d1;
                        txtViewTongTien.setText(String.format("%1$,.0f", tongTien));

                        abc_isChecked = true;
                    } else if (editTxtMaGiamGia.getText().toString().equals("XYZ")) {
                        txtViewGiamGia.setText(String.format("%1$,.0f", d2));


                        tongTien -= d2;
                        txtViewTongTien.setText(String.format("%1$,.0f", tongTien));

                        xyz_isChecked = true;
                    } else if (!editTxtMaGiamGia.getText().toString().equals("ABC")
                            || !editTxtMaGiamGia.getText().toString().equals("XYZ")){
                        if (abc_isChecked) {
                            Log.d("d1 =", String.valueOf(d1));
                            tongTien += d1;

                            abc_isChecked = false;
                        } else if (xyz_isChecked) {
                            Log.d("d2 =", String.valueOf(d2));
                            tongTien += d2;

                            xyz_isChecked = false;
                        }

                        txtViewTongTien.setText(String.format("%1$,.0f", tongTien));

                        d1 = 0;
                        txtViewGiamGia.setText(String.format("%1$,.0f", d1));
                    }

                }
            }
        });
    }

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
                txtViewTongTien.setText(String.format("%1$,.0f", tongTien));
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
                txtViewTongTien.setText(String.format("%1$,.0f", tongTien));
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
                txtViewTongTien.setText(String.format("%1$,.0f", tongTien));
            }
        });
    }

    private void anhXa() {
        //ÁNH XẠ BANH MI
        radioBtnGrBanhMi = (RadioGroup) findViewById(R.id.radioBtnGrBanhMi);
        banh_mi_checbox_opLa = (RadioButton) findViewById(R.id.banh_mi_checbox_opLa);
        banh_mi_checbox_phoMaiThitNguoi = (RadioButton) findViewById(R.id.banh_mi_checbox_phoMaiThitNguoi);
        banh_mi_checbox_chaCa = (RadioButton) findViewById(R.id.banh_mi_checbox_chaCa);

        banhMi_btn_tang_mon = (Button) findViewById(R.id.banhMi_btn_tang_mon);
        banhMi_btn_giam_mon = (Button) findViewById(R.id.banhMi_btn_giam_mon);

        banhMi_txtView_soLuong = (TextView) findViewById(R.id.banhMi_txtView_soLuong);

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