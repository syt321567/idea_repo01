package com.iot.dao;

import com.iot.domain.Menu;

import java.util.List;

public interface MenuMapper {



    /**
     * 查询全部的父子菜单信息 * */
    public List<Menu> findSubMenuListByPid(int pid);

    /*根据角色id查询关联的菜单id的信息*/
    public  List<Integer> findMenuByRoleId(int id);

    /*查询所有菜单的信息*/
    public List<Menu> findAllMenu();

    /*保存菜单信息*/
    public void saveMenu(Menu menu);

    /*修改的菜单的信息*/
    public  void updateMenu(Menu menu);

    /*根据id查询菜单*/
    public  Menu findMenuById(int id );

}
