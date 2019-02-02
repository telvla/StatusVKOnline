package at.telvla.statusvkonline;

import android.content.Context;
import android.util.Log;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CheckServer {
    Context context;
    String file_name = "id_vk";
    String get_id_file;
    Integer flag;
    boolean ch_compare;
    String ser_online;
    Retrofit CallServer;
    API api;

    void StartPing(){
        context = MyApplication.getContext();

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                try {
                    get_id_file = new File_RQ().File_Read(context, file_name);
                    if (!get_id_file.equals("")) {
                        flag = 1;
                    } else {
                        flag = 0;
                    }
                } catch (Exception e) {
                    flag = 0;
                }

                if (flag == 1) {
                    CallServer = ApiClient.getClient();
                    api = CallServer.create(API.class);

                    Call<List<Info>> call = api.CheckExistencePage(get_id_file);
                    call.enqueue(new Callback<List<Info>>() {
                        @Override
                        public void onResponse(Call<List<Info>> call, Response<List<Info>> response) {

                            List<Info> list = response.body();
                            ser_online = list.get(1).getOnline();
                            ser_online = ser_online.substring(0, ser_online.length() - 1);
                            ch_compare = new CompareStatus().CompareStatus(ser_online);

                            Log.i("test_online", "---------" + ch_compare + "----" + ser_online + "----" + ser_online);

                            if (ch_compare == false && ser_online.equals("Online")) {
                                NotificationSend check = new NotificationSend();
                                check.Send(ser_online);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Info>> call, Throwable t) {
                        }
                    });
                }
            }
        };
        timer.schedule(timerTask, 0, 10000);
    }
}