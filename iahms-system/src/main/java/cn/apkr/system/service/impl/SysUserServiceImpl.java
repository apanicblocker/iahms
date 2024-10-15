package cn.apkr.system.service.impl;

import cn.apkr.common.core.domain.entity.SysUser;
import cn.apkr.system.mapper.SysUserMapper;
import cn.apkr.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;

    // Test
    @Override
    public List<SysUser> selectUserList(SysUser user) {
        return userMapper.selectUserList(null);
    }

    @Override
    public boolean checkUsernameUnique(SysUser user) {
        return false;
    }

    @Override
    public boolean registerUser(SysUser user) {
        return false;
    }
}
