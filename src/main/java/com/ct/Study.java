package com.ct;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by zeject on 2017/3/23.
 */
public class Study {

    public static void main(String[] args) {
        Map map = new ConcurrentHashMap();
        List list = new CopyOnWriteArrayList();
        map.put("a", 1);
        list.add(1);
        System.out.println(map);
        System.out.println(list);
        System.out.println(StringUtils.isEmpty("asd"));
    }

}
