package cn.lovingliu.mmall.service.impl;

import cn.lovingliu.mmall.mbg.mapper.PmsBrandMapper;
import cn.lovingliu.mmall.mbg.model.PmsBrand;
import cn.lovingliu.mmall.service.PmsBrandService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description:
 * @Date：Created in 2019-10-10
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {

    @Autowired
    private PmsBrandMapper brandMapper;

    public int createBrand(PmsBrand pmsBrand){
        return brandMapper.insert(pmsBrand);
    }
    public List<PmsBrand> listAllBrand(){
        return brandMapper.selectAll();
    }
    public int updateBrand(Long id,PmsBrand pmsBrand){
        pmsBrand.setId(id);
        return brandMapper.updateByPrimaryKeySelective(pmsBrand);
    }
    public int deleteBrand(Long id){
        return brandMapper.deleteByPrimaryKey(id);
    }
    public List<PmsBrand> listBrand(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<PmsBrand> pmsBrandList = brandMapper.selectAll();
        return pmsBrandList;
    }
    public PmsBrand getBrand(Long id){
        return brandMapper.selectByPrimaryKey(id);
    }
}
