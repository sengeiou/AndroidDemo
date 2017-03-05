package com.boc.lfj.httpdemo.common.log;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.boc.lfj.httpdemo.app.BaseApplication;
import com.boc.lfj.httpdemo.common.log.common.AndroidVersionCheckUtils;
import com.boc.lfj.httpdemo.common.log.util.ExternalOverFroyoUtils;
import com.boc.lfj.httpdemo.common.log.util.ExternalUnderFroyoUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 打印到sdcard上面的日志类
 * @author liuweina
 * @date 2013/11/12
 *
 */
public class PrintToFileLogger implements ILogger {

	public static final int VERBOSE = 2;

	public static final int DEBUG = 3;

	public static final int INFO = 4;

	public static final int WARN = 5;

	public static final int ERROR = 6;

	public static final int ASSERT = 7;
	/**
	 * log文件的绝对路径
	 */
	private String mPath;
	private Writer mWriter;

	private static final SimpleDateFormat TIMESTAMP_FMT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	/**
	 * log文件名称的不变部分
	 */
	private String basePath = "";
	/**
	 * log文件父文件夹的名称
	 */
	private static String LOG_DIR = "logs";
	/**
	 * log文件名称的前缀
	 */
	private static String BASE_FILENAME = "log";
	/**
	 * log文件名称的后缀
	 */
	private static String SUFFIX_FILENAME = ".txt";
	/**
	 * log文件所在的父文件夹
	 */
	private File logDir;
	/**
	 * log文件
	 */
	private File newFile;
	/**
	 * log文件第一行的内容，包括设备品牌、手机型号、Android版本、手机分辨率
	 */
	private String phoneInfo;
	/**
	 * sd卡或缓存中最多显示的log文件数
	 */
	private int fileNumbers = 5;
	/**
	 * sd卡或缓存中每个文件的容量，此处是1024 * 10
	 */
	private int fileCapacity = 10*1024;

	public PrintToFileLogger() {

	}

