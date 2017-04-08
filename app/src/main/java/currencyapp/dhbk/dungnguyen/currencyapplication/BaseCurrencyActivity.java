package currencyapp.dhbk.dungnguyen.currencyapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class BaseCurrencyActivity extends AppCompatActivity {

    ListView lvBaseCurrency;
    ArrayList<String> arrayBaseCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_currency);

        setContentView(R.layout.activity_base_currency);
        lvBaseCurrency = (ListView) findViewById(R.id.ListViewBaseCurrency);
        arrayBaseCurrency = new ArrayList<String>();

        arrayBaseCurrency.add("AUD");
        arrayBaseCurrency.add("BGN");
        arrayBaseCurrency.add("BRL");
        arrayBaseCurrency.add("CAD");
        arrayBaseCurrency.add("CHF");
        arrayBaseCurrency.add("CNY");
        arrayBaseCurrency.add("CZK");
        arrayBaseCurrency.add("DKK");
        arrayBaseCurrency.add("GBP");
        arrayBaseCurrency.add("HKD");
        arrayBaseCurrency.add("HRK");
        arrayBaseCurrency.add("HUF");
        arrayBaseCurrency.add("IDR");
        arrayBaseCurrency.add("ILS");
        arrayBaseCurrency.add("INR");
        arrayBaseCurrency.add("JPY");
        arrayBaseCurrency.add("KRW");
        arrayBaseCurrency.add("MXN");
        arrayBaseCurrency.add("MYR");
        arrayBaseCurrency.add("NOK");
        arrayBaseCurrency.add("NZD");
        arrayBaseCurrency.add("PHP");
        arrayBaseCurrency.add("PLN");
        arrayBaseCurrency.add("RON");
        arrayBaseCurrency.add("RUB");
        arrayBaseCurrency.add("SEK");
        arrayBaseCurrency.add("SGD");
        arrayBaseCurrency.add("THB");
        arrayBaseCurrency.add("TRY");
        arrayBaseCurrency.add("ZAR");
        arrayBaseCurrency.add("EUR");

        ArrayAdapter adapter = new ArrayAdapter(
                BaseCurrencyActivity.this,
                android.R.layout.simple_list_item_1,
                arrayBaseCurrency
        );

        lvBaseCurrency.setAdapter(adapter);

        lvBaseCurrency.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent nextpage = new Intent(BaseCurrencyActivity.this, TransferCurrencyActivity.class);

                startActivity(nextpage);
            }
        });
        //
    }

}