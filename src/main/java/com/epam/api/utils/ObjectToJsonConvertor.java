package com.epam.api.utils;

import com.google.gson.Gson;


public class ObjectToJsonConvertor {

    public static String convertObjectToJson (Object object){
        return new Gson().toJson(object);
    }
}
