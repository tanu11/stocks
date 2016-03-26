package com.example.tanvi.stocks21;


        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.widget.FrameLayout;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.Serializable;
        import java.util.ArrayList;

public class StocksActivity extends AppCompatActivity implements StockListFragment.StockListFragmentInterface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stocks);

    }


    @Override
    public void itemClicked(Stock s) {

        Toast.makeText(this, s.companyName, Toast.LENGTH_LONG).show();
        FrameLayout detailLayout = (FrameLayout)findViewById(R.id.DetailFramelayout);

        if (detailLayout == null) {
            Intent i = new Intent();
            i.setClass(this, DetailActivity.class);
          i.putExtra("selected_stock",s);
            startActivity(i);
        }
        else {
           /* DetailFragment df = (DetailFragment) getFragmentManager().findFragmentById(R.id.detailviewfragment);

                df.setStock(s);
        */   DetailFragment detailFragment=new DetailFragment();
            Bundle b=new Bundle();
            b.putSerializable("selected",s);

            detailFragment.setArguments(b);
            getFragmentManager().beginTransaction().replace(R.id.DetailFramelayout,detailFragment).commit();

        }



    }
}
