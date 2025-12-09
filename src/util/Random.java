package util;

public class Random {

	public static int getInclude(int min, int max) {
		return (int)(Math.random() * (max - min) + min);
	}

	public static int getExclude(int min, int max) {
		return (int)(Math.random() * ((max - 1) - (min - 1)) + (min - 1));
	}
}
