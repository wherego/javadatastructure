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
	  
    @BeforeClass // ע��,���������static...��Ϊ���������౻װ�ص�ʱ��ͱ�����(��ʱ��û����ʵ��)  
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
        System.out.println("һ�����Կ�ʼ����");  
    }  
  
    @After  
    public void tearDown() throws Exception {  
        System.out.println("һ�����Խ���");  
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
  
    @Test(timeout = 1000) // ��λΪ����  
    public void testSquareRoot() {  
        cal.squareRoot(4);  
    }  
  
    @Test(expected = Exception.class)  
    public void testDivide() throws Exception {  
        System.out.println("teddd");  
        cal.divide(4, 0);// �ܼ򵥵İ취.......  
    }  

}
