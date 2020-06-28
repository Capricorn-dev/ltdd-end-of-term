package com.example.demo2062020_chisobmi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTxtChieuCao, editTxtViewCanNang;

    RadioButton rdoBtnNam, rdoBtnNu;
    Button btnTinhToan;

    TextView txtViewSoBMI, txtViewKetQua, txtViewNhanXet;

    Double bmi = 0.0;
    Double chieuCao = 0.0,
            canNang = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mapping();

        btnTinhToan_SetOnClickListener();
    }

    private void btnTinhToan_SetOnClickListener() {
        btnTinhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTxtChieuCao.getText().toString().isEmpty() ||
                        editTxtViewCanNang.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.txtAlert,
                            Toast.LENGTH_SHORT).show();
                    txtViewNhanXet.setText(R.string.txtAlert);
                    txtViewNhanXet.setTextColor(Color.RED);
                } else {
                    chieuCao =  Double.parseDouble(editTxtChieuCao.getText().toString()) / 100;
                    canNang =  Double.parseDouble(editTxtViewCanNang.getText().toString());


                    long temp = (long) tinhBMI(canNang, chieuCao);
                    long temp1 = Math.round(temp);

                    txtViewSoBMI.setText(String.valueOf(temp1));

                    nhanXetVeBMI(temp1);
                }

            }
        });
    }

    public String nhanXetVeBMI(long bmi) {
        if (rdoBtnNam.isChecked()) {

            if (bmi == 40.0) {
                txtViewNhanXet.setText(R.string.ad4);
                txtViewNhanXet.setTextColor(Color.RED);
                txtViewKetQua.setText(R.string.wei6);
            } else if (bmi >= 30.0 && bmi <= 39.9) {
                txtViewNhanXet.setText(R.string.ad3);
                txtViewNhanXet.setTextColor(Color.RED);
                txtViewKetQua.setText(R.string.wei5);
            } else if (bmi >= 25.0 && bmi <= 29.9) {
                txtViewNhanXet.setText(R.string.ad2);
                txtViewNhanXet.setTextColor(Color.RED);
                txtViewKetQua.setText(R.string.wei4);
            } else if (bmi >= 23.0 && bmi <= 24.9) {
                txtViewNhanXet.setText(R.string.ad2);
                txtViewNhanXet.setTextColor(Color.RED);
                txtViewKetQua.setText(R.string.wei3);
            } else if (bmi == 23.0) {
                txtViewNhanXet.setText(R.string.ad2);
                txtViewNhanXet.setTextColor(Color.RED);
                txtViewKetQua.setText(R.string.wei2);
            } else if (bmi >= 18.5 && bmi <= 22.9) {
                txtViewNhanXet.setText(R.string.ad1);
                txtViewNhanXet.setTextColor(Color.BLUE);
                txtViewKetQua.setText(R.string.wei1);
            } else if (bmi <= 18.5) {
                txtViewNhanXet.setText(R.string.ad0);
                txtViewNhanXet.setTextColor(Color.RED);
                txtViewKetQua.setText(R.string.wei0);
            }

        } else if (rdoBtnNu.isChecked()) {
            if (bmi == 40.0) {
                txtViewNhanXet.setText(R.string.ad5);
                txtViewNhanXet.setTextColor(Color.RED);
                txtViewKetQua.setText(R.string.wei6);
            } else if (bmi >= 35.0 && bmi <= 39.9) {
                txtViewNhanXet.setText(R.string.ad5);
                txtViewNhanXet.setTextColor(Color.RED);
                txtViewKetQua.setText(R.string.wei5);
            } else if (bmi >= 30.0 && bmi <= 34.9) {
                txtViewNhanXet.setText(R.string.ad5);
                txtViewNhanXet.setTextColor(Color.RED);
                txtViewKetQua.setText(R.string.wei4);
            } else if (bmi >= 25.0 && bmi <= 29.9) {
                txtViewNhanXet.setText(R.string.ad5);
                txtViewNhanXet.setTextColor(Color.RED);
                txtViewKetQua.setText(R.string.wei3);
            } else if (bmi == 25.0) {
                txtViewNhanXet.setText(R.string.ad5);
                txtViewNhanXet.setTextColor(Color.RED);
                txtViewKetQua.setText(R.string.wei2);
            } else if (bmi >= 18.5 && bmi <= 24.9) {
                txtViewNhanXet.setText(R.string.wei1);
                txtViewNhanXet.setTextColor(Color.BLUE);
                txtViewKetQua.setText(R.string.wei1);
            } else if (bmi <= 18.5) {
                txtViewNhanXet.setText(R.string.ad5);
                txtViewNhanXet.setTextColor(Color.RED);
                txtViewKetQua.setText(R.string.wei0);
            }
        }
        return null;
    }

    public double tinhBMI(double canNang, double chieuCao) {

        bmi = canNang / (chieuCao * chieuCao);
        return bmi;
    }

    private void mapping() {
        editTxtChieuCao = (EditText) findViewById(R.id.editTxtChieuCao);
        editTxtViewCanNang = (EditText) findViewById(R.id.editTxtViewCanNang);

        rdoBtnNam = (RadioButton) findViewById(R.id.rdoBtnNam);
        rdoBtnNu = (RadioButton) findViewById(R.id.rdoBtnNu);
        btnTinhToan = (Button) findViewById(R.id.btnTinhToan);

        txtViewSoBMI = (TextView) findViewById(R.id.txtViewSoBMI);
        txtViewKetQua = (TextView) findViewById(R.id.txtViewKetQua);
        txtViewNhanXet = (TextView) findViewById(R.id.txtViewNhanXet);
    }
}