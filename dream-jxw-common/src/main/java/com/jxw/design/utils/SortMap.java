package com.jxw.design.utils;

import java.util.*;
import java.util.Map.Entry;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/7 15:38
 * @Description 最小堆求top n
 */
public class SortMap {
    public static Map<Integer, Integer> sortMapByValue(int[] data) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : data) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        List<Map.Entry<Integer, Integer>> mapList = new ArrayList<Map.Entry<Integer, Integer>>(
                map.entrySet());
        Collections.sort(mapList, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Entry<Integer, Integer> o1,
                               Entry<Integer, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        Map<Integer, Integer> result = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> entry : mapList) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
