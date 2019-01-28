package at.telvla.statusvkonline;

import android.content.Context;

public class CompareStatus {
    boolean rezult;
    Context context;
    String file_online = "file_online";
    String get_status_file;

    boolean CompareStatus (String ser_online) {
        context = MyApplication.getContext();
        try {
            get_status_file = new File_RQ().File_Read(context, file_online);
            if (get_status_file.equals("")) {
                get_status_file = "";
            }
        } catch (Exception e) {
            get_status_file = "";
        }

        if (get_status_file.equals(ser_online)) {
            rezult = true;
        } else {
            new File_RQ().File_Entry(context, file_online, ser_online);
            rezult = false;
        }
        return rezult;
    }
}