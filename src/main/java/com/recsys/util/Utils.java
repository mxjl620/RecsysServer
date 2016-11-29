package com.recsys.util;


import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Utils {
    public static Long generateRowKey() {
        return Long.MAX_VALUE - System.currentTimeMillis();
    }

    public static String Object2JsonStr(Object obj) {
        ObjectMapper mapperObj = new ObjectMapper();
        try {
            return mapperObj.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object Jsonstr2Object(String str, Class c) {
        ObjectMapper mapperObj = new ObjectMapper();
        try {
            return mapperObj.readValue(str, c);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getFileSize(Long size) {
        Long len = size / 1024;
        if (len == 0){
            return size.toString() + " Bytes";
        } else {
            return len.toString() + " KB";
        }
    }
}
