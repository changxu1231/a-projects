package com.mbyte.easy.security.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.security.entity.SysRole;
import com.mbyte.easy.security.entity.SysUser;
import com.mbyte.easy.security.entity.SysUserRoles;
import com.mbyte.easy.security.service.IRoleService;
import com.mbyte.easy.security.service.IUserService;
import com.mbyte.easy.util.PageInfo;
import com.mbyte.easy.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 管理员控制类
 * 
 * @author 杨凯旋
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private String prefix = "security/";

	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;


	@ModelAttribute("roleList")
	public List<SysRole> resourceList() {
		List<SysRole> roleList = roleService.selectByRole(null);
		return roleList;
	}

	/**
	 * 进入管理员管理界面
	 * 
	 * @return
	 */
	@PreAuthorize("hasAuthority('/user')")
	@RequestMapping
	public String index(Model model,
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {
		SysUser user = new SysUser();
		if (name != null && !"".equals(name.trim())) {
			user.setUsername(name);
			model.addAttribute("name", name.trim());
		}

		Page<SysUser> page = new Page<SysUser>(pageNo, pageSize);
		IPage<SysUser> pageInfo = userService.selectByUserForPage(page, name);

		model.addAttribute("pageInfo", new PageInfo<SysUser>(pageInfo));
		return this.prefix + "admin-list";
	}

	/**
	 * 添加管理员
	 * 
	 * @param model
	 * @param user
	 * @return
	 */
	@PreAuthorize("hasAuthority('/user/add-user')")
	@RequestMapping(value = "/add-user")
	public String addRoleBefore(Model model, @ModelAttribute(value = "user") SysUser user) {
		return this.prefix + "admin-add";
	}

	@ResponseBody
	@RequestMapping(value = "/add-user", params = "save=true")
	public String addRole(Model model, @ModelAttribute(value = "user") SysUser user,
			@RequestParam(required = false) String userRoles) {
		SysUserRoles sysUserRoles = new SysUserRoles();
		SysUser dbUser = userService.selectByUsername(user.getUsername());
		// 用户名已存在
		if (dbUser != null) {
			return "2";
		}
		if (user != null && user.getUsername() != null && !"".equals(user.getUsername())) {
			user.setPassword(Utility.QuickPassword(user.getPassword()));
			user.setCreatetime(new Date());
			user.setUpdatetime(new Date());
			userService.insert(user);
			user = userService.selectByUsername(user.getUsername());
			if (!"".equals(userRoles) && userRoles != null) {
				// 角色字段处理
				String[] roleIds = userRoles.split(",");
				for (String ids : roleIds) {
					Long id = Long.valueOf(ids);
					sysUserRoles.setRolesId(id);
					sysUserRoles.setSysUserId(user.getId());
                    userService.insertuserRoles(sysUserRoles);
				}
			}
			return "1";
		}
		return "0";
	}

	/**
	 * 禁用/启用用户
	 * 
	 * @param model
	 * @param id
	 * @return
	 * @Description:
	 */
	@PreAuthorize("hasAuthority('/user/available-user')")
	@ResponseBody
	@RequestMapping(value = "/available-user/{id}")
	public String availableUser(Model model, @PathVariable("id") Long id) {
		SysUser user = userService.selectByPrimaryKey(id);
		if (user.getAvailable() != null) {
			user.setAvailable(!user.getAvailable());
			userService.updateByPrimaryKey(user);
			return "1";
		} else if (user.getAvailable() == null) {
			user.setAvailable(true);
			userService.updateByPrimaryKey(user);
			return "1";
		}
		return "0";
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@PreAuthorize("hasAuthority('/user/delete-user')")
	@ResponseBody
	@RequestMapping(value = "/delete-user/{id}")
	public Integer delet(Model model, @PathVariable("id") Long id) {
		try {
			userService.removeUser(id);
			return 1;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 * @Description:
	 */
	@PreAuthorize("hasAuthority('/user/delete-user')")
	@ResponseBody
	@RequestMapping(value = "/deleteAll-user", produces = "application/json", consumes = "application/json")
	public Integer deleteAll(@RequestBody Long[] ids) {
		try {
			for (long id : ids) {
                userService.removeUser(id);
			}
			return 1;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0;
	}

	/**
	 * 修改管理员信息
	 * 
	 * @param model
	 * @param user
	 * @return
	 */
	@PreAuthorize("hasAuthority('/user/edit-user')")
	@RequestMapping(value = "/edit-user/{id}")
	public String editUserBefore(Model model, @ModelAttribute(value = "user") SysUser user,
			@PathVariable("id") Long id) {
		// 根据ID查询用户
		user = userService.selectByPrimaryKey(id);
		// 查询对应用户的角色
		List<SysRole> userroles = roleService.selectRolesByUserID(id);
		user.setRoles(userroles);
		model.addAttribute("userroles", userroles);
		model.addAttribute("user", user);
		return this.prefix + "admin-editor";
	}

	/**
	 * @Author 王震
	 * @Description 更新用户和关联的角色信息
	 * @Date 15:28 2019/4/14
	 * @Param [model, user, userRoles]
	 * @return java.lang.String
	 **/
	@ResponseBody
	@RequestMapping(value = "/edit-user", params = "save=true")
	public String editRole(Model model, @ModelAttribute(value = "user") SysUser user,
			@RequestParam(required = false) String userRoles) {
	    return userService.editUserAndRole(user, userRoles) + "";
	}

	/**
	 * 修改管理员密码
	 * 
	 * @param model
	 * @param user
	 * @return
	 * @Description:
	 */
	@PreAuthorize("hasAuthority('/user/modify-password')")
	@RequestMapping(value = "/modify-password/{id}")
		public String modifyPasswordBefore(Model model, @ModelAttribute(value = "user") SysUser user,
				@PathVariable("id") Long id) {
			user = userService.selectByPrimaryKey(user.getId());
			model.addAttribute("user", user);
			return this.prefix + "admin-modify-password";
	}

	/**
	 * @Author 王震
	 * @Description 修改系统用户密码
	 * @Date 15:29 2019/4/14
	 * @Param [model, user, adminPassword, req]
	 * @return java.lang.String
	 **/
	@ResponseBody
	@RequestMapping(value = "/modify-password", params = "save=true")
	public String modifyPassword(Model model, @ModelAttribute(value = "user") SysUser user, String adminPassword,
			HttpServletRequest req) {
		SysUser dbUser = userService.selectByPrimaryKey(user.getId());

		String loginUserName = Utility.getCurrentUsername();
		SysUser loginUser = userService.selectByUsername(loginUserName);

		if (!Utility.checkPassword(adminPassword, loginUser.getPassword())) {
            // 管理员密码不正确
		    return "2";
		}
		if (dbUser != null) {
			dbUser.setPassword(Utility.QuickPassword(user.getPassword()));
			userService.updateByPrimaryKey(dbUser);
			
			return "1";
		}
		return "0";
	}
	@RequestMapping("/modify-passwords")
	public String modifyPasswordBefores(Model model, @ModelAttribute(value = "user") SysUser user) {

		String username = Utility.getCurrentUsername();
		System.err.println("111111" + user.getId());
		Long id = userService.selectLid(username);
		user = userService.selectByPrimaryKey(id);
		model.addAttribute("user", user);
		return this.prefix + "admin-modify-password";
	}
	@RequestMapping("/time")
	public String time() {

		return this.prefix + "admin-time";
	}
}
