package com.adjust.api.web.rest;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ClassUpdater {

	static public Object updateClass(Object updater, Object updatee) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		for (Field f : updatee.getClass().getDeclaredFields()) {
			String setMethodName = "set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
			Object value;
			if (f.getType() == Boolean.class || f.getType() == boolean.class) {
				String isMethodName = "is" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
				value = updater.getClass().getDeclaredMethod(isMethodName).invoke(updater);
			} else {
				String getMethodName = "get" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
				value = updater.getClass().getDeclaredMethod(getMethodName).invoke(updater);
			}
			if (value != null) {
				updatee.getClass().getDeclaredMethod(setMethodName, f.getType()).invoke(updatee, value);
			}
		}
		return updatee;
	}
}
