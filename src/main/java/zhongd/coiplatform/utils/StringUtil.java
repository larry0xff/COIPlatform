package zhongd.coiplatform.utils;

import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.util.StringUtils;

public class StringUtil extends StringUtils {
	public static final String ISO_8859_1 = "iso-8859-1";
	public static final String UTF_8 = "utf-8";

	public static boolean isMobile(String mobile) {
		if (isEmpty(mobile)) {
			return false;
		} else {
			Pattern p = Pattern.compile("^((14[0-9])|(17[0-9])|(13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
			Matcher m = p.matcher(mobile);
			return m.matches();
		}
	}

	public static boolean isEmpty(String str) {
		return str == null ? true : str.equals("");
	}

	public static boolean isNotEmpty(String str) {
		return null != str && !"".equals(str);
	}

	public static boolean isNotEmptyORNull(String str) {
		return null != str && !"".equals(str) && !"null".equalsIgnoreCase(str);
	}

	public static String numberFormat(Double number, String format) {
		if (number != null && format != null && !"".equals(format)) {
			try {
				DecimalFormat e = new DecimalFormat(format);
				return e.format(number);
			} catch (Exception arg2) {
				return "";
			}
		} else {
			return "";
		}
	}

	public static String hideString(String input, int startPos, int endPos) {
		if (isEmpty(input)) {
			return input;
		} else {
			int len = input.length();
			if (startPos >= 0 && endPos <= len - 1) {
				char[] chars = input.toCharArray();
				StringBuffer sb = new StringBuffer();

				for (int i = 0; i < chars.length; ++i) {
					if (i >= startPos && i <= endPos) {
						sb.append('*');
					} else {
						sb.append(chars[i]);
					}
				}

				return sb.toString();
			} else {
				return input;
			}
		}
	}

	public static String keepSecretString(String firstName, String lastName) {
		String keepSecretString = firstName + " " + lastName;
		StringBuffer sb = new StringBuffer();
		char[] chars;
		int i;
		if (keepSecretString.length() < keepSecretString.getBytes().length) {
			keepSecretString = keepSecretString.replace(" ", "");
			chars = keepSecretString.toCharArray();
			System.out.println(chars.length);

			for (i = 0; i < chars.length; ++i) {
				if (i < 1) {
					sb.append(chars[i]);
				} else {
					sb.append('*');
				}
			}
		} else if (isNotEmpty(firstName) && isNotEmpty(lastName)) {
			chars = firstName.toCharArray();
			char[] arg6 = lastName.toCharArray();

			int i1;
			for (i1 = 0; i1 < chars.length; ++i1) {
				if (i1 >= 1 && i1 <= chars.length) {
					sb.append('*');
				} else {
					sb.append(chars[i1]);
				}
			}

			sb.append(" ");

			for (i1 = 0; i1 < arg6.length; ++i1) {
				if (i1 >= 0 && i1 <= arg6.length - 2) {
					sb.append('*');
				} else {
					sb.append(arg6[i1]);
				}
			}
		} else if (isEmpty(firstName) || isEmpty(lastName)) {
			keepSecretString = keepSecretString.replace(" ", "");
			chars = keepSecretString.toCharArray();

			for (i = 0; i < chars.length; ++i) {
				if (i >= 1 && i <= chars.length - 2) {
					sb.append('*');
				} else {
					sb.append(chars[i]);
				}
			}
		}

		return sb.toString();
	}

	public static String getFriendlyPhoneNumber(String str) {
		if (isEmpty(str)) {
			return "";
		} else {
			String countryCode = "";
			String areaCode = "";
			String number = "";
			Pattern pattern = Pattern.compile("^\\([+]*([0-9]*)\\)([0-9]+)[-]+([0-9]+)$");
			Matcher matcher = pattern.matcher(str);
			if (matcher.find()) {
				countryCode = matcher.group(1);
				areaCode = matcher.group(2);
				number = matcher.group(3);
				return "(+" + countryCode + ")" + areaCode + "-" + number;
			} else {
				matcher.reset();
				pattern = Pattern.compile("^([0-9]+)[-]+([0-9]+)$");
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					areaCode = matcher.group(1);
					number = matcher.group(2);
					return areaCode + "-" + number;
				} else {
					matcher.reset();
					pattern = Pattern.compile("^([0-9]+)-([0-9]+)-([0-9]+)$");
					matcher = pattern.matcher(str);
					if (matcher.find()) {
						countryCode = matcher.group(1);
						areaCode = matcher.group(2);
						number = matcher.group(3);
						return "(+" + countryCode + ")" + areaCode + "-" + number;
					} else {
						matcher.reset();
						pattern = Pattern.compile("^([0-9]+)-([0-9]+)$");
						matcher = pattern.matcher(str);
						if (matcher.find()) {
							areaCode = matcher.group(1);
							number = matcher.group(2);
							return areaCode + "-" + number;
						} else {
							return str;
						}
					}
				}
			}
		}
	}

