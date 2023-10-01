package com.mhxx307.lavominhquan_tk3_fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ProductAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Product> sanPhams;

    public ProductAdapter(Context context, int layout, ArrayList<Product> sanPhams) {
        this.context = context;
        this.layout = layout;
        this.sanPhams = sanPhams;
    }

    @Override
    public int getCount() {
        return sanPhams.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflate.inflate(layout, null);

        ImageView imgView = view.findViewById(R.id.imageView);
        TextView txtTenSanPham = view.findViewById(R.id.txtTenSanPham);
        TextView txtGia = view.findViewById(R.id.txtGiaTien);

        Product sanPham = sanPhams.get(i);

        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        imgView.setImageResource(sanPham.getImg());
        txtTenSanPham.setText(sanPham.getTenSanPham());
        txtGia.setText(currencyFormatter.format(sanPham.getGiaSanPham()) + "");

        return view;
    }
}
