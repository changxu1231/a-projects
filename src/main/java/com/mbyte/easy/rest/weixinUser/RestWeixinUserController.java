package com.mbyte.easy.rest.weixinUser;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.entity.WeixinUser;
import com.mbyte.easy.admin.service.IWeixinUserService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
* <p>
* 前端控制器
* </p>
* @author 会写代码的怪叔叔
* @since 2019-03-11
*/
@RestController
@RequestMapping("rest/weixinUser")
public class RestWeixinUserController extends BaseController  {

    @Autowired
    private IWeixinUserService weixinUserService;

    /**
    * 查询列表
    *
    * @param pageNo
    * @param pageSize
    * @param weixinUser
    * @return
    */
    @RequestMapping
    public AjaxResult index(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, WeixinUser weixinUser) {
        Page<WeixinUser> page = new Page<WeixinUser>(pageNo, pageSize);
        QueryWrapper<WeixinUser> queryWrapper = new QueryWrapper<WeixinUser>();

        if(weixinUser.getNickName() != null  && !"".equals(weixinUser.getNickName() + "")) {
            queryWrapper = queryWrapper.like("nickName",weixinUser.getNickName());
         }


        if(weixinUser.getGender() != null  && !"".equals(weixinUser.getGender() + "")) {
            queryWrapper = queryWrapper.like("gender",weixinUser.getGender());
         }


        if(weixinUser.getLanguage() != null  && !"".equals(weixinUser.getLanguage() + "")) {
            queryWrapper = queryWrapper.like("language",weixinUser.getLanguage());
         }


        if(weixinUser.getCity() != null  && !"".equals(weixinUser.getCity() + "")) {
            queryWrapper = queryWrapper.like("city",weixinUser.getCity());
         }


        if(weixinUser.getProvince() != null  && !"".equals(weixinUser.getProvince() + "")) {
            queryWrapper = queryWrapper.like("province",weixinUser.getProvince());
         }


        if(weixinUser.getAvatarUrl() != null  && !"".equals(weixinUser.getAvatarUrl() + "")) {
            queryWrapper = queryWrapper.like("avatarUrl",weixinUser.getAvatarUrl());
         }


        if(weixinUser.getOpenId() != null  && !"".equals(weixinUser.getOpenId() + "")) {
            queryWrapper = queryWrapper.like("openId",weixinUser.getOpenId());
         }


        if(weixinUser.getCreatetime() != null  && !"".equals(weixinUser.getCreatetime() + "")) {
            queryWrapper = queryWrapper.like("createtime",weixinUser.getCreatetime());
         }


        if(weixinUser.getUpdatetime() != null  && !"".equals(weixinUser.getUpdatetime() + "")) {
            queryWrapper = queryWrapper.like("updatetime",weixinUser.getUpdatetime());
         }


        if(weixinUser.getUserName() != null  && !"".equals(weixinUser.getUserName() + "")) {
            queryWrapper = queryWrapper.like("userName",weixinUser.getUserName());
         }


        if(weixinUser.getPhone() != null  && !"".equals(weixinUser.getPhone() + "")) {
            queryWrapper = queryWrapper.like("phone",weixinUser.getPhone());
         }


        if(weixinUser.getCurrentUnit() != null  && !"".equals(weixinUser.getCurrentUnit() + "")) {
            queryWrapper = queryWrapper.like("current_unit",weixinUser.getCurrentUnit());
         }

        IPage<WeixinUser> pageInfo = weixinUserService.page(page, queryWrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("searchInfo",  weixinUser);
        map.put("pageInfo", new PageInfo(pageInfo));

        return this.success(map);
    }


    /**
    * 添加
    * @param weixinUser
    * @return
    */
    @PostMapping("add")
    public AjaxResult add(WeixinUser weixinUser){
        return toAjax(weixinUserService.save(weixinUser));
    }

    /**
    * 添加
    * @param weixinUser
    * @return
    */
    @PostMapping("edit")
    public AjaxResult edit(WeixinUser weixinUser){
        return toAjax(weixinUserService.updateById(weixinUser));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(weixinUserService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(weixinUserService.removeByIds(ids));
    }

}

