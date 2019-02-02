package at.telvla.statusvkonline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import at.telvla.statusvk.R;

public class AdapterTime extends ArrayAdapter<String> {
    List<String> contactList;
    Context context;
    LayoutInflater inflater;
    View rowView;
    TextView date_textview;

    public AdapterTime(Context context, List<String> list) {
        super(context, 0, list);
        contactList = list;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        rowView = inflater.inflate(R.layout.listtimeview, parent, false);
        date_textview = rowView.findViewById(R.id.date);
        date_textview.setText(contactList.get(position));
        return rowView;
    }
}