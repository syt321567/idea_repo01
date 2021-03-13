package com.iot.controller;

import com.iot.domain.ResponseResult;
import com.iot.domain.Role;
import com.iot.domain.RoleMenuVo;
import com.iot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;


    /*查找用户的角色的信息*/
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role) {

        List<Role> allRole = roleService.findAllRole(role);
        return new ResponseResult(true, 200, "响应成功", allRole);
    }

    /*修改或者添加角色的信息*/
    @RequestMapping("saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role) {
        if (role.getId() == null) {

            roleService.saveRole(role);
            return new ResponseResult(true, 200, "响应成功", null);

        } else {

            roleService.updateRole(role);

            return new ResponseResult(true, 200, "响应成功", null);
        }

    }

    /*根据id 查询角色的信息*/
    @RequestMapping("/findRoleById")
    public  ResponseResult findRoleById(@RequestParam int id){

        Role role = roleService.findRoleById(id);
        return new ResponseResult(true,200,"响应成功",role);

    }


    /*为角色分配菜单*/
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu (@RequestBody RoleMenuVo roleMenuVo){

        roleService.RoleContextMenu(roleMenuVo);

        return  new ResponseResult(true,200,"响应成功",null);

    }


    /*根据id 删除角色的信息*/
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(int id){

            roleService.deleteRole(id);

            return new ResponseResult(true,200,"响应成功",null);

    }


}
