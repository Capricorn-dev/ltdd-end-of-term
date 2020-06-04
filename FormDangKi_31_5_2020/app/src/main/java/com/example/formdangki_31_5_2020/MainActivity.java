package com.example.formdangki_31_5_2020;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText  editTxt_Hoten, editTxt_Username, editTxt_Matkhau, editTxt_Ngaysinh;
    Button btn_Tao;
    RadioGroup radioGr_GioiTinh;
    RadioButton radio_btn_nam, radio_btn_nu;
    Spinner spiner_quocTich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        test();
        btn_Tao_setOnClickListener();
    }

    private void test() {
        editTxt_Hoten.setText("h");
        editTxt_Username.setText("s1");
        editTxt_Matkhau.setText("tT@12345");
        editTxt_Ngaysinh.setText("2");
        radio_btn_nu.setChecked(true);
    }

    private void btn_Tao_setOnClickListener() {
        btn_Tao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( isMatkhauValidated() && !isHoTenEmpty() && !isUsernameEmpty()
                        && !isNgaySinhEmpty() && isRadioGr_GioiTinhChecked() && isUsernameValidated() ) {
                    Toast.makeText(MainActivity.this, "Bạn đã tạo thành công", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, SuccessActivity.class);
                    intent.putExtra("1", editTxt_Hoten.getText().toString());

                    startActivity(intent);
                } else {
                    isUsernameValidated();
                    isMatkhauValidated();
                    isNgaySinhEmpty();
                    isHoTenEmpty();
                }
            }
        });
    }

    private boolean isRadioGr_GioiTinhChecked() {
        if (radio_btn_nam.isChecked() || radio_btn_nu.isChecked()) {
            return true;
        }
        return false;
    }

    private boolean isHoTenEmpty() {
        String editTxt_Hoten_Error = "Vui lòng không để trống";

        if (editTxt_Hoten.getText().toString().isEmpty()) {
            editTxt_Hoten.setError(editTxt_Hoten_Error);
            return true;
        }
        return false;
    }

    private boolean isUsernameValidated() {
        String regexForUser = "^(\\d|\\w)+$";
        String usernameError = "Vui lòng nhập đúng định dạng: không nhập quá 128 kí tự, không có khoảng trống, không có kí tự đặc biệt và không được để trống";

        if (editTxt_Username.length() >= 128 || !editTxt_Username.getText().toString().matches(regexForUser)) {
            editTxt_Username.setError(usernameError);
            return false;
        }
        return true;
    }

    private boolean isUsernameEmpty() {
        if (editTxt_Username.getText().toString().isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean isNgaySinhEmpty() {
        String editTxt_Ngaysinh_Error = "Vui lòng không để trống";

        if (editTxt_Ngaysinh.getText().toString().isEmpty()) {
            editTxt_Ngaysinh.setError(editTxt_Ngaysinh_Error);
            return true;
        }
        return false;
    }
    private boolean isMatkhauValidated() {
        String regexForPass = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        String regExeditTxt_Matkhau_Error = "Vui lòng nhập mật khẩu có ít nhất: 8 kí tự, " +
                "1 kí tự số, 1 kí tự đặc biệt, 1 kí tự chữ hoa và 1 kí tự chữ thường";

        if (editTxt_Matkhau.getText().toString().matches(regexForPass)) {
            return true;
        }
        editTxt_Matkhau.setError(regExeditTxt_Matkhau_Error);
        return false;
    }

    private void anhXa() {
        editTxt_Hoten = (EditText) findViewById(R.id.editTxt_Hoten);
        editTxt_Username = (EditText) findViewById(R.id.editTxt_Username);
        editTxt_Matkhau = (EditText) findViewById(R.id.editTxt_Matkhau);
        editTxt_Ngaysinh = (EditText) findViewById(R.id.editTxt_Ngaysinh);

        btn_Tao = (Button) findViewById(R.id.btn_Tao);

        radioGr_GioiTinh = (RadioGroup) findViewById(R.id.radioGr_GioiTinh);
        radio_btn_nam = (RadioButton) findViewById(R.id.radio_btn_nam);
        radio_btn_nu = (RadioButton) findViewById(R.id.radio_btn_nu);

        spiner_quocTich = (Spinner) findViewById(R.id.spiner_quocTich);
    }
}
