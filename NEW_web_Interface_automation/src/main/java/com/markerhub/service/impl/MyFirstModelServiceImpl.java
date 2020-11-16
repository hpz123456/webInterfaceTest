package com.markerhub.service.impl;

import com.markerhub.entity.MyFirstModel;
import com.markerhub.mapper.MyFirstModelMapper;
import com.markerhub.service.MyFirstModelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2020-11-10
 */
@Service
public class MyFirstModelServiceImpl extends ServiceImpl<MyFirstModelMapper, MyFirstModel> implements MyFirstModelService {

    @Autowired
    private MyFirstModelMapper myFirstModelMapper;

    public List<MyFirstModel> find_all(){

        List<MyFirstModel> myFirstModels_list = myFirstModelMapper.find_all();
        return myFirstModels_list;
    }


    public void saveModification(MyFirstModel myFirstModel_list){

        myFirstModelMapper.saveModification(myFirstModel_list);
    }

    public void deleteList(List<MyFirstModel> caseid_list){

        myFirstModelMapper.deleteList(caseid_list);
    }

}
