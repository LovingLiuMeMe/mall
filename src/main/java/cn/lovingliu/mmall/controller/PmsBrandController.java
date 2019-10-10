package cn.lovingliu.mmall.controller;

import cn.lovingliu.mmall.common.ServerResponse;
import cn.lovingliu.mmall.mbg.model.PmsBrand;
import cn.lovingliu.mmall.service.PmsBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：LovingLiu
 * @Description: 品牌管理
 * @Date：Created in 2019-10-10
 */
@RestController
@RequestMapping("/brand/")
@Slf4j
public class PmsBrandController {
    @Autowired
    private PmsBrandService pmsBrandService;

    @PostMapping("create")
    public ServerResponse create(@RequestBody PmsBrand pmsBrand){
        int count = pmsBrandService.createBrand(pmsBrand);
        if(count == 1){
            log.info("【品牌创建成功】,{}",pmsBrand);
            return ServerResponse.createBySuccessMessage("创建成功");
        }
        log.error("【品牌创建失败】");
        return ServerResponse.createByErrorMessage("创建失败");
    }
}
