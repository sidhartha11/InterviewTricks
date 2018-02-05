/**
 * 
 */
package com.georgecurington.functionalstudymod.mathstuff.dynamic.subsum;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 5, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
import java.util.*;
public class RecursiveSubsetsSummingToN {
    
    static List<List<Integer>> subsetSum(int[] a,int W){
        Arrays.sort(a);
        return subsetSumHelper(a,W,new ArrayList<>(),0);
    }
    
    static List<List<Integer>> subsetSumHelper(int[] a,int W,List<Integer> curr_list,int index){
        List<List<Integer>> result = new ArrayList<List<Integer>>();        
        if(W < 0 || index == a.length) return result;
        
        for(int i=index;i<a.length;++i){
            if(a[i] > W) return result;
            if(a[i] == W){
                List<Integer> new_list = new ArrayList<Integer>(curr_list);
                new_list.add(a[i]);
                result.add(new_list);
                return result; 
            }else{
                List<Integer> new_list = new ArrayList<Integer>(curr_list);
                new_list.add(a[i]);
                result.addAll(subsetSumHelper(a,W-a[i],new_list,i + 1));
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] a = {11,3,5,0,1};
        int W = 10000;
        System.out.println(subsetSum(a,W).toString());
    }
}