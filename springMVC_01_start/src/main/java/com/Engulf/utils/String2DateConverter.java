package com.Engulf.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class String2DateConverter implements Converter<String, Date> {
    /**
     *
     * @param source 传入进来的字符串
     * @return
     */
    @Override
    public Date convert(String source) {
        if(source == null){
            throw new RuntimeException("传入正确参数");
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //把字符串转为日期
            return df.parse(source);
        } catch (Exception e) {
            throw new RuntimeException("转换失败");
        }
    }
}
