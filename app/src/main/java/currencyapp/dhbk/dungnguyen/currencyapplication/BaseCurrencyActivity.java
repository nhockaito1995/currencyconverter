package currencyapp.dhbk.dungnguyen.currencyapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class BaseCurrencyActivity extends AppCompatActivity {

    ListView lvBaseCurrency;
    ArrayList<String> arrayBaseCurrency;
    TextView txvNote;

    public boolean setElement(String s, String x, ArrayList<String> n){
        if(s.equals(x)) return false;
        else{
            n.add(x);
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_currency);

        txvNote = (TextView) findViewById(R.id.textViewNote);
        Bundle bud = getIntent().getExtras();
        String currencynote = bud.getString("transfercurrency");
        txvNote.setText("Bạn có muốn chuyển đổi từ "+currencynote+" sang:");

        lvBaseCurrency = (ListView) findViewById(R.id.ListViewBaseCurrency);

        arrayBaseCurrency = new ArrayList<String>();
        setElement(currencynote,"AUD",arrayBaseCurrency);
        setElement(currencynote,"BGN",arrayBaseCurrency);
        setElement(currencynote,"BRL",arrayBaseCurrency);
        setElement(currencynote,"CAD",arrayBaseCurrency);
        setElement(currencynote,"CHF",arrayBaseCurrency);
        setElement(currencynote,"CNY",arrayBaseCurrency);
        setElement(currencynote,"CZK",arrayBaseCurrency);
        setElement(currencynote,"DKK",arrayBaseCurrency);
        setElement(currencynote,"GBP",arrayBaseCurrency);
        setElement(currencynote,"HKD",arrayBaseCurrency);
        setElement(currencynote,"HRK",arrayBaseCurrency);
        setElement(currencynote,"HUF",arrayBaseCurrency);
        setElement(currencynote,"IDR",arrayBaseCurrency);
        setElement(currencynote,"ILS",arrayBaseCurrency);
        setElement(currencynote,"INR",arrayBaseCurrency);
        setElement(currencynote,"JPY",arrayBaseCurrency);
        setElement(currencynote,"KRW",arrayBaseCurrency);
        setElement(currencynote,"MXN",arrayBaseCurrency);
        setElement(currencynote,"MYR",arrayBaseCurrency);
        setElement(currencynote,"NOK",arrayBaseCurrency);
        setElement(currencynote,"NZD",arrayBaseCurrency);
        setElement(currencynote,"PHP",arrayBaseCurrency);
        setElement(currencynote,"PLN",arrayBaseCurrency);
        setElement(currencynote,"RON",arrayBaseCurrency);
        setElement(currencynote,"RUB",arrayBaseCurrency);
        setElement(currencynote,"SEK",arrayBaseCurrency);
        setElement(currencynote,"SGD",arrayBaseCurrency);
        setElement(currencynote,"THB",arrayBaseCurrency);
        setElement(currencynote,"TRY",arrayBaseCurrency);
        setElement(currencynote,"ZAR",arrayBaseCurrency);
        setElement(currencynote,"EUR",arrayBaseCurrency);

        ArrayAdapter adapter = new ArrayAdapter(
                BaseCurrencyActivity.this,
                android.R.layout.simple_list_item_1,
                arrayBaseCurrency
        );

        lvBaseCurrency.setAdapter(adapter);

        lvBaseCurrency.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bd = getIntent().getExtras();
                String currencycode = bd.getString("transfercurrency");
                Intent nextpage = new Intent(BaseCurrencyActivity.this, TransferCurrencyActivity.class);
                nextpage.putExtra("transfermoney", currencycode);
                nextpage.putExtra("basemoney", arrayBaseCurrency.get(position));
                startActivity(nextpage);
            }
        });
        //
    }
}
