package com.markerhub.controller;


import com.markerhub.common.lang.Result;
import com.markerhub.entity.MyFirstModel;
import com.markerhub.service.MyFirstModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2020-11-10
 */
@RestController
@RequestMapping("/my-first-model")
public class MyFirstModelController {

    @Autowired
    MyFirstModelService myFirstModelService;

    //查询所有用例
    @PostMapping("/findAll")
    public Result find_all(){
        List<MyFirstModel> myFirstModels_list = myFirstModelService.find_all();

        return Result.succ(200,"操作成功",myFirstModels_list);
    }
    //新增一条用例
    @PostMapping("/saveModification")
    public Result saveModification(@RequestBody MyFirstModel myFirstModel){
        String uuid= UUID.randomUUID().toString().replace("-", "").toLowerCase();
        myFirstModel.setId(uuid);
        myFirstModelService.saveModification(myFirstModel);
        return Result.succ(200,"操作成功",null);
    }

    //批量删除用例
    @PostMapping("/deleteList")
    public Result deleteList(@RequestBody List<MyFirstModel> caseid_list){

        myFirstModelService.deleteList(caseid_list);
        return Result.succ(200,"操作成功",null);
    }

    //查询用例
    @PostMapping("/findCase")
    public Result findCase(@RequestBody MyFirstModel myFirstModel){

        List<MyFirstModel> myFirstModels_list = myFirstModelService.findCase(myFirstModel);
        return Result.succ(200,"操作成功",myFirstModels_list);
    }

    //修改用例
    @PostMapping("/updateCase")
    public Result updateCase(@RequestBody MyFirstModel myFirstModel){

        myFirstModelService.updateCase(myFirstModel);
        return Result.succ(200,"操作成功",null);
    }





}
