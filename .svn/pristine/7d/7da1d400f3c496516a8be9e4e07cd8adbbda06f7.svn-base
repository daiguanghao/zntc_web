package com.eqy.utils;

import java.util.Collection;
import java.util.Map;

import com.eqy.constants.SystemConstants;

public class CommonMethod {

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj) {
		// Object类型判空
		if (obj == null)
			return true;
		// CharSequence类型判空
		if (obj instanceof CharSequence)
			return ((CharSequence) obj).length() == 0;
		// Collection类型判空
		if (obj instanceof Collection)
			return ((Collection) obj).isEmpty();
		// Map类型判空
		if (obj instanceof Map)
			return ((Map) obj).isEmpty();
		// Object[]类型判空
		if (obj instanceof Object[]) {
			// 类型转换
			Object[] object = (Object[]) obj;
			// 长度为空
			if (object.length == 0) {
				return true;
			}
			boolean empty = true;
			// 循环遍历数组内每个元素
			for (int i = 0; i < object.length; i++) {
				// 递归判断每个元素，某一个元素为空即为空
				if (!isEmpty(object[i])) {
					empty = false;
					break;
				}
			}
			return empty;
		}
		return false;
	}

	/**
	 * 结果验证
	 * 
	 * @param resultCode
	 * @return
	 */
	public static boolean isSuccess(String resultCode) {
		boolean flag = false;
		if (SystemConstants.WEB_SUCCESS.equals(resultCode)) {
			flag = true;
		}
		return flag;
	}
}
