package com.markerhub.controller;


import com.markerhub.common.lang.Result;
import com.markerhub.entity.MyFirstModel;
import com.markerhub.service.MyFirstModelService;
import com.markerhub.tool.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 前端控制器
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
    public Result find_all() {

        List<MyFirstModel> myFirstModels_list = myFirstModelService.find_all();
        return Result.succ(200, "操作成功", myFirstModels_list);

    }

    //新增一条用例
    @PostMapping("/saveModification")
    public Result saveModification(@RequestBody List<MyFirstModel> myFirstModel) {

        for (MyFirstModel fm : myFirstModel) {
            if (fm.getCaseId() == null || fm.getCaseId() == "") {
                return Result.succ(200, "操作成功", null);
            }
            String id = MathUtils.getPrimaryKey();
            fm.setId(id);
        }
        myFirstModelService.saveMoreModification(myFirstModel);
        return Result.succ(200, "操作成功", null);
    }

    //批量新增用例
    @PostMapping("/batchSaveModification")
    public Result batchSaveModification(@RequestBody MultipartFile file) {
        String name = file.getName();
        System.out.println(name);
//        String filename = file.getOriginalFilename();
//        myFirstModelService.batchSaveModification(file);
        return Result.succ(200, "操作成功", null);
    }

    //批量删除用例
    @PostMapping("/deleteList")
    public Result deleteList(@RequestBody List<String> caseid_list) {

        myFirstModelService.deleteList(caseid_list);
        return Result.succ(200, "操作成功", null);
    }

    //查询用例
    @PostMapping("/findCase")
    public Result findCase(@RequestBody MyFirstModel myFirstModel) {
        System.out.println(myFirstModel.toString());
        List<MyFirstModel> myFirstModels_list = myFirstModelService.findCase(myFirstModel);
        for (MyFirstModel m : myFirstModels_list) {
            System.out.println(m.toString());
        }
        return Result.succ(200, "操作成功", myFirstModels_list);
    }

    //修改用例
    @PostMapping("/updateCase")
    public Result updateCase(@RequestBody MyFirstModel myFirstModel) {

        myFirstModelService.updateCase(myFirstModel);
        return Result.succ(200, "操作成功", null);
    }


}
