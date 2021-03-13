package com.iot.service;

import com.iot.domain.Role;
import com.iot.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {



    /*查询角色列表*/
    public List<Role> findAllRole(Role role);

    /*添加角色的信息*/
    public void saveRole(Role role);

    /*根据id 查询角色的信息*/
    public Role findRoleById(int id);

    /*修改角色的信息*/
    public void updateRole(Role role);

    /*关联角色的菜单的信息*/
    public void  RoleContextMenu(RoleMenuVo roleMenuVo);

    /*删除角色的信息*/
    public void deleteRole(int id);
}
