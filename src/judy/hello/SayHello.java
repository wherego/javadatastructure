/*

* Copyright ® 2014 guangzhou judyblog Co. Ltd. 

* All right reserved. 

* @author judyge
 
* @version 1.0

* @Time   14/11/19
 
* @descriptin
 
* 这个类是一个实习类 HelloWorld  练习java编程风格
* 包括文档注释   函数 类  常量 变量 包的命名   for 函数缩进风格
* 实现了对其他对象说HelloWorld的功能.
*/


package judy.hello;    

/**

* 类的描述

* @author judyge

* @Time 2014-11-19

* 练习实现了对其他对象说HelloWorld的功能.

*/

public class SayHello {   
	
	/** 招呼语 不会变的 */
	public final String Say_Word="Hello World";   
	
	public String[] sayObj = new String[5];         // 说话的对象数组 
	
	/**

	   * 构造方法 的描述

	   * @param sayObj

	   *  初始化对象数组

	   **/
	public SayHello(String[] sayObj){
		this.sayObj=sayObj;
	}
	
	
	/**

	*说helloworld

	*@param 

	*@return

	*@exception  (方法有异常的话加)

	* @author judyge

	* @Time2014-11-19 15:02:29


	   */
	
	public void sayHello(){
		/*
		 * @param i
		 * 获取说话对象 并说话欢迎
		 * */
		for(int i=0;i<5;i++){
			System.out.println(Say_Word+sayObj[i]);
		}
		
	}
}
