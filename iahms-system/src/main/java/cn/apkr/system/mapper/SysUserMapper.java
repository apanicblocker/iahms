package cn.apkr.system.mapper;

import cn.apkr.common.core.domain.entity.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysUserMapper {

    /**
     * 根据条件分页查询用户列表
     * @param user 用户信息
     * @return 用户信息集合
     */
    List<SysUser> selectUserList(SysUser user);

    /**
     * 通过用户名查询用户
     * @param username 用户名
     * @return 用户对象信息
     */
    @Select("select * from sys_user where username=#{username}")
    SysUser selectUserByUsername(String username);

    /**
     * 新增用户信息
     * @param user 用户信息
     * @return 结果
     */
    @Insert("insert into sys_user(username, password) values(#{username}, #{password})")
    int insertUser(SysUser user);

}
