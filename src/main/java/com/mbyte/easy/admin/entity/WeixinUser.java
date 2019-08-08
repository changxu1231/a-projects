package com.mbyte.easy.admin.entity;

import com.mbyte.easy.common.entity.BaseEntity;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 微信用户表
 * </p>
 *
 * @author 会写代码的怪叔叔
 * @since 2019-04-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class WeixinUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 昵称
     */
    @TableField("nickName")
    private String nickName;

    /**
     * 性别 1：男
     */
    private Integer gender;

    /**
     * 语言
     */
    private String language;

    /**
     * 城市
     */
    private String city;

    /**
     * 省份
     */
    private String province;

    /**
     * 头像地址#img
     */
    @TableField("avatarUrl")
    private String avatarUrl;

    /**
     * 唯一主键id
     */
    @TableField("openId")
    private String openId;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;

    /**
     * 更新时间
     */
    private LocalDateTime updatetime;

    /**
     * 用户姓名
     */
    @TableField("userName")
    private String userName;

    /**
     * 用户联系方式
     */
    private String phone;

    /**
     * 所在单位
     */
    private String currentUnit;


}
