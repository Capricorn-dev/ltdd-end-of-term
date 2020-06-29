package com.example.buoi5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    Context context = this;
    RadioGroup radioGroup1;
    EditText editTextThuNhapHang;
    EditText editTextChiPhi;
    EditText editTextSoTien;
    Spinner spinnerLaiSuat;

    Button btTinhToan;

    RadioButton thang12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup1 = findViewById(R.id.radioGroup1);
        editTextThuNhapHang = findViewById(R.id.editTextThuNhapHang);
        editTextChiPhi = findViewById(R.id.editTextChiPhi);
        editTextSoTien = findViewById(R.id.editTextSoTien);
        spinnerLaiSuat = findViewById(R.id.spinnerLaiSuat);
        btTinhToan = findViewById(R.id.btTinhToan);
        thang12 = findViewById(R.id.thang12);

        btTinhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BigDecimal thuNhapSauChiPhi = new BigDecimal(Double.parseDouble(editTextThuNhapHang.getText().toString()) -
                        Double.parseDouble(editTextChiPhi.getText().toString()));
                BigDecimal tienCanVay = new BigDecimal(Double.parseDouble(editTextSoTien.getText().toString()));
                if(editTextThuNhapHang.getText().toString().length() == 0)
                {
                    Toast.makeText(context, "Thu nhập trống", Toast.LENGTH_LONG).show();
                }
                else if(editTextChiPhi.getText().toString().length() == 0)
                {
                    Toast.makeText(context, "Chi phí trống", Toast.LENGTH_LONG).show();
                }
                else if(editTextSoTien.getText().toString().length() == 0)
                {
                    Toast.makeText(context, "Số tiền trống", Toast.LENGTH_LONG).show();
                }
                else if(kiemTraChoMuonHayKhong(tienCanVay, thuNhapSauChiPhi) == false)
                {
                    Toast.makeText(context, "Số tiền vay không phù hợp với thu nhập sau chi phí", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(context, "Thành công", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private boolean kiemTraChoMuonHayKhong(BigDecimal soTienVay, BigDecimal thuNhapSauChiPhi)
    {
        if(soTienVay.doubleValue() > thuNhapSauChiPhi.doubleValue() * 10)
        {
            return false;
        }
        return true;
    }
}
