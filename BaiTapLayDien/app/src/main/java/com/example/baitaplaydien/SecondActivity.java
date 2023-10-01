package com.example.baitaplaydien;

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
        EditText et_HeSoA=findViewById(R.id.editText_HeSoA);
        EditText et_HeSoB=findViewById(R.id.editText_HeSoB);
        EditText et_HeSoC=findViewById(R.id.editText_HeSoB);
        Button bt_Submit=findViewById(R.id.bt_Submit);
        bt_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent();
                intent.putExtra("a", et_HeSoA.getText().toString());
                intent.putExtra("b", et_HeSoB.getText().toString());
                intent.putExtra("c", et_HeSoC.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}