package HzArrayDC3;

public class HzArrayDC3 {
	
	
	/** 
	 *  
	 * Build Suffix Array using DC3/KS Algorithm  
	 *   
	 *   
	 * Copyright (c) 2011 ljs (http://blog.csdn.net/ljsspace/) 
	 * Licensed under GPL (http://www.opensource.org/licenses/gpl-license.php)  
	 *  
	 * @author ljs 
	 * 2011-07-18 
	 * 
	 */  
 
	    public static final char MAX_CHAR = '\u00FF';  
	  
	    class Suffix{  
	        int[] sa;    
	        //Note: the p-th suffix in sa: SA[rank[p]-1]];  
	        //p is the index of the array "rank", start with 0;  
	        //a text S's p-th suffix is S[p..n], n=S.length-1.  
	        int[] rank;   
	        boolean done;  
	           
	        public Suffix(int[] sa,int[] rank){  
	            this.sa = sa;  
	            this.rank = rank;  
	        }  
	    }  
	      
	  
	    //a prefix of suffix[isuffix] represented with digits  
	    class Tuple{  
	        int isuffix; //the p-th suffix  
	        int[] digits;  
	        public Tuple(int suffix,int[] digits){  
	            this.isuffix = suffix;  
	            this.digits = digits;             
	        }  
	        public String toString(){  
	            StringBuffer sb = new StringBuffer();             
	            sb.append(isuffix);  
	            sb.append("(");  
	            for(int i=0;i<digits.length;i++){  
	                sb.append(digits[i]);  
	                if(i<digits.length-1)  
	                    sb.append("-");  
	            }  
	            sb.append(")");  
	            return sb.toString();  
	        }  
	    }  
	      
	    //d: the digit to do countingsort  
	    //max: A value's range is 0...max  
	    private void countingSort(int d,Tuple[] tA,Tuple[] tB,int max){  
	        //init the counter array  
	        int[] C = new int[max+1];  
	        for(int i=0;i<=max;i++){  
	            C[i] = 0;  
	        }  
	        //stat the count  
	        for(int j=0;j<tA.length;j++){  
	            C[tA[j].digits[d]]++;  
	        }  
	        //process the counter array C  
	        for(int i=1;i<=max;i++){  
	            C[i]+=C[i-1];  
	        }  
	        //distribute the values    
	        for(int j=tA.length-1;j>=0;j--){  
	            //C[A[j]] <= A.length   
	            tB[--C[tA[j].digits[d]]]=tA[j];           
	        }  
	    }  
	      
	    //tA: input  
	    //tB: output for rank caculation  
	    private void radixSort(Tuple[] tA,Tuple[] tB,int max,int digitsLen){  
	        int len = tA.length;  
	        int digitsTotalLen = tA[0].digits.length;  
	              
	        for(int d=digitsTotalLen-1,j=0;j<digitsLen;d--,j++){  
	            this.countingSort(d, tA, tB, max);  
	            //assign tB to tA  
	            if(j<digitsLen-1){  
	                for(int i=0;i<len;i++){  
	                    tA[i] = tB[i];  
	                }         
	            }  
	        }  
	    }  
	      
	    //max is the maximum value in any digit of TA.digits[], used for counting sort  
	    //tA: input  
	    //tB: the place holder, reused between iterations  
	    private Suffix rank(Tuple[] tA,Tuple[] tB,int max,int digitsLen){         
	        int len = tA.length;          
	        radixSort(tA,tB,max,digitsLen);   
	          
	        int digitsTotalLen = tA[0].digits.length;  
	          
	        //caculate rank and sa    
	        int[] sa = new int[len];  
	        sa[0] = tB[0].isuffix;    
	          
	        int[] rank = new int[len+2]; //add 2 for sentinel     
	        rank[len]=1;rank[len+1] = 1;  
	        int r = 1; //rank starts with 1  
	        rank[tB[0].isuffix] = r;          
	        for(int i=1;i<len;i++){  
	            sa[i] = tB[i].isuffix;    
	              
	            boolean equalLast = true;  
	            for(int j=digitsTotalLen-digitsLen;j<digitsTotalLen;j++){  
	                if(tB[i].digits[j]!=tB[i-1].digits[j]){  
	                    equalLast = false;  
	                    break;  
	                }  
	            }  
	            if(!equalLast){  
	                r++;  
	            }  
	            rank[tB[i].isuffix] = r;      
	        }  
	                   
	        Suffix suffix = new Suffix(sa,rank);  
	        //judge if we are done  
	        if(r==len){  
	            suffix.done = true;  
	        }else{  
	            suffix.done = false;  
	        }  
	        return suffix;  
	          
	    }  
	      
	    private int[] orderSuffixes(Tuple[] tA,Tuple[] tB,int max,int digitsLen){         
	        int len = tA.length;          
	        radixSort(tA,tB,max,digitsLen);           
	        //caculate rank and sa    
	        int[] sa = new int[len];  
	        for(int i=0;i<len;i++){  
	            sa[i] = tB[i].isuffix;                
	        }  
	        return sa;         
	    }  
	      
