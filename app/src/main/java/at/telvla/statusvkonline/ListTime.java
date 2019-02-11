package at.telvla.statusvkonline;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
    AdapterTime adapters;
    Context context;
    String file_list_time = "file_list_time";
    String value_list_time = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartAppSDK.init(this, YOURAPPID, false);

        setContentView(R.layout.list_time_wrap);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.logo_vk);*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        context = MyApplication.getContext();
        listView = findViewById(R.id.listView);

        try {
            value_list_time = new File_RQ().File_Read(context, file_list_time);
            if (value_list_time.equals("")) {
                value_list_time = "";
            }
        } catch (Exception e) {
            value_list_time = "";
        }

        List<String> aryrr = new ArrayList<>();
        String[] mas_list_time = value_list_time.split(",");
        int mas_list_time_count = mas_list_time.length;
        for (int i = 0; i < mas_list_time_count; i++) {
            aryrr.add(mas_list_time[i]);
        }

        adapters = new AdapterTime(getApplicationContext(), aryrr);
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
        //finish();
    }
}