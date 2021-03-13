package com.iot.service.impl;

import com.iot.dao.MenuMapper;
import com.iot.domain.Menu;
import com.iot.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired

    private MenuMapper menuMapper;


    @Override
    public List<Menu> findSubMenuListByPid(int pid) {

       return menuMapper.findSubMenuListByPid(pid);
    }

    @Override
    public List<Integer> findMenuByRoleId(int id) {
      return   menuMapper.findMenuByRoleId(id);
    }

    @Override
    public List<Menu> findAllMenu() {
      return   menuMapper.findAllMenu();
    }

    @Override
    public void saveMenu(Menu menu) {

        Date date = new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);

        menuMapper.saveMenu(menu);
    }

    @Override
    public void updateMenu(Menu menu) {

        menuMapper.updateMenu(menu);
    }

    @Override
    public Menu findMenuById(int id) {
      return  menuMapper.findMenuById(id);
    }


}
