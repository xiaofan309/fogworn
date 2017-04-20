/*   1:    */package com.base.utils.json;

/*   2:    */
/*   3:    */import com.base.utils.DateUtil;
/*   4:    */
import java.lang.reflect.Array;
/*   5:    */
import java.lang.reflect.Method;
/*   6:    */
import java.sql.Timestamp;
/*   7:    */
import java.util.Collection;
/*   8:    */
import java.util.Date;
/*   9:    */
import java.util.Iterator;
/*  10:    */
import java.util.Map;
/*  11:    */
import java.util.Set;
/*  12:    */
import org.json.JSONArray;
/*  13:    */
import org.json.JSONException;
/*  14:    */
import org.json.JSONObject;
/*  15:    */
import org.json.JSONString;

/*  16:    */
/*  17:    */public class JSONUtil
/* 18: */{
	/* 19: 26 */private static JSONUtil instance = null;

	/* 20: */
	/* 21: */protected Object proxyCheck(Object bean)
	/* 22: */{
		/* 23: 38 */return bean;
		/* 24: */}

	/* 25: */
	/* 26: */public static String toJSONString(Object obj)
	/* 27: */throws JSONException
	/* 28: */{
		/* 29: 42 */return toJSONString(obj, false);
		/* 30: */}

	/* 31: */
	/* 32: */public static String toJSONString(Object obj,
			boolean useClassConvert)
	/* 33: */throws JSONException
	/* 34: */{
		/* 35: 46 */if (instance == null) {
			/* 36: 47 */instance = new JSONUtil();
			/* 37: */}
		/* 38: 48 */return instance.getJSONObject(obj, useClassConvert)
				.toString();
		/* 39: */}

	/* 40: */
	/* 41: */private String getJSONArray(Object arrayObj,
			boolean useClassConvert)
	/* 42: */throws JSONException
	/* 43: */{
		/* 44: 54 */if (arrayObj == null) {
			/* 45: 55 */return "null";
			/* 46: */}
		/* 47: 57 */arrayObj = proxyCheck(arrayObj);
		/* 48: */
		/* 49: 59 */JSONArray jSONArray = new JSONArray();
		/* 50: 60 */if ((arrayObj instanceof Collection))
		/* 51: */{
			/* 52: 61 */Iterator iterator = ((Collection) arrayObj).iterator();
			/* 53: 62 */while (iterator.hasNext())
			/* 54: */{
				/* 55: 63 */Object rowObj = iterator.next();
				/* 56: 64 */if (rowObj == null) {
					/* 57: 65 */jSONArray.put(new JSONStringObject(null));
					/* 58: 66 */} else if ((rowObj.getClass().isArray())
						|| ((rowObj instanceof Collection))) {
					/* 59: 67 */jSONArray.put(getJSONArray(rowObj,
							useClassConvert));
					/* 60: */} else {
					/* 61: 69 */jSONArray.put(getJSONObject(rowObj,
							useClassConvert));
					/* 62: */}
				/* 63: */}
			/* 64: */}
		/* 65: 72 */if (arrayObj.getClass().isArray())
		/* 66: */{
			/* 67: 73 */int arrayLength = Array.getLength(arrayObj);
			/* 68: 74 */for (int i = 0; i < arrayLength; i++)
			/* 69: */{
				/* 70: 75 */Object rowObj = Array.get(arrayObj, i);
				/* 71: 76 */if (rowObj == null) {
					/* 72: 77 */jSONArray.put(new JSONStringObject(null));
					/* 73: 78 */} else if ((rowObj.getClass().isArray())
						|| ((rowObj instanceof Collection))) {
					/* 74: 79 */jSONArray.put(getJSONArray(rowObj,
							useClassConvert));
					/* 75: */} else {
					/* 76: 81 */jSONArray.put(getJSONObject(rowObj,
							useClassConvert));
					/* 77: */}
				/* 78: */}
			/* 79: */}
		/* 80: 84 */return jSONArray.toString();
		/* 81: */}

	/* 82: */
	/* 83: */JSONStringObject getJSONObject(Object value,
			boolean useClassConvert)
	/* 84: */throws JSONException
	/* 85: */{
		/* 86: 92 */if (value == null) {
			/* 87: 93 */return new JSONStringObject("null");
			/* 88: */}
		/* 89: 95 */value = proxyCheck(value);
		/* 90: 96 */if ((value instanceof JSONString))
		/* 91: */{
			Object o;
			/* 92: */try
			/* 93: */{
				/* 94: 99 */o = ((JSONString) value).toJSONString();
				/* 95: */}
			/* 96: */catch (Exception e)
			/* 97: */{
				/* 98: */
				/* 99:101 */throw new JSONException(e);
				/* 100: */}
			/* 102:103 */throw new JSONException(
					"Bad value from toJSONString: " + o);
			/* 103: */}
		/* 104:105 */if ((value instanceof Number)) {
			/* 105:106 */return new JSONStringObject(
					JSONObject.numberToString((Number) value));
			/* 106: */}
		/* 107:108 */if (((value instanceof Boolean))
				|| ((value instanceof JSONObject)) ||
				/* 108:109 */((value instanceof JSONArray))) {
			/* 109:110 */return new JSONStringObject(value.toString());
			/* 110: */}
		/* 111:112 */if ((value instanceof Timestamp))
		/* 112: */{
			/* 113:113 */String str = DateUtil.getFormattedDateUtil(
					(Timestamp) value, "yyyy-MM-dd HH:mm:ss");
			/* 114:114 */return new JSONStringObject(JSONObject.quote(str));
			/* 115: */}
		/* 116:116 */if ((value instanceof Date))
		/* 117: */{
			/* 118:117 */String str = DateUtil.getFormattedDateUtil(
					(Date) value, "yyyy-MM-dd HH:mm:ss");
			/* 119:118 */return new JSONStringObject(JSONObject.quote(str));
			/* 120: */}
		/* 121:120 */if ((value instanceof String)) {
			/* 122:121 */return new JSONStringObject(JSONObject.quote(value
					.toString()));
			/* 123: */}
		/* 124:122 */if ((value instanceof Map))
		/* 125: */{
			/* 126:124 */JSONObject jSONObject = new JSONObject();
			/* 127: */
			/* 128:126 */Iterator iterator = ((Map) value).keySet().iterator();
			/* 129:127 */while (iterator.hasNext())
			/* 130: */{
				/* 131:128 */String key = iterator.next().toString();
				/* 132:129 */Object valueObj = ((Map) value).get(key);
				/* 133:130 */jSONObject.put(key,
						getJSONObject(valueObj, useClassConvert));
				/* 134: */}
			/* 135:132 */return new JSONStringObject(jSONObject.toString());
			/* 136: */}
		/* 137:137 */if ((value instanceof Class)) {
			/* 138:138 */return new JSONStringObject(
					JSONObject.quote(((Class) value).getSimpleName()));
			/* 139: */}
		/* 140:142 */if (((value instanceof Collection))
				|| (value.getClass().isArray())) {
			/* 141:143 */return new JSONStringObject(getJSONArray(
					proxyCheck(value), useClassConvert));
			/* 142: */}
		/* 143:146 */return reflectObject(value, useClassConvert);
		/* 144: */}

	/* 145: */
	/* 146: */private JSONStringObject reflectObject(Object bean,
			boolean useClassConvert)
	/* 147: */{
		/* 148:152 */JSONObject jSONObject = new JSONObject();
		/* 149: */
		/* 150:154 */Class klass = bean.getClass();
		/* 151:155 */Method[] methods = klass.getMethods();
		/* 152:156 */for (int i = 0; i < methods.length; i++) {
			/* 153: */try
			/* 154: */{
				/* 155:158 */Method method = methods[i];
				/* 156:159 */String name = method.getName();
				/* 157:160 */String key = "";
				/* 158:161 */if (name.startsWith("get")) {
					/* 159:162 */key = name.substring(3);
					/* 160:163 */} else if (name.startsWith("is")) {
					/* 161:164 */key = name.substring(2);
					/* 162: */}
				/* 163:166 */if ((key.length() > 0) &&
				/* 164:167 */(Character.isUpperCase(key.charAt(0))) &&
				/* 165:168 */(method.getParameterTypes().length == 0))
				/* 166: */{
					/* 167:169 */if (key.length() == 1) {
						/* 168:170 */key = key.toLowerCase();
						/* 169:171 */} else if (!Character.isUpperCase(key
							.charAt(1))) {
						/* 170:172 */key =
						/* 171:173 */key.substring(0, 1).toLowerCase()
								+ key.substring(1);
						/* 172: */}
					/* 173:175 */Object elementObj = method.invoke(bean, null);
					/* 174:176 */if ((useClassConvert)
							|| (!(elementObj instanceof Class))) {
						/* 175:179 */jSONObject.put(key,
								getJSONObject(elementObj, useClassConvert));
						/* 176: */}
					/* 177: */}
				/* 178: */}
			/* 179: */catch (Exception localException) {
			}
			/* 180: */}
		/* 181:185 */return new JSONStringObject(jSONObject.toString());
		/* 182: */}
	/* 183: */
}

/*
 * Location: C:\Users\congxiaofan\Desktop\
 * 
 * Qualified Name: com.base.utils.json.JSONUtil
 * 
 * JD-Core Version: 0.7.0.1
 */