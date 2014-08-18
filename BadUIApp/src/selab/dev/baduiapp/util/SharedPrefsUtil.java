package selab.dev.baduiapp.util;

import selab.dev.baduiapp.BadUIApp;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefsUtil {
	public static void putSharedPreference(String key, String value)  {
		SharedPreferences prefs = 
				PreferenceManager.getDefaultSharedPreferences(BadUIApp.getContext());

		SharedPreferences.Editor editor = prefs.edit();

		editor.putString(key, value);
		editor.commit();
	}
	
	public static void putSharedPreference(String key, int value)  {
		SharedPreferences prefs = 
				PreferenceManager.getDefaultSharedPreferences(BadUIApp.getContext());

		SharedPreferences.Editor editor = prefs.edit();

		editor.putInt(key, value);
		editor.commit();
	}
	
	

	public static String getSharedPreferenceStr(String key) {
		SharedPreferences prefs = 
				PreferenceManager.getDefaultSharedPreferences(BadUIApp.getContext());

		return prefs.getString(key, null);
	}
	
	public static int getSharedPreferenceInt(String key) {
		SharedPreferences prefs = 
				PreferenceManager.getDefaultSharedPreferences(BadUIApp.getContext());

		return prefs.getInt(key,0);
	}
}
