package at.telvla.statusvkonline;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.startapp.android.publish.adsCommon.StartAppSDK;
import com.victor.loading.rotate.RotateLoading;

import java.util.List;

import at.telvla.statusvk.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    EditText enter_id;
    TextView textview_servis;
    TextView textview_simple;
    String enter_idValue;
    String file_name = "id_vk";
    Boolean result_save;
    String get_id_file;
    Toast toast;
    Context context;
    Retrofit CallServer;
    API api;
    String YOURAPPID = "200214407";
    String check_code;
    String file_list_time = "file_list_time";
    RotateLoading rotateLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartAppSDK.init(this, YOURAPPID, false);

        setContentView(R.layout.activity_toolbar);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;
        startService(new Intent(this, PingService.class));

        textview_simple = findViewById(R.id.textview_simple);
        textview_servis = findViewById(R.id.textview_servis);
        enter_id = findViewById(R.id.enter_id);
        try {
            result_save = new File_RQ().File_Entry(context, file_list_time, "-");
            get_id_file = new File_RQ().File_Read(context, file_name);
            if (!get_id_file.equals("")) {
                enter_id.setText(get_id_file);
                textview_servis.setText("Данный id " + get_id_file + " отслеживается в фонновом режими");
            } else {
                textview_servis.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            enter_id.setText("");
        }
    }

    public void BtnSaveIdVk (View v) {

        if (new isOnline().getOnline(MyApplication.getContext()) == true) {
            enter_idValue = enter_id.getText().toString();
            if (!enter_id.getText().toString().equals("")) {

                textview_servis.setVisibility(View.GONE);
                textview_simple.setVisibility(View.GONE);
                rotateLoading = findViewById(R.id.rotateloading);
                rotateLoading.start();

                CallServer = ApiClient.getClient();
                api = CallServer.create(API.class);

                Call<List<Info>> call = api.CheckExistencePage(enter_idValue);
                call.enqueue(new Callback<List<Info>>() {
                    @Override
                    public void onResponse(Call<List<Info>> call, Response<List<Info>> response) {

                        List<Info> list = response.body();
                        check_code = list.get(1).getCodeCheck();

                        if (check_code.equals("200")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setTitle("Проверка существования страницы!")
                                    .setMessage(Html.fromHtml("<p style='color:#000;'>Страница существует! Сохранить данный ID?</p>"))
                                    .setIcon(R.drawable.vk)
                                    .setCancelable(false)
                                    .setNegativeButton("Да",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                    toast = Toast.makeText(getApplicationContext(),
                                                            "Данные сохранены!", Toast.LENGTH_SHORT);
                                                    toast.show();
                                                }
                                            });
                            AlertDialog alert = builder.create();
                            alert.show();

                            result_save = new File_RQ().File_Entry(context, file_name, enter_idValue);

                            textview_servis.setText("Данный id " + enter_idValue + " отслеживается в фонновом режими");
                            textview_servis.setVisibility(View.VISIBLE);
                            textview_simple.setVisibility(View.VISIBLE);

                            if(rotateLoading.isStart()){
                                rotateLoading.stop();
                            }

                            if (result_save == true) {
                            } else {
                                toast = Toast.makeText(getApplicationContext(),
                                        "Данные не сохранены! Ошибка!", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        } else {
                            toast = Toast.makeText(getApplicationContext(),
                                    "Данная страница не доступна!", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Info>> call, Throwable t) {
                    }
                });
            } else {
                toast = Toast.makeText(getApplicationContext(),
                        "Введите ID!", Toast.LENGTH_SHORT);
                toast.show();
            }
        } else {
            Toast.makeText(this, "Нет соединение с интернетом", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_list) {
            Intent intent = new Intent(this, ListTime.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}