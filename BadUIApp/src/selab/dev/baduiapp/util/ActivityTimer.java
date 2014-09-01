package selab.dev.baduiapp.util;

import java.util.TimerTask;

public class ActivityTimer extends TimerTask {

	private int times = 0;
	private boolean isRunning = false;
	
	public ActivityTimer() {
		isRunning = true;
	}
	
	@Override
	public void run() {
		while(isRunning) {
			++times;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stopTimer() {
		isRunning = false;
	}
	
	public int getElapsedTime() {
		return times;
	}
	
	
}
