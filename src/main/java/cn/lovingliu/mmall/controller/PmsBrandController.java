package cn.lovingliu.mmall.controller;

import cn.lovingliu.mmall.common.CommonPage;
import cn.lovingliu.mmall.common.ServerResponse;
import cn.lovingliu.mmall.mbg.model.PmsBrand;
import cn.lovingliu.mmall.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description: 品牌管理
 * @Date：Created in 2019-10-10
 */
@Api(tags = "PmsBrandController", description = "品牌管理")
@RestController
@RequestMapping("/brand/")
@Slf4j
public class PmsBrandController {
    @Autowired
    private PmsBrandService brandService;

    @ApiOperation("获取所有品牌列表")
    @GetMapping("listAll")
    public ServerResponse<List<PmsBrand>> getBrandList() {
        return ServerResponse.createBySuccess(brandService.listAllBrand());
    }

    @ApiOperation("添加品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ServerResponse createBrand(@RequestBody PmsBrand pmsBrand) {
        ServerResponse serverResponse;
        int count = brandService.createBrand(pmsBrand);
        if (count == 1) {
            serverResponse = ServerResponse.createBySuccess(pmsBrand);
            log.debug("createBrand success:{}", pmsBrand);
        } else {
            serverResponse = ServerResponse.createByErrorMessage("操作失败");
            log.error("createBrand failed:{}", pmsBrand);
        }
        return serverResponse;
    }

    @ApiOperation("更新指定id品牌信息")
    @PostMapping(value = "/update/{id}")
    public ServerResponse updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrand) {
        ServerResponse serverResponse;
        int count = brandService.updateBrand(id, pmsBrand);
        if (count == 1) {
            serverResponse = ServerResponse.createBySuccess(pmsBrand);
            log.debug("updateBrand success:{}", pmsBrand);
        } else {
            serverResponse = ServerResponse.createByErrorMessage("操作失败");
            log.error("updateBrand failed:{}", pmsBrand);
        }
        return serverResponse;
    }

    @ApiOperation("删除指定id的品牌")
    @GetMapping(value = "/delete/{id}")
    public ServerResponse deleteBrand(@PathVariable("id") Long id) {
        int count = brandService.deleteBrand(id);
        if (count == 1) {
            log.debug("deleteBrand success :id={}", id);
            return ServerResponse.createBySuccess(id);
        } else {
            log.error("deleteBrand failed :id={}", id);
            return ServerResponse.createByErrorMessage("操作失败");
        }
    }

    @ApiOperation("分页查询品牌列表")
    @GetMapping(value = "/list")
    public ServerResponse<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1")
                                                        @ApiParam("页码")
                                                        Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3")
                                                        @ApiParam("每页数量")
                                                        Integer pageSize) {
        List<PmsBrand> brandList = brandService.listBrand(pageNum, pageSize);
        return ServerResponse.createBySuccess(CommonPage.restPage(brandList));
    }

    @ApiOperation("获取指定id的品牌详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ServerResponse<PmsBrand> brand(@PathVariable("id") Long id) {
        return ServerResponse.createBySuccess(brandService.getBrand(id));
    }
}
