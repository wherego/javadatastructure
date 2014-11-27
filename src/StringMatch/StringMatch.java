package StringMatch;

public class StringMatch {
    
    int compare_num;

    public StringMatch() {
        this.compare_num = 0;
    }
        
    void clear_num(){
        this.compare_num=0;
    }
    
    public boolean add_num(){
        this.compare_num++;
        return true;
    }
        
    /**
     * BF 串匹配算法
     * @param s 主串
     * @param t 模式
     * @return
     */
    public int bf(String s,String t){
        this.clear_num(); //清空比较次数
        int j;
        for(int i=0;i<s.length()-t.length()+1;i++){
            for(j=0;j<t.length();j++){
                this.add_num();
                if(s.charAt(i+j)!=t.charAt(j)){ //如果有一字符不匹配，则回溯i,j
                    j=0;
                    break;    
                }                
            }
            this.add_num();
            if (j==t.length()){ //All T matched??
                System.out.println("BF比较次数："+this.compare_num);
                return i;
            }
        }
        
        return 0;
    }
    
    /**
     * KMP 串匹配算法  王红梅老师书上的KMP算法与严蔚敏老师《数据结构》书的
     * KMP相同，采用同样next[]定义，我在实现对应的Java代码的时候不好转换，
     * 主要因为下标的关系，所以我参考了《算法导论》上的KMP算法，
     * next[q] = max{k:k<p and P[k] is a suffix of P[q]};
     * @param s 主串
     * @param p 模式
     * @return
     */
    public int kmp(String s,String p){
                
        int[] next = next(p); //init the next[]
        this.clear_num(); //清空比较次数
        int q=0;  //number of characters matched
        for(int i=0;i<s.length();i++){
            while(this.add_num()&&(q>0)&&(p.charAt(q)!=s.charAt(i))){
                q=next[q-1];  //next character doesn't match
            }
            this.add_num();
            if(p.charAt(q)==s.charAt(i)) q++;  //next character matches
            this.add_num();
            if(q==p.length()){  //All the P matched???
                System.out.println("KMP比较次数："+this.compare_num);
                return i+1-p.length();
            }
        }        
        return 0;
    }
    
    /**
     * KMP next[]计算, 可以看成是P与P匹配的问题，P[q]是主串P[k]是模式
     * @param p
     * @return
     */
    public int[] next(String p){
        this.clear_num(); //清空比较次数
        int[] next = new int[p.length()];
        next[0] = 0;
        int k=0; //number of characters matched
        for(int q=1;q<p.length();q++){
            while (this.add_num()&&(k>0)&&(p.charAt(k)!=p.charAt(q))) k = next[k];
            this.add_num();
            if(p.charAt(k)==p.charAt(q)) k++;
            next[q] = k;                    
        }
        
        System.out.println("KMP求next数组的比较次数:"+this.compare_num);
        return next;        
    }
    

    /**
     * @param args
     */
    public static void main(String[] args) {
        StringMatch m = new StringMatch();
        System.out.println(m.bf("ababcabccabccacbab", "abccac"));
        System.out.println(m.kmp("ababcabccabccacbab", "abccac"));
    }

}
