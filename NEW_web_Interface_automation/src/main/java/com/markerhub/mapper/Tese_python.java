package com.markerhub.mapper;

import net.sf.json.JSONObject;

public class Tese_python {

    String string;
    Integer integer;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public Tese_python(String string, Integer integer) {
        this.string = string;
        this.integer = integer;
    }



    @Override
    public String toString() {
        return "{" +
                "string:'" + string + '\'' +
                ", integer:" + integer +
                '}';
    }

    public JSONObject json_python() {
        String json = "{" +
                "'string':'" + string + '\'' +
                ",'integer':" + integer +
                '}';
//        System.out.println(json);
        JSONObject jsonObject = new JSONObject(json);
//        System.out.println(json);

        return jsonObject;
    }

}
