package com.example.bt3_doinamduonglich;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText namDuongLich,namAmLich;
    Button buttonChuyenDoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);}

//        namDuongLich= findViewById(R.id.namDuongLich);
//        namAmLich=findViewById(R.id.namAmLich);
//
//        buttonChuyenDoi=findViewById(R.id.buttonChuyenDoi);
//
//        String strNam=namDuongLich.getText().toString().trim();
//        int NamDuongLich=Integer.parseInt(strNam);
//        if (NamDuongLich<1990){
//            namAmLich.setText("");
//        }
//
//        buttonChuyenDoi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String[] can = {"Mậu", "Kỷ", "Canh", "Tân", "Nhâm", "Quý", "Giáp", "Ất", "Bính", "Đinh"};
//                String[] chi = {"Thân", "Dậu", "Tuất", "Hợi", "Tý", "Sửu", "Dần", "Mẹo", "Thìn", "Tỵ", "Ngọ", "Mùi"};
//
//                namAmLich.setText("" + can[(NamDuongLich % 10)] + chi[NamDuongLich % 12]);
//            }
//        });
//    }

}