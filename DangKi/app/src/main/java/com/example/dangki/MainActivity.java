package com.example.dangki;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String NAME = "NAME";
    private Activity context = this;
    //Các biến dùng chung
    Spinner chooseNationality;
    TextView name;
    TextView username;
    TextView password;
    TextView birthday;
    Button buttonCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] nationality = {"Việt Nam" , "Hàn Xẻng", "Nhật Bổn", "Campuchia", "Laos"};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ánh xạ
        chooseNationality = (Spinner) findViewById(R.id.chooseNationality);
        name = (TextView) findViewById(R.id.name);
        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);
        buttonCreate = (Button) findViewById(R.id.buttonCreate);
        birthday = (TextView) findViewById(R.id.birthday);
        //Gán spinner
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, nationality);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        chooseNationality.setAdapter(arrayAdapter);
        //Textchange
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(name.getText().length() == 0)
                {
                    name.setError("Không được để trống");
                }

            }
        });

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(username.getText().length() == 0)
                {
                    username.setError("Không được để trống");
                }
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int passwordLength = password.getText().length();
                String passwordString = password.getText().toString();
                char[] checkPassword = password.getText().toString().toCharArray();
                if(passwordLength == 0)
                {
                    password.setError("Không được để trống");
                }
                else if(passwordLength < 8)
                {
                    password.setError("Mật khẩu phải lớn hơn bằng 8 kí tự");
                }

            }
        });
        //Create
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checkNumber = false;
                boolean checkUpperCase = false;
                boolean checkLowerCase = false;
                boolean checkSymbol = false;
                char[] passwordCharacter = password.getText().toString().toCharArray();
                for(char c: passwordCharacter)
                {
                    if(Character.isDigit(c) && checkNumber == false)
                    {
                        checkNumber = true;
                    }
                    else if(Character.isUpperCase(c) && checkUpperCase == false)
                    {
                        checkUpperCase = true;
                    }
                    else if(Character.isLowerCase(c) && checkLowerCase == false)
                    {
                        checkLowerCase = true;
                    }
                    else if(!Character.isDigit(c) &&!Character.isAlphabetic(c) && checkSymbol == false)
                    {
                        checkSymbol = true;
                    }
                }
                //Kiểm tra null
                if(name.length() == 0 || username.length() == 0 || password.length() == 0 || birthday.length() == 0)
                {
                    new AlertDialog.Builder(context).setTitle("Lỗi")
                            .setMessage("Có các dữ liệu để trống. Vui lòng thử lại")

                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).show();
                }
                else if(password.length() < 8)
                {
                    new AlertDialog.Builder(context).setTitle("Lỗi")
                            .setMessage("Mật khẩu phải đủ 8 kí tự")

                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).show();
                }
                else if(!checkNumber || !checkUpperCase || !checkSymbol || !checkLowerCase)
                {

                    new AlertDialog.Builder(context).setTitle("Lỗi")
                        .setMessage("Mật khẩu phải đủ ít nhất 1 kí tự đặc biệt, 1 chữ số, 1 chữ Hoa, 1 chữ thường")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).show();
                }

                else
                {
                    Intent intent = new Intent(context, CreateSuccess.class);
                    intent.putExtra(NAME, name.getText().toString());
                    startActivity(intent);
                    Log.d("Status", "Success");
                }
            }
        });
    }
}
