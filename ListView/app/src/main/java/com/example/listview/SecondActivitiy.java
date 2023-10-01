package com.example.listview;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivitiy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView tensp=findViewById(R.id.tenSP);
        ImageView img=findViewById(R.id.img);
        TextView gia=findViewById(R.id.gia);
        TextView thongtinsp=findViewById(R.id.chitietsanpham);
        Button btn_mua=findViewById(R.id.mua);

        tensp.setText(getIntent().getExtras().getString("sanpham"));
        img.setImageResource(getIntent().getExtras().getInt("img"));
        gia.setText(getIntent().getExtras().getString("gia"));
        thongtinsp.setText(getIntent().getExtras().getString("chitietSP"));
        btn_mua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SecondActivitiy.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}