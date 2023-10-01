package com.example.bt2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        myDialog.setTitle("Thông báo");

        myDialog.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Thêm thông tin mới", Toast.LENGTH_SHORT).show();
                EditText ed_taiKhoan=findViewById(R.id.taiKhoan);
                EditText ed_matKhau=findViewById(R.id.matKhau);
                recreate();
                ed_taiKhoan.setText("");
                ed_matKhau.setText("");
            }
        });
        myDialog.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Thoát khỏi chương trình", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        EditText ed_taiKhoan=findViewById(R.id.taiKhoan);
        EditText ed_matKhau=findViewById(R.id.matKhau);

        CheckBox save=findViewById(R.id.save);

        Button bt_DangNhap=findViewById(R.id.dangNhap);
        Button bt_Thoat=findViewById(R.id.thoat);




        bt_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taikhoan = ed_taiKhoan.getText().toString();
                String matkhau = ed_matKhau.getText().toString();
                if (taikhoan .isEmpty() || taikhoan .length() == 0 || taikhoan .equals("") || taikhoan == null){
                    Toast.makeText(MainActivity.this,"Tài khoản không được để trống",Toast.LENGTH_LONG).show();

                } if (matkhau .isEmpty() || matkhau .length() == 0 || matkhau .equals("") || matkhau == null){
                    Toast.makeText(MainActivity.this,"Mật khẩu không được để trống",Toast.LENGTH_LONG).show();

                }else {
                    if(save.isChecked()){
                        Toast.makeText(MainActivity.this, "Chào mừng bạn đăng nhập hệ thống, thông tin của bạn đã được lưu", Toast.LENGTH_SHORT).show();
                        ed_taiKhoan.setText("");
                        ed_matKhau.setText("");
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Chào mừng bạn đăng nhập hệ thống, thông tin của bạn không được lưu", Toast.LENGTH_SHORT).show();
                        ed_taiKhoan.setText("");
                        ed_matKhau.setText("");
                    }
                }

            }
        });
        bt_Thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mes = "Bạn có muốn thoát không";
                myDialog.setMessage(mes);
                AlertDialog dialog = myDialog.create();
                dialog.show();
            }
        });
    }
}