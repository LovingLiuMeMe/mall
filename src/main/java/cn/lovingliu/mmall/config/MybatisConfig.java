package cn.lovingliu.mmall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author：LovingLiu
 * @Description: mybatis的配置类
 * @Date：Created in 2019-10-23
 */
@Configuration
@MapperScan("cn.lovingliu.mmall.mbg.mapper")
public class MybatisConfig {
}
