package corporation.darkshadow.ocr.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by Aman on 28/4/18.
 */

public class SharedPrefUtil {

    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "MyPref";

    public SharedPrefUtil(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createCard(String key, Set<String> values){
        editor.putStringSet(key,values);
        editor.commit();
    }

    public Set<String> retrieveCard(String key, Set<String> defValue){
        return sharedPreferences.getStringSet(key, defValue);
    }

    public void createSize(String key, int size){
        editor.putInt(key, size);
        editor.commit();
    }

    public int getSize(String key, int defvalue){
        return sharedPreferences.getInt(key, defvalue);
    }
}