	public void open() {
		if (AndroidVersionCheckUtils.hasFroyo()) {
			logDir = ExternalOverFroyoUtils.getDiskSdCardDir(BaseApplication.getInstance()
					.getApplicationContext(), LOG_DIR);
		} else {
			logDir = ExternalUnderFroyoUtils.getDiskSdCardDir(BaseApplication.getInstance()
					.getApplicationContext(), LOG_DIR);
		}
		if (!logDir.exists()) {
			logDir.mkdirs();
			// do not allow media scan
			// try {
			// new File(logDir, ".nomedia").createNewFile();
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
		}
		if (ExternalOverFroyoUtils.getUsableSpace(logDir) < 10 * 1024 * 1024) {
			// 存储空间小于10Mb，存储一个文件，提示“存储空间不足”，退出
			try {
				newFile = new File(basePath + "_" + System.currentTimeMillis() + SUFFIX_FILENAME);
				mPath = newFile.getAbsolutePath();
				mWriter = new BufferedWriter(new FileWriter(mPath), 1024);
				mWriter.write("存储空间不足");
				mWriter.write('\n');
				mWriter.flush();
				mWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		basePath = logDir.getAbsolutePath() + "/" + BASE_FILENAME;
		phoneInfo = "设备品牌：" + android.os.Build.BRAND + ",手机型号：" + android.os.Build.MODEL
				+ ",Android版本：" + android.os.Build.VERSION.RELEASE + ",手机分辨率："
				+ getResolution(BaseApplication.getInstance().getApplicationContext());
		fileCreateOpe();
		fileDeleteOpe();
	}

	/**
	 * 写入文件时的操作，如果文件超过指定长度，新建一个文件；如果文件数目多于指定个数，删除最开始的文件
	 */
	public void fileOperation() {
		if (newFile.length() > fileCapacity) {
			fileCreateOpe();
			fileDeleteOpe();
		}
	}

	/**
	 * 创建文件，
	 * 
	 * @throws IOException
	 */
	public void fileCreateOpe() {
		try {
			newFile = new File(basePath + "_" + System.currentTimeMillis() + SUFFIX_FILENAME);
			mPath = newFile.getAbsolutePath();
			mWriter = new BufferedWriter(new FileWriter(mPath), 1024);
			mWriter.write(phoneInfo);
			mWriter.write('\n');
			mWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除文件，在某次首次创建文件前，和再次创建文件后判断
	 */
	private void fileDeleteOpe() {
		File[] fileLists = logDir.listFiles();
		if (fileLists.length > fileNumbers) {
			// 需删除一个文件
			String fileName0 = fileLists[0].getName();
			int j = 0;
			for (int i = 1; i < fileLists.length; i++) {
				String fileName1 = fileLists[i].getName();
				if (fileName0.compareTo(fileName1) > 0) {
					fileName0 = fileName1;
					j = i;
				}
			}
			fileLists[j].delete();
			// 已删除一个文件后，还比指定文件数目多，需要再次删除
			if (logDir.listFiles().length - fileNumbers > 1) {
				fileDeleteOpe();
			}

		}
	}

	/**
	 * 获取手机分辨率
	 */
	public static String getResolution(Context mContext) {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		WindowManager mWindowManager = (WindowManager) mContext.getSystemService("window");
		mWindowManager.getDefaultDisplay().getMetrics(displayMetrics);
		int screenWidth = displayMetrics.widthPixels;
		int screenHeight = displayMetrics.heightPixels;
		return screenWidth + "*" + screenHeight;
	}

	public String getPath() {
		return mPath;
	}

	@Override
	public void d(String tag, String message) {
		println(DEBUG, tag, message);
	}

	@Override
	public void e(String tag, String message) {
		println(ERROR, tag, message);
	}

	@Override
	public void i(String tag, String message) {
		println(INFO, tag, message);
	}

	@Override
	public void v(String tag, String message) {
		println(VERBOSE, tag, message);
	}

	@Override
	public void w(String tag, String message) {
		println(WARN, tag, message);
	}

	/**
	 * 目前只保存warn和error的Log到文件中
	 */
	@Override
	public void println(int priority, String tag, String message) {
		String printMessage = "";
		switch (priority) {
		case VERBOSE:
			printMessage = "[V]|" + tag + "|"
					+ BaseApplication.getInstance().getApplicationContext().getPackageName()
					+ "|" + message;
			break;
		case DEBUG:
			printMessage = "[D]|" + tag + "|"
					+ BaseApplication.getInstance().getApplicationContext().getPackageName()
					+ "|" + message;
			break;
		case INFO:
			printMessage = "[I]|" + tag + "|"
					+ BaseApplication.getInstance().getApplicationContext().getPackageName()
					+ "|" + message;
			break;
		case WARN:
			printMessage = "[W]|" + tag + "|"
					+ BaseApplication.getInstance().getApplicationContext().getPackageName()
					+ "|" + message;
			println(printMessage);
			break;
		case ERROR:
			printMessage = "[E]|" + tag + "|"
					+ BaseApplication.getInstance().getApplicationContext().getPackageName()
					+ "|" + message;
			println(printMessage);
			break;
		default:

			break;
		}
		// println(printMessage);

	}

	/**
	 * 只保存error和warning的信息到文件中
	 * 
	 * @param message
	 */
	public void println(String message) {
		try {
			mWriter.write(TIMESTAMP_FMT.format(new Date()));
			mWriter.write(message);
			mWriter.write('\n');
			mWriter.flush();
			fileOperation();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void close() {
		try {
			mWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得最新的file，程序崩溃时需要获取上传到服务器
	 * 
	 * @return
	 */
	public File getFreshFile() {
		File file = null;
		if(logDir.exists()) {
			File[] fileLists = logDir.listFiles();
			if (fileLists.length > 0) {
				String fileName0 = fileLists[0].getName();
				int j = 0;
				for (int i = 1; i < fileLists.length; i++) {
					String fileName1 = fileLists[i].getName();
					if (fileName0.compareTo(fileName1) < 0) {
						fileName0 = fileName1;
						j = i;
					}
				}
				file = fileLists[j];
			}
		}
		return file;
	}
}
