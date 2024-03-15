package com.yoyo.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理blog中tags和comment属性
 * @author Chester
 */
public class TagUtil {
    public static List<Integer>  stringTolist(String s){
        List<Integer> integers=new ArrayList<>();
        String[] split = s.split(",");
        for (String s1:split){
            if (!s1.equals("")){
                integers.add(Integer.parseInt(s1));
            }
        }
        return integers;
    }
}
