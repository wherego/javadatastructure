/*

* Copyright ® 2014 guangzhou judyblog Co. Ltd. 

* All right reserved. 

* @author judyge
 
* @version 1.0

* @Time   14/11/19
 
* @descriptin
 
* 测试类是否实现好 方法是否实现好
*/



package judy.hello;

/**

* 类的描述

* @author judyge

* @Time 2014-11-19

* 测试类功能是否正常

*/

public class Test {
	
	public static void main(String args[]){
		String[] sayObj = new String[]{"C++","C","python","PHP","lua"};  //数组  赋值 给SayHello构造方法
		SayHello sayHi=new SayHello(sayObj);        //对象  SayHello类的实例
		sayHi.sayHello();		
	}

}
