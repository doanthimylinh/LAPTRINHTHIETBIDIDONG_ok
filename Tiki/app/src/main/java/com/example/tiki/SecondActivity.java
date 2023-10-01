package com.example.tiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {


    int index ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ImageView sanpham = findViewById(R.id.imgView_SanPham);
        ImageView imgXanhNhat = findViewById(R.id.img_xanhnhat);
        ImageView imgDo = findViewById(R.id.img_do);
        ImageView imgDen = findViewById(R.id.img_den);
        ImageView imgXanhDuong = findViewById(R.id.img_xanhduong);

        imgXanhNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sanpham.setImageResource(R.drawable.vs_bac);
                index = R.drawable.vs_bac;
            }
        });

        imgDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sanpham.setImageResource(R.drawable.vs_red);
                index = R.drawable.vs_red;
            }
        });

        imgDen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sanpham.setImageResource(R.drawable.vsmart_black);
                index = R.drawable.vsmart_black;

            }
        });

        imgXanhDuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sanpham.setImageResource(R.drawable.vsmart_live_xanh);
                index = R.drawable.vsmart_live_xanh;
            }
        });

        Button btnXong = findViewById(R.id.btn_Xong);
        btnXong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent trave = new Intent();
                trave.putExtra("str", index);
                setResult(RESULT_OK, trave);
                finish();
            }
        });
    }
}