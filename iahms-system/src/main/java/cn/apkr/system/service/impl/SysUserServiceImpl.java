package cn.apkr.system.service.impl;

import cn.apkr.common.core.domain.entity.SysUser;
import cn.apkr.system.mapper.SysUserMapper;
import cn.apkr.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    @Override
    public List<SysUser> selectUserList(SysUser user) {
        return userMapper.selectUserList(user);
    }

    @Override
    public boolean checkUsernameUnique(SysUser user) {
        return Objects.isNull(userMapper.selectUserByUsername(user.getUsername()));
    }

    @Override
    public boolean registerUser(SysUser user) {
        return userMapper.insertUser(user) > 0;
    }

    @Override
    public SysUser selectUserByUserName(String username) {
        return userMapper.selectUserByUsername(username);
    }
}
