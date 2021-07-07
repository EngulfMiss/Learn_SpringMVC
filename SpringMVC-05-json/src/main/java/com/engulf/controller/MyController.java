package com.engulf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.engulf.pojo.Champion;
import com.engulf.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.management.ObjectName;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
//@RestController   后面的方法都只返回字符串不走视图解析器
public class MyController {
    //Fastjson的使用

    /*
    * JSON字符串 转 Java对象 --> JSON.parseObject(str,Object.class)
    * java对象 转 JSON对象 --> (JSONObject)JSON.toJSON(object)
    * java对象 转 JSON字符串 --> JSON.toJSONString(object)
    * JSON对象 转 java对象 --> JSON.toJavaObject(jsonObject,Object.class)
    *
    * */

    @ResponseBody
    @RequestMapping("/j5")
    public String json5(){
        List<Champion> champions = new ArrayList<Champion>();

        Champion champion = new Champion(1,"千珏",1500);
        Champion champion2 = new Champion(2,"纳尔",10);
        Champion champion3 = new Champion(3,"妮蔻",15);
        Champion champion4 = new Champion(1,"莉莉娅",150);

        champions.add(champion);
        champions.add(champion2);
        champions.add(champion3);
        champions.add(champion4);

        JSONObject json = (JSONObject) JSON.toJSON(champion);

        String s = JSON.toJSONString(champions);
        return s;
    }


    @ResponseBody  //有ResponseBody注解就不会走视图解析器，会直接返回一个字符串
    @RequestMapping(value = "/j4")
    public String json4(){
        ObjectMapper mapper = new ObjectMapper();
        Date date = new Date();

        //使用自定义工具类
        return JsonUtils.getJson(date,"yyyy-MM-dd");
    }


    @ResponseBody  //有ResponseBody注解就不会走视图解析器，会直接返回一个字符串
    @RequestMapping(value = "/j3")
    public String json3() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Date date = new Date();

        //时间格式化方式二:(ObjectMapper)
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        mapper.setDateFormat(sdf);
        return mapper.writeValueAsString(sdf.format(date));

        // ObjectMapper,时间解析后的默认格式是Timestamp时间戳
        // 时间格式化方式一:(SimpleDateFormat)
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return mapper.writeValueAsString(sdf.format(date));*/
    }

    @ResponseBody  //有ResponseBody注解就不会走视图解析器，会直接返回一个字符串
    @RequestMapping(value = "/j2")
    public String json2() throws JsonProcessingException {

        //jackson   ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        List<Champion> champions = new ArrayList<Champion>();

        Champion champion = new Champion(1,"千珏",1500);
        Champion champion2 = new Champion(2,"纳尔",10);
        Champion champion3 = new Champion(3,"妮蔻",15);
        Champion champion4 = new Champion(1,"莉莉娅",150);

        champions.add(champion);
        champions.add(champion2);
        champions.add(champion3);
        champions.add(champion4);

        String s = mapper.writeValueAsString(champions);

        return s;
    }

    @ResponseBody
    @RequestMapping("/j1"/*,produces = "application/json;charset=utf-8"*/)
    public String json1() throws JsonProcessingException {

        //jackson   ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        List<Champion> champions = new ArrayList<Champion>();

        Champion champion = new Champion(1,"千珏",1500);


        String s = mapper.writeValueAsString(champion);
        //toString返回结果 ：
        // Champion(id=1, name=kindred, age=1500)
        //jackson的ObjectMapper的writeValueAsString方法的结果 ：
        // {"id":1,"name":"kindred","age":1500}
        return s;
    }
}
