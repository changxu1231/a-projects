package com.mbyte.easy.security.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.security.entity.SysResource;

import java.util.List;

/**
 * @Author 王震
 * @Description 系统资源服务接口
 * @Date 14:53 2019/4/14
 **/
public interface IResourceService {

    /**
     * @Author 王震
     * @Description 根据系统用户名查询用户权限信息
     * @Date 15:33 2019/4/14
     * @Param [loginUserName]
     * @return java.util.List<com.mbyte.easy.security.entity.SysResource>
     **/
    List<SysResource> selectByUsername(String loginUserName);

    /**
     * @Author 王震
     * @Description 查询权限资源列表
     * @Date 15:37 2019/4/14
     * @Param [resource]
     * @return java.util.List<com.mbyte.easy.security.entity.SysResource>
     **/
    List<SysResource> selectByResource(SysResource resource);
    /**
     * @Author 王震
     * @Description 分页查询权限列表
     * @Date 15:39 2019/4/14
     * @Param [page, resource]
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.mbyte.easy.security.entity.SysResource>
     **/
    IPage<SysResource> selectBySysResourceForPage(Page<SysResource> page, SysResource resource);

    /**
     * @Author 王震
     * @Description 添加权限
     * @Date 15:41 2019/4/14
     * @Param [resource]
     * @return int
     **/
    int insert(SysResource resource);

    /**
     * @Author 王震
     * @Description 根据主键查询权限信息
     * @Date 15:42 2019/4/14
     * @Param [id]
     * @return com.mbyte.easy.security.entity.SysResource
     **/
    SysResource selectByPrimaryKey(Long id);

    /**
     * @Author 王震
     * @Description 根据主键更新权限信息
     * @Date 15:42 2019/4/14
     * @Param [resource]
     * @return int
     **/
    int updateByPrimaryKeySelective(SysResource resource);

    /**
     * @Author 王震
     * @Description 根据主键删除权限
     * @Date 15:42 2019/4/14
     * @Param [id]
     * @return int
     **/
    int deleteByPrimaryKey(long id);
    /**
     * @Author 王震
     * @Description 更具角色id查询权限信息
     * @Date 16:11 2019/4/14
     * @Param [id]
     * @return java.util.List<com.mbyte.easy.security.entity.SysResource>
     **/
    List<SysResource> selectResourceByRoleId(Long id);
}
