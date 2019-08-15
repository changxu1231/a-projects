package com.mbyte.easy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.entity.WeixinUser;
import com.mbyte.easy.admin.service.IWeixinUserService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.FileUtil;
import com.mbyte.easy.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
* <p>
* 前端控制器
* </p>
* @author 会写代码的怪叔叔
* @since 2019-03-11
*/
@Controller
@RequestMapping("/admin/weixinUser")
public class WeixinUserController extends BaseController  {

    private String prefix = "admin/weixinUser/";

    @Autowired
    private IWeixinUserService weixinUserService;
    private MultipartFile file;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param weixinUser
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, WeixinUser weixinUser) {
        Page<WeixinUser> page = new Page<WeixinUser>(pageNo, pageSize);
        QueryWrapper<WeixinUser> queryWrapper = new QueryWrapper<WeixinUser>();
        if(!ObjectUtils.isEmpty(weixinUser.getNickName())) {
            queryWrapper = queryWrapper.like("nickName",weixinUser.getNickName());
         }
        if(!ObjectUtils.isEmpty(weixinUser.getGender())) {
            queryWrapper = queryWrapper.like("gender",weixinUser.getGender());
         }
        if(!ObjectUtils.isEmpty(weixinUser.getLanguage())) {
            queryWrapper = queryWrapper.like("language",weixinUser.getLanguage());
         }
        if(!ObjectUtils.isEmpty(weixinUser.getCity())) {
            queryWrapper = queryWrapper.like("city",weixinUser.getCity());
         }
        if(!ObjectUtils.isEmpty(weixinUser.getProvince())) {
            queryWrapper = queryWrapper.like("province",weixinUser.getProvince());
         }
        if(!ObjectUtils.isEmpty(weixinUser.getAvatarUrl())) {
            queryWrapper = queryWrapper.like("avatarUrl",weixinUser.getAvatarUrl());
         }
        if(!ObjectUtils.isEmpty(weixinUser.getOpenId())) {
            queryWrapper = queryWrapper.like("openId",weixinUser.getOpenId());
         }
        if(!ObjectUtils.isEmpty(weixinUser.getCreatetime())) {
            queryWrapper = queryWrapper.like("createtime",weixinUser.getCreatetime());
         }
        if(!ObjectUtils.isEmpty(weixinUser.getUpdatetime())) {
            queryWrapper = queryWrapper.like("updatetime",weixinUser.getUpdatetime());
         }
        if(!ObjectUtils.isEmpty(weixinUser.getUserName())) {
            queryWrapper = queryWrapper.like("userName",weixinUser.getUserName());
         }
        if(!ObjectUtils.isEmpty(weixinUser.getPhone())) {
            queryWrapper = queryWrapper.like("phone",weixinUser.getPhone());
         }
        if(!ObjectUtils.isEmpty(weixinUser.getCurrentUnit())) {
            queryWrapper = queryWrapper.like("current_unit",weixinUser.getCurrentUnit());
         }
        IPage<WeixinUser> pageInfo = weixinUserService.page(page, queryWrapper);
        model.addAttribute("searchInfo", weixinUser);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"list";
    }

    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("addBefore")
    public String addBefore(){
        return prefix+"add";
    }
    /**
    * 添加
    * @param weixinUser
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(WeixinUser weixinUser){

        return toAjax(weixinUserService.save(weixinUser));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("weixinUser",weixinUserService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param weixinUser
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(WeixinUser weixinUser, MultipartFile avatarUrlFile){
        String avatarUrl = FileUtil.uploadFile(avatarUrlFile);
        weixinUser.setAvatarUrl(avatarUrl);
        return toAjax(weixinUserService.updateById(weixinUser));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(weixinUserService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(weixinUserService.removeByIds(ids));
    }

}

