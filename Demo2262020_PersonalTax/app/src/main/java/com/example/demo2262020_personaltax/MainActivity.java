package com.example.demo2262020_personaltax;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText    editTxtThuNhapTrongThang;
    EditText    editTxtSoNguoiPhuThuoc;

    Spinner     spinnerThang;
    Spinner     spinnerNam;

    Button      btnTinhToan;

    TextView    txtViewThuNhapTinhThue;
    TextView    txtViewTienThueTNCN;

    ArrayAdapter<CharSequence> adapter1, adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();

        btnTinhToan_SetOnClickListener();

        initSpinnerThang();
        initSpinnerNam();
    }

    private void btnTinhToan_SetOnClickListener() {
        btnTinhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTxtThuNhapTrongThang.getText().toString().isEmpty() == true || editTxtSoNguoiPhuThuoc.getText().toString().isEmpty() == true) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
                } else {
                    String thuNhapTrongThang = editTxtThuNhapTrongThang.getText().toString();
                    String soNguoiPhuThuoc = editTxtSoNguoiPhuThuoc.getText().toString();

                    String thang = spinnerThang.getSelectedItem().toString();
                    String nam = spinnerNam.getSelectedItem().toString();

                    double tienThuNhapTinhThue = thuNhapTinhThue(thuNhapTrongThang, thang, nam, soNguoiPhuThuoc);
                    if (tienThuNhapTinhThue > 0) {
                        txtViewThuNhapTinhThue.setText(String.format("%1$,.0f", tienThuNhapTinhThue));
                    } else {
                        txtViewThuNhapTinhThue.setText("0");
                    }
//                    txtViewThuNhapTinhThue.setText(String.format("%1$,.0f", tienThuNhapTinhThue));

                    double tienThueTNCN = thueTNCN(tienThuNhapTinhThue);

                    if (tienThueTNCN > 0) {
                        txtViewTienThueTNCN.setText(String.format("%1$,.0f", tienThueTNCN));
                    } else {
                        txtViewTienThueTNCN.setText("0");
                    }
//                    txtViewTienThueTNCN.setText(String.format("%1$,.0f", tienThueTNCN));
                }
            }
        });
    }

    private double thueTNCN(double thuNhapTinhThue) {
        double tienThueTNCN = 0;
        double tienThuNhapTrongThang = thuNhapTinhThue;

        if (tienThuNhapTrongThang <= 5000000) {
            tienThueTNCN = tienThuNhapTrongThang * (5 / 100);
        } else if (tienThuNhapTrongThang > 5000000 && tienThuNhapTrongThang <= 10000000) {
            tienThueTNCN = tienThuNhapTrongThang * (10 / 100) - 250000;
        } else if (tienThuNhapTrongThang > 10000000 && tienThuNhapTrongThang <= 18000000) {
            tienThueTNCN = tienThuNhapTrongThang * (15 / 100) - 250000;
        } else if (tienThuNhapTrongThang > 18000000 && tienThuNhapTrongThang <= 32000000) {
            tienThueTNCN = tienThuNhapTrongThang * (20 / 100) - 1650000;
        } else if (tienThuNhapTrongThang > 32000000 && tienThuNhapTrongThang <= 52000000) {
            tienThueTNCN = tienThuNhapTrongThang * (25 / 100) - 3250000;
        } else if (tienThuNhapTrongThang > 52000000 && tienThuNhapTrongThang <= 8000000) {
            tienThueTNCN = tienThuNhapTrongThang * (30 / 100) - 5850000;
        } else if (tienThuNhapTrongThang > 8000000) {
            tienThueTNCN = tienThuNhapTrongThang * (35 / 100) - 9850000;
        }

        return tienThueTNCN;
    }

    private double thuNhapTinhThue(String thuNhapTrongThang, String thang, String nam, String soNguoi) {
        double tienThuNhapTinhThue = 0;
        double tienThuNhapTrongThang = Double.parseDouble(thuNhapTrongThang);

        tienThuNhapTinhThue = tienThuNhapTrongThang - cacKhoanGiamTru(thang, nam, soNguoi);

        return tienThuNhapTinhThue;
    }

    private double cacKhoanGiamTru(String thang, String nam, String soNguoi) {
        double tienCacKhoanGiam = 0;

        tienCacKhoanGiam = tienGiamTruBanThan(thang, nam) + tienGiamNguoiPhuThuoc(soNguoi, thang, nam);

        return tienCacKhoanGiam;
    }

    private double tienGiamTruBanThan(String thang, String nam){
        double tienGiamTruBan = 0;

        int soThang = Integer.parseInt(thang);
        int soNam = Integer.parseInt(nam);

        if (soThang < 7 && soNam <= 2020) {
            tienGiamTruBan = 9000000;
        } else if (soThang >= 7 && soNam >= 2020) {
            tienGiamTruBan = 11000000;
        }

        return tienGiamTruBan;
    }

    private double tienGiamNguoiPhuThuoc(String nguoiPhuThuoc, String thang, String nam) {
        int soNguoiPhuThuoc = Integer.parseInt(nguoiPhuThuoc);
        int soThang = Integer.parseInt(thang);
        int soNam = Integer.parseInt(nam);

        double tienGiamNguoiPhuThuoc = 0;

        if (soThang < 7 && soNam <= 2020) {
            tienGiamNguoiPhuThuoc = 3600000 * soNguoiPhuThuoc;
        } else if (soThang >= 7 && soNam >= 2020) {
            tienGiamNguoiPhuThuoc = 4400000 * soNguoiPhuThuoc;
        }

        return tienGiamNguoiPhuThuoc;
    }

    private void initSpinnerNam() {
        adapter2 = ArrayAdapter.createFromResource(this,
                R.array.nam, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerNam.setAdapter(adapter2);
    }

    private void initSpinnerThang() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter1 = ArrayAdapter.createFromResource(this,
                R.array.thang, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerThang.setAdapter(adapter1);
    }

    private void mapping() {
        editTxtThuNhapTrongThang = (EditText) findViewById(R.id.editTxtThuNhapTrongThang);
        editTxtSoNguoiPhuThuoc = (EditText) findViewById(R.id.editTxtSoNguoiPhuThuoc);

        spinnerThang = (Spinner) findViewById(R.id.spinnerThang);
        spinnerNam = (Spinner) findViewById(R.id.spinnerNam);

        btnTinhToan = (Button) findViewById(R.id.btnTinhToan);

        txtViewThuNhapTinhThue = (TextView) findViewById(R.id.txtViewThuNhapTinhThue);
        txtViewTienThueTNCN = (TextView) findViewById(R.id.txtViewTienThueTNCN);
    }
}