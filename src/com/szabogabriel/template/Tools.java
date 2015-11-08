package com.szabogabriel.template;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Tools {

	public static String readFile(File f) {
		StringBuilder ret = new StringBuilder();
		byte [] buffer = new byte [2048];
		int read;
		try (FileInputStream in = new FileInputStream(f)) {
			while ((read = in.read(buffer)) >= 0) {
				ret.append(new String(buffer, 0, read));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret.toString();
	}
	
}
