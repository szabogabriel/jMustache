package com.jmtemplate;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Tools {

	public static String readFile(File f) {
		StringBuilder ret = new StringBuilder();
		byte[] buffer = new byte[2048];
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

	public static File saveUrlToTempFile(final String filename, final URL urlString) {
		BufferedInputStream in = null;
		FileOutputStream fout = null;
		File ret = null;
		try {
			ret = File.createTempFile(filename, ".template");
			in = new BufferedInputStream(new URL(urlString.toString() + "/" + filename).openStream());
			fout = new FileOutputStream(ret);

			final byte data[] = new byte[1024];
			int count;
			while ((count = in.read(data, 0, 1024)) != -1) {
				fout.write(data, 0, count);
			}
		} catch (Exception e) {

		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception ex) {
				}
			}
			if (fout != null) {
				try {
					fout.close();
				} catch (Exception ex) {
				}
			}
		}
		return ret;
	}

}
