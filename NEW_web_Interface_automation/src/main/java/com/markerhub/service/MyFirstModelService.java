package com.markerhub.service;

import com.markerhub.entity.MyFirstModel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2020-11-10
 */
public interface MyFirstModelService extends IService<MyFirstModel> {

    List find_all();


    void saveModification(MyFirstModel myFirstModel_list);

    void deleteList(List<MyFirstModel> caseid_list);


    List<MyFirstModel> findCase(MyFirstModel myFirstModel);

    void updateCase(MyFirstModel myFirstModel);
}
