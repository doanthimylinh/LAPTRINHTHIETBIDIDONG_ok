package com.example.fragment_tk3_kieu2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomGrid extends BaseAdapter {
    private Context context;
     int layout;
     ArrayList<SanPham> sanPhams;

    public CustomGrid(Context context, int layout, ArrayList<SanPham> sanPhams) {
        this.context = context;
        this.layout = layout;
        this.sanPhams = sanPhams;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return sanPhams.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);

            TextView tv_sanpham = (TextView) view.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView)view.findViewById(R.id.grid_image);
            TextView tv_gia = (TextView) view.findViewById(R.id.grid_gia);
             SanPham sanPham= sanPhams.get(position);
            tv_sanpham.setText(sanPham.getSanpham());
            imageView.setImageResource(sanPham.getHinh());
            tv_gia.setText(sanPham.getGia());

        return view;
    }
}
