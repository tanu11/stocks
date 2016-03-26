package com.example.tanvi.stocks21;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by tanvi on 25-03-2016.
 */
public class DetailFragment extends Fragment {
  Stock s;
    TextView stockName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.detail_fragment,container,false);
        stockName = (TextView) v.findViewById(R.id.detailFragmentNameTextView);
        Bundle b = getArguments();
        if (b != null) {
            s = (Stock)b.getSerializable("selected");
            if (s != null)
                stockName.setText(s.companyName);
            else
                stockName.setText("abccccccc");
        }

     return v;
    }
    public void setStock(Stock s) {
        this.s = s;
       stockName.setText(s.companyName);
    }
}
