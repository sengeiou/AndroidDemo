package com.boc.lfj.httpdemo.common.log;

import java.io.File;

/**
 * 日志的接口
 * @author liuweina
 * @date 2013/11/12
 *
 */
public interface ILogger {
	void v(String tag, String message);

	void d(String tag, String message);

	void i(String tag, String message);

	void w(String tag, String message);

	void e(String tag, String message);

	void open();

	void close();

	void println(int priority, String tag, String message);

	File getFreshFile();
}
