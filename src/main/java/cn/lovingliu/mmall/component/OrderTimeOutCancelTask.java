package cn.lovingliu.mmall.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author：LovingLiu
 * @Description: 订单超时取消并解锁库存的定时器
 * @Date：Created in 2019-10-24
 */
@Component
@Slf4j
public class OrderTimeOutCancelTask {

    /**
     * cron表达式：Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
     * 每10分钟扫描一次，扫描设定超时时间之前下的订单，如果没支付则取消该订单
     */
    @Scheduled(cron = "0/10 0 * ? * ?")
    private void cancelTimeOutOrder() {
        // TODO: 2019/5/3 此处应调用取消订单的方法，具体查看mall项目源码
        System.out.println("取消订单，并根据sku编号释放锁定库存");
        log.info("取消订单，并根据sku编号释放锁定库存");
    }
}
