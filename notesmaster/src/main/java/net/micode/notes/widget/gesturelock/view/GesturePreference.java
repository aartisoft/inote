package net.micode.notes.widget.gesturelock.view;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 
 * 类描述：
 * 
 * 创建时间：2019/1/12 19:05
 */
public class GesturePreference {
    private Context context;
    private final String fileName = "com.oden.gesturelock.filename";
    private String nameTable = "com.oden.gesturelock.nameTable";

    public GesturePreference(Context context, int nameTableId) {
        this.context = context;
        if (nameTableId != -1)
            this.nameTable = nameTable + nameTableId;
    }

    public void WriteStringPreference(String data) {
        SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(nameTable, data);
        editor.commit();
    }

    public String ReadStringPreference() {
        SharedPreferences preferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return preferences.getString(nameTable, "null");
    }

}
