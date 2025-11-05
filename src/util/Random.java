package util;

public class Random {
	public static int getInclude(int min, int max) {
		return (int)(Math.random() * (max - min) + min);
	}
}
