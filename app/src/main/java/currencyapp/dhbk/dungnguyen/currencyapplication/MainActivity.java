package currencyapp.dhbk.dungnguyen.currencyapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends Activity {

    ListView lvCurrency;
    ArrayList<Currency> arrayCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvCurrency = (ListView) findViewById(R.id.ListViewCurrency);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute("http://api.fixer.io/latest?base=USD");
            }
        });
    }

    private class ReadJSON extends AsyncTask<String, Integer, String > {

        @Override
        protected String doInBackground(String... params) {
            String kq = docNoiDung_Tu_URL(params[0]);
            return kq;
        }

        @Override
        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            try {
                arrayCurrency = new ArrayList<Currency>();

                JSONObject root = new JSONObject(s);
                JSONObject rates = root.getJSONObject("rates");

                arrayCurrency.add(new Currency("AUD",Float.parseFloat(rates.getString("AUD"))));
                arrayCurrency.add(new Currency("BGN",Float.parseFloat(rates.getString("BGN"))));
                arrayCurrency.add(new Currency("BRL",Float.parseFloat(rates.getString("BRL"))));
                arrayCurrency.add(new Currency("CAD",Float.parseFloat(rates.getString("CAD"))));
                arrayCurrency.add(new Currency("CHF",Float.parseFloat(rates.getString("CHF"))));
                arrayCurrency.add(new Currency("CNY",Float.parseFloat(rates.getString("CNY"))));
                arrayCurrency.add(new Currency("CZK",Float.parseFloat(rates.getString("CZK"))));
                arrayCurrency.add(new Currency("DKK",Float.parseFloat(rates.getString("DKK"))));
                arrayCurrency.add(new Currency("GBP",Float.parseFloat(rates.getString("GBP"))));
                arrayCurrency.add(new Currency("HKD",Float.parseFloat(rates.getString("HKD"))));
                arrayCurrency.add(new Currency("HRK",Float.parseFloat(rates.getString("HRK"))));
                arrayCurrency.add(new Currency("HUF",Float.parseFloat(rates.getString("HUF"))));
                arrayCurrency.add(new Currency("IDR",Float.parseFloat(rates.getString("IDR"))));
                arrayCurrency.add(new Currency("ILS",Float.parseFloat(rates.getString("ILS"))));
                arrayCurrency.add(new Currency("INR",Float.parseFloat(rates.getString("INR"))));
                arrayCurrency.add(new Currency("JPY",Float.parseFloat(rates.getString("JPY"))));
                arrayCurrency.add(new Currency("KRW",Float.parseFloat(rates.getString("KRW"))));
                arrayCurrency.add(new Currency("MXN",Float.parseFloat(rates.getString("MXN"))));
                arrayCurrency.add(new Currency("MYR",Float.parseFloat(rates.getString("MYR"))));
                arrayCurrency.add(new Currency("NOK",Float.parseFloat(rates.getString("NOK"))));
                arrayCurrency.add(new Currency("NZD",Float.parseFloat(rates.getString("NZD"))));
                arrayCurrency.add(new Currency("PHP",Float.parseFloat(rates.getString("PHP"))));
                arrayCurrency.add(new Currency("PLN",Float.parseFloat(rates.getString("PLN"))));
                arrayCurrency.add(new Currency("RON",Float.parseFloat(rates.getString("RON"))));
                arrayCurrency.add(new Currency("RUB",Float.parseFloat(rates.getString("RUB"))));
                arrayCurrency.add(new Currency("SEK",Float.parseFloat(rates.getString("SEK"))));
                arrayCurrency.add(new Currency("SGD",Float.parseFloat(rates.getString("SGD"))));
                arrayCurrency.add(new Currency("THB",Float.parseFloat(rates.getString("THB"))));
                arrayCurrency.add(new Currency("TRY",Float.parseFloat(rates.getString("TRY"))));
                arrayCurrency.add(new Currency("ZAR",Float.parseFloat(rates.getString("ZAR"))));
                arrayCurrency.add(new Currency("EUR",Float.parseFloat(rates.getString("EUR"))));

                CurrencyAdapter adapter = new CurrencyAdapter(
                        MainActivity.this,
                        R.layout.currency_row,
                        arrayCurrency
                );

                lvCurrency.setAdapter(adapter);

                lvCurrency.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent nextpage = new Intent(MainActivity.this, BaseCurrencyActivity.class);
                        nextpage.putExtra("BaseCurrencyCode", arrayCurrency.get(position).CurrencyCode);
                        nextpage.putExtra("Transfer", arrayCurrency.get(position).Transfer);
                        startActivity(nextpage);
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static String docNoiDung_Tu_URL(String theUrl)
    {
        StringBuilder content = new StringBuilder();

        try
        {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }

}