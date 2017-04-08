package currencyapp.dhbk.dungnguyen.currencyapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;

public class TransferCurrencyActivity extends Activity {
    TextView from, to;
    EditText editForm, editTo;
    Button btnConvert;
    Float rateofmoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_currency);

        from = (TextView) findViewById(R.id.textViewFrom);
        to = (TextView) findViewById(R.id.textViewTo);
        editForm = (EditText) findViewById(R.id.editTextForm);
        editTo = (EditText) findViewById(R.id.editTextTo);
        btnConvert = (Button) findViewById(R.id.buttonConvert);

        Bundle bd = getIntent().getExtras();
        String transfermoney = bd.getString("transfermoney");
        String basemoney = bd.getString("basemoney");
        final String URL = "http://api.fixer.io/latest?base="+basemoney;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJSON().execute(URL);
            }
        });

        from.setText(transfermoney);
        to.setText(basemoney);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float a = Float.parseFloat(editForm.getText().toString());
                DecimalFormat df = new DecimalFormat("#.0000");
                editTo.setText(String.valueOf(df.format(a/rateofmoney)));
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
                Bundle bud = getIntent().getExtras();
                String transfermoney = bud.getString("transfermoney");

                JSONObject root = new JSONObject(s);
                JSONObject rates = root.getJSONObject("rates");
                rateofmoney = Float.parseFloat(rates.getString(transfermoney));

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
