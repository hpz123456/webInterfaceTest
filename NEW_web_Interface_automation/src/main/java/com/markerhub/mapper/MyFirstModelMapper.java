package com.markerhub.mapper;

import com.markerhub.entity.MyFirstModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2020-11-10
 */
public interface MyFirstModelMapper extends BaseMapper<MyFirstModel> {

    List<MyFirstModel> find_all();

    void saveModification(MyFirstModel myFirstModel_list);


    void deleteList(List<MyFirstModel> caseid_list);
}
