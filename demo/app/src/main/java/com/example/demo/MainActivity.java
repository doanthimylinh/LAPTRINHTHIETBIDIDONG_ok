package com.example.demo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int manghinhbai[] = {
            R.drawable.c10, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5,
            R.drawable.c6, R.drawable.c7, R.drawable.c8, R.drawable.c9, R.drawable.c10,
            R.drawable.cj, R.drawable.cq, R.drawable.ck,
            R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5,
            R.drawable.d6, R.drawable.d7, R.drawable.d8, R.drawable.d9, R.drawable.d10,
            R.drawable.dj, R.drawable.dq, R.drawable.dk,
            R.drawable.h1, R.drawable.h2, R.drawable.h3, R.drawable.h4, R.drawable.h5,
            R.drawable.h6, R.drawable.h7, R.drawable.h8, R.drawable.h9, R.drawable.h10,
            R.drawable.hj, R.drawable.hq, R.drawable.hk,
            R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5,
            R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10,
            R.drawable.sj, R.drawable.sq, R.drawable.sk};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        myDialog.setTitle("Kết Quả");

        myDialog.setPositiveButton("Chơi tiếp", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Lượt chơi mới", Toast.LENGTH_SHORT).show();
                recreate();
            }
        });
        myDialog.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Thoát khỏi chương trình", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        //Máy
        ImageView igvM1 = findViewById(R.id.imageViewM1);
        ImageView igvM2 = findViewById(R.id.imageViewM2);
        ImageView igvM3 = findViewById(R.id.imageViewM3);
        //Người Chơi
        ImageView igvNg1 = findViewById(R.id.imageViewNg1);
        ImageView igvNg2 = findViewById(R.id.imageViewNg2);
        ImageView igvNg3 = findViewById(R.id.imageViewNg3);
        //Kết quả chi tiết
        TextView tv_M = findViewById(R.id.tv_kqM);
        TextView tv_Ng = findViewById(R.id.tv_kqNg);
        //Điểm cộng dồn
        TextView may = findViewById(R.id.May);
        TextView nguoi = findViewById(R.id.Nguoi);
        //Nút
        Button choi = findViewById(R.id.btn_play);
        choi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Tính điểm cộng dồn
                int mayW = Integer.parseInt(may.getText().toString().trim());
                int nguoiW = Integer.parseInt(nguoi.getText().toString().trim());
                if ((mayW + nguoiW) != 10) {

                    int[] valueMay = lay3songaunhien(0, 51);
                    igvM1.setImageResource(manghinhbai[valueMay[0]]);
                    igvM2.setImageResource(manghinhbai[valueMay[1]]);
                    igvM3.setImageResource(manghinhbai[valueMay[2]]);
                    tv_M.setText(tinhketqua(valueMay));

                    int[] valueNguoi = lay3songaunhien(0, 51);
                    igvNg1.setImageResource(manghinhbai[valueNguoi[0]]);
                    igvNg2.setImageResource(manghinhbai[valueNguoi[1]]);
                    igvNg3.setImageResource(manghinhbai[valueNguoi[2]]);
                    tv_Ng.setText(tinhketqua(valueNguoi));

                    int nutMay = tinhSoNut(valueMay);
                    int nutNguoi = tinhSoNut(valueNguoi);

                    if (nutMay > nutNguoi) {
                        mayW += 1;
                        may.setText(String.valueOf(mayW));
                    } else if (nutMay < nutNguoi) {
                        nguoiW += 1;
                        nguoi.setText(String.valueOf(nguoiW));
                    }
                } else {
                    if (mayW > nguoiW){
                        String mes= "LOSE\n"+"Máy: "+mayW+"\n"+"Bạn: "+nguoiW;
                        myDialog.setMessage(mes);
                        AlertDialog dialog= myDialog.create();
                        dialog.show();
                    }
                    else  if (nguoiW > mayW){
                        String mes= "WIN\n"+"Bạn: "+nguoiW+"\n"+"Máy: "+mayW;
                        myDialog.setMessage(mes);
                        AlertDialog dialog= myDialog.create();
                        dialog.show();
                    }
                    else{
                        String mes= "DRAW\n"+"Bạn: "+nguoiW+"\n"+"Máy: "+mayW;
                        myDialog.setMessage(mes);
                        AlertDialog dialog= myDialog.create();
                        dialog.show();
                    }
                }


            }
        });
    }


    // Lấy 3 số ngẫu nhiên ko trùng
    private int[] lay3songaunhien(int min, int max) {
        int[] baso = new int[3];
        int i = 0;
        baso[i++] = random(min, max);
        do {
            int k = random(min, max);
            if (!ktratrung(k, baso))
                baso[i++] = k;
        } while (i < 3);
        return baso;
    }

    //Chọn ngẫu nhiên trong khoảng xác định
    private int random(int min, int max) {
        return min + (int) (Math.random() * (max - min + 1));
    }

    //Kiểm tra giá trị trùng
    private boolean ktratrung(int k, int[] a) {
        for (int i = 0; i < a.length; i++)
            if (a[i] == k)
                return true;
        return false;
    }

    //Tính số lá Tây
    private int tinhsoTay(int[] a) {
        int k = 0;
        for (int i = 0; i < a.length; i++)
            if (a[i] % 13 >= 10 && a[i] % 13 < 13)
                k++;
        return k;
    }

    //Tính kết quả của 3 lá bài rút đc
    private String tinhketqua(int[] baso) {
        String KQ = "";
        if (tinhsoTay(baso) == 3)
            KQ = "Kết Quả: 3 tây";
        else {
            int tong = 0;
            for (int i = 0; i < baso.length; i++)
                if (baso[i] % 13 < 10)
                    tong += baso[i] % 13 + 1;
            if (tong % 10 == 0)
                KQ = "Bù, trong đó có " + tinhsoTay(baso) + " tây";
            else
                KQ = "Kết quả là " + tong % 10 + " nút, trong đó có " + tinhsoTay(baso) + " tây";
        }
        return KQ;
    }

    private int tinhSoNut(int[] baso) {
        if (tinhsoTay(baso) == 3) {
            return 10;
        } else {
            int tong = 0;
            for (int i = 0; i < baso.length; i++)
                if (baso[i] % 13 < 10)
                    tong += baso[i] % 13 + 1;
            if (tong % 10 == 0)
                return 0;
            else
                return tong % 10;
        }
    }
}