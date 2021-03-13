package com.iot.dao;

import com.iot.domain.Role;
import com.iot.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {


    /*查询角色列表*/
    public List<Role> findAllRole(Role role);

    /*添加角色的信息*/
    public void saveRole(Role role);

    /*根据id 查询角色的信息*/
    public Role findRoleById(int id);

    /*修改角色的信息*/
    public void updateRole(Role role);


    /*清空角色的菜单关联信息*/
    public void deleteRoleContextMenu(int id);


    /*关联角色的菜单的信息*/
    public void RoleContextMenu(Role_menu_relation role_menu_relation);


    /*删除角色根据id*/
    public void deleteRole(int id);

}