	    //rank needs sentinel: len+2  
	    public Suffix reduce(int[] rank,int max){  
	        int len = rank.length - 2;  
	          
	        int n1 = (len+1)/3;  
	        int n2 = len/3;  
	        Tuple[] tA = new Tuple[n1+n2];  
	        Tuple[] tB = new Tuple[n1+n2];  
	          
	        for(int i=0,j=1;i<n1;i++,j+=3){  
	            int r1 =  rank[j];  
	            int r2 =  rank[j+1];  
	            int r3 =  rank[j+2];  
	            tA[i] = new Tuple(i,new int[]{r1,r2,r3});  
	        }  
	        for(int i=n1,j=2;i<n1+n2;i++,j+=3){  
	            int r1 =  rank[j];  
	            int r2 =  rank[j+1];  
	            int r3 =  rank[j+2];       
	            tA[i] = new Tuple(i,new int[]{r1,r2,r3});  
	        }  
	           
	        return rank(tA,tB,max,3);         
	    }  
	      
	      
	    public int[] skew(int[] rank,int max){  
	        int len = rank.length - 2;  
	          
	        //step 1: caculate sa12  
	        Suffix suffixT12 = reduce(rank,max);  
	           
	          
	        int[] sa12 = null;  
	        if(!suffixT12.done){  
	            int[] rankT12 = suffixT12.rank;  
	            int maxT12 = rankT12[suffixT12.sa[suffixT12.sa.length-1]];  
	            sa12 = skew(rankT12,maxT12);  
	            // debug for string: GACCCACCACC#  
	            //s12 = new Suffix();  
	            //s12.rank = new int[]{3,6,5,4,7,2,1,1,1};  
	            //s12.sa = new int[]{7,6,5,0,3,2,1,4};  
	            //s12.done =true;                         
	        }else{  
	            sa12 = suffixT12.sa;              
	        }  
	          
	        //index conversion for sa12  
	        int n1 = (len+1)/3;  
	        for(int j=0;j<sa12.length;j++){  
	            if(sa12[j]<n1){  
	                sa12[j] = 1 + 3*sa12[j];  
	            }else{  
	                sa12[j] = 2 + 3*(sa12[j]-n1);  
	            }                 
	        }  
	        //recaculate rank for sa12  
	        int[] rank12 = new int[len+2];  
	        rank12[len] = 1;rank12[len+1] = 1;  
	        for(int k=0;k<sa12.length;k++){  
	            rank12[sa12[k]] = k+1;  
	        }  
	           
	            
	          
	        //step 2: caculate sa0        
	        int n0=(len+2)/3;  
	        Tuple[] tA = new Tuple[n0];  
	        Tuple[] tB = new Tuple[n0];  
	        for(int i=0,j=0;i<n0;i++,j+=3){  
	            int r1 =  rank[j];  
	            int r2 =  rank12[j+1];   
	            tA[i] = new Tuple(i,new int[]{r1,r2});  
	        }  
	        int max12 = rank12[sa12[sa12.length-1]];          
	        int[] sa0 = orderSuffixes(tA,tB,max<max12?max12:max,2);  
	        //index conversion for sa0  
	        for(int j=0;j<n0;j++){  
	            sa0[j] = 3*sa0[j];                    
	        }          
	          
	        //step 3: merge sa12 and sa0  
	        int[] sa = new int[len];  
	        int i=0,j=0;  
	        int k=0;  
	        while(i<sa12.length && j<sa0.length){  
	            int p = sa12[i];  
	            int q = sa0[j];  
	            if(p%3==1){  
	                //case 1  
	                if(rank[p]<rank[q]){  
	                    sa[k++] = p;i++;  
	                }else if(rank[p]>rank[q]){  
	                    sa[k++] = q;j++;  
	                }else{  
	                    if(rank12[p+1]<rank12[q+1]){  
	                        sa[k++] = p;i++;  
	                    }else{  
	                        sa[k++] = q;j++;  
	                    }                     
	                }  
	            }else{  
	                //case 2  
	                if(rank[p]<rank[q]){  
	                    sa[k++] = p;i++;  
	                }else if(rank[p]>rank[q]){  
	                    sa[k++] = q;j++;  
	                }else{  
	                    if(rank[p+1]<rank[q+1]){  
	                        sa[k++] = p;i++;  
	                    }else if(rank[p+1]>rank[q+1]){  
	                        sa[k++] = q;j++;  
	                    }else{  
	                        if(rank12[p+2]<rank12[q+2]){  
	                            sa[k++] = p;i++;  
	                        }else{  
	                            sa[k++] = q;j++;  
	                        }             
	                    }  
	                }  
	            }             
	        }  
	        for(int m=i;m<sa12.length;m++){  
	            sa[k++] = sa12[m];  
	        }  
	        for(int m=j;m<sa0.length;m++){  
	            sa[k++] = sa0[m];  
	        }         
	          
	        return sa;        
	    }  
	    //Precondition: the last char in text must be less than other chars.  
	    public Suffix solve(String text){  
	        if(text == null)return null;  
	        int len = text.length();  
	        if(len == 0) return null;  
	          
	        char base = text.charAt(len-1); //the smallest char  
	        Tuple[] tA = new Tuple[len];  
	        Tuple[] tB = new Tuple[len]; //placeholder  
	        for(int i=0;i<len;i++){  
	            tA[i] = new Tuple(i,new int[]{0,text.charAt(i)-base});  
	        }  
	        Suffix suffix = rank(tA,tB,MAX_CHAR-base,1);  
	           
	        int max = suffix.rank[suffix.sa[len-1]];  
	        int[] sa  = skew(suffix.rank,max);  
	          
	        //caculate rank for result suffix array  
	        int[] r = new int[len];       
	        for(int k=0;k<sa.length;k++){  
	            r[sa[k]] = k+1;  
	        }  
	        return new Suffix(sa,r);  
	          
	    }  
	    public void report(Suffix suffix){  
	        int[] sa = suffix.sa;  
	        int[] rank = suffix.rank;  
	        int len = sa.length;  
	          
	        System.out.println("suffix array:");  
	        for(int i=0;i<len;i++){  
	            System.out.format(" %s", sa[i]);              
	        }  
	        System.out.println();  
	        System.out.println("rank array:");  
	        for(int i=0;i<len;i++){  
	            System.out.format(" %s", rank[i]);            
	        }         
	        System.out.println();  
	    }  
	    
	  
	}  


