package selab.dev.baduiapp.util;

public class SeqHolder {
	private static int seq;
	
	public static int getCurrentSeq() {
		SeqHolder.seq = SharedPrefsUtil.getSharedPreferenceInt("seq");
		SeqHolder.seq += 1;
		return seq;
	}
	public static void saveCurrentSeq() {
		SharedPrefsUtil.putSharedPreference("seq", seq);
	}
}
