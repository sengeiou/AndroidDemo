package com.boc.lfj.httpdemo.common.log;

import android.util.Log;

import java.io.File;

/**
 * 打印到LogCat上面的日志类
 * @author liuweina
 * @date 2013/11/12
 *
 */
public class PrintToLogCatLogger implements ILogger {
	@Override
	public void d(String tag, String message) {
		Log.d(tag, message);
	}

	@Override
	public void e(String tag, String message) {
		Log.e(tag, message);
	}

	@Override
	public void i(String tag, String message) {
		Log.i(tag, message);
	}

	@Override
	public void v(String tag, String message) {
		Log.v(tag, message);
	}

	@Override
	public void w(String tag, String message) {
		Log.w(tag, message);
	}

	@Override
	public void println(int priority, String tag, String message) {
		Log.println(priority, tag, message);
	}

	@Override
	public void open() {

	}

	@Override
	public void close() {

	}

	@Override
	public File getFreshFile() {
		return null;
	}
}
