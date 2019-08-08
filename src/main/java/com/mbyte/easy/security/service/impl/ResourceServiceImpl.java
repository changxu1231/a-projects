package com.mbyte.easy.security.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.security.entity.SysResource;
import com.mbyte.easy.security.mapper.SysResourceMapper;
import com.mbyte.easy.security.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: easy
 * @description: 系统资源服务实现类
 * @author: 王震
 * @create: 2019-04-14 14:53
 **/
@Service
public class ResourceServiceImpl implements IResourceService{

    @Autowired
    private SysResourceMapper resourceMapper;

    /**
     * @Author 王震
     * @Description 根据系统用户名查询权限信息
     * @Date 15:34 2019/4/14
     * @Param [loginUserName]
     * @return java.util.List<com.mbyte.easy.security.entity.SysResource>
     **/
    @Override
    public List<SysResource> selectByUsername(String loginUserName) {
        return resourceMapper.selectByUsername(loginUserName);
    }

    /**
     * @Author 王震
     * @Description 查询权限信息
     * @Date 15:38 2019/4/14
     * @Param [resource]
     * @return java.util.List<com.mbyte.easy.security.entity.SysResource>
     **/
    @Override
    public List<SysResource> selectByResource(SysResource resource) {
        return resourceMapper.selectByResource(resource);
    }

    /**
     * @Author 王震
     * @Description 分页查询权限列表
     * @Date 15:39 2019/4/14
     * @Param [page, resource]
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.mbyte.easy.security.entity.SysResource>
     **/
    @Override
    public IPage<SysResource> selectBySysResourceForPage(Page<SysResource> page, SysResource resource) {
        return this.resourceMapper.selectBySysResourceForPage(page, resource);
    }

    /**
     * @Author 王震
     * @Description 添加权限
     * @Date 15:43 2019/4/14
     * @Param [resource]
     * @return int
     **/
    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public int insert(SysResource resource) {
        return this.resourceMapper.insert(resource);
    }

    /**
     * @Author 王震
     * @Description 根据主键查询权限信息
     * @Date 15:43 2019/4/14
     * @Param [id]
     * @return com.mbyte.easy.security.entity.SysResource
     **/
    @Override
    public SysResource selectByPrimaryKey(Long id) {
        return this.resourceMapper.selectByPrimaryKey(id);
    }

    /**
     * @Author 王震
     * @Description 根据主键更新权限信息
     * @Date 15:44 2019/4/14
     * @Param [resource]
     * @return int
     **/
    @Transactional(
            rollbackFor = {Exception.class}
    )
    @Override
    public int updateByPrimaryKeySelective(SysResource resource) {
        return this.resourceMapper.updateByPrimaryKeySelective(resource);
    }

    /**
     * @Author 王震
     * @Description 根据主键删除资源信息
     * @Date 15:44 2019/4/14
     * @Param [id]
     * @return int
     **/
    @Override
    public int deleteByPrimaryKey(long id) {
        return this.resourceMapper.deleteByPrimaryKey(id);
    }
    /**
     * @Author 王震
     * @Description 根据角色id查询权限信息
     * @Date 16:12 2019/4/14
     * @Param [id]
     * @return java.util.List<com.mbyte.easy.security.entity.SysResource>
     **/
    @Override
    public List<SysResource> selectResourceByRoleId(Long id) {
        return this.resourceMapper.selectResourceByRoleId(id);
    }


}
