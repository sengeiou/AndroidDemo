package com.boc.lfj.httpdemo.common;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author admin
 * 
 */
public abstract class Detect {

	public static final short INVALID_NUMBER_VALUE = 0;

	public static final String EMPTY_STRING = "";

	public static final String DELIMITER = ",";
	
	/** notEmpty */
	public static boolean notEmpty(String string) {
		return null != string && !EMPTY_STRING.equals(string);
	}

	public static boolean notEmpty(Collection<?> collection) {
		// List<Object> list = new ArrayList<Object>();
		// list.add(null);
		// System.out.println(notEmpty(list)); //true

		if (null != collection) {
			Iterator<?> iterator = collection.iterator();
			if (null != iterator) {
				while (iterator.hasNext()) {
					if (null != iterator.next()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean notEmpty(Map<?, ?> map) {
		return null != map && !map.isEmpty();
	}

	public static boolean notEmpty(byte[] array) {
		return null != array && array.length > 0;
	}

	public static boolean notEmpty(short[] array) {
		return null != array && array.length > 0;
	}

	public static boolean notEmpty(int[] array) {
		return null != array && array.length > 0;
	}

	public static boolean notEmpty(long[] array) {
		return null != array && array.length > 0;
	}

	public static <T> boolean notEmpty(T[] array) {
		return null != array && array.length > 0;
	}

	/** is */
	public static boolean isNegative(double value) {
		return value < 0;
	}

	public static boolean isPositive(double value) {
		return value > 0;
	}

	public static boolean isTrue(Boolean value) {
		return Boolean.TRUE.equals(value);
	}

	public static boolean isFalse(Boolean value) {
		return Boolean.FALSE.equals(value);
	}

	/**  */
	public static double max(double a, double b) {
		return (a > b) ? a : b;
	}

	public static double max(double... values) {
		// TBD
		return 0;
	}

	public static double min(double a, double b) {
		return (a < b) ? a : b;
	}

	public static double min(double... values) {
		// TBD
		return 0;
	}

	
	public static boolean equals(Class<?> clazz1, Class<?> clazz2) {
		return clazz1 == null ? clazz2 == null : clazz1.equals(clazz2);
	}

	public static boolean equals(Boolean boolean1, Boolean boolean2) {
		return boolean1 == null ? boolean2 == null : boolean1.booleanValue() == boolean2.booleanValue();
	}

	/** contains */
	public static boolean contains(long value, long[] values) {
		if (notEmpty(values)) {
			for (long v : values) {
				if (v == value) {
					return true;
				}
			}
		}
		return false;
	}
	public static boolean contains(short value, short[] values) {
		if (notEmpty(values)) {
			for (long v : values) {
				if (v == value) {
					return true;
				}
			}
		}
		return false;
	}
	// public static boolean contains(String value, String[] values) {
	// for (String v : values) {
	// if (null == value) {
	// if (null == v) {
	// return true;
	// }
	// } else if (value.equals(v)) {
	// return true;
	// }
	//
	// }
	// }
	// return false;
	// }

	public static <E> boolean contains(E one, List<E> list) {
		if (notEmpty(list) && null != one) {
			for (E item : list) {
				if (one.equals(item)) {
					return true;
				}
			}
		}
		return false;
	}

	public static <E> boolean contains(E one, E[] many) {
		if (notEmpty(many) && null != one) {
			return Arrays.asList(many).contains(one);
		}
		return false;
	}

	/** *array */

	public static short[] shortArray(String value) {
		return shortArray(value, DELIMITER);
	}

	public static short[] shortArray(String value, char delimiter) {
		if (!notEmpty(value)) {
			return null;
		}
		String[] values = StringUtils.split(value, String.valueOf(delimiter));

		short[] shortValues = new short[values.length];
		for (int i = 0; i < values.length; i++) {
			shortValues[i] = Short.parseShort(values[i]);
		}
		return shortValues;
	}

	public static short[] shortArray(String value, String delimiter) {
		if (!notEmpty(value)) {
			return null;
		}
		String[] values = StringUtils.split(value, delimiter);

		short[] shortValues = new short[values.length];
		for (int i = 0; i < values.length; i++) {
			shortValues[i] = Short.parseShort(values[i]);
		}
		return shortValues;
	}

	public static int[] intArray(String value) {
		return intArray(value, DELIMITER);
	}

	public static int[] intArray(String value, String delimiter) {
		if (!notEmpty(value)) {
			return null;
		}
		String[] values = StringUtils.split(value, delimiter);

		int[] intValues = new int[values.length];
		for (int i = 0; i < values.length; i++) {
			intValues[i] = Integer.parseInt(values[i]);
		}
		return intValues;
	}

	public static long[] longArray(String value) {
		return longArray(value, DELIMITER);
	}

	public static long[] longArray(String value, char delimiter) {
		if (!notEmpty(value)) {
			return null;
		}
		String[] values = StringUtils.split(value, String.valueOf(delimiter));

		long[] longValues = new long[values.length];
		for (int i = 0; i < values.length; i++) {
			longValues[i] = Long.parseLong(values[i]);
		}
		return longValues;
	}

	public static long[] longArray(String value, String delimiter) {
		if (!notEmpty(value)) {
			return null;
		}
		String[] values = StringUtils.split(value, delimiter);

		long[] longValues = new long[values.length];
		for (int i = 0; i < values.length; i++) {
			longValues[i] = Long.parseLong(values[i]);
		}
		return longValues;
	}

	public static double[] doubleArray(String value) {
		return doubleArray(value, DELIMITER);
	}

	public static double[] doubleArray(String value, String delimiter) {
		String[] values = StringUtils.split(value, delimiter);

		double[] doubleValues = new double[values.length];
		for (int i = 0; i < values.length; i++) {
			doubleValues[i] = Double.parseDouble(values[i]);
		}
		return doubleValues;
	}

	/** grouping */
	public static List<long[]> grouping(long[] values, int groupSize) {
		if (notEmpty(values)) {

			Assertion.isPositive(groupSize, "divideSize must be bigger than 0");

			int groupLength = values.length / groupSize + ((values.length % groupSize) > 0 ? 1 : 0);

			List<long[]> longArryGroup = new LinkedList<long[]>();
			long[] valueArray = null;
			for (int i = 0; i < groupLength; i++) {
				int arrayLength = (i < groupLength - 1 || values.length % groupSize == 0) ? groupSize : (values.length % groupSize);

				valueArray = new long[arrayLength];
				for (int j = 0; j < arrayLength; j++) {
					valueArray[j] = values[i * groupSize + j];
				}
				longArryGroup.add(valueArray);
			}

			return longArryGroup;
		}
		return null;
	}

	/** join */
	public static <K, V> String join(Map<K, V> values) {// toQueryString
		return "TBD";
	}

	/** as */
	public static boolean asBoolean(Object value) {
		return (value instanceof Boolean) ? ((Boolean) value).booleanValue() : Boolean.parseBoolean(asString(value));
	}

	public static Boolean asWrapperBoolean(Object value) {
		return null == value ? null : asBoolean(value);
	}

	public static short asShort(Object value) {
		return (value instanceof Short) ? ((Short) value).shortValue() : Short.parseShort(asString(value));
	}

	public static Short asWrapperShort(Object value) {
		return null == value ? null : asShort(value);
	}

	public static int asInt(Object value) {
		return (value instanceof Number) ? ((Number) value).intValue() : Integer.parseInt(asString(value));
	}

	public static Integer asWrapperInteger(Object value) {
		return null == value ? null : asInt(value);
	}

	public static long asLong(Object value) {
		/** tq:如果不抛异常，会有隐患 */
		// (org.apache.commons.lang.math.NumberUtils.isNumber(stringValue))
		return (value instanceof Number) ? ((Number) value).longValue() : Long.parseLong(asString(value));
	}

	@Deprecated
	public static long asLong(Object value, long nullValue) {
		return null == value ? nullValue : asLong(value);
	}

	public static Long asWrapperLong(Object value) {
		return null == value ? null : asLong(value);
	}

	public static double asDouble(Object value) {
		return (value instanceof Double) ? ((Double) value).doubleValue() : Double.parseDouble(asString(value));
	}

	public static Double asWrapperDouble(Object value) {
		return null == value ? null : asDouble(value);
	}

	public static String asString(Object object) {
		return (null == object) ? null : StringUtils.trimWhitespace(String.valueOf(object));
	}

	public static String asString(Object value, String nullValue) {
		return null == value ? nullValue : asString(value);
	}

	@SuppressWarnings("unchecked")
	public static <E> E[] asArray(List<E> list) {
		return notEmpty(list) ? (E[]) list.toArray() : null;
	}

	/** union */
	public static <E> List<E> union(List<E>... lists) {
		List<E> result = new ArrayList<E>();
		if (null != lists) {
			for (List<E> list : lists) {
				if (notEmpty(list)) {
					result.addAll(list);
				}
			}
		}
		return (List<E>) escapeEmpty(result);
	}

	public static <E> Set<E> union(Set<E>... sets) {
		Set<E> result = new HashSet<E>();
		if (null != sets) {
			for (Set<E> set : sets) {
				if (notEmpty(set)) {
					result.addAll(set);
				}
			}
		}
		return escapeEmpty(result);
	}

	public static <E> Collection<E> union(Collection<E>... collections) {
		Collection<E> result = new ArrayList<E>();
		if (null != collections) {
			for (Collection<E> collection : collections) {
				if (notEmpty(collection)) {
					result.addAll(collection);
				}
			}
		}
		return escapeEmpty(result);
	}

	/** escapeEmpty */
	public static <E> List<E> escapeEmpty(List<E> list) {
		return notEmpty(list) ? list : null;
	}

	public static <E> Set<E> escapeEmpty(Set<E> set) {
		return notEmpty(set) ? set : null;
	}

	// TODO: remove null element in collection
	public static <E> Collection<E> escapeEmpty(Collection<E> collection) {
		return notEmpty(collection) ? collection : null;
	}

	public static String escapeVarchar(String value) {
		return (notEmpty(value) && value.length() > 4000) ? value.substring(0, 3990) : value;
	}

	public static <E> E firstOne(List<E> list) {
		return notEmpty(list) ? list.iterator().next() : null;
	}

	public static boolean onlyOne(List<?> list) {
		return notEmpty(list) && list.size() == 1;
	}

	/** unmodifiable */
	public static <E> List<E> unmodifiable(List<E> list) {
		return notEmpty(list) ? Collections.unmodifiableList(list) : null;
	}

	public static <E> Set<E> unmodifiable(Set<E> set) {
		return notEmpty(set) ? Collections.unmodifiableSet(set) : null;
	}

	public static boolean between(long value, long floor, long ceil) {
		return value >= floor && value <= ceil;
	}

	public static String trim(String string) {
		return StringUtils.trimWhitespace(string);
	}

	/*
	 * 0 指前面补充零 formatLength 字符总长度为 formatLength d 代表为正数。
	 */
	@Deprecated
	/* use apache StringUtls.leftPad */
	public static String frontCompWithZore(int source, int formatLength) {

		String newString = String.format("%0" + formatLength + "d", source);
		return newString;
	}

	/** endsWith */
	public static boolean endsWith(String target, String[] suffixs) {
		if (Detect.notEmpty(suffixs)) {
			for (String suffix : suffixs) {
				boolean result = target.endsWith(suffix);
				if (true == result) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) throws Exception {

		List<Object> list = new ArrayList<Object>();
		list.add(null);
		list.add(null);
		System.out.println("size=" + list.size() + ", " + notEmpty(list));

		// long[] a = null;
		// System.out.println(notEmpty( a));
		// Short value = null;
		// System.out.println(asShort((Short)value));

		// List<long[]> longValueGroup = grouping(new long[] { 1, 2, 3, 4, 5, 6,
		// 7, 8, 9, 10 }, 5);
		// if (null != longValueGroup) {
		// for (int i = 0; i < longValueGroup.size(); i++) {
		// long[] longValue = longValueGroup.get(i);
		//
		// System.out.println(longValue.length + ": " + join(longValue));
		// }
		// }
		//
		// System.out.println(JsonUtil.marshal(escapeDuplication(new long[] { 1,
		// 2, 2, 5, 2, 2, 1, 2, 3, 4, 1, 2, 3 })));

	}
}
