package reflect;
import java.lang.reflect.Field;
import reflect.User;

public class test {
	
	public static void main(String[] args)throws Exception {
        Class cls = Class.forName("reflect.User");
        Object obj = cls.newInstance();       //创建User的对象
        Field f = cls.getDeclaredField("test");    //获取test属性
        f.setAccessible(true);                    //打开私有属性test的访问权限
        f.set(obj, "zhangsan");                    //为test重新复制
        System.out.println(f.get(obj));            //获取obj的test属性值
        //根据方法名execute获取方法
        java.lang.reflect.Method m = cls.getMethod("execute", String.class, int.class);
        m.invoke(obj, "dennisit",23);            //调用execute方法
    }
	

}
