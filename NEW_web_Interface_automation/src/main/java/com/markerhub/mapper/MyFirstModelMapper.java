package com.markerhub.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.markerhub.entity.MyFirstModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 关注公众号：MarkerHub
 * @since 2020-11-10
 */
public interface MyFirstModelMapper extends BaseMapper<MyFirstModel> {

    List<MyFirstModel> find_all();

    void saveModification(MyFirstModel myFirstModel_list);

    void deleteList(List<String> caseid_list);

    List<MyFirstModel> findCase(MyFirstModel myFirstModel);

    void updateCase(MyFirstModel myFirstModel);

    void saveMoreModification(@Param("myFirstModel_list") List<MyFirstModel> myFirstModel_list);

    List<MyFirstModel> findById(List<String> caseid_list);
}
