package com.example.bt2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView tv_KetQua=findViewById(R.id.textView_KetQua);

        String hoten=getIntent().getExtras().getString("ht");
        String namSinh=getIntent().getExtras().getString("ns");

        String st="";
        st="Họ và Tên: "+hoten;
        st +="Năm sinh: "+ namSinh;
        tv_KetQua.setText(st);
    }
}