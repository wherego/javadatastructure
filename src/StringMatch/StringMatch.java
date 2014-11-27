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
     * BF ��ƥ���㷨
     * @param s ����
     * @param t ģʽ
     * @return
     */
    public int bf(String s,String t){
        this.clear_num(); //��ձȽϴ���
        int j;
        for(int i=0;i<s.length()-t.length()+1;i++){
            for(j=0;j<t.length();j++){
                this.add_num();
                if(s.charAt(i+j)!=t.charAt(j)){ //�����һ�ַ���ƥ�䣬�����i,j
                    j=0;
                    break;    
                }                
            }
            this.add_num();
            if (j==t.length()){ //All T matched??
                System.out.println("BF�Ƚϴ�����"+this.compare_num);
                return i;
            }
        }
        
        return 0;
    }
    
    /**
     * KMP ��ƥ���㷨  ����÷��ʦ���ϵ�KMP�㷨����ε����ʦ�����ݽṹ�����
     * KMP��ͬ������ͬ��next[]���壬����ʵ�ֶ�Ӧ��Java�����ʱ�򲻺�ת����
     * ��Ҫ��Ϊ�±�Ĺ�ϵ�������Ҳο��ˡ��㷨���ۡ��ϵ�KMP�㷨��
     * next[q] = max{k:k<p and P[k] is a suffix of P[q]};
     * @param s ����
     * @param p ģʽ
     * @return
     */
    public int kmp(String s,String p){
                
        int[] next = next(p); //init the next[]
        this.clear_num(); //��ձȽϴ���
        int q=0;  //number of characters matched
        for(int i=0;i<s.length();i++){
            while(this.add_num()&&(q>0)&&(p.charAt(q)!=s.charAt(i))){
                q=next[q-1];  //next character doesn't match
            }
            this.add_num();
            if(p.charAt(q)==s.charAt(i)) q++;  //next character matches
            this.add_num();
            if(q==p.length()){  //All the P matched???
                System.out.println("KMP�Ƚϴ�����"+this.compare_num);
                return i+1-p.length();
            }
        }        
        return 0;
    }
    
    /**
     * KMP next[]����, ���Կ�����P��Pƥ������⣬P[q]������P[k]��ģʽ
     * @param p
     * @return
     */
    public int[] next(String p){
        this.clear_num(); //��ձȽϴ���
        int[] next = new int[p.length()];
        next[0] = 0;
        int k=0; //number of characters matched
        for(int q=1;q<p.length();q++){
            while (this.add_num()&&(k>0)&&(p.charAt(k)!=p.charAt(q))) k = next[k];
            this.add_num();
            if(p.charAt(k)==p.charAt(q)) k++;
            next[q] = k;                    
        }
        
        System.out.println("KMP��next����ıȽϴ���:"+this.compare_num);
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
