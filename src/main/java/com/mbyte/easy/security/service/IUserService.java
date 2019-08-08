package com.mbyte.easy.security.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.security.entity.SysUser;
import com.mbyte.easy.security.entity.SysUserRoles;

public interface IUserService {
    /**
     * @Author 王震
     * @Description 分页查询系统用户信息
     * @Date 14:58 2019/4/14
     * @Param [page, name]
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.mbyte.easy.security.entity.SysUser>
     **/
    IPage<SysUser> selectByUserForPage(Page<SysUser> page, String name);

    /**
     * @Author 王震
     * @Description 根据用户名查询用户信息
     * @Date 15:00 2019/4/14
     * @Param [username]
     * @return com.mbyte.easy.security.entity.SysUser
     **/
    SysUser selectByUsername(String username);

    /**
     * @Author 王震
     * @Description 插入用户权限关联信息
     * @Date 15:03 2019/4/14
     * @Param [sysUserRoles]
     * @return int
     **/
    int insertuserRoles(SysUserRoles sysUserRoles);
    /**
     * @Author 王震
     * @Description 保存系统用户信息
     * @Date 15:05 2019/4/14
     * @Param [user]
     * @return int
     **/
    int insert(SysUser user);

    /**
     * @Author 王震
     * @Description 根据主键查询用户信息
     * @Date 15:07 2019/4/14
     * @Param [id]
     * @return com.mbyte.easy.security.entity.SysUser
     **/
    SysUser selectByPrimaryKey(Long id);

    /**
     * @Author 王震
     * @Description 根据主键更新系统用户信息
     * @Date 15:09 2019/4/14
     * @Param [user]
     * @return int
     **/
    int updateByPrimaryKey(SysUser user);

    /**
     * @Author 王震
     * @Description 删除系统用户
     * @Date 15:14 2019/4/14
     * @Param [id]
     * @return int
     **/
    int removeUser(Long id);

    /**
     * @Author 王震
     * @Description 更新用户和关联的角色信息
     * @Date 15:26 2019/4/14
     * @Param [user, userRoles]
     * @return int
     **/
    int editUserAndRole(SysUser user, String userRoles);
    //查询id
    Long selectLid(String username);

}
