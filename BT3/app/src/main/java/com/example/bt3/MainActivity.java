package com.example.bt3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView tv_KetQua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_KetQua=findViewById(R.id.textView_KetQua);
        Button bt_NhapThongTin=findViewById(R.id.bt_NhapThongTin);
        bt_NhapThongTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,SecondActivity.class);
                startActivityForResult(intent, 9999);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 9999 && resultCode == RESULT_OK){
            String hoten = data.getExtras().getString("ht").toString();
            String namsinh = data.getExtras().getString("ns").toString();

            int namSinh=Integer.parseInt(namsinh);
            Calendar calendar=Calendar.getInstance();
            int namHienTai=calendar.get(Calendar.YEAR);
            String st="";
            st+="Họ và tên: "+hoten;
            st+="\nNăm sinh: "+namsinh;
            st+="\n Tuổi: "+(namHienTai-namSinh);
            tv_KetQua.setText(st);
        }

    }
}