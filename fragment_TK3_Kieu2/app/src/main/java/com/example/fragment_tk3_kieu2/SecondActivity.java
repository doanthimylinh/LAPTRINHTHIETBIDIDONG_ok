package com.example.fragment_tk3_kieu2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        SanPham sanPham= (SanPham) intent.getSerializableExtra("sanpham");
        TextView tensp=findViewById(R.id.tenSP);
        ImageView img=findViewById(R.id.img);
        TextView gia=findViewById(R.id.gia);
        TextView thongtinsp=findViewById(R.id.chitietsanpham);
        Button btn_mua=findViewById(R.id.mua);


        img.setImageResource(sanPham.getHinh());
        tensp.setText(sanPham.getSanpham());
        gia.setText(sanPham.getGia());
        thongtinsp.setText(sanPham.getThongtin());
        btn_mua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}