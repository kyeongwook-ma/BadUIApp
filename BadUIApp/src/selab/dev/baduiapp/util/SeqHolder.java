package selab.dev.baduiapp.util;

public class SeqHolder {
	private static int seq;

	public static int getCurrSeq() {
		return SharedPrefsUtil.getSharedPreferenceInt("seq");
	}
	
	public static void saveCurrentSeq() {
		SharedPrefsUtil.putSharedPreference("seq", seq);
	}
}
