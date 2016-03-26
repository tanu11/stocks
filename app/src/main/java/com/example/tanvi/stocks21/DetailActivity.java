package com.example.tanvi.stocks21;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        DetailFragment detailFragment=(DetailFragment)getFragmentManager().findFragmentById(R.id.detailviewfragment);
        Intent i=getIntent();
       Stock  s=(Stock) i.getSerializableExtra("selected_stock");
        detailFragment.setStock(s);
    }
}
