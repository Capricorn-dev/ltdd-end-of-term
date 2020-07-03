package com.example.demo1562020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

    double giaXe_price = 0;
    double phiTruocBa_price = 0;
    double phiSuDungDuongBo_price = 0;
    double baoHiemTNDS = 0;
    double phiDangKyBienSo = 0;
    double phiDangKyKiem = 0;

    double tong = 0;
    boolean loaiXeisSelected = false;
    boolean diaPhuongisSelected = false;

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

        SpinnerLoaiXe_setOnItemClickListener();
        btnTinhToan_SetOnClickListener();
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

    private void btnTinhToan_SetOnClickListener() {
        btnTinhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTxtGiaXe.getText().toString().isEmpty() && loaiXeisSelected && diaPhuongisSelected) {
                    tong = giaXe_price + phiTruocBa_price + phiSuDungDuongBo_price
                            + baoHiemTNDS + phiDangKyBienSo + phiDangKyKiem;

                    txtViewTongCong_price_1.setText(String.format("%1$,.0f" + vnd, tong));
                    txtViewTongCong_price.setText(String.format("%1$,.0f" + vnd, tong));
                } else {
                    Log.d("Không nhập gì hết", "LỖIIIIII");
                }
            }
        });
    }

    public void SpinnerLoaiXe_setOnItemClickListener() {
        spinnerLoaiXe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Xe du lịch dưới 10 chỗ
                if (position == 1) {
                    phiSuDungDuongBo_price = 1560000;
                    txtViewPhiSuDungDuongBo_price.setText(String.format("%1$,.0f" + vnd, phiSuDungDuongBo_price));

                    baoHiemTNDS = 794000;
                    txtViewBaoHiem_price.setText(String.format("%1$,.0f" + vnd, baoHiemTNDS));

                    phiDangKyKiem = 340000;
                    txtViewDangKiemPhiDangKiem_price.setText(String.format("%1$,.0f" + vnd, phiDangKyKiem));

                    loaiXeisSelected = true;
                } else if (position == 3) { // Xe tải nhỏ
                    phiSuDungDuongBo_price = 2160000;
                    txtViewPhiSuDungDuongBo_price.setText(String.format("%1$,.0f" + vnd, phiSuDungDuongBo_price));

                    baoHiemTNDS = 953000;
                    txtViewBaoHiem_price.setText(String.format("%1$,.0f" + vnd, baoHiemTNDS));

                    phiDangKyKiem = 540000;
                    txtViewDangKiemPhiDangKiem_price.setText(String.format("%1$,.0f" + vnd, phiDangKyKiem));

                    loaiXeisSelected = true;
                } else if (position == 2) { // Xe bán tải
                    phiSuDungDuongBo_price = 0;
                    txtViewPhiSuDungDuongBo_price.setText(String.format("%1$,.0f" + vnd, phiSuDungDuongBo_price));

                    baoHiemTNDS = 933000;
                    txtViewBaoHiem_price.setText(String.format("%1$,.0f" + vnd, baoHiemTNDS));

                    phiDangKyKiem = 540000;
                    txtViewDangKiemPhiDangKiem_price.setText(String.format("%1$,.0f" + vnd, phiDangKyKiem));

                    loaiXeisSelected = true;
                } else if (position == 0) { // Không chọn gì cả
                    phiSuDungDuongBo_price = 0;
                    txtViewPhiSuDungDuongBo_price.setText(String.format("%1$,.0f" + vnd, phiSuDungDuongBo_price));

                    baoHiemTNDS = 0;
                    txtViewBaoHiem_price.setText(String.format("%1$,.0f" + vnd, baoHiemTNDS));

                    phiDangKyKiem = 0;
                    txtViewDangKiemPhiDangKiem_price.setText(String.format("%1$,.0f" + vnd, phiDangKyKiem));

                    loaiXeisSelected = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void spinnerBienSoXe_setOnItemClickListener() {
        spinnerBienSoXe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String bienSo = adapterBienSo.getItem(position).toString();

                // Không chọn gì cả
                if (position == 0) {
                    phiTruocBa = "Phí trước bạ(0%)";
                    txtViewPhiTruocBa.setText(phiTruocBa);

                    phiTruocBa_price = 0;
                    txtViewPhiTruocBa_price.setText(String.format("%1$,.0f" + vnd, phiTruocBa_price));

                    phiDangKyBienSo = 0;
                    txtViewPhiDangKy_price.setText(String.format("%1$,.0f" + vnd, phiDangKyBienSo));

                    diaPhuongisSelected = false;
                } else if (position == 1) { // TP.HCM
                    phiTruocBa = "Phí trước bạ(10%)";
                    txtViewPhiTruocBa.setText(phiTruocBa);

                    phiTruocBa_price = giaXe_price * 0.1;
                    txtViewPhiTruocBa_price.setText(String.format("%1$,.0f" + vnd, phiTruocBa_price));

                    phiDangKyBienSo = 11000000;
                    txtViewPhiDangKy_price.setText(String.format("%1$,.0f" + vnd, phiDangKyBienSo));

                    diaPhuongisSelected = true;
                } else { // Địa phương khác
                    phiTruocBa = "Phí trước bạ(3%)";
                    txtViewPhiTruocBa.setText(phiTruocBa);

                    phiTruocBa_price = giaXe_price * 0.03;
                    txtViewPhiTruocBa_price.setText(String.format("%1$,.0f" + vnd, phiTruocBa_price));

                    phiDangKyBienSo = 3000000;
                    txtViewPhiDangKy_price.setText(String.format("%1$,.0f" + vnd, phiDangKyBienSo));

                    diaPhuongisSelected = true;
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
                String s1 = editTxtGiaXe.getText().toString();
                try {
                    if (!s1.isEmpty()) {
                        giaXe_price = Double.parseDouble(s1);
                    }
                } catch (NumberFormatException e) {
                    Log.d("Gia xe bug", e.getMessage());
                }

                String text;
                if (s1.isEmpty()) {
                    giaXe_price = 0;
                }

                txtViewGiaDamPhan_price.setText(String.format("%1$,.0f" + vnd, giaXe_price));
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
        txtViewBaoHiem_price = (TextView) findViewById(R.id.txtViewBaoHiem_price);
        txtViewPhiDangKy_price = (TextView) findViewById(R.id.txtViewPhiDangKy_price);
        txtViewDangKiemPhiDangKiem_price = (TextView) findViewById(R.id.txtViewDangKiemPhiDangKiem_price);

        txtViewTongCong_price = (TextView) findViewById(R.id.txtViewTongCong_price);
        txtViewTongCong_price_1 = (TextView) findViewById(R.id.txtViewTongCong_price_1);
    }
}