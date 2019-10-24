package cn.lovingliu.mmall.controller;

import cn.lovingliu.mmall.common.ServerResponse;
import cn.lovingliu.mmall.dto.UmsAdminLoginParamDto;
import cn.lovingliu.mmall.mbg.model.UmsAdmin;
import cn.lovingliu.mmall.mbg.model.UmsPermission;
import cn.lovingliu.mmall.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：LovingLiu
 * @Description: 后台用户管理
 * @Date：Created in 2019-10-24
 */
@RestController
@Api(tags = "UmsAdminController",description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {
    @Autowired
    private UmsAdminService adminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public ServerResponse register(@RequestBody UmsAdmin umsAdminParam){
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if(umsAdmin == null){
            return  ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccess("注册成功",umsAdmin);
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public ServerResponse login(@RequestBody @Valid UmsAdminLoginParamDto umsAdminLoginParam, BindingResult bindingResult){
       if(bindingResult.hasErrors()){
           return ServerResponse.createByErrorMessage(bindingResult.getFieldError().getDefaultMessage());
       }
       String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
       if(token == null){
           return ServerResponse.createByErrorMessage("用户不存在");
       }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return ServerResponse.createBySuccess(tokenMap);
    }

    @ApiOperation("获取用户权限")
    @GetMapping(value = "/permission/{adminId}")
    public ServerResponse<List<UmsPermission>> getPermissionList(@PathVariable Long adminId){
        List<UmsPermission> permissionList = adminService.getPermissionList(adminId);
        return ServerResponse.createBySuccess(permissionList);
    }


}
