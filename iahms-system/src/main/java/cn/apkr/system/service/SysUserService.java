package cn.apkr.system.service;

import cn.apkr.common.core.domain.entity.SysUser;

import java.util.List;

public interface SysUserService {

    List<SysUser> selectUserList(SysUser user);

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
