package com.mbyte.easy.security.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.security.entity.SysRole;
import com.mbyte.easy.security.entity.SysRoleResources;
import com.mbyte.easy.security.mapper.SysRoleMapper;
import com.mbyte.easy.security.mapper.SysRoleResourcesMapper;
import com.mbyte.easy.security.mapper.SysUserRolesMapper;
import com.mbyte.easy.security.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: easy
 * @description: 角色服务实现类
 * @author: 王震
 * @create: 2019-04-14 14:51
 **/
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysRoleResourcesMapper roleResourceMapper;
    @Autowired
    private SysUserRolesMapper userRolesMapper;

    /**
     * @Author 王震
     * @Description 查询角色列表
     * @Date 14:57 2019/4/14
     * @Param [role]
     * @return java.util.List<com.mbyte.easy.security.entity.SysRole>
     **/
    @Override
    public List<SysRole> selectByRole(SysRole role) {
        return roleMapper.selectByRole(role);
    }
    /**
     * @Author 王震
     * @Description 根据用户id查询角色
     * @Date 15:20 2019/4/14
     * @Param [id]
     * @return java.util.List<com.mbyte.easy.security.entity.SysRole>
     **/
    @Override
    public List<SysRole> selectRolesByUserID(Long id) {
        return roleMapper.selectRolesByUserID(id);
    }
    /**
     * @Author 王震
     * @Description 根据角色名称，分页查询角色列表
     * @Date 15:50 2019/4/14
     * @Param [page, name]
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.mbyte.easy.security.entity.SysRole>
     **/
    @Override
    public IPage<SysRole> selectByRoleForPage(Page<SysRole> page, String name) {
        return this.roleMapper.selectByRoleForPage(page, name);
    }
    /**
     * @Author 王震
     * @Description 添加角色和绑定相应的权限
     * @Date 15:53 2019/4/14
     * @Param [role, roleResources]
     * @return int
     **/
    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public int addRoleAndResource(SysRole role, String roleResources) {
        SysRoleResources sysRoleResource = new SysRoleResources();
        if (role != null && role.getName() != null && !"".equals(role.getName())) {
            roleMapper.insert(role);
            role = roleMapper.selectByRolename(role.getName());
            if (!"".equals(roleResources) && roleResources != null) {
                // 权限资源字段处理
                String[] resourceIds = roleResources.split(",");
                for (String ids : resourceIds) {
                    Long id = Long.valueOf(ids);
                    sysRoleResource.setResourcesId(id);
                    sysRoleResource.setSysRoleId(role.getId());
                    roleResourceMapper.insert(sysRoleResource);
                }
            }
            return 1;
        }
        return 0;
    }

    /**
     * @Author 王震
     * @Description 根据主键查询用户角色信息
     * @Date 16:11 2019/4/14
     * @Param [id]
     * @return com.mbyte.easy.security.entity.SysRole
     **/
    @Override
    public SysRole selectByPrimaryKey(Long id) {
        return this.roleMapper.selectByPrimaryKey(id);
    }
    /**
     * @Author 王震
     * @Description 更新关联的角色和权限信息
     * @Date 16:15 2019/4/14
     * @Param [role, roleResources]
     * @return int
     **/
    @Override
    @Transactional(
            rollbackFor = {Exception.class}
    )
    public int updateRoleAndResource(SysRole role, String roleResources) {
        if (role != null) {
            SysRoleResources sysUserRoles = new SysRoleResources();
            roleMapper.updateByPrimaryKey(role);
            if (!"".equals(roleResources) && roleResources != null) {
                roleResourceMapper.deleteByRoleId(role.getId());
                // 权限资源字段处理
                String[] roleIds = roleResources.split(",");
                for (String ids : roleIds) {
                    Long id = Long.valueOf(ids);
                    sysUserRoles.setResourcesId(id);
                    sysUserRoles.setSysRoleId(role.getId());
                    roleResourceMapper.insert(sysUserRoles);
                }
            }
            return 1;
        }
        return 0;
    }
    /**
     * @Author 王震
     * @Description 根据角色id，删除角色
     * @Date 16:20 2019/4/14
     * @Param [id]
     * @return int
     **/
    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public int removeRole(Long id) {
        roleResourceMapper.deleteByRoleId(id);
        userRolesMapper.deleteUserRolesById(id);
        return roleMapper.deleteByPrimaryKey(id);
    }
}
