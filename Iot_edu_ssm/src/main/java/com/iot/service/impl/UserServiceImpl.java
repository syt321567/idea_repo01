package com.iot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iot.dao.UserMapper;
import com.iot.domain.*;
import com.iot.service.UserService;
import com.iot.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserMapper userMapper;

    @Override
    public PageInfo<User> findAllUserByPage(UserVO userVO) {

        /*进行分页的配置*/
        PageHelper.startPage(userVO.getCurrentPage(), userVO.getPageSize());

        //进行查询

        List<User> allUserByPage = userMapper.findAllUserByPage();

        PageInfo<User> pageInfo = new PageInfo<>(allUserByPage);

        return pageInfo;


    }

    @Override
    public void updateUserStatus(int id, String status) {

        User user = new User();
        user.setId(id);
        user.setUpdate_time(new Date());
        user.setStatus(status);

        userMapper.updateUserStatus(user);

    }

    @Override
    public User login(User user) throws Exception {

        User user1 = userMapper.login(user);
        System.out.println(user1);
        System.out.println(user1.getPassword());
        //通过md5算法进行判断  密码是否相同
        if (user1 != null && Md5.verify(user.getPassword(), "lagou", user1.getPassword())) {
            return user1;
        }
        return null;
    }

    @Override
    public void updateUserPassword(UserVO userVO) {
        //将密码加密的之后插入数据库中
        String password = userVO.getPassword();
        try {
            password = Md5.md5(password, "lagou");
            System.out.println(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userVO.setPassword(password);

        userMapper.updateUserPassword(userVO);
    }

    @Override
    public List<Role> findUserRelationRoleById(int id) {
        return userMapper.findUserRelationRoleById(id);
    }

    @Override
    public void userContextRole(UserVO userVo) {

        //首先清空id对应的关系表的数据
        userMapper.deleteUserContextRole(userVo.getUserId());

        //再将数据进行添加到角色和用户的关联表中


        /*对数据进行赋值的操作*/
        List<Integer> roleIdList = userVo.getRoleIdList();

        for (Integer roleId : roleIdList) {

            User_Role_relation userRoleRelation = new User_Role_relation();
            Date date = new Date();
            userRoleRelation.setUserId(userVo.getUserId());
            userRoleRelation.setRoleId(roleId);
            userRoleRelation.setCreatedTime(date);
            userRoleRelation.setUpdatedTime(date);
            userRoleRelation.setCreatedBy("system");
            userRoleRelation.setUpdatedby("system");

            userMapper.userContextRole(userRoleRelation);

        }

    }

    @Override
    public ResponseResult getUserPermissions(Integer id) {
        //1.获取当前用户拥有的角色
        List<Role> roleList = userMapper.findUserRelationRoleById(id);

        /*创建一个集合封装到 角色id*/
        List<Integer> list = new ArrayList<>();

        /*2.将角色的id分装到list集和中*/
        for (Role role : roleList) {
            list.add(role.getId());
        }

        /*3.根据角色的id查询 顶级的菜单*/
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(list);

        /*4.为顶级的菜单封装对应的子集菜单*/
        for (Menu menu : parentMenu) {
            List<Menu> subMenuByPid = userMapper.findSubMenuByPid(menu.getId());
            menu.setMenuList(subMenuByPid);

        }
        //5.获取资源权限
        List<Resource> resourceList = userMapper.findResourceByRoleId(list);

        //6.封装数据
        Map<String, Object> map = new HashMap<>();
        //menuList: 菜单权限数据
        map.put("menuList", parentMenu);
        //resourceList: 资源权限数据
        map.put("resourceList", resourceList);

        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;

    }

}

