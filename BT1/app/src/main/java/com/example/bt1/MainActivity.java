package com.example.bt1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText_HeSoA=findViewById(R.id.et_HeSoA);
        EditText editText_HeSoB=findViewById(R.id.et_HeSoB);
        Button button_Tong=findViewById(R.id.bt_Tong);
        Button button_Hieu=findViewById(R.id.bt_Hieu);
        Button button_Nhan=findViewById(R.id.bt_Nhan);
        Button button_Chia=findViewById(R.id.bt_Chia);
        TextView textView_KetQua=findViewById(R.id.tv_KetQua);

        button_Tong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a=Integer.parseInt(editText_HeSoA.getText().toString());
                int b=Integer.parseInt(editText_HeSoB.getText().toString());
                int c=a+b;
                textView_KetQua.setText("Result " + c);

            }
        });
        button_Hieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a=Integer.parseInt(editText_HeSoA.getText().toString());
                int b=Integer.parseInt(editText_HeSoB.getText().toString());

                int d=a-b;

                textView_KetQua.setText("Result " + d);

            }
        });
        button_Nhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a=Integer.parseInt(editText_HeSoA.getText().toString());
                int b=Integer.parseInt(editText_HeSoB.getText().toString());
                int e=a*b;

                textView_KetQua.setText("Result " + e);

            }
        });
        button_Chia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a=Integer.parseInt(editText_HeSoA.getText().toString());
                int b=Integer.parseInt(editText_HeSoB.getText().toString());

                int f=a/b;
                textView_KetQua.setText("Result " + f);

            }
        });
    }

}