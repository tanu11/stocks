package com.example.tanvi.stocks21;

/**
 * Created by tanvi on 22-03-2016.
 */


        import android.os.AsyncTask;
        import android.util.Log;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.IOException;
        import java.io.InputStream;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.Scanner;

/**
 * Created by manishakhattar on 19/03/16.
 */
public class StocksAsyncTask extends AsyncTask<String, Void, Stock[]> {

    StocksAsyncTaskInterface listener;
    // StocksActivity currentActivity;
    public StocksAsyncTask(StocksAsyncTaskInterface listener){
        this.listener = listener;
        //  this.currentActivity = currentActivity;
    }

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p/>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected Stock[] doInBackground(String... params) {

        String urlString = params[0];
        //  params[1];
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream == null) {
                return null;
            }
            Scanner s = new Scanner(inputStream);
            StringBuffer output = new StringBuffer();
            while (s.hasNext()) {
                output.append(s.nextLine());
            }
            Log.i("jsondata", output.toString());

            return parseStocksJSON(output.toString());

        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {
            return null;
        }

    }

    private Stock[] parseStocksJSON(String output) {
        try {
            JSONObject obj = new JSONObject(output);
            JSONObject query = obj.getJSONObject("query");
            JSONObject results = query.getJSONObject("results");
            JSONArray quotes = results.getJSONArray("quote");
            Stock[] stocks = new Stock[quotes.length()];

            for(int i = 0; i < quotes.length(); i++){
                JSONObject currentStock = quotes.getJSONObject(i);
                Stock s = new Stock();
                s.companyName = currentStock.getString("Name");
                s.ticker = currentStock.getString("symbol");
                s.currentPrice = Double.parseDouble(currentStock.getString("Bid"));
                s.percentChange =  Double.parseDouble(currentStock.getString("Change"));
                stocks[i] = s;
            }
            return stocks;

        } catch (JSONException e) {
            return null;
        }

    }

    @Override
    protected void onPostExecute(Stock[] stocks) {
        //super.onPostExecute(stocks);
        if(listener != null){

            listener.onTaskComplete(stocks);


        }

        //  currentActivity.stocks= stocks;
    }

    public interface StocksAsyncTaskInterface{
        void onTaskComplete(Stock[] stocks);
    }

}
