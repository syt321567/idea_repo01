package com.iot.controller;

import com.iot.domain.Menu;
import com.iot.domain.ResponseResult;
import com.iot.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    /*查询所有的菜单*/
    @RequestMapping("findSubMenuListByPid")
    public ResponseResult findSubMenuListByPid() {

        //-1 表示查询所有菜单数据
        List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);
        return new ResponseResult(true, 200, "响应成功", subMenuListByPid);

    }


    /*根据角色id 查询菜单的id*/
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(@RequestParam int roleId) {

        List<Integer> menu = menuService.findMenuByRoleId(roleId);

        return new ResponseResult(true, 200, "响应成功", menu);

    }


    /*查询所有的菜单*/
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {

        List<Menu> allMenu = menuService.findAllMenu();

        return new ResponseResult(true, 200, "响应成功", allMenu);
    }

    //菜单信息的回显  根据id查询菜单的信息
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuById(@RequestParam int id) {

        /*添加信息的回显*/
        if (id == -1) {
            //等于-1为添加的操作  回显的时候不需要添加menu的信息
            //添加操作 回显不需要查询 menu信息
            List<Menu> menuList = menuService.findSubMenuListByPid(-1);

            //封装数据
            Map<String, Object> map = new HashMap<>();
            //添加的数据的时候menuInfo的数据不需要加载出来  需要加载出来的是一级的菜单
            map.put("menuInfo", null);
            map.put("parentMenuList", menuList);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
            return result;
        } else {

            //修改信息的回显

            Menu menu = menuService.findMenuById(id);

            List<Menu> subMenuList = menuService.findSubMenuListByPid(-1);

            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo", menu);
            map.put("parentMenuList", subMenuList);
            return new ResponseResult(true, 200, "响应成功", map);

        }
    }

    /*保存获或者修改菜单的信息*/
    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu) {

        if (menu.getId() == null) {

            menuService.saveMenu(menu);
            return new ResponseResult(true, 200, "响应成功", null);
        } else {

            menuService.updateMenu(menu);
            return new ResponseResult(true, 200, "响应成功", null);

        }

    }

}
