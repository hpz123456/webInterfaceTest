package com.markerhub.request_interface;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/msgapi")
public class UserController {

    @ResponseBody
    @RequestMapping(value = "/bothway", method = {RequestMethod.POST})
    public String bothway(@RequestBody Map<String, Object> map) throws IOException, ParseException {
        String uname = (String) map.get("uname");//用户名
        String token = (String) map.get("token");//用户密码
        String showcaller = (String) map.get("showcaller");//显示号码（接入号）
        String from = (String) map.get("from");//主叫
        String to = (String) map.get("to");//被叫
        Map paramMap = new HashMap();
        paramMap.put("uname", uname);
        paramMap.put("token", token);
        paramMap.put("showcaller", showcaller);
        paramMap.put("to", to);
        paramMap.put("from", from);
        String url = "http://zxjt.weiyingjia.org/msgapi/bothway";
        String result = requestPost.doPost(url, paramMap, "utf-8");
        System.out.print(result);
        return result;
    }

}