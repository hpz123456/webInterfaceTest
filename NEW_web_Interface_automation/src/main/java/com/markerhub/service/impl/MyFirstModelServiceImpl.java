package com.markerhub.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markerhub.caseTestNG.Entry;
import com.markerhub.caseTestNG.testservice;
import com.markerhub.entity.MyFirstModel;
import com.markerhub.entity.caseModel;
import com.markerhub.mapper.MyFirstModelMapper;
import com.markerhub.service.MyFirstModelService;
import com.markerhub.tool.ExcelUtil;
import com.markerhub.tool.MathUtils;
import com.markerhub.tool.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2020-11-10
 */
@Service
public class MyFirstModelServiceImpl extends ServiceImpl<MyFirstModelMapper, MyFirstModel> implements MyFirstModelService {

    @Autowired
    private MyFirstModelMapper myFirstModelMapper;

    //查询所有用例
    @Override
    public List<MyFirstModel> find_all() {
        List<MyFirstModel> myFirstModels_list = myFirstModelMapper.find_all();
        return myFirstModels_list;
    }

    //新增一条用例
    @Override
    public void saveModification(MyFirstModel myFirstModel_list) {

        myFirstModelMapper.saveModification(myFirstModel_list);
    }

    //批量删除用例
    @Override
    public void deleteList(List<String> caseid_list) {

        myFirstModelMapper.deleteList(caseid_list);
    }

    //查询测试用例
    @Override
    public List<MyFirstModel> findCase(MyFirstModel myFirstModel) {

        List<MyFirstModel> myFirstModels_list = myFirstModelMapper.findCase(myFirstModel);
        return myFirstModels_list;
    }

    //修改测试用例
    @Override
    public void updateCase(MyFirstModel myFirstModel) {
        List<MyFirstModel> list = new ArrayList<MyFirstModel>();
        list.add(myFirstModel);
        list = formatting(list);
        for (MyFirstModel m : list) {
            myFirstModelMapper.updateCase(m);
        }
//        myFirstModelMapper.updateCase(formatting(myFirstModel));
    }

    //批量新增用例
    @Override
    public void saveMoreModification(List<MyFirstModel> myFirstModel_list) {
        List<MyFirstModel> myFirstModels = formatting(myFirstModel_list);
        deWeight(myFirstModels);
        if(myFirstModels.size() > 0){
            myFirstModelMapper.saveMoreModification(myFirstModels);
        }
//        myFirstModelMapper.saveMoreModification(this.formatting(myFirstModel_list));
    }


    //通过Excel批量新增用例
    @Override
    public void batchSaveModification(MultipartFile file) {
        String path = "src\\main\\java\\com\\markerhub\\file_Excel";
        String file_name = fileUpload(file);
        Integer sheet_number = sheet_list(path, file_name);
        for (int i = 0; i < sheet_number; i++) {
            List<MyFirstModel> myFirstModel_list = myFirstModels(path, file_name, i);
            if (myFirstModel_list.isEmpty()) {
                continue;
            }
            List<MyFirstModel> myFirstModels = formatting(myFirstModel_list);
            deWeight(myFirstModels);
            if(myFirstModels.size() > 0){
                myFirstModelMapper.saveMoreModification(myFirstModels);
            }
//            myFirstModelMapper.saveMoreModification(this.formatting(myFirstModel_list));
        }
        String fpath = path + "\\" + file_name;
        File f = new File(fpath);
        f.delete();
    }

