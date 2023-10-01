package com.example.bt_3;

import androidx.appcompat.app.AlertDialog;
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
        EditText duong= findViewById(R.id.DuongLich);
        Button cd= findViewById(R.id.btn_CD);
        TextView am= findViewById(R.id.AmLich);
        cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int dl= Integer.parseInt(duong.getText().toString().trim());
                String can,chi;
                if(dl>=1900){
                    switch (dl%10){
                        case 0: can="Canh";break;
                        case 1: can="Tân";break;
                        case 2: can="Nhâm";break;
                        case 3: can="Quý";break;
                        case 4: can="Giáp";break;
                        case 5: can="Ất";break;
                        case 6: can="Bính";break;
                        case 7: can="Đinh";break;
                        case 8: can="Mậu";break;
                        default: can="Ký";
                    }switch (dl%12){
                        case 0: chi="Thân";break;
                        case 1: chi="Dậu";break;
                        case 2: chi="Tuất";break;
                        case 3: chi="Hợi";break;
                        case 4: chi="Tý";break;
                        case 5: chi="Sửu";break;
                        case 6: chi="Dần";break;
                        case 7: chi="Mẹo";break;
                        case 8: chi="Thìn";break;
                        case 9: chi="Tỵ";break;
                        case 10: chi="Ngọ";break;
                        default: chi="Mùi";
                    }
                    am.setText(can+" "+chi);
                }
                else {
                    duong.setText("");
                }
            }
        });
    }
}