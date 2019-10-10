package cn.lovingliu.mmall.service.impl;

import cn.lovingliu.mmall.mbg.mapper.PmsBrandMapper;
import cn.lovingliu.mmall.mbg.model.PmsBrand;
import cn.lovingliu.mmall.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-10-10
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {

    @Autowired
    private PmsBrandMapper pmsBrandMapper;

    public int createBrand(PmsBrand pmsBrand){
        return pmsBrandMapper.insert(pmsBrand);
    }
}
