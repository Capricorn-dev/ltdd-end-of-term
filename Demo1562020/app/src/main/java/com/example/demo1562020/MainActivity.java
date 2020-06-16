package com.example.demo1562020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTxtGiaXe;

    Spinner spinnerLoaiXe, spinnerBienSoXe;

    Button btnTinhToan;

    TextView txtViewGiaDamPhan_price;
    TextView txtViewPhiTruocBa;
    TextView txtViewPhiTruocBa_price;
    TextView txtViewPhiSuDungDuongBo_price;
    TextView txtViewBaoHiem_price;
    TextView txtViewPhiDangKy_price;
    TextView txtViewDangKiemPhiDangKiem_price;

    TextView txtViewTongCong_price;
    TextView txtViewTongCong_price_1;

    ArrayAdapter adapterLoaiXe, adapterBienSo;

    long giaXe_price = 0;
    long phiTruocBa_price = 0;

    String vnd = " đ";
    String phiTruocBa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapping();

        initSpinnerLoaiXe();
        initSpinnerBienSo();

        editTxtGiaXe_addTextChangedListener();


        spinnerBienSoXe_setOnItemClickListener();

//        String bienSo = adapterBienSo.getItem(position).toString();
//
//        if (bienSo.equals("TP.HCM")) {
//            phiTruocBa = "Phí trước bạ(10%)";
//            txtViewPhiTruocBa.setText(phiTruocBa);
//        } else {
//            phiTruocBa = "Phí trước bạ(3%)";
//            txtViewPhiTruocBa.setText(phiTruocBa);
//        }
    }

    public void spinnerBienSoXe_setOnItemClickListener() {
        spinnerBienSoXe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String bienSo = adapterBienSo.getItem(position).toString();

                if (position == 1) {
                   phiTruocBa = "Phí trước bạ(10%)";
                   txtViewPhiTruocBa.setText(phiTruocBa);

                    phiTruocBa_price = (long) (giaXe_price * 0.1);
                    txtViewPhiTruocBa_price.setText(String.valueOf(phiTruocBa_price));
                } else {
                   phiTruocBa = "Phí trước bạ(3%)";
                   txtViewPhiTruocBa.setText(phiTruocBa);

                    phiTruocBa_price = (long) (giaXe_price * 0.03);
                    txtViewPhiTruocBa_price.setText(String.valueOf(phiTruocBa_price));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void editTxtGiaXe_addTextChangedListener() {
        editTxtGiaXe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    giaXe_price = Long.parseLong(editTxtGiaXe.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "LỖI", Toast.LENGTH_SHORT).show();
                }

                txtViewGiaDamPhan_price.setText(editTxtGiaXe.getText().toString());
            }
        });
    }

    private void initSpinnerBienSo() {
        adapterBienSo = ArrayAdapter.createFromResource(this,
                R.array.bienSo, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterBienSo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerBienSoXe.setAdapter(adapterBienSo);
    }

    private void initSpinnerLoaiXe() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        adapterLoaiXe = ArrayAdapter.createFromResource(this,
                R.array.loaiXe, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterLoaiXe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerLoaiXe.setAdapter(adapterLoaiXe);
    }

    private void mapping() {
        editTxtGiaXe = (EditText) findViewById(R.id.editTxtGiaXe);

        spinnerLoaiXe = (Spinner) findViewById(R.id.spinnerLoaiXe);
        spinnerBienSoXe = (Spinner) findViewById(R.id.spinnerBienSoXe);

        btnTinhToan = (Button) findViewById(R.id.btnTinhToan);

        txtViewGiaDamPhan_price = (TextView) findViewById(R.id.txtViewGiaDamPhan_price);
        txtViewPhiTruocBa = (TextView) findViewById(R.id.txtViewPhiTruocBa);
        txtViewPhiTruocBa_price = (TextView) findViewById(R.id.txtViewPhiTruocBa_price);
        txtViewPhiSuDungDuongBo_price = (TextView) findViewById(R.id.txtViewPhiSuDungDuongBo_price);
        txtViewBaoHiem_price = (TextView) findViewById(R.id.txtViewGiaDamPhan_price);
        txtViewPhiDangKy_price = (TextView) findViewById(R.id.txtViewPhiDangKy_price);
        txtViewDangKiemPhiDangKiem_price = (TextView) findViewById(R.id.txtViewDangKiemPhiDangKiem_price);

        txtViewTongCong_price = (TextView) findViewById(R.id.txtViewTongCong_price);
        txtViewTongCong_price_1 = (TextView) findViewById(R.id.txtViewTongCong_price_1);
    }
}