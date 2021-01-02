package com.markerhub.controller;


import com.markerhub.common.lang.Result;
import com.markerhub.entity.MyFirstModel;
import com.markerhub.service.MyFirstModelService;
import com.markerhub.service.impl.MyFirstModelServiceImpl;
import com.markerhub.tool.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
//        String name = file.getName();
//        System.out.println(name);
        String filename = file.getOriginalFilename();
        myFirstModelService.batchSaveModification(file);
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
//        for (MyFirstModel m : myFirstModels_list) {
//            System.out.println(m.toString());
//        }
        List<MyFirstModel> myFirstModelslist = MyFirstModelServiceImpl.listStort(myFirstModels_list);
        return Result.succ(200, "操作成功", myFirstModelslist);
    }

    //修改用例
    @PostMapping("/updateCase")
    public Result updateCase(@RequestBody MyFirstModel myFirstModel) {

        myFirstModelService.updateCase(myFirstModel);
        return Result.succ(200, "操作成功", null);
    }


    /**
     * 下载用户导入模板
     *
     * @param request
     * @return stats:importUsers
     */
    @PostMapping("/downloadTemplate")
    public void downloadTemp(HttpServletRequest request, HttpServletResponse response) {
        //定义变量
        String downPath = "";
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = null;
        byte[] buffer = new byte[1024];
        InputStream inputStream = null;
        BufferedInputStream bis = null;
        OutputStream os = null; //输出流
        try {
            //获取resource中的文件，并生成流信息
            File file = new File("src/main/java/com/markerhub/file_Excel/测试用例模板.xlsx");
//            resource = resourceLoader.getResource("controller/测试用例模板.xlsx");
//            inputStream = resource.getInputStream();
            inputStream = new FileInputStream(file);
            //设置返回文件信息
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode("userTemplate.xls", "UTF-8"));
            //将内容使用字节流写入输出流中
            os = response.getOutputStream();
            bis = new BufferedInputStream(inputStream);
            while (bis.read(buffer) != -1) {
                os.write(buffer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流信息
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping("/excuteCase")
    public Result excuteCase(@RequestBody List<String> caseid_list) {

        List<MyFirstModel> myFirstModelsList = myFirstModelService.excuteCase(caseid_list);

        return null;
    }


    //接口自动化
    @PostMapping("/batchExecutionCase")
    public Result batchExecutionCase(@RequestBody List<String> caseid_list) {

        List<MyFirstModel> myFirstModelList = myFirstModelService.batchExecutionCase(caseid_list);
        return Result.succ(200, "操作成功", myFirstModelList);
    }

}

