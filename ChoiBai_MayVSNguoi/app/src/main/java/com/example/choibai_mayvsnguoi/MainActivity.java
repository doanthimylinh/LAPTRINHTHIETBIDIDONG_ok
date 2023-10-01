package com.example.choibai_mayvsnguoi;

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
    int manghinhbai[]={
            R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,
            R.drawable.c6,R.drawable.c7,R.drawable.c8,R.drawable.c9,R.drawable.c10,
            R.drawable.cj,R.drawable.cq,R.drawable.ck,
            R.drawable.d1,R.drawable.d2,R.drawable.d3,R.drawable.d4,R.drawable.d5,
            R.drawable.d6,R.drawable.d7,R.drawable.d8,R.drawable.d9,R.drawable.d10,
            R.drawable.dj,R.drawable.dq,R.drawable.dk,
            R.drawable.h1,R.drawable.h2,R.drawable.h3,R.drawable.h4,R.drawable.h5,
            R.drawable.h6,R.drawable.h7,R.drawable.h8,R.drawable.h9,R.drawable.h10,
            R.drawable.hj,R.drawable.hq,R.drawable.hk,
            R.drawable.s1,R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5,
            R.drawable.s6,R.drawable.s7,R.drawable.s8,R.drawable.s9,R.drawable.s10,
            R.drawable.sj,R.drawable.sq,R.drawable.sk};


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

        ImageView iv1 = findViewById(R.id.imageView1);
        ImageView iv2 = findViewById(R.id.imageView2);
        ImageView iv3 = findViewById(R.id.imageView3);
        ImageView iv4 = findViewById(R.id.imageView4);
        ImageView iv5 = findViewById(R.id.imageView5);
        ImageView iv6 = findViewById(R.id.imageView6);

        TextView may = findViewById(R.id.May);
        TextView nguoi = findViewById(R.id.Nguoi);
        TextView tv_ketquamay = findViewById(R.id.tv_KetQuaMay);
        TextView tv_ketquanguoi = findViewById(R.id.tv_KetQuaNguoi);
        Button bt_rutbai = findViewById(R.id.bt_rutbai);

        bt_rutbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int resultMay = Integer.parseInt(may.getText().toString().trim());
                int resultNguoi = Integer.parseInt(nguoi.getText().toString().trim());


                if ((resultMay + resultNguoi) != 10) {
                    int value[] = layBaSoNgauNhien(0, 51);
                    int valuemay[] = {value[0], value[2], value[4]};
                    iv1.setImageResource(manghinhbai[valuemay[0]]);
                    iv2.setImageResource(manghinhbai[valuemay[1]]);
                    iv3.setImageResource(manghinhbai[valuemay[2]]);
                    tv_ketquamay.setText(tinhKetQua(valuemay));


                    int valuenguoi[] = {value[1], value[3], value[5]};
                    iv4.setImageResource(manghinhbai[valuenguoi[0]]);
                    iv5.setImageResource(manghinhbai[valuenguoi[1]]);
                    iv6.setImageResource(manghinhbai[valuenguoi[2]]);
                    tv_ketquanguoi.setText(tinhKetQua(valuenguoi));


                    int nutMay=tinhSoNut(valuemay);
                    int nutNguoi=tinhSoNut(valuenguoi);

                    if (nutMay > nutNguoi) {
                        resultMay += 1;
                        may.setText(String.valueOf(resultMay));
                    } else if (nutMay < nutNguoi) {
                        resultNguoi += 1;
                        nguoi.setText(String.valueOf(resultNguoi));
                    }
                } else {
                    if (resultMay > resultNguoi){
                        String mes= "Bạn đã thua\n"+"Máy: "+resultMay+"\n"+"Bạn: "+resultNguoi;
                        myDialog.setMessage(mes);
                        AlertDialog dialog= myDialog.create();
                        dialog.show();
                    }
                    else  if (resultNguoi > resultMay){
                        String mes= "Bạn đã thắng\n"+"Bạn: "+resultNguoi+"\n"+"Máy: "+resultMay;
                        myDialog.setMessage(mes);
                        AlertDialog dialog= myDialog.create();
                        dialog.show();
                    }
                    else{
                        String mes= "Hòa nhau\n"+"Bạn: "+resultNguoi+"\n"+"Máy: "+resultMay;
                        myDialog.setMessage(mes);
                        AlertDialog dialog= myDialog.create();
                        dialog.show();
                    }
                }
            }

        });
    }

    private int tinhSoNut(int[] baso) {
        if (tinhSotay(baso) == 3) {
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
    private String tinhKetQua(int[] baso){
        String ketqua="";
        if(tinhSotay(baso)==3)
            ketqua="Kết quả: 3 tây";
        else {
            int tong=0;
            for(int i=0; i<baso.length; i++)
                if(baso[i] %13<10)
                    tong+= baso[i]%13+1;
            if(tong%10==0)
                ketqua="Kết quả bù, trong đó có "+tinhSotay(baso)+" tây.";
            else
                ketqua="Kết quả là "+(tong%10)+" nút, trong đó có "+tinhSotay(baso)+" tây";
        }
        return ketqua;
    }
    private int tinhSotay(int[]a){
        int k=0;
        for(int i=0; i<a.length; i++)
            if(a[i]%13>=10 && a[i]%13 <13)
                k++;
        return k;


    }
    //    lay 3 so ngau nhien
    private int[] layBaSoNgauNhien(int min, int max){
        int[] baSo= new int[6];
        int i=0;
        baSo[i++]=random(min,max);
        do {
            int k=random(min, max);
            if(!kiemTraTrung(k,baSo))
                baSo[i++]=k;
        }while (i<6);
        return baSo;
    }
    //    kiem ta trung
    private  boolean kiemTraTrung(int k, int[] a){
        for(int i=0; i< a.length; i++)
            if(a[i]==k)
                return true;
        return false;

    }
    //    lay gia tri ngau nhien
    private  int random(int min, int max){
        return min+(int)(Math.random()*((max-min)+1));

    }
}