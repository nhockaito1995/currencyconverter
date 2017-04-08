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
import java.text.DecimalFormat;
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
            try {
                arrayCurrency = new ArrayList<Currency>();

                JSONObject root = new JSONObject(s);
                JSONObject rates = root.getJSONObject("rates");
                DecimalFormat df = new DecimalFormat("#.0000");

                arrayCurrency.add(new Currency("AUD",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("AUD"))))));
                arrayCurrency.add(new Currency("BGN",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("BGN"))))));
                arrayCurrency.add(new Currency("BRL",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("BRL"))))));
                arrayCurrency.add(new Currency("CAD",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("CAD"))))));
                arrayCurrency.add(new Currency("CHF",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("CHF"))))));
                arrayCurrency.add(new Currency("CNY",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("CNY"))))));
                arrayCurrency.add(new Currency("CZK",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("CZK"))))));
                arrayCurrency.add(new Currency("DKK",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("DKK"))))));
                arrayCurrency.add(new Currency("GBP",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("GBP"))))));
                arrayCurrency.add(new Currency("HKD",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("HKD"))))));
                arrayCurrency.add(new Currency("HRK",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("HRK"))))));
                arrayCurrency.add(new Currency("HUF",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("HUF"))))));
                arrayCurrency.add(new Currency("IDR",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("IDR"))))));
                arrayCurrency.add(new Currency("ILS",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("ILS"))))));
                arrayCurrency.add(new Currency("INR",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("INR"))))));
                arrayCurrency.add(new Currency("JPY",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("JPY"))))));
                arrayCurrency.add(new Currency("KRW",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("KRW"))))));
                arrayCurrency.add(new Currency("MXN",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("MXN"))))));
                arrayCurrency.add(new Currency("MYR",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("MYR"))))));
                arrayCurrency.add(new Currency("NOK",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("NOK"))))));
                arrayCurrency.add(new Currency("NZD",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("NZD"))))));
                arrayCurrency.add(new Currency("PHP",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("PHP"))))));
                arrayCurrency.add(new Currency("PLN",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("PLN"))))));
                arrayCurrency.add(new Currency("RON",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("RON"))))));
                arrayCurrency.add(new Currency("RUB",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("RUB"))))));
                arrayCurrency.add(new Currency("SEK",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("SEK"))))));
                arrayCurrency.add(new Currency("SGD",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("SGD"))))));
                arrayCurrency.add(new Currency("THB",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("THB"))))));
                arrayCurrency.add(new Currency("TRY",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("TRY"))))));
                arrayCurrency.add(new Currency("ZAR",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("ZAR"))))));
                arrayCurrency.add(new Currency("EUR",Float.parseFloat(df.format(1/Float.parseFloat(rates.getString("EUR"))))));

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
                        nextpage.putExtra("transfercurrency", arrayCurrency.get(position).CurrencyCode);
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