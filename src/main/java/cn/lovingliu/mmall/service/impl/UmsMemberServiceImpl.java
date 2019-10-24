package cn.lovingliu.mmall.service.impl;

import cn.lovingliu.mmall.common.ServerResponse;
import cn.lovingliu.mmall.service.RedisService;
import cn.lovingliu.mmall.service.UmsMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @Author：LovingLiu
 * @Description: 会员注册管理
 * @Date：Created in 2019-10-24
 */
@Service
@Slf4j
public class UmsMemberServiceImpl implements UmsMemberService {
    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTHCODE;

    @Value("${redis.key.expire.authCode}")
    private Integer REDIS_KEY_EXPIRE_AUTHCODE;


    @Override
    public ServerResponse generateAuthCode(String telephone) {

        if(redisService.getValue(String.format(REDIS_KEY_PREFIX_AUTHCODE,telephone)) == null){
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            for(int i=0 ;i<6 ;i++){
                sb.append(random.nextInt(10));
            }

            redisService.setValue(String.format(REDIS_KEY_PREFIX_AUTHCODE,telephone),sb.toString());
            redisService.setExpire(String.format(REDIS_KEY_PREFIX_AUTHCODE,telephone),REDIS_KEY_EXPIRE_AUTHCODE);
            log.debug("key={},value={}",String.format(REDIS_KEY_PREFIX_AUTHCODE,telephone),sb.toString());
            return ServerResponse.createBySuccess("获取验证码成功",sb.toString());
        }else{
            return ServerResponse.createByErrorMessage("获取验证码频繁,请在2分钟后重试");
        }
    }

    @Override
    public ServerResponse verifyAuthCode(String telephone, String autuCode) {
        String realAuthCode = redisService.getValue(String.format(REDIS_KEY_PREFIX_AUTHCODE,telephone));
        if(realAuthCode == null){
            return ServerResponse.createByErrorMessage("验证码已经过期");
        }
        if(realAuthCode.equals(autuCode)){
            log.debug("验证成功");
            return ServerResponse.createBySuccessMessage("验证成功");
        }else{
            log.debug("验证码校验失败");
            return ServerResponse.createByErrorMessage("验证码校验失败");
        }
    }
}
