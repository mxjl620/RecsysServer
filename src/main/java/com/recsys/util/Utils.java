package com.recsys.util;


public class Utils {
    public static final Long generateRowKey(){
        return Long.MAX_VALUE - System.currentTimeMillis();
    }
}
