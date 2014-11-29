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
  
    //���캯�����Ա������г�ʼ��  
    //����һ�������Ե��࣬���Ҷ�������������һ�����ڴ�Ų�����һ�����ڴ���ڴ��Ľ����  
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
