package cn.lovingliu.mmall.controller;

import cn.lovingliu.mmall.common.ServerResponse;
import cn.lovingliu.mmall.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author：LovingLiu
 * @Description: 会员注册管理
 * @Date：Created in 2019-10-24
 */
@RestController
@Api(tags = "UmsMemberController", description = "会员登录注册管理")
@RequestMapping("sso")
public class UmsMemberController {
    @Autowired
    private UmsMemberService umsMemberService;

    @ApiOperation("获取验证码")
    @GetMapping("/getAuthCode")
    public ServerResponse getAuthCode(@RequestParam
                                      @ApiParam("电话号码")
                                      String telephone){
        return umsMemberService.generateAuthCode(telephone);
    }
    @ApiOperation("获取验证码")
    @GetMapping("/cheeckAuthCode")
    public ServerResponse cheeckAuthCode(@RequestParam
                                         @ApiParam("电话号码")
                                         String telephone,
                                         @RequestParam
                                         @ApiParam("验证码")
                                         String authCode){
        return umsMemberService.verifyAuthCode(telephone,authCode);
    }

}
