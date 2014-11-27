package BruteForce;

public class BruteForce {  
    /** 
     * @param mainStr 主串 
     * @param subStr 子串 
     * @return 字符串匹配成功起始位置 
     */  
    public static int getMatchIndex(String mainStr, String subStr) {  
        int i = 0, j = 0;  
        while (i < mainStr.length()) {  
            if (mainStr.charAt(i) == subStr.charAt(j)) { // 两字母相等则继续  
                i++;  
                j++;  
            } else { // 两字母不等则角标后退重新开始匹配  
                i = i - j + 1; // i 回退到上次匹配首位的下一位  
                j = 0; // j 回退到子串的首位  
            }  
            if (j == subStr.length())  
                return i - j;  
        }  
        return -1;  
    }  
  
    public static void main(String[] args) {  
        System.out.println("匹配成功的位置为: " + getMatchIndex("god is a girl", "girl"));  
    }  
  
}  
