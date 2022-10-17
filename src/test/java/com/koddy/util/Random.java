package com.koddy.util;

import java.util.UUID;

public class Random {

    public static String generate(){
        return UUID.randomUUID().toString();
    }
    public static String generateItem(){
        return  "Item" + generate();
    }

}
