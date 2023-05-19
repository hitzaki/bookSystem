package com.chen.work.mapper;



import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        BitSet bitSet = new BitSet(32);

        System.out.println(bitSet.get(1));
        System.out.println(bitSet.get(0));
        System.out.println(bitSet.get(5));
    }

    public int solution(int n, String s) {
        if(n<3)return 0;
        int count = 0, lt = 1, rt = n-2, lc = 1, rc = 1;
        while (s.charAt(lt)==s.charAt(lt-1)&&lt<n) lt++;
        while (s.charAt(rt)==s.charAt(rt+1)&&rt>-1) rt--;
        while (lt<rt){
            while (s.charAt(lt)==s.charAt(lt-1)&&lt<n) {lt++;   lc++;}
            while (s.charAt(rt)==s.charAt(rt+1)&&rt>-1) {rt--;   rc++; }
        }
        // 请添加具体实现
        return 0;
    }

    static class Bit{
        int size;
    }

}
