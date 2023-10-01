package com.example.diolog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlertDialog.Builder mydialog= new AlertDialog.Builder(this);
        mydialog.setTitle("Thông Báo");
        final  CharSequence item[]={"Đỏ", "Vàng", "Cam"};
        final  boolean check[]={false, false, false};

        mydialog.setMultiChoiceItems(item, check, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                check[i]= b;
//                Toast.makeText(MainActivity.this, item[i].toString(), Toast.LENGTH_SHORT).show();
            }
        });
        mydialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String st= "";
                for(int j=0; j<item.length; j++){
                    if(check[j]==true) {
                        st +=item[j]. toString()+" ";
                        Toast.makeText(MainActivity.this, st, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
//        mydialog.setSingleChoiceItems(item, -1, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(MainActivity.this, item[i].toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        mydialog.setItems(item, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(MainActivity.this, item[i].toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        mydialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(MainActivity.this, "Bạn đã chọn yes", Toast.LENGTH_SHORT).show();
//            }
//        });
//        mydialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(MainActivity.this, "Bạn đã chọn no", Toast.LENGTH_SHORT).show();
//            }
//        });
        AlertDialog dialog= mydialog.create();
        dialog.show();
    }
}