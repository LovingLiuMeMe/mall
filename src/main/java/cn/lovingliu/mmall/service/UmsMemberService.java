package cn.lovingliu.mmall.service;

import cn.lovingliu.mmall.common.ServerResponse;

/**
 * @Author：LovingLiu
 * @Description: 会员管理Service
 * @Date：Created in 2019-10-24
 */
public interface UmsMemberService {
    /**
     * @Desc 生成验证码
     * @Author LovingLiu
    */
    ServerResponse generateAuthCode(String telephone);

    /**
     * @Desc 验证验证码是否匹配
     * @Author LovingLiu
     */
    ServerResponse verifyAuthCode(String telephone,String autuCode);

}
