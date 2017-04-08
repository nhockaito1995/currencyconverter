package currencyapp.dhbk.dungnguyen.currencyapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nhockaito1995 on 08/04/2017.
 */

public class CurrencyAdapter extends BaseAdapter {
    private Context myContext;
    private int myLayout;
    private List<Currency> arrayCurrency;

    public CurrencyAdapter(Context context, int layout, List<Currency> CurrencyList){
        myContext = context;
        myLayout = layout;
        arrayCurrency = CurrencyList;
    }

    @Override
    public int getCount() {
        return arrayCurrency.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(myLayout, null);

        TextView txtCurrencyCode = (TextView) convertView.findViewById(R.id.textViewCurrencyCode);
        txtCurrencyCode.setText(arrayCurrency.get(position).CurrencyCode);

        TextView txtTransfer = (TextView) convertView.findViewById(R.id.textViewTransfer);
        txtTransfer.setText(String.valueOf(arrayCurrency.get(position).Transfer));

        TextView txtTypeCurrency = (TextView) convertView.findViewById(R.id.textViewTypeCurrency);

        return convertView;
    }
}
