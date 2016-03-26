package com.example.tanvi.stocks21;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tanvi on 22-03-2016.
 */
public class StockArrayAdapter extends ArrayAdapter<Stock> {

    Context context;
    ArrayList<Stock> stocks;

    public StockArrayAdapter(Context context, ArrayList<Stock> objects) {
        super(context, 0, objects);
        this.context = context;
        this.stocks = objects;
    }

   static class ViewHolder {
        TextView stockName;
        TextView stockTicker;
        TextView stockPrice;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.stock_item_layout, null);
            ViewHolder vh = new ViewHolder();
            vh.stockName = (TextView) convertView.findViewById(R.id.textView_stockName);
            vh.stockTicker = (TextView) convertView.findViewById(R.id.textView_stockTicker);
            vh.stockPrice = (TextView) convertView.findViewById(R.id.textView_stockPrice);
            convertView.setTag(vh);

        }
        Stock currentStock = stocks.get(position);
        ViewHolder vh = (ViewHolder) convertView.getTag();
        vh.stockName.setText(currentStock.companyName);
        vh.stockTicker.setText(currentStock.ticker);
        vh.stockPrice.setText(currentStock.currentPrice + "/" + currentStock.percentChange);

        return convertView;
    }
}