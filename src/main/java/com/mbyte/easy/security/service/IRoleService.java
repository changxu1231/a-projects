package com.mbyte.easy.security.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.security.entity.SysResource;
import com.mbyte.easy.security.entity.SysRole;

import java.util.List;

/**
 * @Author 王震
 * @Description 角色接口
 * @Date 14:51 2019/4/14
 **/
public interface IRoleService {
    /**
     * @Author 王震
     * @Description 查询角色信息
     * @Date 15:18 2019/4/14
     * @Param [role]
     * @return java.util.List<com.mbyte.easy.security.entity.SysRole>
     **/
    List<SysRole> selectByRole(SysRole role);
    /**
     * @Author 王震
     * @Description 根据用户id查询角色
     * @Date 15:18 2019/4/14
     * @Param [id]
     * @return java.util.List<com.mbyte.easy.security.entity.SysRole>
     **/
    List<SysRole> selectRolesByUserID(Long id);
    /**
     * @Author 王震
     * @Description 根据角色名称，分页查询角色列表
     * @Date 15:49 2019/4/14
     * @Param [page, name]
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.mbyte.easy.security.entity.SysRole>
     **/
    IPage<SysRole> selectByRoleForPage(Page<SysRole> page, String name);
    /**
     * @Author 王震
     * @Description 添加角色和绑定相应的权限
     * @Date 15:52 2019/4/14
     * @Param [role, roleResources]
     * @return int
     **/
    int addRoleAndResource(SysRole role, String roleResources);
    /**
     * @Author 王震
     * @Description 根据主键获取用户角色
     * @Date 16:00 2019/4/14
     * @Param [id]
     * @return com.mbyte.easy.security.entity.SysRole
     **/
    SysRole selectByPrimaryKey(Long id);
    /**
     * @Author 王震
     * @Description 更新角色信息和关联的权限信息
     * @Date 16:14 2019/4/14
     * @Param [role, roleResources]
     * @return int
     **/
    int updateRoleAndResource(SysRole role, String roleResources);
    /**
     * @Author 王震
     * @Description 根据角色id，删除角色
     * @Date 16:19 2019/4/14
     * @Param [id]
     * @return int
     **/
    int removeRole(Long id);

}
