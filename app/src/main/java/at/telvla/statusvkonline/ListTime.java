package at.telvla.statusvkonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import at.telvla.statusvk.R;


public class ListTime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_list_time);


        ListView listView = (ListView)findViewById(R.id.listView);

        List<String> list = new ArrayList<>();

        list.add("12.12.2019");
        list.add("1.12.2019");
        list.add("11.12.2019");
        list.add("02.12.2019");
        list.add("05.12.2019");


        AdapterTime adapters = new AdapterTime(getApplicationContext(), list);

        listView.setAdapter(adapters);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ListTime.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
