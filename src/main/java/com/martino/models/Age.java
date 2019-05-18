package com.martino.models;

public class Age {
	public static String pipe(int a) {
		if (a < 1) {
			return a + " an";
		} else {
			return a + " ans";
		}
	}
}
