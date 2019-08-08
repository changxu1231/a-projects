package com.mbyte.easy.security.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.security.entity.SysUser;
import com.mbyte.easy.security.entity.SysUserRoles;
import com.mbyte.easy.security.mapper.SysUserMapper;
import com.mbyte.easy.security.mapper.SysUserRolesMapper;
import com.mbyte.easy.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


/**
 * @program: easy
 * @description: 管理员用户服务实现类
 * @author: 王震
 * @create: 2019-04-14 10:54
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysUserRolesMapper userRolesMapper;

    /**
     * @Author 王震
     * @Description 分页查询系统用户
     * @Date 14:59 2019/4/14
     * @Param [page, name]
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.mbyte.easy.security.entity.SysUser>
     **/
    @Override
    public IPage<SysUser> selectByUserForPage(Page<SysUser> page, String name) {
        return userMapper.selectByUserForPage(page, name);
    }

    /**
     * @Author 王震
     * @Description 根据用户名查询用户信息
     * @Date 15:01 2019/4/14
     * @Param [username]
     * @return com.mbyte.easy.security.entity.SysUser
     **/
    @Override
    public SysUser selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    /**
     * @Author 王震
     * @Description 插入用户权限关联信息
     * @Date 15:03 2019/4/14
     * @Param [sysUserRoles]
     * @return int
     **/
    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public int insertuserRoles(SysUserRoles sysUserRoles) {
        return userRolesMapper.insert(sysUserRoles);
    }


    /**
     * @Author 王震
     * @Description 查询系统用户信息
     * @Date 15:06 2019/4/14
     * @Param [user]
     * @return int
     **/
    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public int insert(SysUser user) {
        return userMapper.insert(user);
    }

    /**
     * @Author 王震
     * @Description 根据主键查询系统用户信息
     * @Date 15:08 2019/4/14
     * @Param [id]
     * @return com.mbyte.easy.security.entity.SysUser
     **/
    @Override
    public SysUser selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    /**
     * @Author 王震
     * @Description 根据主键更新系统用户信息
     * @Date 15:10 2019/4/14
     * @Param [user]
     * @return int
     **/
    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public int updateByPrimaryKey(SysUser user) {
        return userMapper.updateByPrimaryKey(user);
    }


    /**
     * @Author 王震
     * @Description 删除系统用户
     * @Date 15:15 2019/4/14
     * @Param [roleId]
     * @return int
     **/
    @Override
    public int removeUser(Long id) {
        //先删除用户和角色的关联信息
        userRolesMapper.deleteByUserRoleId(id);
        return userMapper.deleteByPrimaryKey(id);
    }

    /**
     * @Author 王震
     * @Description 更新用户和关联的角色信息
     * @Date 15:26 2019/4/14
     * @Param [user, userRoles]
     * @return int
     **/
    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public int editUserAndRole(SysUser user, String userRoles) {
        if (user != null) {
            SysUserRoles sysUserRoles = new SysUserRoles();
            SysUser dbUser = userMapper.selectByPrimaryKey(user.getId());
            user.setPassword(dbUser.getPassword());
            user.setCreatetime(dbUser.getCreatetime());
            user.setUpdatetime(new Date());
            userMapper.updateByPrimaryKey(user);
            if (!"".equals(userRoles) && userRoles != null) {
                userRolesMapper.deleteByUserRoleId(user.getId());
                // 角色字段处理
                String[] roleIds = userRoles.split(",");
                for (String ids : roleIds) {
                    Long id = Long.valueOf(ids);
                    sysUserRoles.setRolesId(id);
                    sysUserRoles.setSysUserId(user.getId());
                    userRolesMapper.insert(sysUserRoles);
                }
            }
            return 1;
        }
        return 0;
    }
    @Override
    public  Long selectLid(String username){
        return this.userMapper.selectLid(username);
    }

}
