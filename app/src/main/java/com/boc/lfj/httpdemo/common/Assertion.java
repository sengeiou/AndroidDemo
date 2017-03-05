package com.boc.lfj.httpdemo.common;

import junit.framework.Assert;

import java.util.List;

/**
 * 
 * @author admin
 * 
 */
public abstract class Assertion extends Assert {

	public static void isNegative(double value, String message) {
		if (!Detect.isNegative(value)) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void isPositive(double value, String message) {
		if (!Detect.isPositive(value)) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void isPositive(long value, String message) {
		if (!Detect.isPositive(value)) {
			throw new IllegalArgumentException(message);
		}
	}

	/* notEmpty */
	public static void notEmpty(String[] string, String message) {
		if (!Detect.notEmpty(string)) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void notEmpty(long[] values, String message) {
		if (!Detect.notEmpty(values)) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void notEmpty(int[] values, String message) {
		if (!Detect.notEmpty(values)) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void notEmpty(List<?> list, String message) {
		if (!Detect.notEmpty(list)) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void notEmpty(String string, String message) {
		if (!Detect.notEmpty(string)) {
			throw new IllegalArgumentException(message);
		}
	}

	/* isEmpty */
	public static void isEmpty(String[] string, String message) {
		if (Detect.notEmpty(string)) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void isEmpty(long[] values, String message) {
		if (Detect.notEmpty(values)) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void isEmpty(List<?> list, String message) {
		if (Detect.notEmpty(list)) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void isEmpty(String string, String message) {
		if (Detect.notEmpty(string)) {
			throw new IllegalArgumentException(message);
		}
	}

	/* onlyOne */
	public static void onlyOne(List<?> list, String message) {
		if (!Detect.onlyOne(list)) {
			throw new IllegalArgumentException(message);
		}
	}

	// use super.isTrue(boolean expression, String message) instead
	// public static void isTrue(boolean expression, RuntimeException re) {
	// if (!expression) {
	// throw re;
	// }
	// }

	public static void notNull(Object obj, String message) {
		if (null == obj) {
			throw new RuntimeException(message);
		}
	}

}
