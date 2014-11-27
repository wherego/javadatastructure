package HzArrayDC3;

import HzArrayDC3.HzArrayDC3.Suffix;

public class Test {
	
	public static void main(String[] args) {  
        String text = "GACCCACCACC#";  
        HzArrayDC3 dc3 = new HzArrayDC3();  
        Suffix suffix = dc3.solve(text);  
        System.out.format("Text: %s%n",text);  
        dc3.report(suffix);  
          
        text = "mississippi#";  
        dc3 = new HzArrayDC3();  
        suffix = dc3.solve(text);  
        System.out.format("Text: %s%n",text);  
        dc3.report(suffix);  
          
        text = "abcdefghijklmmnopqrstuvwxyz#";  
        dc3 = new HzArrayDC3();  
        suffix = dc3.solve(text);  
        System.out.format("Text: %s%n",text);  
        dc3.report(suffix);  
          
        text = "yabbadabbado#";  
        dc3 = new HzArrayDC3();  
        suffix = dc3.solve(text);  
        System.out.format("Text: %s%n",text);  
        dc3.report(suffix);  
          
        text = "DFDLKJLJldfasdlfjasdfkldjasfldafjdajfdsfjalkdsfaewefsdafdsfa#";  
        dc3 = new HzArrayDC3();  
        suffix = dc3.solve(text);  
        System.out.format("Text: %s%n",text);  
        dc3.report(suffix);  
    }  

}
