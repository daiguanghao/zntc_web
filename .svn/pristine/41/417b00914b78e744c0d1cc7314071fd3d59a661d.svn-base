package com.eqy.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) Dec 11, 2013 9:53:31 AM
 * <p>
 * Company: 欣网视讯
 * <p>
 * 
 * @author daiguanghao@mail.xwtec.cn
 * @version 1.0.0
 */

public final class JSONUtils {

	/**
	 * JSONObject转换为String所需的换行空格数
	 */
	private static final int TAB_LEN = 4;

	private JSONUtils() {
	}

	public static JSONObject newJSONObject() {
		return new JSONObject();
	}

	/**
	 * String转换为JSONObject
	 * 
	 * @param jsonString
	 *            字符串
	 * @return jsonObject
	 */
	public static JSONObject toJSONObject(String jsonString) {
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(jsonString);
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	/**
	 * String转换为JSONArray
	 * 
	 * @param jsonString
	 *            字符串
	 * @return jsonArray
	 */
	public static JSONArray toJSONArray(String jsonString) {
		JSONArray jsonArray = null;
		if (null != jsonString) {
			try {
				jsonArray = new JSONArray(jsonString);
			}
			catch (JSONException e) {
				System.out.println("String [" + jsonString + "] is not a valid JSON array.");
			}
		}
		return jsonArray;
	}

	/**
	 * jsonObject转换为String
	 * 
	 * @param jsonObject
	 *            jsonObject对象
	 * @return jsonString
	 */
	public static String toString(JSONObject jsonObject) {
		String jsonString = null;
		if (null != jsonObject) {
			jsonString = jsonObject.toString();
		}
		return jsonString;
	}

	/**
	 * 将JSON对象转换为缩进形式的字符串
	 * 
	 * @param jsonObject
	 *            JSON对象
	 * @return 缩进形式的字符串
	 */
	public static String toIndentString(JSONObject jsonObject) {
		if (null == jsonObject) {
			return "";
		}

		try {
			return jsonObject.toString(TAB_LEN);
		}
		catch (Exception e) {
			System.out.println("Convert JSONObject to string failed.");
			return "";
		}
	}

	/**
	 * 将JSON字符串转换为缩进形式的字符串
	 * 
	 * @param jsonData
	 *            JSON字符串
	 * @return 缩进形式的字符串,转换失败则返回原字符串
	 */
	public static String toIndentString(String jsonData) {
		try {
			JSONObject jsonObject = new JSONObject(jsonData);
			return jsonObject.toString(TAB_LEN);
		}
		catch (Exception e) {
			System.out.println("String [" + jsonData + "] is not a valid JSON string.");
			return jsonData;
		}
	}

	/**
	 * 将JSON数组转换为String数组
	 * 
	 * @param jsonArray
	 *            JSON数组: 要求每个元素均是String类型,非String类型转换后为空
	 * @return String数组
	 */
	public static String[] toStringArray(JSONArray jsonArray) {
		String[] stringArray = null;
		if (null != jsonArray && jsonArray.length() > 0) {
			stringArray = new String[jsonArray.length()];
			for (int i = 0; i < jsonArray.length(); i++) {
				stringArray[i] = jsonArray.optString(i);
			}
		}
		return stringArray;
	}


	



	/**
	 * 获取JSONObject对象中的value值
	 * 
	 * @param jsonObject
	 *            jsonObject对象
	 * @param fieldKey
	 *            JSONObject对象中的key
	 * @return fieldValue
	 */
	public static String getString(JSONObject jsonObject, String fieldKey) {
		String fieldValue = null;
		if (null != jsonObject) {
			try {
				fieldValue = jsonObject.getString(fieldKey);
			}
			catch (JSONException ex) {
							}
		}
		return fieldValue;
	}

	/**
	 * 获取JSONObject对象中的value值的字节数组
	 * 
	 * @param jsonObject
	 *            jsonObject对象
	 * @param fieldKey
	 *            JSONObject对象中的key
	 * @return byteValue
	 */
	public static byte[] getBytes(JSONObject jsonObject, String fieldKey) {
		String fieldValue = null;
		byte[] byteValue = null;

		if (null != jsonObject) {
			try {
				fieldValue = jsonObject.getString(fieldKey);

				if (null != fieldValue) {
					byteValue = fieldValue.getBytes();
				}
			}
			catch (JSONException ex) {
				//log.debug("Occur Exception when invoking method getBytes(" + fieldKey + ")!", ex);
			}
		}
		return byteValue;
	}

	/**
	 * 根据索引获取JSONArray中的JSONObject
	 * 
	 * @param jsonArray
	 *            JSONArray对象
	 * @param index
	 *            索引
	 * @return fieldValue
	 */
	public static Object getObject(JSONArray jsonArray, int index) {
		Object fieldValue = null;
		if (null != jsonArray) {
			try {
				fieldValue = jsonArray.get(index);
			}
			catch (JSONException ex) {
				//log.debug("Occur Exception when invoking method getObject(" + index + ")!", ex);
			}
		}
		return fieldValue;
	}

	/**
	 * 获取JSONObject对象中long型的value值
	 * 
	 * @param jsonObject
	 *            jsonObject对象
	 * @param fieldKey
	 *            JSONObject中的key
	 * @return fieldValue
	 */
	public static Long getLong(JSONObject jsonObject, String fieldKey) {
		Long fieldValue = null;
		if (null != jsonObject) {
			try {
				fieldValue = jsonObject.getLong(fieldKey);
			}
			catch (JSONException ex) {
				//log.debug("There is no field named '" + fieldKey + "' or it is not a Long value.");
			}
		}
		return fieldValue;
	}

	/**
	 * 向JSON数据中插入键值对
	 * 
	 * @param jsonObject
	 *            JSON数据
	 * @param fieldKey
	 *            键
	 * @param fieldValue
	 *            值
	 * @return 插入后的JSON数据
	 */
	public static JSONObject putObject(JSONObject jsonObject, String fieldKey, Object fieldValue) {
		JSONObject returnJsonObject = null;
		if (null != jsonObject && null != fieldKey) {
			try {
				returnJsonObject = jsonObject.putOpt(fieldKey, fieldValue);
			}
			catch (JSONException ex) {
				//log.error("Field value is invalid [key=" + fieldKey + ",value=" + fieldValue + ".");
			}
		}
		return returnJsonObject;
	}

	/**
	 * 向JSON数据中插入键值对
	 * 
	 * @param jsonObject
	 *            JSON数据
	 * @param fieldKey
	 *            键
	 * @param fieldValue
	 *            值
	 * @param defaultValue
	 *            缺省值，值为null时插入此值
	 * @return 插入后的JSON数据
	 */
	public static JSONObject putObject(JSONObject jsonObject, String fieldKey, Object fieldValue, Object defaultValue) {
		JSONObject returnJsonObject = null;
		if (null != jsonObject && null != fieldKey) {
			try {
				if (null != fieldValue) {
					returnJsonObject = jsonObject.putOpt(fieldKey, fieldValue);
				} else {
					returnJsonObject = jsonObject.putOpt(fieldKey, defaultValue);
				}
			}
			catch (JSONException ex) {
				//log.error("Field value is invalid [key=" + fieldKey + ",value=" + fieldValue + ".");
			}
		}
		return returnJsonObject;
	}

	/**
	 * 获取JSONObject中的JSONObject对象
	 * 
	 * @param jsonObject
	 *            jsonObject对象
	 * @param fieldKey
	 *            JSONObject中key
	 * @return childJSONObject
	 */
	public static JSONObject getJSONObject(JSONObject jsonObject, String fieldKey) {
		JSONObject childJSONObject = null;
		try {
			childJSONObject = jsonObject.getJSONObject(fieldKey);
		}
		catch (JSONException ex) {
			//log.debug("There is no field named '" + fieldKey + "' or it is not a JSONObject.");
		}
		return childJSONObject;
	}

	/**
	 * 获取JSONObject中的JSONArray对象
	 * 
	 * @param jsonObject
	 *            jsonObject对象
	 * @param fieldKey
	 *            JSONObject中key
	 * @return childJSONArray
	 */
	public static JSONArray getJSONArray(JSONObject jsonObject, String fieldKey) {
		JSONArray childJSONArray = null;
		try {
			childJSONArray = jsonObject.getJSONArray(fieldKey);
		}
		catch (JSONException ex) {
			//log.debug("There is no field named '" + fieldKey + "' or it is not a JSONArray.");
		}
		return childJSONArray;
	}

	/**
	 * JSONArray添加Object对象
	 * 
	 * @param jsonArray
	 *            jsonArray对象
	 * @param object
	 *            Object对象
	 * @return jsonArray
	 */
	public static JSONArray putObject(JSONArray jsonArray, Object object) {
		if (null != jsonArray) {
			jsonArray.put(object);
		}
		return jsonArray;
	}

	/**
	 * 检查JSONObject中是否含某条记录
	 * 
	 * @param jsonObject
	 *            jsonObject对象
	 * @param fieldName
	 *            jsonObject对象中的key
	 * @return 是否含某条记录
	 */
	public static boolean checkFieldString(JSONObject jsonObject, String fieldName) {
		if (null == jsonObject) {
			return false;
		}
		try {
			return null != jsonObject.getString(fieldName);
		}
		catch (JSONException e) {
			return false;
		}
	}

	/**
	 * 检查JSONObject中是否含某条记录
	 * 
	 * @param jsonObject
	 *            jsonObject对象
	 * @param fieldName
	 *            jsonObject对象中的key
	 * @return 是否含某条记录
	 */
	public static boolean checkFieldLong(JSONObject jsonObject, String fieldName) {
		if (null == jsonObject) {
			return false;
		}
		try {
			jsonObject.getLong(fieldName);
			return true;
		}
		catch (JSONException e) {
			return false;
		}
	}

	/**
	 * 检查JSONObject中是否含某条记录
	 * 
	 * @param jsonObject
	 *            jsonObject对象
	 * @param fieldName
	 *            jsonObject对象中的key
	 * @return 是否含某条记录
	 */
	public static boolean checkFieldInt(JSONObject jsonObject, String fieldName) {
		if (null == jsonObject) {
			return false;
		}
		try {
			jsonObject.getInt(fieldName);
			return true;
		}
		catch (JSONException e) {
			return false;
		}
	}

	/**
	 * 检查JSONObject中是否含某条记录
	 * 
	 * @param jsonObject
	 *            jsonObject对象
	 * @param fieldName
	 *            jsonObject对象中的key
	 * @return 是否含某条记录
	 */
	public static boolean checkFieldBoolean(JSONObject jsonObject, String fieldName) {
		if (null == jsonObject) {
			return false;
		}
		try {
			jsonObject.getBoolean(fieldName);
			return true;
		}
		catch (JSONException e) {
			return false;
		}
	}

	/**
	 * 在同一入口参数检测中调用
	 * 
	 * @param value
	 *            需要判断的值
	 * @return 参数是否可用
	 */
	private static boolean checkParamFromClient(String value) {
		if (null != value && !"".equals(value)) {
			return true;
		}

		return false;

	}

	/**
	 * 合并两个JSONObject对象
	 * 
	 * @param destObject
	 *            目标JSONObject对象
	 * @param srcObject
	 *            原始JSONObject对象
	 * @return result
	 */
	public static JSONObject combine(JSONObject destObject, JSONObject srcObject) {
		JSONObject result = destObject;
		String[] names = JSONObject.getNames(srcObject);
		for (String name : names) {
			try {
				result.put(name, srcObject.get(name));
			}
			catch (JSONException e) {
				//log.error("key cannot read '" + name + "' ");
			}
		}
		return result;
	}

	/**
	 * 解析客户端自定规则结构 放置Map中
	 * 
	 * @param body
	 *            消息体
	 * @param fieldKey
	 *            结构名称
	 * @return 转换之后结构
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseObjectToMap(JSONObject body, String fieldKey) {
		JSONObject clientVerCheck = body.optJSONObject(fieldKey);
		if (null != clientVerCheck) {
			String key = null;
			Map<String, String> map = new HashMap<String, String>(clientVerCheck.length());
			Iterator<String> ite = clientVerCheck.keys();
			while (ite.hasNext()) {
				key = (String) ite.next();
				map.put(key, clientVerCheck.optString(key));
			}

			return map;
		}

		return null;
	}

	/**
	 * 将JSONObject转换为Map
	 * 
	 * @author wujinlong
	 * @param jsonObject
	 *            要转换为Map的JSONObject
	 * @return 转换后的Map
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> convertJSONObjectToMap(JSONObject jsonObject) {
		Map<String, String> map = new HashMap<String, String>();
		if (null != jsonObject) {
			Iterator<String> ite = jsonObject.keys();
			String key = null;
			while (ite.hasNext()) {
				key = ite.next();
				map.put(key, jsonObject.optString(key));
			}
		}
		return map;
	}

	/**
	 * 将JSONArray转换为List
	 * 
	 * @author 
	 * @param jsonArray
	 *            要转换为List的JSONArray
	 * @return 转换后的list
	 */
	public static List<Map<String, String>> convertJSONArrayToList(JSONArray jsonArray) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (null != jsonArray && jsonArray.length() > 0) {
			for (int i = 0; i < jsonArray.length(); i++) {
				list.add(convertJSONObjectToMap(jsonArray.optJSONObject(i)));
			}
		}
		return list;
	}

}
