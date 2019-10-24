package cn.lovingliu.mmall.service.impl;

import cn.lovingliu.mmall.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author：LovingLiu
 * @Description: Redis相关处理
 * @Date：Created in 2019-10-24
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void setValue(String key, String value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public String getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean setExpire(String key, long expire) {
        return redisTemplate.expire(key,expire, TimeUnit.SECONDS);
    }

    @Override
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    /**
     * @Desc 对key进行递增操作 递增因子:delta
     * @Author LovingLiu
    */
    @Override
    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key,delta);
    }
}
