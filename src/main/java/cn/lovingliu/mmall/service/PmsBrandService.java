package cn.lovingliu.mmall.service;

import cn.lovingliu.mmall.mbg.model.PmsBrand;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-10-10
 */
public interface PmsBrandService {
    int createBrand(PmsBrand pmsBrand);
    List<PmsBrand> listAllBrand();
    int updateBrand(Long id,PmsBrand pmsBrand);
    int deleteBrand(Long id);
    List<PmsBrand> listBrand(Integer pageNum,Integer pageSize);
    PmsBrand getBrand(Long id);
}
