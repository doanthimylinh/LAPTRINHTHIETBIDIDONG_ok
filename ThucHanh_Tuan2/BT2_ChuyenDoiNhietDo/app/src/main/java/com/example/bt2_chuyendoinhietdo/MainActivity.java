package com.example.bt2_chuyendoinhietdo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    EditText edtHeSoF,edtHeSoC;
    Button buttonCtoC, buttonCToF,buttonClear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonCtoC=findViewById(R.id.buttonCtoC);
        buttonCToF=findViewById(R.id.buttonCToF);

        edtHeSoF= findViewById(R.id.heSoF);
        edtHeSoC= findViewById(R.id.heSoC);

        buttonCToF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strHeSoC=edtHeSoC.getText().toString().trim();
                Double heSoC=Double.parseDouble(strHeSoC);
                Double CTF=heSoC*9/5+32;

                edtHeSoF.setText("" +CTF);

            }
        });
        buttonCtoC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strHeSoF=edtHeSoF.getText().toString().trim();
                Double heSoF=Double.parseDouble(strHeSoF);
                Double CTC= (heSoF - 32) * 5.0/9;

                edtHeSoC.setText(""+CTC);
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtHeSoC.setText("");
                edtHeSoF.setText("");
            }
        });

    }


}