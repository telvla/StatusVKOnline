package at.telvla.statusvkonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.startapp.android.publish.adsCommon.StartAppSDK;

import java.util.ArrayList;
import java.util.List;

import at.telvla.statusvk.R;

public class ListTime extends AppCompatActivity {
    String YOURAPPID = "200214407";
    ListView listView;
    List<String> list;
    AdapterTime adapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //StartAppSDK.init(this, YOURAPPID, false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_list_time);

        listView = findViewById(R.id.listView);
        list = new ArrayList<>();

        list.add("12.12.2019");
        list.add("1.12.2019");
        list.add("11.12.2019");
        list.add("02.12.2019");
        list.add("05.12.2019");

        adapters = new AdapterTime(getApplicationContext(), list);
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
