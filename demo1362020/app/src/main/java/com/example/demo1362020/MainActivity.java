package com.example.demo1362020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTxtThuNhapHang, editTxtChiPhiPhaiTraTrongThang, editTxtSoTienMuonVay;
    Spinner spinnerLaiSuatMongMuon;
    RadioGroup rdoGrThoiGianVay;
    RadioButton rdoBtn12thang, rdoBtn24thang, rdoBtn36thang, rdoBtn48thang;
    Button btnTinhToan;
    TextView txtViewSoTienPhaiTraHangThang;

    double thuNhapHangThang = 0;
    double chiPhiPhaiTraTrongThang = 0;
    double soTienMuonVay = 0;

    int soNam = 0;
    double laiSuat = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();
        soNamDuocChon();

        btnTinhToan_SetOnClickListener();
    }

    private void btnTinhToan_SetOnClickListener() {
        btnTinhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTxtThuNhapHang.getText().toString().isEmpty()
                        || editTxtChiPhiPhaiTraTrongThang.getText().toString().isEmpty()
                        || editTxtSoTienMuonVay.getText().toString().isEmpty()
                        || spinnerLaiSuatMongMuon.getSelectedItemPosition() == 0) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin!!"
                            , Toast.LENGTH_SHORT).show();
                } else if (!kiemTraSoTienMuonVay()) {
                    Toast.makeText(MainActivity.this, "Số tiền không được quá 10 lần " +
                                    "và không được ít hơn 20 triệu"
                            , Toast.LENGTH_SHORT).show();
                } else {
                    thuNhapHangThang = Double.parseDouble(editTxtThuNhapHang.getText().toString());
                    chiPhiPhaiTraTrongThang = Double.parseDouble(editTxtChiPhiPhaiTraTrongThang.getText()
                            .toString());
                    soTienMuonVay = Double.parseDouble(editTxtSoTienMuonVay.getText().toString());

                    double laiSuat = laiSuatMongMuon();

                    Log.d("Số năm = ", String.valueOf(soNam));
                    Log.d("laiSuat = ", String.valueOf(laiSuat));

                    double kq = soTienPhaiTraHangThang(soTienMuonVay, laiSuat, soNam);
                    Log.d("tien phai tra = ", String.valueOf(kq));

                    txtViewSoTienPhaiTraHangThang.setText(String.format("%1$,.0f", kq));
                    Toast.makeText(MainActivity.this, "THÀNH CÔNG!!"
                            , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private double soTienPhaiTraHangThang(double soTienMuonVay, double laiSuatMongMuon, int soNam) {
        double kqNam = 0;
        kqNam = soTienMuonVay * Math.pow((1 + laiSuatMongMuon), soNam);

        double kqThang = 0;
        kqThang = (kqNam - soTienMuonVay) / (soNam * 12);

        return kqThang;
    }

    private double laiSuatMongMuon() {
        int chon = spinnerLaiSuatMongMuon.getSelectedItemPosition();

        switch (chon) {
            case 1:
                laiSuat = 0.16;
                break;
            case 2:
                laiSuat = 0.17;
                break;
            case 3:
                laiSuat = 0.18;
                break;
        }
        return laiSuat;
    }

    private void soNamDuocChon() {
        rdoGrThoiGianVay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rdoBtn12thang:
                        soNam = 1;
                        break;
                    case R.id.rdoBtn24thang:
                        soNam = 2;
                        break;
                    case R.id.rdoBtn36thang:
                        soNam = 3;
                        break;
                    case R.id.rdoBtn48thang:
                        soNam = 4;
                        break;
                }
            }
        });
    }

    private boolean kiemTraSoTienMuonVay() {
        thuNhapHangThang = Double.parseDouble(editTxtThuNhapHang.getText().toString());
        chiPhiPhaiTraTrongThang = Double.parseDouble(editTxtChiPhiPhaiTraTrongThang.getText()
                .toString());
        soTienMuonVay = Double.parseDouble(editTxtSoTienMuonVay.getText().toString());

        double soTienConLai = thuNhapHangThang - chiPhiPhaiTraTrongThang;

        if (soTienMuonVay <= 10 * soTienConLai && soTienMuonVay >= 20000000) {
            return true;
        }

        return false;
    }

    private void mapping() {
        editTxtThuNhapHang = (EditText) findViewById(R.id.editTxtThuNhapHang);
        editTxtChiPhiPhaiTraTrongThang = (EditText) findViewById(R.id.editTxtChiPhiPhaiTraTrongThang);
        editTxtSoTienMuonVay = (EditText) findViewById(R.id.editTxtSoTienMuonVay);

        rdoGrThoiGianVay = (RadioGroup) findViewById(R.id.rdoGrThoiGianVay);

        rdoBtn12thang = (RadioButton) findViewById(R.id.rdoBtn12thang);
        rdoBtn24thang = (RadioButton) findViewById(R.id.rdoBtn24thang);
        rdoBtn36thang = (RadioButton) findViewById(R.id.rdoBtn36thang);
        rdoBtn48thang = (RadioButton) findViewById(R.id.rdoBtn48thang);

        btnTinhToan = (Button) findViewById(R.id.btnTinhToan);

        txtViewSoTienPhaiTraHangThang = (TextView) findViewById(R.id.txtViewSoTienPhaiTraHangThang);

        spinnerLaiSuatMongMuon = (Spinner) findViewById(R.id.spinnerLaiSuatMongMuon);
    }
}