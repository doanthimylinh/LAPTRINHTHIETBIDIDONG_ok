package com.example.bt4_tuan2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        myDialog.setTitle("Thông tin cá nhân");

        EditText ed_Hoten = findViewById(R.id.ed_Hoten);
        EditText ed_cmnd = findViewById(R.id.ed_CMND);
        EditText ed_TTBS = findViewById(R.id.ed_TTBS);

        RadioButton bt_TrungCap = findViewById(R.id.bt_TrungCap);
        RadioButton bt_CaoDang = findViewById(R.id.bt_CaoDang);
        RadioButton bt_DaiHoc = findViewById(R.id.bt_DaiHoc);


        CheckBox bt_DocBao = findViewById(R.id.bt_DocBao);
        CheckBox bt_DocSach = findViewById(R.id.bt_DocSach);
        CheckBox bt_DocCoding = findViewById(R.id.bt_DocCoding);
        Button Submit = findViewById(R.id.Submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chinhQuy()){
                    String hoTen = ed_Hoten.getText().toString();
                    String cmnd = ed_cmnd.getText().toString();

                    String bang="";
                    if(bt_CaoDang.isChecked())
                        bang+=bt_CaoDang.getText()+"\n";
                    if(bt_DaiHoc.isChecked())
                        bang+=bt_DaiHoc.getText()+"\n";
                    if(bt_TrungCap.isChecked())
                        bang+=bt_TrungCap.getText()+"\n";

                    String soThich="";
                    if(bt_DocBao.isChecked())
                        soThich+=bt_DocBao.getText()+"\n";
                    if(bt_DocCoding.isChecked())
                        soThich+=bt_DocCoding.getText()+"\n";
                    if(bt_DocSach.isChecked())
                        soThich+=bt_DocSach.getText()+"\n";

                    String boSung=ed_TTBS.getText()+"";

                    String thongtin="Họ tên: "+hoTen+"\n";
                    thongtin+="CMND: "+cmnd+"\n";
                    thongtin+="Bằng cấp: "+bang+"";
                    thongtin+="Sở thích: "+soThich+"";
                    thongtin+="Thông tin bổ sung:\n";
                    thongtin+=boSung+"\n";
                    myDialog.setMessage(thongtin);
                    AlertDialog dialog = myDialog.create();
                    dialog.show();

                }

            }
        });

    }
    private boolean chinhQuy () {
        EditText ed_Hoten = findViewById(R.id.ed_Hoten);
        EditText ed_cmnd = findViewById(R.id.ed_CMND);
        CheckBox bt_DocBao = findViewById(R.id.bt_DocBao);
        CheckBox bt_DocSach = findViewById(R.id.bt_DocSach);
        CheckBox bt_DocCoding = findViewById(R.id.bt_DocCoding);
        String hoTen = ed_Hoten.getText().toString();
        String cmnd = ed_cmnd.getText().toString();
        if (hoTen.isEmpty() || hoTen.length() == 0 || hoTen.equals("") || hoTen == null) {
            Toast.makeText(MainActivity.this, "Họ tên có Dữ liệu!", Toast.LENGTH_LONG).show();
        } else if (hoTen.length() < 3) {
            Toast.makeText(MainActivity.this, "Họ tên phải chứa ít nhất 3 ký tự", Toast.LENGTH_LONG).show();
        }
        if (cmnd.length() > 0) {
            if (!cmnd.matches("\\d{9}")) {
                Toast.makeText(MainActivity.this, "CMND chỉ được nhập số và có đúng 9 chữ số", Toast.LENGTH_LONG).show();
                ed_cmnd.requestFocus();
                return  false;
            }
        }
        int soThich=0;
        if(bt_DocBao.isChecked())
            soThich++;
        if(bt_DocCoding.isChecked())
            soThich++;
        if(bt_DocSach.isChecked())
            soThich++;
        if (soThich<1){
            Toast.makeText(MainActivity.this, "Phải chọn ít nhất 1 sở thích", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}