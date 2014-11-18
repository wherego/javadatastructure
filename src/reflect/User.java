package reflect;

import java.io.Serializable;

public class User implements Serializable {
	
	
	   private String test;
	    
	    public void execute(String name,int age){
	        System.out.println("name=" + name + ",age=" + age);
	    }

}
