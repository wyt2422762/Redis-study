package com.wyt.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Test {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> hm = new HashMap<Integer,Integer>();  
        for (int i = 0; i < nums.length; i++) {
            int currentNumber = nums[i];
            int otherNumber = target - currentNumber;
            if (hm.containsKey(otherNumber)) {
                return new int[]{(int)hm.get(otherNumber), i};
            } else {
              hm.put(currentNumber, i);
            } 
        }
        return null;
    }

    //查询最长的没有重复字母的子串的长度
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++){
            for (int j = i + 1; j <= n; j++){
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
            }
        }
        return ans;
    }

    public static boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }
    
    //二分查找发
    public static int biSearch(int[] n, int a){
        int start = 0;
        int end = n.length - 1;
        while(start < end){
            int mid=(start+end)/2;
            if(n[mid] == a){
                return mid;
            }else if(n[mid] < a){
                start = mid + 1;
            }else if(n[mid] > a){
                start = mid - 1;
            }
        }
        return -1;
    }
    
    //冒泡排序
    public static void bubbleSort(int[] n){
        for(int i = 0;i < n.length - 1;i++){//外层循环控制排序趟数
            for(int j = 0;j < n.length - 1 - i;j++){
                if(n[j] > n[j+1]){
                    int temp = n[j+1];
                    n[j+1] = n[j];
                    n[j] = temp;
                }
            }
        }
    }
    
    //快速排序
    public static void quickSort(int[] n, int low, int high){
        if(high < low){
            return;
        }
        int i = low;
        int j = high;
        int key = n[i];
        while(i < j){
            
            //从后往前比较
            while(n[j] >= key && i < j){
                j--;
            }
            
            if (i < j) {
                int temp = n[j];
                n[j] = n[i];
                n[i] = temp;
                i++;
            }
            
            //从前往后比较
            while(n[i] <= key && i < j){
                i++;
            }
            
            if (i < j) {
                int temp = n[i];
                n[i] = n[j];
                n[j] = temp;
                j--;
            }
            
        }

        if (i > low) {
            quickSort(n, low, i - 1);
        }
        if (j < high) {
            quickSort(n, j + 1, high);
        }
    }
    
    public static void main(String[] args) {
        //System.out.println(lengthOfLongestSubstring("pwkepwkeqqpwkpwk"));
        
        int[] n = {4,7,8,3,1,6,2,5,9,11,10};
        
        //quickSort(n, 0, n.length-1);
        bubbleSort(n);
        
        for(int a:n){
            System.out.println(a);
        }
        
    }
}