    public List<MyFirstModel> formatting(List<MyFirstModel> myFirstModel_list) {
//        List<MyFirstModel> list = new ArrayList<MyFirstModel>();
        for (MyFirstModel myFirstModel : myFirstModel_list) {
            myFirstModel.setCaseId(StringUtils.replaceBlank(myFirstModel.getCaseId()));
            myFirstModel.setProjectName(StringUtils.replaceBlank(myFirstModel.getProjectName()));
            myFirstModel.setSubordinateModule(StringUtils.replaceBlank(myFirstModel.getSubordinateModule()));
            myFirstModel.setCaseName(StringUtils.replaceBlank(myFirstModel.getCaseName()));
            myFirstModel.setHeader(StringUtils.replaceBlank(myFirstModel.getHeader()));
            myFirstModel.setFormData(StringUtils.replaceBlank(myFirstModel.getFormData()));
            myFirstModel.setData(StringUtils.replaceBlank(myFirstModel.getData()));
            myFirstModel.setParams(StringUtils.replaceBlank(myFirstModel.getParams()));
            myFirstModel.setRequestMethod(StringUtils.replaceBlank(myFirstModel.getRequestMethod()));
            myFirstModel.setCookie(StringUtils.replaceBlank(myFirstModel.getCookie()));
            myFirstModel.setCaseDescription(StringUtils.replaceBlank(myFirstModel.getCaseDescription()));
            myFirstModel.setToken(StringUtils.replaceBlank(myFirstModel.getToken()));
            myFirstModel.setDependData(StringUtils.replaceBlank(myFirstModel.getDependData()));
            myFirstModel.setListAssert(StringUtils.replaceBlank(myFirstModel.getListAssert()));
            myFirstModel.setListVagueAssert(StringUtils.replaceBlank(myFirstModel.getListVagueAssert()));
            myFirstModel.setCommonAssert(StringUtils.replaceBlank(myFirstModel.getCommonAssert()));
            myFirstModel.setGetCookie(StringUtils.replaceBlank(myFirstModel.getGetCookie()));
            myFirstModel.setRemark(StringUtils.replaceBlank(myFirstModel.getRemark()));
            myFirstModel.setAssertResult(StringUtils.replaceBlank(myFirstModel.getAssertResult()));
            myFirstModel.setEditor(StringUtils.replaceBlank(myFirstModel.getEditor()));
            myFirstModel.setPerformer(StringUtils.replaceBlank(myFirstModel.getPerformer()));
        }
        return myFirstModel_list;
    }

    //去重
    public List<MyFirstModel> deWeight(List<MyFirstModel> myFirstModel_list){
        List<String> caseId_List = new ArrayList<String>();
        for (MyFirstModel myFirstModel:myFirstModel_list) {
            caseId_List.add(myFirstModel.getCaseId());
        }
        List<MyFirstModel> myFirstModelId_List1 = myFirstModelMapper.findByCaseId(caseId_List);
        if(myFirstModelId_List1.size()>0) {
            for (MyFirstModel myFirstModel1 : myFirstModelId_List1) {
//                for (MyFirstModel myFirstModel2 : myFirstModel_list) {
                    myFirstModel_list.removeIf(MyFirstModel -> MyFirstModel.getCaseId().equals(myFirstModel1.getCaseId()));
//                    if (myFirstModel1.getCaseId().equals(myFirstModel2.getCaseId())) {
//                        myFirstModel_list.remove(myFirstModel2);
//                    }
//                }
            }
        }
        return myFirstModel_list;
    }

