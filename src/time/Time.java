package time;

public class Time {

	public void test1() {  
        // --��һ�����Ժ���Ϊ��λ����ġ�  
        long startTime=System.currentTimeMillis(); //��ȡ��ʼʱ��       
        Role role=new Role();
        role.role();
        long endTime=System.currentTimeMillis(); //��ȡ����ʱ��  
      
        System.out.println("��������ʱ�䣺 "+(endTime-startTime)+"ms");  
        System.out.println("1ʱ�䣺 "+startTime+"ms");  
        System.out.println("2ʱ�䣺 "+endTime+"ms");  
    }  
    public void test2() {  
        //�ڶ�����������Ϊ��λ����ġ�  
        long startTime=System.nanoTime(); //��ȡ��ʼʱ��        
        Role role=new Role();
        role.role();
        long endTime=System.nanoTime(); //��ȡ����ʱ��  
      
        System.out.println("��������ʱ�䣺 "+(endTime-startTime)+"ns");  
        System.out.println("1ʱ�䣺 "+startTime+"ns");  
        System.out.println("2ʱ�䣺 "+endTime+"ns");  
    }  
	
	
}
