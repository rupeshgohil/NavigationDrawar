package aru.navigationdrawar;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SecureSharedPreference {

    public static String PREFERENCE_NAME="Androidtest";
    public static SharedPreferences pref;

    public static boolean setPreference(String key, String defaoult, Context context){
        if(key != null){
            if(getPreference(key,"",context).equals(key)){
                return false;
            }else{
                pref = context.getSharedPreferences(PREFERENCE_NAME,context.MODE_PRIVATE);
                Editor edit = pref.edit();
                edit.putString(key,defaoult);
                edit.commit();
                return true;
            }
        }
        return false;
    }
    public  static String getPreference(String key, String defaoult,Context context){
        pref = context.getSharedPreferences(PREFERENCE_NAME,context.MODE_PRIVATE);
        return pref.getString(key,defaoult);
    }

    public static void setPreferenceboolean(String key, boolean defaoult, Context context){

                pref = context.getSharedPreferences(PREFERENCE_NAME,context.MODE_PRIVATE);
                Editor edit = pref.edit();
                edit.putBoolean(key,defaoult);
                edit.commit();

    }
    public  static boolean getPrefereceboolean(String key, boolean defaoult,Context context){
        pref = context.getSharedPreferences(PREFERENCE_NAME,context.MODE_PRIVATE);
        return pref.getBoolean(key,defaoult);
    }
    public static void deletePref(Context activity) {
        pref = activity.getSharedPreferences(PREFERENCE_NAME,
                 Context.MODE_PRIVATE);
         pref.edit().clear().commit();
        }

}
