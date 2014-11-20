package selab.dev.baduiapp.util;

public class SeqHolder {
	private static int seq;

	public static int getCurrSeq() {
        seq = SharedPrefsUtil.getSharedPreferenceInt("seq") + 1;
		return seq;
	}
	
	public static void saveCurrentSeq() {
		SharedPrefsUtil.putSharedPreference("seq", seq);
	}
}
