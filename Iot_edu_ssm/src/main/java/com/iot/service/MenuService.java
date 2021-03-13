package com.iot.service;

import com.iot.dao.MenuMapper;
import com.iot.domain.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MenuService {


    /**
     * 查询全部的父子菜单信息 * */
    public List<Menu> findSubMenuListByPid(int pid);


    /*根据角色id查询关联的菜单id的信息*/
    public  List<Integer> findMenuByRoleId(int id);


    /*查询所有的菜单的信息*/
    public List<Menu> findAllMenu();


    /*添加菜单的信息*/
    public  void saveMenu(Menu menu);


    /*修改菜单的信息*/
    public   void  updateMenu(Menu menu);


    /*根据id 查询菜单的信息*/
    public  Menu findMenuById(int id);




}
