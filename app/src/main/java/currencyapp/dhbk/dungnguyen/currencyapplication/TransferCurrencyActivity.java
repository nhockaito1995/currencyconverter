package currencyapp.dhbk.dungnguyen.currencyapplication;

import android.os.Bundle;
import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;

public class TransferCurrencyActivity extends Activity {
    TextView from, to;
    EditText editForm, editTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_currency);

        from = (TextView) findViewById(R.id.textViewFrom);
        to = (TextView) findViewById(R.id.textViewTo);
        editForm = (EditText) findViewById(R.id.editTextForm);
        editTo = (EditText) findViewById(R.id.editTextTo);


    }

}
