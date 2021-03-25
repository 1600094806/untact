package com.juj.untact.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Util {
	public static String getNowDateStr() {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		return format1.format(time);
	}
	
	public static Map<String,Object> mapOf(Object... args){
		int size = args.length;
		if(size % 2 != 0) {
			throw new IllegalArgumentException("인자를 잘 입력해주세요.");
		}
		Map<String,Object> map = new HashMap<>();
		for(int i = 0;i<size/2;i++) {
			int keyIndex = i*2;
			int valueIndex = keyIndex+1;
			
			String key = (String)args[keyIndex];
			Object value = args[valueIndex];
			
			map.put(key,value);
			
		}
		return map;
	}
}
