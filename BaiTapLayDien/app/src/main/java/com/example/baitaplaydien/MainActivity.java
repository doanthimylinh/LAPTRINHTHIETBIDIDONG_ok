package com.example.baitaplaydien;

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
        Button bt_GiaiPT=findViewById(R.id.bt_GiaiPT);
        bt_GiaiPT.setOnClickListener(new View.OnClickListener() {
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
            float hesoA = data.getExtras().getFloat("a");
            float hesoB = data.getExtras().getFloat("b");
            float hesoC = data.getExtras().getFloat("c");
            String st="";
            float denta=((hesoB*hesoB)-(4*hesoA*hesoC));

            if(denta>0){
                st+="x1"+(-hesoB+Math.sqrt(denta));
                 st+="\nx2: "+(-hesoB-Math.sqrt(denta));
                 tv_KetQua.setText("Pt co 1 nghiem phan biet:"+st);
            }
            else if(denta==0)
                tv_KetQua.setText("Pt co nghiem kep x1=x2= "+(-hesoB/2*hesoA));
            else tv_KetQua.setText("pt vo nghiem");


        }

    }
}