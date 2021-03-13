package com.iot.dao;

import com.iot.domain.*;

import java.util.List;

public interface UserMapper {

    /*分页条件查询*/
    public List<User> findAllUserByPage();

    /*修改用户的状态*/
    public void updateUserStatus(User user);


    /*用户登录*/
    public User login(User user);


    /*清空user和roles角色表*/
    public void deleteUserContextRole(int id);

    /*添加用户角色*/
    public void userContextRole(User_Role_relation user_role_relation);

    /*修改密码*/
    public void updateUserPassword(UserVO userVO);


    /*根据id查询给用户的角色的信息*/
    public List<Role> findUserRelationRoleById(int id);

    /**
     * 根据角色id,查询角色拥有的顶级菜单信息 *
     */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /**
     * 根据PID 查询子菜单信息 *
     */
    public List<Menu> findSubMenuByPid(int pid);

    /**
     * 获取用户拥有的资源权限信息 *
     */
    public List<Resource> findResourceByRoleId(List<Integer> ids);

}
