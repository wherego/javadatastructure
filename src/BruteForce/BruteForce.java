package BruteForce;

public class BruteForce {  
    /** 
     * @param mainStr ���� 
     * @param subStr �Ӵ� 
     * @return �ַ���ƥ��ɹ���ʼλ�� 
     */  
    public static int getMatchIndex(String mainStr, String subStr) {  
        int i = 0, j = 0;  
        while (i < mainStr.length()) {  
            if (mainStr.charAt(i) == subStr.charAt(j)) { // ����ĸ��������  
                i++;  
                j++;  
            } else { // ����ĸ������Ǳ�������¿�ʼƥ��  
                i = i - j + 1; // i ���˵��ϴ�ƥ����λ����һλ  
                j = 0; // j ���˵��Ӵ�����λ  
            }  
            if (j == subStr.length())  
                return i - j;  
        }  
        return -1;  
    }  
  
    public static void main(String[] args) {  
        System.out.println("ƥ��ɹ���λ��Ϊ: " + getMatchIndex("god is a girl", "girl"));  
    }  
  
}  
