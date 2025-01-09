package cn.apkr.system.service;

import cn.apkr.common.core.domain.entity.SysUser;

import java.util.List;

public interface ISysUserService {

    /**
     * 查询用户信息列表
     * @param user 用户信息
     * @return 用户信息列表
     */
    List<SysUser> selectUserList(SysUser user);

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    SysUser selectUserByUserName(String username);

    /**
     * 更新用户信息
     * @param user 用户信息
     * @return 结果
     */
    int updateUserProfile(SysUser user);

    /**
     * 检查用户名唯一
     * @param user 用户信息
     * @return 结果
     */
    boolean checkUsernameUnique(SysUser user);

    /**
     * 注册用户
     * @param user 用户信息
     * @return 结果
     */
    boolean registerUser(SysUser user);
}
