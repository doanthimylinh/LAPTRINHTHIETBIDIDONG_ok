package com.example.bt3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        EditText et_HoTen=findViewById(R.id.editText_HoTen);
        EditText et_NamSinh=findViewById(R.id.editText_NamSinh);
        Button bt_Submit=findViewById(R.id.bt_Submit);
        bt_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent();
                intent.putExtra("ht", et_HoTen.getText().toString());
                intent.putExtra("ns", et_NamSinh.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}