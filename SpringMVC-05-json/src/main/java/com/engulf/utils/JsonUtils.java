package com.engulf.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

public class JsonUtils {

    public static String getJson(Object object){
        return getJson(object,"yyyy-MM-dd");
    }

    public static String getJson(Object object,String sdf){
        ObjectMapper mapper = new ObjectMapper();

        //时间格式化方式二:(ObjectMapper)
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sdf);
        mapper.setDateFormat(simpleDateFormat);
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
