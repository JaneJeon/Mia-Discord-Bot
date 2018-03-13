package util;

import java.util.Scanner;

public class Load {
	// reads in entire file and loads it into a String
	public static String string(String filename) {
		return new Scanner(Load.class.getClassLoader().getResourceAsStream(filename)).next();
	}
}