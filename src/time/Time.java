package time;

public class Time {

	public void test1() {  
        // --第一种是以毫秒为单位计算的。  
        long startTime=System.currentTimeMillis(); //获取开始时间       
        Role role=new Role();
        role.role();
        long endTime=System.currentTimeMillis(); //获取结束时间  
      
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");  
        System.out.println("1时间： "+startTime+"ms");  
        System.out.println("2时间： "+endTime+"ms");  
    }  
    public void test2() {  
        //第二种是以纳秒为单位计算的。  
        long startTime=System.nanoTime(); //获取开始时间        
        Role role=new Role();
        role.role();
        long endTime=System.nanoTime(); //获取结束时间  
      
        System.out.println("程序运行时间： "+(endTime-startTime)+"ns");  
        System.out.println("1时间： "+startTime+"ns");  
        System.out.println("2时间： "+endTime+"ns");  
    }  
	
	
}
