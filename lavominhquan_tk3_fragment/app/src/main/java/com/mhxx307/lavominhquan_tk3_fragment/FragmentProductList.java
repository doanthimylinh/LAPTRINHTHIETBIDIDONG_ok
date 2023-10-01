package com.mhxx307.lavominhquan_tk3_fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FragmentProductList extends Fragment {

    ArrayList<Product> productList;
    ProductAdapter productAdapter;
    ProductDataAbstract productDataAbstract;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        GridView grid = view.findViewById(R.id.grid);
        productDataAbstract = (ProductDataAbstract) getActivity();

        productList = new ArrayList<>();
        createArrayProduct();

        productAdapter = new ProductAdapter(getActivity(), R.layout.layout_product, productList);
        grid.setAdapter(productAdapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                productDataAbstract.sendDataProduct(productList.get(i));
            }
        });

        return view;
    }

    private void createArrayProduct() {
        productList.add(new Product(R.drawable.kho_ga, "Khô gà", 69000, "Khô gà thơm ngon, cay nồng"));
        productList.add(new Product(R.drawable.lanh_dao_don_gian, "Sách lãnh đạo", 69000, "Sách lãnh đạo đơn giản"));
        productList.add(new Product(R.drawable.lanh_dao_don_gian, "Sách lãnh đạo", 69000, "Sách lãnh đạo đơn giản"));
        productList.add(new Product(R.drawable.xe_can_cau, "Xe cần cẩu", 69000, "Xe cần cẩu mô hình cho trẻ con"));
        productList.add(new Product(R.drawable.ca_nau_lau, "Ca nấu lẩu", 69000, "Ca nấu lẩu đa năng"));
        productList.add(new Product(R.drawable.do_choi_mo_hinh, "Xe mô hình", 69000, "Mô hình đồ chơi cho trẻ con"));
        productList.add(new Product(R.drawable.do_choi_mo_hinh, "Xe mô hình", 69000, "Mô hình đồ chơi cho trẻ con"));
        productList.add(new Product(R.drawable.kho_ga, "Khô gà", 69000, "Khô gà thơm ngon, cay nồng"));
        productList.add(new Product(R.drawable.lanh_dao_don_gian, "Sách lãnh đạo", 69000, "Sách lãnh đạo đơn giản"));
        productList.add(new Product(R.drawable.hieu_long_tre_con, "Hiểu lòng trẻ con", 69000, "Sách hay hiểu lòng trẻ con"));
        productList.add(new Product(R.drawable.xe_can_cau, "Xe cần cẩu", 69000, "Xe cần cẩu mô hình cho trẻ con"));
        productList.add(new Product(R.drawable.ca_nau_lau, "Ca nấu lẩu", 69000, "Ca nấu lẩu đa năng"));
        productList.add(new Product(R.drawable.do_choi_mo_hinh, "Xe mô hình", 69000, "Mô hình đồ chơi cho trẻ con"));
        productList.add(new Product(R.drawable.ca_nau_lau, "Ca nấu lẩu", 69000, "Ca nấu lẩu đa năng"));
        productList.add(new Product(R.drawable.do_choi_mo_hinh, "Xe mô hình", 69000, "Mô hình đồ chơi cho trẻ con"));
        productList.add(new Product(R.drawable.hieu_long_tre_con, "Hiểu lòng trẻ con", 69000, "Sách hay hiểu lòng trẻ con"));
        productList.add(new Product(R.drawable.lanh_dao_don_gian, "Sách lãnh đạo", 69000, "Sách lãnh đạo đơn giản"));
        productList.add(new Product(R.drawable.hieu_long_tre_con, "Hiểu lòng trẻ con", 69000, "Sách hay hiểu lòng trẻ con"));
    }
}
