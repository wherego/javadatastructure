package annotation;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;


public class test {
	
	public static void main(String[] args) {
		Annotation ann = Model.class.getAnnotation(RClazz.class);
		String str = ann.toString();
		System.out.println(str);
		
		Field[] fields = Model.class.getDeclaredFields();
		for(int i=0;i<fields.length;i++){
			Field field = fields[i];
			Annotation fa = field.getAnnotation(RField.class);
			String s = fa.toString();
			System.out.print(s + " " + field.getName() + " " );
			System.out.println(field.getType().getName());
		}
	}

}
