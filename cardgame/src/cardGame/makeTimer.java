package cardGame;

public class makeTimer {
	static String timerBuffer;

	// �ʸ� �ð����� ��������
	public String getTime(int secs) {
		int sec = secs % 60;
		int min = secs / 60;
		return timerBuffer = String.format("%02d:%02d", min, sec);
	}
}
