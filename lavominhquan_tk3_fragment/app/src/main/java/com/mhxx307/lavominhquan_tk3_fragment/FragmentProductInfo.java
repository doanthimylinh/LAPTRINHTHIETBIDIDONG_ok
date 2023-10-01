package com.mhxx307.lavominhquan_tk3_fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class FragmentProductInfo extends Fragment {
    ImageView imgProduct;
    TextView txtName, txtContent, txtPrice;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_info, container, false);

        imgProduct = view.findViewById(R.id.imgProduct);
        txtName = view.findViewById(R.id.txt_Name);
        txtContent = view.findViewById(R.id.txt_Content);
        txtPrice = view.findViewById(R.id.txt_price);

        return view;
    }

    public void setInfo(Product product) {
        imgProduct.setImageResource(product.getImg());
        txtName.setText(product.getTenSanPham());
        txtContent.setText(product.getThongTinSanPham());
        txtPrice.setText("$"+product.getGiaSanPham());
    }


}
