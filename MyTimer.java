import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
	static int interval;

	static Timer timer;
	Cryptogram currentCryptogram;

	public MyTimer() {
		int delay = 1000;
		int period = 1000;
		timer = new Timer();
		interval = 0;
		// System.out.println(secs);
		timer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				changeInterval();

			}
		}, delay, period);
	}

	private static final int changeInterval() {
		return ++interval;
	}

	public void stopTimer() {
		timer.cancel();
		System.out.println("You have completed the cryptogram in " + interval + "s");
		System.out.println();
	}

	public void penaltyTime() {
		interval = interval + 60;
	}

	public static int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		MyTimer.interval = interval;
	}
}