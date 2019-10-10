package cn.lovingliu.mmall.mbg.mapper;

import cn.lovingliu.mmall.mbg.model.pmsBrand;

public interface pmsBrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(pmsBrand record);

    int insertSelective(pmsBrand record);

    pmsBrand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(pmsBrand record);

    int updateByPrimaryKeyWithBLOBs(pmsBrand record);

    int updateByPrimaryKey(pmsBrand record);
}