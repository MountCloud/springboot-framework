package org.mountcloud.springmvc.framework.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具
 * @author zhanghaishan
 * @version V1.0
 * date 2015/06/12.
 */
public class StringUtil {

	/**
	 * 首字母转大写
	 * @param s 字符串
	 * @return 转换后的字符串
	 */
    public static String toUpperCaseFirstOne(String s){
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

	/**
	 * 根据正则查找字符串
	 * @param str 字符串
	 * @param reg 正则
	 * @return 查询结果
	 */
	public static List<String> extractString(String str,String reg){
    	List<String> strs = new ArrayList<String>();
    	
		String regexString = reg;
		Pattern p = Pattern.compile(regexString);
		Matcher m = p.matcher(str);
		boolean result = m.find();
		while (result) {
			strs.add(m.group());
			result = m.find();
		}
		
		return strs;
    }

	/**
	 * 字符串转Int
	 * @param obj 字符串
	 * @return 转换结果
	 */
	public static Integer parseInt(Object obj){
    	if(obj==null){
    		return null;
    	}
    	return Integer.parseInt(obj.toString());
    }

}
