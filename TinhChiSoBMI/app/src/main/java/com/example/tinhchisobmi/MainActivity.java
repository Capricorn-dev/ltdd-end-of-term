package com.example.tinhchisobmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {
    Context context = this;
    EditText heightEditText;
    EditText weightEditText;
    Button calculateBtn;
    TextView resultTxt;
    TextView commentTxt;
    RadioButton femaleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ánh xạ
        heightEditText = (EditText) findViewById(R.id.heightEditText);
        weightEditText = (EditText) findViewById(R.id.weightEditText);
        calculateBtn = (Button) findViewById(R.id.calculateBtn);
        resultTxt = (TextView) findViewById(R.id.resultTxt);
        commentTxt = (TextView) findViewById(R.id.commentTxt);
        femaleBtn = (RadioButton) findViewById(R.id.femaleBtn);

        //Click
        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(heightEditText.getText().toString().length() == 0)
                {
                    Toast.makeText(context, "Không được để trống chiều cao.", Toast.LENGTH_SHORT).show();
                }
                else if(weightEditText.getText().toString().length() == 0)
                {
                    Toast.makeText(context, "Không được để trống cân nặng.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String result = "";
                    double height = 0;
                    double weight = 0;
                    try {
                        height = Double.parseDouble(heightEditText.getText().toString());
                        weight = Double.parseDouble(weightEditText.getText().toString());
                    }
                    catch (Exception ex)
                    {

                    }
                    result = calculateBMI(height, weight);
                    //Kết quả
                    resultTxt.setText(result);
                    //Nhận xét
                    double BMIresult = 0;
                    try {
                        BMIresult  = Double.parseDouble(result);
                    }
                    catch (Exception ex)
                    {

                    }
                    //Check
                    if(femaleBtn.isChecked())
                    {
                        commentTxt.setText(commentYourBMI(true, BMIresult));
                    }
                    else
                    {
                        commentTxt.setText(commentYourBMI(false, BMIresult));
                    }
                }
            }
        });

    }
    private String calculateBMI(double height, double weight)
    {
        double heightConvertCmToM = height / 100;
        return String.format("%.1f", weight / (heightConvertCmToM * heightConvertCmToM));
    }
    private String commentYourBMI(boolean isFemale, double result)
    {
        String comment = "";
        if(isFemale)
        {
            if(result < 18.5)
            {
                comment = "Bạn có chỉ số IBM thấp.\nBạn phải tập luyện ăn và ăn uống đều độ để lên cân.";
            }
            else if(result >= 18.5 && result <= 22.9)
            {
                comment = "Chúc mừng bạn.\nBạn có chỉ số IBM bình thường.";
            }
            else if(result >= 23 && result <= 23.9)
            {
                comment = "Bạn có chỉ số IBM mức trung bình.\nBạn bị thừa cân, cần phải tập luyện.";
            }
            else if(result >= 23 && result <= 24.9)
            {
                comment = "Bạn có chỉ số IBM cao hơn mức trung bình.\nBạn bị béo phì, hãy giảm cân nếu có thể.";
            }
            else if(result >= 25 && result <= 29.9)
            {
                comment = "Bạn có chỉ số IBM cao hơn mức trung bình khá.\nBạn bị béo phì cấp độ 1, hãy giảm cân.";
            }
            else if(result >= 30 && result <= 39.9)
            {
                comment = "Bạn có chỉ số IBM mức khá.\nBạn bị béo phì cấp độ 2, hãy tập luyện và giảm cân.";
            }
            else
            {
                comment = "Bạn có chỉ số IBM mức cao.\nBạn bị béo phì cấp độ 3, cần phải giảm cân gấp.";
            }
        }
        else
        {

            if(result < 18.5)
            {
                comment = "Bạn có chỉ số IBM thấp.\nBạn phải tập luyện ăn và ăn uống đều độ để lên cân.";
            }
            else if(result >= 18.5 && result <= 24.9)
            {
                comment = "Chúc mừng bạn.\nBạn có chỉ số IBM bình thường.";
            }
            else if(result >= 25 && result <= 25.9)
            {
                comment = "Bạn có chỉ số IBM mức trung bình.\nBạn bị thừa cân, cần phải tập luyện.";
            }
            else if(result >= 25 && result <= 29.9)
            {
                comment = "Bạn có chỉ số IBM cao hơn mức trung bình.\nBạn bị béo phì, hãy giảm cân nếu có thể.";
            }
            else if(result >= 30 && result <= 34.9)
            {
                comment = "Bạn có chỉ số IBM cao hơn mức trung bình khá.\nBạn bị béo phì cấp độ 1, hãy giảm cân.";
            }
            else if(result >= 35 && result <= 39.9)
            {
                comment = "Bạn có chỉ số IBM mức khá.\nBạn bị béo phì cấp độ 2, hãy tập luyện và giảm cân.";
            }
            else
            {
                comment = "Bạn có chỉ số IBM mức cao.\nBạn bị béo phì cấp độ 3, cần phải giảm cân gấp.";
            }
        }
        return comment;
    }
}
