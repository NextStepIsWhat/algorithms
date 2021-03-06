package lhe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassUtil {

	/**
	 * 
	  * 打印类的方法信息
	  * 
	  * @param obj    
	  * @return void
	 */
	public static void printClassMethodMessage(Object obj){
		Class<?> c = obj.getClass();
		//获取类的名称
		System.out.println("类的名称："+c.getName());
		
		/*
		 * Method类，方法对象
		 * 一个成员方法就是一个Method对象
		 * getMethods()方法获取的是所有的public的函数，包括父类继承而来的函数
		 * getDeclaredMethods()获取的是所有该类自己声明的方法，不同的访问权限
		 */
		Method[] ms = c.getMethods();//c.getDeclaredMethods()
		for(int i=0;i<ms.length;i++){
			//得到方法的返回值类型的类类型
			Class<?> returnType = ms[i].getReturnType();
			System.out.print(returnType.getName()+" ");
			//得到方法的名称
			System.out.print(ms[i].getName() + "(");
			//得到方法的参数类型---得到的是参数列表的类型的类类型
			@SuppressWarnings("rawtypes")
			Class[] paramTypes = ms[i].getParameterTypes();
			for(Class<?> class1: paramTypes){
				System.out.print(class1.getName()+",");
			}
			System.out.println(")");
		}
	}
	/**
	 * 
	  * 打印类的成员变量
	  * 成员变量也是对象
	  * java.lang.reflect.Field
	  * Field类封装了关于成员变量的操作
	  * @param obj    
	  * @return void
	 */
	public static void printClassFieldMessage(Object obj){
		Class<?> c = obj.getClass();
		//获取类的名称
		System.out.println("类的名称："+c.getName());
		/*
		 * Field类，
		 * c.getFields()： 获取的是所有的public的成员变量的信息
		 * c.getDeclaredFields()：获取的是该类自己声明的成员变量信息
		 */
		Field[] fields = c.getDeclaredFields();
		for(Field field: fields){
			//得到成员变量的类型的类类型
			Class<?> fieldType = field.getType();
			System.out.print(fieldType.getName() + " ");
			//得到成员变量的名称
			System.out.println(field.getName()+",");	
		}		
	}
	
	/**
	 * 
	  * 打印类的构造函数的信息
	  *
	  * 
	  * @param obj    
	  * @return void
	 */
	public static void printClassConMessage(Object obj){
		Class<?> c = obj.getClass();
		/*
		 * 构造函数也是对象
		 * java.lang.Constructor中封装了构造函数的信息
		 * getConstructors获取所有的public的构造函数
		 * getDeclaredConstructors得到所有的构造函数
		 */
		@SuppressWarnings("rawtypes")
		Constructor[] cons = c.getDeclaredConstructors();
		for(Constructor<?> con: cons){
			//得到方法的名称
			System.out.print(con.getName() + "(");
			//得到方法的参数类型---得到的是参数列表的类型的类类型
			@SuppressWarnings("rawtypes")
			Class[] paramTypes = con.getParameterTypes();
			for(Class<?> class1: paramTypes){
				System.out.print(class1.getName()+",");
			}
			System.out.println(")");
		}
	}
	
	/**
	 * 
	  * 反射方法调用
	  *
	  * @param obj
	  * @param methodName
	  * @param paramTypes
	  * @param args    
	  * @return void
	 */
	public static void methodInvoked(Object obj, String methodName, Class[] paramTypes, Object[] args){
		Class<?> c = obj.getClass();
		
		try {
			Method m = c.getMethod(methodName, paramTypes);
			m.invoke(c, args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		int i =1;
//		ClassUtil.printClassMethodMessage(i);
		
		String str = "string";
//		ClassUtil.printClassMethodMessage(str);
//		ClassUtil.printClassFieldMessage(str);
//		ClassUtil.printClassConMessage(str);
		
		@SuppressWarnings("rawtypes")
		Class[] paramTypes = {Object.class};
		String[] strs = {"hello"};
		ClassUtil.methodInvoked(new ClassUtil(), "printClassFieldMessage", paramTypes, strs);
	}
}