	public static String getFriendlyMobileNumber(String str) {
		if (isEmpty(str)) {
			return "";
		} else {
			String countryCode = "";
			String number = "";
			Pattern pattern = Pattern.compile("^\\([+]*([0-9]*)\\)([0-9]+)$");
			Matcher matcher = pattern.matcher(str);
			if (matcher.find()) {
				countryCode = matcher.group(1);
				number = matcher.group(2);
				return "(+" + countryCode + ")" + number;
			} else {
				matcher.reset();
				pattern = Pattern.compile("^([0-9]+)-([0-9]+)$");
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					countryCode = matcher.group(1);
					number = matcher.group(2);
					return "(+" + countryCode + ")" + number;
				} else {
					return str;
				}
			}
		}
	}

	public static String getImageAbslouteUrl(String url, String mediaPath) {
		return isNotEmpty(url) && isNotEmpty(mediaPath)
				? (url.startsWith("/") ? mediaPath + url : mediaPath + "/" + url)
				: "";
	}

	public static String replaceSign(String str) {
		if (isNotEmpty(str) && str.indexOf(",") != -1) {
			str = str.replaceAll(",", "\\|");
		}

		return str;
	}

	public static String replaceSign(String str, String from, String to) {
		if (isNotEmpty(str) && str.indexOf(from) != -1) {
			str = str.replaceAll(from, to);
		}

		return str;
	}

	public static String toString(Object obj) {
		return obj != null ? obj.toString() : "";
	}

	public static String toEncoding(String str, String currentEncoding, String targetEncoding) {
		try {
			if (str != null && !str.equals("")) {
				return new String(str.getBytes(currentEncoding), targetEncoding);
			}
		} catch (Exception arg3) {
			arg3.printStackTrace();
		}

		return str;
	}

	public static String arrayToString(Object[] array) {
		StringBuffer sb = new StringBuffer();
		String result = "";
		if (array != null && array.length > 0) {
			Object[] arg2 = array;
			int arg3 = array.length;

			for (int arg4 = 0; arg4 < arg3; ++arg4) {
				Object temp = arg2[arg4];
				if (temp != null) {
					sb.append(String.valueOf(temp));
					sb.append(",");
				}
			}

			result = sb.substring(0, sb.toString().length() - 1);
		}

		return result;
	}

	public static String listToString(List<String> list) {
		StringBuffer sb = new StringBuffer();
		String result = "";
		if (list != null && list.size() > 0) {
			Iterator arg2 = list.iterator();

			while (arg2.hasNext()) {
				Object temp = arg2.next();
				sb.append(String.valueOf(temp).trim());
				sb.append(",");
			}

			result = sb.length() > 0 ? sb.substring(0, sb.toString().length() - 1) : "";
		}

		return result;
	}

	public static List<String> StringToList(String arrayString) {
		ArrayList resultList = new ArrayList();
		if (null != arrayString) {
			String[] stringArray = arrayString.split(",");
			if (stringArray != null && stringArray.length > 0) {
				String[] arg2 = stringArray;
				int arg3 = stringArray.length;

				for (int arg4 = 0; arg4 < arg3; ++arg4) {
					String temp = arg2[arg4];
					resultList.add(temp);
				}
			}
		}

		return resultList;
	}

	public static String getValueFromMap(Map<String, String> paramMap, String key) {
		if (isEmpty(key)) {
			return "";
		} else {
			String result = "";
			if (paramMap.containsKey(key)) {
				result = (String) paramMap.get(key);
				result = result != null ? result : "";
			}

			return result;
		}
	}

	public static String arrayToStringSQL(Object[] array) {
		StringBuffer sb = new StringBuffer();
		String result = "";
		if (array != null && array.length > 0) {
			Object[] arg2 = array;
			int arg3 = array.length;

			for (int arg4 = 0; arg4 < arg3; ++arg4) {
				Object temp = arg2[arg4];
				sb.append("\'" + String.valueOf(temp) + "\'");
				sb.append(",");
			}

			result = sb.substring(0, sb.toString().length() - 1);
		}

		return result;
	}

	public static boolean checkObj(Object obj) {
		boolean bool = true;
		if (obj == null || "".equals(obj.toString().trim())) {
			bool = false;
		}

		return bool;
	}

	public static boolean isNum(String str) {
		boolean flg;
		try {
			Double.parseDouble(str);
			flg = true;
		} catch (Exception arg2) {
			flg = false;
		}

		return flg;
	}

	public static String adjustUrl(String url, int size) {
		String returnResult = "#";
		if (isNotEmptyORNull(url)) {
			StringBuffer imageUrl = new StringBuffer(url);
			returnResult = imageUrl.insert(imageUrl.lastIndexOf("."), "-" + String.valueOf(size) + "-" + size)
					.toString();
		}

		return returnResult;
	}

	public static String removeLeftAndRightTheFirstStr(String value, String replaceStr) {
		if (value != null && value.indexOf(replaceStr) == 0 && value.lastIndexOf(replaceStr) == value.length() - 1) {
			value = value.substring(1, value.length() - 1);
		}

		return value;
	}

	public static String formatMoney(String value) {
		try {
			if (org.apache.commons.lang.StringUtils.isNumeric(value)) {
				DecimalFormat ex = new DecimalFormat();
				ex.applyPattern("##,##0.00");
				value = ex.format(Double.parseDouble(value) / 100.0D);
			}
		} catch (Exception arg1) {
			arg1.printStackTrace();
		}

		return value;
	}

	public static String getStringNoBlank(String str) {
		if (str != null && !"".equals(str)) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			String strNoBlank = m.replaceAll("");
			return strNoBlank;
		} else {
			return str;
		}
	}

	public static String carriageReturnConvertToHtml(String str) {
		if (str != null && !"".equals(str)) {
			Pattern p = Pattern.compile("\r\n");
			Matcher m = p.matcher(str);
			String result = m.replaceAll("</br>");
			return result;
		} else {
			return str;
		}
	}

	public static String convertBlankToHtml(String str) {
		if (str != null && !"".equals(str)) {
			Pattern p = Pattern.compile("\\s");
			Matcher m = p.matcher(str);
			String result = m.replaceAll("&nbsp;");
			return result;
		} else {
			return str;
		}
	}

	public static boolean validateIdsIsLegal(String ids) {
		if (ids != null && !"".equals(ids)) {
			String[] idArray = ids.split(",");
			String[] arg1 = idArray;
			int arg2 = idArray.length;

			for (int arg3 = 0; arg3 < arg2; ++arg3) {
				String id = arg1[arg3];

				try {
					Integer.parseInt(id);
				} catch (NumberFormatException arg6) {
					arg6.printStackTrace();
					return false;
				}
			}
		}

		return true;
	}
	public static String getUUIDString(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	public static void main(String[] args) {
		String mobile = "17060145780";
		System.out.println(mobile + " is mobile :" + isMobile(mobile));
	}
}