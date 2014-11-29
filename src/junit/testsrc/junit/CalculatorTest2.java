package junit;
import java.util.Arrays;  
import java.util.Collection;  
import org.junit.Assert;  
import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.junit.runners.Parameterized;  
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class CalculatorTest2 {

    private Calculator cal = new Calculator();  
    private int param;  
    private int result;  
  
    //构造函数，对变量进行初始化  
    //定义一个待测试的类，并且定义两个变量，一个用于存放参数，一个用于存放期待的结果。  
    public CalculatorTest2(int param, int result) {  
           this.param = param;  
           this.result = result;  
    }  
      
    @Parameters  
    public static Collection data(){  
        return Arrays.asList(new Object[][]{  
            {2, 4},  
            {0, 0},  
            {-3, 9},  
      });  
    }  
  
    @Test  
    public void squareTest() {  
        int temp = cal.square(param);  
        Assert.assertEquals(result, temp);  
    }  
	
}