    //获取文件sheet页
    public Integer sheet_list(String path, String file_name) {
        InputStream ins = null;
        XSSFWorkbook wb = null;
        Integer sheet_number = 0;
        File target = new File(path, file_name);
        try {
            ins = new FileInputStream(target);
            wb = new XSSFWorkbook(ins);
            ins.close();
            // 得到Excel工作表的sheet页数
            sheet_number = wb.getNumberOfSheets();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sheet_number;

    }

    //批量实例化
    public List<MyFirstModel> myFirstModels(String path, String file_name, Integer sheet_number) {
        ExcelUtil excelUtil = new ExcelUtil();
//        String path = "D:\\my_web_interface\\NEW_web_Interface_automation\\src\\main\\java\\com\\markerhub\\file_Excel";
        List<Map<String, String>> list = excelUtil.readExcel2007(path, file_name, 0, 0, sheet_number);
        List<MyFirstModel> myFirstModels_list = new ArrayList<MyFirstModel>();

        for (Map<String, String> model : list) {
            if (model.get("用例id") != null && model.get("用例id") != "") {
                MyFirstModel myFirstModel = new MyFirstModel();
                myFirstModel.setCaseId(model.get("用例id"));
                myFirstModel.setProjectName(model.get("项目名称"));
                myFirstModel.setSubordinateModule(model.get("所属模块"));
                myFirstModel.setCaseName(model.get("用例名称"));
                myFirstModel.setUrl(model.get("URL"));
                myFirstModel.setHeader(model.get("header请求头"));
                myFirstModel.setFormData(model.get("formData"));
                myFirstModel.setData(model.get("data请求体"));
                myFirstModel.setParams(model.get("params请求体"));
                myFirstModel.setRequestMethod(model.get("请求方式"));
                myFirstModel.setCookie(model.get("cookies"));
                myFirstModel.setCaseDescription(model.get("用例描述"));
                myFirstModel.setToken(model.get("token"));
                myFirstModel.setDependData(model.get("数据依赖"));
                myFirstModel.setListAssert(model.get("列表断言"));
                myFirstModel.setListVagueAssert(model.get("列表模糊断言"));
                myFirstModel.setCommonAssert(model.get("普通断言"));
                myFirstModel.setGetCookie(model.get("获取cookies"));
                myFirstModel.setRemark(model.get("备注"));
                myFirstModel.setEditor(model.get("编写人"));
                myFirstModel.setPerformer(model.get("执行人"));
                myFirstModel.setId(MathUtils.getPrimaryKey());
                myFirstModels_list.add(myFirstModel);
            }
        }
        return myFirstModels_list;
    }

    //存用例文件
    public String fileUpload(MultipartFile file) {
        if (file.isEmpty()) {
            return "false";
        }
        String path = "src/main/java/com/markerhub/file_Excel";
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        File dest = new File(new File(path).getAbsolutePath() + "/" + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest); // 保存文件
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

    @Override
    public List<MyFirstModel> excuteCase(List<String> caseid_list) {

        List<MyFirstModel> myFirstModels_list = myFirstModelMapper.findById(caseid_list);


        return myFirstModels_list;
    }


    @Override
    public List<MyFirstModel> batchExecutionCase(List<String> caseid_list) {
        //查找选中的case,并放入caseModel
        caseModel.testCase = myFirstModelMapper.findById(caseid_list);
        //初始化returnTestCase
        caseModel.returnTestCase = new ArrayList<MyFirstModel>();
        //用例数据重洗
        caseModel.listStort();
        //调用Entry开始testNG
        Entry.callTestNG();
        //调用结束后拿到返回结果
        List<MyFirstModel> returnMyFirstModelList = caseModel.returnTestCase;
        //执行结束后清除caseModel,objects,returnTestCase;
        caseModel.testCase = null;
        testservice.objects = null;

        return returnMyFirstModelList;
    }

    public static List<MyFirstModel> listStort(List<MyFirstModel> myFirstModels_list) {
        //接受通用用例的list
        List<MyFirstModel> myFirstModelsTY = new ArrayList<MyFirstModel>();
        //接受非通用用例的id
        List<String> caseNameid = new ArrayList<String>();
        //接收普通用例的list
        List<List<MyFirstModel>> PTCaseList = new ArrayList<List<MyFirstModel>>();
        //最后需要返回的list
        List<MyFirstModel> returnList = new ArrayList<MyFirstModel>();
        //吧名字分类
        for (MyFirstModel my : myFirstModels_list) {
            String[] st = my.getCaseId().split("-");
            //将TY用例筛选出来
            if (st[0].equals("TY")) {
                myFirstModelsTY.add(my);
            } else {
                //将普通用例筛选出来
                caseNameid.add(st[0]);
            }
        }
        //去重
        List<String> caseNameIdDe = caseModel.deWeigjt(caseNameid);
        //将用例名相同的放在一起
        for (String st : caseNameIdDe) {
            List<MyFirstModel> myf = new ArrayList<MyFirstModel>();
            for (MyFirstModel my : myFirstModels_list) {
                if (st.equals(my.getCaseId().split("-")[0])) {
                    myf.add(my);
                }
            }
            PTCaseList.add(myf);
        }
        //清空testCase
//        testCase.clear();
        //将TY排序，并挨个放入testCase
        List<MyFirstModel> TY = caseModel.sourtListMyFirstModelTY(myFirstModelsTY);
        for (MyFirstModel my : TY) {
            returnList.add(my);
        }
        //将PT排序，并挨个放入testCase
        for (List<MyFirstModel> m : PTCaseList) {
            List<MyFirstModel> PT = caseModel.sourtListMyFirstModelPT(m);
            for (MyFirstModel my : PT) {
                returnList.add(my);
            }
        }
        return returnList;
    }



}
