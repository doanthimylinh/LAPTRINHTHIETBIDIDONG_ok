package com.example.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomGrid extends BaseAdapter {
    private Context mContext;
    private final String[] sanpham;
    private final int[] Imageid;
    private final String[] gia;

    public CustomGrid(Context c, String[] sanpham, int[] Imageid, String[] gia) {
        mContext = c;
        this.sanpham = sanpham;
        this.Imageid = Imageid;
        this.gia = gia;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return sanpham.length;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.custom_gridview, null);
            TextView tv_sanpham = (TextView) grid.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            TextView tv_gia = (TextView) grid.findViewById(R.id.grid_gia);
            tv_sanpham.setText(sanpham[position]);
            tv_gia.setText(gia[position]);
            imageView.setImageResource(Imageid[position]);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}
