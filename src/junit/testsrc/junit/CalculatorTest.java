package junit;
import org.junit.After;  
import org.junit.AfterClass;  
import org.junit.Assert;  
import org.junit.Before;  
import org.junit.BeforeClass;  
import org.junit.Ignore;  
import org.junit.Test;  

public class CalculatorTest {
	
	private Calculator cal = new Calculator();  
	  
    @BeforeClass // 注意,这里必须是static...因为方法将在类被装载的时候就被调用(那时候还没创建实例)  
    public static void before()  
    {  
        System.out.println("global");  
    }  
  
    @AfterClass  
    public static void after() {  
        System.out.println("global destroy");  
    }  
  
    @Before  
    public void setUp() throws Exception {  
        System.out.println("一个测试开始。。");  
    }  
  
    @After  
    public void tearDown() throws Exception {  
        System.out.println("一个测试结束");  
    }  
  
    @Test  
    @Ignore  
    public void testAdd() {  
        int result = cal.add(1, 2);  
        Assert.assertEquals(3, result);  
    }  
  
    @Test  
    public void testMinus() {  
        int result = cal.minus(5, 2);  
        Assert.assertEquals(3, result);  
    }  
  
    @Test  
    public void testMultiply() {  
        int result = cal.multiply(4, 2);  
        Assert.assertEquals(8, result);  
    }  
  
    @Test(timeout = 1000) // 单位为毫秒  
    public void testSquareRoot() {  
        cal.squareRoot(4);  
    }  
  
    @Test(expected = Exception.class)  
    public void testDivide() throws Exception {  
        System.out.println("teddd");  
        cal.divide(4, 0);// 很简单的办法.......  
    }  

}
