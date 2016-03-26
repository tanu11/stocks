package com.example.tanvi.stocks21;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by tanvi on 24-03-2016.
 */
public class StockListFragment extends Fragment implements StocksAsyncTask.StocksAsyncTaskInterface {
    ArrayList<Stock> stocks;
    StockArrayAdapter adapter;
    public interface StockListFragmentInterface {
        void itemClicked(Stock s);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.stock_list_fragment,container,false);
        ListView lv=(ListView)v.findViewById(R.id.fragmentListView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Stock s = stocks.get(position);
                StockListFragmentInterface listener = (StockListFragmentInterface)getActivity();
                listener.itemClicked(s);
               // Toast.makeText(getActivity(),s.companyName,Toast.LENGTH_LONG).show();
            }
        });
        stocks = new ArrayList<Stock>();
//        //  stocks = new Stock[0];
        StocksAsyncTask asyncTask = new StocksAsyncTask(this);
        asyncTask.execute(getUrlString(), "abc");
        adapter = new StockArrayAdapter(getActivity(), stocks);
        lv.setAdapter(adapter);
        return v;
    }

    public String getUrlString() {
        return "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22YHOO%22%2C%22GOOG%22%2C%22MSFT%22%2C%22AMZN%22)%0A%09%09&env=http%3A%2F%2Fdatatables.org%2Falltables.env&format=json";
    }


    @Override
    public void onTaskComplete(Stock[] stocks) {
        for(int i = 0; i < stocks.length; i++){
            this.stocks.add(stocks[i]);
            // this.stock[i] = stock[i];
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}
