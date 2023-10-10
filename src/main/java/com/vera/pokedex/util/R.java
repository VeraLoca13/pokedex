package com.vera.pokedex.util;

import java.io.File;
import java.io.InputStream;

public class R {

    public static InputStream getProperties(String name){
        System.out.println("config"+ File.separator+name);
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("config"+ File.separator+name);
    }
}
