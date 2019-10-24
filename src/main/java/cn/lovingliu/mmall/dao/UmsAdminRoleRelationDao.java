package cn.lovingliu.mmall.dao;

import cn.lovingliu.mmall.mbg.model.UmsPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author：LovingLiu
 * @Description: UmsAdminRoleRelation 自定义操作
 * @Date：Created in 2019-10-24
 */
public interface UmsAdminRoleRelationDao {
    /**
     * 获取用户所有权限(包括+-权限)
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);
}
