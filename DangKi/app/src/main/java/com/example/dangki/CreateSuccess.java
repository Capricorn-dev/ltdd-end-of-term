package com.example.dangki;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CreateSuccess extends AppCompatActivity {
    private String name;
    TextView nameCreateSuccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_success);
        nameCreateSuccess = (TextView) findViewById(R.id.nameCreateSuccess);
        Intent intent = getIntent();
        name = intent.getStringExtra(MainActivity.NAME);
        nameCreateSuccess.setText(name);
    }
}
