package com.mhxx307.lavominhquan_tk3_fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductInfoActivity extends AppCompatActivity {

    ImageView imgProduct;
    TextView txtName, txtCotnent, txtPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra("product");

        imgProduct = findViewById(R.id.imgProduct);
        txtName = findViewById(R.id.txt_Name);
        txtCotnent = findViewById(R.id.txt_Content);
        txtPrice = findViewById(R.id.txt_price);

        imgProduct.setImageResource(product.getImg());
        txtName.setText(product.getTenSanPham());
        txtCotnent.setText(product.getThongTinSanPham());
        txtPrice.setText("$"+product.getGiaSanPham());

    }
}