package cn.lovingliu.mmall.service;

/**
 * @Author：LovingLiu
 * @Description: redis 操作的接口,对象和数组都会以json形式进行存储
 * @Date：Created in 2019-10-24
 */
public interface RedisService {
    /**
     * 存储数据
     */
    void setValue(String key, String value);

    /**
     * 获取数据
     */
    String getValue(String key);

    /**
     * 设置超期时间
     */
    boolean setExpire(String key, long expire);

    /**
     * 删除数据
     */
    void remove(String key);

    /**
     * 自增操作
     * @param delta 自增步长
     */
    Long increment(String key, long delta);
}
