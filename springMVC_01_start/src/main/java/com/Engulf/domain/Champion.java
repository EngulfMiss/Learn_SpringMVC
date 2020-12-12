package com.Engulf.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Champion implements Serializable {
    private String name;
    private String psw;
    private Double gold;

//    private User user;

    private List<User> list;
    private Map<String,User> map;

    public List<User> getList() {
        return list;
    }

    public void setList(List<User> list) {
        this.list = list;
    }

    public Map<String, User> getMap() {
        return map;
    }

    public void setMap(Map<String, User> map) {
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public Double getGold() {
        return gold;
    }

    public void setGold(Double gold) {
        this.gold = gold;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }


    @Override
    public String toString() {
        return "Champion{" +
                "name='" + name + '\'' +
                ", psw='" + psw + '\'' +
                ", gold=" + gold +
                ", list=" + list +
                ", map=" + map +
                '}';
    }
}
