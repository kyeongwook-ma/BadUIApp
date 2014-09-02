package selab.dev.baduiapp.util;


public class ActivityTimer extends Thread {

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
	
	public void startTimer() {
		isRunning = true;
	}
	
	public void stopTimer() {
		isRunning = false;
	}
	
	public int getElapsedTime() {
		return times;
	}
	
	
}
