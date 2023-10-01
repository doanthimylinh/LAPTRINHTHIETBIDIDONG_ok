package com.mhxx307.lavominhquan_tk3_fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements ProductDataAbstract {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void sendDataProduct(Product product) {
        // get FragmentProductInfo when activity is landscape
        FragmentProductInfo fragmentProductInfo = (FragmentProductInfo) getFragmentManager().findFragmentById(R.id.fragment_product_info);

        Configuration configuration = getResources().getConfiguration();

        // if activity is landscape(HAVE 2 FRAGMENTS, fragment_list_product & fragment_product_info): use setInfo in FragmentProductInfo class
        if (fragmentProductInfo != null && configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragmentProductInfo.setInfo(product);
        }else {
            // if activity not landscape(have 1 fragment, fragment_list_product): intent to activity 2
            Intent intent = new Intent(MainActivity.this, ProductInfoActivity.class);
            intent.putExtra("product", product);
            startActivity(intent);
        }
    }
}