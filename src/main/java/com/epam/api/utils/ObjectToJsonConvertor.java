package com.epam.api.utils;

import com.epam.api.model.BaseObject;
import com.google.gson.Gson;


public class ObjectToJsonConvertor {

    public static String convertObjectToJson (BaseObject object){
        return new Gson().toJson(object);
    }
}
