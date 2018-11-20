package org.mountcloud.springmvc.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Object工具类
 * @author zhanghaishan
 * @version V1.0
 * date 2015/06/12.
 */
public class ObjectUtil {
	private static final Logger LOG = LoggerFactory.getLogger(ObjectUtil.class);

	/**
	 * 以源对象中的非空属 覆盖目标对象中的属</br> <b>PS</b>：对象需相同类型
	 * 
	 * @param target 目标对象 --PS:为数据库对象
	 * @param source 源对象--PS:为提交上对象
	 * @return 最终对象
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static <T> T cover(Object target, Object source)
			throws InstantiationException, IllegalAccessException {
		source.getClass().newInstance();
		List<Field> fields = new ArrayList<>();
		ObjectUtil.getFields(source.getClass(), fields,null);
		for (Field field : fields) {
			if (Modifier.isFinal(field.getModifiers()))
				continue;
			field.setAccessible(true);
			if (field.get(source) != null)
				field.set(target, field.get(source));
		}
		return (T) target;
	}
	
	/**
	 * 以源对象中的非空属 覆盖目标对象中的属</br> <b>PS</b>：对象需相同类型
	 * 
	 * @param target 目标对象 --PS:为数据库对象
	 * @param source 源对象--PS:为提交上对象
	 * @return 最终对象
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static <T> T coverAndString(Object target, Object source)
			throws InstantiationException, IllegalAccessException {
		source.getClass().newInstance();
		List<Field> fields = new ArrayList<>();
		ObjectUtil.getFields(source.getClass(), fields,null);
		for (Field field : fields) {
			if (Modifier.isFinal(field.getModifiers()))
				continue;
			field.setAccessible(true);
			
			if (field.get(source) != null){
				if(field.getType().equals(String.class)){
					String tempStr = (String) field.get(source);
					if(tempStr!=null&&tempStr.length()!=0){
						field.set(target, field.get(source));
					}
				}else{
					field.set(target, field.get(source));
				}
			}
		}
		return (T) target;
	}

	/**
	 * 返回class中所有方法
	 * @param t 需要返回的class
	 * @param methods 结果需要放入的数组
	 * @param index 需要深入的层级，起始层为1，如果需要查t的父类的方法，index为2，父类的父类为3，依次叠加
	 */
	public static void getMethods(Class t,List<Method> methods,Integer index){
		
		methods.addAll(Arrays.asList(t.getMethods()));
		
		if(index!=null){
			index = index -1;
			if(index<=0){
				return;
			}
		}
		
		Class t1 = t.getSuperclass();
		if (t1.getSimpleName().equals("Object"))
			return;
		getMethods(t1, methods,index);
	}


	/**
	 * 返回class中所有属性
	 * @param t 需要查询的类
	 * @param fields 将结果合并的数组
	 * @param index 需要深入的层级，起始层为1，如果需要查t的父类的方法，index为2，父类的父类为3，依次叠加
	 */
	public static void getFields(Class t, List<Field> fields,Integer index) {
		
		fields.addAll(Arrays.asList(t.getDeclaredFields()));
		
		if(index!=null){
			index = index -1;
			if(index<=0){
				return;
			}
		}
		
		Class t1 = t.getSuperclass();
		if (t1.getSimpleName().equals("Object"))
			return;
		getFields(t1, fields,index);
	}

	/**
	 * 根据属性名获取值
	 * 
	 * @param fieldName 属性名
	 * @param obj 类
	 * @return 获取属性中的值
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getObjectValue(String fieldName, Object obj)
			throws Exception {
		String firstLetter = fieldName.substring(0, 1).toUpperCase();
		String getter = "get" + firstLetter + fieldName.substring(1);
		Method method = obj.getClass().getMethod(getter, new Class[] {});
		Object value = method.invoke(obj, new Object[] {});
		return (T) value;
	}

	/**
	 * 根据属性名设置值
	 * @param obj 实体
	 * @param filedName 属性名
	 * @param val 值
	 * @return 设置的结果
	 */
	public static boolean setVal(Object obj,String filedName,Object val){
		boolean state = false;
		try {
			String upFiledName =  StringUtil.toUpperCaseFirstOne(filedName);
			String setMethodName = "set"+upFiledName;
			Method mt = getMethod(obj,setMethodName,null);
			if(mt!=null){
				mt.invoke(obj, val);
				state = true;
			}
			
		} catch (Exception e) {
			state = false;
			e.printStackTrace();
		}
		return state;
	}

	/**
	 * 返回方法
	 * @param obj 类
	 * @param methodName 方法名称
	 * @param pcls obj的class
	 * @return
	 */
	public static Method getMethod(Object obj,String methodName,Class<?> ...pcls){
		
		Method met = null;
		
		try {
			if(obj!=null&&methodName!=null){
				if(pcls!=null){
					met = obj.getClass().getMethod(methodName, pcls);
				}
				if(pcls==null){
					List<Method> methods = new ArrayList<Method>();
					getMethods(obj.getClass(), methods, 1);
					if(methods!=null&&methods.size()>0){
						for(int i=0;i<methods.size();i++){
							Method mt = methods.get(i);
							if(mt.getName().equals(methodName)){
								met = mt;
								break;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			met= null;
			e.printStackTrace();
		}
		
		return met;
	}

	/**
	 * 给空属性附上默认值
	 * @param objs 实体
	 * @param notSet 不需要设置默认值的属性名
	 */
	public static <T> void setNullFields(List<T> objs,List<String> notSet){
		if(objs!=null){
			objs.forEach((obj)->setNullFields(obj,notSet));
		}
	}

	/**
	 * 将空属性附上默认值
	 * @param obj 实体
	 * @param notSet 不需要设置默认值的实体
	 */
	public static <T> void setNullFields(T obj, List<String> notSet){
		if(obj==null){
			return;
		}
		Class entityClass = obj.getClass();

		List<Field>  entityFields = new ArrayList<Field>();
		getFields(entityClass, entityFields,null);

		for(Field field:entityFields){
			if(field.getName().equals("serialVersionUID")){
				continue;
			}

			if(notSet!=null&&notSet.contains(field.getName())){
				continue;
			}

			try{
				PropertyDescriptor pd = new PropertyDescriptor(field.getName(), entityClass);
				Method get = pd.getReadMethod();
				Object getValue = get.invoke(obj);

				if(getValue==null){

					Method set = pd.getWriteMethod();

					Class fieldClass = field.getType();
					if(fieldClass.equals(String.class)){
						set.invoke(obj,new String(""));
					}

					if(fieldClass.equals(Integer.class)){
						set.invoke(obj,new Integer(0));
					}

					if(fieldClass.equals(Double.class)){
						set.invoke(obj,new Double(0));
					}

					if(fieldClass.equals(Float.class)){
						set.invoke(obj,new Float(0));
					}

					if(fieldClass.equals(Date.class)){
						set.invoke(obj,new Date());
					}

					if(fieldClass.equals(Boolean.class)){
						set.invoke(obj,new Date());
					}
				}

			}catch (Exception e){
				LOG.error(e.getMessage());
			}

		}

	}
}
