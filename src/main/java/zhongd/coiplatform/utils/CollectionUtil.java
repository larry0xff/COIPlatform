package zhongd.coiplatform.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CollectionUtil {
	public static final String COMMA = ",";

	public static boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}

	public static boolean isNotEmpty(Map<?, ?> map) {
		return map != null && !map.isEmpty() && map.size() > 0;
	}

	public static boolean isEmpty(Map<?, ?> map) {
		return !isNotEmpty(map);
	}

	public static String convertToStringWithComma(List<String> list) {
		return convertToStringWithSplitor(list, ",");
	}

	public static String convertToStringWithComma(Map<String, ?> map) {
		return convertToStringWithSplitor(map, ",");
	}

	public static String convertToStringWithSplitor(Map<String, ?> map, String splitor) {
		if (isEmpty(map)) {
			return "";
		} else if (StringUtil.isEmpty(splitor)) {
			return "";
		} else {
			StringBuilder builder = new StringBuilder();
			Iterator result = map.keySet().iterator();

			while (result.hasNext()) {
				String key = (String) result.next();
				builder.append(key).append(splitor);
			}

			String result1 = builder.toString();
			return StringUtil.isEmpty(result1) ? "" : result1.substring(0, result1.length() - 1);
		}
	}

	public static String convertToStringWithSplitor(List<String> list, String splitor) {
		if (isEmpty((Collection) list)) {
			return "";
		} else if (StringUtil.isEmpty(splitor)) {
			return "";
		} else {
			StringBuilder builder = new StringBuilder();
			HashMap map = new HashMap();
			Iterator result = list.iterator();

			String key;
			while (result.hasNext()) {
				key = (String) result.next();
				if (!map.containsKey(key)) {
					map.put(key, key);
				}
			}

			result = map.keySet().iterator();

			while (result.hasNext()) {
				key = (String) result.next();
				builder.append(key).append(splitor);
			}

			String result1 = builder.toString();
			return StringUtil.isEmpty(result1) ? "" : result1.substring(0, result1.length() - 1);
		}
	}
}