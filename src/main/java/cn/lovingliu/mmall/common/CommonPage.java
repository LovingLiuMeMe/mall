package cn.lovingliu.mmall.common;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description: 封装pageHelp分页插件返回的参数
 * @Date：Created in 2019-10-23
 */
@Data
public class CommonPage<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> list;

    /**
     * @Desc 数据封装
     * @Author LovingLiu
    */
    public static <T> CommonPage<T> restPage(List<T> list) {
        CommonPage<T> result = new CommonPage<T>();
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        result.setTotalPage(pageInfo.getPages());
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setList(pageInfo.getList());
        return result;
    }
}
