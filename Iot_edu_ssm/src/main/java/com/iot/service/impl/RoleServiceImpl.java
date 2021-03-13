package com.iot.service.impl;

import com.iot.dao.RoleMapper;
import com.iot.domain.Role;
import com.iot.domain.RoleMenuVo;
import com.iot.domain.Role_menu_relation;
import com.iot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;


    @Override
    public List<Role> findAllRole(Role role) {

        return roleMapper.findAllRole(role);
    }

    @Override
    public void saveRole(Role role) {
        Date date = new Date();


        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        role.setCreatedBy("索雨田");
        role.setUpdatedBy("索雨田");
        roleMapper.saveRole(role);

    }

    @Override
    public Role findRoleById(int id) {
      return   roleMapper.findRoleById(id);
    }

    @Override
    public void updateRole(Role role) {

        role.setUpdatedBy("索雨田");
        role.setUpdatedTime(new Date());

        roleMapper.updateRole(role);
    }

    @Override
    public void RoleContextMenu(RoleMenuVo roleMenuVo) {

        /*清空角色菜单映射的表*/
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        for (Integer mid : roleMenuVo.getMenuIdList()) {

            /*封装数据*/
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setCreatedTime(new Date());
            role_menu_relation.setUpdatedTime(new Date());
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");
            roleMapper.RoleContextMenu(role_menu_relation);

        }
    }

    @Override
    public void deleteRole(int id) {

        /*清空菜单和角色关联表的信息*/
        roleMapper.deleteRoleContextMenu(id);

        roleMapper.deleteRole(id);

    }
}
