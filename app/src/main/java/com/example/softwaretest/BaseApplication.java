package com.example.softwaretest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {

	public static Context context;

	@Override
	public void onCreate() {
		super.onCreate();
		context = this;

		try {
			File file1 = new File(composeLocation("writableZHCN.dic"));
			File file2 = new File(composeLocation("writableZHCN.dic-journal"));
			if (!file1.exists()) {
				copyBigDataToSD("writableZHCN.dic", file1.getName());
			}
			if (!file2.exists()) {
				copyBigDataToSD("writableZHCN.dic-journal", file2.getName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void copyBigDataToSD(String fileName, String strOutFileName) throws IOException {
		InputStream myInput;
		OutputStream myOutput = new FileOutputStream(strOutFileName);
		myInput = this.getAssets().open(fileName);
		byte[] buffer = new byte[1024];
		int length = myInput.read(buffer);
		while (length > 0) {
			myOutput.write(buffer, 0, length);
			length = myInput.read(buffer);
		}

		myOutput.flush();
		myInput.close();
		myOutput.close();
	}

	public static Context getContext() {
		return context;
	}

	public static String composeLocation(String fileName) {
		String dataLocation = "/data/data/" + context.getPackageName() + "/";
		return new StringBuilder().append(dataLocation).append(fileName).toString();
	}

}
