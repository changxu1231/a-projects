package com.mbyte.easy.security.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.security.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysUserMapper {
	int deleteByPrimaryKey(Long id);

	int insert(SysUser record);

	int insertSelective(SysUser record);

	SysUser selectByPrimaryKey(Long id);
	
	/**
	 * 根据用户名查找唯一用户
	 * @param userName
	 * @return
	 */
	SysUser selectByUsername(String userName);

	/**
	 *  根据条件查询用户信息
	 * 
	 * @param user
	 * @return
	 */
	List<SysUser> selectByUser(SysUser user);
	
	

	int updateByPrimaryKeySelective(SysUser record);

	int updateByPrimaryKey(SysUser record);

	/**
	 * 分页查询数据
	 * @param page
	 * @param username
	 * @return
	 */
	IPage<SysUser> selectByUserForPage(Page page, @Param("username") String username);
	@Select("<script> select id from sys_user where username = #{username}  </script>")
	Long selectLid(String username);

}